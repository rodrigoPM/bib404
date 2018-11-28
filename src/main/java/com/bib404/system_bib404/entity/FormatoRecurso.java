package com.bib404.system_bib404.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "formato_recurso")
public class FormatoRecurso {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "nombre_formato")
	private String nombre_formato;

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

	public FormatoRecurso(int id, String nombre_formato) {
		super();
		this.id = id;
		this.nombre_formato = nombre_formato;
	}

	public FormatoRecurso() {
		super();
	}

}
