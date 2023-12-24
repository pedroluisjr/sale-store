package com.salestore.salesstore.controller;

import com.salestore.salesstore.dto.AuthenticationDto;
import com.salestore.salesstore.dto.UserDto;
import com.salestore.salesstore.exception.ErrorMessage;
import com.salestore.salesstore.exception.LoginResponseMessage;
import com.salestore.salesstore.model.UserTable;
import com.salestore.salesstore.security.TokenService;
import com.salestore.salesstore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    private final UserService userService;

    public AuthenticationController(AuthenticationManager authenticationManager,
                                    TokenService tokenService,
                                    UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Operation(summary = "Create a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created succesfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
            }),
            @ApiResponse(responseCode = "403", description = "Not authenticated user", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
            }),
            @ApiResponse(responseCode = "409", description = "Conflict with login or email", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
            })
    })
    @PostMapping(value = "/register", produces = "application/json", consumes = { "application/json", "application/x-www-form-urlencoded" })
    @Parameter(description = "Create a user", required = true)
    @Transactional
    public ResponseEntity<String> createUser(@RequestBody @Valid UserDto userDto) {
        try {
            userService.createUser(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ErrorMessage errorMessage) {
            return ResponseEntity.badRequest().body(errorMessage.getMessage());
        }
    }

    @Operation(summary = "Login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved token", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationDto.class))
            }),
            @ApiResponse(responseCode = "403", description = "User not exist", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationDto.class))
            })
    })
    @Parameter(description = "Login", required = true)
    @PostMapping(value = "/login", produces = "application/json", consumes = { "application/json", "application/x-www-form-urlencoded" })
    public ResponseEntity<LoginResponseMessage> login(@RequestBody @Valid AuthenticationDto authenticationDto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDto.getLogin(), authenticationDto.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserTable) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseMessage(token));
    }

}
