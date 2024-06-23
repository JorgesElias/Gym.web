package Gym.comercial.demo.infrastructure.adapters.output.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import Gym.comercial.demo.application.ports.output.ClientePersistencePort;
import Gym.comercial.demo.domain.model.Cliente;
import Gym.comercial.demo.infrastructure.adapters.output.persistence.mapper.ClientePersistenceMapper;
import Gym.comercial.demo.infrastructure.adapters.output.persistence.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Component
public class ClientePersistenceAdapter  implements ClientePersistencePort{


    private final ClienteRepository repository;
    private final ClientePersistenceMapper mapper;

    @Override
    public Optional<Cliente> findByid(Long idcliente) {
        return repository.findById(idcliente).map(mapper::toCliente);
    }

    @Override
    public List<Cliente> findAll() {
        return mapper.toClienteList(repository.findAll());
    }

    @Override
    public Cliente save(Cliente cliente) {
        return mapper.toCliente(repository.save(mapper.toClienteEntity(cliente)));
    }

    @Override
    public void deleteByid(Long idcliente) {
        repository.deleteById(idcliente);
    }

    public Optional<Cliente> findByEmailAndPassword(String email, String password) {
        return repository.findByEmailAndContrasena(email, password).map(mapper::toCliente);
    }
}
