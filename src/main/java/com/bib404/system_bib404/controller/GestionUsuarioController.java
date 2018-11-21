package com.bib404.system_bib404.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bib404.system_bib404.constant.Template;

public class GestionUsuarioController {
	@RequestMapping("/gestion_usuario")
	public ModelAndView listPrestamos(Model model, HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView(Template.GESTION_USUARIO);
		/*mav.addObject("prestamos", prestamoServiceImpl.listPrestamos());
		model.addAttribute("prestado", 1);*/
		
		return mav;
	}

}
