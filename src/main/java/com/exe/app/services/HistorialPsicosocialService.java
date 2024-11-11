package com.exe.app.services;

import java.util.List; // Importa de java.util
import java.util.Optional; // Importa de java.util

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exe.app.entity.HistorialPsicosocial;
import com.exe.app.repository.HistorialPsicosocialRepository;



@Service("historialPsicosocialService")
public class HistorialPsicosocialService {

    @Autowired
    HistorialPsicosocialRepository historialPsicosocialRepository;

    public List<HistorialPsicosocial> gethistorialPsicosocial(){
        return historialPsicosocialRepository.findAll();
    }

    public void saveOrUpdate(HistorialPsicosocial historialPsicosocial){
        historialPsicosocialRepository.save(historialPsicosocial);
    }

        public Optional<HistorialPsicosocial> getHistorialPsicosocialById(Long idHistorial_Psicosocial){
        return historialPsicosocialRepository.findById(idHistorial_Psicosocial);
    }
    
    public void eliminarhistorialpsicosocial(Long idHistorial_Psicosocial) {
        if (historialPsicosocialRepository.existsById(idHistorial_Psicosocial)) {
            historialPsicosocialRepository.deleteById(idHistorial_Psicosocial);
        }
    }

}
