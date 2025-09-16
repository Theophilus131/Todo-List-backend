package com.todo.TodoList.service;


import com.todo.TodoList.data.models.Todo;
import com.todo.TodoList.data.repository.TodoRepository;
import com.todo.TodoList.dto.request.TodoRequestDto;
import com.todo.TodoList.dto.response.TodoResponseDto;
import com.todo.TodoList.exceptions.TodoNotFoundExceptions;
import com.todo.TodoList.mappers.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TodoServices {

    @Autowired
    private TodoRepository todoRepository;

    public TodoResponseDto createTodo(TodoRequestDto requestDto) {
        Todo todo = TodoMapper.toEntity(requestDto);
        Todo saved = todoRepository.save(todo);
        return TodoMapper.toDto(saved);
    }


    public List<TodoResponseDto> getAllTodos() {

        List<Todo> todos = todoRepository.findAll();
        List<TodoResponseDto> dtos = new ArrayList<>();

        for (Todo todo : todos) {
            TodoResponseDto dto = TodoMapper.toDto(todo);
            dtos.add(dto);
        }

        return dtos;
    }


    public TodoResponseDto getTodoById(String id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);

        if (!optionalTodo.isPresent()) {
            throw new TodoNotFoundExceptions("Todo with id " + id + " not found");
        }

        Todo todo = optionalTodo.get();

        TodoResponseDto dto = TodoMapper.toDto(todo);
        return dto;
    }


    public TodoResponseDto updateTodo(String id, TodoRequestDto requestDto) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);

        if (!optionalTodo.isPresent()) {
            throw new TodoNotFoundExceptions("Todo with id " + id + " not found");
        }

        Todo todo = optionalTodo.get();

        todo.setTitle(requestDto.getTitle());
        todo.setDescription(requestDto.getDescription());
        todo.setDueDate(requestDto.getDueDate());

        Todo updatedTodo = todoRepository.save(todo);

        TodoResponseDto dto = TodoMapper.toDto(updatedTodo);
        return dto;
    }


    public void deleteTodo(String id){
        todoRepository.deleteById(id);
    }

    public TodoResponseDto markAsCompleted(String id){
       Todo todo = todoRepository.findById(id)
               .orElseThrow(()-> new TodoNotFoundExceptions("Todo with id " + id + " not found"));

       todo.setCompleted(true);
       Todo update = todoRepository.save(todo);

       return TodoMapper.toDto(update);

    }

}
