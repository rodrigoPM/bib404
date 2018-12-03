package com.bib404.system_bib404.service;

import java.util.List;

import com.bib404.system_bib404.entity.Categoria;

public interface CategoriaService {

	public abstract Categoria addCategoria(Categoria categoria);
	public abstract Categoria updateCategoria(Categoria categoria);
	public abstract List<Categoria> listAllCategorias(int id_bib);
	public abstract Categoria findByID(int id_cat);
	public abstract boolean deleteCategoria(int id_cat);
	public abstract boolean existsById(int id_cat);
}
