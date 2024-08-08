package com.lorenzoMrt.url_shortener.domain;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenerService {

    public String encode(String originalUrl) {
        return DigestUtils.sha256Hex(originalUrl).substring(0, 8);
    }
}
