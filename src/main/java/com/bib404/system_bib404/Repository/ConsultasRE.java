package com.bib404.system_bib404.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.bib404.system_bib404.entity.Autor;
import com.bib404.system_bib404.entity.Biblioteca;
import com.bib404.system_bib404.entity.QAutor;
import com.bib404.system_bib404.entity.QBiblioteca;
import com.bib404.system_bib404.entity.QDetalleRecurso;
import com.bib404.system_bib404.entity.QRecursoBibliotecario;
import com.bib404.system_bib404.entity.QRecursoEspecifico;
import com.bib404.system_bib404.entity.QUsuario;
import com.bib404.system_bib404.entity.RecursoBibliotecario;
import com.bib404.system_bib404.entity.RecursoEspecifico;
import com.bib404.system_bib404.entity.Usuario;
import com.querydsl.jpa.impl.JPAQuery;

@Repository("consultasRE")
public class ConsultasRE {
    private QRecursoEspecifico qRecursoEspecifico=QRecursoEspecifico.recursoEspecifico;
	private QDetalleRecurso qDetalleRecurso=QDetalleRecurso.detalleRecurso;
	private QRecursoBibliotecario qRecursoBibliotecario=QRecursoBibliotecario.recursoBibliotecario;
	private QAutor qAutor=QAutor.autor;
	private QUsuario qUsuario=QUsuario.usuario;
	private QBiblioteca qBiblioteca=QBiblioteca.biblioteca;
	@PersistenceContext
	private EntityManager em;

	//Devuelve los recursos especificos de un recurso bibliotecario
	public List<RecursoEspecifico> findJoin(int id) {
		JPAQuery<RecursoEspecifico> query=new JPAQuery<RecursoEspecifico>(em);
		List<RecursoEspecifico> res=query.select(qRecursoEspecifico)
				.from(qRecursoEspecifico)
				.innerJoin(qRecursoEspecifico.detalle_recurso,qDetalleRecurso)
				.innerJoin(qDetalleRecurso.recursoBib,qRecursoBibliotecario)
				.where(qRecursoBibliotecario.id.eq(id)).fetch();
		return res;
	}

	//Devuelve un recurso bibliotecario
	public RecursoBibliotecario ver(int id) {
		JPAQuery<RecursoBibliotecario> query=new JPAQuery<RecursoBibliotecario>(em);
		RecursoBibliotecario rb=query.select(qRecursoBibliotecario)
				.from(qRecursoBibliotecario)
				.where(qRecursoBibliotecario.id.eq(id)).fetchOne();
		return rb;
	}

	//Devuelve todos los administradores de bibliotecas
	public List<Usuario> obtenerAdmins(){
		JPAQuery<Usuario> query=new JPAQuery<Usuario>(em);
		List<Usuario> admins=query.select(qUsuario).from(qUsuario)
				.where(qUsuario.rol.eq("ADMIN")).fetch();
		return admins;
	}
	
	//devuelve la biblioteca que pertenece a un administrador
	public Biblioteca biblioteca(int id){
		JPAQuery<Biblioteca> query=new JPAQuery<Biblioteca>(em);
		Biblioteca bib=query.select(qBiblioteca).from(qBiblioteca)
				.innerJoin(qBiblioteca,qUsuario.biblioteca)
				.where(qUsuario.id.eq(id)).fetchOne();
		return bib;
	}

	//devuelve los usuarios normales de una biblioteca
	public List<Usuario> listaUsuarios(Biblioteca bib) {
		JPAQuery<Usuario> query=new JPAQuery<Usuario>(em);
		List<Usuario> usuarios=query.select(qUsuario).from(qUsuario)
				.where(qUsuario.rol.eq("USER_ROLE"),
						qUsuario.biblioteca.eq(bib)).fetch();
		return usuarios;
	}
	

}
