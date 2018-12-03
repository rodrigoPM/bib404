package com.bib404.system_bib404.controller.ver_prestar_recurso;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.bib404.system_bib404.Repository.ConsultasRE;
import com.bib404.system_bib404.constant.Constante;
import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.entity.Prestamo;
import com.bib404.system_bib404.entity.Usuario;
import com.bib404.system_bib404.service.impl.Functions;
import com.bib404.system_bib404.service.impl.PrestamoServiceImpl;
import com.bib404.system_bib404.service.impl.RecursoEspecificoServiceImpl;
@Controller
@RequestMapping("/recursoBibliotecario")
public class RecursoBibliotecarioController {
	
    @Autowired
    @Qualifier("consultasRE")
    ConsultasRE consultas;

    @Autowired
    @Qualifier("Functions")
    Functions function;

    @Autowired
    @Qualifier("prestamoServiceImpl")
    PrestamoServiceImpl prestamoServiceImpl;

    @Autowired
    @Qualifier("recursoEspecificoServiceImpl")
    RecursoEspecificoServiceImpl recursoEspecificoServiceImp;
    
	@GetMapping("/verRecurso")
	public ModelAndView verEspecifico(@RequestParam(name = "id", required = false) int id, HttpServletRequest request, Model model) {
        ModelAndView modelAndView = new ModelAndView(Template.RECURSOSESPECIFICOS);
        modelAndView.addObject("recursosEspecificos", consultas.findJoin(id));
        modelAndView.addObject("recursoBib", consultas.ver(id));
        return modelAndView;
	}
	@PostMapping("/prestarRecurso")
	public ModelAndView prestarEspecifico(@RequestParam(name = "id", required = false) int id, HttpServletRequest request, Model model) {
        ModelAndView modelAndView = new ModelAndView(Template.SOLICITAR_PRESTAMO);
        if(function.isUser(request)) {
        	HttpSession session = request.getSession();
        	Usuario user = (Usuario) session.getAttribute(Constante.USER);
        	Date fecha = new Date();
        	Prestamo prestamo = new Prestamo();
        	prestamo.setEstado(1);
        	prestamo.setCantidad_recurso_bib(1);
        	prestamo.setFecha_entrega(fecha);
        	prestamo.setMora(false);
        	prestamo.setUsuario(user);
        	prestamo.setFecha_devolucion(fecha);
        	prestamo.setFecha_entrega(fecha);
        	prestamo.setRecursoEspecifico(recursoEspecificoServiceImp.findByIdRecurso(id));
        	prestamoServiceImpl.addPres(prestamo);
        	
        	modelAndView.addObject("mensaje", "Su solicitud de prestamo ha sido enviada");
        	modelAndView.addObject("prestamo", prestamo);
        	modelAndView.addObject("recurso", recursoEspecificoServiceImp.findByIdRecurso(id));
        	}
        else
        	modelAndView.addObject("mensaje", "Debes iniciar sesion o registrarte");
        return modelAndView;
	}
}
