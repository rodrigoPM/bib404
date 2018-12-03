package com.bib404.system_bib404.service;

import java.util.List;

import com.bib404.system_bib404.entity.RecursoBibliotecario;

public interface RecursoBibliotecarioService {
	
	public abstract List<RecursoBibliotecario> listAllRBOfBib(int id_bib); 
	public abstract boolean existsById(int id_rb);
	public abstract RecursoBibliotecario findById(int id_rb);
	public abstract RecursoBibliotecario updateRB(RecursoBibliotecario rb);
	
}
