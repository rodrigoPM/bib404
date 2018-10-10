package com.bib404.system_bib404.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_recurso")
public class TipoRecurso {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "nombre_tipo_recurso")
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

	public TipoRecurso(int id, String nombre_tipo_recurso) {
		super();
		this.id = id;
		this.nombre_tipo_recurso = nombre_tipo_recurso;
	}

	public TipoRecurso() {
		super();
	}

}
