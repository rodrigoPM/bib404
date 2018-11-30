package com.bib404.system_bib404.service.impl;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bib404.system_bib404.constant.Constante;
import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.entity.Usuario;
import com.bib404.system_bib404.service.Funciones;

@Service("Functions")
public class Functions implements Funciones{
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioServiceImpl usuarioImp;

	//Funcion que verifica si el usuario es un superusuario es decir el administrador de los administradores de una biblioteca
	@Override
	public boolean isSuperUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute(Constante.USER)!=null) {
			Usuario user = (Usuario) session.getAttribute(Constante.USER);
			if (user.getRol().compareToIgnoreCase(Constante.SUPER_USUARIO)==0) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	

	//Funcion que verifica si el usuario es un superusuario es decir el administrador del sistema bib404
	@Override
	public boolean isSuperUserBIB404(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute(Constante.USER)!=null) {
			Usuario user = (Usuario) session.getAttribute(Constante.USER);
			if (user.getRol().compareToIgnoreCase(Constante.SUPER_SUPERUSUARIO)==0) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}

	//funcion que verifica si el usuario es un administrador de una biblioteca
	@Override
	public boolean isAdmin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute(Constante.USER)!=null) {
			Usuario user = (Usuario) session.getAttribute(Constante.USER);
			if (user.getRol().compareToIgnoreCase(Constante.ADMIN)==0 && user.isEnable()) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}

	//Funcion que verifica que el usuario este logeado, este es un usuario simple no administra nada
	@Override
	public boolean isUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute(Constante.USER)!=null) {
			Usuario user = (Usuario) session.getAttribute(Constante.USER);
			System.out.println(user.getRol());
			if (user.getRol().compareToIgnoreCase(Constante.USUARIO_SIMPLE)==0 && user.isEnable()) {
				return true;
			}else {
				return false;
			}
			
		}else {
			return false;
		}
	}
	
	//esta funcion verifica que sea un usuario, no importa si es superusuario o administrador o usuario simple siempre que este logeao devolvera un true
	@Override
	public boolean isAnyUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute(Constante.USER)!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	//Esta funcion verifica si el id_user es realmente el usuario registrado y si esta en la biblioteca que le corresponde
	@Override
	public boolean pertenece_biblioteca(int id_user, int id_bib, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute(Constante.USER)!=null) {
			Usuario user = (Usuario) session.getAttribute(Constante.USER);
			System.out.println(user.getBiblioteca().getNombre_biblioteca()+"   id: "+user.getBiblioteca().getId());
			if (user.getBiblioteca().getId()==id_bib && user.getId()==id_user) {
				return true;
			}else {
				return false;
			}
			
		}else {
			return false;
		}
	}
	
	//Esta funcion solo verifica si el usuario en cuestion pertenece a la biblioteca, este logeado o no solo lo verifica
	@Override
	public boolean userInBib(int id_user, int id_bib) {
		Usuario user = usuarioImp.findById(id_user);
		if (user.getBiblioteca().getId()==id_bib) {
			return true;
		}else {
			return false;
		}
	}

}
