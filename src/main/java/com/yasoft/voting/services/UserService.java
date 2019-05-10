package com.yasoft.voting.services;

import com.yasoft.voting.models.InnerUser;
import com.yasoft.voting.models.User;
import com.yasoft.voting.repositories.UserRepository;
import com.yasoft.voting.utils.Md5Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(String name, String password, int level) {
        User user = new User();
        user.setName(name);
        String passwordMd5 = Md5Manager.getMd5(password);
        user.setPassword(passwordMd5);
        user.setLevel(level);
        userRepository.save(user);
    }

    public InnerUser loginUser(String name, String password) {
        String passwordMd5 = Md5Manager.getMd5(password);
        Optional<User> optionalUser = userRepository.findByNameAndPassword(name, passwordMd5);

        if (!optionalUser.isPresent())
            return null;

        User dbUser = optionalUser.get();
        return new InnerUser(dbUser.getId(), dbUser.getName(), dbUser.getLevel());
    }

    private User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public User findUser(Long userId) {
        return getUserById(userId);
    }
}
