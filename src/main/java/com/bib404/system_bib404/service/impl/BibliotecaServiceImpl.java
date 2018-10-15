package com.bib404.system_bib404.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bib404.system_bib404.Repository.BibliotecaRepository;
import com.bib404.system_bib404.component.BibliotecaConverter;
import com.bib404.system_bib404.entity.Biblioteca;
import com.bib404.system_bib404.model.BibliotecaModel;
import com.bib404.system_bib404.service.BibliotecaService;

@Service("bibliotecaServiceImpl")
public class BibliotecaServiceImpl implements BibliotecaService{
	
	@Autowired
	@Qualifier("bibliotecaRepository")
	private BibliotecaRepository bibliotecaRepository;
	
	@Autowired
	@Qualifier("bibliotecaConverter")
	private BibliotecaConverter bibliotecaConverter;
	
	@Override
	public List<BibliotecaModel> listAllBibs() {
		// TODO Auto-generated method stub}
		List<Biblioteca> bibliotecas = bibliotecaRepository.findAll();
		System.out.println("numero encontradas: "+bibliotecas.size());
		List<BibliotecaModel> bibliotecasModel = new ArrayList<BibliotecaModel>();
		for (Biblioteca biblioteca : bibliotecas) {
			bibliotecasModel.add(bibliotecaConverter.convertBiblioteca2BibliotecaModel(biblioteca));
			System.out.println(bibliotecaConverter.convertBiblioteca2BibliotecaModel(biblioteca).getId()+" id de la biblioteca");
		}
		return bibliotecasModel;
	}

	@Override
	public BibliotecaModel findById(int id) {
		// TODO Auto-generated method stub
		Optional<Biblioteca> biblioteca =bibliotecaRepository.findById(id);
		BibliotecaModel bibliotecaModel=bibliotecaConverter.convertBiblioteca2BibliotecaModel(biblioteca.get());
		return bibliotecaModel;
	}

}
