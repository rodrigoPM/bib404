package com.bib404.system_bib404.model;

import java.util.Set;

import com.bib404.system_bib404.entity.Municipio;
import com.bib404.system_bib404.entity.RecursoBibliotecario;

public class BibliotecaModel {

	private int id;
	private String nombre_biblioteca;
	private String codigo_biblioteca;
	private MunicipioModel municipio;
	private Set<RecursoBibliotecario> recurso_bib;

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

	public String getCodigo_biblioteca() {
		return codigo_biblioteca;
	}

	public void setCodigo_biblioteca(String codigo_biblioteca) {
		this.codigo_biblioteca = codigo_biblioteca;
	}

	public MunicipioModel getMunicipio() {
		return municipio;
	}

	public void setMunicipio(MunicipioModel municipio) {
		this.municipio = municipio;
	}

	

	public Set<RecursoBibliotecario> getRecurso_bib() {
		return recurso_bib;
	}

	public void setRecurso_bib(Set<RecursoBibliotecario> recurso_bib) {
		this.recurso_bib = recurso_bib;
	}



	public BibliotecaModel(int id, String nombre_biblioteca, String codigo_biblioteca, MunicipioModel municipio,
			Set<RecursoBibliotecario> recurso_bib) {
		super();
		this.id = id;
		this.nombre_biblioteca = nombre_biblioteca;
		this.codigo_biblioteca = codigo_biblioteca;
		this.municipio = municipio;
		this.recurso_bib = recurso_bib;
	}

	public BibliotecaModel() {
		super();
	}

}
