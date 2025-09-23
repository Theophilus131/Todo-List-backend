package com.todo.TodoList.service;


import com.todo.TodoList.data.models.Todo;
import com.todo.TodoList.data.repository.TodoRepository;
import com.todo.TodoList.dto.request.TodoRequestDto;
import com.todo.TodoList.dto.response.TodoResponseDto;
import com.todo.TodoList.exceptions.TodoNotFoundExceptions;
import com.todo.TodoList.mappers.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class TodoServices {

    @Autowired
    private TodoRepository todoRepository;

    public TodoResponseDto addTodo(TodoRequestDto requestDto, String userId) {
        Todo todo = new Todo();
        todo.setTitle(requestDto.getTitle());
        todo.setDescription(requestDto.getDescription());
        todo.setDueDate(requestDto.getDueDate());
        todo.setUserId(userId);

        Todo saved = todoRepository.save(todo);
        return TodoMapper.toDto(saved);
    }

    public List<TodoResponseDto> getTodosByUser(String userId) {
        List<Todo> todos = todoRepository.findByUserId(userId);
        List<TodoResponseDto> dtos = new ArrayList<>();

        for (Todo todo : todos) {
            TodoResponseDto dto = TodoMapper.toDto(todo);
            dtos.add(dto);
        }

        return dtos;
    }

    public TodoResponseDto getTodoById(String id, String userId) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);

        if (!optionalTodo.isPresent()) {
            throw new TodoNotFoundExceptions("Todo with id " + id + " not found");
        }

        Todo todo = optionalTodo.get();

        if (!todo.getUserId().equals(userId)) {
            throw new TodoNotFoundExceptions("Todo not found for this user");
        }

        return TodoMapper.toDto(todo);
    }

    public TodoResponseDto updateTodo(String id, TodoRequestDto requestDto, String userId) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);

        if (!optionalTodo.isPresent()) {
            throw new TodoNotFoundExceptions("Todo with id " + id + " not found");
        }

        Todo todo = optionalTodo.get();

        if (!todo.getUserId().equals(userId)) {
            throw new TodoNotFoundExceptions("Todo not found for this user");
        }

        todo.setTitle(requestDto.getTitle());
        todo.setDescription(requestDto.getDescription());
        todo.setDueDate(requestDto.getDueDate());
        todo.setCreationDate(LocalDateTime.now());

        Todo updatedTodo = todoRepository.save(todo);
        return TodoMapper.toDto(updatedTodo);
    }

    public void deleteTodo(String id, String userId) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);

        if (!optionalTodo.isPresent()) {
            throw new TodoNotFoundExceptions("Todo with id " + id + " not found");
        }

        Todo todo = optionalTodo.get();

        if (!todo.getUserId().equals(userId)) {
            throw new TodoNotFoundExceptions("Todo not found for this user");
        }

        todoRepository.delete(todo);
    }

    public TodoResponseDto markAsCompleted(String id, String userId) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);

        if (!optionalTodo.isPresent()) {
            throw new TodoNotFoundExceptions("Todo with id " + id + " not found");
        }

        Todo todo = optionalTodo.get();

        if (!todo.getUserId().equals(userId)) {
            throw new TodoNotFoundExceptions("Todo not found for this user");
        }

        todo.setCompleted(true);
        Todo update = todoRepository.save(todo);

        return TodoMapper.toDto(update);
    }
}
