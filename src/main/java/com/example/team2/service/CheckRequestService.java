package com.example.team2.service;

import com.example.team2.model.Request;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckRequestService {
    private final RequestService requestService;

    public void individualRequestCheck() {
        Request request = requestService.findById(0); // TODO: впихнуть id из DTO

    }

    public void groupRequestCheck() {

    }
}
