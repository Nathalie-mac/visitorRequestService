package com.example.team2.repository;

import com.example.team2.dto.BlyadskoeFioDTO;
import com.example.team2.model.Person;
import com.example.team2.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Boolean> findDistinctBlackListByRequest(Request request);

    @Query("SELECT NEW com.example.team2.dto.BlyadskoeFioDTO(p.lastName, p.firstName, p.middleName) " +
            "FROM Person p WHERE p.request = :requestId")
    List<BlyadskoeFioDTO> findNameByRequest(Long requestId);
}
