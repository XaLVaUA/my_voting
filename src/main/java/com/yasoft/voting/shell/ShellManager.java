package com.yasoft.voting.shell;

import com.yasoft.voting.controllers.RestChatController;
import com.yasoft.voting.models.Message;
import com.yasoft.voting.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
public class ShellManager {
    private RestChatController chatController;
    private User user;

    @Autowired
    public ShellManager(RestChatController chatController) {
        this.chatController = chatController;
    }

    @ShellMethod("send-message")
    public void sendMessage(String message) {
        chatController.sendMessage(user, message);
    }

    @ShellMethod("print-all-messages")
    public void printAllMessages() {
        List<Message> messages = chatController.getAllMessages();

        for(Message message : messages) {
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
}
