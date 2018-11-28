package com.bib404.system_bib404.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bib404.system_bib404.entity.DetalleRecurso;

@Repository("detalleRecursoRepository")
public interface DetalleRecursoRepository extends JpaRepository<DetalleRecurso, Serializable>{

}
