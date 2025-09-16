package com.todo.TodoList.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestDto {

    private String password;
    private String name;

    @NotBlank(message = "required email input")
    @Email(message = "invalid email format")
    private String email;
}
