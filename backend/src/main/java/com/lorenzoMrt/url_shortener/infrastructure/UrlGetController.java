package com.lorenzoMrt.url_shortener.infrastructure;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lorenzoMrt.url_shortener.application.RetrieveOriginalUrlUseCase;

@RestController
@RequestMapping("/api/qrk")
public class UrlGetController {

    private final RetrieveOriginalUrlUseCase retrieveOriginalUrlUseCase;

    public UrlGetController(RetrieveOriginalUrlUseCase retrieveOriginalUrlUseCase) {
        this.retrieveOriginalUrlUseCase = retrieveOriginalUrlUseCase;
    }

    @GetMapping(value = "/{shortUrl}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getOriginalUrl(@PathVariable String shortUrl) {
        var originalUrl = retrieveOriginalUrlUseCase.execute(shortUrl);
        return ResponseEntity.ok(originalUrl);
    }
    
}
