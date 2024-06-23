package Gym.comercial.demo.infrastructure.adapters.input.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Gym.comercial.demo.application.ports.input.MembresiaServicePort;
import Gym.comercial.demo.domain.model.Membresia;
import Gym.comercial.demo.infrastructure.adapters.input.rest.mapper.MembresiaRestMapper;
import Gym.comercial.demo.infrastructure.adapters.input.rest.model.request.MembresiaCreateRequest;
import Gym.comercial.demo.infrastructure.adapters.input.rest.model.response.MembresiaResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/membresia")
@RequiredArgsConstructor
public class MembresiaRestAdapter {

    private final MembresiaServicePort servicePort;
    private final MembresiaRestMapper restMapper;

    @GetMapping("/v1/membresias")
    public List<MembresiaResponse> findAll() {
        return restMapper.toMembresiaResponseList(servicePort.findAll());
    }

    @GetMapping("/v1/membresia/{idMembresia}")
    public MembresiaResponse findById(@PathVariable Long idMembresia) {
        return restMapper.toMembresiaResponse(servicePort.findById(idMembresia));
    }

    @PostMapping("/v1/membresia")
    public ResponseEntity<MembresiaResponse> save(@Valid @RequestBody MembresiaCreateRequest request) {
        Membresia membresia = restMapper.toMembresia(request);
        Membresia savedMembresia = servicePort.save(membresia);
        MembresiaResponse response = restMapper.toMembresiaResponse(savedMembresia);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/v1/membresia/{idMembresia}")
    public MembresiaResponse update(@PathVariable Long idMembresia, @Valid @RequestBody MembresiaCreateRequest request) {
        Membresia membresia = restMapper.toMembresia(request);
        return restMapper.toMembresiaResponse(servicePort.update(idMembresia, membresia));
    }

    @PutMapping("/v1/membresia/{idMembresia}/cancelar")
    public MembresiaResponse cancelarMembresia(@PathVariable Long idMembresia) {
        return restMapper.toMembresiaResponse(servicePort.cancelarMembresia(idMembresia));
    }

    @PutMapping("/v1/membresia/{idMembresia}/renovar")
    public MembresiaResponse renovarMembresia(@PathVariable Long idMembresia, @RequestParam Long duracionRenovacion) {
        return restMapper.toMembresiaResponse(servicePort.renovarMembresia(idMembresia, duracionRenovacion));
    }

    @PutMapping("/v1/membresia/{idMembresia}/generar-codigoqr")
    public MembresiaResponse generarCodigoQR(@PathVariable Long idMembresia) {
        return restMapper.toMembresiaResponse(servicePort.generarCodigoQR(idMembresia));
    }
}