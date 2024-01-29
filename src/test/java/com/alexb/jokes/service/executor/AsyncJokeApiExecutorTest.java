package com.alexb.jokes.service.executor;

import com.alexb.jokes.configuration.AsyncConfig;
import com.alexb.jokes.dto.JokeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ContextConfiguration(classes = AsyncConfig.class)
public class AsyncJokeApiExecutorTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AsyncJokeApiExecutor asyncJokeApiExecutor;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetJokeDtoAsync() {
        JokeDto jokeDto = new JokeDto("Setup", "Punchline");
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(JokeDto.class)))
                .thenReturn(jokeDto);

        CompletableFuture<JokeDto> future = asyncJokeApiExecutor.getJokeDtoAsync();

        verify(restTemplate).getForObject(Mockito.anyString(), Mockito.eq(JokeDto.class));

        assertEquals(jokeDto, future.join());
    }
}
