package com.bib404.system_bib404.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
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
	public ModelAndView getAllUser(HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView(Template.AUTORIZAR);
//		mav.addObject("usuarios", usuarioImp.listUsuario());
		return mav;
	}
}
