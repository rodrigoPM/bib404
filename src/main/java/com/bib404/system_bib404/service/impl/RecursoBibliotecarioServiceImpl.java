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
	public List<RecursoBibliotecarioModel> listAllRBOfBib(int id_bib) {
		List<RecursoBibliotecario> rbs=rbRep.findAllRBofBib(id_bib);
		List<RecursoBibliotecarioModel> rbModel=new ArrayList<RecursoBibliotecarioModel>();
		for(RecursoBibliotecario rb:rbs) {
			rbModel.add(rbConverter.converterRB2RBModel(rb));
		}
		return rbModel;
	}

}
