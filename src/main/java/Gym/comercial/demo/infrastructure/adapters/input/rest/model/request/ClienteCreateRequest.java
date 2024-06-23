package Gym.comercial.demo.infrastructure.adapters.input.rest.model.request;




import jakarta.validation.constraints.NotBlank;

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
public class ClienteCreateRequest {

@NotBlank(message = "Field actualizarinformacion cannot be empty or null.")
    private Long actualizarinformacion;

    @NotBlank(message = "Field email cannot be empty or null.")
    private String email;

    
}
