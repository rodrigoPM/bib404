package com.bib404.system_bib404.service;

import java.util.ArrayList;
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

	public abstract int addDpto(Departamento depto);

	public abstract List<Departamento> listDpto();

	public abstract int addMunicipio(Municipio municipio);

	public abstract List<Municipio> listMunicipios();

	public abstract int addMunicipio(Municipio municipio, int id_departamento);

	public abstract int addBiblio(Biblioteca bib, int id_municipio);

	public abstract List<Biblioteca> listBibliotecas();

	public abstract int addUser(Usuario usuario, int id_numicipio, int id_b);

	public abstract Biblioteca findBibBy(int id);

	public abstract Municipio findMunBy(int id);

	public abstract List<Municipio> listMunicipiosOrderByNombre();

	public abstract Departamento findDepBy(int id);

	public abstract Usuario findById(int id);
	
}
