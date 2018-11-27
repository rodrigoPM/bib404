package com.bib404.system_bib404.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "solicitud")
public class Solicitud {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator="SEQUENCE1")
	@SequenceGenerator(name="SEQUENCE1", sequenceName="SEQUENCE1", allocationSize=1)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@Column(name = "nombre_biblioteca")
	private String nombre_biblioteca;
	
	@Column(name="id_municipio")
	private int id_municipio; //campo no debe estar relacionado con municipio porque igual esta tabla es solo temporal, no vale la pena

	@Column(name="correo")
	private String correo;
	
	@Column(name="usuario")
	private String usuario;
	
	@Column(name="enable")
	private boolean enable;
	
	

	public Solicitud() {
		super();
		this.enable=false;
	}

	public Solicitud(int id, String nombre_biblioteca, int id_municipio, String correo) {
		super();
		this.id = id;
		this.nombre_biblioteca = nombre_biblioteca;
		this.id_municipio = id_municipio;
		this.correo = correo;
		this.enable=false;
	}
	
	

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre_biblioteca() {
		return nombre_biblioteca;
	}

	public void setNombre_biblioteca(String nombre_biblioteca) {
		this.nombre_biblioteca = nombre_biblioteca;
	}


	public int getId_municipio() {
		return id_municipio;
	}

	public void setId_municipio(int id_municipio) {
		this.id_municipio = id_municipio;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	
	
	
	
	
	
}
