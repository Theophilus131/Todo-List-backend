package com.todo.TodoList.controllers;


import com.todo.TodoList.dto.request.LoginRequestDto;
import com.todo.TodoList.dto.response.UserResponseDto;
import com.todo.TodoList.service.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://127.0.0.1:5501")
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserServices userServices;

    @PostMapping("/login")
    public UserResponseDto login(@Valid @RequestBody LoginRequestDto loginRequestDto){
        return userServices.login(loginRequestDto);
    }




}
