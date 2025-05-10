package com.example.team2.service;

import com.example.team2.dto.StaticRequestDTO;
import com.example.team2.dto.request.ManagerConfirmationRequestDTO;
import com.example.team2.dto.response.ManagerConfirmationResponseDTO;
import com.example.team2.model.RejectReason;
import com.example.team2.model.Request;
import com.example.team2.model.StatusType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerConfirmationService {
    private final RequestService requestService;
    private final PersonService personService;

    //Данные с фронта
    public Request requestCheck(ManagerConfirmationResponseDTO managerConfirmationResponseDTO) { //TODO: придумать менее конченое название
        Request request = requestService.findById(managerConfirmationResponseDTO.getIdRequest());
        requestService.updateRequest(managerConfirmationResponseDTO, request);

        return request;
    }

    //На фронт
    public  ManagerConfirmationRequestDTO getManagerConfirmation(long requestId) { // TODO: придумать менее конченное название
        ManagerConfirmationRequestDTO managerConfirmationRequestDTO = new ManagerConfirmationRequestDTO();

        Request request = requestService.findById(requestId);

        managerConfirmationRequestDTO.setInBlackList(personService.isAnyPersonInBlackList(request));
        managerConfirmationRequestDTO.setRequestDTO(requestService.getStaticRequestDTO(request));

        String status = requestService.findStatusByRequest(request);
        List<String> statusTypes = Arrays.stream(StatusType.values()).map(StatusType::getStatusType).toList();
        managerConfirmationRequestDTO.setStatus(status);
        managerConfirmationRequestDTO.setStatusList(statusTypes);

        String rejectionReason = requestService.findRejectReasonByRequest(request);
        List<String> rejectionReasons = Arrays.stream(RejectReason.values()).map(RejectReason::getReason).toList();
        managerConfirmationRequestDTO.setRejectReason(rejectionReason);
        managerConfirmationRequestDTO.setRejectReasonList(rejectionReasons);

        managerConfirmationRequestDTO.setVisitTime(requestService.findRequestTimeByRequest(request));
        managerConfirmationRequestDTO.setVisitDate(requestService.findRequestDateByRequest(request));

        return managerConfirmationRequestDTO;
    }

}
