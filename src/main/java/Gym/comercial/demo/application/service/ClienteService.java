package Gym.comercial.demo.application.service;


import java.util.List;


import org.springframework.stereotype.Service;

import Gym.comercial.demo.application.ports.input.ClienteServicePort;
import Gym.comercial.demo.application.ports.output.ClientePersistencePort;
import Gym.comercial.demo.domain.exception.ClienteNotFoundException;

import Gym.comercial.demo.domain.model.Cliente;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService implements ClienteServicePort {

    private final ClientePersistencePort persistencePort;

    @Override
    public Cliente findByid(Long idcliente) {
        return persistencePort.findByid(idcliente).orElseThrow(ClienteNotFoundException::new);
    }

    @Override
    public List<Cliente> findAll() {
        return persistencePort.findAll();
    }

    @Override
    public Cliente save(Cliente cliente) {
        return persistencePort.save(cliente);
    }

    @Override
    public Cliente update(Long idcliente, Cliente cliente) {
        return persistencePort.findByid(idcliente)
                .map(existingCliente -> {
                    existingCliente.setNombre(cliente.getNombre());
                    existingCliente.setEmail(cliente.getEmail());
                    existingCliente.setContrasena(cliente.getContrasena());
                    return persistencePort.save(existingCliente);
                })
                .orElseThrow(ClienteNotFoundException::new);
    }

    @Override
    public void deleteByid(Long idcliente) {
        if (persistencePort.findByid(idcliente).isEmpty()) {
            throw new ClienteNotFoundException();
        }
        persistencePort.deleteByid(idcliente);
    }
}

