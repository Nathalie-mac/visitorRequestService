package com.example.team2.service;

import com.example.team2.model.Request;
import com.example.team2.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;

    public void save(Request request) {
        requestRepository.save(request);
    }
}
