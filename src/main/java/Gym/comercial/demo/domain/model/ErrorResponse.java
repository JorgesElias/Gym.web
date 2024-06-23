package Gym.comercial.demo.domain.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
@Setter
@Getter
public class ErrorResponse {


    private String code;

    private String message;


    private List<String> details;
    private LocalDateTime timestamp;
    
}
