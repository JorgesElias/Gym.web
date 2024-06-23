package Gym.comercial.demo.domain.model;

import java.time.LocalDate;


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
public class Membresia {

    private Long idMembresia;
    
    private String tipoMembresia;
    private LocalDate fechaDeInicio;
    private LocalDate fechaDeFin;
    private Boolean cancelarMembresia;
    private String codigoQR;

    
}
