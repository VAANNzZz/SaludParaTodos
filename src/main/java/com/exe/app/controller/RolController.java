package com.exe.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exe.app.entity.Rol;
import com.exe.app.services.RolService;

@RestController
@RequestMapping("/home/Roles")
public class RolController {

    @Autowired 
    RolService rolService;

    @GetMapping
    public List<Rol> getAll(){
        return rolService.getRol();
    }
}

