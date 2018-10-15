package com.bib404.system_bib404.entity;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "editorial")
public class Editorial {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "nombre_editorial")
	private String nombre_editorial;

	@Temporal(TemporalType.DATE)
	@Column(name = "anio_publicacion")
	private Date anio_publicacion;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "editorial")
	private RecursoEspecifico recurso_especifico;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre_editorial() {
		return nombre_editorial;
	}

	public void setNombre_editorial(String nombre_editorial) {
		this.nombre_editorial = nombre_editorial;
	}

	public Date getAnio_publicacion() {
		return anio_publicacion;
	}

	public void setAnio_publicacion(Date anio_publicacion) {
		this.anio_publicacion = anio_publicacion;
	}

	public RecursoEspecifico getRecurso_especifico() {
		return recurso_especifico;
	}

	public void setRecurso_especifico(RecursoEspecifico recurso_especifico) {
		this.recurso_especifico = recurso_especifico;
	}

	public Editorial(int id, String nombre_editorial, Date anio_publicacion) {
		super();
		this.id = id;
		this.nombre_editorial = nombre_editorial;
		this.anio_publicacion = anio_publicacion;
	}

	public Editorial() {
		super();
	}

}
