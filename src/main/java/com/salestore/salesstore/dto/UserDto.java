package com.salestore.salesstore.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salestore.salesstore.model.UserTable;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(name = "User")
public class UserDto {

    @NotBlank
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String login;

    @NotBlank
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @NotBlank
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotBlank
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String surname;

    public UserTable toUser(){
        return new ModelMapper().map(this, UserTable.class);
    }

    public UserDto(UserTable user){
        new ModelMapper().map(user, this);
    }

}
