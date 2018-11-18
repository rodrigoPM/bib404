package com.bib404.system_bib404.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.bib404.system_bib404.entity.Usuario;

public interface Funciones {
	public abstract boolean isSuperUser(HttpServletRequest request);
	public abstract boolean isAdmin(HttpServletRequest request);
	public abstract boolean isUser(HttpServletRequest request);
	public abstract boolean pertenece_biblioteca(int id_user, int id_bib, HttpServletRequest request);
	public abstract boolean isAnyUser(HttpServletRequest request);
	public abstract boolean userInBib(int id_user, int id_bib);
	public abstract boolean isSuperUserBIB404(HttpServletRequest request);
}
