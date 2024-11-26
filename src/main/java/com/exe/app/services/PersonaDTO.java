package com.exe.app.services;

public class PersonaDTO {
	private Long id;
	private String password;
	
	public PersonaDTO() {
	}
	
	public PersonaDTO(Long id, String password) {
		this.id = id;
		this.password = password;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
