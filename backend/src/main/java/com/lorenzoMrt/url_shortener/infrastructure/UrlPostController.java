package com.lorenzoMrt.url_shortener.infrastructure;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lorenzoMrt.url_shortener.application.ShortenUrlUseCase;
import com.lorenzoMrt.url_shortener.infrastructure.requests.ShortenRequest;

@RestController
@RequestMapping("/api/qrk")
public class UrlPostController {

    private final ShortenUrlUseCase shortenUrlUseCase;

    public UrlPostController(ShortenUrlUseCase shortenUrlUseCase) {
        this.shortenUrlUseCase = shortenUrlUseCase;
    }

    @PostMapping("/shorten")
    public ResponseEntity<String> shorten(@RequestBody ShortenRequest request) {
        var shortUrl = shortenUrlUseCase.execute(request.originalUrl(), request.creationTime());
        return ResponseEntity.ok(shortUrl);
    }
}
