package com.bib404.system_bib404.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.bib404.system_bib404.entity.RecursoEspecifico;

public class FormatoRecursoModel {
	private int id;

	private String nombre_formato;

	private RecursoEspecifico recurso_especifico;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre_formato() {
		return nombre_formato;
	}

	public void setNombre_formato(String nombre_formato) {
		this.nombre_formato = nombre_formato;
	}

	public RecursoEspecifico getRecurso_especifico() {
		return recurso_especifico;
	}

	public void setRecurso_especifico(RecursoEspecifico recurso_especifico) {
		this.recurso_especifico = recurso_especifico;
	}

	public FormatoRecursoModel(int id, String nombre_formato) {
		super();
		this.id = id;
		this.nombre_formato = nombre_formato;
	}

	public FormatoRecursoModel() {
		super();
	}

}

