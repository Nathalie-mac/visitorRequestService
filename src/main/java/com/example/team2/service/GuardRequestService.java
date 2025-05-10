package com.example.team2.service;

//сервис для терминала сотрудника охраны

import com.example.team2.dto.request.FilterListDTO;
import com.example.team2.model.Request;
import com.example.team2.repository.DepartmentRepository;
import com.example.team2.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuardRequestService {
    private final RequestService requestService;
    private final RequestRepository requestRepository;
    private final DepartmentRepository departmentRepository;

    //списки для фильтров на странице охранника
    public FilterListDTO getGuardFilterLists(){
        List<String> statuses = requestRepository.findDistinctStatusBy();
        List<String> departments = departmentRepository.findDistinctDepartmentNameBy();
        List<String> appointments = requestRepository.findDistinctAppoinmentTypeBy();
        if (statuses.isEmpty() || departments.isEmpty() || appointments.isEmpty()){
            // TODO: обработка исключениЯ
            return null;
        }else{
            FilterListDTO filterListDTO = new FilterListDTO(appointments, departments, statuses);
            return filterListDTO;
        }
    }

    //выдаем список
    public a(){
        List<Request> requests = requestRepository.getApprovedRequests();
        if (requests.isEmpty()){
            // TODO: обработка исключениЯ
        }else{
            for (Request request: requests){

            }
        }

        //TODO:  добавить маппер для ДТО-шки
    }



}
