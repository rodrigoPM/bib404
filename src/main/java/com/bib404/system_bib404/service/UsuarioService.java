package com.bib404.system_bib404.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bib404.system_bib404.entity.Biblioteca;
import com.bib404.system_bib404.entity.Departamento;
import com.bib404.system_bib404.entity.Municipio;
import com.bib404.system_bib404.entity.Usuario;



public interface UsuarioService {
	
	public abstract Usuario getUserId(long id);
	
	public abstract List<Usuario> listUsuario();
	
	public abstract void removeUser(Usuario usuario);
	
	public abstract Usuario updateUser(Usuario usuario);
	
	public abstract Usuario findBy(String username);

	int addDpto(Departamento depto);

	List<Departamento> listDpto();

	int addMunicipio(Municipio municipio);

	List<Municipio> listMunicipios();

	int addMunicipio(Municipio municipio, int id_departamento);

	int addBiblio(Biblioteca bib, int id_municipio);

	List<Biblioteca> listBibliotecas();

	int addUser(Usuario usuario, int id_numicipio, int id_b);

	Biblioteca findBibBy(int id);

	Municipio findMunBy(int id);

	List<Municipio> listMunicipiosOrderByNombre();

	Departamento findDepBy(int id);
	
}
