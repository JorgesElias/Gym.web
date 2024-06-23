package Gym.comercial.demo.infrastructure.adapters.input.rest.model.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {
    
    private Long idcliente;
    private String email;
    private String nombre;
    private String contrasena;
}
