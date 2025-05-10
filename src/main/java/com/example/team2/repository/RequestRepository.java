package com.example.team2.repository;

import com.example.team2.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    Request findById(long id);

    @Query("SELECT r FROM Request r WHERE r.status = 'APPROVED'")
    List<Request> getApprovedRequests();

}