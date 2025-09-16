package com.todo.TodoList.service;

import com.todo.TodoList.data.models.User;
import com.todo.TodoList.data.repository.UserRepository;
import com.todo.TodoList.dto.request.LoginRequestDto;
import com.todo.TodoList.dto.request.UserRequestDto;
import com.todo.TodoList.dto.response.UserResponseDto;
import com.todo.TodoList.exceptions.InvalidCredentialsException;
import com.todo.TodoList.exceptions.UserAlreadyExistsException;
import com.todo.TodoList.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public UserResponseDto registerUser(UserRequestDto registerRequest) {
        if(userRepository.findByEmail(registerRequest.getEmail()).isPresent()){
            throw new UserAlreadyExistsException("user with email" +registerRequest.getEmail()+" already Exits");
        }

        User user = UserMapper.toEntity(registerRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User saved = userRepository.save(user);
        return UserMapper.toDto(saved);

    }

    public UserResponseDto login(LoginRequestDto loginDto) {

        Optional<User> optionalUser = userRepository.findByEmail(loginDto.getEmail());

        if (!optionalUser.isPresent()) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        // Get the user
        User user = optionalUser.get();

        boolean passwordMatches = passwordEncoder.matches(loginDto.getPassword(), user.getPassword());
        if (!passwordMatches) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        // Convert the User entity to a response DTO
        UserResponseDto dto = UserMapper.toDto(user);
        return dto;
    }


}
