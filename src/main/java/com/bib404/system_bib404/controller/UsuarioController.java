package com.bib404.system_bib404.controller;

import java.lang.ProcessBuilder.Redirect;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.model.Usuario;
import com.bib404.system_bib404.service.EncriptadoPass;
import com.bib404.system_bib404.service.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioServiceImpl usuarioImp;
	
	@Autowired
	@Qualifier("encriptadoPass")
	private EncriptadoPass encriptado;
	
	@RequestMapping("/listUser")
	public ModelAndView getAllUser() {
		ModelAndView mav = new ModelAndView(Template.USUARIOS);
		mav.addObject("usuarios", usuarioImp.listUsuario());
		return mav;
	}
	
	@GetMapping("/registrarse")
	public String registrarse(Model model) {
		model.addAttribute("titulo","BIB404-registrarse");
		return Template.REGISTRAR;
	}
	
	@PostMapping("/addUser")
	public String redireccion(@Valid @ModelAttribute("password") String password) {
		Log log = LogFactory.getLog(UsuarioController.class);
		log.info(password); //contraseña sin encriptar
		String pass = encriptado.Encriptar(password);
		log.info(pass); //contraseña encriptada
		String passDe = encriptado.Desencriptar(pass);
		log.info(passDe);
		return "redirect:/index";
	}
	
	
}
