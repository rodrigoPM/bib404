package com.bib404.system_bib404.Repository;

import java.awt.Image;
import java.io.File;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bib404.system_bib404.entity.Usuario;
import com.bib404.system_bib404.model.PerfilModel;
@Transactional(readOnly=true)
@Repository("actualizarPerfil")
public interface actualizarPerfil extends JpaRepository<Usuario,Serializable> {
	
	public abstract Usuario findById(int id);
		
		@Query(value = "select * from usuario where usuario.username = ? ", 
		  nativeQuery = true)
		Usuario buscarusuario(String username);
		
		
		@Query(value = "select * from usuario where trunc(usuario.fecha_registro)=? and usuario.biblioteca_id=?  ", 
				  nativeQuery = true)
				List<Usuario> obtenerRecursosHoy(String fecha_registro,int biblioteca_id);
		
		
		
}
