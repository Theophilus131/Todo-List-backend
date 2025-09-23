package com.todo.TodoList.service;


import com.todo.TodoList.data.models.Todo;
import com.todo.TodoList.data.repository.TodoRepository;
import com.todo.TodoList.dto.request.TodoRequestDto;
import com.todo.TodoList.dto.response.TodoResponseDto;
import com.todo.TodoList.exceptions.TodoNotFoundExceptions;
import com.todo.TodoList.mappers.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServices {

    @Autowired
    private TodoRepository todoRepository;

    public TodoResponseDto createTodo(String userId, TodoRequestDto requestDto) {
        Todo todo = new Todo();
        todo.setTitle(requestDto.getTitle());
        todo.setDescription(requestDto.getDescription());
        todo.setDueDate(requestDto.getDueDate());
        todo.setUserId(userId);

        Todo saved = todoRepository.save(todo);
        return TodoMapper.toDto(saved);
    }

    public List<TodoResponseDto> getTodoByUser(String userId) {
        List<Todo> todos = todoRepository.findByUserId(userId);
        List<TodoResponseDto> dtos = new ArrayList<>();

        for (Todo todo : todos) {
            TodoResponseDto dto = TodoMapper.toDto(todo);
            dtos.add(dto);
        }

        return dtos;
    }

    public TodoResponseDto getTodoById(String userId, String id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);

        if (!optionalTodo.isPresent()) {
            throw new TodoNotFoundExceptions("Todo with id " + id + " not found");
        }

        Todo todo = optionalTodo.get();

        if (!todo.getUserId().equals(userId)) {
            throw new TodoNotFoundExceptions("This todo does not belong to user " + userId);
        }

        return TodoMapper.toDto(todo);
    }

    public TodoResponseDto updateTodo(String userId, String id, TodoRequestDto requestDto) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);

        if (!optionalTodo.isPresent()) {
            throw new TodoNotFoundExceptions("Todo with id " + id + " not found");
        }

        Todo todo = optionalTodo.get();

        if (!todo.getUserId().equals(userId)) {
            throw new TodoNotFoundExceptions("You are not allowed to update this todo");
        }

        todo.setTitle(requestDto.getTitle());
        todo.setDescription(requestDto.getDescription());
        todo.setDueDate(requestDto.getDueDate());
        todo.setCreationDate(LocalDate.from(LocalDateTime.now()));

        Todo updatedTodo = todoRepository.save(todo);
        return TodoMapper.toDto(updatedTodo);
    }

    public void deleteTodo(String userId, String id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);

        if (!optionalTodo.isPresent()) {
            throw new TodoNotFoundExceptions("Todo with id " + id + " not found");
        }

        Todo todo = optionalTodo.get();

        if (!todo.getUserId().equals(userId)) {
            throw new TodoNotFoundExceptions("You are not allowed to delete this todo");
        }

        todoRepository.delete(todo);
    }

    public TodoResponseDto markAsCompleted(String userId, String id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);

        if (!optionalTodo.isPresent()) {
            throw new TodoNotFoundExceptions("Todo with id " + id + " not found");
        }

        Todo todo = optionalTodo.get();

        if (!todo.getUserId().equals(userId)) {
            throw new TodoNotFoundExceptions("You are not allowed to complete this todo");
        }

        todo.setCompleted(true);
        Todo updatedTodo = todoRepository.save(todo);

        return TodoMapper.toDto(updatedTodo);
    }
}
