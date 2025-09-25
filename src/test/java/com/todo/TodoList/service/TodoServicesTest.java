package com.todo.TodoList.service;

import com.todo.TodoList.data.models.User;
import com.todo.TodoList.data.repository.TodoRepository;
import com.todo.TodoList.data.repository.UserRepository;
import com.todo.TodoList.dto.request.TodoRequestDto;
import com.todo.TodoList.dto.response.TodoResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TodoServicesTest {

    @Autowired
    private TodoServices todoServices;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    private String userId;

    @BeforeEach
    void setUp() {
       User user = new User();
       user.setName("theo");
       user.setEmail("theo@12.com");
       user.setPassword("password123");
       user = userRepository.save(user);
       userId = user.getUserid();
       todoRepository.deleteAll();

    }

    @Test
    void testCrateTodo() {
        TodoRequestDto requestDto = new TodoRequestDto();

        requestDto.setTitle("buy milk");
        requestDto.setDescription("at the store");
        requestDto.setDueDate(LocalDate.now().plusDays(2));

        TodoResponseDto saved = todoServices.createTodo(userId, requestDto);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getTitle()).isEqualTo("buy milk");
        assertThat(saved.getCompleted()).isFalse();

    }

    @Test
    void getTodoByUser() {
        TodoRequestDto requestDto = new TodoRequestDto();

        requestDto.setTitle("buy milk");
        requestDto.setDescription("at the store");
        requestDto.setDueDate(LocalDate.now().plusDays(2));
        todoServices.createTodo(userId, requestDto);

        List<TodoResponseDto> todos = todoServices.getTodoByUser(userId);
        assertThat(todos.size()).isEqualTo(1);
        assertThat(todos.get(0).getTitle()).isEqualTo("buy milk");
        assertThat(todos.get(0).getCompleted()).isFalse();
    }

    @Test
    void updateTodo() {
        TodoRequestDto requestDto = new TodoRequestDto();
        requestDto.setTitle("buy milk");
        requestDto.setDescription("at the store");
        requestDto.setDueDate(LocalDate.now().plusDays(2));
        TodoResponseDto saved = todoServices.createTodo(userId, requestDto);

        requestDto.setTitle("buy eggs");
        TodoResponseDto updated = todoServices.updateTodo(userId, saved.getId(), requestDto);

        assertThat(updated.getTitle()).isEqualTo("buy eggs");
    }

    @Test
    void deleteTodo() {
        TodoRequestDto requestDto = new TodoRequestDto();
        requestDto.setTitle("buy milk");
        requestDto.setDescription("at the store");
        requestDto.setDueDate(LocalDate.now().plusDays(2));
        TodoResponseDto saved = todoServices.createTodo(userId, requestDto);

        todoServices.deleteTodo(userId, saved.getId());
        List<TodoResponseDto> todos = todoServices.getTodoByUser(userId);
        assertThat(todos.size()).isEqualTo(0);
    }

    @Test
    void markAsCompleted() {
        TodoRequestDto requestDto = new TodoRequestDto();
        requestDto.setTitle("buy milk");
        requestDto.setDescription("at the store");
        requestDto.setDueDate(LocalDate.now().plusDays(2));
        TodoResponseDto saved = todoServices.createTodo(userId, requestDto);
        TodoResponseDto completed = todoServices.markAsCompleted(userId, saved.getId());
        assertThat(completed.getCompleted()).isTrue();
    }
}