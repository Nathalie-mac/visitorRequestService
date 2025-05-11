package com.example.team2.service;

import com.example.team2.dto.request.RequestsTableDTO;
import com.example.team2.dto.request.RowRequestsDTO;
import com.example.team2.dto.StaticRequestDTO;
import com.example.team2.dto.response.ManagerConfirmationResponseDTO;
import com.example.team2.mapper.MapperRequest;
import com.example.team2.model.*;
import com.example.team2.dto.response.AppointmentRequestResponseDTO;
import com.example.team2.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;
    private final DepartmentService departmentService;
    private final DepartmentWorkerService departmentWorkerService;
    private final PassportDataService passportDataService;
    private final UserService userService;
    private final PersonService personService;
    private final MapperRequest mapperRequest;

    public Request save(Request request) {
        return requestRepository.save(request);
    }

    public Request createRequest(AppointmentRequestResponseDTO appointmentRequestResponseDTO, AppointmentType appointmentType) {
        Request request = new Request();
        request.setAppointmentType(appointmentType);

        //Информация для пропуска
        request.setRequestStartDate(appointmentRequestResponseDTO.getStartApplicationPeriod());
        request.setRequestStartDate(appointmentRequestResponseDTO.getEndApplicationPeriod());
        request.setPurpose(AppointmentPurpose.valueOf(appointmentRequestResponseDTO.getPurposeVisit()));

        //Принимающая сторона
        Department department = departmentService.findById(appointmentRequestResponseDTO.getDepartment());
        request.setDepartment(department);
        DepartmentWorker departmentWorker = departmentWorkerService.findById(appointmentRequestResponseDTO.getWorkerName());
        request.setWorker(departmentWorker);

        //Остальное, что нужно
        request.setStatus(StatusType.PENDING); // По умолчанию статус
        request.setUser(userService.findUserById(appointmentRequestResponseDTO.getUserId()));

        return save(request);
    }

    public Request findById(long id) {
        return requestRepository.findById(id);
    }

    public void updateRequest(ManagerConfirmationResponseDTO managerConfirmationResponseDTO, Request request) {
        mapperRequest.updateRequest(managerConfirmationResponseDTO, request);
        save(request);
    }

    public List<String> getStatuses(){
        return Arrays.stream(StatusType.values()).map(StatusType::getStatusType).toList();
    }

    public List<String> getAppointmentTypes(){
        return Arrays.stream(AppointmentType.values()).map(AppointmentType::getType).toList();
    }

    public List<Request> findByUser(User user) {
        return requestRepository.findByUser(user);
    }

    public StaticRequestDTO getStaticRequestDTO(Request request) {
        StaticRequestDTO staticRequestDTO = new StaticRequestDTO();
        mapperRequest.mapRequestToStaticRequestDTO(request, staticRequestDTO);

        staticRequestDTO.setVisitors(personService.getVisitorsDTOs(request));
        staticRequestDTO.setDocs(passportDataService.findPassportIdByRequest(request));

        return staticRequestDTO;
    }

    String findStatusById(long id) {
        return requestRepository.findById(id).getStatus().getStatusType();
    }

    String findRejectReasonById(long id) {
        return requestRepository.findById(id).getRejectReason().getReason();
    }

    LocalDate findRequestDateById(long id) {
        return requestRepository.findById(id).getRequestDate();
    }

    LocalTime findRequestTimeById(long id) {
        return requestRepository.findById(id).getRequestTime();
    }
}
