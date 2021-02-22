package com.example.felece.Repository;

import com.example.felece.Model.Todo;

import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {
    Todo findById(Integer id);
	Todo findByTodo(String todo);
}