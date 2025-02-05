package com.example.controller;

import com.example.model.Book;
import com.example.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Test
    void shouldCreateBook() {
        Book book = new Book("Test Title", "Test Author");
        
        ResponseEntity<Book> response = bookController.createBook(book);
        
        assertEquals(200, response.getStatusCode().value());
        verify(bookService).saveBook(book);
    }
} 