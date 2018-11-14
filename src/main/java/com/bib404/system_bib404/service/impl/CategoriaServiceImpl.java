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
	public CategoriaModel addCategoria(CategoriaModel categoriaModel) {
		// TODO Auto-generated method stub
		
		//ver si no hay registros, 
		if(categoriaRepository.countCategoria().intValue()==0) {//no hay registros
			categoriaModel.setId(1);
		}else {//ya hay registros
			categoriaModel.setId(categoriaRepository.lastID().intValue() +1);
		}
		
		if(categoriaModel.getCategoria()==null){//si no es subcategoria
			int codigoSuceso=categoriaRepository.saveSimple(categoriaModel.getId(), 
					categoriaModel.getNombre_categoria(), categoriaModel.getDescripcion_categoria());
			System.out.println("codigo:"+codigoSuceso);
			return findByID(categoriaModel.getId());
		}else { //es subcategoria
			int codigoSuceso=categoriaRepository.saveComplex(categoriaModel.getId(), 
					categoriaModel.getNombre_categoria(), categoriaModel.getCategoria().getId(),categoriaModel.getDescripcion_categoria());
			return findByID(categoriaModel.getId());
		}
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

	@Override
	public CategoriaModel findByID(int id_cat) {
		// TODO Auto-generated method stub
		Optional<Categoria> categoria=categoriaRepository.findById(id_cat);
		CategoriaModel categoriaModel=categoriaConverter.convertCategoria2CategoriaModel(categoria.get());
		return categoriaModel;
	}

}
