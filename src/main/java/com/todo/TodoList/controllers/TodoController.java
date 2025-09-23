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
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoServices todoServices;

    @PostMapping
    public TodoResponseDto createTodo(@Valid @RequestBody TodoRequestDto requestDto,
                                      @RequestParam String userId) {
        return todoServices.addTodo(requestDto, userId);
    }

    @GetMapping
    public List<TodoResponseDto> getUserTodos(@RequestParam String userId) {
        return todoServices.getTodosByUser(userId);
    }

    @GetMapping("/{id}")
    public TodoResponseDto getTodoById(@PathVariable String id,
                                       @RequestParam String userId) {
        return todoServices.getTodoById(id, userId);
    }

    @PutMapping("/{id}")
    public TodoResponseDto updateTodo(@PathVariable String id,
                                      @Valid @RequestBody TodoRequestDto requestDto,
                                      @RequestParam String userId) {
        return todoServices.updateTodo(id, requestDto, userId);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable String id,
                           @RequestParam String userId) {
        todoServices.deleteTodo(id, userId);
    }

    @PatchMapping("/{id}/complete")
    public TodoResponseDto markAsCompleted(@PathVariable String id,
                                           @RequestParam String userId) {
        return todoServices.markAsCompleted(id, userId);
    }
}
