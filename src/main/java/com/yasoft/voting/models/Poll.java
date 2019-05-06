package com.yasoft.voting.models;

import org.hibernate.annotations.Formula;

import javax.persistence.*;

@Entity
@Table(name = "polls")
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Formula("(select count(*) from poll_answers pa where pa.poll_id = id and pa.vote = true)")
    private int trueVotes;

    @Formula("(select count(*) from poll_answers pa where pa.poll_id = id and pa.vote = false)")
    private int falseVotes;

    public Poll() { }

    public Poll(User user, String title, String text, int trueVotes, int falseVotes) {
        this.user = user;
        this.title = title;
        this.text = text;
        this.trueVotes = trueVotes;
        this.falseVotes = falseVotes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTrueVotes() {
        return trueVotes;
    }

    public void setTrueVotes(int trueVotes) {
        this.trueVotes = trueVotes;
    }

    public int getFalseVotes() {
        return falseVotes;
    }

    public void setFalseVotes(int falseVotes) {
        this.falseVotes = falseVotes;
    }
}