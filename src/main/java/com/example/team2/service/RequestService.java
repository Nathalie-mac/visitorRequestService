package com.example.team2.service;

import com.example.team2.model.*;
import com.example.team2.model.dto.SignUpRequestDTO;
import com.example.team2.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;
    private final DepartmentService departmentService;
    private final DepartmentWorkerService departmentWorkerService;

    public Request save(Request request) {
        return requestRepository.save(request);
    }

    public Request createRequest(SignUpRequestDTO signUpRequestDTO) {
        Request request = new Request();
        request.setAppointmentType(AppointmentType.INDIVIDUAL);

        //Информация для пропуска
        request.setRequestStartDate(signUpRequestDTO.getStartApplicationPeriod());
        request.setRequestStartDate(signUpRequestDTO.getEndApplicationPeriod());
        request.setPurpose(AppointmentPurpose.valueOf(signUpRequestDTO.getPurposeVisit()));

        //Принимающая сторона
        Department department = departmentService.findById(signUpRequestDTO.getDepartment());
        request.setDepartment(department);
        DepartmentWorker departmentWorker = departmentWorkerService.findById(signUpRequestDTO.getWorkerName());
        request.setWorker(departmentWorker);

        return save(request);
    }
}
