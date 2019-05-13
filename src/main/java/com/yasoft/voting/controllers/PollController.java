package com.yasoft.voting.controllers;

import com.yasoft.voting.models.InnerPoll;
import com.yasoft.voting.models.InnerPollAnswer;
import com.yasoft.voting.services.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("polls")
public class PollController {
    private PollService pollService;

    @Autowired
    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @CrossOrigin(origins = { "http://localhost:4200" })
    @PostMapping("create")
    public void createPoll(@RequestParam Long userId, @RequestParam String text) {
        pollService.createPoll(userId, text);
    }

    @CrossOrigin(origins = { "http://localhost:4200" })
    @PostMapping("vote")
    public void answerPoll(@RequestParam Long userId, @RequestParam Long pollId, @RequestParam boolean vote) {
        pollService.answerPoll(userId, pollId, vote);
    }

    @CrossOrigin(origins = { "http://localhost:4200" })
    @GetMapping("/")
    public @ResponseBody
    List<InnerPoll> getAllPolls() {
        return pollService.getAllPolls();
    }

    @CrossOrigin(origins = { "http://localhost:4200" })
    @PostMapping("answers")
    public @ResponseBody List<InnerPollAnswer> getPollAnswers(@RequestParam Long userId) {
        return pollService.getPollAnswers(userId);
    }
}
