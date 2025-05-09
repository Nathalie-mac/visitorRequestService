package com.example.team2.mapper;

import com.example.team2.model.Request;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MapperUpdateRequest {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "appointmentType", ignore = true)
    @Mapping(target = "requestDate", source = "") //TODO: Заполнить source для нужных полей
    @Mapping(target = "requestTime", source = "")
    @Mapping(target = "actualEnterTime", ignore = true)
    @Mapping(target = "actualExitTime", ignore = true)
    @Mapping(target = "requestStartDate", ignore = true)
    @Mapping(target = "requestEndDate", ignore = true)
    @Mapping(target = "purpose", source = "")
    @Mapping(target = "status", source = "")
    @Mapping(target = "rejectReason", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "worker", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateRequest(int ok, @MappingTarget Request request);// TODO: Заменить int ok на DTO
}
