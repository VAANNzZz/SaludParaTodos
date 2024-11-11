package com.exe.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exe.app.entity.Cita;
import com.exe.app.repository.CitaRepository;



@Service("citaService")
public class CitaService {

    @Autowired 
    CitaRepository citaRepository;

    public List<Cita>getCita(){
        return citaRepository.findAll();
    }

    public void saveOrUpdate(Cita cita){
        citaRepository.save(cita);
    }

        public Optional<Cita> getCitaById(Long idCitas){
        return citaRepository.findById(idCitas);
    }
    
    public void eliminarcita(Long idCitas) {
        if (citaRepository.existsById(idCitas)) {
            citaRepository.deleteById(idCitas);
        }
    }

}
