package com.bib404.system_bib404.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;



public class Usuario {
	@NotNull
	@Size(min=4, max=60)
	private String username;
	
	@NotNull
	@Size(min=3, max=60)
	private String password;

	@NotNull
	private String nombre;

	@NotNull
	private String apellido;

	@NotNull
	private String email;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date fechaNacimiento;

	private boolean esSuperuser;

	public Usuario(String username, String password, String nombre, String apellido, String email,
			Date fechaNacimiento, boolean esSuperuser) {
		super();
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.esSuperuser = esSuperuser;
	}
	public Usuario(){
		this.esSuperuser=false;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public boolean isEsSuperuser() {
		return esSuperuser;
	}

	public void setEsSuperuser(boolean esSuperuser) {
		this.esSuperuser = esSuperuser;
	}
	
	
	
}

