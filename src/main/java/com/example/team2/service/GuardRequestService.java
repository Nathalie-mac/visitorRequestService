package com.example.team2.service;

//сервис для терминала сотрудника охраны

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GuardRequestService {
    public final RequestService requestService;


}
