package com.todo.TodoList.controllers;


import com.todo.TodoList.dto.request.TodoRequestDto;
import com.todo.TodoList.dto.response.TodoResponseDto;
import com.todo.TodoList.service.TodoServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://127.0.0.1:5501")
@RestController
@RequestMapping("/users/{userId}/todos")
public class TodoController {

    @Autowired
    private TodoServices todoServices;

    // CREATE
    @PostMapping
    public TodoResponseDto createTodo(@PathVariable String userId,
                                      @Valid @RequestBody TodoRequestDto requestDto) {
        return todoServices.createTodo(userId, requestDto);
    }


    @GetMapping
    public List<TodoResponseDto> getUserTodos(@PathVariable String userId) {
        return todoServices.getTodoByUser(userId);
    }


    @GetMapping("/{id}")
    public TodoResponseDto getTodoById(@PathVariable String userId,
                                       @PathVariable String id) {
        return todoServices.getTodoById(userId, id);
    }


    @PutMapping("/{id}")
    public TodoResponseDto updateTodo(@PathVariable String userId,
                                      @PathVariable String id,
                                      @Valid @RequestBody TodoRequestDto requestDto) {
        return todoServices.updateTodo(userId, id, requestDto);
    }


    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable String userId,
                           @PathVariable String id) {
        todoServices.deleteTodo(userId, id);
    }


    @PutMapping("/{id}/complete")
    public TodoResponseDto markAsCompleted(@PathVariable String userId,
                                           @PathVariable String id) {
        return todoServices.markAsCompleted(userId, id);
    }
}
