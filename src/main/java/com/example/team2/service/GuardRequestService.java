package com.example.team2.service;

//сервис для терминала сотрудника охраны

import com.example.team2.model.Request;
import com.example.team2.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuardRequestService {
    private final RequestService requestService;
    private final RequestRepository requestRepository;

    //выдаем список одобренных заявок
    public List<someDTO> getApprovedRequests(SomeDTO someDTO){
        List<Request> requests = requestRepository.getApprovedRequests();
        //TODO:  добавить маппер для ДТО-шки
    }

    //выдаем список
    public



}
