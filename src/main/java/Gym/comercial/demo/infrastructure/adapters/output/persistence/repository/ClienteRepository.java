package Gym.comercial.demo.infrastructure.adapters.output.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import Gym.comercial.demo.infrastructure.adapters.output.persistence.entity.ClienteEntity;
@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity,Long>{
 
  Optional<ClienteEntity> findByEmailAndContrasena(String email, String contrasena);
}
