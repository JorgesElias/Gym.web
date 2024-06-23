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
public class DuenoCreateRequest {

    @NotBlank(message = "Field iddueno cannot be empty or null.")
    private Long iddueno;
}
