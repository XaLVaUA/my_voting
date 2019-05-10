package com.yasoft.voting.services;

import com.yasoft.voting.models.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChatService {
    void sendMessage(Long userId, String message);
    List<InnerMessage> getAllMessages();
    void createUser(String name, String password, int level);
    InnerUser loginUser(String name, String password);
    void createPoll(Long userId, String title, String text);
    void answerPoll(Long userId, Long pollId, boolean vote);
    List<InnerPoll> getAllPolls();
    void addCensorPhrase(String phrase);
    List<InnerPollAnswer> getPollAnswers(Long userId);
}
