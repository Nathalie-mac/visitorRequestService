package com.example.team2.service;

import com.example.team2.model.*;
import com.example.team2.dto.response.AppointmentRequestResponseDTO;
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

    public Request createRequest(AppointmentRequestResponseDTO appointmentRequestResponseDTO) {
        Request request = new Request();
        request.setAppointmentType(AppointmentType.INDIVIDUAL);

        //Информация для пропуска
        request.setRequestStartDate(appointmentRequestResponseDTO.getStartApplicationPeriod());
        request.setRequestStartDate(appointmentRequestResponseDTO.getEndApplicationPeriod());
        request.setPurpose(AppointmentPurpose.valueOf(appointmentRequestResponseDTO.getPurposeVisit()));

        //Принимающая сторона
        Department department = departmentService.findById(appointmentRequestResponseDTO.getDepartment());
        request.setDepartment(department);
        DepartmentWorker departmentWorker = departmentWorkerService.findById(appointmentRequestResponseDTO.getWorkerName());
        request.setWorker(departmentWorker);

        return save(request);
    }
}
