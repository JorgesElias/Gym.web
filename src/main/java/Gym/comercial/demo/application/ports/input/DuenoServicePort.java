package Gym.comercial.demo.application.ports.input;

import java.util.List;


import Gym.comercial.demo.domain.model.Dueno;

public interface DuenoServicePort {
  Dueno findByid (Long iddueno);

    List<Dueno> findAll ();

    Dueno save (Dueno dueno);

    Dueno update (Long iddueno, Dueno dueno);

    void deleteByid(Long iddueno);
}
