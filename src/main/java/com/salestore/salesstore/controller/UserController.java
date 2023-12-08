package com.salestore.salesstore.controller;

import com.salestore.salesstore.dto.UserDto;
import com.salestore.salesstore.exception.ErrorMessage;
import com.salestore.salesstore.model.UserTable;
import com.salestore.salesstore.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@RestController
@RequestMapping("/api/user")
@Tag(name = "user", description = "User API endpoint")
@OpenAPIDefinition(info = @Info(
        title = "Stores API",
        description = "ERP Store for studying Spring Boot",
        version = "0.0.1"))
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Create a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created succesfully"),
            @ApiResponse(responseCode = "409", description = "Conflict with login or email")
    })
    @PostMapping(consumes = { "application/json", "application/x-www-form-urlencoded" })
    @Parameter(description = "Create a user", required = true)
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
        try {
            userService.createUser(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ErrorMessage errorMessage) {
            return ResponseEntity.badRequest().body(errorMessage.getMessage());
        }
    }

}
