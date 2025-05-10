package com.example.team2.service;

import com.example.team2.dto.request.RequestsTableDTO;
import com.example.team2.dto.request.RowRequestsDTO;
import com.example.team2.dto.StaticRequestDTO;
import com.example.team2.dto.response.ManagerConfirmationResponseDTO;
import com.example.team2.mapper.MapperUpdateRequest;
import com.example.team2.model.*;
import com.example.team2.dto.response.SignUpRequestResponseDTO;
import com.example.team2.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    private final PersonService personService;
    private final MapperRequest mapperRequest;
    private final MapperUpdateRequest mapperUpdateRequest;
    private final PersonService personService;

    public Request save(Request request) {
        return requestRepository.save(request);
    }

    public Request createRequest(SignUpRequestResponseDTO signUpRequestResponseDTO, AppointmentType appointmentType) {
        Request request = new Request();
        request.setAppointmentType(appointmentType);

        //Информация для пропуска
        request.setRequestStartDate(signUpRequestResponseDTO.getStartApplicationPeriod());
        request.setRequestStartDate(signUpRequestResponseDTO.getEndApplicationPeriod());
        request.setPurpose(AppointmentPurpose.valueOf(signUpRequestResponseDTO.getPurposeVisit()));

        //Принимающая сторона
        Department department = departmentService.findById(signUpRequestResponseDTO.getDepartment());
        request.setDepartment(department);
        DepartmentWorker departmentWorker = departmentWorkerService.findById(signUpRequestResponseDTO.getWorkerName());
        request.setWorker(departmentWorker);

        //Остальное, что нужно
        request.setStatus(StatusType.PENDING); // По умолчанию статус
        //TODO: добавить set для User

        return save(request);
    }

    public Request findById(long id) {
        return requestRepository.findById(id);
    }

    public void updateRequest(ManagerConfirmationResponseDTO managerConfirmationResponseDTO, Request request) {
        mapperUpdateRequest.updateRequest(managerConfirmationResponseDTO, request); //TODO: изменить int ok на DTO
        save(request);
    }

    public List<String> getStatuses(){
        return Arrays.stream(StatusType.values()).map(StatusType::getStatusType).toList();
    }

    public List<String> getAppointmentTypes(){
        return Arrays.stream(AppointmentType.values()).map(AppointmentType::getType).toList();
    }

    //терминал менеджера, страница "оформление заявок", таблица
    public RequestsTableDTO getRequestsTableDTO() {
      List<RowRequestsDTO> rowRequestsDTOS = new ArrayList<>();
      List<Request> requests = requestRepository.findAll();
      if (requests.isEmpty()) {
          //TODO: обработка ошибок
      }else{
          for (Request request : requests) {
              RowRequestsDTO rowRequestsDTO = new RowRequestsDTO();
              mapperUpdateRequest.mapToRowRequestDTO(request, rowRequestsDTO);
              rowRequestsDTO.setUserNames(personService.getPersonFiosInRequest(request.getId()));

              Department department = departmentService.findById(request.getDepartment().getId());
              rowRequestsDTO.setDepartment(department.getDepartmentName());

              rowRequestsDTOS.add(rowRequestsDTO);
          }
      }
      return new RequestsTableDTO(rowRequestsDTOS);
    }

    //терминал охранника, страница "одобренные заявки", таблица
    public RequestsTableDTO getApprovedRequestsTableDTO() {
        List<RowRequestsDTO> rowRequestsDTOS = new ArrayList<>();
        List<Request> requests = requestRepository.getApprovedRequests();
        if (requests.isEmpty()) {
            //TODO: обработка ошибок
        }else{
            for (Request request : requests) {
                RowRequestsDTO rowRequestsDTO = new RowRequestsDTO();
                mapperUpdateRequest.mapToRowRequestDTO(request, rowRequestsDTO);
                rowRequestsDTO.setUserNames(personService.getPersonFiosInRequest(request.getId()));

                Department department = departmentService.findById(request.getDepartment().getId());
                rowRequestsDTO.setDepartment(department.getDepartmentName());

                rowRequestsDTOS.add(rowRequestsDTO);
            }
        }
        return new RequestsTableDTO(rowRequestsDTOS);
    }


    public StaticRequestDTO getStaticRequestDTO(Request request) {
        StaticRequestDTO staticRequestDTO = new StaticRequestDTO();
        mapperRequest.mapRequestToStaticRequestDTO(request, staticRequestDTO);

        staticRequestDTO.setVisitors(personService.getVisitorsDTOs(request));
        staticRequestDTO.setDocs(passportDataService.findPassportIdByRequest(request));

        return staticRequestDTO;
    }
}
