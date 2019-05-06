package com.yasoft.voting.controllers;

import com.yasoft.voting.models.InnerMessage;
import com.yasoft.voting.models.InnerPoll;
import com.yasoft.voting.models.InnerUser;
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
    public void sendMessage(@RequestParam Long userId, @RequestParam String message) {
        chatService.sendMessage(userId, message);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("messages")
    public @ResponseBody List<InnerMessage> getAllMessages() {
        return chatService.getAllMessages();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("user/create")
    public void createUser(@RequestParam String name, @RequestParam String password) {
        chatService.createUser(name, password, 0);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("user/login")
    public @ResponseBody InnerUser loginUser(@RequestParam String name, @RequestParam String password) {
        return chatService.loginUser(name, password);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("polls/create")
    public void createPoll(@RequestParam Long userId, @RequestParam String title, @RequestParam String text) {
        chatService.createPoll(userId, title, text);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("polls/vote")
    public void answerPoll(@RequestParam Long userId, @RequestParam Long pollId, @RequestParam boolean vote) {
        chatService.answerPoll(userId, pollId, vote);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("polls")
    public @ResponseBody List<InnerPoll> getAllPolls() {
        return chatService.getAllPolls();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("censor/add")
    public void addCensorPhrase(@RequestParam String phrase) {
        chatService.addCensorPhrase(phrase);
    }
}
