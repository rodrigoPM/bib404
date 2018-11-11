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
import com.bib404.system_bib404.service.impl.Functions;
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
	
	@Autowired
	@Qualifier("Functions")
	private Functions funcion;

	@RequestMapping("/{id}")
	public String inicioBibX(@PathVariable("id") int id_bib, HttpServletRequest request, Model model)  throws ServletException, IOException {
		model.addAttribute("biblioteca",bibliotecaService.findById(id_bib));
		if(id_bib<1) {
			System.out.println("No se encontro biblioteca");
			return "redirect:/";
		}
		model.addAttribute("name_bib", bibliotecaService.findById(id_bib).getNombre_biblioteca()); //nombre de la biblioteca
		model.addAttribute("urlHome", "/bib404/"+bibliotecaService.findById(id_bib).getId());  //url de la biblioteca 
		
		if(funcion.isAnyUser(request)) {
			model.addAttribute("isUser", true);
			System.out.println("Usuario simple");
		}else {
			model.addAttribute("isNoUser", true);
			System.out.println("No se valida si es usuario");
		}
		
		
		model.addAttribute("rbs", rbService.listAllRBOfBib(id_bib));  //listado de recursos bibliotecarios
		if(rbService.listAllRBOfBib(id_bib).size()==0) {
			model.addAttribute("vacio","No se encontraron Recursos bibliotecarios");
		}
		
		return Template.INDEX_BIB_X;
	}
	
	
	
}
