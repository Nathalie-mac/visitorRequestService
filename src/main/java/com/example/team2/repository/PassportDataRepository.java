package com.example.team2.repository;

import com.example.team2.model.PassportData;
import com.example.team2.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassportDataRepository extends JpaRepository<PassportData, Long> {
    List<PassportData> findPassportIdByRequest(Request request);
}
