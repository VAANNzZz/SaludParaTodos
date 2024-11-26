package com.exe.app.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


    @Data
    @Entity
    @Table(name = "historial_psicosocial")
public class HistorialPsicosocial {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idHistorial_Psicosocial", insertable = false, updatable = false)
    private long idHistorial_Psicosocial;

    @Column(name = "historial_psicosocial", length = 500, nullable = false)
    private String historial_psicosocial;

    @Column(name = "ultima_actualizacion")
    private LocalDate ultima_actualizacion;

    public HistorialPsicosocial() {
    }


    public HistorialPsicosocial(long idHistorial_Psicosocial, String historial_psicosocial, LocalDate ultima_actualizacion) {
        this.idHistorial_Psicosocial = idHistorial_Psicosocial;
        this.historial_psicosocial = historial_psicosocial;
        this.ultima_actualizacion = ultima_actualizacion;
    }


    public long getIdHistorial_Psicosocial() {
        return this.idHistorial_Psicosocial;
    }

    public void setIdHistorial_Psicosocial(long idHistorial_Psicosocial) {
        this.idHistorial_Psicosocial = idHistorial_Psicosocial;
    }

    public String getHistorial_psicosocial() {
        return this.historial_psicosocial;
    }

    public void setHistorial_psicosocial(String historial_psicosocial) {
        this.historial_psicosocial = historial_psicosocial;
    }

    public LocalDate getUltima_actualizacion() {
        return this.ultima_actualizacion;
    }

    public void setUltima_actualizacion(LocalDate ultima_actualizacion) {
        this.ultima_actualizacion = ultima_actualizacion;
    }



    public void saveOrUpdate(HistorialPsicosocial historialPsicosocial) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveOrUpdate'");
    }

    
}
