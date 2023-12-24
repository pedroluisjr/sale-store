package com.salestore.salesstore.dto;

import lombok.Getter;

@Getter
public enum UserRoleDto {

    ADMIN("admin"),
    USER("user");

    private String role;

    UserRoleDto(String role) {
        this.role = role;
    }

}
