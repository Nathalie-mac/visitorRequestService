package com.example.team2.repository;

import com.example.team2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUserLogin(String login);
    User findUserById(long id);
}
