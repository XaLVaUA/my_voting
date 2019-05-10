package com.yasoft.voting.services;

import com.yasoft.voting.models.*;
import com.yasoft.voting.repositories.MessageRepository;
import com.yasoft.voting.repositories.PollAnswerRepository;
import com.yasoft.voting.repositories.PollRepository;
import com.yasoft.voting.repositories.UserRepository;
import com.yasoft.voting.utils.Md5Manager;
import com.yasoft.voting.utils.CensorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {
    private MessageRepository messageRepository;
    private UserRepository userRepository;
    private PollRepository pollRepository;
    private PollAnswerRepository pollAnswerRepository;
    private CensorManager censorManager;

    @Autowired
    public ChatServiceImpl(MessageRepository messageRepository, UserRepository userRepository, PollRepository pollRepository, PollAnswerRepository pollAnswerRepository, CensorManager censorManager) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.pollRepository = pollRepository;
        this.pollAnswerRepository = pollAnswerRepository;
        this.censorManager = censorManager;
    }

    @Override
    public void sendMessage(Long userId, String message) {
        User dbUser = getUserById(userId);
        if (dbUser == null) {
            return;
        }

        String validMessage = censorManager.getValidString(message);

        Message msg = new Message();
        msg.setText(validMessage);
        msg.setUser(dbUser);

        messageRepository.save(msg);
    }

    @Override
    public List<InnerMessage> getAllMessages() {
        List<Message> messages = messageRepository.findAll();
        List<InnerMessage> innerMessages = new ArrayList<>();
        for (Message message : messages) {
            innerMessages.add(new InnerMessage(message));
        }
        return innerMessages;
    }

    @Override
    public void createUser(String name, String password, int level) {
        User user = new User();
        user.setName(name);
        String passwordMd5 = Md5Manager.getMd5(password);
        user.setPassword(passwordMd5);
        user.setLevel(level);
        userRepository.save(user);
    }

    @Override
    public InnerUser loginUser(String name, String password) {
        String passwordMd5 = Md5Manager.getMd5(password);
        Optional<User> optionalUser = userRepository.findByNameAndPassword(name, passwordMd5);

        if (!optionalUser.isPresent())
            return null;

        User dbUser = optionalUser.get();
        return new InnerUser(dbUser.getId(), dbUser.getName(), dbUser.getLevel());
    }

    @Override
    public void createPoll(Long userId, String title, String text) {
        User dbUser = getUserById(userId);
        if (dbUser == null)
            return;

        Poll poll = new Poll();
        poll.setUser(dbUser);
        poll.setTitle(title);
        poll.setText(text);
        pollRepository.save(poll);
    }

    @Override
    public void answerPoll(Long userId, Long pollId, boolean vote) {
        User dbUser = getUserById(userId);
        if (dbUser == null)
            return;

        Poll dbPoll = getPollById(pollId);
        if (dbPoll == null)
            return;

        PollAnswer pollAnswer = new PollAnswer();
        pollAnswer.setUser(dbUser);
        pollAnswer.setPoll(dbPoll);
        pollAnswer.setVote(vote);
        pollAnswerRepository.save(pollAnswer);
    }

    @Override
    public List<InnerPoll> getAllPolls() {
        List<Poll> polls = pollRepository.findAll();
        List<InnerPoll> innerPolls = new ArrayList<>();
        for (Poll poll : polls) {
            innerPolls.add(new InnerPoll(poll));
        }
        return innerPolls;
    }

    @Override
    public void addCensorPhrase(String phrase) {
        censorManager.addCensorPhrase(phrase);
    }

    @Override
    public List<InnerPollAnswer> getPollAnswers(Long userId) {
        Optional<List<PollAnswer>> optionalPollAnswer = pollAnswerRepository.findAllByUserId(userId);
        if(!optionalPollAnswer.isPresent())
            return null;

        List<PollAnswer> pollAnswers = optionalPollAnswer.get();

        List<InnerPollAnswer> innerPollAnswers = new ArrayList<>();
        for (PollAnswer pollAnswer : pollAnswers) {
            innerPollAnswers.add(new InnerPollAnswer(pollAnswer));
        }

        return innerPollAnswers;
    }

    private User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    private Poll getPollById(Long id) {
        Optional<Poll> optionalPoll = pollRepository.findById(id);
        return optionalPoll.orElse(null);
    }
}
