package com.bib404.system_bib404.Repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bib404.system_bib404.entity.Usuario;
import com.bib404.system_bib404.model.PerfilModel;

@Repository("perfilRepository")
public interface PerfilRepository extends JpaRepository<Usuario,Serializable> {
	
	public abstract Usuario findById(int id);
	

}
