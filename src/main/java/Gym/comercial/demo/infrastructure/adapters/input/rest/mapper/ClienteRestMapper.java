package Gym.comercial.demo.infrastructure.adapters.input.rest.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import Gym.comercial.demo.domain.model.Cliente;
import Gym.comercial.demo.infrastructure.adapters.input.rest.model.request.ClienteCreateRequest;
import Gym.comercial.demo.infrastructure.adapters.input.rest.model.response.ClienteResponse;

@Mapper(componentModel = "spring",unmappedTargetPolicy  =  ReportingPolicy.IGNORE)
public interface ClienteRestMapper {

    Cliente toCliente (ClienteCreateRequest request);

    ClienteResponse toClienteResponse (Cliente cliente);

    List<ClienteResponse> toClienteResponseList(List<Cliente> clienteList);
}
