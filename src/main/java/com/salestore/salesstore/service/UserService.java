package com.salestore.salesstore.service;

import com.salestore.salesstore.dto.UserDto;
import com.salestore.salesstore.exception.ErrorMessage;
import com.salestore.salesstore.model.UserTable;
import com.salestore.salesstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Bean
    BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void createUser (UserDto userDto) {

        Optional<UserTable> user = userRepository.existsByLoginOrEmail(userDto.getLogin(), userDto.getEmail());

        if (user.isEmpty()) {
            userDto.setPassword(passwordEncoder().encode(userDto.getPassword()));
            userRepository.save(userDto.toUser());
            ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Login or email already in database");
        }

    }

    public ResponseEntity<UserTable> findUserById(Long id) {

        Optional<UserTable> existUser = userRepository.findById(id);;

        if (existUser.isPresent()){
            return ResponseEntity.of(existUser);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not in database");
        }
    }

}
