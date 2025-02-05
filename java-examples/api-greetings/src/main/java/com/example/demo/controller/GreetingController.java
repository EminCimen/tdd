package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.model.Book;
import com.example.demo.service.GreetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api")
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @PostMapping("/greet")
    public ResponseEntity<String> greetPerson(@RequestBody Person person) {
        try {
            String greeting = greetingService.greetPerson(person);
            return ResponseEntity.ok(greeting);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/books")
    public ResponseEntity<String> saveBook(@RequestBody Book book) {
        try {
            greetingService.saveBookToFile(book);
            return ResponseEntity.status(HttpStatus.CREATED).body("Kitap başarıyla kaydedildi");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Kitap kaydedilirken bir hata oluştu: " + e.getMessage());
        }
    }
} 