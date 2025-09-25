package com.todo.TodoList.service;

import com.todo.TodoList.data.models.User;
import com.todo.TodoList.data.repository.UserRepository;
import com.todo.TodoList.dto.request.UserRequestDto;
import com.todo.TodoList.dto.response.UserResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.OPTIONAL;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserServicesTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServices userServices;

    private UserRequestDto userRequestDto;

    @BeforeEach
    void setUp() {
        userRequestDto = new UserRequestDto();
        userRequestDto.setName("theo");
        userRequestDto.setEmail("theo@12.com");
        userRequestDto.setPassword("password123");
        userRepository.deleteAll();


    }

    @Test
    void testRegisterNewUser(){
       UserResponseDto saved = userServices.registerUser(userRequestDto);

       assertThat(saved.getName()).isEqualTo("theo");
       assertThat(saved.getEmail()).isEqualTo("theo@12.com");


       Optional<User> userInDatabase = userRepository.findByEmail("theo@12.com");
       assertThat(userInDatabase).isPresent();

    }



}