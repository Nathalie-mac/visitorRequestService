package com.example.team2.auth.services;

import com.example.team2.auth.data.entity.roles.RoleType;
import com.example.team2.auth.data.entity.user.User;
import com.example.team2.auth.data.repositories.RoleRepository;
import com.example.team2.auth.data.repositories.UserRepository;
import com.example.team2.auth.exceptions.data.ExistingUserWithThatUsernameException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private User save(User user) {
        return userRepository.save(user);
    }

    public User createUser(String username, String password) {

        if (findUserByUsername(username) != null) {
            throw new ExistingUserWithThatUsernameException();
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRoles(Set.of((roleRepository.findByName(RoleType.ROLE_USER))));

        return save(user);
    }


    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
