package com.ing.wit.ai.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.rag")
public record RagProperties(
        int defaultTopK,
        int chunkSize,
        int overlap
) {}
