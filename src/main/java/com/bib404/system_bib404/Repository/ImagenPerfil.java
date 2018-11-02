package com.bib404.system_bib404.Repository;

import java.awt.Image;
import java.io.File;
import java.io.Serializable;
import java.util.Date;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bib404.system_bib404.entity.Usuario;
import com.bib404.system_bib404.model.PerfilModel;
@Transactional(readOnly=true)
@Repository("ImagenPerfil")
public interface ImagenPerfil extends JpaRepository<Usuario,Date> {
	
	@Modifying
	 @Transactional(readOnly=false)
		@Query(value = "update Usuario u set u.fecha_nacimiento=? where u.username = ?", 
		  nativeQuery = true)
		int actualizarFecha(  Date fecha_nacimiento, String username);	
}
