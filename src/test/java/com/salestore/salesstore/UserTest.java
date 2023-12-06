package com.salestore.salesstore;

import com.salestore.salesstore.dto.UserDto;
import com.salestore.salesstore.model.UserTable;
import com.salestore.salesstore.repository.UserRepository;
import com.salestore.salesstore.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.Optional;

@WebMvcTest
@AutoConfigureMockMvc
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void userPostTest() {

        UserDto userDto = new UserDto("Teste", "pedro", "pedro@grupoacert.com.br", "Pedro", "Luiz");

        Mockito.doReturn(this.userRepository.findByLogin(Mockito.anyString()))
                .when(Optional.of(userDto));

        this.userService.createUser(userDto);

        Optional<UserTable> userTable = userRepository.findByLogin("Teste");

        Assertions.assertEquals("Teste", userTable.get().getLogin());;

    }

}
