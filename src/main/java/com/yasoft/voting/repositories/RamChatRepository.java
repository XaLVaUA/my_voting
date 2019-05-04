package com.yasoft.voting.repositories;

import com.yasoft.voting.models.Message;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RamChatRepository implements ChatRepository {
    private List<Message> messages;

    public RamChatRepository() {
        this.messages = new ArrayList<>();
    }

    @Override
    public void addMessage(Message message) {
        messages.add(message);
    }

    @Override
    public List<Message> findAllMessages() {
        return messages;
    }
}
