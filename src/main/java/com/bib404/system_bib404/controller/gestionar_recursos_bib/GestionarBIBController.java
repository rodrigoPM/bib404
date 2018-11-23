package com.bib404.system_bib404.controller.gestionar_recursos_bib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.entity.Usuario;

@Controller
@RequestMapping("/")
public class GestionarBIBController {
	
	
	@GetMapping("/recursos")
	public String redirectPerfilForm(Model model,HttpServletRequest request) {

		
		
		HttpSession sesion = request.getSession();
			    
	    

		return Template.GESTION;
		
	}


}
