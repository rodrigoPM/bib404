package com.bib404.system_bib404.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "municipio")
public class Municipio {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "nombre_municipio")
	private String nombre_municipio;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "departamento_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Departamento departamento;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre_municipio() {
		return nombre_municipio;
	}

	public void setNombre_municipio(String nombre_municipio) {
		this.nombre_municipio = nombre_municipio;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Municipio(int id, String nombre_municipio, Departamento departamento) {
		super();
		this.id = id;
		this.nombre_municipio = nombre_municipio;
		this.departamento = departamento;
	}

	public Municipio() {
	}

}
