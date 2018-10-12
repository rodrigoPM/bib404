package com.bib404.system_bib404.component;

import org.springframework.stereotype.Component;

import com.bib404.system_bib404.entity.Usuario;
import com.bib404.system_bib404.model.PerfilModel;
@Component("perfilConverter")
public class PerfilConverter {

	public Usuario convertPerfilModel2Perfil(PerfilModel perfilModel) {
		Usuario perfil =new Usuario();
		perfil.setUsername(perfilModel.getUsername());
		perfil.setApellido(perfilModel.getApellido());
		perfil.setBiblioteca(perfilModel.getBiblioteca());
		perfil.setEmail(perfilModel.getEmail());
		perfil.setEnable(perfilModel.isEnable());
		perfil.setFecha_nacimiento(perfilModel.getFecha_nacimiento());
		perfil.setFecha_registro(perfilModel.getFecha_registro());
		perfil.setFoto_perfil(perfilModel.getFoto_perfil());
		perfil.setGenero(perfilModel.getGenero());
		perfil.setId(perfilModel.getId());
		perfil.setLugar_estudio(perfilModel.getLugar_estudio());
	    perfil.setMunicipio(perfilModel.getMunicipio());
	    perfil.setNombre(perfilModel.getNombre());
	    perfil.setNombre_madre(perfilModel.getNombre_madre());
	    perfil.setNombre_padre(perfilModel.getNombre_padre());
	    perfil.setNumero_telefono(perfilModel.getNumero_telefono());
	    perfil.setOcupacion(perfilModel.getOcupacion());
	    perfil.setPassword(perfilModel.getPassword());
	    perfil.setRol(perfilModel.getRol());
		return perfil;
	}
	
	
	public PerfilModel convertPerfil2PerfilModel(Usuario usuario) {
		PerfilModel perfil = new PerfilModel();
		perfil.setUsername(usuario.getUsername());
		perfil.setApellido(usuario.getApellido());
		perfil.setBiblioteca(usuario.getBiblioteca());
		perfil.setEmail(usuario.getEmail());
		perfil.setEnable(usuario.isEnable());
		perfil.setFecha_nacimiento(usuario.getFecha_nacimiento());
		perfil.setFecha_registro(usuario.getFecha_registro());
		perfil.setFoto_perfil(usuario.getFoto_perfil());
		perfil.setGenero(usuario.getGenero());
		perfil.setId(usuario.getId());
		perfil.setLugar_estudio(usuario.getLugar_estudio());
	    perfil.setMunicipio(usuario.getMunicipio());
	    perfil.setNombre(usuario.getNombre());
	    perfil.setNombre_madre(usuario.getNombre_madre());
	    perfil.setNombre_padre(usuario.getNombre_padre());
	    perfil.setNumero_telefono(usuario.getNumero_telefono());
	    perfil.setOcupacion(usuario.getOcupacion());
	    perfil.setPassword(usuario.getPassword());
	    perfil.setRol(usuario.getRol());
		
		return perfil;
	}
	
	
}
