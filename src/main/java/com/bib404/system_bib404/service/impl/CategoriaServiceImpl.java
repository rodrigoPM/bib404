package com.bib404.system_bib404.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bib404.system_bib404.Repository.CategoriaRepository;
import com.bib404.system_bib404.component.CategoriaConverter;
import com.bib404.system_bib404.entity.Categoria;
import com.bib404.system_bib404.model.CategoriaModel;
import com.bib404.system_bib404.service.CategoriaService;

@Service("categoriaServiceImpl")
public class CategoriaServiceImpl implements CategoriaService{

	@Autowired
	@Qualifier("categoriaRepository")
	private CategoriaRepository categoriaRepository;
	@Autowired
	@Qualifier("categoriaConverter")
	private CategoriaConverter categoriaConverter;

	@Override
	public Categoria addCategoria(Categoria categoria) {
		// TODO Auto-generated method stub
		Categoria cat=categoriaRepository.save(categoria);
		return cat;
	}

@Override
public boolean deleteCategoria(int id_cat){
	if(findByID(id_cat)!=null){
		categoriaRepository.delete(findByID(id_cat));
		if (!existsById(id_cat)) {
			return true;
		}else{
			return false;
		}
	}else{
		return false;
	}
}

@Override
public boolean existsById(int id_cat){
	return categoriaRepository.existsById(id_cat);
}

	@Override
	public List<Categoria> listAllCategorias(int id_bib) {
		// TODO Auto-generated method stub
		List<Categoria> categorias=categoriaRepository.findByBibliotecaId(id_bib);
		return categorias;
	}

	@Override
	public Categoria findByID(int id_cat) {
		// TODO Auto-generated method stub
		try {
			Optional<Categoria> categoria=categoriaRepository.findById(id_cat);
			return categoria.get();
		} catch(Exception e) {
			return null;
		}
	}

}
