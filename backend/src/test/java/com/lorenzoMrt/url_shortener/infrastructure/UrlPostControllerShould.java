package com.lorenzoMrt.url_shortener.infrastructure;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lorenzoMrt.url_shortener.TestcontainersConfiguration;
import com.lorenzoMrt.url_shortener.infrastructure.requests.ShortenRequest;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@AutoConfigureMockMvc
final class UrlPostControllerShould {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shorten() throws Exception {
        String originalUrl = "https://www.example.com";
        String time = "2024-08-11 05:12 pm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");

        LocalDateTime creationTime = LocalDateTime.parse(time, formatter);
        var req = new ShortenRequest(originalUrl, creationTime);
        var reqJson = objectMapper.writeValueAsString(req);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/qrk/shorten")
                        .content(reqJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(not(emptyOrNullString())));

    }
}