package com.bib404.system_bib404.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.bib404.system_bib404.entity.QDetalleRecurso;
import com.bib404.system_bib404.entity.QRecursoBibliotecario;
import com.bib404.system_bib404.entity.QRecursoEspecifico;
import com.bib404.system_bib404.entity.RecursoBibliotecario;
import com.bib404.system_bib404.entity.RecursoEspecifico;
import com.querydsl.jpa.impl.JPAQuery;

@Repository("consultasRE")
public class ConsultasRE {
	private QRecursoEspecifico qRecursoEspecifico=QRecursoEspecifico.recursoEspecifico;
	private QDetalleRecurso qDetalleRecurso=QDetalleRecurso.detalleRecurso;
	private QRecursoBibliotecario qRecursoBibliotecario=QRecursoBibliotecario.recursoBibliotecario;
	@PersistenceContext
	private EntityManager em;
	
	public List<RecursoEspecifico> findJoin(int id) {
		JPAQuery<RecursoEspecifico> query=new JPAQuery<RecursoEspecifico>(em);
		List<RecursoEspecifico> res=query.select(qRecursoEspecifico)
				.from(qRecursoEspecifico)
				.innerJoin(qRecursoEspecifico.detalle_recurso,qDetalleRecurso)
				.innerJoin(qDetalleRecurso.recurso_bib,qRecursoBibliotecario)
				.where(qRecursoBibliotecario.id.eq(id)).fetch();
		return res;
	}
	public RecursoBibliotecario ver(int id) {
		JPAQuery<RecursoBibliotecario> query=new JPAQuery<RecursoBibliotecario>(em);
		RecursoBibliotecario rb=query.select(qRecursoBibliotecario)
				.from(qRecursoBibliotecario)
				.where(qRecursoBibliotecario.id.eq(id)).fetchOne();
		return rb;
	}

}
