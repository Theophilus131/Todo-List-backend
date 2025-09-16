package com.todo.TodoList.exceptions;

public class TodoNotFoundExceptions extends RuntimeException {
    public TodoNotFoundExceptions(String message) {
        super(message);
    }
}
