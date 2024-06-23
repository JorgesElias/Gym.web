package Gym.comercial.demo.application.ports.output;

import java.util.List;
import java.util.Optional;

import Gym.comercial.demo.domain.model.Cliente;



public interface ClientePersistencePort {

    Optional<Cliente> findByid(Long idcliente);
    List<Cliente> findAll(); 
    Cliente save(Cliente cliente);
    void deleteByid(Long idcliente);
    
}
