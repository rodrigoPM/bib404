package com.bib404.system_bib404.model;

import java.util.Date;

public class Graf {
	private int cantidad;
	private Date fecha;
	
	
	public Graf(int cantidad, Date fecha) {
		super();
		this.cantidad = cantidad;
		this.fecha = fecha;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	
}
