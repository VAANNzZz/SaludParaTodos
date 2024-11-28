package com.exe.app.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tareas")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarea;

    private String descripcion;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "idPersonaDestinatario") // Relacion con el aprendiz (destinatario)
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "idPersonaAsignador") // Relacion con el orientador (asignador)
    private Persona asignador;

    private LocalDate fechaEntrega;
    private String material;


    public Tarea() {
    }

    public Tarea(Long idTarea, String descripcion, String estado, Persona persona, Persona asignador, LocalDate fechaEntrega, String material) {
        this.idTarea = idTarea;
        this.descripcion = descripcion;
        this.estado = estado;
        this.persona = persona;
        this.asignador = asignador;
        this.fechaEntrega = fechaEntrega;
        this.material = material;
    }
    

    public Long getIdTarea() {
        return this.idTarea;
    }

    public void setIdTarea(Long idTarea) {
        this.idTarea = idTarea;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Persona getPersona() {
        return this.persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Persona getAsignador() {
        return this.asignador;
    }

    public void setAsignador(Persona asignador) {
        this.asignador = asignador;
    }

    public LocalDate getFechaEntrega() {
        return this.fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getMaterial() {
        return this.material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }


}