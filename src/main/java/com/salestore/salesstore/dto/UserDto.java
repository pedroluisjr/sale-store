package com.salestore.salesstore.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salestore.salesstore.model.UserTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private String login;
    private String password;
    private String email;
    private String name;
    private String surname;

    public UserTable toUser(){
        return new ModelMapper().map(this, UserTable.class);
    }

    public UserDto(UserTable user){
        new ModelMapper().map(user, this);
    }

}
