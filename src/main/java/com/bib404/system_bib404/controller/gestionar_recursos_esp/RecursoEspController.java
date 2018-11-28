package com.bib404.system_bib404.controller.gestionar_recursos_esp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.constant.Url;
import com.bib404.system_bib404.entity.RecursoEspecifico;
import com.bib404.system_bib404.model.ObjectAux;
import com.bib404.system_bib404.service.impl.BibliotecaServiceImpl;
import com.bib404.system_bib404.service.impl.RecursoBibliotecarioServiceImpl;
import com.bib404.system_bib404.service.impl.RecursoEspecificoServiceImpl;
import com.bib404.system_bib404.service.impl.Functions;

@Controller
@RequestMapping(Url.GESTION_REC_ESP) //--> /bib404/{id_bib}/{id_rb}/recurso_especifico
public class RecursoEspController {

	@Autowired
	@Qualifier("Functions")
	private Functions funcion;

	@Autowired
	@Qualifier("bibliotecaServiceImpl")
	private BibliotecaServiceImpl biblioteca;

	@Autowired
	@Qualifier("recursoBibliotecarioServiceImpl")
	private RecursoBibliotecarioServiceImpl rb;

	@Autowired
	@Qualifier("recursoEspecificoServiceImpl")
	private RecursoEspecificoServiceImpl re;


	@RequestMapping("")
	public ModelAndView gestionRecursoEspecifico(@PathVariable("id_bib") int id_bib,@PathVariable("id_rb") int id_rb,HttpServletRequest request) {
		if(!biblioteca.existsBibById(id_bib) || !rb.existsRBById(id_rb)){
			ModelAndView mav =new ModelAndView("redirect:/");
			return mav;
		}
		HttpSession session = request.getSession();
		ModelAndView mav =new ModelAndView(Template.GESTION_REC_ESP);
		mav.addObject("titulo", "Recursos Especificos");
//		mav.addObject("url_rec_esp", "/bib404/"+id_bib+"/"+id_rb+"/recurso_especifico");
		mav.addObject("name_bib", biblioteca.findById(id_bib).getNombre_biblioteca());
		mav.addObject("crearRecEsp", "/bib404/"+id_bib+"/"+id_rb+"/recurso_especifico/nueva"); //action del form crear categoria
		mav.addObject("borrarRecEsp", "/bib404/"+id_bib+"/"+id_rb+"/recurso_especifico/borrar"); //action del form eliminar categoria
		mav.addObject("recEspModel", new RecursoEspecifico());
		mav.addObject("objectAux", new ObjectAux());

		if(re.listAllRecEsp(id_rb).size()>0) {
			mav.addObject("recEsps", re.listAllRecEsp(id_rb));
		}

		if(funcion.isAnyUser(request)) {
			mav.addObject("isUser", true);
		}else {
			mav.addObject("isNoUser", true);
			System.out.println("No se valida si es usuario");
		}
		return mav;
	}

	@PostMapping("/nueva")
	public String crearRecEsp(@ModelAttribute(name="recEspModel") RecursoEspecifico rec_esp,@PathVariable("id_bib") int id_bib,
																			@PathVariable("id_rb") int id_rb,HttpServletRequest request ) {
			return "redirect:/";
	}
	@PostMapping("/borrar")
	public String borrarRecEsp(@ModelAttribute(name="objectAux") ObjectAux ox,@PathVariable("id_bib") int id_bib,
																			@PathVariable("id_rb") int id_rb,HttpServletRequest request ){
			return "redirect:/";
	}
}
