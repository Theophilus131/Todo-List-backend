package com.todo.TodoList.controllers;


import com.todo.TodoList.dto.request.UserRequestDto;
import com.todo.TodoList.dto.response.UserResponseDto;
import com.todo.TodoList.service.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://127.0.0.1:5501")
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
