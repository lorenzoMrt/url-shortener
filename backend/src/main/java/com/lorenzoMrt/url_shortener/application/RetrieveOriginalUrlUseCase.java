package com.lorenzoMrt.url_shortener.application;

import org.springframework.stereotype.Component;

import com.lorenzoMrt.url_shortener.domain.UrlRepository;

@Component
public class RetrieveOriginalUrlUseCase {
    private final UrlRepository urlRepository;

    public RetrieveOriginalUrlUseCase(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String execute(String shortUrl) {
    return urlRepository.findByShortUrl(shortUrl).getOriginalUrl();
    }
}
