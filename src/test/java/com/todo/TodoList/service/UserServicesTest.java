package com.todo.TodoList.service;

import com.todo.TodoList.data.repository.UserRepository;
import com.todo.TodoList.dto.request.UserRequestDto;
import com.todo.TodoList.dto.response.UserResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserServicesTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServices userServices;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();


    }

    @Test
    void testRegisterNewUser(){
        UserRequestDto userRequestDto = new UserRequestDto();

         userServices.registerUser(userRequestDto);
         assertEquals(1,userRepository.count());

    }



}