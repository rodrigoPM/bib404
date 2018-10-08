package com.bib404.system_bib404.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.model.Usuario;

@Controller
@RequestMapping("/")
public class IndexBib404Controller {
	
//	@GetMapping("/")
//	public String paginaInicioSistema(Model model) {
//		return Template.INDEX_BIB_X;
//	}
	
	
//	@GetMapping("/")
//	public String redirecionar() {
//		return "redirect:/bib_example";
//	}
	
	@GetMapping("/")
	public String indexAnonimo(HttpServletRequest request, Model model)  throws ServletException, IOException  {
		HttpSession sesion = request.getSession();
		model.addAttribute("titulo","BIB404");
		
		if(sesion.getAttribute(Template.USER)==null) {
			return Template.INDEX_ANONIMO;
		}else {
			return "redirect:/index";
		}

	}
	
	@GetMapping("/index")
	public String indexUsuario(HttpServletRequest request, Model model)  throws ServletException, IOException  {
		HttpSession sesion = request.getSession();
		model.addAttribute("titulo","BIB404");		
		if(sesion.getAttribute(Template.USER)==null) {
			return "redirect:/";
		}else {
			model.addAttribute("user", sesion.getAttribute(Template.USER));
			return Template.INDEX_USER;
		}

	}
	
	
	
	
}
