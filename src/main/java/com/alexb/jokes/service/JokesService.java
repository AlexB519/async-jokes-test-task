package com.alexb.jokes.service;

import com.alexb.jokes.dto.JokeDto;

import java.util.List;

public interface JokesService {
    List<JokeDto> getJokes(int count);
}
