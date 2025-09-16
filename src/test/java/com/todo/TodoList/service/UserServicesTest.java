package com.todo.TodoList.service;

import com.todo.TodoList.data.repository.UserRepository;
import com.todo.TodoList.dto.request.UserRequestDto;
import com.todo.TodoList.dto.response.UserResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class UserServicesTest {
    private UserRepository userRepository;
    private UserServices userServices;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userServices = new UserServices();
//        userServices.userRepository = userRepository;

    }

    @Test
    void testRegisterNewUser(){
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName("theo umar");
        userRequestDto.setEmail("theo@gmail.com");
        userRequestDto.setPassword("password1234");


    }

}