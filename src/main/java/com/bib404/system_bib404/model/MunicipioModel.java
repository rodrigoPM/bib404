package com.bib404.system_bib404.model;

import com.bib404.system_bib404.entity.Departamento;

public class MunicipioModel {
	
	private int id;
	private String nombre_municipio;
	private Departamento departamento;
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
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	public MunicipioModel(int id, String nombre_municipio, Departamento departamento) {
		super();
		this.id = id;
		this.nombre_municipio = nombre_municipio;
		this.departamento = departamento;
	}
	public MunicipioModel() {
		super();
	}
	
	
}
