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

import com.exe.app.entity.HistorialPsicosocial;
import com.exe.app.services.HistorialPsicosocialService;

@RestController
@RequestMapping("/home/historial_psicosocial")
public class HistorialPsicosocialController {

    @Autowired 
    HistorialPsicosocialService historialPsicosocialService;

    @GetMapping
    public List<HistorialPsicosocial> getALL() {
        return historialPsicosocialService.gethistorialPsicosocial();
    }

    @PostMapping
    public void guardarhistorialpsicosocial(@RequestBody HistorialPsicosocial historialPsicosocial) {
    historialPsicosocialService.saveOrUpdate(historialPsicosocial);

     }
     
     @GetMapping("/eliminarhistorialpsicosocial/{idHistorial_Psicosocial}")
    public String eliminarhistorialpsicosocial(@PathVariable("idHistorial_Psicosocial") Long idHistorial_Psicosocial, RedirectAttributes redirectAttributes) {
    historialPsicosocialService.eliminarhistorialpsicosocial(idHistorial_Psicosocial);
    redirectAttributes.addFlashAttribute("mensaje", "historial psicosocial eliminada con éxito");
    return "redirect:/historial_psicosocial";
    }


}
