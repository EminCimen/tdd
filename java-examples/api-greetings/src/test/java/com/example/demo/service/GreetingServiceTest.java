package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class GreetingServiceTest {

    private GreetingService greetingService = new GreetingService();

    @Test
    void whenPersonAgeIsLessThan18_thenThrowException() {
        Person person = new Person();
        person.setName("John");
        person.setAge(17);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            greetingService.greetPerson(person);
        });

        assertEquals("Age must be 18 or older", exception.getMessage());
    }

    @Test
    void whenPersonAgeIsGreaterThanOrEqualTo18_thenReturnGreeting() {
        Person person = new Person();
        person.setName("John");
        person.setAge(18);

        String greeting = greetingService.greetPerson(person);

        assertEquals("Merhaba John, hoş geldiniz!", greeting);
    }

    @Test
    void whenBookTitleAndAuthorAreValid_thenSaveToFile(@TempDir Path tempDir) throws IOException {
        // Test öncesi BOOKS_FILE_PATH'i geçici dosya yoluna ayarla
        GreetingService.BOOKS_FILE_PATH = tempDir.resolve("books.txt").toString();
        
        Book book = new Book();
        book.setName("Test Book");
        book.setAuthor("Test Author");

        greetingService.saveBookToFile(book);

        // Dosyadan içeriği oku
        String fileContent = Files.readString(Path.of(GreetingService.BOOKS_FILE_PATH));
        assertTrue(fileContent.contains("Test Book,Test Author"));
    }

    @Test
    void whenBookTitleOrAuthorIsEmpty_thenThrowException() {
        Book book = new Book();
        book.setName("");
        book.setAuthor("Test Author");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            greetingService.saveBookToFile(book);
        });

        assertEquals("Kitap adı ve yazar boş olamaz", exception.getMessage());
    }
} 