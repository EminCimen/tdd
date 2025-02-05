package com.example.repository;

import com.example.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookRepositoryTest {

    @TempDir
    Path tempDir;

    @Test
    void shouldSaveBookToFile() throws IOException {
        Path tempFile = tempDir.resolve("books.txt");
        BookRepository repository = new BookRepository() {
            @Override
            protected String getFilePath() {
                return tempFile.toString();
            }
        };
        Book book = new Book("Test Title", "Test Author");

        repository.save(book);

        assertTrue(Files.exists(tempFile));
        List<String> lines = Files.readAllLines(tempFile);
        assertTrue(lines.get(0).contains("Test Title,Test Author"));
    }
} 