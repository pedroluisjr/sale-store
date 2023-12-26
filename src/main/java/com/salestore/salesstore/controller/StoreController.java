package com.salestore.salesstore.controller;

import com.salestore.salesstore.dto.StoreDto;
import com.salestore.salesstore.exception.ErrorMessage;
import com.salestore.salesstore.service.StoreService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @Operation(summary = "Create a store")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created succesfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StoreDto.class))
            }),
            @ApiResponse(responseCode = "403", description = "Not authenticated user", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StoreDto.class))
            }),
            @ApiResponse(responseCode = "409", description = "Conflict with cnpj", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StoreDto.class))
            })
    })
    @PostMapping(produces = "application/json", consumes = { "application/json", "application/x-www-form-urlencoded" })
    @Parameter(description = "Create a store", required = true)
    @Transactional
    public ResponseEntity<String> createStore(@RequestBody @Valid StoreDto storeDto) {
        try {
            storeService.createStore(storeDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ErrorMessage errorMessage) {
            return ResponseEntity.badRequest().body(errorMessage.getMessage());
        }
    }

}
