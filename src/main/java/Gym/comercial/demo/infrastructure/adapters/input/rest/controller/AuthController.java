package Gym.comercial.demo.infrastructure.adapters.input.rest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import Gym.comercial.demo.domain.model.Cliente;
import Gym.comercial.demo.infrastructure.adapters.output.persistence.ClientePersistenceAdapter;

import jakarta.servlet.http.HttpSession;
@Controller
public class AuthController {
    @Autowired
    private ClientePersistenceAdapter clienteAdapter;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("error", false);
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, 
                        @RequestParam String password, 
                        HttpSession session, 
                        Model model) {
        Optional<Cliente> cliente = clienteAdapter.findByEmailAndPassword(email, password);
        
        if (cliente.isPresent()) {
            session.setAttribute("usuarioLogueado", cliente.get());
            return "redirect:/";
        } else {
            model.addAttribute("error", true);
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}