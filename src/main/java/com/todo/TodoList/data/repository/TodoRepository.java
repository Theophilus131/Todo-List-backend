package com.todo.TodoList.data.repository;

import com.todo.TodoList.data.models.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends MongoRepository<Todo,String> {

    List<Todo> findByUserId(String userId);

}
