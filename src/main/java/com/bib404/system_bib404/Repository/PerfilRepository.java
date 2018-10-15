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
	int actualizarNombre(String nombre, int id);
	
	@Modifying
	 @Transactional(readOnly=false)
		@Query(value = "update Usuario u set u.apellido = ? where u.id = ?", 
		  nativeQuery = true)
		int actualizarApellido(String nombre, int id);
	
	@Modifying
	 @Transactional(readOnly=false)
		@Query(value = "update Usuario u set u.email = ? where u.id = ?", 
		  nativeQuery = true)
		int actualizarEmail(String email, int id);
	@Modifying
	 @Transactional(readOnly=false)
		@Query(value = "update Usuario u set u.genero = ? where u.id = ?", 
		  nativeQuery = true)
		int actualizarGenero(String genero, int id);
	

	@Modifying
	 @Transactional(readOnly=false)
		@Query(value = "update Usuario u set u.nombre_padre = ? where u.id = ?", 
		  nativeQuery = true)
		int actualizarPadre(String padre, int id);
	
	
	@Modifying
	 @Transactional(readOnly=false)
		@Query(value = "update Usuario u set u.nombre_madre = ? where u.id = ?", 
		  nativeQuery = true)
		int actualizarMadre(String madre, int id);
	
	@Modifying
	 @Transactional(readOnly=false)
		@Query(value = "update Usuario u set u.numero_telefono= ? where u.id = ?", 
		  nativeQuery = true)
		int actualizarNumero(String numero, int id);
	
	
	@Modifying
	 @Transactional(readOnly=false)
		@Query(value = "update Usuario u set u.ocupacion = ? where u.id = ?", 
		  nativeQuery = true)
		int actualizarOcupacion(String ocupacion, int id);
	
	@Modifying
	 @Transactional(readOnly=false)
		@Query(value = "update Usuario u set u.username = ? where u.id = ?", 
		  nativeQuery = true)
		int actualizarUsername(String username, int id);
	
	@Modifying
	 @Transactional(readOnly=false)
		@Query(value = "update Usuario u set u.biblioteca_id = ? where u.id = ?", 
		  nativeQuery = true)
		int actualizarBiblioteca(int biblioteca_id, int id);
	
	@Modifying
	 @Transactional(readOnly=false)
		@Query(value = "update Usuario u set u.municipio_id = ? where u.id = ?", 
		  nativeQuery = true)
		int actualizarMunicipio(int municipio_id, int id);
	
}
