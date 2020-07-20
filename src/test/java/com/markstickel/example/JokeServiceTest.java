package com.markstickel.example;

import com.markstickel.example.joke.JokeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class JokeServiceTest {

    @InjectMocks
    private JokeService jokeService;

    @Test
    public void testRandomQuote() {
        assertNotNull(jokeService.randomJoke());
    }
}
