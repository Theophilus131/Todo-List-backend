package com.todo.TodoList.data.repository;

import com.todo.TodoList.data.models.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.*;


@DataMongoTest
class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    void saveTodo(){
        todoRepository.deleteAll();
        todoRepository.save(new Todo());
        assertEquals(1,todoRepository.count());
    }

}