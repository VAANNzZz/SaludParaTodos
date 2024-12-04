package com.exe.app.entity;

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
@Table(name = "personas")
public class Persona {

    public enum TipoDocumento {
        CedulaCiudadania,
        TarjetaIdentidad,
        CedulaExtranjeria,
        Pasaporte
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idPersonas", insertable = false, updatable = false)
    private long idPersonas;

    @Column(name = "primer_nombre", nullable = false)
    private String primer_nombre;

    @Column(name = "segundo_nombre")
    private String segundo_nombre;

    @Column(name = "primer_apellido", nullable = false)
    private String primer_apellido;

    @Column(name = "segundo_apellido")
    private String segundo_apellido;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoDocumento", nullable = false)
    private TipoDocumento tipoDocumento = TipoDocumento.CedulaCiudadania;

    @Column(name = "numeroDocumento",unique = true, nullable = false)
    private String numeroDocumento;

    @Column(name = "numeroContacto",unique = true, nullable = false)
    private String numeroContacto;

    @Column(name = "email", unique = true , nullable = false)
    private String email;

    @Column(name = "contraseña", nullable = false)
    private String contraseña;

    @ManyToOne
    @JoinColumn(name = "idRol")
    private Rol rol;

    public Persona() {
    }




    public Persona(long idPersonas, String primer_nombre, String segundo_nombre, String primer_apellido, String segundo_apellido, TipoDocumento tipoDocumento, String numeroDocumento, String numeroContacto, String email, String contraseña, Rol rol) {
        this.idPersonas = idPersonas;
        this.primer_nombre = primer_nombre;
        this.segundo_nombre = segundo_nombre;
        this.primer_apellido = primer_apellido;
        this.segundo_apellido = segundo_apellido;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.numeroContacto = numeroContacto;
        this.email = email;
        this.contraseña = contraseña;
        this.rol = rol;
    }


    public long getIdPersonas() {
        return this.idPersonas;
    }

    public void setIdPersonas(long idPersonas) {
        this.idPersonas = idPersonas;
    }

    public String getPrimer_nombre() {
        return this.primer_nombre;
    }

    public void setPrimer_nombre(String primer_nombre) {
        this.primer_nombre = primer_nombre;
    }

    public String getSegundo_nombre() {
        return this.segundo_nombre;
    }

    public void setSegundo_nombre(String segundo_nombre) {
        this.segundo_nombre = segundo_nombre;
    }

    public String getPrimer_apellido() {
        return this.primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public String getSegundo_apellido() {
        return this.segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }

    public TipoDocumento getTipoDocumento() {
        return this.tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return this.numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNumeroContacto() {
        return this.numeroContacto;
    }

    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return this.contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Rol getRol() {
        return this.rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    
    
}
