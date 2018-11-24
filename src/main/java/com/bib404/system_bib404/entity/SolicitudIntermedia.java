package com.bib404.system_bib404.entity;

public class SolicitudIntermedia {
	private String nombre_biblioteca;
	private int id_municipio;
	private String usuario;
	private String correo;
	private String contra;
	private String codigo_biblioteca;
	
	
	
	public SolicitudIntermedia() {
		super();
	}
	public SolicitudIntermedia(String nombre_biblioteca, int id_municipio, String usuario, String correo, String contra,
			String codigo_biblioteca) {
		super();
		this.nombre_biblioteca = nombre_biblioteca;
		this.id_municipio = id_municipio;
		this.usuario = usuario;
		this.correo = correo;
		this.contra = contra;
		this.codigo_biblioteca = codigo_biblioteca;
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
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getContra() {
		return contra;
	}
	public void setContra(String contra) {
		this.contra = contra;
	}
	public String getCodigo_biblioteca() {
		return codigo_biblioteca;
	}
	public void setCodigo_biblioteca(String codigo_biblioteca) {
		this.codigo_biblioteca = codigo_biblioteca;
	}
	

}
