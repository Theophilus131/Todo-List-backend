package com.todo.TodoList.controllers;

import com.todo.TodoList.data.models.Todo;
import com.todo.TodoList.dto.request.TodoRequestDto;
import com.todo.TodoList.dto.response.TodoResponseDto;
import com.todo.TodoList.service.TodoServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoServices todoServices;


    @PostMapping
    public TodoResponseDto createTodo(@Valid @RequestBody TodoRequestDto todoRequestDto){
        return todoServices.createTodo(todoRequestDto);
    }

    @GetMapping
    public List<TodoResponseDto> getAllTodos(){
        return todoServices.getAllTodos();
    }

    @GetMapping("/{id}")
    public TodoResponseDto getTodoById(@PathVariable String id){
        return todoServices.getTodoById(id);
    }

    @PutMapping("/{id}")
    public TodoResponseDto updateTodo(@PathVariable String id, @Valid @RequestBody TodoRequestDto todoRequestDto){
        return todoServices.updateTodo(id,todoRequestDto);
    }


    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable String id){
        todoServices.deleteTodo(id);
    }

    @PatchMapping("/{id}/complete")
    public TodoResponseDto markAsCompleted(@PathVariable String id){
        return todoServices.markAsCompleted(id);
    }



}
