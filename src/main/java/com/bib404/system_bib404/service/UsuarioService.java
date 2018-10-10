package com.bib404.system_bib404.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bib404.system_bib404.model.UsuarioModel;


public interface UsuarioService {
	
	public abstract UsuarioModel getUserId(long id);
	
	public abstract List<UsuarioModel> listUsuario();
	
	public abstract int addUser(UsuarioModel usuario);
	
	public abstract void removeUser(UsuarioModel usuario);
	
	public abstract UsuarioModel updateUser(UsuarioModel usuario);
	
	public abstract UsuarioModel findBy(String username);
	
}
