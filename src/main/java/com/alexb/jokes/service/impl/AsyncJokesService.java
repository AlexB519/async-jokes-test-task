package com.alexb.jokes.service.impl;

import com.alexb.jokes.dto.JokeDto;
import com.alexb.jokes.service.JokesService;
import com.alexb.jokes.service.executor.AsyncJokeApiExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class AsyncJokesService implements JokesService {
    private final AsyncJokeApiExecutor asyncJokeApiExecutor;

    public AsyncJokesService(AsyncJokeApiExecutor asyncJokeApiExecutor) {
        this.asyncJokeApiExecutor = asyncJokeApiExecutor;
    }

    public List<JokeDto> getJokes(int count) {
        if (count > 100) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't obtain more than 100 jokes in one request");
        }

        List<JokeDto> jokes = new ArrayList<>();
        for (int i = 0; i < count; i += 10) {
            int batchSize = Math.min(10, count - i);
            List<JokeDto> batch = getJokesBatch(batchSize);
            jokes.addAll(batch);
        }

        return jokes;
    }

    private List<JokeDto> getJokesBatch(int batchSize) {
        List<CompletableFuture<JokeDto>> futures = new ArrayList<>();
        for (int i = 0; i < batchSize; i++) {
            CompletableFuture<JokeDto> future = asyncJokeApiExecutor.getJokeDtoAsync();
            futures.add(future);
        }
        return getAllJokeDtos(futures);
    }

    private List<JokeDto> getAllJokeDtos(List<CompletableFuture<JokeDto>> futures) {
        return futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
}
