/* package com.exe.app.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.exe.app.entity.Persona;
import com.exe.app.entity.Tarea;
import com.exe.app.services.PersonaService;
import com.exe.app.services.TareaService;

@Controller
public class TareaController {

    @Autowired
    private PersonaService personaService;

    @Autowired
    private TareaService tareaService;

    @GetMapping("/tareasAprendices")
    public String mostrarTareasAprendices( @RequestParam Long idPersonaDestinatario, Model model){
        Persona destinatario = personaService.getPersonaById(idPersonaDestinatario). orElse(null);
        if (destinatario == null || destinatario.getRol().getNombre_rol().equals("user")){
            model.addAttribute("error", "aprendiz no encontrado");
            return "errorPage";
        }
        List<Tarea> misTareas = tareaService.getTareasByPersonaId(idPersonaDestinatario);
        model.addAttribute("misTareas", misTareas);
        model.addAttribute("personaId", idPersonaDestinatario);
        return "tareasAprendices";
    }

    @GetMapping("/asignarTarea")
    public String mostrarAsignarTarea (Model model){
        model.addAttribute("tarea", new Tarea());
        model.addAttribute("aprendices", personaService.findByRol("user"));
        model.addAttribute("orientadores", personaService.findByRol("administrador"));
        return "asignarTarea";
    }

    @PostMapping("asignarTarea")
    public String asignarTarea(@RequestParam  Long idPersonaAsignador, @RequestParam Long idPersonaDestinatario,
                                @RequestParam String descripcion, @RequestParam String estado, 
                                @RequestParam("fechaEntrega") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaEntrega,
                                @RequestParam("material") MultipartFile materialFile, Model model){
    Persona asignador = personaService.getPersonaById(idPersonaAsignador).orElse(null);
    if (asignador == null || !asignador.getRol().getNombre_rol().equals("administrador")){ 
        model.addAttribute("error", "Orientador no encontrado o no tiene el rol adecuado.");
        return "errorPage"; 
    }

    Persona destinatario = personaService.getPersonaById(idPersonaDestinatario).orElse(null);
    if (destinatario == null || destinatario .getRol(). getNombre_rol().equals("user")){
        model.addAttribute("error", "Aprendiz no encontrado");
        return "errorPage";
    }

    Tarea tarea = new Tarea();
    tarea.setDescripcion(descripcion);
    tarea.setEstado(estado);
    tarea.setFechaEntrega(fechaEntrega);
    if (!materialFile.isEmpty()) {
        String material = materialFile.getOriginalFilename();
        tarea.setMaterial(material);
    }
    tarea.setPersona(destinatario);
    tarea.setAsignador(asignador);
    tareaService.saveOrUpdate(tarea);

    return "redirect:/tareasOreintadores";

}

    @GetMapping("/visualizarTarea")
    public String  verTareasAsignadas (@RequestParam Long idPersonaAsignador, Model model){
    Persona asignador = personaService.getPersonaById(idPersonaAsignador).orElse(null);
    if (asignador == null || !asignador.getRol().getNombre_rol().equals("administrador")){
        model.addAttribute("error", "No tiene permiso para acceder a esta sección");
        return "errorpage";
    }
    
    List<Tarea> tareasAsignadas = tareaService.getTareasByAsignadorId(idPersonaAsignador);
    model.addAttribute("tareasAsignadas", tareasAsignadas);
    return "visualizarTarea";

}

    //Subir tarea por el aprendiz
    @PostMapping("/subirTarea")
    public String subirTarea(@RequestParam Long idPersonaDestinatario, @RequestParam String descripcion, 
                            @RequestParam("fechaEntrega") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaEntrega, Model model){
        Persona destinatario = personaService.getPersonaById(idPersonaDestinatario).orElse(null);
        if (destinatario == null || !destinatario.getRol().getNombre_rol().equals("user")) {
            model.addAttribute("error", "Aprendiz no enconctrado");
            return "errorPage";
        }
    
        LocalDate currenDate = LocalDate.now();
        if (fechaEntrega.isBefore(currenDate)){
            model.addAttribute("error", "No se puede subir la tarea después del tiempo establecido");
            return "errorPage";
        }
    Tarea tarea = new Tarea();
    tarea.setDescripcion(descripcion);
    tarea.setEstado("pendiente");
    tarea.setPersona(destinatario);
    tareaService.saveOrUpdate(tarea);

    return "redirect:/tareasAprendices?idPersonaDestinatario=" + idPersonaDestinatario;
    }

}


 */