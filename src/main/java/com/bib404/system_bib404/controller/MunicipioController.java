package com.bib404.system_bib404.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.bib404.system_bib404.entity.Municipio;
import com.bib404.system_bib404.service.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/municipio")
public class MunicipioController {
	
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioServiceImpl usuarioImp;
	
	@GetMapping("/")
	public String departamento(Model model) {
		String depto = "municipio";
		List<Departamento> departamentos = usuarioImp.listDpto();
		model.addAttribute("Municipio",new Municipio());
		model.addAttribute("departamentos",departamentos);
		return depto;
	}
	
	@PostMapping("/addMun")
	public String insertDpto(@Valid @ModelAttribute("municipio") Municipio municipio, @ModelAttribute("departament") String departament) {
		String ret="";
		Log log = LogFactory.getLog(UsuarioController.class);
		log.info(""+municipio.getId());
		log.info(municipio.getNombre_municipio());
		log.info(departament);
		int id_dep = Integer.parseInt(departament);
		int v=usuarioImp.addMunicipio(municipio,id_dep);
		if (v==0) {
			ret="redirect:/municipio/";
		}else {
			ret ="redirect:/";
		}
		return ret;
	}
	
	
	
}


