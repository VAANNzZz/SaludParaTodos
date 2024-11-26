package com.exe.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exe.app.entity.Persona;

@Repository("PersonaRepository")
public interface PersonaRepository extends JpaRepository<Persona, Long>{

    Optional<Persona> findByNumeroDocumento(String numeroDocumento);

    // Método para verificar si ya existe una persona con el mismo número de documento
    boolean existsByNumeroDocumento(String numeroDocumento);

    // Método para encontrar persona por número de contacto
    Optional<Persona> findByNumeroContacto(String numeroContacto);

    // Método para verificar si ya existe una persona con el mismo número de contacto
    boolean existsByNumeroContacto(String numeroContacto);

    // Método para encontrar persona por correo electrónico
    Optional<Persona> findByEmail(String email);

    // Método para verificar si ya existe una persona con el mismo correo electrónico
    boolean existsByEmail(String email);

}
