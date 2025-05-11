package com.example.team2.service;

import com.example.team2.auth.exceptions.data.ExistingUserWithThatUsernameException;
import com.example.team2.auth.exceptions.data.UserNotFoundException;
import com.example.team2.model.User;
import com.example.team2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User createUser(String login, String password) {

        if (userRepository.findUserByUserLogin(login) != null) {
            throw new ExistingUserWithThatUsernameException();
        }
        User user = new User();
        user.setUserLogin(login);
        user.setUserPassword(password);

        return save(user);
    }

    public User findUserById(long id) {
        return userRepository.findUserById(id);
    }

    public User findUserByLogin(String login) {
        User findUser = userRepository.findUserByUserLogin(login);
        if (findUser == null) {
            throw new UserNotFoundException();
        }
        return userRepository.findUserByUserLogin(login);
    }
}
