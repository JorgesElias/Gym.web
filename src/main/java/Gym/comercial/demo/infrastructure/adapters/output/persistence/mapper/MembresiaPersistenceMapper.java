package Gym.comercial.demo.infrastructure.adapters.output.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import Gym.comercial.demo.domain.model.Membresia;
import Gym.comercial.demo.infrastructure.adapters.output.persistence.entity.MembresiaEntity;

@Mapper(componentModel = "spring")
public interface MembresiaPersistenceMapper {

    MembresiaEntity toMembresiaEntity (Membresia membresia);

    Membresia toMembresia (MembresiaEntity entity);

    List<Membresia> toMembresiasList(List<MembresiaEntity> entityList);
}
