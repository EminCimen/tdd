package com.example.demo.service;

import com.example.demo.model.Todo;
import com.example.demo.repository.TodoRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    
    private final TodoRepository todoRepository;
    
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
    
    public Todo createTodo(Todo todo) {
        validateTodo(todo);
        return todoRepository.save(todo);
    }
    
    private void validateTodo(Todo todo) {
        if (todo == null) {
            throw new IllegalArgumentException("Todo cannot be null");
        }
        
        if (todo.getTitle() == null || todo.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Todo title cannot be empty");
        }

        if (todo.getTitle().trim().toLowerCase().contains("emin")) {
            throw new IllegalArgumentException("Todo title cannot contain the word 'emin'");
        }

        if (todo.getTitle().trim().length() < 5) {
            throw new IllegalArgumentException("Todo title must be at least 5 characters long");
        } 
    }
} 