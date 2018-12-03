package com.bib404.system_bib404.Repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bib404.system_bib404.entity.Prestamo;
import com.bib404.system_bib404.model.PrestamoModel;

@Repository("prestamoRepository")
public interface PrestamoRepository extends JpaRepository<Prestamo, Serializable>{
	public abstract Prestamo findById(int id);
	List<Prestamo> findByUsuarioId(int id_user);
	
	@Query(value = "select * from PRESTAMO inner join USUARIO on USUARIO.id=PRESTAMO.usuario_id where USUARIO.biblioteca_id = ? ", nativeQuery = true)
	List<Prestamo> listPrestamos(int biblioteca_id);

}
