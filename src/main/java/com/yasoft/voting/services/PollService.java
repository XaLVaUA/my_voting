package com.yasoft.voting.services;

import com.yasoft.voting.models.*;
import com.yasoft.voting.repositories.PollAnswerRepository;
import com.yasoft.voting.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PollService {
    private PollRepository pollRepository;
    private PollAnswerRepository pollAnswerRepository;
    private UserService userService;
    private CensorService censorService;

    @Autowired
    public PollService(PollRepository pollRepository, PollAnswerRepository pollAnswerRepository, UserService userService, CensorService censorService) {
        this.pollRepository = pollRepository;
        this.pollAnswerRepository = pollAnswerRepository;
        this.userService = userService;
        this.censorService = censorService;
    }

    public void createPoll(Long userId, String title, String text) {
        User dbUser = userService.findUser(userId);
        if (dbUser == null)
            return;

        String validTitle = censorService.getValidString(title);
        String validText = censorService.getValidString(text);

        Poll poll = new Poll();
        poll.setUser(dbUser);
        poll.setTitle(validTitle);
        poll.setText(validText);
        pollRepository.save(poll);
    }

    public void answerPoll(Long userId, Long pollId, boolean vote) {
        User dbUser = userService.findUser(userId);
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

    public List<InnerPoll> getAllPolls() {
        List<Poll> polls = pollRepository.findAll();
        List<InnerPoll> innerPolls = new ArrayList<>();
        for (Poll poll : polls) {
            innerPolls.add(new InnerPoll(poll));
        }
        return innerPolls;
    }

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

    private Poll getPollById(Long id) {
        Optional<Poll> optionalPoll = pollRepository.findById(id);
        return optionalPoll.orElse(null);
    }
}
