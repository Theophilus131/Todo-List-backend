package com.todo.TodoList.dto.request;


import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
