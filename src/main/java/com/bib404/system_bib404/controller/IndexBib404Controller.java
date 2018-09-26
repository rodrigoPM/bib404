package com.bib404.system_bib404.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bib404.system_bib404.constant.Template;

@Controller
@RequestMapping("/")
public class IndexBib404Controller {
	
	@GetMapping("/")
	public String paginaInicioSistema(Model model) {
		return Template.INDEX_BIB404;
	}
}
