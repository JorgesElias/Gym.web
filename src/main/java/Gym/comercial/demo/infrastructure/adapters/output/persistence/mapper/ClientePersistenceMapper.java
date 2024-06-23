package Gym.comercial.demo.infrastructure.adapters.output.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import Gym.comercial.demo.domain.model.Cliente;
import Gym.comercial.demo.infrastructure.adapters.output.persistence.entity.ClienteEntity;

@Mapper(componentModel = "spring")
public interface ClientePersistenceMapper {
    ClienteEntity toClienteEntity(Cliente cliente);
    Cliente toCliente(ClienteEntity entity);
    List<Cliente> toClienteList(List<ClienteEntity> entityList);
}
