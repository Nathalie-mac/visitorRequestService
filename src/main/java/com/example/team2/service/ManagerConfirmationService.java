package com.example.team2.service;

import com.example.team2.dto.StaticRequestDTO;
import com.example.team2.dto.request.ManagerConfirmationRequestDTO;
import com.example.team2.dto.request.RequestsTableDTO;
import com.example.team2.dto.request.RowRequestsDTO;
import com.example.team2.dto.response.ManagerConfirmationResponseDTO;
import com.example.team2.mapper.MapperRequest;
import com.example.team2.model.Department;
import com.example.team2.model.RejectReason;
import com.example.team2.model.Request;
import com.example.team2.model.StatusType;
import com.example.team2.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerConfirmationService {
    private final RequestService requestService;
    private final PersonService personService;
    private final RequestRepository requestRepository;
    private final MapperRequest mapperRequest;
    private final DepartmentService departmentService;
    private final GuardRequestService guardRequestService;

    //Данные с фронта
    public Request requestCheck(ManagerConfirmationResponseDTO managerConfirmationResponseDTO) { //TODO: придумать менее конченое название
        Request request = requestService.findById(managerConfirmationResponseDTO.getIdRequest());
        requestService.updateRequest(managerConfirmationResponseDTO, request);

        return request;
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
                mapperRequest.mapToRowRequestDTO(request, rowRequestsDTO);
                rowRequestsDTO.setUserNames(guardRequestService.getPersonFiosInRequest(request.getId()));

                Department department = departmentService.findById(request.getDepartment().getId());
                rowRequestsDTO.setDepartment(department.getDepartmentName());

                rowRequestsDTOS.add(rowRequestsDTO);
            }
        }
        return new RequestsTableDTO(rowRequestsDTOS);
    }

    //На фронт
    public  ManagerConfirmationRequestDTO getManagerConfirmation(long requestId) { // TODO: придумать менее конченное название
        ManagerConfirmationRequestDTO managerConfirmationRequestDTO = new ManagerConfirmationRequestDTO();

        Request request = requestService.findById(requestId);

        managerConfirmationRequestDTO.setInBlackList(personService.isAnyPersonInBlackList(request));
        managerConfirmationRequestDTO.setRequestDTO(requestService.getStaticRequestDTO(request));

        String status = requestService.findStatusById(requestId);
        List<String> statusTypes = Arrays.stream(StatusType.values()).map(StatusType::getStatusType).toList();
        managerConfirmationRequestDTO.setStatus(status);
        managerConfirmationRequestDTO.setStatusList(statusTypes);

        String rejectionReason = requestService.findRejectReasonById(requestId);
        List<String> rejectionReasons = Arrays.stream(RejectReason.values()).map(RejectReason::getReason).toList();
        managerConfirmationRequestDTO.setRejectReason(rejectionReason);
        managerConfirmationRequestDTO.setRejectReasonList(rejectionReasons);

        managerConfirmationRequestDTO.setVisitTime(requestService.findRequestTimeById(requestId));
        managerConfirmationRequestDTO.setVisitDate(requestService.findRequestDateById(requestId));

        return managerConfirmationRequestDTO;
    }

}
