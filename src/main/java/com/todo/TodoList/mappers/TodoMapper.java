package com.todo.TodoList.mappers;

import com.todo.TodoList.data.models.Todo;
import com.todo.TodoList.dto.request.TodoRequestDto;
import com.todo.TodoList.dto.response.TodoResponseDto;

public class TodoMapper {

    // convert request dto to entity
    public static Todo toEntity(TodoRequestDto todoRequest){

        Todo todo = new Todo();

        todo.setTitle(todoRequest.getTitle());
        todo.setDescription(todoRequest.getDescription());
        todo.setDueDate(todoRequest.getDueDate());

        return todo;

    }

    // convert entity to response dto
    public static TodoResponseDto toDto(Todo todo){
        TodoResponseDto ResponseDto = new TodoResponseDto();

        ResponseDto.setId(todo.getId());
        ResponseDto.setTitle(todo.getTitle());
        ResponseDto.setDescription(todo.getDescription());
        ResponseDto.setCreatedAt(todo.getCreationDate());
        ResponseDto.setDueDate(todo.getDueDate());

        return ResponseDto;

    }
}
