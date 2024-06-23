package Gym.comercial.demo.infrastructure.adapters.output.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Gym.comercial.demo.infrastructure.adapters.output.persistence.entity.MembresiaEntity;

@Repository
public interface MembresiaRepository  extends JpaRepository<MembresiaEntity, Long>{

}
