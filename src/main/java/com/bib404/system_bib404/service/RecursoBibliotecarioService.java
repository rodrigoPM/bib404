package com.bib404.system_bib404.service;

import java.util.List;

import com.bib404.system_bib404.entity.RecursoBibliotecario;

public interface RecursoBibliotecarioService {
	
	public abstract List<RecursoBibliotecario> listAllRBOfBib(int id_bib); 
	public abstract boolean existsRBById(int id_rb);
	
}
