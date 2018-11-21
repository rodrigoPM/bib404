package com.bib404.system_bib404.Repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bib404.system_bib404.entity.Categoria;
import com.bib404.system_bib404.entity.Usuario;

@Repository("usuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuario, Serializable>{
	
	List<Usuario> findByBibliotecaId(int bibliotecaId);
}
