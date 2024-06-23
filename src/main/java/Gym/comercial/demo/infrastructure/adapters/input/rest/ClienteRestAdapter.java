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

import Gym.comercial.demo.application.ports.input.ClienteServicePort;
import Gym.comercial.demo.infrastructure.adapters.input.rest.mapper.ClienteRestMapper;
import Gym.comercial.demo.infrastructure.adapters.input.rest.model.request.ClienteCreateRequest;

import Gym.comercial.demo.infrastructure.adapters.input.rest.model.response.ClienteResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/Clientes")
@RestController
@RequiredArgsConstructor
public class ClienteRestAdapter {
 private final ClienteServicePort servicePort;

 private final ClienteRestMapper restMapper;

 

 @GetMapping("/v1/cliente")
 public List<ClienteResponse> finAll(){
    return restMapper.toClienteResponseList(servicePort.findAll());
 }


 @GetMapping("/v1/cliente/{idcliente}")
 public ClienteResponse findByid(@PathVariable Long idcliente){
    return restMapper.toClienteResponse(servicePort.findByid(idcliente));

}
    @PostMapping("/v1/cliente")
    public ResponseEntity<ClienteResponse> save (@Valid @RequestBody ClienteCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(restMapper.toClienteResponse(servicePort.save(restMapper.toCliente(request))));
    }

    @PutMapping("/v1/cliente/{idcliente}")
      public ClienteResponse update(@PathVariable Long idcliente,@Valid @RequestBody ClienteCreateRequest request){
       
        return restMapper.toClienteResponse(servicePort.update(idcliente, restMapper.toCliente(request)));
      }

      @DeleteMapping("/v1/cliente/{idcliente}")
      public void delete(@PathVariable Long idcliente){
       
      servicePort.deleteByid(idcliente);
      }
 }

