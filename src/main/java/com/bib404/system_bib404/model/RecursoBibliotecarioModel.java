package com.bib404.system_bib404.model;

import java.util.Set;

import com.bib404.system_bib404.entity.Biblioteca;
import com.bib404.system_bib404.entity.Categoria;
import com.bib404.system_bib404.entity.TipoRecurso;

public class RecursoBibliotecarioModel {
	private int id;
	private String nombre_recurso_bib;
	private String descripcion_recurso_bib;
	private String sinopsis_recurso_bib;
	private String imagen_recurso_bibl;
	private String digital_recurso_bib;
	private String fisico_recurso_bib;
	private String total_recurso_bib;
	private Set<Biblioteca> biblioteca;
	private TipoRecurso tipo_recurso;
	private Set<Categoria> categoria;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre_recurso_bib() {
		return nombre_recurso_bib;
	}

	public void setNombre_recurso_bib(String nombre_recurso_bib) {
		this.nombre_recurso_bib = nombre_recurso_bib;
	}

	public String getDescripcion_recurso_bib() {
		return descripcion_recurso_bib;
	}

	public void setDescripcion_recurso_bib(String descripcion_recurso_bib) {
		this.descripcion_recurso_bib = descripcion_recurso_bib;
	}

	public String getSinopsis_recurso_bib() {
		return sinopsis_recurso_bib;
	}

	public void setSinopsis_recurso_bib(String sinopsis_recurso_bib) {
		this.sinopsis_recurso_bib = sinopsis_recurso_bib;
	}

	public String getImagen_recurso_bibl() {
		return imagen_recurso_bibl;
	}

	public void setImagen_recurso_bibl(String imagen_recurso_bibl) {
		this.imagen_recurso_bibl = imagen_recurso_bibl;
	}

	public String getDigital_recurso_bib() {
		return digital_recurso_bib;
	}

	public void setDigital_recurso_bib(String digital_recurso_bib) {
		this.digital_recurso_bib = digital_recurso_bib;
	}

	public String getFisico_recurso_bib() {
		return fisico_recurso_bib;
	}

	public void setFisico_recurso_bib(String fisico_recurso_bib) {
		this.fisico_recurso_bib = fisico_recurso_bib;
	}

	public String getTotal_recurso_bib() {
		return total_recurso_bib;
	}

	public void setTotal_recurso_bib(String total_recurso_bib) {
		this.total_recurso_bib = total_recurso_bib;
	}

	public Set<Biblioteca> getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(Set<Biblioteca> biblioteca) {
		this.biblioteca = biblioteca;
	}

	public TipoRecurso getTipo_recurso() {
		return tipo_recurso;
	}

	public void setTipo_recurso(TipoRecurso tipo_recurso) {
		this.tipo_recurso = tipo_recurso;
	}

	public Set<Categoria> getCategoria() {
		return categoria;
	}

	public void setCategoria(Set<Categoria> categoria) {
		this.categoria = categoria;
	}

	public RecursoBibliotecarioModel(int id, String nombre_recurso_bib, String descripcion_recurso_bib,
			String sinopsis_recurso_bib, String imagen_recurso_bibl, String digital_recurso_bib,
			String fisico_recurso_bib, String total_recurso_bib, Set<Biblioteca> biblioteca, TipoRecurso tipo_recurso,
			Set<Categoria> categoria) {
		super();
		this.id = id;
		this.nombre_recurso_bib = nombre_recurso_bib;
		this.descripcion_recurso_bib = descripcion_recurso_bib;
		this.sinopsis_recurso_bib = sinopsis_recurso_bib;
		this.imagen_recurso_bibl = imagen_recurso_bibl;
		this.digital_recurso_bib = digital_recurso_bib;
		this.fisico_recurso_bib = fisico_recurso_bib;
		this.total_recurso_bib = total_recurso_bib;
		this.biblioteca = biblioteca;
		this.tipo_recurso = tipo_recurso;
		this.categoria = categoria;
	}

	public RecursoBibliotecarioModel() {
		super();
	}

}
