package com.todo.TodoList.data.models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "todoDataBase")
public class Todo {

    @Id

    private String id;
    private String title;
    private String description;
    private Boolean completed = false;
    private LocalDateTime creationDate = LocalDateTime.now();
    private LocalDateTime dueDate;
    private String userId;

}

