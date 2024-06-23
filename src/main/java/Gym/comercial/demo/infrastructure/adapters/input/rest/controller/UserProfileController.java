package Gym.comercial.demo.infrastructure.adapters.input.rest.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import Gym.comercial.demo.application.service.ClienteService;
import Gym.comercial.demo.domain.model.Cliente;


import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserProfileController {

    private final ClienteService clienteService;

    public UserProfileController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/profile")
    public String showUserProfile(Model model, HttpSession session) {
        Cliente usuarioLogueado = (Cliente) session.getAttribute("usuarioLogueado");
        if (usuarioLogueado != null) {
            model.addAttribute("usuario", usuarioLogueado);
            return "PerfilDeUsuario"; 
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/profile")
    public String updateUserProfile(@ModelAttribute Cliente usuario, HttpSession session, RedirectAttributes redirectAttributes) {
        Cliente usuarioLogueado = (Cliente) session.getAttribute("usuarioLogueado");
        if (usuarioLogueado != null) {
            try {
                usuarioLogueado.setNombre(usuario.getNombre());
                usuarioLogueado.setEmail(usuario.getEmail());
                usuarioLogueado.setContrasena(usuario.getContrasena());
               
                clienteService.update(usuarioLogueado.getIdcliente(), usuarioLogueado);
                session.setAttribute("usuarioLogueado", usuarioLogueado);
                redirectAttributes.addFlashAttribute("mensaje", "Perfil actualizado correctamente");
                return "redirect:/user/profile";
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("mensajeError", "Ocurrió un error al actualizar el perfil.");
                e.printStackTrace();
                return "redirect:/user/profile";
            }
        } else {
            return "redirect:/login";
        }
    }
}