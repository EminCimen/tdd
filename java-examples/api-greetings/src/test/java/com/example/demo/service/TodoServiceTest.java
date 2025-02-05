package com.example.demo.service;

import com.example.demo.model.Todo;
import com.example.demo.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class TodoServiceTest {

    // TodoRepository'nin sahte (mock) versiyonunu oluşturur.
    // Bu sayede gerçek veritabanı işlemleri yapmadan test edebiliriz.
    @Mock
    private TodoRepository todoRepository;

    // TodoService sınıfına mock nesnelerini enjekte eder.
    // TodoService içindeki TodoRepository referansı, yukarıdaki mock nesne ile değiştirilir.
    @InjectMocks
    private TodoService todoService;

    // MockitoAnnotations.openMocks(this) çağrısı, @Mock ve @InjectMocks 
    // anotasyonlarını işleyerek mock nesnelerini başlatır ve enjekte eder
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTodo_Success() {
        // Setup
        Todo inputTodo = new Todo();
        inputTodo.setTitle("Test Todo");

        Todo savedTodo = new Todo();
        savedTodo.setId(1L);
        savedTodo.setTitle("Test Todo");

        when(todoRepository.save(any(Todo.class))).thenReturn(savedTodo);

        // Execution
        Todo result = todoService.createTodo(inputTodo);

        // Validation
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Todo", result.getTitle());
    }

    @Test
    void createTodo_ShortTitle_ThrowsException() {
        // Setup
        Todo inputTodo = new Todo();
        inputTodo.setTitle("Test"); // 4 karakter
        
        // Execution & Validation
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            todoService.createTodo(inputTodo);
        });
        
        assertEquals("Todo title must be at least 5 characters long", exception.getMessage());
    }


    @Test
    void createTodo_NullTitle_ThrowsException() {
        // Setup
        Todo inputTodo = new Todo();
        inputTodo.setTitle(null);
        
        // Execution & Validation
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            todoService.createTodo(inputTodo);
        });
        
        assertEquals("Todo title cannot be empty", exception.getMessage());
    }

    @Test
    void createTodo_NullTodo_ThrowsException() {
        // Setup & Execution & Validation
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            todoService.createTodo(null);
        });
        
        assertEquals("Todo cannot be null", exception.getMessage());
    }

    @Test
    void createTodo_TitleStartsWithEmin_ThrowsException() {
        // Setup
        Todo inputTodo = new Todo();
        inputTodo.setTitle("Emin olarak yapılacaklar");
        
        // Execution & Validation
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            todoService.createTodo(inputTodo);
        });
        
        assertEquals("Todo title cannot contain the word 'emin'", exception.getMessage());
    }
    
    @Test
    void createTodo_TitleContainsEmin_ThrowsException() {
        // Setup
        Todo inputTodo = new Todo();
        inputTodo.setTitle("Bu işten emin değilim");
        
        // Execution & Validation
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            todoService.createTodo(inputTodo);
        });
        
        assertEquals("Todo title cannot contain the word 'emin'", exception.getMessage());
    }
    
    @Test
    void createTodo_TitleEndsWithEmin_ThrowsException() {
        // Setup
        Todo inputTodo = new Todo();
        inputTodo.setTitle("İşi yapacak olan emin");
        
        // Execution & Validation
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            todoService.createTodo(inputTodo);
        });
        
        assertEquals("Todo title cannot contain the word 'emin'", exception.getMessage());
    }
} 