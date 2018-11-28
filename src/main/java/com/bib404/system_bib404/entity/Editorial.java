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

public class Editorial {

	private int id;


	private String nombre_editorial;


	private Date anio_publicacion;


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
