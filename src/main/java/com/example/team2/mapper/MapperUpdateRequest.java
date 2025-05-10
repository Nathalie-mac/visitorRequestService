package com.example.team2.mapper;

import com.example.team2.dto.response.ManagerConfirmationResponseDTO;
import com.example.team2.model.Request;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MapperUpdateRequest {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "appointmentType", ignore = true)
    @Mapping(target = "requestDate", source = "visitDate") //TODO: поменять названия для visitDate и visitTime для соответствия Entity
    @Mapping(target = "requestTime", source = "visitTime")
    @Mapping(target = "actualEnterTime", ignore = true)
    @Mapping(target = "actualExitTime", ignore = true)
    @Mapping(target = "requestStartDate", ignore = true)
    @Mapping(target = "requestEndDate", ignore = true)
    @Mapping(target = "purpose", ignore = true)
    @Mapping(target = "status", source = "status")
    @Mapping(target = "rejectReason", source = "rejectReason")
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "worker", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateRequest(ManagerConfirmationResponseDTO managerConfirmationResponseDTO, @MappingTarget Request request);// TODO: Заменить int ok на DTO
}
