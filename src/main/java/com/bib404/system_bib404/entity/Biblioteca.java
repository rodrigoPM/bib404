package com.bib404.system_bib404.entity;

import java.util.Date;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "biblioteca")
public class Biblioteca {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "nombre_biblioteca")
	private String nombre_biblioteca;

	@Column(name = "codigo_biblioteca")
	@Size(min = 5, max = 30)
	private String codigo_biblioteca;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "municipio_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Municipio municipio;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_registro")
	private Date fecha_registro;


	public int getId() {
		return id;
	}
	
	

	public Date getFecha_registro() {
		return fecha_registro;
	}



	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}



	public void setId(int id) {
		this.id = id;
	}

	public String getNombre_biblioteca() {
		return nombre_biblioteca;
	}

	public void setNombre_biblioteca(String nombre_biblioteca) {
		this.nombre_biblioteca = nombre_biblioteca;
	}

	public String getCodigo_biblioteca() {
		return codigo_biblioteca;
	}

	public void setCodigo_biblioteca(String codigo_biblioteca) {
		this.codigo_biblioteca = codigo_biblioteca;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Biblioteca(int id, String nombre_biblioteca, @Size(min = 5, max = 30) String codigo_biblioteca,
			Municipio municipio) {
		super();
		this.id = id;
		this.nombre_biblioteca = nombre_biblioteca;
		this.codigo_biblioteca = codigo_biblioteca;
		this.municipio = municipio;

		this.fecha_registro=new Date();
	}

	public Biblioteca() {
		super();
	}

	@Override
	public String toString() {
		return "Biblioteca [id=" + id + ", nombre_biblioteca=" + nombre_biblioteca + ", codigo_biblioteca="
				+ codigo_biblioteca + ", municipio=" + municipio + "]";
	}
	
}
