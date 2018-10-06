package com.bib404.system_bib404.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bib404.system_bib404.Repository.UsuarioRepository;
import com.bib404.system_bib404.model.Usuario;
import com.bib404.system_bib404.service.UsuarioService;

@Service("usuarioServiceImpl")
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	@Qualifier("usuarioRepository")
	private UsuarioRepository usuarioRep;
	
	
	@Override
	public Usuario getUserId(long id) {
		
		return null;
	}

	@Override
	public List<Usuario> listUsuario() {
		return usuarioRep.findAll();
	}

	@Override
	public Usuario addUser(Usuario usuario) {
		return usuarioRep.save(usuario);
	}

	@Override
	public void removeUser(Usuario usuario) {
		usuarioRep.delete(usuario);
	}

	@Override
	public Usuario updateUser(Usuario usuario) {
		return usuarioRep.save(usuario);
	}
	

}
