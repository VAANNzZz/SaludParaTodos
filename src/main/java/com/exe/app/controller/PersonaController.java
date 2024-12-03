package com.exe.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.exe.app.entity.Persona;
import com.exe.app.services.PersonaService;

@RestController
@RequestMapping("/home/Personas")
public class PersonaController {

    @Autowired
    PersonaService personaService;

    /*
     * @GetMapping
     * public String mostrar(){
     * return "";
     * }
     */

    @GetMapping
    public List<Persona> getAll() {
        return personaService.getPersona();
    }

    @GetMapping("/eliminarpersona/{idPersonas}")
    public String eliminarpersona(@PathVariable("idPersonas") Long idPersonas, RedirectAttributes redirectAttributes) {
        personaService.eliminarpersona(idPersonas);
        redirectAttributes.addFlashAttribute("mensaje", "Persona eliminada con éxito");
        return "redirect:/personas";

    }


    /*
     * @GetMapping("/eliminarpersona")
     * public String eliminarPersona(@PathVariable("idPersona") Long idPersona,
     * RedirectAttributes redirectAttributes) {
     * try {
     * personaService.eliminarPersona(idPersona);
     * redirectAttributes.addFlashAttribute("mensaje",
     * "Persona eliminada con éxito");
     * } catch (Exception e) {
     * redirectAttributes.addFlashAttribute("error",
     * "No se pudo eliminar la persona");
     * }
     * return "redirect:/personas";
     * }
     */

    // Eliminar Persona

}
