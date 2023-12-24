package com.salestore.salesstore.service;

import com.salestore.salesstore.dto.UserAttDto;
import com.salestore.salesstore.dto.UserDto;
import com.salestore.salesstore.dto.UserRoleDto;
import com.salestore.salesstore.model.UserTable;
import com.salestore.salesstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void createUser (UserDto userDto) {

        if (userRepository.existsByLoginOrEmail(userDto.getLogin(), userDto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Login or email already in the database");
        } //Verificado se o login ou email já existe no banco.

            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userDto.setRole(userDto.getRole().toUpperCase());
            userRepository.save(userDto.toUser());
            ResponseEntity.status(HttpStatus.CREATED).build();

    }

    public ResponseEntity<UserTable> findUserById(Long id) {

        UserTable existUser = userRepository.findById(id) //Verifica se o id do usuário existe, caso não exista retorna not found.
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

            return ResponseEntity.ok(existUser);
    }

    public void attUser(Long id, UserAttDto userAttDto) {

        UserTable existingUser = userRepository.findById(id) //Verifica se o id do usuário existe, caso não exista retorna not found.
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        userRepository.findByEmailOrId(userAttDto.getEmail(), id) //Verifica se o email existe em outros id's além do enviado na request
                .ifPresent(user -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in database"); //Caso exista retorna Status.CONFLICT
                });

            existingUser.setEmail(userAttDto.getEmail());
            existingUser.setName(userAttDto.getName());
            existingUser.setPassword(passwordEncoder.encode(userAttDto.getPassword())); //Pega a senha enviada no userAttDto e realiza o encode e salva no objeto existingUser
            existingUser.setSurname(userAttDto.getSurname());
            existingUser.setRole(UserRoleDto.valueOf(userAttDto.getRole()));

            userRepository.save(existingUser); //Salva o existingUser no banco

        ResponseEntity.ok().build();

    }

}
