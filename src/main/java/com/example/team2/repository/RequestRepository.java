package com.example.team2.repository;

import com.example.team2.model.RejectReason;
import com.example.team2.model.Request;
import com.example.team2.model.StatusType;
import com.example.team2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public interface RequestRepository extends JpaRepository<Request, Long> {
    Request findById(long id);

    @Query("SELECT r FROM Request r WHERE r.status = 'APPROVED'")
    List<Request> getApprovedRequests();

    List<Request> findByUser(User user);

    //List<String> findDistinctStatusBy();

    //List<String> findDistinctAppoinmentTypeBy();

    //StatusType findStatusById(long id);

    //RejectReason findRejectReasonById(long id);

    //LocalDate findRequestDateById(long id);

    //LocalTime findRequestTimeById(long id);
}