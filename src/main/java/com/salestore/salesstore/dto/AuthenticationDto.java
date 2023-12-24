package com.salestore.salesstore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDto {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String login;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

}
