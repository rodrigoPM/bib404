package com.bib404.system_bib404.model;

import java.util.Date;

import com.bib404.system_bib404.entity.Biblioteca;
import com.bib404.system_bib404.entity.Municipio;

public class PerfilModel {
	private int id;

	private String username;

	private String password;

	private String nombre;

	private String apellido;



	private String nombre_padre;

	private String nombre_madre;
	private String numero_telefono;

	private String lugar_estudio;
	private String genero;
	private String ocupacion;
	private String email;


	private String foto_perfil;

	private boolean enable;

	private String rol;

	private Municipio municipio;

	private Biblioteca biblioteca;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getNombre_padre() {
		return nombre_padre;
	}

	public void setNombre_padre(String nombre_padre) {
		this.nombre_padre = nombre_padre;
	}

	public String getNombre_madre() {
		return nombre_madre;
	}

	public void setNombre_madre(String nombre_madre) {
		this.nombre_madre = nombre_madre;
	}

	public String getNumero_telefono() {
		return numero_telefono;
	}

	public void setNumero_telefono(String numero_telefono) {
		this.numero_telefono = numero_telefono;
	}

	public String getLugar_estudio() {
		return lugar_estudio;
	}

	public void setLugar_estudio(String lugar_estudio) {
		this.lugar_estudio = lugar_estudio;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}




	public String getFoto_perfil() {
		return foto_perfil;
	}

	public void setFoto_perfil(String foto_perfil) {
		this.foto_perfil = foto_perfil;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Biblioteca getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}

	public PerfilModel() {

	}

	public PerfilModel(int id, String username, String password, String nombre, String apellido,
			String nombre_padre, String nombre_madre, String numero_telefono, String lugar_estudio, String genero,
			String ocupacion, String email, String foto_perfil, boolean enable, String rol,
			Municipio municipio, Biblioteca biblioteca) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.apellido = apellido;
		
		this.nombre_padre = nombre_padre;
		this.nombre_madre = nombre_madre;
		this.numero_telefono = numero_telefono;
		this.lugar_estudio = lugar_estudio;
		this.genero = genero;
		this.ocupacion = ocupacion;
		this.email = email;
		
		this.foto_perfil = foto_perfil;
		this.enable = enable;
		this.rol = rol;
		this.municipio = municipio;
		this.biblioteca = biblioteca;
	}

}
