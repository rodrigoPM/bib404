package com.bib404.system_bib404.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bib404.system_bib404.entity.Categoria;
import com.bib404.system_bib404.entity.Usuario;

@Repository("categoriaRepository")
public interface CategoriaRepository extends JpaRepository<Categoria, Serializable>{

	List<Categoria> findByBibliotecaId(int bibliotecaId);
	

	@Modifying
	@Transactional(readOnly=false)
	@Query(value="select max(c.id) from categoria c", nativeQuery=true)
	Integer lastID();

	@Modifying
	@Transactional(readOnly=false)
	@Query(value="select count(id) from categoria", nativeQuery=true)
	Integer countCategoria();



	@Query(value = "select * from categoria where trunc(categoria.created_at)=? and categoria.biblioteca_id=?  ", 
			  nativeQuery = true)
			List<Categoria> obtenerCategoria(String created_at,int biblioteca_id);
	
	
}
