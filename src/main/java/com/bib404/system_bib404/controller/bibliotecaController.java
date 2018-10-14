package com.bib404.system_bib404.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.service.impl.BibliotecaServiceImpl;
import com.bib404.system_bib404.service.impl.RecursoBibliotecarioServiceImpl;

@Controller
@RequestMapping("/bib404")
public class bibliotecaController {
	
	@Autowired
	@Qualifier("bibliotecaServiceImpl")
	private BibliotecaServiceImpl bibliotecaService;
	
	@Autowired
	@Qualifier("recursoBibliotecarioServiceImpl")
	private RecursoBibliotecarioServiceImpl rbService;

	@RequestMapping("/{id}")
	public ModelAndView inicioBibX(@PathVariable("id") int id_bib, HttpServletRequest request, Model model)  throws ServletException, IOException {
		ModelAndView mav = new ModelAndView(Template.INDEX_BIB_X);
		mav.addObject("biblioteca",bibliotecaService.findById(id_bib));
		if(bibliotecaService.findById(id_bib)==null) {
			System.out.println("No se encontro biblioteca");
			mav.setViewName(Template.INDEX_BIB404);
			return mav;
		}
		mav.addObject("name_bib", bibliotecaService.findById(id_bib).getNombre_biblioteca());
		mav.addObject("urlHome", "/bib404/"+bibliotecaService.findById(id_bib).getId());
		HttpSession sesion = request.getSession();
		if(sesion.getAttribute(Template.USER)!=null) {
			mav.addObject("isUser", true);
		}else {
			mav.addObject("isNoUser", true);
		}
		mav.addObject("rbs", rbService.listAllRBOfBib(id_bib));
		if(rbService.listAllRBOfBib(id_bib).size()==0) {
			mav.addObject("vacio","No se encontraron Recursos bibliotecarios");
		}
		System.out.println(rbService.listAllRBOfBib(id_bib).size());
		return mav;
	}
	
	
	
}
