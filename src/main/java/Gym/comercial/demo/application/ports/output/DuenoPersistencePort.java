package Gym.comercial.demo.application.ports.output;

import java.util.List;
import java.util.Optional;


import Gym.comercial.demo.domain.model.Dueno;

public interface DuenoPersistencePort {

    Optional<Dueno> findByid(Long iddueno);

 List<Dueno> finAll();

 Dueno save(Dueno dueno);

    void deleteByid (Long iddueno);
}
