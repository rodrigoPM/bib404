package com.bib404.system_bib404.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bib404.system_bib404.Repository.AutorRepository;
import com.bib404.system_bib404.entity.Autor;
import com.bib404.system_bib404.entity.Categoria;
import com.bib404.system_bib404.service.AutorService;

@Service("autorServiceImpl")
public class AutorServiceImpl implements AutorService{
	
	@Autowired
	@Qualifier("autorRepository")
	private AutorRepository autorRep;

	@Override
	public Autor addAutor(Autor autor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Autor> listAllAutores() {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public Autor findByID(int id_autor) {
		// TODO Auto-generated method stub
		try {
			Optional<Autor> autor=autorRep.findById(id_autor);
			return autor.get();
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public boolean existsById(int id_autor) {
		// TODO Auto-generated method stub
		return autorRep.existsById(id_autor);
	}
	
}
