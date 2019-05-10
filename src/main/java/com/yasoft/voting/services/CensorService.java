package com.yasoft.voting.services;

import com.yasoft.voting.utils.CensorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CensorService {
    private CensorManager censorManager;

    @Autowired
    public CensorService(CensorManager censorManager) {
        this.censorManager = censorManager;
    }

    public void addCensorPhrase(String phrase) {
        censorManager.addCensorPhrase(phrase);
    }

    public String getValidString(String message) {
        return censorManager.getValidString(message);
    }
}
