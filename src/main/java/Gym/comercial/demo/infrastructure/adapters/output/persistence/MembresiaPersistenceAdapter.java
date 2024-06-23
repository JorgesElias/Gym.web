package Gym.comercial.demo.infrastructure.adapters.output.persistence;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Gym.comercial.demo.application.ports.output.MembresiaPersistencePort;
import Gym.comercial.demo.domain.model.Membresia;
import Gym.comercial.demo.infrastructure.adapters.output.persistence.entity.MembresiaEntity;
import Gym.comercial.demo.infrastructure.adapters.output.persistence.mapper.MembresiaPersistenceMapper;
import Gym.comercial.demo.infrastructure.adapters.output.persistence.repository.MembresiaRepository;
import net.glxn.qrgen.QRCode;

@Component
public class MembresiaPersistenceAdapter implements MembresiaPersistencePort {

    @Autowired
    private MembresiaRepository membresiaRepository;

    @Autowired
    private MembresiaPersistenceMapper membresiaPersistenceMapper;

    @Override
    public Optional<Membresia> findById(Long idMembresia) {
        return membresiaRepository.findById(idMembresia)
                .map(membresiaPersistenceMapper::toMembresia);
    }

    @Override
    public List<Membresia> findAll() {
        return membresiaPersistenceMapper.toMembresiasList(membresiaRepository.findAll());
    }

    @Override
    public Membresia save(Membresia membresia) {
        MembresiaEntity entity = membresiaPersistenceMapper.toMembresiaEntity(membresia);
        MembresiaEntity savedEntity = membresiaRepository.save(entity);
        return membresiaPersistenceMapper.toMembresia(savedEntity);
    }

    @Override
    public Membresia update(Long idMembresia, Membresia membresia) {
        MembresiaEntity entity = membresiaPersistenceMapper.toMembresiaEntity(membresia);
        entity.setIdMembresia(idMembresia);
        MembresiaEntity updatedEntity = membresiaRepository.save(entity);
        return membresiaPersistenceMapper.toMembresia(updatedEntity);
    }

    @Override
    public Membresia cancelarMembresia(Long idMembresia) {
        MembresiaEntity entity = membresiaRepository.findById(idMembresia)
                .orElseThrow(() -> new IllegalArgumentException("Membresia not found"));
        entity.setCancelarMembresia(true);
        MembresiaEntity updatedEntity = membresiaRepository.save(entity);
        return membresiaPersistenceMapper.toMembresia(updatedEntity);
    }

    @Override
    public Membresia renovarMembresia(Long idMembresia, Long duracionRenovacion) {
        MembresiaEntity entity = membresiaRepository.findById(idMembresia)
                .orElseThrow(() -> new IllegalArgumentException("Membresia not found"));
        entity.setFechaDeFin(entity.getFechaDeFin().plusWeeks(duracionRenovacion));
        MembresiaEntity updatedEntity = membresiaRepository.save(entity);
        return membresiaPersistenceMapper.toMembresia(updatedEntity);
    }

    @Override
    public Membresia generarCodigoQR(Long idMembresia) { 
        Optional<MembresiaEntity> optionalEntity = membresiaRepository.findById(idMembresia);
        if (optionalEntity.isPresent()) {
            MembresiaEntity entity = optionalEntity.get();
            String qrCodeBase64 = generateQRCode(String.valueOf(Math.round(Math.random() * 1000000)));
            entity.setCodigoQR(qrCodeBase64); 
            MembresiaEntity updatedEntity = membresiaRepository.save(entity);
            return membresiaPersistenceMapper.toMembresia(updatedEntity);
        }
        throw new IllegalArgumentException("Membresia not found");
    }

    private String generateQRCode(String text) {
        try {
            ByteArrayOutputStream stream = QRCode.from(text).withSize(250, 250).stream();
            byte[] qrImageBytes = stream.toByteArray();
            return Base64.getEncoder().encodeToString(qrImageBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error generating QR code", e);
        }
    }
}