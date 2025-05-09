package com.example.team2.service;

import com.example.team2.model.Request;
import com.example.team2.repository.RequestRepository;
import org.springframework.stereotype.Service;

@Service
public class RequestService {
    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public void save(Request request) {
        requestRepository.save(request);
    }
}
