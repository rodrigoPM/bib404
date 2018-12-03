package com.bib404.system_bib404.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.entity.Prestamo;
import com.bib404.system_bib404.entity.Usuario;
import com.bib404.system_bib404.service.impl.PrestamoServiceImpl;
import com.bib404.system_bib404.service.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/")
public class VerDigitalesController {
	@Autowired
	@Qualifier("prestamoServiceImpl")
	private PrestamoServiceImpl prestamoServiceImpl;
	
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@RequestMapping("/ver_digitales")
	public ModelAndView listPrestados(@RequestParam("id_user") int id_user, Model model, HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView(Template.DIGITALES);
		//mav.addObject("name_bib", bibliotecaServiceImpl.findById(id_bib).getNombre_biblioteca());
		if(prestamoServiceImpl.listPrestadosEspecificos(id_user).size()>0) {
			mav.addObject("prestamos", prestamoServiceImpl.listPrestadosEspecificos(id_user));
			mav.addObject("userid", usuarioServiceImpl.findById(id_user).getId());
		}
		else {
			model.addAttribute("error", "No tienes recusos digitales actualmente");
		}
		
		return mav;
	}
	@PostMapping("/buscar_digital")
	public ModelAndView buscar(@RequestParam("id_user") int id_user, Model model,
			@RequestParam(name = "str", required = false, defaultValue = "all") String str, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Template.DIGITALES);
		Usuario user= new Usuario();
		mav.addObject("userid", usuarioServiceImpl.findById(id_user).getId());

		model.addAttribute("user", user);
		// buscar por nombre recurso
		List<Prestamo> usuarios = prestamoServiceImpl.listPrestadosEspecificos(id_user);
		List<Prestamo> usuariosBuscados = new ArrayList<Prestamo>();
		if (!str.equals("all") && !str.equals("")) {
			for (Prestamo user2 : usuarios) {
				if (user2.getRecursoEspecifico().getDetalle_recurso().getRecurso_bib().getNombre_recurso_bib().toLowerCase().contains(str.toLowerCase())) {// si el nombre de la categoria contiene str de busqueda
					usuariosBuscados.add(user2);
				}
			}
		}else{
			usuariosBuscados=usuarios;
		}

		if (usuariosBuscados.size() > 0) {
			mav.addObject("prestamos", usuariosBuscados);
			mav.addObject("exito", "Los recursos digitales encontrados con el termino " + str.toUpperCase() + " son:");
		} else {
			mav.addObject("error", "No hay resultados que coincidan con la busqueda");
		}
/*
		if (funcion.isAnyUser(request)) {
			mav.addObject("isUser", true);
		} else {
			mav.addObject("isNoUser", true);
			System.out.println("No se valida si es usuario");
		}*/
		return mav;
	}
	@GetMapping("pdf")
	public ModelAndView pruebaPDF(HttpServletRequest request, @RequestParam("id_prestamo") int id_prestamo){
		ModelAndView mav=new ModelAndView(Template.PDF);
		mav.addObject("nombreFile", prestamoServiceImpl.pdf(id_prestamo));
		return mav;
	}

}
