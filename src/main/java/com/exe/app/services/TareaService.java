package com.exe.app.services;

import com.exe.app.entity.Tarea;
import com.exe.app.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    public List<Tarea> getTareas() {
        return tareaRepository.findAll();  // Obtener todas las tareas
    }

    public void saveOrUpdate(Tarea tarea) {
        tareaRepository.save(tarea);  // Guardar o actualizar tarea
    }

    public void eliminarTarea(Long id) {
        tareaRepository.deleteById(id);  // Eliminar tarea
    }

    public List<Tarea> getTareasByAsignadorId(Long idPersonaAsignador)
        { return tareaRepository.findByAsignadorIdPersonas(idPersonaAsignador);
    }
    
    public List<Tarea> getTareasByPersonaId(Long idPersonaDestinatario) { 
        return tareaRepository.findByAsignadorIdPersonas(idPersonaDestinatario);
    }


}