package com.bib404.system_bib404.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.entity.Municipio;
import com.bib404.system_bib404.entity.Prestamo;
import com.bib404.system_bib404.model.PrestamoModel;
import com.bib404.system_bib404.service.impl.PrestamoServiceImpl;
import com.bib404.system_bib404.service.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/autorizar")
public class AutorizarController {
	@Autowired
	@Qualifier("prestamoServiceImpl")
	private PrestamoServiceImpl prestamoServiceImpl;
	
	@RequestMapping("/listPrestamos")
	public ModelAndView listPrestamos(HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView(Template.AUTORIZAR);
		mav.addObject("prestamos", prestamoServiceImpl.listPrestamos());
		return mav;
	}
	@GetMapping("/autorizado")
	public ModelAndView autorizado(HttpServletRequest request, Model model) throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView(Template.AUTORIZAR);
		mav.addObject("prestamos", prestamoServiceImpl.listPrestamos());
		model.addAttribute("mensaje", 0);
		return mav;
		
	}
	@GetMapping("/denegado")
	public ModelAndView denegado(HttpServletRequest request, Model model) throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView(Template.AUTORIZAR);
		mav.addObject("prestamos", prestamoServiceImpl.listPrestamos());
		model.addAttribute("mensaje", 1);
		return mav;
		
	}
	@GetMapping("/recibido")
	public ModelAndView recibido(HttpServletRequest request, Model model) throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView(Template.AUTORIZAR);
		mav.addObject("prestamos", prestamoServiceImpl.listPrestamos());
		model.addAttribute("mensaje", 2);
		return mav;
		
	}
	@PostMapping("/addPrestamo")
	public String addPrestamo(@ModelAttribute(name="prestamoModel") PrestamoModel prestamoModel) {
		return Template.AUTORIZAR;
	}

}
