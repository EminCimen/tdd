package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.service.GreetingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GreetingControllerTest {

    @Mock
    private GreetingService greetingService;

    @InjectMocks
    private GreetingController greetingController;

    @Test
    void whenValidPerson_thenReturnOkResponse() {
        // given
        Person person = new Person("John", 20);
        when(greetingService.greetPerson(any(Person.class)))
            .thenReturn("Merhaba John, hoş geldiniz!");

        // when
        ResponseEntity<String> response = greetingController.greetPerson(person);

        // then
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals("Merhaba John, hoş geldiniz!", response.getBody());
    }

    @Test
    void whenInvalidAge_thenReturnBadRequest() {
        // given
        Person person = new Person("John", 16);
        when(greetingService.greetPerson(any(Person.class)))
            .thenThrow(new IllegalArgumentException("Age must be 18 or older"));

        // when
        ResponseEntity<String> response = greetingController.greetPerson(person);

        // then
        assertTrue(response.getStatusCode().is4xxClientError());
        assertEquals("Age must be 18 or older", response.getBody());
    }
} 