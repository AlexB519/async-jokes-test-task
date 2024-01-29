package com.alexb.jokes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestDemo2Application {

    public static void main(String[] args) {
        SpringApplication.from(Demo2Application::main).with(TestDemo2Application.class).run(args);
    }

}
