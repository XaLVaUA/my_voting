package com.yasoft.voting.controllers;

import com.yasoft.voting.models.InnerUser;
import com.yasoft.voting.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = { "http://localhost:4200" })
    @PostMapping("create")
    public void createUser(@RequestParam String name, @RequestParam String password) {
        userService.createUser(name, password, 0);
    }

    @CrossOrigin(origins = { "http://localhost:4200" })
    @PostMapping("login")
    public @ResponseBody
    InnerUser loginUser(@RequestParam String name, @RequestParam String password) {
        return userService.loginUser(name, password);
    }

    @CrossOrigin(origins = { "http://localhost:4200" })
    @PostMapping("create-admin")
    public void createAdmin(@RequestParam String name, @RequestParam String password) {
        userService.createUser(name, password, 2);
    }
}
