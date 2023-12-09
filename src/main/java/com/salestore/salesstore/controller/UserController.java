package com.salestore.salesstore.controller;

import com.salestore.salesstore.dto.UserDto;
import com.salestore.salesstore.exception.ErrorMessage;
import com.salestore.salesstore.model.UserTable;
import com.salestore.salesstore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Create a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created succesfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserTable.class))
            }),
            @ApiResponse(responseCode = "409", description = "Conflict with login or email", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserTable.class))
            })
    })
    @PostMapping(consumes = { "application/json", "application/x-www-form-urlencoded" })
    @Parameter(description = "Create a user", required = true)
    @Transactional
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDto userDto) {
        try {
            userService.createUser(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ErrorMessage errorMessage) {
            return ResponseEntity.badRequest().body(errorMessage.getMessage());
        }
    }

    @Operation(summary = "Retrieve user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User exist", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserTable.class))
            }),
            @ApiResponse(responseCode = "404", description = "User not found in database", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserTable.class))
            })
    })
    @GetMapping(value = "/{id}", consumes = { "application/json", "application/x-www-form-urlencoded" })
    public ResponseEntity<UserTable> getUserById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(userService.findUserById(id).getBody());
        } catch (ErrorMessage errorMessage) {
            return ResponseEntity.notFound().build();
        }
    }

}
