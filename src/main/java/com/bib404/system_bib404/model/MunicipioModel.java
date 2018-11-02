package com.bib404.system_bib404.model;


public class MunicipioModel {
	
	private int id;
	private String nombre_municipio;
	private DepartamentoModel departamento;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre_municipio() {
		return nombre_municipio;
	}
	public void setNombre_municipio(String nombre_municipio) {
		this.nombre_municipio = nombre_municipio;
	}
	public DepartamentoModel getDepartamento() {
		return departamento;
	}
	public void setDepartamento(DepartamentoModel departamento) {
		this.departamento = departamento;
	}
	public MunicipioModel(int id, String nombre_municipio, DepartamentoModel departamento) {
		super();
		this.id = id;
		this.nombre_municipio = nombre_municipio;
		this.departamento = departamento;
	}
	public MunicipioModel() {
		super();
	}
	
	
	
}
