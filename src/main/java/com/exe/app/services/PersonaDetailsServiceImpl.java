package com.exe.app.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.exe.app.entity.Persona;
import com.exe.app.entity.SecurityPersona;
import com.exe.app.repository.PersonaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class PersonaDetailsServiceImpl implements UserDetailsService {

    private PersonaRepository personaRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String numeroDocumento) throws UsernameNotFoundException {
        Persona persona = personaRepository.findByNumeroDocumento(numeroDocumento)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new SecurityPersona(persona);
    }
    
}
