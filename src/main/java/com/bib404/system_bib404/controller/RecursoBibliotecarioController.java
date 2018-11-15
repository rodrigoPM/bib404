package com.bib404.system_bib404.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bib404.system_bib404.Repository.ConsultasRE;
import com.bib404.system_bib404.constant.Template;
@Controller
@RequestMapping("/recursoBibliotecario")
public class RecursoBibliotecarioController {
	
    @Autowired
    @Qualifier("consultasRE")
    ConsultasRE consultas;
    
	@GetMapping("/verRecurso")
	public ModelAndView verEspecifico(@RequestParam(name = "id", required = false) int id, HttpServletRequest request, Model model) {
        ModelAndView modelAndView = new ModelAndView(Template.RECURSOSESPECIFICOS);
        modelAndView.addObject("recursosEspecificos", consultas.findJoin(id));        
        return modelAndView;
	}
}
