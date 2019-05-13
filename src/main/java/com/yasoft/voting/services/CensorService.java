package com.yasoft.voting.services;

import com.yasoft.voting.models.CensorPhrase;
import com.yasoft.voting.models.User;
import com.yasoft.voting.utils.CensorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CensorService {
    private CensorManager censorManager;
    private UserService userService;

    @Autowired
    public CensorService(CensorManager censorManager, UserService userService) {
        this.censorManager = censorManager;
        this.userService = userService;
    }

    public void addCensorPhrase(Long userId, String phrase) {
        User user = userService.findUser(userId);
        if(user == null)
            return;
        if(user.getLevel() != 2)
            return;
        censorManager.addCensorPhrase(phrase);
    }

    public String getValidString(String message) {
        return censorManager.getValidString(message);
    }

    public List<CensorPhrase> getAllCensorPhrases() {
        return censorManager.getAllCensorPhrases();
    }
}
