package com.yasoft.voting.controllers;

import com.yasoft.voting.services.CensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("censor")
public class CensorController {
    private CensorService censorService;

    @Autowired
    public CensorController(CensorService censorService) {
        this.censorService = censorService;
    }

    @CrossOrigin(origins = { "http://localhost:4200" })
    @PostMapping("add")
    public void addCensorPhrase(@RequestParam String phrase) {
        censorService.addCensorPhrase(phrase);
    }
}
