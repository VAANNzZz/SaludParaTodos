package com.exe.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.exe.app.entity.Persona;
import com.exe.app.repository.PersonaRepository;

@Service("personaService")
public class PersonaService{

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inyectar el PasswordEncoder

    @Autowired
    PersonaMapper personaMapper;

    public List<Persona> getPersona(){
        return personaRepository.findAll();
    }

    public Optional<Persona> obtenerPersonaPorNumeroDocumento(String numeroDocumento) {
        return personaRepository.findByNumeroDocumento(numeroDocumento);
    }

    public Optional<Persona> getPersonaById(Long idPersonas) {
        return personaRepository.findById(idPersonas);
    }

    public Optional<Persona> getByEmail(String Email) {
        return personaRepository.findByEmail(Email); 
    }
    
    public void updatePersona(PersonaDTO personaDTO) {
        Persona persona = personaRepository.findById(personaDTO.getIdPersonas()).get();
        personaMapper.updatePersonaFromDTO(personaDTO, persona);
        personaRepository.save(persona);
    }
    public void saveOrUpdate(Persona persona) {
        // Verificar si la contraseña ya está cifrada
        String contraseñaActual = persona.getContraseña();
        if (contraseñaActual != null && !contraseñaActual.startsWith("$2a$")) { // Si no está cifrada, cifrarla
            String encryptedPassword = passwordEncoder.encode(contraseñaActual);
            persona.setContraseña(encryptedPassword);
        }
        personaRepository.save(persona);
    }
    
    public void eliminarpersona(Long idPersonas) {
        if (personaRepository.existsById(idPersonas)) {
            personaRepository.deleteById(idPersonas);
        }
    }

    public boolean existsByEmail(String email) {
        return personaRepository.findByEmail(email).isPresent();
    }

    public boolean existsByNumeroDocumento(String numeroDocumento) {
        return personaRepository.findByNumeroDocumento(numeroDocumento).isPresent();
    }

    public boolean existsByNumeroContacto(String numeroContacto) {
        return personaRepository.findByNumeroContacto(numeroContacto).isPresent();
    }

    public void save(Persona persona) {
        personaRepository.save(persona);
    }


    public void actualizarContraseñasExistentes(){
        // Actualizar todas las contraseñas en la base de datos si no están cifradas
        List<Persona> personas = personaRepository.findAll();
        for (Persona persona : personas) {
            String contraseñaActual = persona.getContraseña();

            // Verificar si la contraseña es nula
            if (contraseñaActual == null) {
                continue; // Saltar a la siguiente iteración si la contraseña es nula
            }

            // Cifrar la contraseña solo si no está cifrada con BCrypt
            if (!contraseñaActual.startsWith("$2a$")) {
                String contraseñaCifrada = passwordEncoder.encode(contraseñaActual);
                persona.setContraseña(contraseñaCifrada);
                personaRepository.save(persona);
            }
        }
    }

    public Object findByRol(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByRol'");
    }
}
