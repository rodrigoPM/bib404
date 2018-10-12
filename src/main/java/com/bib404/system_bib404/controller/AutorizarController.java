package com.bib404.system_bib404.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.service.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/autorizar")
public class AutorizarController {
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioServiceImpl usuarioImp;
	
	@RequestMapping("/listPrestamos")
	public ModelAndView getAllPrestamos(HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView(Template.AUTORIZAR);
//		mav.addObject("usuarios", usuarioImp.listUsuario());
		return mav;
	}
	@GetMapping("/autorizado")
	public String autorizado(Model model) {
		model.addAttribute("mensaje", 0);
		return Template.AUTORIZAR;
		
	}
	@GetMapping("/denegado")
	public String denegado(Model model) {
		model.addAttribute("mensaje", 1);
		return Template.AUTORIZAR;
		
	}
	@GetMapping("/recibido")
	public String recibido(Model model) {
		model.addAttribute("mensaje", 2);
		return Template.AUTORIZAR;
		
	}
}
