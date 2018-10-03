package com.bib404.system_bib404.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="usuario")
public class Usuario {
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@NotNull
	@Size(min=4, max=60)
	@Column(name="username")
	private String username;
	
	@NotNull
	@Size(min=3, max=60)
	@Column(name="password")
	private String password;

	@NotNull
	@Column(name="nombre")
	private String nombre;

	@NotNull
	@Column(name="apellido")
	private String apellido;

	@NotNull
	@Column(name="email")
	private String email;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	@Column(name="fechanacimiento")
	private Date fechaNacimiento;

	@Column(name="essuperuser")
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

