package Gym.comercial.demo.infrastructure.adapters.input.rest.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


import Gym.comercial.demo.domain.model.Dueno;

import Gym.comercial.demo.infrastructure.adapters.input.rest.model.request.DuenoCreateRequest;

import Gym.comercial.demo.infrastructure.adapters.input.rest.model.response.DuenoResponse;

@Mapper(componentModel = "spring",unmappedTargetPolicy  =  ReportingPolicy.IGNORE)
public interface DuenoRestMapper {

    Dueno toDueno (DuenoCreateRequest request);

    DuenoResponse toDuenoResponse (Dueno dueno);

    List<DuenoResponse> toDuenoResponseList(List<Dueno> duenoList);
}
