package com.example.todo.service;

import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {
    
    @Mock
    private TodoRepository todoRepository;
    
    @InjectMocks
    private TodoService todoService;
    
    @Test
    void shouldCreateTodo() {
        // given
        Todo todo = Todo.builder()
                .title("Test Todo")
                .description("Test Description")
                .build();
        
        Todo savedTodo = Todo.builder()
                .id(1L)
                .title("Test Todo")
                .description("Test Description")
                .build();
        
        when(todoRepository.save(any(Todo.class))).thenReturn(savedTodo);
        
        // when
        Todo result = todoService.createTodo(todo);
        
        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getTitle()).isEqualTo("Test Todo");
        assertThat(result.getDescription()).isEqualTo("Test Description");
    }
    
    @Test
    void shouldGetAllTodos() {
        // given
        Todo todo1 = Todo.builder()
                .id(1L)
                .title("Todo 1")
                .description("Description 1")
                .build();
        
        Todo todo2 = Todo.builder()
                .id(2L)
                .title("Todo 2")
                .description("Description 2")
                .build();
        
        when(todoRepository.findAll()).thenReturn(Arrays.asList(todo1, todo2));
        
        // when
        List<Todo> result = todoService.getAllTodos();
        
        // then
        assertThat(result).hasSize(2);
        assertThat(result).contains(todo1, todo2);
    }
    
    @Test
    void shouldGetTodoById() {
        // given
        Todo todo = Todo.builder()
                .id(1L)
                .title("Test Todo")
                .description("Test Description")
                .build();
        
        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));
        
        // when
        Todo result = todoService.getTodoById(1L);
        
        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
    }
    
    @Test
    void shouldUpdateTodo() {
        // given
        Todo existingTodo = Todo.builder()
                .id(1L)
                .title("Old Title")
                .description("Old Description")
                .build();
                
        Todo updatedTodo = Todo.builder()
                .id(1L)
                .title("New Title")
                .description("New Description")
                .build();
        
        when(todoRepository.findById(1L)).thenReturn(Optional.of(existingTodo));
        when(todoRepository.save(any(Todo.class))).thenReturn(updatedTodo);
        
        // when
        Todo result = todoService.updateTodo(1L, updatedTodo);
        
        // then
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("New Title");
        assertThat(result.getDescription()).isEqualTo("New Description");
    }
    
    @Test
    void shouldDeleteTodo() {
        // given
        Todo todo = Todo.builder()
                .id(1L)
                .title("Test Todo")
                .description("Test Description")
                .build();
        
        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));
        doNothing().when(todoRepository).deleteById(1L);
        
        // when
        todoService.deleteTodo(1L);
        
        // then
        verify(todoRepository, times(1)).deleteById(1L);
    }
} 