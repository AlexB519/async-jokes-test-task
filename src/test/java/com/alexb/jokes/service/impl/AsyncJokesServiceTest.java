package com.alexb.jokes.service.impl;

import com.alexb.jokes.dto.JokeDto;
import com.alexb.jokes.service.executor.AsyncJokeApiExecutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AsyncJokesServiceTest {

    @Mock
    private AsyncJokeApiExecutor asyncJokeApiExecutor;

    @InjectMocks
    private AsyncJokesService asyncJokesService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetJokes() {
        JokeDto jokeDto1 = new JokeDto("Setup 1", "Punchline 1");
        JokeDto jokeDto2 = new JokeDto("Setup 2", "Punchline 2");
        Mockito.when(asyncJokeApiExecutor.getJokeDtoAsync()).thenReturn(CompletableFuture.completedFuture(jokeDto1),
                CompletableFuture.completedFuture(jokeDto2));

        List<JokeDto> result = asyncJokesService.getJokes(2);

        assertEquals(Arrays.asList(jokeDto1, jokeDto2), result);

        Mockito.verify(asyncJokeApiExecutor, Mockito.times(2)).getJokeDtoAsync();
    }

    @Test
    public void testGetJokesWithBadRequest() {
        assertThrows(ResponseStatusException.class, () -> asyncJokesService.getJokes(101));

        Mockito.verify(asyncJokeApiExecutor, Mockito.never()).getJokeDtoAsync();
    }
}
