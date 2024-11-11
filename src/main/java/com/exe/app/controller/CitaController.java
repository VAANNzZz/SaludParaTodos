package com.exe.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.exe.app.entity.Cita;
import com.exe.app.services.CitaService;



@RestController
@RequestMapping("/home/Citas")
public class CitaController {

    @Autowired 
    CitaService citaService;

    @GetMapping
    public List<Cita> getAll(){
        return citaService.getCita();
    }

    @PostMapping
     public void guardarcita(@RequestBody Cita cita){
     citaService.saveOrUpdate(cita);

     }
     
     @GetMapping("/eliminarcita/{idCitas}")
    public String eliminarcita(@PathVariable("idCitas") Long idCitas, RedirectAttributes redirectAttributes) {
    citaService.eliminarcita(idCitas);
    redirectAttributes.addFlashAttribute("mensaje", "Cita eliminada con éxito");
    return "redirect:/citas";
    }

}