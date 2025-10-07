package com.example.todoApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todoApp.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
