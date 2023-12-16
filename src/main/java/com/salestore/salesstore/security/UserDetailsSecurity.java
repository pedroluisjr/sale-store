package com.salestore.salesstore.security;

import com.salestore.salesstore.model.UserTable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsSecurity implements UserDetails {

    private final UserTable userTable;

    public UserDetailsSecurity(UserTable userTable) {
        this.userTable = userTable;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return userTable.getPassword();
    }

    @Override
    public String getUsername() {
        return userTable.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
