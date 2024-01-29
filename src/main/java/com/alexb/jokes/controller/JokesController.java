package com.alexb.jokes.controller;

import com.alexb.jokes.dto.JokeDto;
import com.alexb.jokes.service.JokesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JokesController {

    private final JokesService jokesService;

    public JokesController(JokesService jokesService) {
        this.jokesService = jokesService;
    }

    @GetMapping("/jokes")
    public List<JokeDto> getJokes(@RequestParam(defaultValue = "5") int count) {
        return jokesService.getJokes(count);
    }
}
