package com.bib404.system_bib404.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bib404.system_bib404.model.Usuario;


public interface UsuarioService {
	
	public abstract Usuario getUserId(long id);
	
	public abstract List<Usuario> listUsuario();
	
	public abstract Usuario addUser(Usuario usuario);
	
	public abstract void removeUser(Usuario usuario);
	
	public abstract Usuario updateUser(Usuario usuario);
	
}
