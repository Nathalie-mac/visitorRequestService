package com.example.team2.service;

//сервис для терминала сотрудника охраны

import com.example.team2.dto.CustomFioDTO;
import com.example.team2.dto.request.FilterListDTO;
import com.example.team2.dto.request.GuardOfficerSetUpTimeRequestDTO;
import com.example.team2.dto.request.RequestsTableDTO;
import com.example.team2.dto.request.RowRequestsDTO;
import com.example.team2.dto.response.GuardOfficerSetUpTimeResponseDTO;
import com.example.team2.mapper.MapperRequest;
import com.example.team2.model.Department;
import com.example.team2.model.Person;
import com.example.team2.model.Request;
import com.example.team2.repository.DepartmentRepository;
import com.example.team2.repository.PersonRepository;
import com.example.team2.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GuardRequestService {
    private final RequestService requestService;
    //private final RequestRepository requestRepository;
    private final DepartmentService departmentService;
    private final PersonService personService;
    private final RequestRepository requestRepository;
    private final MapperRequest mapperRequest;

    //списки для фильтров на странице охранника
    public FilterListDTO getFilterLists(){
        List<String> statuses = requestService.getStatuses();
        List<String> departments = departmentService.getDepartmentNames();
        List<String> appointments = requestService.getAppointmentTypes();

//        if (statuses.isEmpty() || departments.isEmpty() || appointments.isEmpty()){
//            // TODO: обработка исключениЯ
//            return null;
//        }else{
        FilterListDTO filterListDTO = new FilterListDTO(appointments, departments, statuses);
        return filterListDTO;
//        }
    }


    public List<String> getPersonFiosInRequest(Long id){
        Request request = requestService.findById(id);
        List<CustomFioDTO> rawFios =  personService.findNameByRequest(request);
        return rawFios.stream()
                .map(dto -> String.join(" ", dto.lastName(), dto.firstName(), dto.middleName()))
                .toList();
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
                mapperRequest.mapToRowRequestDTO(request, rowRequestsDTO);
                rowRequestsDTO.setUserNames(getPersonFiosInRequest(request.getId()));

                Department department = departmentService.findById(request.getDepartment().getId());
                rowRequestsDTO.setDepartment(department.getDepartmentName());

                rowRequestsDTOS.add(rowRequestsDTO);
            }
        }
        return new RequestsTableDTO(rowRequestsDTOS);
    }

    //данные для указания времени охранником
    public GuardOfficerSetUpTimeRequestDTO getGuardOfficerSetUpTime(long requestId){
        GuardOfficerSetUpTimeRequestDTO guardOfficerSetUpTimeDTO = new GuardOfficerSetUpTimeRequestDTO();
        Request request = requestService.findById(requestId);
        guardOfficerSetUpTimeDTO.setInBlackList(personService.isAnyPersonInBlackList(request));
        guardOfficerSetUpTimeDTO.setRequestDTO(requestService.getStaticRequestDTO(request));
        guardOfficerSetUpTimeDTO.setEnterTime(request.getActualEnterTime());
        guardOfficerSetUpTimeDTO.setExitTime(request.getActualExitTime());
        return guardOfficerSetUpTimeDTO;
    }

    //ОТПРАВКА ОХРАННИКОМ ВРЕМЕНИ ЗАХОДА В ХАТУ
    public void setGuardOfficerEnterTime(GuardOfficerSetUpTimeResponseDTO guardOfficerSetUpTimeDTO){
        Request request = requestService.findById(guardOfficerSetUpTimeDTO.getIdRequest());
        request.setActualEnterTime(guardOfficerSetUpTimeDTO.getTime());
        requestService.save(request);
    }

    //ОТПРАВКА ОХРАННИКОМ ВРЕМЕНИ УХОДА ИЗ ХАТЫ
    public void setGuardOfficerExitTime(GuardOfficerSetUpTimeResponseDTO guardOfficerSetUpTimeDTO){
        Request request = requestService.findById(guardOfficerSetUpTimeDTO.getIdRequest());
        request.setActualExitTime(guardOfficerSetUpTimeDTO.getTime());
        requestService.save(request);
    }


}
