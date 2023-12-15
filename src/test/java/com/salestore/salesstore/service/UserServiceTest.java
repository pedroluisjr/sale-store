package com.salestore.salesstore.service;

import com.salestore.salesstore.dto.UserDto;
import com.salestore.salesstore.model.UserTable;
import com.salestore.salesstore.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks //Classe que estou realizando os testes.
    private UserService userService;

    @Mock //Instancia o objeto na classe UserServiceTest
    private UserRepository userRepository;

    @Captor
    private ArgumentCaptor<UserTable> userCaptor; //"Captura" o objeto UserDto, porque estou passando como parâmetro na classe userService.createUser.

    @Test
    void createUserTest() {

        //ARRANGE
        UserDto userDto = new UserDto("Pedro", "teste123", "pedro@pedro.com.br", "Pedro Luiz", "Silva");

        //ACT
        userService.createUser(userDto);

        //ASSERT
        then(userRepository).should().save(userCaptor.capture()); //O .save é o parametro que quero realizar o teste.
        UserTable savedUser = userCaptor.getValue(); //Pegar o valor do UserDto.
        Assertions.assertEquals(userDto.getLogin(), savedUser.getLogin());
        Assertions.assertEquals(userDto.getName(), savedUser.getName());
        Assertions.assertEquals(userDto.getPassword(), savedUser.getPassword());
        Assertions.assertEquals(userDto.getEmail(), savedUser.getEmail());
        Assertions.assertEquals(userDto.getSurname(), savedUser.getSurname());

    }

}