package com.bib404.system_bib404.model;

public class TipoRecursoModel {
	private int id;
	private String nombre_tipo_recurso;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre_tipo_recurso() {
		return nombre_tipo_recurso;
	}

	public void setNombre_tipo_recurso(String nombre_tipo_recurso) {
		this.nombre_tipo_recurso = nombre_tipo_recurso;
	}

	public TipoRecursoModel(int id, String nombre_tipo_recurso) {
		super();
		this.id = id;
		this.nombre_tipo_recurso = nombre_tipo_recurso;
	}

	public TipoRecursoModel() {
		super();
	}

}
