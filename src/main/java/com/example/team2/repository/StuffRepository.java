package com.example.team2.repository;

import com.example.team2.model.Stuff;
import com.example.team2.model.StuffRoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StuffRepository extends JpaRepository<Stuff, Long> {
    Stuff findStuffByUserCodeAndStuffRole(String code, StuffRoleType stuffRole);
}
