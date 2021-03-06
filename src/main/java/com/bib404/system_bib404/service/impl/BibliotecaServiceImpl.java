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
	public List<Biblioteca> listAllBibliotecas(){
		List<Biblioteca> bibliotecas=bibliotecaRepository.findAll();
		return bibliotecas;
	}
	@Override
	public boolean deleteBiblioteca(int id_bib){
		if(findBibById(id_bib)!=null){
			bibliotecaRepository.delete(findBibById(id_bib));
			if (!existsBibById(id_bib)) {
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	@Override
	public Biblioteca findBibById(int id_bib){
		try {
			Optional<Biblioteca> bib = bibliotecaRepository.findById(id_bib);
			return bib.get();
		} catch(Exception e) {
				return null;
		}
	}
@Override
public boolean existsBibById(int id_bib){
	return bibliotecaRepository.existsById(id_bib);
}



	@Override
	public List<BibliotecaModel> listAllBibs() {
		// TODO Auto-generated method stub}
		List<Biblioteca> bibliotecas = bibliotecaRepository.findAll();
		List<BibliotecaModel> bibliotecasModel = new ArrayList<BibliotecaModel>();
		for (Biblioteca biblioteca : bibliotecas) {
			System.out.println(biblioteca);
			bibliotecasModel.add(bibliotecaConverter.convertBiblioteca2BibliotecaModel(biblioteca));
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

	@Override
	public Biblioteca updateBiblioteca(Biblioteca bib) {
		return bibliotecaRepository.save(bib);
	}

}
