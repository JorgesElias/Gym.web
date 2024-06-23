package Gym.comercial.demo.infrastructure.adapters.input.rest.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import Gym.comercial.demo.domain.model.Membresia;
import Gym.comercial.demo.infrastructure.adapters.input.rest.model.request.MembresiaCreateRequest;
import Gym.comercial.demo.infrastructure.adapters.input.rest.model.response.MembresiaResponse;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MembresiaRestMapper {

   
    Membresia toMembresia(MembresiaCreateRequest request);

    MembresiaResponse toMembresiaResponse(Membresia membresia);

    List<MembresiaResponse> toMembresiaResponseList(List<Membresia> membresiaList);
}