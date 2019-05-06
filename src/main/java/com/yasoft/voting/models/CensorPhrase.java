package com.yasoft.voting.models;

import javax.persistence.*;

@Entity
@Table(name = "censor_phrases")
public class CensorPhrase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "phrase")
    private String phrase;

    public CensorPhrase() { }

    public CensorPhrase(String phrase) {
        this.phrase = phrase;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
}
