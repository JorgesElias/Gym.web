package Gym.comercial.demo.application.ports.input;


import java.util.List;

import Gym.comercial.demo.domain.model.Membresia;

public interface MembresiaServicePort {
    Membresia findById(Long idMembresia);
    List<Membresia> findAll();
    Membresia save(Membresia membresia);
    Membresia update(Long idMembresia, Membresia membresia);
    Membresia cancelarMembresia(Long idMembresia);
    Membresia renovarMembresia(Long idMembresia, Long duracionRenovacion);
    Membresia generarCodigoQR(Long idMembresia);
}