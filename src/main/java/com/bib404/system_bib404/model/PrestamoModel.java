package com.bib404.system_bib404.model;

import java.util.Date;
import java.util.Set;

import com.bib404.system_bib404.entity.RecursoEspecifico;
import com.bib404.system_bib404.entity.Usuario;

public class PrestamoModel {
	
	private int id;
	private Date fecha_prestamo;
	private Date fecha_entrega;
	private int cantidad_recurso_bib;
	private boolean mora;
	private Date fecha_devolucion;
	private Usuario usuario;
	private Set<RecursoEspecifico> recurso_especifico;
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
	public PrestamoModel(int id, Date fecha_prestamo, Date fecha_entrega, int cantidad_recurso_bib, boolean mora,
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
	public PrestamoModel() {
		super();
	}
	

}
