package com.lorenzoMrt.url_shortener.domain;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenerService {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = ALPHABET.length();

    public String encode(String originalUrl) {
        return DigestUtils.sha256Hex(originalUrl).substring(0, 8);
    }

    public long decode(String shortUrl) {
        long id = 0;
        for (int i = 0; i < shortUrl.length(); i++) {
            id = id * BASE + ALPHABET.indexOf(shortUrl.charAt(i));
        }
        return id;
    }
}
