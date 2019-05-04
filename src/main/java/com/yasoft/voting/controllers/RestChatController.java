package com.yasoft.voting.controllers;

import com.yasoft.voting.models.Message;
import com.yasoft.voting.models.User;
import com.yasoft.voting.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("chat")
public class RestChatController {
    private ChatService chatService;

    @Autowired
    public RestChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("send")
    public void sendMessage(@RequestBody User user, @RequestBody String message) {
        chatService.sendMessage(user, message);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("messages")
    public @ResponseBody List<Message> getAllMessages() {
        return chatService.getAllMessages();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("user/create")
    public void createUser(@RequestBody String name, @RequestBody String password) {
        chatService.createUser(name, password, 0);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("user/login")
    public @ResponseBody User loginUser(@RequestBody String name, @RequestBody String password) {
        return chatService.loginUser(name, password);
    }
}
