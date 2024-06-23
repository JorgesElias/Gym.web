package Gym.comercial.demo.application.ports.output;


import java.util.List;
import java.util.Optional;
import Gym.comercial.demo.domain.model.Membresia;

public interface MembresiaPersistencePort {
    Optional<Membresia> findById(Long idMembresia);
    List<Membresia> findAll();
    Membresia save(Membresia membresia);
    Membresia update(Long idMembresia, Membresia membresia);
    Membresia cancelarMembresia(Long idMembresia);
    Membresia renovarMembresia(Long idMembresia, Long duracionRenovacion);
    Membresia generarCodigoQR(Long idMembresia);
}