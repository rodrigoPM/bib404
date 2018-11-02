package com.bib404.system_bib404.component;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.bib404.system_bib404.entity.Biblioteca;
import com.bib404.system_bib404.entity.RecursoBibliotecario;
import com.bib404.system_bib404.model.BibliotecaModel;
import com.bib404.system_bib404.model.RecursoBibliotecarioModel;

@Component("bibliotecaConverter")
public class BibliotecaConverter {

	@Autowired
	@Qualifier("municipioConverter")
	private MunicipioConverter municipio;

	@Autowired
	@Qualifier("recursoBibliotecarioConverter")
	private RecursoBibliotecarioConverter rec_bib;

	public Biblioteca convertBibliotecaModel2Biblioteca(BibliotecaModel bibliotecaModel) {
		Biblioteca biblioteca = new Biblioteca();
		biblioteca.setId(bibliotecaModel.getId());
		biblioteca.setNombre_biblioteca(bibliotecaModel.getNombre_biblioteca());
		biblioteca.setCodigo_biblioteca(bibliotecaModel.getCodigo_biblioteca());
		biblioteca.setMunicipio(municipio.convertMunicipioModel2Municipio(bibliotecaModel.getMunicipio()));
		
		biblioteca.setRecurso_bib(bibliotecaModel.getRecurso_bib());

		return biblioteca;
	}

	public BibliotecaModel convertBiblioteca2BibliotecaModel(Biblioteca biblioteca) {
		BibliotecaModel bibliotecaModel = new BibliotecaModel();
		bibliotecaModel.setId(biblioteca.getId());
		bibliotecaModel.setNombre_biblioteca(biblioteca.getNombre_biblioteca());
		bibliotecaModel.setCodigo_biblioteca(biblioteca.getCodigo_biblioteca());
		bibliotecaModel.setMunicipio(municipio.convertMunicipio2MunicipioModel(biblioteca.getMunicipio()));

		bibliotecaModel.setRecurso_bib(biblioteca.getRecurso_bib());

		return bibliotecaModel;
	}
}
