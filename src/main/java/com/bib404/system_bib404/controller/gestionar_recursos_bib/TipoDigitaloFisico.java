package com.bib404.system_bib404.controller.gestionar_recursos_bib;

public class TipoDigitaloFisico {

	private int id;
	private String tipo;

	public TipoDigitaloFisico(int id, String tipo) {

		this.id = id;
		this.tipo = tipo;
	}

	public TipoDigitaloFisico() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
