package com.example.repository;

import com.example.model.Book;
import org.springframework.stereotype.Repository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@Repository
public class BookRepository {

    private static final String DEFAULT_FILE_PATH = "books.txt";

    protected String getFilePath() {
        return DEFAULT_FILE_PATH;
    }

    public void save(Book book) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(getFilePath(), true))) {
            writer.println(book.getTitle() + "," + book.getAuthor());
        } catch (IOException e) {
            throw new RuntimeException("Dosya yazma hatası", e);
        }
    }
} 