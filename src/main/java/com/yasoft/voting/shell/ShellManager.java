package com.yasoft.voting.shell;

import com.yasoft.voting.controllers.RestChatController;
import com.yasoft.voting.models.InnerMessage;
import com.yasoft.voting.models.InnerUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
public class ShellManager {
    private RestChatController chatController;
    private InnerUser user;

    @Autowired
    public ShellManager(RestChatController chatController) {
        this.chatController = chatController;
    }

    @ShellMethod("send-message")
    public void sendMessage(String message) {
        chatController.sendMessage(user.getId(), message);
    }

    @ShellMethod("print-all-messages")
    public void printAllMessages() {
        List<InnerMessage> messages = chatController.getAllMessages();

        for(InnerMessage message : messages) {
            System.out.println(message.getUser().getName() + ": " + message.getText());
        }
    }

    @ShellMethod("create-user")
    public void createUser(String name, String password) {
        chatController.createUser(name, password);
    }

    @ShellMethod("login-user")
    public void loginUser(String name, String password) {
        user = chatController.loginUser(name, password);

        if(user == null) {
            System.out.println("user == null");
        }
    }

    @ShellMethod("create-poll")
    public void createPoll(String title, String text) {
        chatController.createPoll(user.getId(), title, text);
    }

    @ShellMethod("answer-poll")
    public void answerPoll(long pollId, boolean vote) {
        chatController.answerPoll(user.getId(), pollId, vote);
    }

    @ShellMethod("init-shell-user")
    public void initShellUser() {
        String name = "ShellUser";
        String password = "shell";
        createUser(name, password);
        loginUser(name, password);
    }

    @ShellMethod("add-censor-phrase")
    public void addCensorPhrase(String phrase) {
        chatController.addCensorPhrase(phrase);
    }
}
