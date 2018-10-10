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
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "autor")
public class Autor {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "nombre_autor")
	private String nombre_autor;

	@Column(name = "apellido_autor")
	private String apellido_autor;

	@Column(name = "nacionalidad_autor")
	private String nacionalidad_autor;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "autor")
	private Set<RecursoEspecifico> recurso_especifico = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre_autor() {
		return nombre_autor;
	}

	public void setNombre_autor(String nombre_autor) {
		this.nombre_autor = nombre_autor;
	}

	public String getApellido_autor() {
		return apellido_autor;
	}

	public void setApellido_autor(String apellido_autor) {
		this.apellido_autor = apellido_autor;
	}

	public String getNacionalidad_autor() {
		return nacionalidad_autor;
	}

	public void setNacionalidad_autor(String nacionalidad_autor) {
		this.nacionalidad_autor = nacionalidad_autor;
	}

	public Set<RecursoEspecifico> getRecurso_especifico() {
		return recurso_especifico;
	}

	public void setRecurso_especifico(Set<RecursoEspecifico> recurso_especifico) {
		this.recurso_especifico = recurso_especifico;
	}

	public Autor(int id, String nombre_autor, String apellido_autor, String nacionalidad_autor,
			Set<RecursoEspecifico> recurso_especifico) {
		super();
		this.id = id;
		this.nombre_autor = nombre_autor;
		this.apellido_autor = apellido_autor;
		this.nacionalidad_autor = nacionalidad_autor;
		this.recurso_especifico = recurso_especifico;
	}

	public Autor() {
		super();
	}

}
