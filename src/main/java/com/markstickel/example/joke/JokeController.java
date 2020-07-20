package com.markstickel.example.joke;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JokeController {

    private final JokeService jokeService;

    public JokeController(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    @GetMapping("/joke")
    public ResponseEntity<JokeResponse> getRandomQuote() {
        JokeResponse quote = new JokeResponse();
        quote.setContent(jokeService.randomJoke());
        return ResponseEntity.ok(quote);
    }
}
