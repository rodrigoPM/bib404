package com.bib404.system_bib404.model;

import java.util.HashSet;
import java.util.Set;

import com.bib404.system_bib404.entity.Autor;
import com.bib404.system_bib404.entity.DetalleRecurso;
import com.bib404.system_bib404.entity.Editorial;
import com.bib404.system_bib404.entity.FormatoRecurso;
import com.bib404.system_bib404.entity.Prestamo;

public class RecursoEspecificoModel {
	private int id;
	private boolean consulta_interna;
	private int volumen_recurso;
	private int edicion_recurso;
	private boolean prestado;
	private String codigo_rec_esp;
	private FormatoRecurso formato_recurso;
	private Editorial editorial;
	private DetalleRecurso detalle_recurso;
	private Set<Prestamo> prestamo = new HashSet<>();
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

	public Editorial getEditorial() {
		return editorial;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

	public DetalleRecurso getDetalle_recurso() {
		return detalle_recurso;
	}

	public void setDetalle_recurso(DetalleRecurso detalle_recurso) {
		this.detalle_recurso = detalle_recurso;
	}

	public Set<Prestamo> getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(Set<Prestamo> prestamo) {
		this.prestamo = prestamo;
	}

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

	public RecursoEspecificoModel(int id, boolean consulta_interna, int volumen_recurso, int edicion_recurso,
			boolean prestado, String codigo_rec_esp, FormatoRecurso formato_recurso, Editorial editorial,
			DetalleRecurso detalle_recurso, Set<Prestamo> prestamo, Set<Autor> autor) {
		super();
		this.id = id;
		this.consulta_interna = consulta_interna;
		this.volumen_recurso = volumen_recurso;
		this.edicion_recurso = edicion_recurso;
		this.prestado = prestado;
		this.codigo_rec_esp = codigo_rec_esp;
		this.formato_recurso = formato_recurso;
		this.editorial = editorial;
		this.detalle_recurso = detalle_recurso;
		this.prestamo = prestamo;
		this.autor = autor;
	}

	public RecursoEspecificoModel() {
		
	}

}

