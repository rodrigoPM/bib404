package com.bib404.system_bib404.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bib404.system_bib404.entity.RecursoBibliotecario;
import com.bib404.system_bib404.entity.Usuario;
@Transactional(readOnly=true)
@Repository("recursoBibliotecarioRepository")
public interface RecursoBibliotecarioRepository extends JpaRepository<RecursoBibliotecario, Serializable>{
	
	List<RecursoBibliotecario> findByBibliotecaId(int bibliotecaId);
	
	@Query(value = "select * from recurso_bib where Upper(recurso_bib.nombre_recurso_bib) like upper(?) and recurso_bib.biblioteca_id=?  ", 
			  nativeQuery = true)
			List<RecursoBibliotecario> buscarecurso(String nombre_recurso_bib,int biblioteca_id);
			
	@Query(value = "select * from  where trunc(usuario.fecha_registro)=? and usuario.biblioteca_id=?  ", 
			  nativeQuery = true)
			List<Usuario> obtenerRecursosHoy(String fecha_registro,int biblioteca_id);
	
	
}
