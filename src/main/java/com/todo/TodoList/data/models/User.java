package com.todo.TodoList.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "users")
public class User {

    @Id
    private String userid;
    private String name;
    private String email;
    private String password;
    private LocalDateTime createdAt = LocalDate.now();

    @DBRef
    private List<String> todoIds =new ArrayList<>();

}
