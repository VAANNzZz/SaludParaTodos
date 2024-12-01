package com.exe.app.entity;

import java.time.LocalDate; // Cambiado a LocalDateTime

import com.exe.app.entity.Cita.Tipocita;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "citas")
public class Cita {

    public enum Estado {
        PENDIENTE,
        AVANZANDO,
        COMPLETADA
    }

    public enum Tipocita {
        VIRTUAL,
        PRESENCIAL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCitas", insertable = false, updatable = false)
    private long idCitas;

    @Column(name = "fecha", nullable = false) // Añadido nullable = false
    private LocalDate fecha; // Cambiado a LocalDateTime

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado = Estado.PENDIENTE;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoCita", nullable = false)
    private Tipocita tipoCita = Tipocita.PRESENCIAL;

    @ManyToOne
    @JoinColumn(name = "idPersonas")
    private Persona persona;

    public Cita() {
    }
   

    public Cita(long idCitas, LocalDate fecha, Estado estado, Tipocita tipoCita, Persona persona) {
        this.idCitas = idCitas;
        this.fecha = fecha;
        this.estado = estado;
        this.tipoCita = tipoCita;
        this.persona = persona;
    }


    public long getIdCitas() {
        return this.idCitas;
    }

    public void setIdCitas(long idCitas) {
        this.idCitas = idCitas;
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Estado getEstado() {
        return this.estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Tipocita getTipoCita() {
        return this.tipoCita;
    }

    public void setTipoCita(Tipocita tipoCita) {
        this.tipoCita = tipoCita;
    }

    public Persona getPersona() {
        return this.persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    
}
