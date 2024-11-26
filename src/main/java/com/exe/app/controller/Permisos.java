package com.exe.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class Permisos {

    @GetMapping("/permisos")
    public String permisos() {
        return "permisos";
    }

}

