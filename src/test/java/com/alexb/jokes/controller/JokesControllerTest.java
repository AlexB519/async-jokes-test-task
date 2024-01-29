package com.alexb.jokes.controller;

import com.alexb.jokes.dto.JokeDto;
import com.alexb.jokes.service.JokesService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

@WebMvcTest(JokesController.class)
class JokesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JokesService jokesService;

    @InjectMocks
    private JokesController jokeController;

    @Test
    public void testGetJokes__should_work_correct() throws Exception {
        JokeDto joke1 = new JokeDto("Setup 1", "Punchline 1");
        JokeDto joke2 = new JokeDto("Setup 2", "Punchline 2");
        Mockito.when(jokesService.getJokes(Mockito.anyInt())).thenReturn(Arrays.asList(joke1, joke2));

        mockMvc.perform(MockMvcRequestBuilders.get("/jokes")
                        .param("count", "2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].setup").value("Setup 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].punchline").value("Punchline 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].setup").value("Setup 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].punchline").value("Punchline 2"));

        Mockito.verify(jokesService, Mockito.times(1)).getJokes(2);
    }
}