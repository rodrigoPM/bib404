package com.bib404.system_bib404.model;

import java.util.Set;

import com.bib404.system_bib404.entity.RecursoBibliotecario;

public class CategoriaModel {

	private int id;
	private String nombre_categoria;
	private String descripcion_categoria;
	private Set<RecursoBibliotecario> recurso_bib;
	private CategoriaModel categoria;
	private int categoria_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getCategoria_id() {
		return categoria_id;
	}

	public void setCategoria_id(int categoria_id) {
		this.categoria_id = categoria_id;
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

	public String getDescripcion_categoria() {
		return descripcion_categoria;
	}

	public void setDescripcion_categoria(String descripcion_categoria) {
		this.descripcion_categoria = descripcion_categoria;
	}

	public CategoriaModel(int id, String nombre_categoria, String descripcion_categoria,
			Set<RecursoBibliotecario> recurso_bib, CategoriaModel categoria, int categoria_id) {
		super();
		this.id = id;
		this.nombre_categoria = nombre_categoria;
		this.descripcion_categoria = descripcion_categoria;
		this.recurso_bib = recurso_bib;
		this.categoria = categoria;
		this.categoria_id=categoria_id;
	}

	public CategoriaModel() {
		super();
		this.categoria=null;
	}

	@Override
	public String toString() {
		return "CategoriaModel [id=" + id + ", nombre_categoria=" + nombre_categoria + ", descripcion_categoria="
				+ descripcion_categoria + ", recurso_bib=" + recurso_bib + ", categoria=" + categoria + "]";
	}
	
	

}
