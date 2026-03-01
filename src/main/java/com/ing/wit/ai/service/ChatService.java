package com.ing.wit.ai.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private static final Logger log = LoggerFactory.getLogger(ChatService.class);
    private final RetrievalService retrievalService;
    private final ChatModel chatModel;

    public ChatService(RetrievalService retrievalService, ChatModel chatModel) {
        this.retrievalService = retrievalService;
        this.chatModel = chatModel;
    }

    public String chat(String message) {
        // TODO: Task 6 - Retrieve the Context
        // First, we need to find the relevant information from our ING policy PDF.
        // Call retrievalService.retrieveContext(userQuery) and save it to a String variable.
        String context = "Replace me with the real context from Task 6!";

        // TODO: Task 7 - Prompt Engineering
        // Time to tell Gemini how to behave!
        // Replace this with the FINAL RAG prompt from Task 7
        // Give Gemini a persona (e.g., "You are an ING assistant"), give it strict rules
        // (like "answer ONLY from the context"), and inject your 'context' and 'userQuery'.
        String prompt = message;
        log.debug("Generated prompt for Gemini:\n{}", prompt);

        // TODO: Task 5 - Hello Gemini!
        // Let's make our first connection to the LLM.
        // To connect to Gemini, use your 'chatModel' to send the message and return the generated text.
        // Hint: chatModel.call(new Prompt(prompt))
        return "Not implemented yet. Check Task 5 in your guide to wake up Gemini!";
    }
}
