package com.example.todoApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todoApp.exception.ResourceNotFoundException;
import com.example.todoApp.model.Todo;
import com.example.todoApp.repository.TodoRepository;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/todos")

public class TodoController {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @PostMapping
    public Todo createTodo(@Valid @RequestBody Todo todo) {
        return todoRepository.save(todo);
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo with id" + id + "not found!"));
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @Valid @RequestBody Todo todoDetails) {
        Todo todo = todoRepository.findById(id).orElse(null);

        if (todo != null) {
            todo.setTitle(todoDetails.getTitle());
            todo.setCompleted(todoDetails.isCompleted());
            return todoRepository.save(todo);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoRepository.deleteById(id);
    }
}
