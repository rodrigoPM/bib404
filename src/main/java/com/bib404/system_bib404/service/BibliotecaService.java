package com.bib404.system_bib404.service;

import java.util.List;

import com.bib404.system_bib404.model.BibliotecaModel;
import com.bib404.system_bib404.entity.Biblioteca;

public interface BibliotecaService {
	public abstract List<BibliotecaModel> listAllBibs();
	public abstract BibliotecaModel findById(int id);
	public abstract List<Biblioteca> listAllBibliotecas();
	public abstract Biblioteca findBibById(int id_bib);
	public abstract boolean existsBibById(int id_bib);
	public abstract boolean deleteBiblioteca(int id_bib);
	public abstract Biblioteca updateBiblioteca(Biblioteca bib);

}
