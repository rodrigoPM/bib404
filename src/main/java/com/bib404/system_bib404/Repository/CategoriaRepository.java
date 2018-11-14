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

@Repository("categoriaRepository")
public interface CategoriaRepository extends JpaRepository<Categoria, Serializable>{
	
	@Modifying
	@Transactional(readOnly=false)
	@Query(value="insert into categoria (id, nombre_categoria, descripcion_categoria) values (?, ?, ?)", nativeQuery=true)
	int saveSimple(int id_cat, String nombre_cat, String descripcion_cat);
	
	@Modifying
	@Transactional(readOnly=false)
	@Query(value="insert into categoria (id, nombre_categoria, categoria_id, descripcion_categoria) values (?, ?, ?, ?)", nativeQuery=true)
	int saveComplex(int id_cat, String nombre_cat, int cat_id, String descripcion_cat);
	
	@Modifying
	@Transactional(readOnly=false)
	@Query(value="select max(c.id) from categoria c", nativeQuery=true)
	Integer lastID();
	
	@Modifying
	@Transactional(readOnly=false)
	@Query(value="select count(id) from categoria", nativeQuery=true)
	Integer countCategoria();
	
	
	
}
