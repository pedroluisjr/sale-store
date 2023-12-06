package com.salestore.salesstore.service;

import com.salestore.salesstore.dto.UserDto;
import com.salestore.salesstore.model.UserTable;
import com.salestore.salesstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public ResponseEntity<UserTable> createUser (UserDto userDto) {

        Optional<UserTable> user = userRepository.findUser(userDto.getLogin(), userDto.getEmail());

        if (user.isEmpty()) {
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userRepository.save(userDto.toUser());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Login or email already in database");
        }

    }

}
