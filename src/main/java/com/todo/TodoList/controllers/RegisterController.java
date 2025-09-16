package com.todo.TodoList.controllers;


import com.todo.TodoList.dto.request.UserRequestDto;
import com.todo.TodoList.dto.response.UserResponseDto;
import com.todo.TodoList.service.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class RegisterController {

    @Autowired
    private UserServices userService;


    @PostMapping("/registerUser")
    public UserResponseDto register(@Valid @RequestBody UserRequestDto userRequestDto){
        return userService.registerUser(userRequestDto);
    }

}
