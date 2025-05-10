package com.example.team2.service;

//сервис для терминала сотрудника охраны

import com.example.team2.dto.request.FilterListDTO;
import com.example.team2.dto.request.GuardOfficerSetUpTimeRequestDTO;
import com.example.team2.model.Person;
import com.example.team2.model.Request;
import com.example.team2.repository.DepartmentRepository;
import com.example.team2.repository.PersonRepository;
import com.example.team2.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuardRequestService {
    private final RequestService requestService;
    //private final RequestRepository requestRepository;
    private final DepartmentService departmentService;
    private final PersonService personService;

    //списки для фильтров на странице охранника
    public FilterListDTO getFilterLists(){
        List<String> statuses = requestService.getStatuses();
        List<String> departments = departmentService.getDepartmentNames();
        List<String> appointments = requestService.getAppointmentTypes();

        if (statuses.isEmpty() || departments.isEmpty() || appointments.isEmpty()){
            // TODO: обработка исключениЯ
            return null;
        }else{
            FilterListDTO filterListDTO = new FilterListDTO(appointments, departments, statuses);
            return filterListDTO;
        }
    }

    //данные для указания времени охранником
    public GuardOfficerSetUpTimeRequestDTO getGuardOfficerSetUpTime(long requestId){
        GuardOfficerSetUpTimeRequestDTO guardOfficerSetUpTimeDTO = new GuardOfficerSetUpTimeRequestDTO();
        Request request = requestService.findById(requestId);
        guardOfficerSetUpTimeDTO.setInBlackList(personService.isAnyPersonInBlackList(request));
        //guardOfficerSetUpTimeDTO.setRequestDTO(); //TODO: дописать после Семена staticRequest
        guardOfficerSetUpTimeDTO.setEnterTime(request.getActualEnterTime());
        guardOfficerSetUpTimeDTO.setExitTime(request.getActualExitTime());
        return guardOfficerSetUpTimeDTO;
    }

    //
    public void setGuardOfficerEnterTime(GuardOfficerSetUpTimeRequestDTO guardOfficerSetUpTimeDTO){

    }


}
