package com.todo.TodoList.dto.response;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class TodoResponseDto {

    private String id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime dueDate;
}
