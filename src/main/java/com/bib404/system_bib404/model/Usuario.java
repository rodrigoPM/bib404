package com.bib404.system_bib404.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;

import com.bib404.system_bib404.service.impl.EncriptadoImp;


@Entity
@Table(name="USUARIO", schema="BIB404")
public class Usuario implements Serializable{
	private static final long serialVersionUID = -1280037900360314186L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="id_secuencia")
	@SequenceGenerator(name="id_secuencia",allocationSize=1)
	@Column(name="ID", unique=true, nullable=false)
	private int id;
	
	@NotNull
	@Size(min=4, max=60)
	@Column(name="USERNAME",unique=true ,nullable=false)
	private String username;
	
	@NotNull
	@Size(min=3, max=60)
	@Column(name="PASSWORD", nullable=false)
	private String password;

	@NotNull
	@Column(name="NOMBRE", nullable=false)
	private String nombre;

	@NotNull
	@Column(name="APELLIDO", nullable=false)
	private String apellido;

	@NotNull
	@Column(name="EMAIL", nullable=false)
	private String email;

	@NotNull
	@Column(name="FECHANACIMIENTO", nullable=false)
	private String fechaNacimiento;
	
	@NotNull
	@Column(name="ENABLE", nullable=false)
	private boolean enable;
	
	@NotNull
	@Column(name="ROL", nullable=false)
	private String rol;
	
	

	public Usuario(String username, String password, String nombre, String apellido, String email,
			String fechaNacimiento, boolean enable) {
		super();
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.enable= enable;
	}
	
	public Usuario(String username, String password, String nombre, String apellido, String email,
			String fechaNacimiento) {
		super();
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.enable= true;
	}

	public Usuario(String username, String password, String nombre, String apellido, String email,
			String fechaNacimiento, boolean enable, String rol) {
		super();
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.enable= enable;
		this.rol=rol;
	}

	public Usuario(String username, String password, String nombre, String apellido, String email,
			String fechaNacimiento, String rol) {
		super();
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.enable= true;
		this.rol=rol;
	}
	
	public Usuario(){
		this.enable=true;
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

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable= enable;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	
	
}

