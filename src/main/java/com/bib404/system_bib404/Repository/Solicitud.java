package com.bib404.system_bib404.Repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Transactional(readOnly=true)
@Repository("solicitud")
public interface Solicitud extends JpaRepository<com.bib404.system_bib404.entity.Solicitud,Serializable>{
	
	@Query(value = "select * from solicitud where solicitud.enable = 0", nativeQuery = true)
	List<com.bib404.system_bib404.entity.Solicitud> findSolicitud();
	
	com.bib404.system_bib404.entity.Solicitud findById(int id);
	
	
}
