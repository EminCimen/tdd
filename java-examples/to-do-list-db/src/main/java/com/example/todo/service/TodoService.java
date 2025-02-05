package com.example.todo.service;

import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    
    private final TodoRepository todoRepository;
    
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
    
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }
    
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }
    
    public Todo getTodoById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
    }
    
    public Todo updateTodo(Long id, Todo todoDetails) {
        Todo todo = getTodoById(id);
        todo.setTitle(todoDetails.getTitle());
        todo.setDescription(todoDetails.getDescription());
        return todoRepository.save(todo);
    }
    
    public void deleteTodo(Long id) {
        Todo todo = getTodoById(id);
        todoRepository.deleteById(id);
    }
} 