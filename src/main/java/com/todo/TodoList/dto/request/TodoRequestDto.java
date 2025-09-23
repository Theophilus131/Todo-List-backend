package com.todo.TodoList.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TodoRequestDto {

    private String title;
    private String description;
    private LocalDate dueDate;
    private String userId;
}
