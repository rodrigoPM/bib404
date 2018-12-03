package com.bib404.system_bib404.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bib404.system_bib404.Repository.RecursoBibliotecarioRepository;
import com.bib404.system_bib404.component.RecursoBibliotecarioConverter;
import com.bib404.system_bib404.entity.RecursoBibliotecario;
import com.bib404.system_bib404.model.RecursoBibliotecarioModel;
import com.bib404.system_bib404.service.RecursoBibliotecarioService;

@Service("recursoBibliotecarioServiceImpl")
public class RecursoBibliotecarioServiceImpl implements RecursoBibliotecarioService {
	
	@Autowired
	@Qualifier("recursoBibliotecarioRepository")
	private RecursoBibliotecarioRepository rbRep;
	
	@Autowired
	@Qualifier("recursoBibliotecarioConverter")
	private RecursoBibliotecarioConverter rbConverter;
	
	@Override
	public List<RecursoBibliotecario> listAllRBOfBib(int id_bib) {
		List<RecursoBibliotecario> rbs=rbRep.findByBibliotecaId(id_bib);
		return rbs;
	}

	@Override
	public boolean existsById(int id_rb) {
		// TODO Auto-generated method stub
		return rbRep.existsById(id_rb);
	}

	@Override
	public RecursoBibliotecario findById(int id_rb) {
		return rbRep.findById(id_rb).get();
	}

	@Override
	public RecursoBibliotecario updateRB(RecursoBibliotecario rb) {
		return rbRep.save(rb);
	}

}
