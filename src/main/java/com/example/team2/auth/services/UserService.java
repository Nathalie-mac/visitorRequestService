package com.example.team2.auth.services;

import com.example.team2.auth.data.repositories.UserRepository;
import com.example.team2.auth.exceptions.ExistingUserWithThatUserLoginException;
import com.example.team2.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private User save(User user) {
        return userRepository.save(user);
    }

    public User createUser(String userLogin, String password) {

        if (userRepository.findUserByUserLogin(userLogin) != null) {
            throw new ExistingUserWithThatUserLoginException();
        }
        User user = new User();
        user.setUserLogin(userLogin);
        user.setUserPassword(password);

        return save(user);
    }

}
