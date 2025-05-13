package com.example.team2.repository;


import com.example.team2.dto.CustomFioDTO;
import com.example.team2.model.Person;
import com.example.team2.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findDistinctBlackListByRequest(Request request);
    List<Person> findByRequest(Request request);

    @Query("SELECT NEW com.example.team2.dto.CustomFioDTO(p.lastName, p.firstName, p.middleName) " +
            "FROM Person p WHERE p.request = :request")
    List<CustomFioDTO> findNameByRequest(Request request);
}
