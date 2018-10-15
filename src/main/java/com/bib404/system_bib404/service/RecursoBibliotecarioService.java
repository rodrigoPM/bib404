package com.bib404.system_bib404.service;

import java.util.List;

import com.bib404.system_bib404.model.RecursoBibliotecarioModel;

public interface RecursoBibliotecarioService {
	public abstract List<RecursoBibliotecarioModel> listAllRBOfBib(int id_bib); 
	
}
