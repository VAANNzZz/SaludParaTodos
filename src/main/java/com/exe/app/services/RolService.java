package com.exe.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exe.app.entity.Rol;
import com.exe.app.repository.RolRepository;

@Service("rolService")
public class RolService {

    @Autowired 
    RolRepository rolRepository;

    public List<Rol>getRol(){
        return rolRepository.findAll();
    }

    public void saveOrUpdate(Rol rol) {
        rolRepository.save(rol);
    }

    public void eliminarRol(Long idRol) {
        if (rolRepository.existsById(idRol)) {
            rolRepository.deleteById(idRol);
        }
    }

    public List<Rol> getAllRoles() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllRoles'");
    }

    public Rol obtenerRolPorId(Long idRol) {
        Optional<Rol> rol = rolRepository.findById(idRol);
        return rol.orElseThrow(() -> new IllegalArgumentException("Rol no encontrado con id: " + idRol));
    }

}

