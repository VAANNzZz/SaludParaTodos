package com.exe.app.services;

import com.exe.app.entity.Rol;

public class PersonaDTO {
	private Long idPersonas;
	private String primer_nombre;
	private String segundo_nombre;
	private String primer_apellido;
	private String segundo_apellido;
	private enum TipoDocumento {
		CedulaCiudadania, TarjetaIdentidad, CedulaExtranjero, Pasaporte}; 
	private String tipoDocumento;
	private String numeroDocumento;
	private String numeroContacto;
	private String email;
	private Rol rol;
	private String contraseña;
	
	public PersonaDTO() {
	}
	

	public PersonaDTO(Long idPersonas, String primer_nombre, String segundo_nombre, String primer_apellido, String segundo_apellido, String tipoDocumento, String numeroDocumento, String numeroContacto, String email, Rol rol, String contraseña) {
		this.idPersonas = idPersonas;
		this.primer_nombre = primer_nombre;
		this.segundo_nombre = segundo_nombre;
		this.primer_apellido = primer_apellido;
		this.segundo_apellido = segundo_apellido;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.numeroContacto = numeroContacto;
		this.email = email;
		this.rol = rol;
		this.contraseña = contraseña;
	}


	public Long getIdPersonas() {
		return this.idPersonas;
	}

	public void setIdPersonas(Long idPersonas) {
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

	public String getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
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

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getContraseña() {
		return this.contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	
}
