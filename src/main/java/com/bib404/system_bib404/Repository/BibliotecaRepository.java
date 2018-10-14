package com.bib404.system_bib404.Repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bib404.system_bib404.entity.Biblioteca;

@Repository("bibliotecaRepository")
public interface BibliotecaRepository extends JpaRepository<Biblioteca, Serializable>{
	
}