package com.bib404.system_bib404.model;

public class DepartamentoModel {
	private int id;
	private String nombre_departamento;
	private String zona_geografica;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre_departamento() {
		return nombre_departamento;
	}

	public void setNombre_departamento(String nombre_departamento) {
		this.nombre_departamento = nombre_departamento;
	}

	public String getZona_geografica() {
		return zona_geografica;
	}

	public void setZona_geografica(String zona_geografica) {
		this.zona_geografica = zona_geografica;
	}

	public DepartamentoModel(int id, String nombre_departamento, String zona_geografica) {
		super();
		this.id = id;
		this.nombre_departamento = nombre_departamento;
		this.zona_geografica = zona_geografica;
	}

	public DepartamentoModel() {
		super();
	}

}
