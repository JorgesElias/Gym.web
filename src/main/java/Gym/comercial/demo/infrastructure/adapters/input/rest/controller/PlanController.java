package Gym.comercial.demo.infrastructure.adapters.input.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Gym.comercial.demo.domain.model.Cliente;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/planes")
public class PlanController {

    @GetMapping("/{plan}")
    public String showPlanDetails(@PathVariable String plan, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Cliente usuarioLogueado = (Cliente) session.getAttribute("usuarioLogueado");
        if (usuarioLogueado != null) {
            String beneficios = obtenerBeneficiosDelPlan(plan);
            model.addAttribute("plan", plan);
            model.addAttribute("beneficios", beneficios);
            return "DetallesDelPlan";
        } else {
            redirectAttributes.addFlashAttribute("mensaje", "Debes iniciar sesión primero");
            return "redirect:/login";
        }
    }

    private String obtenerBeneficiosDelPlan(String plan) {
        switch (plan) {
            case "Bronce":
                return "Beneficios del Plan Bronce...";
            case "Plata":
                return "Beneficios del Plan Plata...";
            case "Oro":
                return "Beneficios del Plan Oro...";
            default:
                return "Beneficios no disponibles";
        }
    }
}