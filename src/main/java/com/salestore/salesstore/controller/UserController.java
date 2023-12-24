package com.salestore.salesstore.controller;

import com.salestore.salesstore.dto.UserAttDto;
import com.salestore.salesstore.exception.ErrorMessage;
import com.salestore.salesstore.model.UserTable;
import com.salestore.salesstore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Retrieve user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserTable.class))
            }),
            @ApiResponse(responseCode = "404", description = "User not found in database", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserTable.class))
            })
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserTable> getUserById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(userService.findUserById(id).getBody());
        } catch (ErrorMessage errorMessage) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Update user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserAttDto.class))
            }),
            @ApiResponse(responseCode = "409", description = "Email already in database", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserAttDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Bad request to API", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
            })
    })
    @PutMapping(value = "/{id}", produces = "application/json", consumes = { "application/json", "application/x-www-form-urlencoded" })
    public ResponseEntity<String> attUser(@PathVariable("id")Long id, @RequestBody UserAttDto userAttDto){
        try {
            userService.attUser(id, userAttDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (ErrorMessage errorMessage) {
            return ResponseEntity.badRequest().body(errorMessage.getMessage());
        }
    }

}
