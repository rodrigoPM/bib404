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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "recurso_esp")
public class RecursoEspecifico {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "consulta_interna")
	private boolean consulta_interna;

	@Column(name = "volumen_recurso")
	private int volumen_recurso;

	@Column(name = "edicion_recurso")
	private int edicion_recurso;

	@Column(name = "prestado")
	private boolean prestado;

	@Column(name = "codigo_rec_esp")
	private String codigo_rec_esp;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "formato_recurso_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private FormatoRecurso formato_recurso;

	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "detalle_recurso_id", nullable = false)
	private DetalleRecurso detalle_recurso;

	//@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	//@JoinTable(name = "prestamo_recurso_esp", joinColumns = {
			//@JoinColumn(name = "prestamo_id") }, inverseJoinColumns = { @JoinColumn(name = "recurso_esp_id") })
	//private Set<Prestamo> prestamo = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "recurso_esp_autor", joinColumns = {
	@JoinColumn(name = "recurso_esp_id") }, inverseJoinColumns = { @JoinColumn(name = "autor_id") })
	private Set<Autor> autor = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isConsulta_interna() {
		return consulta_interna;
	}

	public void setConsulta_interna(boolean consulta_interna) {
		this.consulta_interna = consulta_interna;
	}

	public int getVolumen_recurso() {
		return volumen_recurso;
	}

	public void setVolumen_recurso(int volumen_recurso) {
		this.volumen_recurso = volumen_recurso;
	}

	public int getEdicion_recurso() {
		return edicion_recurso;
	}

	public void setEdicion_recurso(int edicion_recurso) {
		this.edicion_recurso = edicion_recurso;
	}

	public boolean isPrestado() {
		return prestado;
	}

	public void setPrestado(boolean prestado) {
		this.prestado = prestado;
	}

	public FormatoRecurso getFormato_recurso() {
		return formato_recurso;
	}

	public void setFormato_recurso(FormatoRecurso formato_recurso) {
		this.formato_recurso = formato_recurso;
	}


	public DetalleRecurso getDetalle_recurso() {
		return detalle_recurso;
	}

	public void setDetalle_recurso(DetalleRecurso detalle_recurso) {
		this.detalle_recurso = detalle_recurso;
	}
/*
	public Set<Prestamo> getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(Set<Prestamo> prestamo) {
		this.prestamo = prestamo;
	}*/

	public String getCodigo_rec_esp() {
		return codigo_rec_esp;
	}

	public void setCodigo_rec_esp(String codigo_rec_esp) {
		this.codigo_rec_esp = codigo_rec_esp;
	}

	public Set<Autor> getAutor() {
		return autor;
	}

	public void setAutor(Set<Autor> autor) {
		this.autor = autor;
	}

	public RecursoEspecifico(int id, boolean consulta_interna, int volumen_recurso, int edicion_recurso,
			boolean prestado, String codigo_rec_esp, FormatoRecurso formato_recurso,
			DetalleRecurso detalle_recurso, Set<Autor> autor) {
		super();
		this.id = id;
		this.consulta_interna = consulta_interna;
		this.volumen_recurso = volumen_recurso;
		this.edicion_recurso = edicion_recurso;
		this.prestado = prestado;
		this.codigo_rec_esp = codigo_rec_esp;
		this.formato_recurso = formato_recurso;
		this.detalle_recurso = detalle_recurso;
		this.autor = autor;
	}

	public RecursoEspecifico() {
		
	}

}
