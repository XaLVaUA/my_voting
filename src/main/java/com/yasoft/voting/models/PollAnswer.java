package com.yasoft.voting.models;

import javax.persistence.*;

@Entity
@Table(name = "poll_answers")
public class PollAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "vote")
    private boolean vote;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;

    public PollAnswer() { }

    public PollAnswer(User user, boolean vote, Poll poll) {
        this.user = user;
        this.vote = vote;
        this.poll = poll;
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

    public boolean isVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }
}
