package com.salestore.salesstore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(name = "User Actualization")
public class UserAttDto {

    private String password;

    private String email;

    private String name;

    private String surname;

}
