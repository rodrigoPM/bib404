package com.bib404.system_bib404.service;

import java.util.List;

import com.bib404.system_bib404.model.CategoriaModel;

public interface CategoriaService {
	
	public abstract CategoriaModel addCategoria(CategoriaModel categoriaModel);
	public abstract List<CategoriaModel> listAllCategorias();
	public abstract CategoriaModel findByID(int id_cat);
}
