package com.bib404.system_bib404.Repository;

import com.bib404.system_bib404.entity.FormatoRecurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("formatoRecursoRepository")
public interface FormatoRecursoRepository extends JpaRepository<FormatoRecurso, Serializable>{

}
