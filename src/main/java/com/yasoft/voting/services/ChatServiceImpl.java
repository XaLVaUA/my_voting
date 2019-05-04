package com.yasoft.voting.services;

import com.yasoft.voting.models.Message;
import com.yasoft.voting.models.User;
import com.yasoft.voting.repositories.MessageRepository;
import com.yasoft.voting.repositories.UserRepository;
import com.yasoft.voting.utils.Md5Manager;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {
    private MessageRepository messageRepository;
    private UserRepository userRepository;

    @Autowired
    public ChatServiceImpl(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void sendMessage(User user, String message) {
        if(!userRepository.existsById(user.getId())) {
            return;
        }

        Message msg = new Message();
        msg.setText(message);
        msg.setUser(user);

        messageRepository.save(msg);
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public void createUser(String name, String password, int level) {
        User user = new User();
        user.setName(name);
        String md5 = Md5Manager.getMd5(password);
        user.setPassword(md5);
        user.setLevel(level);
        userRepository.save(user);
    }

    @Override
    public User loginUser(String name, String password) {
        String md5 = Md5Manager.getMd5(password);
        Optional<User> usrs = userRepository.findByNameAndPassword(name, md5);

        if(!usrs.isPresent())
            return null;

        return usrs.get();
    }
}
