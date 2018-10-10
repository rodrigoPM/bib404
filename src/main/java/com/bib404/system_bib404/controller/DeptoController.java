package com.bib404.system_bib404.controller;

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

import com.bib404.system_bib404.entity.Departamento;
import com.bib404.system_bib404.service.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/departamento")
public class DeptoController {
	
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioServiceImpl usuarioImp;
	
	@GetMapping("/")
	public String departamento(Model model) {
		String depto = "departamento";
		model.addAttribute("departamento",new Departamento());
		return depto;
	}
	
	@PostMapping("/addDpto")
	public String insertDpto(@Valid @ModelAttribute("departamento") Departamento depto) {
		String ret="";
		Log log = LogFactory.getLog(UsuarioController.class);
		log.info(""+depto.getId());
		log.info(depto.getNombre_departamento());
		log.info(depto.getZona_geografica());
		int v=usuarioImp.addDpto(depto);
		if (v==0) {
			ret="/departamento/";
		}else {
			ret ="redirect:/";
		}
		return ret;
	}
	
	
	
}










