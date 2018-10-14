package com.bib404.system_bib404.Repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bib404.system_bib404.entity.Usuario;
import com.bib404.system_bib404.model.PerfilModel;
@Transactional(readOnly=true)
@Repository("perfilRepository")
public interface PerfilRepository extends JpaRepository<Usuario,Integer> {
	
	public abstract Usuario findById(int id);
	
	@Modifying
 @Transactional(readOnly=false)
	@Query(value = "update Usuario u set u.nombre = ? where u.id = ?", 
	  nativeQuery = true)
	int actualizar(String nombre, int id);
	
}
