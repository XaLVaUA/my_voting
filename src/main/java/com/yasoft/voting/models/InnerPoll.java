package com.yasoft.voting.models;

public class InnerPoll {
    private Long id;
    private InnerUser user;
    private String text;
    private int trueVotes;
    private int falseVotes;

    public InnerPoll() { }

    public InnerPoll(Long id, InnerUser user, String text, int trueVotes, int falseVotes) {
        this.id = id;
        this.user = user;
        this.text = text;
        this.trueVotes = trueVotes;
        this.falseVotes = falseVotes;
    }

    public InnerPoll(Poll dbPoll) {
        this(dbPoll.getId(), new InnerUser(dbPoll.getUser()), dbPoll.getText(), dbPoll.getTrueVotes(), dbPoll.getFalseVotes());
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
