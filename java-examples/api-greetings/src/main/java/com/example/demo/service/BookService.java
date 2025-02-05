package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    //Basım yılı 2015'ten küçük olamaz
    //Kitap ismi en az 3 karakter olmalıdır
    //Kitap ve yazar ismi aynı olamaz
    //Kitap ismi tamamen büyük harflerden oluşamaz 

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book saveBook(Book book) {
        validateBook(book);
        if (book.getName().equals("Ahmet")) {
            book.setName("Mehmet");
        }
        return bookRepository.save(book);
    }

    private void validateBook(Book book) {
        if (book.getName().length() < 3) {
            throw new IllegalArgumentException("Kitap ismi en az 3 karakter olmalıdır");
        }
        if (book.getName().equals(book.getAuthor())) {
            throw new IllegalArgumentException("Kitap ve yazar ismi aynı olamaz");
        }
        if (book.getName().equals(book.getName().toUpperCase())) {
            throw new IllegalArgumentException("Kitap ismi tamamen büyük harflerden oluşamaz");
        }
        if (book.getPublicationYear() < 2015) {
            throw new IllegalArgumentException("Basım yılı 2015'ten küçük olamaz");
        }
    }
} 