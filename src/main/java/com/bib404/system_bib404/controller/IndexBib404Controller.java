package com.bib404.system_bib404.controller;

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
	public ModelAndView indexAnonimo() {
		ModelAndView mav = new ModelAndView(Template.INDEX_ANONIMO);
		mav.addObject("titulo","BIB404");
		return mav;
	}
	
	@GetMapping("/registrarse")
	public String registrarse(Model model) {
		model.addAttribute("titulo","BIB404-registrarse");
		model.addAttribute("usuario", new Usuario());
		return Template.REGISTRAR;
	}
	
	@PostMapping("/addUser")
	public ModelAndView redireccion(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult bind ) {
		ModelAndView mav = new ModelAndView();
		if (bind.hasErrors()) {
			mav.addObject("titulo",usuario.getNombre());
			mav.setViewName(Template.REGISTRAR);
		}else {
			mav.setViewName(Template.INDEX_USER);
			mav.addObject("usuario",usuario);
			mav.addObject("titulo","BIB404");
		}
		return mav;
	}
	
	
	
}
