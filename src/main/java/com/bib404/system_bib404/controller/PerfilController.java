package com.bib404.system_bib404.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.entity.Usuario;
import com.bib404.system_bib404.model.PerfilModel;
import com.bib404.system_bib404.service.EncriptadoPass;
import com.bib404.system_bib404.service.PerfilService;
import com.bib404.system_bib404.service.impl.UsuarioServiceImpl;


@Controller
@RequestMapping("/")
public class PerfilController {
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioServiceImpl usuarioImp;
@Autowired
@Qualifier("PerfilServiceImpl")
private PerfilService perfilservice;	

@Autowired
@Qualifier("encriptadoPass")
private EncriptadoPass encriptado;


@GetMapping("/perfil")
public String redirectPerfilForm(@ModelAttribute(Template.USER) Usuario user,Model model,HttpServletRequest request) {

	HttpSession userName = request.getSession();
    model.addAttribute("user", userName.getAttribute(Template.USER));
	model.addAttribute("bibliotecas", usuarioImp.listBibliotecas());
	model.addAttribute("municipios", usuarioImp.listMunicipios());

	return Template.PERFIL;

}
	
	
	
	
}
	

