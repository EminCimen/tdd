package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.model.Person;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@Service
public class GreetingService {
    
    protected static String BOOKS_FILE_PATH = "books.txt";

    public String greetPerson(Person person) {
        if (person.getAge() < 18) {
            //db insert
            throw new IllegalArgumentException("Age must be 18 or older");
        }
        return String.format("Merhaba %s, hoş geldiniz!", person.getName());
    }

    public void saveBookToFile(Book book) throws IOException {
        if (book.getName() == null || book.getName().trim().isEmpty() ||
            book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            throw new IllegalArgumentException("Kitap adı ve yazar boş olamaz");
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(BOOKS_FILE_PATH, true))) {
            writer.println(book.getName() + "," + book.getAuthor());
        }
    }

    public String greetBook(Book book) {
        if (book.getName() != null && book.getName().equals(book.getAuthor())) {
            return "Merhaba " + book.getName();
        }
        return "Merhaba Yabancı";
    }

    public boolean isValid(Book book) {
        return book.getName() != null;
    }
} 