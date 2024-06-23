package Gym.comercial.demo.infrastructure.adapters.output.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;


import Gym.comercial.demo.domain.model.Dueno;

import Gym.comercial.demo.infrastructure.adapters.output.persistence.entity.DuenoEntity;

@Mapper(componentModel = "spring")
public interface DuenoPersistenceMapper {

    DuenoEntity toDuenoEntity(Dueno dueno);
    Dueno toDueno(DuenoEntity entity);
    List<Dueno> toDuenoList(List<DuenoEntity> entityList);
}
