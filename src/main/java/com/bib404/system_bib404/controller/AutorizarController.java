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
import org.springframework.web.bind.annotation.RequestParam;
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
	public ModelAndView listPrestamos(Model model, HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView(Template.AUTORIZAR);
		mav.addObject("prestamos", prestamoServiceImpl.listPrestamos());
		model.addAttribute("prestado", 1);
		
		return mav;
	}
	@RequestMapping("/listPrestados")
	public ModelAndView listPrestados(Model model, HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView(Template.AUTORIZAR);
		mav.addObject("prestamos", prestamoServiceImpl.listPrestados());
		model.addAttribute("prestado", 2);
		
		return mav;
	}
	@RequestMapping("/listPrestadosMora")
	public ModelAndView listPrestadosMora(Model model, HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView(Template.AUTORIZAR);
		mav.addObject("prestamos", prestamoServiceImpl.listPrestados());
		model.addAttribute("prestado", 3);
		
		return mav;
	}
	@RequestMapping("/listDenegados")
	public ModelAndView listDenegados(Model model, HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView(Template.DENEGADOS);
		mav.addObject("prestamos", prestamoServiceImpl.listDenegados());
		model.addAttribute("prestado", 1);
		
		return mav;
	}
	@RequestMapping("/listRecibidos")
	public ModelAndView listRecibidos(Model model, HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView(Template.DENEGADOS);
		mav.addObject("prestamos", prestamoServiceImpl.listRecibidos());
		model.addAttribute("prestado", 2);
		
		return mav;
	}
	@GetMapping("/autorizado")
	public ModelAndView autorizado(@RequestParam(name="id", required=false)int id, Model model,
			@ModelAttribute(name="prestamoModel") PrestamoModel prestamoModel, HttpServletRequest request) throws ServletException, IOException  {
		PrestamoModel prestamo=new PrestamoModel();
		int val=2;
		if(id!=0) {
			prestamo=prestamoServiceImpl.findPrestamoByIdModel(id);
			prestamo.setEstado(val);
			prestamoServiceImpl.addPrestamo(prestamo);
			model.addAttribute("mensaje", 0);
			model.addAttribute("prestado", 1);
			ModelAndView mav = new ModelAndView(Template.AUTORIZAR);
			mav.addObject("prestamos", prestamoServiceImpl.listPrestamos());
		}
		prestamo.setEstado(val);
		model.addAttribute("prestamoModel", prestamo);
		ModelAndView mav = new ModelAndView(Template.AUTORIZAR);
		mav.addObject("prestamos", prestamoServiceImpl.listPrestamos());

		return mav;
		
	}
	@GetMapping("/denegado")
	public ModelAndView denegado(@RequestParam(name="id", required=false)int id, Model model,
			@ModelAttribute(name="prestamoModel") PrestamoModel prestamoModel, HttpServletRequest request) throws ServletException, IOException  {
		PrestamoModel prestamo=new PrestamoModel();
		int val=0;
		if(id!=0) {
			prestamo=prestamoServiceImpl.findPrestamoByIdModel(id);
			prestamo.setEstado(val);
			prestamoServiceImpl.addPrestamo(prestamo);
			model.addAttribute("mensaje", 1);
			model.addAttribute("prestado", 1);
			ModelAndView mav = new ModelAndView(Template.AUTORIZAR);
			mav.addObject("prestamos", prestamoServiceImpl.listPrestamos());
		}
		prestamo.setEstado(val);
		model.addAttribute("prestamoModel", prestamo);
		ModelAndView mav = new ModelAndView(Template.AUTORIZAR);
		mav.addObject("prestamos", prestamoServiceImpl.listPrestamos());

		return mav;
		
	}
	@GetMapping("/recibido")
	public ModelAndView recibido(@RequestParam(name="id", required=false)int id, Model model,
			@ModelAttribute(name="prestamoModel") PrestamoModel prestamoModel, HttpServletRequest request) throws ServletException, IOException  {
		PrestamoModel prestamo=new PrestamoModel();
		int val=3;
		if(id!=0) {
			prestamo=prestamoServiceImpl.findPrestamoByIdModel(id);
			prestamo.setEstado(val);
			prestamoServiceImpl.addPrestamo(prestamo);
			model.addAttribute("mensaje", 2);
			model.addAttribute("prestado", 2);

		}
		prestamo.setEstado(val);
		model.addAttribute("prestamoModel", prestamo);
		ModelAndView mav = new ModelAndView(Template.AUTORIZAR);
		mav.addObject("prestamos", prestamoServiceImpl.listPrestamos());
		model.addAttribute("prestado",2);

		return mav;
		
	}
	@GetMapping("/recibidoMora")
	public ModelAndView recibidoMora(@RequestParam(name="id", required=false)int id, Model model,
			@ModelAttribute(name="prestamoModel") PrestamoModel prestamoModel, HttpServletRequest request) throws ServletException, IOException  {
		PrestamoModel prestamo=new PrestamoModel();
		int val=3;
		if(id!=0) {
			prestamo=prestamoServiceImpl.findPrestamoByIdModel(id);
			prestamo.setEstado(val);
			prestamoServiceImpl.addPrestamo(prestamo);
			model.addAttribute("mensaje", 2);
			model.addAttribute("prestado", 3);
			ModelAndView mav = new ModelAndView(Template.AUTORIZAR);
			mav.addObject("prestamos", prestamoServiceImpl.listPrestamos());
		}
		prestamo.setEstado(val);
		model.addAttribute("prestamoModel", prestamo);
		ModelAndView mav = new ModelAndView(Template.AUTORIZAR);
		mav.addObject("prestamos", prestamoServiceImpl.listPrestamos());
		

		return mav;
		
	}

	@PostMapping("/addPrestamo")
	public String addPrestamo(@ModelAttribute(name="prestamoModel") PrestamoModel prestamoModel) {
		prestamoServiceImpl.addPrestamo(prestamoModel);
		return Template.AUTORIZAR;
	}

}
