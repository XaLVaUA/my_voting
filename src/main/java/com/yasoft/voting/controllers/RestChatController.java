package com.yasoft.voting.controllers;

import com.yasoft.voting.models.*;
import com.yasoft.voting.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class RestChatController {
    private ChatService chatService;

    @Autowired
    public RestChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @CrossOrigin(origins = { "http://localhost:4200" })
    @PostMapping("send")
    public void sendMessage(@RequestParam Long userId, @RequestParam String message) {
        chatService.sendMessage(userId, message);
    }

    @CrossOrigin(origins = { "http://localhost:4200" })
    @GetMapping("messages")
    public @ResponseBody List<InnerMessage> getAllMessages() {
        return chatService.getAllMessages();
    }
}
