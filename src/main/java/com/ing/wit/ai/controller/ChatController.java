package com.ing.wit.ai.controller;

import com.ing.wit.ai.dto.ChatRequest;
import com.ing.wit.ai.dto.ChatResponse;
import com.ing.wit.ai.service.ChatService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/health")
    public String health() { return "UP"; }

    @PostMapping("/chat")
    public ChatResponse chat(@Valid @RequestBody ChatRequest request) {
        long start = System.currentTimeMillis();
        return new ChatResponse(chatService.chat(request.message()), System.currentTimeMillis() - start);
    }
}