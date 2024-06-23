package Gym.comercial.demo.domain.model;





import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    private Long idcliente;
    private String email;
    private String nombre;
    private String contrasena;

    
    private List<Membresia> membresias;
   

   
}
