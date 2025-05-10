package com.example.team2.service;

import com.example.team2.dto.StaticRequestDTO;
import com.example.team2.dto.request.ManagerConfirmationRequestDTO;
import com.example.team2.dto.response.ManagerConfirmationResponseDTO;
import com.example.team2.model.RejectReason;
import com.example.team2.model.Request;
import com.example.team2.model.StatusType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

        StaticRequestDTO requestDTO = new StaticRequestDTO();

        managerConfirmationRequestDTO.setRequestDTO();
        managerConfirmationRequestDTO.setStatus();
        managerConfirmationRequestDTO.setRejectReason();
        managerConfirmationRequestDTO.setVisitTime();
        managerConfirmationRequestDTO.setVisitDate();

        return managerConfirmationRequestDTO;
    }




    //
}
