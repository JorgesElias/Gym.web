package Gym.comercial.demo.application.ports.input;

import Gym.comercial.demo.domain.model.Cliente;


import java.util.List;
public interface ClienteServicePort {

    Cliente findByid (Long idcliente);

    List<Cliente> findAll ();

    Cliente save (Cliente cliente);

    Cliente update (Long idcliente, Cliente cliente);

    void deleteByid(Long idcliente);

   

}
