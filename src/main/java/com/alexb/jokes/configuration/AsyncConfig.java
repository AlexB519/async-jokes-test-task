package com.alexb.jokes.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

    // could be modified
    @Bean(name = "jokeApiThreadPool")
    public TaskExecutor jokeApiThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(15);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("JokeApi_Async-");
        executor.initialize();
        return executor;
    }
}
