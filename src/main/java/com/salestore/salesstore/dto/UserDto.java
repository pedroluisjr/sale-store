package com.salestore.salesstore.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salestore.salesstore.model.UserTable;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.ui.Model;

@AllArgsConstructor
@Data
@Schema(name = "User")
public class UserDto {

    private static ModelMapper modelMapper;

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

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String role;

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.createTypeMap(UserDto.class, UserTable.class)
                .addMapping(UserDto::getRole, UserTable::setRole);
    }

    public UserTable toUser(){
        return modelMapper.map(this, UserTable.class);
    }

    public UserDto(UserTable user){
        modelMapper.map(user, this);
    }

}
