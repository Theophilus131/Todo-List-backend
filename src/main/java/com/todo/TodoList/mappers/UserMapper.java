package com.todo.TodoList.mappers;

import com.todo.TodoList.data.models.User;
import com.todo.TodoList.dto.request.UserRequestDto;
import com.todo.TodoList.dto.response.UserResponseDto;


public class UserMapper {

    public static User toEntity(UserRequestDto userRequest){
        User user = new User();

        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

        return user;
    }

    public static UserResponseDto toDto(User user){
        UserResponseDto userResponse = new UserResponseDto();

        userResponse.setId(user.getUserid());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setCreatedAt(user.getCreatedAt().toLocalDate());

        return userResponse;
    }
}
