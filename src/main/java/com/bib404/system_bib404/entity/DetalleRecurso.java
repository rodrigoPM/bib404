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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "detalle_recurso")
public class DetalleRecurso {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "total_rec_bib")
	private int total_rec_bib;

	@Column(name = "total_dig_rec_bib")
	private int total_dig_rec_bib;

	@Column(name = "total_fis_rec_bib")
	private int total_fis_rec_bib;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_ingreso_r_e")
	private Date fecha_ingreso_r_e;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "detalle_recurso")
	private RecursoEspecifico recurso_especifico;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "recurso_bib_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private RecursoBibliotecario recursoBib;

	public DetalleRecurso(int id, int total_rec_bib, int total_dig_rec_bib, int total_fis_rec_bib,
			Date fecha_ingreso_r_e, RecursoEspecifico recurso_especifico, RecursoBibliotecario recursoBib) {
		super();
		this.id = id;
		this.total_rec_bib = total_rec_bib;
		this.total_dig_rec_bib = total_dig_rec_bib;
		this.total_fis_rec_bib = total_fis_rec_bib;
		this.fecha_ingreso_r_e = fecha_ingreso_r_e;
		this.recurso_especifico = recurso_especifico;
		this.recursoBib = recursoBib;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTotal_rec_bib() {
		return total_rec_bib;
	}

	public void setTotal_rec_bib(int total_rec_bib) {
		this.total_rec_bib = total_rec_bib;
	}

	public int getTotal_dig_rec_bib() {
		return total_dig_rec_bib;
	}

	public void setTotal_dig_rec_bib(int total_dig_rec_bib) {
		this.total_dig_rec_bib = total_dig_rec_bib;
	}

	public int getTotal_fis_rec_bib() {
		return total_fis_rec_bib;
	}

	public void setTotal_fis_rec_bib(int total_fis_rec_bib) {
		this.total_fis_rec_bib = total_fis_rec_bib;
	}

	public Date getFecha_ingreso_r_e() {
		return fecha_ingreso_r_e;
	}

	public void setFecha_ingreso_r_e(Date fecha_ingreso_r_e) {
		this.fecha_ingreso_r_e = fecha_ingreso_r_e;
	}

	public RecursoEspecifico getRecurso_especifico() {
		return recurso_especifico;
	}

	public void setRecurso_especifico(RecursoEspecifico recurso_especifico) {
		this.recurso_especifico = recurso_especifico;
	}

	public RecursoBibliotecario getRecurso_bib() {
		return recursoBib;
	}

	public void setRecurso_bib(RecursoBibliotecario recursoBib) {
		this.recursoBib = recursoBib;
	}

	public DetalleRecurso() {
		super();
	}

}
