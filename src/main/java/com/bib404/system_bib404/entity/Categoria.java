package com.bib404.system_bib404.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "categoria")
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "nombre_categoria")
	private String nombre_categoria;

	@Column(name = "descripcion_categoria")
	private String descripcion_categoria;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "categoria")
	private Set<RecursoBibliotecario> recurso_bib = new HashSet<>();

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "categoria_id", nullable = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Categoria categoria;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "biblioteca_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Biblioteca biblioteca;

//atributos auxiliares
	private int idCatForanea;

public int getIdCatForanea(){
	return idCatForanea;
}

public void setIdCategoriaForanea(int idCat){
	this.idCatForanea=idCat;
}
//fin atributos auxiliares

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

	public Categoria(int id, String nombre_categoria, Set<RecursoBibliotecario> recurso_bib) {
		super();
		this.id = id;
		this.nombre_categoria = nombre_categoria;
		this.recurso_bib = recurso_bib;
	}

	public Set<RecursoBibliotecario> getRecurso_bib() {
		return recurso_bib;
	}

	public void setRecurso_bib(Set<RecursoBibliotecario> recurso_bib) {
		this.recurso_bib = recurso_bib;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Biblioteca getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}

	public String getDescripcion_categoria() {
		return descripcion_categoria;
	}

	public void setDescripcion_categoria(String descripcion_categoria) {
		this.descripcion_categoria = descripcion_categoria;
	}

	public Categoria(int id, String nombre_categoria, String descripcion_categoria,
			Set<RecursoBibliotecario> recurso_bib, Categoria categoria, Biblioteca biblioteca) {
		super();
		this.id = id;
		this.nombre_categoria = nombre_categoria;
		this.descripcion_categoria = descripcion_categoria;
		this.recurso_bib = recurso_bib;
		this.categoria = categoria;
		this.biblioteca=biblioteca;
	}
	public Categoria(int id, String nombre_categoria, String descripcion_categoria,
			Set<RecursoBibliotecario> recurso_bib, Categoria categoria, Biblioteca biblioteca,int idCatForanea) {
		super();
		this.id = id;
		this.nombre_categoria = nombre_categoria;
		this.descripcion_categoria = descripcion_categoria;
		this.recurso_bib = recurso_bib;
		this.categoria = categoria;
		this.biblioteca=biblioteca;
		this.idCatForanea=idCatForanea;
	}

	public Categoria() {
		super();
	}

}
