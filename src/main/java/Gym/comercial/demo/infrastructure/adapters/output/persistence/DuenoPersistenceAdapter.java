package Gym.comercial.demo.infrastructure.adapters.output.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import Gym.comercial.demo.application.ports.output.DuenoPersistencePort;
import Gym.comercial.demo.domain.model.Dueno;

import Gym.comercial.demo.infrastructure.adapters.output.persistence.mapper.DuenoPersistenceMapper;
import Gym.comercial.demo.infrastructure.adapters.output.persistence.repository.DuenoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DuenoPersistenceAdapter implements DuenoPersistencePort {

    private final DuenoRepository duenoRepository;

    private final DuenoPersistenceMapper duenoPersistenceMapper;

      @Override
    public Optional<Dueno> findByid(Long iddueno) {
      return duenoRepository.findById(iddueno).map(duenoPersistenceMapper::toDueno);
    }

    @Override
    public List<Dueno> finAll() {
       return duenoPersistenceMapper.toDuenoList(duenoRepository.findAll());
    }

    @Override
    public Dueno save(Dueno dueno) {
      return duenoPersistenceMapper.toDueno(duenoRepository.save(duenoPersistenceMapper.toDuenoEntity(dueno)));
    }

    @Override
    public void deleteByid(Long iddueno) {
        duenoRepository.deleteById(iddueno);
    }
}
