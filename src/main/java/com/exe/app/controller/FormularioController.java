/* package com.exe.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormularioController {

    @PostMapping("/procesarFormulario")
    public String procesarFormulario(
            @RequestParam("usuario") String usuario, 
            @RequestParam("contraseña") String contraseña, 
            Model model) {
        
        // Procesar los valores como desees, por ejemplo, imprimir en consola
        System.out.println("usuario: " + usuario);
        System.out.println("Contraseña: " + contraseña);
        
        // Añadir los valores al modelo para mostrarlos en una vista
        model.addAttribute("usuario", usuario);
        model.addAttribute("contraseña", contraseña);
        
        // Redirigir a una vista llamada "resultado"
        return "resultado";
    }
} */