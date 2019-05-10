package com.yasoft.voting.models;

public class InnerPollAnswer {
    private Long id;
    private InnerUser user;
    private boolean vote;
    private InnerPoll poll;

    public InnerPollAnswer(){

    }

    public InnerPollAnswer(Long id, InnerUser user, boolean vote, InnerPoll poll) {
        this.id = id;
        this.user = user;
        this.vote = vote;
        this.poll = poll;
    }

    public InnerPollAnswer(PollAnswer dbPollAnswer) {
        this(dbPollAnswer.getId(), new InnerUser(dbPollAnswer.getUser()), dbPollAnswer.getVote(), new InnerPoll(dbPollAnswer.getPoll()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InnerUser getUser() {
        return user;
    }

    public void setUser(InnerUser user) {
        this.user = user;
    }

    public boolean isVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }

    public InnerPoll getPoll() {
        return poll;
    }

    public void setPoll(InnerPoll poll) {
        this.poll = poll;
    }
}
