package com.lorenzoMrt.url_shortener.application;


import com.lorenzoMrt.url_shortener.domain.Url;
import com.lorenzoMrt.url_shortener.domain.UrlRepository;
import com.lorenzoMrt.url_shortener.domain.UrlShortenerService;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class ShortenUrlUseCase {
    private final UrlShortenerService urlShortenerService;
    private final UrlRepository urlRepository;

    public ShortenUrlUseCase(UrlShortenerService urlShortenerService, UrlRepository urlRepository) {
        this.urlShortenerService = urlShortenerService;
        this.urlRepository = urlRepository;
    }

    public String execute(String originalUrl, LocalDateTime creationTime) {
        var baseUrl = "https://qr-k.xyz/";
        var shortUrl = urlShortenerService.encode(originalUrl);
        
        var url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortUrl(shortUrl);
        url.setCreationDateTime(creationTime);

        urlRepository.save(url);
        return baseUrl + url.getShortUrl();
    }
}

