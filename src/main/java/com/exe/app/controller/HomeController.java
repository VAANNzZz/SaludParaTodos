package com.exe.app.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/home")
    public String home(){
        return "Private home";
    }

    @GetMapping("/administrador")
    @PreAuthorize("hasAuthority('administrador')")
    public String administrador() {
        return "Administrador";
    }

}

