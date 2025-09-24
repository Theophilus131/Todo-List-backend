package com.todo.TodoList.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
public class TodoResponseDto {

    private String id;
    private String title;
    private String description;
    private LocalDate createdAt;
    private LocalDate dueDate;
    private Boolean completed;
    private String userId;
}
