package com.bib404.system_bib404.Repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bib404.system_bib404.entity.Biblioteca;
import com.bib404.system_bib404.entity.TipoRecurso;
@Transactional(readOnly=true)
@Repository("bibliotecaRepository")
public interface BibliotecaRepository extends JpaRepository<Biblioteca, Serializable>{
  

	
	@Query(value = "select * from BIBLIOTECA where BIBLIOTECA.ID = ? ", 
	  nativeQuery = true)
public Biblioteca buscarBiblioteca(int ID);

}
