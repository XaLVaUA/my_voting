package com.yasoft.voting.models;

public class InnerMessage {
    private Long id;
    private String text;
    private InnerUser user;

    public InnerMessage() { }

    public InnerMessage(Long id, String text, InnerUser user) {
        this.id = id;
        this.text = text;
        this.user = user;
    }

    public InnerMessage(Message dbMessage) {
        this(dbMessage.getId(), dbMessage.getText(), new InnerUser(dbMessage.getUser()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public InnerUser getUser() {
        return user;
    }

    public void setUser(InnerUser user) {
        this.user = user;
    }
}
