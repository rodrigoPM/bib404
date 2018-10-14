package com.bib404.system_bib404.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.entity.Biblioteca;
import com.bib404.system_bib404.entity.Municipio;
import com.bib404.system_bib404.service.impl.BibliotecaServiceImpl;
import com.bib404.system_bib404.service.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/")
public class IndexBib404Controller {
	
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioServiceImpl usuarioImp;
	
	@Autowired
	@Qualifier("bibliotecaServiceImpl")
	private BibliotecaServiceImpl bibliotecaService;
	
	@GetMapping("/")
	public ModelAndView indexAnonimo(HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView(Template.INDEX_BIB404);
		mav.addObject("titulo", "System BIB404");
		mav.addObject("bibliotecas", bibliotecaService.listAllBibs());
		System.out.println("numero de elementos: "+bibliotecaService.listAllBibs().size());
		return mav;
	}
	@GetMapping("/index")
	public String indexUsuario(HttpServletRequest request, Model model)  throws ServletException, IOException  {
		HttpSession sesion = request.getSession();
		model.addAttribute("titulo","BIB404");		
		if(sesion.getAttribute(Template.USER)==null) {
			return "redirect:/";
		}else {
			model.addAttribute("user", sesion.getAttribute(Template.USER));
			return Template.INDEX_USER;
		}

	}
	
	@GetMapping("/biblioteca")
	public String biblioteca(HttpServletRequest request, Model model)  throws ServletException, IOException  {
		String bib ="biblioteca";
		List<Municipio> municipios = usuarioImp.listMunicipios();
		model.addAttribute("biblioteca",new Biblioteca());
		model.addAttribute("municipios",municipios);
		return bib;
	}
	

	@PostMapping("/addBib")
	public String insertBib(@Valid @ModelAttribute("biblioteca") Biblioteca bib, @ModelAttribute("mun") String mun_id) {
		String ret="";
		Log log = LogFactory.getLog(UsuarioController.class);
		log.info(""+bib.getId());
		log.info(bib.getNombre_biblioteca());
		log.info(bib.getCodigo_biblioteca());
		int id_mun = Integer.parseInt(mun_id);
		int v=usuarioImp.addBiblio(bib, id_mun);
		if (v==0) {
			ret="redirect:/biblioteca";
		}else {
			ret ="redirect:/";
		}
		return ret;
	}	
	
	
	
}
