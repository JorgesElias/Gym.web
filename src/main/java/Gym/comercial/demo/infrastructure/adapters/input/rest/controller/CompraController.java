package Gym.comercial.demo.infrastructure.adapters.input.rest.controller;


import java.io.ByteArrayOutputStream;
import java.time.LocalDate;

import java.util.Base64;

import java.util.UUID;


import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Gym.comercial.demo.application.service.ClienteService;
import Gym.comercial.demo.application.service.MembresiaService;
import Gym.comercial.demo.domain.model.Cliente;
import Gym.comercial.demo.domain.model.Membresia;
import jakarta.servlet.http.HttpSession;
import net.glxn.qrgen.QRCode;

@Controller
@RequestMapping("/user")
public class CompraController {

    private final ClienteService clienteService;
    private final MembresiaService membresiaService;


    public CompraController(ClienteService clienteService, MembresiaService membresiaService) {
        this.clienteService = clienteService;
        this.membresiaService = membresiaService;
    }

    @PostMapping("/comprar")
    public String comprar(@RequestParam("plan") String plan, HttpSession session, RedirectAttributes redirectAttributes) {
        Cliente usuarioLogueado = (Cliente) session.getAttribute("usuarioLogueado");
        if (usuarioLogueado != null) {
            
            if (usuarioLogueado.getMembresias() != null && !usuarioLogueado.getMembresias().isEmpty()) {
                redirectAttributes.addFlashAttribute("mensajeError", "Ya tienes una membresía activa. No puedes comprar otra.");
                return "redirect:/planes/" + plan;
            }

          
            Long identificacionUnica = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
            String qrCodeBase64 = generateQRCode(identificacionUnica.toString());

            
            Membresia nuevaMembresia = Membresia.builder()
                .idMembresia(identificacionUnica)
                .tipoMembresia(plan)
                .fechaDeInicio(LocalDate.now())
                .fechaDeFin(LocalDate.now().plusMonths(1))
                .cancelarMembresia(false)
                .codigoQR(qrCodeBase64)
                .build();

           
            membresiaService.save(nuevaMembresia);

           
            usuarioLogueado.getMembresias().add(nuevaMembresia);
            clienteService.update(usuarioLogueado.getIdcliente(), usuarioLogueado);

           
            redirectAttributes.addFlashAttribute("mensaje", "Compra realizada correctamente. Tu perfil ha sido actualizado.");
            return "redirect:/user/profile"; 
        } else {
            return "redirect:/login";
        }
    }

    private String generateQRCode(String text) {
        try {
            ByteArrayOutputStream stream = QRCode.from(text).withSize(250, 250).stream();
            byte[] qrImageBytes = stream.toByteArray();
            return Base64.getEncoder().encodeToString(qrImageBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error generating QR code", e);
        }
    }
}