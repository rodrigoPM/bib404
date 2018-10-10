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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "prestamo")
public class Prestamo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_prestamo")
	private Date fecha_prestamo;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_entrega")
	private Date fecha_entrega;

	@Column(name = "cantidad_recurso_bib")
	private int cantidad_recurso_bib;

	@Column(name = "mora")
	private boolean mora;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_devolucion")
	private Date fecha_devolucion;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "usuario_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Usuario usuario;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "prestamo")
	private Set<RecursoEspecifico> recurso_especifico = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha_prestamo() {
		return fecha_prestamo;
	}

	public void setFecha_prestamo(Date fecha_prestamo) {
		this.fecha_prestamo = fecha_prestamo;
	}

	public Date getFecha_entrega() {
		return fecha_entrega;
	}

	public void setFecha_entrega(Date fecha_entrega) {
		this.fecha_entrega = fecha_entrega;
	}

	public int getCantidad_recurso_bib() {
		return cantidad_recurso_bib;
	}

	public void setCantidad_recurso_bib(int cantidad_recurso_bib) {
		this.cantidad_recurso_bib = cantidad_recurso_bib;
	}

	public boolean isMora() {
		return mora;
	}

	public void setMora(boolean mora) {
		this.mora = mora;
	}

	public Date getFecha_devolucion() {
		return fecha_devolucion;
	}

	public void setFecha_devolucion(Date fecha_devolucion) {
		this.fecha_devolucion = fecha_devolucion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<RecursoEspecifico> getRecurso_especifico() {
		return recurso_especifico;
	}

	public void setRecurso_especifico(Set<RecursoEspecifico> recurso_especifico) {
		this.recurso_especifico = recurso_especifico;
	}

	public Prestamo(int id, Date fecha_prestamo, Date fecha_entrega, int cantidad_recurso_bib, boolean mora,
			Date fecha_devolucion, Usuario usuario, Set<RecursoEspecifico> recurso_especifico) {
		super();
		this.id = id;
		this.fecha_prestamo = fecha_prestamo;
		this.fecha_entrega = fecha_entrega;
		this.cantidad_recurso_bib = cantidad_recurso_bib;
		this.mora = mora;
		this.fecha_devolucion = fecha_devolucion;
		this.usuario = usuario;
		this.recurso_especifico = recurso_especifico;
	}

	public Prestamo() {
		super();
	}

}
