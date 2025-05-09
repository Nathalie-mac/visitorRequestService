package com.example.team2.service;

import com.example.team2.model.*;
import com.example.team2.model.dto.response.SignUpRequestResponseDTO;
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

    public Request createRequest(SignUpRequestResponseDTO signUpRequestResponseDTO) {
        Request request = new Request();
        request.setAppointmentType(AppointmentType.INDIVIDUAL);

        //Информация для пропуска
        request.setRequestStartDate(signUpRequestResponseDTO.getStartApplicationPeriod());
        request.setRequestStartDate(signUpRequestResponseDTO.getEndApplicationPeriod());
        request.setPurpose(AppointmentPurpose.valueOf(signUpRequestResponseDTO.getPurposeVisit()));

        //Принимающая сторона
        Department department = departmentService.findById(signUpRequestResponseDTO.getDepartment());
        request.setDepartment(department);
        DepartmentWorker departmentWorker = departmentWorkerService.findById(signUpRequestResponseDTO.getWorkerName());
        request.setWorker(departmentWorker);

        return save(request);
    }
}
