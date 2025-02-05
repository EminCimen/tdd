package com.example.demo.controller;

import com.example.demo.model.Todo;
import com.example.demo.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class TodoControllerTest {

    @Mock
    private TodoService todoService;

    @InjectMocks
    private TodoController todoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTodo_Success() {
        // Hazırlık
        Todo inputTodo = new Todo();
        inputTodo.setTitle("Test Todo");
        
        Todo savedTodo = new Todo();
        savedTodo.setId(1L);
        savedTodo.setTitle("Test Todo");

        when(todoService.createTodo(any(Todo.class))).thenReturn(savedTodo);

        // Test
        ResponseEntity<Todo> response = todoController.createTodo(inputTodo);

        // Doğrulama
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Test Todo", response.getBody().getTitle());
    }

    @Test
    void createTodo_Error() {
        // Hazırlık
        Todo inputTodo = new Todo();
        when(todoService.createTodo(any(Todo.class))).thenThrow(new RuntimeException("Test error"));

        // Test
        ResponseEntity<Todo> response = todoController.createTodo(inputTodo);

        // Doğrulama
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }
} 