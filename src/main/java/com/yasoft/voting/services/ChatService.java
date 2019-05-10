package com.yasoft.voting.services;

import com.yasoft.voting.models.*;
import com.yasoft.voting.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {
    private MessageRepository messageRepository;
    private CensorService censorService;
    private UserService userService;

    @Autowired
    public ChatService(MessageRepository messageRepository, CensorService censorService, UserService userService) {
        this.messageRepository = messageRepository;
        this.censorService = censorService;
        this.userService = userService;
    }

    public void sendMessage(Long userId, String message) {
        User dbUser = userService.findUser(userId);
        if (dbUser == null) {
            return;
        }

        String validMessage = censorService.getValidString(message);

        Message msg = new Message();
        msg.setText(validMessage);
        msg.setUser(dbUser);

        messageRepository.save(msg);
    }

    public List<InnerMessage> getAllMessages() {
        List<Message> messages = messageRepository.findAll();
        List<InnerMessage> innerMessages = new ArrayList<>();
        for (Message message : messages) {
            innerMessages.add(new InnerMessage(message));
        }
        return innerMessages;
    }
}
