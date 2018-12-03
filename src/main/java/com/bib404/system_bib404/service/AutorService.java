package com.bib404.system_bib404.service;

import java.util.List;

import com.bib404.system_bib404.entity.Autor;

public interface AutorService {
	public abstract Autor addAutor(Autor autor);
	public abstract List<Autor> listAllAutores();
	public abstract Autor findByID(int id_autor);
//	public abstract boolean deleteAutor(int id_autor);
	public abstract boolean existsById(int id_autor);
  
}
