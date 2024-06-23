package Gym.comercial.demo.application.service;


import java.time.LocalDate;

import java.util.List;

import org.springframework.stereotype.Service;

import Gym.comercial.demo.application.ports.input.MembresiaServicePort;
import Gym.comercial.demo.application.ports.output.MembresiaPersistencePort;
import Gym.comercial.demo.domain.exception.MembresiaNotFoundException;
import Gym.comercial.demo.domain.model.Membresia;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class MembresiaService implements MembresiaServicePort {

    private final MembresiaPersistencePort membresiaPersistencePort;

    @Override
    public Membresia findById(Long idMembresia) {
        return membresiaPersistencePort.findById(idMembresia)
                .orElseThrow(MembresiaNotFoundException::new);
    }

    @Override
    public List<Membresia> findAll() {
        return membresiaPersistencePort.findAll();
    }

    @Override
    public Membresia save(Membresia membresia) {
        return membresiaPersistencePort.save(membresia);
    }

    @Override
    public Membresia update(Long idMembresia, Membresia membresia) {
        return membresiaPersistencePort.findById(idMembresia)
                .map(existingMembresia -> {
                    existingMembresia.setTipoMembresia(membresia.getTipoMembresia());
                    existingMembresia.setFechaDeInicio(membresia.getFechaDeInicio());
                    existingMembresia.setFechaDeFin(membresia.getFechaDeFin());
                    return membresiaPersistencePort.save(existingMembresia);
                })
                .orElseThrow(MembresiaNotFoundException::new);
    }

    @Override
    public Membresia cancelarMembresia(Long idMembresia) {
        Membresia membresia = findById(idMembresia);
        membresia.setCancelarMembresia(true);
        return membresiaPersistencePort.save(membresia);
    }

    @Override
    public Membresia renovarMembresia(Long idMembresia, Long duracionRenovacion) {
        Membresia membresia = findById(idMembresia);
        LocalDate nuevaFechaDeFin = membresia.getFechaDeFin().plusWeeks(duracionRenovacion);
        membresia.setFechaDeFin(nuevaFechaDeFin);
        return membresiaPersistencePort.save(membresia);
    }

    @Override
    public Membresia generarCodigoQR(Long idMembresia) {
        return membresiaPersistencePort.generarCodigoQR(idMembresia);
    }
}