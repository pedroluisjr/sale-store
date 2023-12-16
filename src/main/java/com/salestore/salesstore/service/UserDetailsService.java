package com.salestore.salesstore.service;

import com.salestore.salesstore.model.UserTable;
import com.salestore.salesstore.repository.UserRepository;
import com.salestore.salesstore.security.UserDetailsSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository repository;

    public UserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserTable> userTable = repository.findByLogin(username);

        if (userTable.isEmpty()) {
            throw new UsernameNotFoundException("Usuário: "+username+" não existe");
        }

        return new UserDetailsSecurity(userTable.get()) {
        };
    }
}