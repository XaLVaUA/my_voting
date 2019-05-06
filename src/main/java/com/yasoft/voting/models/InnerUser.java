package com.yasoft.voting.models;

public class InnerUser {
    private Long id;
    private String name;
    private int level;

    public InnerUser() { }

    public InnerUser(Long id, String name, int level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }

    public InnerUser(User dbUser) {
        this(dbUser.getId(), dbUser.getName(), dbUser.getLevel());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
