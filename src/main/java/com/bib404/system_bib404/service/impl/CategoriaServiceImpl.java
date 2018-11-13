package com.bib404.system_bib404.service.impl;

import java.util.ArrayList;
import java.util.List;

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
	public CategoriaModel addCategoria(CategoriaModel categoriaModel) {
		// TODO Auto-generated method stub
		if (listAllCategorias().size()==0) {
			categoriaModel.setId(1);
		}else {
			categoriaModel.setId(listAllCategorias().size()+1);
		}
		Categoria categoria=categoriaRepository.save(categoriaConverter.convertCategoriaModel2Categoria(categoriaModel));
		return categoriaConverter.convertCategoria2CategoriaModel(categoria);
	}

	@Override
	public List<CategoriaModel> listAllCategorias() {
		// TODO Auto-generated method stub
		List<Categoria> categorias=categoriaRepository.findAll();
		List<CategoriaModel> categoriaModels=new ArrayList<CategoriaModel>();
		for(Categoria categoria:categorias) {
			categoriaModels.add(categoriaConverter.convertCategoria2CategoriaModel(categoria));
		}
		return categoriaModels;
	}

}
