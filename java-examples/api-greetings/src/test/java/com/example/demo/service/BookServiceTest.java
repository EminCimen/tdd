package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void itShouldThrowExceptionWhenBookNameIsLessThan3Characters() {
        Book book = new Book();
        book.setName("Te");
        book.setAuthor("Test Author");
        book.setPublicationYear(2020);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> bookService.saveBook(book));
        assertEquals("Kitap ismi en az 3 karakter olmalıdır", exception.getMessage());
    }

    @Test
    public void itShouldChangeBookNameToMehmetWhenBookNameIsAhmet() {
        Book book = new Book();
        book.setName("Ahmet");
        book.setAuthor("Test Author");
        book.setPublicationYear(2020);

        Book savedBook = new Book();
        savedBook.setName("Mehmet");
        savedBook.setAuthor("Test Author");
        savedBook.setPublicationYear(2020);

        when(bookRepository.save(book)).thenReturn(savedBook);

        Book outputBook = bookService.saveBook(book);
        assertEquals("Mehmet", outputBook.getName());
    }
} 
