package com.bib404.system_bib404.model;

import java.util.Set;

import com.bib404.system_bib404.entity.RecursoBibliotecario;

public class CategoriaModel {

	private int id;
	private String nombre_categoria;
	private Set<RecursoBibliotecario> recurso_bib;
	private CategoriaModel categoria;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre_categoria() {
		return nombre_categoria;
	}

	public void setNombre_categoria(String nombre_categoria) {
		this.nombre_categoria = nombre_categoria;
	}

	public Set<RecursoBibliotecario> getRecurso_bib() {
		return recurso_bib;
	}

	public void setRecurso_bib(Set<RecursoBibliotecario> recurso_bib) {
		this.recurso_bib = recurso_bib;
	}

	public CategoriaModel getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaModel categoria) {
		this.categoria = categoria;
	}

	public CategoriaModel(int id, String nombre_categoria, Set<RecursoBibliotecario> recurso_bib,
			CategoriaModel categoria) {
		super();
		this.id = id;
		this.nombre_categoria = nombre_categoria;
		this.recurso_bib = recurso_bib;
		this.categoria = categoria;
	}

	public CategoriaModel() {
		super();
	}
	
}