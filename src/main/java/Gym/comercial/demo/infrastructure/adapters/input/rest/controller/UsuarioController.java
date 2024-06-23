package Gym.comercial.demo.infrastructure.adapters.input.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Gym.comercial.demo.domain.model.Cliente;
import Gym.comercial.demo.infrastructure.adapters.output.persistence.ClientePersistenceAdapter;

@Controller
@RequestMapping("/crearcuenta")
public class UsuarioController {

    @Autowired
    private ClientePersistenceAdapter clienteAdapter;

    @GetMapping
    public String showCrearCuentaForm() {
        return "createcount";
    }

    @PostMapping
    public String createAccount(@RequestParam String nombre, 
                                @RequestParam String email, 
                                @RequestParam String password, 
                                Model model) {
        Cliente nuevoCliente = Cliente.builder()
                                      .nombre(nombre)
                                      .email(email)
                                      .contrasena(password)
                                      .build();

        clienteAdapter.save(nuevoCliente);

        model.addAttribute("mensaje", "Cuenta creada exitosamente!");

        return "redirect:/login"; 
    }
}

   

