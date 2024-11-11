package com.exe.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idRol", insertable = false, updatable = false)
    private long idRol;

    @Column(name = "nombre_rol", nullable = false)
    private String nombre_rol;

    
    public Rol() {
    }


    public Rol(long idRol, String nombre_rol) {
        this.idRol = idRol;
        this.nombre_rol = nombre_rol;
    }


    public long getIdRol() {
        return this.idRol;
    }

    public void setIdRol(long idRol) {
        this.idRol = idRol;
    }

    public String getNombre_rol() {
        return this.nombre_rol;
    }

    public void setNombre_rol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }

    
    
}
