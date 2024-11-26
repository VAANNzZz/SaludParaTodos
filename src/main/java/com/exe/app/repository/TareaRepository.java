/* package com.exe.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exe.app.entity.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
    // Buscar tareas por idPersonas del asignador (relación ManyToOne entre Tarea y Persona)
    List<Tarea> findByAsignadorIdPersonas(Long idPersonaAsignador);

    // Buscar tareas por idPersonas del destinatario (relación ManyToOne entre Tarea y Persona)
    List<Tarea> findByPersonaIdPersonas(Long idPersonaDestinatario);
}
    

 */