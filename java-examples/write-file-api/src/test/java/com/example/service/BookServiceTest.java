package com.example.service;

import com.example.model.Book;
import com.example.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void shouldSaveBook() {
        Book book = new Book("Test Title", "Test Author");
        
        bookService.saveBook(book);
        
        verify(bookRepository).save(book);
    }
} 