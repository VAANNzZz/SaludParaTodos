package com.exe.app.entity;

import java.time.LocalDate; // Cambiado a LocalDateTime

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
        COMPLETADA,
        CANCELADA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCitas", insertable = false, updatable = false)
    private long idCitas;

    @Column(name = "fecha", nullable = false) // Añadido nullable = false
    private LocalDate fecha; // Cambiado a LocalDateTime

    /*@Column(name = "descripcion_cita", length = 255, nullable = false)
    private String descripcion_cita;*/

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado = Estado.PENDIENTE;

    @ManyToOne
    @JoinColumn(name = "idHistorial_Psicosocial")
    private HistorialPsicosocial historialPsicosocial;

    @ManyToOne
    @JoinColumn(name = "idPersonas")
    private Persona persona;

    public Cita() {
    }
   

    public Cita(long idCitas, LocalDate fecha, Estado estado, HistorialPsicosocial historialPsicosocial, Persona persona) {
        this.idCitas = idCitas;
        this.fecha = fecha;
        this.estado = estado;
        this.historialPsicosocial = historialPsicosocial;
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

    public HistorialPsicosocial getHistorialPsicosocial() {
        return this.historialPsicosocial;
    }

    public void setHistorialPsicosocial(HistorialPsicosocial historialPsicosocial) {
        this.historialPsicosocial = historialPsicosocial;
    }

    public Persona getPersona() {
        return this.persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    
}
