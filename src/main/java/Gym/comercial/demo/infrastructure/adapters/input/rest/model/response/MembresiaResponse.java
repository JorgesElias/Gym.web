package Gym.comercial.demo.infrastructure.adapters.input.rest.model.response;

import java.time.LocalDate;

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
public class MembresiaResponse {
    private Long idMembresia;
    private String tipoMembresia;
    private LocalDate fechaDeInicio;
    private LocalDate fechaDeFin;
    private Boolean cancelarMembresia;
    private String codigoQR;
}