package com.bib404.system_bib404.controller.gestionar_recursos_bib;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bib404.system_bib404.Repository.PerfilRepository;
import com.bib404.system_bib404.Repository.RecursoBibliotecarioRepository;
import com.bib404.system_bib404.Repository.TipoRecursoRepository;
import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.entity.Municipio;
import com.bib404.system_bib404.entity.RecursoBibliotecario;
import com.bib404.system_bib404.entity.TipoRecurso;
import com.bib404.system_bib404.entity.Usuario;

@Controller
@RequestMapping("/")
public class GestionarBIBController {
	
	@Autowired
	@Qualifier("TipoRecursoRepository")
	private TipoRecursoRepository trr;	
	
	
	@Autowired
	@Qualifier("recursoBibliotecarioRepository")
	private RecursoBibliotecarioRepository rbr;
	
	
	
	
	@GetMapping("/recursos")
	public String redirectPerfilForm(Model model,HttpServletRequest request) {

		List<TipoRecurso> tr = new ArrayList<TipoRecurso>();
		List<RecursoBibliotecario> lrb= new ArrayList<RecursoBibliotecario>();
		lrb=rbr.findAll();
		tr=trr.findAll();
		
		HttpSession sesion = request.getSession();
			    
		model.addAttribute("TipoRecurso", tr);
        model.addAttribute("recursoBib", lrb);

		return Template.GESTION;
		
	}
	
	
	@PostMapping("/addRecurso")
	public String addContra(@Valid@ModelAttribute("RecursoBibliotecario") RecursoBibliotecario recurso,@ModelAttribute("tipoR") String tipR, Model model,HttpServletRequest request) {
		HttpSession sesion = request.getSession();
		TipoRecurso tiporecurso;
		int id_tipo = Integer.parseInt(tipR);
		tiporecurso=trr.buscarRecurso(id_tipo);
		
		recurso.setTipo_recurso(tiporecurso);
	
		rbr.save(recurso);

		return "redirect:/recursos";
}
}