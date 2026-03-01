package com.ing.wit.ai.service;

import com.ing.wit.ai.config.RagProperties;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RetrievalService {
    private final VectorStore vectorStore;
    private final RagProperties ragProperties;

    public RetrievalService(VectorStore vectorStore, RagProperties ragProperties) {
        this.vectorStore = vectorStore;
        this.ragProperties = ragProperties;
    }

    public String retrieveContext(String query) {
        // TODO: Task 8 - Semantic Retrieval & Tuning
        // How many chunks of text should we pull from the PDF to answer the question?
        // Right now, we are grabbing the default value from your application.properties file.
        // Later in the workshop, try changing 'app.rag.default-top-k' to 1, 3, 5 or 8
        // and see how it changes Gemini's answers!
        int k = ragProperties.defaultTopK();

        List<Document> topMatches = vectorStore.similaritySearch(
                new SearchRequest.Builder().query(query).topK(k).build()
        );
        
        return topMatches != null ? topMatches.stream()
                .map(d -> String.format("[Page %s] %s", d.getMetadata().get("page"), d.getFormattedContent()))
                .collect(Collectors.joining("\n---\n")) : null;
    }
}
