package com.example.team2.mapper;

import com.example.team2.dto.StaticRequestDTO;
import com.example.team2.dto.request.RowRequestsDTO;
import com.example.team2.dto.response.ManagerConfirmationResponseDTO;
import com.example.team2.model.Request;
import org.mapstruct.*;

import java.time.LocalDateTime;
import java.util.List;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        imports = {LocalDateTime.class, com.example.team2.model.StatusType.class, com.example.team2.model.RejectReason.class,
                com.example.team2.model.AppointmentType.class, com.example.team2.model.Request.class,
                com.example.team2.dto.response.ManagerConfirmationResponseDTO.class, com.example.team2.model.DepartmentWorker.class,
                com.example.team2.model.Department.class})
public interface MapperRequest {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "appointmentType", ignore = true)
    @Mapping(target = "requestDate", source = "visitDate") //TODO: поменять названия для visitDate и visitTime для соответствия Entity
    @Mapping(target = "requestTime", source = "visitTime")
    @Mapping(target = "actualEnterTime", ignore = true)
    @Mapping(target = "actualExitTime", ignore = true)
    @Mapping(target = "requestStartDate", ignore = true)
    @Mapping(target = "requestEndDate", ignore = true)
    @Mapping(target = "purpose", ignore = true)
    @Mapping(target = "status",
            expression = "java(StatusType.PENDING.getEnum(managerConfirmationResponseDTO.getStatus()))")
    @Mapping(target = "rejectReason",
            expression = "java(RejectReason.ATTACHMENTS.getEnum(managerConfirmationResponseDTO.getRejectReason()))")
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "worker", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateRequest(ManagerConfirmationResponseDTO managerConfirmationResponseDTO, @MappingTarget Request request);

    @Mapping(target = "idRequest", source = "id")
    @Mapping(target = "startApplicationPeriod", source = "requestStartDate")
    @Mapping(target = "endApplicationPeriod", source = "requestEndDate")
    @Mapping(target = "purposeVisit", expression = "java(request.getPurpose().getPurpose())")
    @Mapping(target = "department", expression = "java(request.getDepartment().getDepartmentName())")
    @Mapping(target = "workerName", expression = "java(request.getWorker().getWorkerName())")
    @Mapping(target = "visitors", ignore = true)
    @Mapping(target = "docs", ignore = true)
    void mapRequestToStaticRequestDTO(Request request, @MappingTarget StaticRequestDTO staticRequestDTO);

    @Mapping(target = "idRequest", source = "id")
    @Mapping(target = "appointmentType", expression = "java(request.getAppointmentType().getType())")
    @Mapping(target = "userNames", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "date", source = "requestDate")
    @Mapping(target = "time", source = "requestTime")
    @Mapping(target = "status", expression = "java(request.getStatus().getStatusType())")
    void mapToRowRequestDTO(Request request, @MappingTarget RowRequestsDTO rowRequestsDTO);

}
