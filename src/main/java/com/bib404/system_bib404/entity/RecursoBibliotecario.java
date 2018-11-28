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
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "recurso_bib")
public class RecursoBibliotecario {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "nombre_recurso_bib")
	private String nombre_recurso_bib;

	@Column(name = "descripcion_recurso_bib")
	private String descripcion_recurso_bib;

	@Column(name = "sinopsis_recurso_bib")
	private String sinopsis_recurso_bib;

	@Column(name = "imagen_recurso_bib")
	private String imagen_recurso_bibl;

	@Column(name = "digital_recurso_bib")
	private String digital_recurso_bib;

	@Column(name = "fisico_recurso_bib")
	private String fisico_recurso_bib;

	@Column(name = "total_recurso_bib")
	private String total_recurso_bib;


	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "biblioteca_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Biblioteca biblioteca;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "tipo_recurso_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private TipoRecurso tipo_recurso;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "recurso_bib_categoria", joinColumns = {
	@JoinColumn(name = "recurso_bib_id") }, inverseJoinColumns = { @JoinColumn(name = "categoria_id") })
	private Set<Categoria> categoria = new HashSet<>();

	public RecursoBibliotecario(int id, String nombre_recurso_bib, String descripcion_recurso_bib,
			String sinopsis_recurso_bib, String imagen_recurso_bibl, String digital_recurso_bib,
			String fisico_recurso_bib, String total_recurso_bib, Biblioteca biblioteca, TipoRecurso tipo_recurso,
			Set<Categoria> categoria) {
		super();
		this.id = id;
		this.nombre_recurso_bib = nombre_recurso_bib;
		this.descripcion_recurso_bib = descripcion_recurso_bib;
		this.sinopsis_recurso_bib = sinopsis_recurso_bib;
		this.imagen_recurso_bibl = imagen_recurso_bibl;
		this.digital_recurso_bib = digital_recurso_bib;
		this.fisico_recurso_bib = fisico_recurso_bib;
		this.total_recurso_bib = total_recurso_bib;
		this.biblioteca = biblioteca;
		this.tipo_recurso = tipo_recurso;
		this.categoria = categoria;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre_recurso_bib() {
		return nombre_recurso_bib;
	}

	public void setNombre_recurso_bib(String nombre_recurso_bib) {
		this.nombre_recurso_bib = nombre_recurso_bib;
	}

	public String getDescripcion_recurso_bib() {
		return descripcion_recurso_bib;
	}

	public void setDescripcion_recurso_bib(String descripcion_recurso_bib) {
		this.descripcion_recurso_bib = descripcion_recurso_bib;
	}

	public String getSinopsis_recurso_bib() {
		return sinopsis_recurso_bib;
	}

	public void setSinopsis_recurso_bib(String sinopsis_recurso_bib) {
		this.sinopsis_recurso_bib = sinopsis_recurso_bib;
	}

	public String getImagen_recurso_bibl() {
		return imagen_recurso_bibl;
	}

	public void setImagen_recurso_bibl(String imagen_recurso_bibl) {
		this.imagen_recurso_bibl = imagen_recurso_bibl;
	}

	public String getDigital_recurso_bib() {
		return digital_recurso_bib;
	}

	public void setDigital_recurso_bib(String digital_recurso_bib) {
		this.digital_recurso_bib = digital_recurso_bib;
	}

	public String getFisico_recurso_bib() {
		return fisico_recurso_bib;
	}

	public void setFisico_recurso_bib(String fisico_recurso_bib) {
		this.fisico_recurso_bib = fisico_recurso_bib;
	}

	public String getTotal_recurso_bib() {
		return total_recurso_bib;
	}

	public void setTotal_recurso_bib(String total_recurso_bib) {
		this.total_recurso_bib = total_recurso_bib;
	}



	public Biblioteca getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}

	public TipoRecurso getTipo_recurso() {
		return tipo_recurso;
	}

	public void setTipo_recurso(TipoRecurso tipo_recurso) {
		this.tipo_recurso = tipo_recurso;
	}

	public Set<Categoria> getCategoria() {
		return categoria;
	}

	public void setCategoria(Set<Categoria> categoria) {
		this.categoria = categoria;
	}

	public RecursoBibliotecario() {
		super();
	}

}
