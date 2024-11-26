/* package com.exe.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exe.app.services.PersonaService;

@Controller
public class PasswordResetController {

     @Autowired
    private PersonaService personaService; // Servicio para gestionar usuarios
    
    @Autowired
    private EmailService emailService; // Servicio para enviar correos

    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam("email") String email, 
                                        @RequestParam("g-recaptcha-response") String captchaResponse, 
                                        Model model) {
        // Verificar CAPTCHA usando Google API
        boolean captchaValido = CaptchaService.verifyCaptcha(captchaResponse);
        if (!captchaValido) {
            model.addAttribute("error", "Verificación CAPTCHA fallida.");
            return "forgot-password";
        }

        // Verificar si el correo existe
        if (!userService.emailExists(email)) {
            model.addAttribute("error", "El correo ingresado no está registrado.");
            return "forgot-password";
        }

        // Generar un código de verificación
        String verificationCode = userService.generateVerificationCode(email);

        // Enviar correo electrónico
        emailService.sendVerificationCode(email, verificationCode);

        model.addAttribute("success", "Se ha enviado un código de verificación a su correo.");
        return "redirect:/verify-code"; // Página para ingresar el código
    }

}
 */