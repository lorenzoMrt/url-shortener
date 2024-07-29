package com.lorenzoMrt.url_shortener.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String originalUrl;
    private String shortUrl;

    public Url() {
    }

    public Long getId() {
        return id;
    }


    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}

