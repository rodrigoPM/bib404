package com.bib404.system_bib404.component;

import org.springframework.stereotype.Component;

import com.bib404.system_bib404.entity.Biblioteca;
import com.bib404.system_bib404.model.BibliotecaModel;

@Component("bibliotecaConverter")
public class BibliotecaConverter {

	public Biblioteca convertBibliotecaModel2Biblioteca(BibliotecaModel bibliotecaModel) {
		Biblioteca biblioteca= new Biblioteca();
		biblioteca.setId(bibliotecaModel.getId());
		biblioteca.setNombre_biblioteca(bibliotecaModel.getNombre_biblioteca());
		biblioteca.setCodigo_biblioteca(bibliotecaModel.getCodigo_biblioteca());
		biblioteca.setMunicipio(bibliotecaModel.getMunicipio());
		biblioteca.setRecurso_bib(bibliotecaModel.getRecurso_bib());
		return biblioteca;
	}
	
	public BibliotecaModel convertBiblioteca2BibliotecaModel(Biblioteca biblioteca) {
		BibliotecaModel bibliotecaModel = new BibliotecaModel();
		bibliotecaModel.setId(biblioteca.getId());
		bibliotecaModel.setNombre_biblioteca(biblioteca.getNombre_biblioteca());
		bibliotecaModel.setCodigo_biblioteca(biblioteca.getCodigo_biblioteca());
		bibliotecaModel.setMunicipio(biblioteca.getMunicipio());
		bibliotecaModel.setRecurso_bib(biblioteca.getRecurso_bib());
		return bibliotecaModel;
	}
}