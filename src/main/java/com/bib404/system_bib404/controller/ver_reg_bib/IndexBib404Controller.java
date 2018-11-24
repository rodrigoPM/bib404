	package com.bib404.system_bib404.controller.ver_reg_bib;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bib404.system_bib404.constant.Constante;
import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.constant.Url;
import com.bib404.system_bib404.controller.registrar_iniciar_Sesion.UsuarioController;
import com.bib404.system_bib404.entity.Biblioteca;
import com.bib404.system_bib404.entity.Municipio;
import com.bib404.system_bib404.entity.Usuario;
import com.bib404.system_bib404.service.EncriptadoPass;
import com.bib404.system_bib404.service.impl.BibliotecaServiceImpl;
import com.bib404.system_bib404.service.impl.Functions;
import com.bib404.system_bib404.service.impl.UsuarioServiceImpl;

@Controller
@RequestMapping(Url.INDEX_BIB404) // --> /
public class IndexBib404Controller {

	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioServiceImpl usuarioImp;

	@Autowired
	@Qualifier("bibliotecaServiceImpl")
	private BibliotecaServiceImpl bibliotecaService;

	@Autowired
	@Qualifier("Functions")
	private Functions funtions;

	@Autowired
	@Qualifier("encriptadoPass")
	private EncriptadoPass encriptado;


	@GetMapping("/")
	public ModelAndView indexAnonimo(HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView(Template.INDEX_BIB404);
		mav.addObject("titulo", "System BIB404");
		mav.addObject("urlHome", "/");
		HttpSession sesion = request.getSession();

		if(sesion.getAttribute(Constante.USER)==null) {
			mav.addObject("anonimo", true);
		}else {
			mav.addObject("user2", true);

			if (sesion.getAttribute("userup")==null)
			{

			mav.addObject("user", sesion.getAttribute(Constante.USER));
			}
			else {

				mav.addObject("user", sesion.getAttribute("userup"));

			}


		}
		if(bibliotecaService.listAllBibliotecas().size() >0) {
			mav.addObject("bib", true);
			mav.addObject("bibliotecas", bibliotecaService.listAllBibliotecas());
		}else {
			mav.addObject("bib", false);
		}


		return mav;
	}

	@GetMapping("{id_bib}")
	public String redirectBib(@PathVariable("id_bib") int id_bib) {
		return "redirect:/bib404/"+id_bib;
	}



	@GetMapping("/index")
	public String indexUsuario(HttpServletRequest request, Model model)  throws ServletException, IOException  {
		HttpSession sesion = request.getSession();
		model.addAttribute("titulo","BIB404");
		if(sesion.getAttribute(Constante.USER)==null) {
			return "redirect:/";
		}else {

			model.addAttribute("user", sesion.getAttribute(Constante.USER));
			return "redirect:/";
		}

	}


	@GetMapping("/biblioteca")
	public String biblioteca(HttpServletRequest request, Model model)  throws ServletException, IOException  {
		String bib ="biblioteca";
		if(funtions.isSuperUserBIB404(request)){
			bib = "redirect:/solicitar/recibir";
		}else {
			if (funtions.isAdmin(request)) {
				bib = "redirect:/solicitar/biblioteca";
			}else {
				bib = "redirect:/solicitar/biblioteca";
			}
		}
		return bib;
	}


}
