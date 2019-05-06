package com.yasoft.voting.utils;

import com.yasoft.voting.models.CensorPhrase;
import com.yasoft.voting.repositories.CensorPhraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class CensorManager {
    private CensorPhraseRepository censorPhraseRepository;
    private List<CensorPhrase> censorPhrases;

    @Autowired
    public CensorManager(CensorPhraseRepository censorPhraseRepository) {
        this.censorPhraseRepository = censorPhraseRepository;
    }

    @PostConstruct
    public void init() {
        censorPhrases = censorPhraseRepository.findAll();
    }

    public String getValidString(String str) {
        censorPhrases = censorPhraseRepository.findAll();
        String validString = str;
        for(CensorPhrase phrase : censorPhrases) {
            String phraseStr = phrase.getPhrase();
            String regex = "(?i)" + phraseStr;
            validString = str.replaceAll(regex, getMarkString(phraseStr.length()));
        }
        return validString;
    }

    public void addCensorPhrase(String phrase) {
        CensorPhrase censorPhrase = new CensorPhrase();
        censorPhrase.setPhrase(phrase);
        censorPhraseRepository.save(censorPhrase);
    }

    private String getMarkString(int length) {
        String mark = "*";
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; ++i) {
            sb.append(mark);
        }
        return sb.toString();
    }
}
