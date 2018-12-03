package com.bib404.system_bib404.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.entity.Municipio;
import com.bib404.system_bib404.entity.Prestamo;
import com.bib404.system_bib404.entity.Usuario;
import com.bib404.system_bib404.model.PrestamoModel;
import com.bib404.system_bib404.service.impl.BibliotecaServiceImpl;
import com.bib404.system_bib404.service.impl.Functions;
import com.bib404.system_bib404.service.impl.PrestamoServiceImpl;
import com.bib404.system_bib404.service.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/autorizar")
public class AutorizarController {
	@Autowired
	@Qualifier("Functions")
	private Functions funcion;
	
	@Autowired
	@Qualifier("prestamoServiceImpl")
	private PrestamoServiceImpl prestamoServiceImpl;
	
	@Autowired
	@Qualifier("bibliotecaServiceImpl")
	private BibliotecaServiceImpl bibliotecaServiceImpl;
	
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@RequestMapping("/listPrestamos")
	public ModelAndView listPrestamos(Model model, @RequestParam(name="id_bib") int id_bib, HttpServletRequest request)  throws ServletException, IOException  {
		if (!funcion.isAdmin(request)) {
			ModelAndView mav = new ModelAndView("redirect:/");
			return mav;
		}
		ModelAndView mav = new ModelAndView(Template.AUTORIZAR);
		mav.addObject("name_bib", bibliotecaServiceImpl.findById(id_bib).getNombre_biblioteca());
		mav.addObject("bib", bibliotecaServiceImpl.findById(id_bib).getId());
		if(prestamoServiceImpl.listPrestamos(id_bib).size()>0) {
			mav.addObject("prestamos", prestamoServiceImpl.listPrestamos(id_bib));
		
			model.addAttribute("prestado", 1);
		}
		else {
			model.addAttribute("prestado", 1);
			model.addAttribute("mensaje", 3);
		}
		return mav;
	}
	@RequestMapping("/listPrestados")
	public ModelAndView listPrestados(Model model, @RequestParam(name="id_bib") int id_bib, HttpServletRequest request)  throws ServletException, IOException  {
		
		
		ModelAndView mav = new ModelAndView(Template.AUTORIZAR);
		mav.addObject("name_bib", bibliotecaServiceImpl.findById(id_bib).getNombre_biblioteca());
		mav.addObject("bib", bibliotecaServiceImpl.findById(id_bib).getId());
		if(prestamoServiceImpl.listPrestados(id_bib).size()>0) {
			mav.addObject("prestamos", prestamoServiceImpl.listPrestados(id_bib));
		
			model.addAttribute("prestado", 2);
		}
		else {
			model.addAttribute("prestado", 2);
			model.addAttribute("mensaje", 3);
		}
		
		return mav;
	}
	@RequestMapping("/listPrestadosMora")
	public ModelAndView listPrestadosMora(Model model, @RequestParam(name="id_bib") int id_bib, HttpServletRequest request)  throws ServletException, IOException  {

		ModelAndView mav = new ModelAndView(Template.AUTORIZAR);
		mav.addObject("name_bib", bibliotecaServiceImpl.findById(id_bib).getNombre_biblioteca());
		mav.addObject("bib", bibliotecaServiceImpl.findById(id_bib).getId());
		if(prestamoServiceImpl.listPrestadosMora(id_bib).size()>0) {
			mav.addObject("prestamos", prestamoServiceImpl.listPrestadosMora(id_bib));
		
			model.addAttribute("prestado", 3);
		}
		else {
			model.addAttribute("prestado", 3);
			model.addAttribute("mensaje", 3);
		}

		return mav;
	}
	@RequestMapping("/listDenegados")
	public ModelAndView listDenegados(Model model, @RequestParam(name="id_bib") int id_bib, HttpServletRequest request)  throws ServletException, IOException  {
		
		ModelAndView mav = new ModelAndView(Template.DENEGADOS);
		mav.addObject("name_bib", bibliotecaServiceImpl.findById(id_bib).getNombre_biblioteca());
		mav.addObject("bib", bibliotecaServiceImpl.findById(id_bib).getId());
		if(prestamoServiceImpl.listDenegados(id_bib).size()>0) {
			mav.addObject("prestamos", prestamoServiceImpl.listDenegados(id_bib));
			model.addAttribute("prestado", 1);
		}
		else {
			model.addAttribute("prestado", 1);
			model.addAttribute("mensaje", 3);
		}		
		return mav;
	}
	@RequestMapping("/listRecibidos")
	public ModelAndView listRecibidos(Model model, @RequestParam(name="id_bib") int id_bib, HttpServletRequest request)  throws ServletException, IOException  {
		
		ModelAndView mav = new ModelAndView(Template.DENEGADOS);
		mav.addObject("name_bib", bibliotecaServiceImpl.findById(id_bib).getNombre_biblioteca());
		mav.addObject("bib", bibliotecaServiceImpl.findById(id_bib).getId());
		if(prestamoServiceImpl.listRecibidos(id_bib).size()>0) {
			mav.addObject("prestamos", prestamoServiceImpl.listRecibidos(id_bib));
			model.addAttribute("prestado", 2);
		}
		else {
			model.addAttribute("prestado", 2);
			model.addAttribute("mensaje", 3);
		}	
		
		return mav;
	}
	@GetMapping("/autorizado")
	public ModelAndView autorizado(@RequestParam(name="id", required=false)int id, @RequestParam(name="id_bib") int id_bib, Model model,
			@ModelAttribute(name="prestamoModel") PrestamoModel prestamoModel, HttpServletRequest request) throws ServletException, IOException  {
		PrestamoModel prestamo=new PrestamoModel();
		int val=2;
		if(id!=0) {
			prestamo=prestamoServiceImpl.findPrestamoByIdModel(id);
			prestamo.setEstado(val);
			prestamoServiceImpl.addPrestamo(prestamo);
			model.addAttribute("mensaje", 0);
			model.addAttribute("prestado", 1);
			ModelAndView mav = new ModelAndView(Template.AUTORIZAR);
			mav.addObject("prestamos", prestamoServiceImpl.listPrestamos(id_bib));
		}
		prestamo.setEstado(val);
		model.addAttribute("prestamoModel", prestamo);
		ModelAndView mav = new ModelAndView(Template.AUTORIZAR);
		mav.addObject("name_bib", bibliotecaServiceImpl.findById(id_bib).getNombre_biblioteca());
		mav.addObject("bib", bibliotecaServiceImpl.findById(id_bib).getId());
		mav.addObject("prestamos", prestamoServiceImpl.listPrestamos(id_bib));


		return mav;
		
	}
	@GetMapping("/denegado")
	public ModelAndView denegado(@RequestParam(name="id", required=false)int id, @RequestParam(name="id_bib") int id_bib, Model model,
			@ModelAttribute(name="prestamoModel") PrestamoModel prestamoModel, HttpServletRequest request) throws ServletException, IOException  {
		PrestamoModel prestamo=new PrestamoModel();
		int val=0;
		if(id!=0) {
			prestamo=prestamoServiceImpl.findPrestamoByIdModel(id);
			prestamo.setEstado(val);
			prestamoServiceImpl.addPrestamo(prestamo);
			model.addAttribute("mensaje", 1);
			model.addAttribute("prestado", 1);
			ModelAndView mav = new ModelAndView(Template.AUTORIZAR);
			mav.addObject("prestamos", prestamoServiceImpl.listPrestamos(id_bib));
		}
		prestamo.setEstado(val);
		model.addAttribute("prestamoModel", prestamo);
		ModelAndView mav = new ModelAndView(Template.AUTORIZAR);
		mav.addObject("name_bib", bibliotecaServiceImpl.findById(id_bib).getNombre_biblioteca());
		mav.addObject("bib", bibliotecaServiceImpl.findById(id_bib).getId());
		mav.addObject("prestamos", prestamoServiceImpl.listPrestamos(id_bib));


		return mav;
		
	}
	@GetMapping("/recibido")
	public ModelAndView recibido(@RequestParam(name="id", required=false)int id, @RequestParam(name="id_bib") int id_bib, Model model,
			@ModelAttribute(name="prestamoModel") PrestamoModel prestamoModel, HttpServletRequest request) throws ServletException, IOException  {
		PrestamoModel prestamo=new PrestamoModel();
		
		int val=3;
		if(id!=0) {
			prestamo=prestamoServiceImpl.findPrestamoByIdModel(id);
			prestamo.setEstado(val);
			prestamo.setFecha_entrega(new Date());
			prestamoServiceImpl.addPrestamo(prestamo);
			model.addAttribute("mensaje", 2);
			model.addAttribute("prestado", 2);
			

		}
		prestamo.setEstado(val);
		prestamo.setFecha_entrega(new Date());
		model.addAttribute("prestamoModel", prestamo);
		ModelAndView mav = new ModelAndView(Template.AUTORIZAR);
		mav.addObject("name_bib", bibliotecaServiceImpl.findById(id_bib).getNombre_biblioteca());
		mav.addObject("bib", bibliotecaServiceImpl.findById(id_bib).getId());
		mav.addObject("prestamos", prestamoServiceImpl.listPrestamos(id_bib));
		model.addAttribute("prestado",2);


		return mav;
		
	}
	@GetMapping("/recibidoMora")
	public ModelAndView recibidoMora(@RequestParam(name="id", required=false)int id, @RequestParam(name="id_bib") int id_bib, Model model,
			@ModelAttribute(name="prestamoModel") PrestamoModel prestamoModel, HttpServletRequest request) throws ServletException, IOException  {
		PrestamoModel prestamo=new PrestamoModel();
		int val=3;
		if(id!=0) {
			prestamo=prestamoServiceImpl.findPrestamoByIdModel(id);
			prestamo.setEstado(val);
			prestamo.setFecha_entrega(new Date());
			prestamoServiceImpl.addPrestamo(prestamo);
			model.addAttribute("mensaje", 2);
			model.addAttribute("prestado", 3);
			ModelAndView mav = new ModelAndView(Template.AUTORIZAR);
			mav.addObject("prestamos", prestamoServiceImpl.listPrestamos(id_bib));
		}
		prestamo.setEstado(val);
		prestamo.setFecha_entrega(new Date());
		model.addAttribute("prestamoModel", prestamo);
		ModelAndView mav = new ModelAndView(Template.AUTORIZAR);
		mav.addObject("name_bib", bibliotecaServiceImpl.findById(id_bib).getNombre_biblioteca());
		mav.addObject("bib", bibliotecaServiceImpl.findById(id_bib).getId());
		mav.addObject("prestamos", prestamoServiceImpl.listPrestamos(id_bib));

		return mav;
		
	}

