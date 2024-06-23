package Gym.comercial.demo.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import Gym.comercial.demo.application.ports.input.DuenoServicePort;
import Gym.comercial.demo.application.ports.output.DuenoPersistencePort;
import Gym.comercial.demo.domain.exception.DuenoNotFoundException;
import Gym.comercial.demo.domain.model.Dueno;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DuenoService implements DuenoServicePort {

    private final DuenoPersistencePort duenoPersistencePort;

    @Override
    public Dueno findByid(Long iddueno) {
        return duenoPersistencePort.findByid(iddueno).orElseThrow(DuenoNotFoundException::new);
    }

    @Override
    public List<Dueno> findAll() {
        return duenoPersistencePort.finAll();
    }

    @Override
    public Dueno save(Dueno dueno) {
        return duenoPersistencePort.save(dueno);
    }

    @Override
    public Dueno update(Long iddueno, Dueno dueno) {
        return duenoPersistencePort.findByid(iddueno)
            .map(existingDueno -> {
                return duenoPersistencePort.save(existingDueno);
            })
            .orElseThrow(DuenoNotFoundException::new);
    }

    @Override
    public void deleteByid(Long iddueno) {
        if (duenoPersistencePort.findByid(iddueno).isEmpty()) {
            throw new DuenoNotFoundException();
        }
        duenoPersistencePort.deleteByid(iddueno);
    }
}
