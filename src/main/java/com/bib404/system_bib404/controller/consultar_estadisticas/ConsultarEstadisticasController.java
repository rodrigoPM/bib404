package com.bib404.system_bib404.controller.consultar_estadisticas;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bib404.system_bib404.Repository.ConsultasRE;
import com.bib404.system_bib404.constant.Constante;
import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.entity.Biblioteca;
import com.bib404.system_bib404.entity.Usuario;
import com.bib404.system_bib404.model.Graf;
import com.bib404.system_bib404.service.impl.Functions;
import com.bib404.system_bib404.service.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/consultarEstadisticas")
public class ConsultarEstadisticasController {
    @Autowired
    @Qualifier("consultasRE")
    ConsultasRE consultas;	
	@Autowired
	@Qualifier("Functions")
	private Functions funtions;
	
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioServiceImpl usuarioImp;
	
	@GetMapping("/estadisticasGenerales")
	public ModelAndView estadisticas(HttpServletRequest request)  throws ServletException, IOException  {
		    ModelAndView mav = new ModelAndView();
	        HttpSession session = request.getSession();
	        Usuario admin =(Usuario) session.getAttribute(Constante.USER);
		    List<Usuario> usuarios = consultas.listaUsuarios(admin.getBiblioteca());
			int cantidad = usuarios.size();
			SimpleDateFormat formateador = new SimpleDateFormat("yyyyMMdd");
			Date f = new Date();
			String f4 = formateador.format(f);
			System.out.println(f4);
			ArrayList<Graf> graf = usuarioImp.findUserByYear(f4,31, 365, 12,admin);
			mav.addObject("usuarios", usuarios);
			mav.addObject("cantidad", cantidad);
			mav.addObject("graf", graf);
			mav.addObject("tipo", "meses");
			Date fecha = new Date();
			int mes = fecha.getMonth() - 1;
			fecha.setMonth(mes);
			SimpleDateFormat formatos = new SimpleDateFormat("yyyy-MM-dd");
			String fecha_actual = formatos.format(new Date());
			String fecha_inicial = formatos.format(fecha); 
			mav.addObject("fecha_inicial", fecha_inicial);
			mav.addObject("fecha_actual", fecha_actual);
			mav.addObject("biblioteca", admin.getBiblioteca().getNombre_biblioteca());
			mav.setViewName(Template.CONSULTAR_ESTADISTICAS);
		    return mav;
	}
    @GetMapping("/ultimasemana")
	public ModelAndView estadisticassemana(HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView();
        HttpSession session = request.getSession();
        Usuario admin =(Usuario) session.getAttribute(Constante.USER);
	    List<Usuario> usuarios = consultas.listaUsuarios(admin.getBiblioteca());
			int cantidad = usuarios.size();
			SimpleDateFormat formateador = new SimpleDateFormat("yyyyMMdd");
			Date f = new Date();
			String f4 = formateador.format(f);
			System.out.println(f4);
			ArrayList<Graf> graf = usuarioImp.findUserByDate(f4, 7,admin);
			mav.addObject("usuarios", usuarios);
			mav.addObject("graf", graf);
			mav.addObject("tipo", "dias");
			Date fecha = new Date();
			int mes = fecha.getMonth() - 1;
			fecha.setMonth(mes);
			SimpleDateFormat formatos = new SimpleDateFormat("yyyy-MM-dd");
			String fecha_actual = formatos.format(new Date());
			String fecha_inicial = formatos.format(fecha); 
			mav.addObject("fecha_inicial", fecha_inicial);
			mav.addObject("fecha_actual", fecha_actual);
			mav.addObject("cantidad", cantidad);
			mav.addObject("biblioteca", admin.getBiblioteca().getNombre_biblioteca());
			mav.setViewName(Template.CONSULTAR_ESTADISTICAS);
		return mav;
	}
	
	@GetMapping("/ultimomes")
	public ModelAndView estadisticasmes(HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView();
		    HttpSession session = request.getSession();
		    Usuario admin =(Usuario) session.getAttribute(Constante.USER);
			List<Usuario> usuarios = consultas.listaUsuarios(admin.getBiblioteca());
			int cantidad = usuarios.size();
			SimpleDateFormat formateador = new SimpleDateFormat("yyyyMMdd");
			Date f = new Date();
			String f4 = formateador.format(f);
			System.out.println(f4);
			ArrayList<Graf> graf = usuarioImp.findUserByYear(f4, 7, 31, 5,admin);
			mav.addObject("usuarios", usuarios);
			mav.addObject("graf", graf);
			mav.addObject("tipo", "semanas");
			Date fecha = new Date();
			int mes = fecha.getMonth() - 1;
			fecha.setMonth(mes);
			SimpleDateFormat formatos = new SimpleDateFormat("yyyy-MM-dd");
			String fecha_actual = formatos.format(new Date());
			String fecha_inicial = formatos.format(fecha); 
			mav.addObject("fecha_inicial", fecha_inicial);
			mav.addObject("fecha_actual", fecha_actual);
			mav.addObject("cantidad", cantidad);
			mav.addObject("biblioteca", admin.getBiblioteca().getNombre_biblioteca());
			mav.setViewName(Template.CONSULTAR_ESTADISTICAS);
		return mav;
	}
	
	@GetMapping("ultimomes3")
	public ModelAndView estadisticasmes3(HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView();
	    HttpSession session = request.getSession();
	    Usuario admin =(Usuario) session.getAttribute(Constante.USER);
		List<Usuario> usuarios = consultas.listaUsuarios(admin.getBiblioteca());
			int cantidad = usuarios.size();
			SimpleDateFormat formateador = new SimpleDateFormat("yyyyMMdd");
			Date f = new Date();
			String f4 = formateador.format(f);
			System.out.println(f4);
			ArrayList<Graf> graf = usuarioImp.findUserByYear(f4, 7, 93, 13,admin);
			mav.addObject("usuarios", usuarios);
			mav.addObject("graf", graf);
			mav.addObject("tipo", "semanas");
			Date fecha = new Date();
			int mes = fecha.getMonth() - 1;
			fecha.setMonth(mes);
			SimpleDateFormat formatos = new SimpleDateFormat("yyyy-MM-dd");
			String fecha_actual = formatos.format(new Date());
			String fecha_inicial = formatos.format(fecha); 
			mav.addObject("fecha_inicial", fecha_inicial);
			mav.addObject("fecha_actual", fecha_actual);
			mav.addObject("cantidad", cantidad);
			mav.addObject("biblioteca", admin.getBiblioteca().getNombre_biblioteca());
			mav.setViewName(Template.CONSULTAR_ESTADISTICAS);
		return mav;
	}

}