	@PostMapping("/addPrestamo")
	public String addPrestamo(@ModelAttribute(name="prestamoModel") PrestamoModel prestamoModel) {
		prestamoServiceImpl.addPrestamo(prestamoModel);
		return Template.AUTORIZAR;
	}
	@PostMapping("/buscar_prestamos")
	public ModelAndView buscarPrestamos(@RequestParam("id_bib") int id_bib, Model model,
			@RequestParam(name = "str", required = false, defaultValue = "all") String str, HttpServletRequest request) {
		if (!bibliotecaServiceImpl.existsBibById(id_bib)) {
			ModelAndView mav = new ModelAndView("redirect:/");
			return mav;
		}
		ModelAndView mav = new ModelAndView(Template.AUTORIZAR);
		Usuario user= new Usuario();
		mav.addObject("name_bib", bibliotecaServiceImpl.findById(id_bib).getNombre_biblioteca());
		mav.addObject("bib", bibliotecaServiceImpl.findById(id_bib).getId());

		model.addAttribute("user", user);
		// buscar por nombre de usuario
		List<PrestamoModel> usuarios = prestamoServiceImpl.listPrestamos(id_bib);
		List<PrestamoModel> usuariosBuscados = new ArrayList<PrestamoModel>();
		if (!str.equals("all") && !str.equals("")) {
			for (PrestamoModel user2 : usuarios) {
				if (user2.getUsuario().getUsername().toLowerCase().contains(str.toLowerCase())) {// si el nombre de la categoria contiene str de busqueda
					usuariosBuscados.add(user2);
				}
			}
		}else{
			usuariosBuscados=usuarios;
		}

		if (usuariosBuscados.size() > 0) {
			mav.addObject("prestamos", usuariosBuscados);
			model.addAttribute("prestado", 1);
			mav.addObject("exito", "Los usuarios encontrados con el termino " + str.toUpperCase() + " son:");
		} else {
			model.addAttribute("prestado", 1);
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
	@PostMapping("/buscar_prestados")
	public ModelAndView buscarPrestados(@RequestParam("id_bib") int id_bib, Model model,
			@RequestParam(name = "str", required = false, defaultValue = "all") String str, HttpServletRequest request) {
		if (!bibliotecaServiceImpl.existsBibById(id_bib)) {
			ModelAndView mav = new ModelAndView("redirect:/");
			return mav;
		}
		ModelAndView mav = new ModelAndView(Template.AUTORIZAR);
		Usuario user= new Usuario();
		mav.addObject("name_bib", bibliotecaServiceImpl.findById(id_bib).getNombre_biblioteca());
		mav.addObject("bib", bibliotecaServiceImpl.findById(id_bib).getId());

		model.addAttribute("user", user);
		// buscar por nombre de usuario
		List<PrestamoModel> usuarios = prestamoServiceImpl.listPrestados(id_bib);
		List<PrestamoModel> usuariosBuscados = new ArrayList<PrestamoModel>();
		if (!str.equals("all") && !str.equals("")) {
			for (PrestamoModel user2 : usuarios) {
				if (user2.getUsuario().getUsername().toLowerCase().contains(str.toLowerCase())) {// si el nombre de la categoria contiene str de busqueda
					usuariosBuscados.add(user2);
				}
			}
		}else{
			usuariosBuscados=usuarios;
		}

		if (usuariosBuscados.size() > 0) {
			mav.addObject("prestamos", usuariosBuscados);
			model.addAttribute("prestado", 2);
			mav.addObject("exito", "Los usuarios encontrados con el termino " + str.toUpperCase() + " son:");
		} else {
			model.addAttribute("prestado", 2);
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
	@PostMapping("/buscar_prestadosMora")
	public ModelAndView buscarPrestadosMora(@RequestParam("id_bib") int id_bib, Model model,
			@RequestParam(name = "str", required = false, defaultValue = "all") String str, HttpServletRequest request) {
		if (!bibliotecaServiceImpl.existsBibById(id_bib)) {
			ModelAndView mav = new ModelAndView("redirect:/");
			return mav;
		}
		ModelAndView mav = new ModelAndView(Template.AUTORIZAR);
		Usuario user= new Usuario();
		mav.addObject("name_bib", bibliotecaServiceImpl.findById(id_bib).getNombre_biblioteca());
		mav.addObject("bib", bibliotecaServiceImpl.findById(id_bib).getId());

		model.addAttribute("user", user);
		// buscar por nombre de usuario
		List<PrestamoModel> usuarios = prestamoServiceImpl.listPrestadosMora(id_bib);
		List<PrestamoModel> usuariosBuscados = new ArrayList<PrestamoModel>();
		if (!str.equals("all") && !str.equals("")) {
			for (PrestamoModel user2 : usuarios) {
				if (user2.getUsuario().getUsername().toLowerCase().contains(str.toLowerCase())) {// si el nombre de la categoria contiene str de busqueda
					usuariosBuscados.add(user2);
				}
			}
		}else{
			usuariosBuscados=usuarios;
		}

		if (usuariosBuscados.size() > 0) {
			mav.addObject("prestamos", usuariosBuscados);
			model.addAttribute("prestado", 3);
			mav.addObject("exito", "Los usuarios encontrados con el termino " + str.toUpperCase() + " son:");
		} else {
			model.addAttribute("prestado", 3);
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
	@PostMapping("/buscar_denegados")
	public ModelAndView buscarDenegados(@RequestParam("id_bib") int id_bib, Model model,
			@RequestParam(name = "str", required = false, defaultValue = "all") String str, HttpServletRequest request) {
		if (!bibliotecaServiceImpl.existsBibById(id_bib)) {
			ModelAndView mav = new ModelAndView("redirect:/");
			return mav;
		}
		ModelAndView mav = new ModelAndView(Template.DENEGADOS);
		Usuario user= new Usuario();
		mav.addObject("name_bib", bibliotecaServiceImpl.findById(id_bib).getNombre_biblioteca());
		mav.addObject("bib", bibliotecaServiceImpl.findById(id_bib).getId());

		model.addAttribute("user", user);
		// buscar por nombre de usuario
		List<PrestamoModel> usuarios = prestamoServiceImpl.listDenegados(id_bib);
		List<PrestamoModel> usuariosBuscados = new ArrayList<PrestamoModel>();
		if (!str.equals("all") && !str.equals("")) {
			for (PrestamoModel user2 : usuarios) {
				if (user2.getUsuario().getUsername().toLowerCase().contains(str.toLowerCase())) {// si el nombre de la categoria contiene str de busqueda
					usuariosBuscados.add(user2);
				}
			}
		}else{
			usuariosBuscados=usuarios;
		}

		if (usuariosBuscados.size() > 0) {
			mav.addObject("prestamos", usuariosBuscados);
			model.addAttribute("prestado", 1);
			mav.addObject("exito", "Los usuarios encontrados con el termino " + str.toUpperCase() + " son:");
		} else {
			model.addAttribute("prestado", 1);
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
	@PostMapping("/buscar_recibidos")
	public ModelAndView buscarRecibidos(@RequestParam("id_bib") int id_bib, Model model,
			@RequestParam(name = "str", required = false, defaultValue = "all") String str, HttpServletRequest request) {
		if (!bibliotecaServiceImpl.existsBibById(id_bib)) {
			ModelAndView mav = new ModelAndView("redirect:/");
			return mav;
		}
		ModelAndView mav = new ModelAndView(Template.DENEGADOS);
		Usuario user= new Usuario();
		mav.addObject("name_bib", bibliotecaServiceImpl.findById(id_bib).getNombre_biblioteca());
		mav.addObject("bib", bibliotecaServiceImpl.findById(id_bib).getId());

		model.addAttribute("user", user);
		// buscar por nombre de usuario
		List<PrestamoModel> usuarios = prestamoServiceImpl.listRecibidos(id_bib);
		List<PrestamoModel> usuariosBuscados = new ArrayList<PrestamoModel>();
		if (!str.equals("all") && !str.equals("")) {
			for (PrestamoModel user2 : usuarios) {
				if (user2.getUsuario().getUsername().toLowerCase().contains(str.toLowerCase())) {// si el nombre de la categoria contiene str de busqueda
					usuariosBuscados.add(user2);
				}
			}
		}else{
			usuariosBuscados=usuarios;
		}

		if (usuariosBuscados.size() > 0) {
			mav.addObject("prestamos", usuariosBuscados);
			model.addAttribute("prestado", 2);
			mav.addObject("exito", "Los usuarios encontrados con el termino " + str.toUpperCase() + " son:");
		} else {
			model.addAttribute("prestado", 2);
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

}
