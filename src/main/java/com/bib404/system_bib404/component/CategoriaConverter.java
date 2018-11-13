package com.bib404.system_bib404.component;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.bib404.system_bib404.entity.Categoria;
import com.bib404.system_bib404.model.CategoriaModel;


@Component("categoriaConverter")
public class CategoriaConverter {

	@Autowired
	@Qualifier("recursoBibliotecarioConverter")
	private RecursoBibliotecarioConverter rbConverter;

	public Categoria convertCategoriaModel2Categoria(CategoriaModel categoriaModel) {
		Categoria categoria = new Categoria();
		categoria.setId(categoriaModel.getId());
		categoria.setNombre_categoria(categoriaModel.getNombre_categoria());
		categoria.setDescripcion_categoria(categoriaModel.getDescripcion_categoria());
		categoria.setCategoria(convertCategoriaModel2Categoria(categoriaModel.getCategoria()));

		categoria.setRecurso_bib(categoriaModel.getRecurso_bib());
		
		return categoria;
	}

	public CategoriaModel convertCategoria2CategoriaModel(Categoria categoria) {
		CategoriaModel categoriaModel = new CategoriaModel();
		categoriaModel.setId(categoria.getId());
		categoriaModel.setNombre_categoria(categoria.getNombre_categoria());
		categoriaModel.setDescripcion_categoria(categoria.getDescripcion_categoria());
		categoriaModel.setCategoria(convertCategoria2CategoriaModel(categoria.getCategoria()));

		categoriaModel.setRecurso_bib(categoria.getRecurso_bib());

		return null;
	}
}
