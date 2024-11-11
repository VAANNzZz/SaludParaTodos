package com.exe.app.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.exe.app.services.PersonaService;

import jakarta.annotation.PostConstruct;

@Configuration
public class DataInitializer {

    @Autowired
    private PersonaService personaService;

    @PostConstruct
    public void init() {
        // Actualiza todas las contraseñas no cifradas en la base de datos
        personaService.actualizarContraseñasExistentes();
    }  
}
