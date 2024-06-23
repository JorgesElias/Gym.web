package Gym.comercial.demo.infrastructure.adapters.input.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Gym.comercial.demo.application.ports.input.DuenoServicePort;
import Gym.comercial.demo.infrastructure.adapters.input.rest.mapper.DuenoRestMapper;
import Gym.comercial.demo.infrastructure.adapters.input.rest.model.request.DuenoCreateRequest;
import Gym.comercial.demo.infrastructure.adapters.input.rest.model.response.DuenoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/Dueños")
@RestController
@RequiredArgsConstructor
public class DuenoRestAdapter {

    private final DuenoServicePort duenoServicePort;

    private final DuenoRestMapper duenoRestMapper;

    @GetMapping("/v1/dueno")
    public List<DuenoResponse> findAll() {
        return duenoRestMapper.toDuenoResponseList(duenoServicePort.findAll());
    }

    @GetMapping("/v1/dueno/{iddueno}")
    public DuenoResponse findByid(@PathVariable Long iddueno) {
        return duenoRestMapper.toDuenoResponse(duenoServicePort.findByid(iddueno));
    }

    @PostMapping("/v1/dueno")
    public ResponseEntity<DuenoResponse> save(@Valid @RequestBody DuenoCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(duenoRestMapper.toDuenoResponse(duenoServicePort.save(duenoRestMapper.toDueno(request))));
    }

    @PutMapping("/v1/dueno/{iddueno}")
    public DuenoResponse update(@PathVariable Long iddueno, @Valid @RequestBody DuenoCreateRequest request) {
        return duenoRestMapper.toDuenoResponse(duenoServicePort.update(iddueno, duenoRestMapper.toDueno(request)));
    }

    @DeleteMapping("/v1/dueno/{iddueno}")
    public void delete(@PathVariable Long iddueno) {
        duenoServicePort.deleteByid(iddueno);
    }
}

