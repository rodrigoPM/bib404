package com.bib404.system_bib404.Repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bib404.system_bib404.entity.TipoRecurso;

@Transactional(readOnly=true)
@Repository("TipoRecursoRepository")
public interface TipoRecursoRepository extends JpaRepository<TipoRecurso,Serializable> {

	public abstract TipoRecurso findById(int id);
	
	@Query(value = "select * from TIPO_RECURSO where TIPO_RECURSO.ID = ? ", 
	  nativeQuery = true)
	TipoRecurso buscarRecurso(int ID);
}

