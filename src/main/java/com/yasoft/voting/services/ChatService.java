package com.yasoft.voting.services;

import com.yasoft.voting.models.Message;
import com.yasoft.voting.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChatService {
    void sendMessage(User user, String message);
    List<Message> getAllMessages();
    void createUser(String name, String password, int level);
    User loginUser(String name, String password);
}
