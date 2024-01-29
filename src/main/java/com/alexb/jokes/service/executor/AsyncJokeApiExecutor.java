package com.alexb.jokes.service.executor;

import com.alexb.jokes.dto.JokeDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Component
public class AsyncJokeApiExecutor {

    private final RestTemplate restTemplate;
    private final String jokeDtoApiUrl;

    public AsyncJokeApiExecutor(RestTemplate restTemplate, @Value("${joke.api.url}") String jokeDtoApiUrl) {
        this.restTemplate = restTemplate;
        this.jokeDtoApiUrl = jokeDtoApiUrl;
    }

    @Async("jokeApiThreadPool")
    public CompletableFuture<JokeDto> getJokeDtoAsync() {
        try {
             // you may add System.out.println(Thread.currentThread().getName()); in order to make sure thread pool is used
            return CompletableFuture.completedFuture(restTemplate.getForObject(jokeDtoApiUrl, JokeDto.class));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(null);
        }
    }
}
