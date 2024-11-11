package com.exe.app.ServiceInterface;

import java.util.Optional;

import com.exe.app.entity.Persona;

public interface IPersonasService {

    public void delete(int id_personas);

    Optional<Persona> findByNumeroDocumento(String numeroDocumento);

}
