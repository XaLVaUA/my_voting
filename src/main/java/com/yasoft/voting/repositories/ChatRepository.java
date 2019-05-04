package com.yasoft.voting.repositories;

import com.yasoft.voting.models.Message;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository {
    void addMessage(Message message);
    List<Message> findAllMessages();
}
