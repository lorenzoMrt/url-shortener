package com.lorenzoMrt.url_shortener.infrastructure.requests;

import java.time.LocalDateTime;

public record ShortenRequest(String originalUrl, LocalDateTime creationTime) {

}
