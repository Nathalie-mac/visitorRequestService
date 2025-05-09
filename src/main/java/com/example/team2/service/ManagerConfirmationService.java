package com.example.team2.service;

import com.example.team2.model.Request;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerConfirmationService {
    private final RequestService requestService;

    public void requestCheck() {
        Request request = requestService.findById(0);// TODO: впихнуть id из DTO
        //requestService.updateRequest(); TODO: Запихнуть в updateRequest DTO и request
    }

    //
}
