package com.bib404.system_bib404.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bib404.system_bib404.Repository.BibliotecaRepository;
import com.bib404.system_bib404.Repository.CategoriaRepository;
import com.bib404.system_bib404.Repository.RecursoBibliotecarioRepository;
import com.bib404.system_bib404.Repository.actualizarPerfil;
import com.bib404.system_bib404.constant.Constante;
import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.entity.Biblioteca;
import com.bib404.system_bib404.entity.Categoria;
import com.bib404.system_bib404.entity.RecursoBibliotecario;
import com.bib404.system_bib404.entity.Usuario;
import com.bib404.system_bib404.service.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/")
public class ConsultarHistorial {

	@Autowired
	@Qualifier("recursoBibliotecarioRepository")
	private RecursoBibliotecarioRepository rbr;
	
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioServiceImpl usuarioImp;
	
	@Autowired
	@Qualifier("bibliotecaRepository")
	private BibliotecaRepository br;
	
	@Autowired
	@Qualifier("actualizarPerfil")
		private actualizarPerfil actPerfil;
	
	@Autowired
	@Qualifier("categoriaRepository")
	private CategoriaRepository cat;
	@GetMapping("/presentacion")
	public String presentacion(Model model,HttpServletRequest request) {

	
	
	
	
	return Template.CONSULTAR;
	}
	
	
	@GetMapping("/hoy")
	public String diaHoy(Model model,HttpServletRequest request) {
	
		
		HttpSession session = request.getSession();
		Usuario user =(Usuario) session.getAttribute(Constante.USER);
		Biblioteca busca=new Biblioteca();
		Date fecha = new Date();
		int anio = fecha.getYear();
		fecha.setYear(anio);
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yy");
		String fecha_actual = formateador.format(fecha);
		
		System.out.println("fecha"+fecha_actual);
		busca=br.buscarBiblioteca(user.getBiblioteca().getId());
		List<Usuario> lrb= new ArrayList<Usuario>();
	lrb=actPerfil.obtenerRecursosHoy(fecha_actual, busca.getId());
	List<Categoria> categoria = new ArrayList<Categoria>();
	categoria=cat.obtenerCategoria(fecha_actual, busca.getId());
	model.addAttribute("usuarios", lrb);
	model.addAttribute("categorias", categoria);
	model.addAttribute("exito",true);
	model.addAttribute("mensaje","usuarios que se registraron en esta biblioteca Hoy");
	model.addAttribute("mensaje2","categorias registradas en esta biblioteca Hoy");
	return Template.CONSULTAR;
	
	
	}
	
	
	
	@GetMapping("/ayer")
	public String diaAyer(Model model,HttpServletRequest request) {
	
		HttpSession session = request.getSession();
		Usuario user =(Usuario) session.getAttribute(Constante.USER);
		Biblioteca busca=new Biblioteca();
		Date fecha = new Date();
		int anio = fecha.getYear();
		int dia= fecha.getDate()-1;
		fecha.setYear(anio);
		fecha.setDate(dia);
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yy");
		String fecha_actual = formateador.format(fecha);
		
		busca=br.buscarBiblioteca(user.getBiblioteca().getId());
		List<Usuario> lrb= new ArrayList<Usuario>();
	lrb=actPerfil.obtenerRecursosHoy(fecha_actual, busca.getId());
	List<Categoria> categoria = new ArrayList<Categoria>();
	categoria=cat.obtenerCategoria(fecha_actual, busca.getId());
	model.addAttribute("usuarios", lrb);
	model.addAttribute("categorias", categoria);
	model.addAttribute("exito",true);
	model.addAttribute("mensaje","usuarios que se registraron en esta biblioteca Hoy");
	model.addAttribute("mensaje2","categorias registradas en esta biblioteca Hoy");
	return Template.CONSULTAR;
		
	
	}
	
	
	
	
}