package com.bib404.system_bib404.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "departamento")
public class Departamento {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "nombre_departamento")
	private String nombre_departamento;

	@Column(name = "zona_geografica")
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

	public Departamento() {
	}

	public Departamento(int id, String nombre_departamento, String zona_geografica) {
		super();
		this.id = id;
		this.nombre_departamento = nombre_departamento;
		this.zona_geografica = zona_geografica;
	}
}
