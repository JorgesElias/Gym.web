package Gym.comercial.demo.infrastructure.adapters.input.rest.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import Gym.comercial.demo.domain.model.Cliente;

import jakarta.servlet.http.HttpSession;





@Controller
public class HomeController {

    @GetMapping("/")
    public String landingPage(HttpSession session, Model model) {
        Cliente usuarioLogueado = (Cliente) session.getAttribute("usuarioLogueado");
        if (usuarioLogueado != null) {
            model.addAttribute("usuarioLogueado", usuarioLogueado);
            System.out.println("Usuario logueado en HomeController: " + usuarioLogueado.getEmail());  
        } else {
            System.out.println("No hay usuario logueado en HomeController.");  
        }
        return "landingPage";
    }
}







