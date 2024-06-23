package Gym.comercial.demo.infrastructure.adapters.output.persistence.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Membresia")
public class MembresiaEntity {
   @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long idMembresia;
    
    private String tipoMembresia;
    private LocalDate fechaDeInicio;
    private LocalDate fechaDeFin;
    private Boolean cancelarMembresia;
    private String codigoQR;



     @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;
   
}
