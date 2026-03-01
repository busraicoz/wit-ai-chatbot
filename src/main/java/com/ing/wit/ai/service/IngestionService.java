package com.ing.wit.ai.service;

import com.ing.wit.ai.config.RagProperties;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IngestionService {
    private static final Logger log = LoggerFactory.getLogger(IngestionService.class);
    private final VectorStore vectorStore;
    private final RagProperties ragProperties;

    @Value("classpath:/documents/policy.pdf")
    private Resource pdfResource;

    public IngestionService(VectorStore vectorStore, RagProperties ragProperties) {
        this.vectorStore = vectorStore;
        this.ragProperties = ragProperties;
    }

    @PostConstruct
    public void ingestPdf() {
        log.info("Starting document ingestion for workshop...");
        try {
            if (!pdfResource.exists()) {
                log.warn("policy.pdf not found in src/main/resources/documents/!");
                return;
            }
            PagePdfDocumentReader pdfReader = new PagePdfDocumentReader(
                    pdfResource, PdfDocumentReaderConfig.builder().withPagesPerDocument(1).build()
            );
            TokenTextSplitter splitter = new TokenTextSplitter(
                    ragProperties.chunkSize(), ragProperties.chunkSize(),
                    ragProperties.overlap(), ragProperties.chunkSize(), true
            );
            List<Document> splitDocuments = splitter.apply(pdfReader.get());
            vectorStore.add(splitDocuments);
            log.info("Successfully embedded and ingested {} chunks into the Vector Store.", splitDocuments.size());
        } catch (Exception e) {
            log.error("Failed to ingest PDF: {}", e.getMessage());
        }
    }
}
