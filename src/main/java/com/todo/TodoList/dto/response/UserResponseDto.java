package com.todo.TodoList.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponseDto {
    private String id;
    private String name;
    private String email;
    private LocalDate createdAt;

}
