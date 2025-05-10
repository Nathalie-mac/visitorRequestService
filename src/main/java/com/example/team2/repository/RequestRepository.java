package com.example.team2.repository;

import com.example.team2.model.RejectReason;
import com.example.team2.model.Request;
import com.example.team2.model.StatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    Request findById(long id);

    @Query("SELECT r FROM Request r WHERE r.status = 'APPROVED'")
    List<Request> getApprovedRequests();

    List<String> findDistinctStatusBy();

    List<String> findDistinctAppoinmentTypeBy();

    StatusType findStatusByRequest(Request request);

    RejectReason findRejectReasonByRequest(Request request);

    LocalDate findRequestDateByRequest(Request request);

    LocalTime findRequestTimeByRequest(Request request);
}