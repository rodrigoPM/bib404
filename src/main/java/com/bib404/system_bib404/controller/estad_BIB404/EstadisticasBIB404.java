package com.bib404.system_bib404.controller.estad_BIB404;

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

import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.entity.Biblioteca;
import com.bib404.system_bib404.model.Graf;
import com.bib404.system_bib404.service.impl.Functions;
import com.bib404.system_bib404.service.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/estadisicas")
public class EstadisticasBIB404 {
	@Autowired
	@Qualifier("Functions")
	private Functions funtions;
	
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioServiceImpl usuarioImp;
	
	@GetMapping("/BIB404")
	public ModelAndView estadisticas(HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView();
		if (funtions.isSuperUser(request)) {
			mav.setViewName(Template.estadisticas);
		}else {
			List<Biblioteca> bibliotecas = usuarioImp.listBibliotecas();
			int cantidad = bibliotecas.size();
			SimpleDateFormat formateador = new SimpleDateFormat("yyyyMMdd");
			Date f = new Date();
			String f4 = formateador.format(f);
			System.out.println(f4);
			ArrayList<Graf> graf = usuarioImp.findBibByYear(f4,31, 365, 12);
			mav.addObject("bibliotecas", bibliotecas);
			mav.addObject("cantidad", cantidad);
			mav.addObject("graf", graf);
			mav.setViewName(Template.estadisticas);
		}
		mav.addObject("titulo", "System BIB404");
		return mav;
	}
	
	@GetMapping("/BIB404/ultimasemana")
	public ModelAndView estadisticassemana(HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView();
		if (funtions.isSuperUser(request)) {
			mav.setViewName(Template.estadisticas);
		}else {
			List<Biblioteca> bibliotecas = usuarioImp.listBibliotecas();
			int cantidad = bibliotecas.size();
			SimpleDateFormat formateador = new SimpleDateFormat("yyyyMMdd");
			Date f = new Date();
			String f4 = formateador.format(f);
			System.out.println(f4);
			ArrayList<Graf> graf = usuarioImp.findBibByDate(f4, 7);
			mav.addObject("bibliotecas", bibliotecas);
			mav.addObject("graf", graf);
			mav.addObject("cantidad", cantidad);
			mav.setViewName(Template.estadisticas);
		}
		mav.addObject("titulo", "System BIB404");
		return mav;
	}
	
	@GetMapping("/BIB404/ultimomes")
	public ModelAndView estadisticasmes(HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView();
		if (funtions.isSuperUser(request)) {
			mav.setViewName(Template.estadisticas);
		}else {
			List<Biblioteca> bibliotecas = usuarioImp.listBibliotecas();
			int cantidad = bibliotecas.size();
			SimpleDateFormat formateador = new SimpleDateFormat("yyyyMMdd");
			Date f = new Date();
			String f4 = formateador.format(f);
			System.out.println(f4);
			ArrayList<Graf> graf = usuarioImp.findBibByDate(f4, 31);
			mav.addObject("bibliotecas", bibliotecas);
			mav.addObject("graf", graf);
			mav.addObject("cantidad", cantidad);
			mav.setViewName(Template.estadisticas);
		}
		mav.addObject("titulo", "System BIB404");
		return mav;
	}
	
	@GetMapping("/BIB404/ultimomes3")
	public ModelAndView estadisticasmes3(HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView();
		if (funtions.isSuperUser(request)) {
			mav.setViewName(Template.estadisticas);
		}else {
			List<Biblioteca> bibliotecas = usuarioImp.listBibliotecas();
			int cantidad = bibliotecas.size();
			SimpleDateFormat formateador = new SimpleDateFormat("yyyyMMdd");
			Date f = new Date();
			String f4 = formateador.format(f);
			System.out.println(f4);
			ArrayList<Graf> graf = usuarioImp.findBibByYear(f4, 7, 93, 13);
			mav.addObject("bibliotecas", bibliotecas);
			mav.addObject("graf", graf);
			mav.addObject("cantidad", cantidad);
			mav.setViewName(Template.estadisticas);
		}
		mav.addObject("titulo", "System BIB404");
		return mav;
	}
	
	
	
	
}
