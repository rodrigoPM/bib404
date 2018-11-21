package com.bib404.system_bib404.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.service.impl.BibliotecaServiceImpl;
import com.bib404.system_bib404.service.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/")
public class GestionUsuarioController {
	
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@Autowired
	@Qualifier("bibliotecaServiceImpl")
	private BibliotecaServiceImpl bibliotecaServiceImpl;

	@RequestMapping("/bib404/{id_bib}/gestion_usuario")
	public ModelAndView listUsuarios(@PathVariable("id_bib") int id_bib, Model model, HttpServletRequest request)  throws ServletException, IOException  {
		
		ModelAndView mav = new ModelAndView(Template.GESTION_USUARIO);
		mav.addObject("name_bib", bibliotecaServiceImpl.findById(id_bib).getNombre_biblioteca());
		if(usuarioServiceImpl.listUsuarioBib(id_bib).size()>0) {
			mav.addObject("usuarios", usuarioServiceImpl.listUsuarioBib(id_bib));
		}
		
		mav.addObject("eliminarUsuario", "/bib404/"+id_bib+"/gestion_usuario/eliminar");
		
		return mav;
	}

}
