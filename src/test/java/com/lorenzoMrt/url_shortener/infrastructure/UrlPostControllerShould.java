package com.lorenzoMrt.url_shortener.infrastructure;

import com.lorenzoMrt.url_shortener.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@AutoConfigureMockMvc
final class UrlPostControllerShould {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shorten() throws Exception {
        String originalUrl = "https://www.example.com";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/shorten")
                        .content(originalUrl)
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(not(emptyOrNullString())));

    }
}