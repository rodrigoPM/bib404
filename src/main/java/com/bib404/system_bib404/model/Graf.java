package com.bib404.system_bib404.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Graf {
	private int cantidad;
	private Date fecha;
	private String formato;
	
	public String getFormato() {
		return formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}
	
	public Graf(int cantidad, Date fecha) {
		super();
		this.cantidad = cantidad;
		this.fecha = fecha;
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy/MM/dd");
		this.formato = "\'"+formateador.format(fecha)+"\'";
		System.out.println(formato);
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
