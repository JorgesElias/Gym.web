package Gym.comercial.demo.infrastructure.adapters.input.rest.model.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class MembresiaCreateRequest {

    @NotBlank(message = "Field tipoMembresia cannot be empty or null.")
    private String tipoMembresia;

    @NotBlank(message = "Field fechaDeInicio cannot be empty or null.")
    private LocalDate fechaDeInicio;

    @NotNull(message = "Field fechaDeFin cannot be empty or null.")
    private LocalDate fechaDeFin; 

    @NotBlank(message = "Field cancelarMembresia cannot be empty or null.")
    private Boolean cancelarMembresia;

    @NotBlank(message = "Field codigoQR cannot be empty or null.")
    private Long codigoQR;
}