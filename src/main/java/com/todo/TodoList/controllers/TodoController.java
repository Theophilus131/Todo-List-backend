package com.todo.TodoList.controllers;

import com.todo.TodoList.data.models.Todo;
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

    // GET all todos for a user
    @GetMapping
    public List<TodoResponseDto> getUserTodos(@PathVariable String userId) {
        return todoServices.getTodoByUser(userId);
    }

    // GET a single todo by id
    @GetMapping("/{id}")
    public TodoResponseDto getTodoById(@PathVariable String userId,
                                       @PathVariable String id) {
        return todoServices.getTodoById(userId, id);
    }

    // UPDATE a todo
    @PutMapping("/{id}")
    public TodoResponseDto updateTodo(@PathVariable String userId,
                                      @PathVariable String id,
                                      @Valid @RequestBody TodoRequestDto requestDto) {
        return todoServices.updateTodo(userId, id, requestDto);
    }

    // DELETE a todo
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable String userId,
                           @PathVariable String id) {
        todoServices.deleteTodo(userId, id);
    }

    // MARK AS COMPLETE
    @PutMapping("/{id}/complete")
    public TodoResponseDto markAsCompleted(@PathVariable String userId,
                                           @PathVariable String id) {
        return todoServices.markAsCompleted(userId, id);
    }
}
