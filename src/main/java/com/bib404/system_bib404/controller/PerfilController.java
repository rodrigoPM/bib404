package com.bib404.system_bib404.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bib404.system_bib404.Repository.PerfilRepository;
import com.bib404.system_bib404.Repository.UsuarioRepository;
import com.bib404.system_bib404.component.PerfilConverter;
import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.entity.Usuario;
import com.bib404.system_bib404.model.PerfilModel;
import com.bib404.system_bib404.service.EncriptadoPass;
import com.bib404.system_bib404.service.PerfilService;
import com.bib404.system_bib404.service.impl.UsuarioServiceImpl;


@Controller
@RequestMapping("/")
public class PerfilController {
	
	private static Log LOG= LogFactory.getLog(UsuarioController.class);
	@Autowired
	@Qualifier("perfilRepository")
		private PerfilRepository perfilRepository;
	@Autowired
	@Qualifier("usuarioRepository")
	private UsuarioRepository usuarioRep;
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioServiceImpl usuarioImp;
@Autowired
@Qualifier("PerfilServiceImpl")
private PerfilService perfilservice;	

@Autowired
@Qualifier("encriptadoPass")
private EncriptadoPass encriptado;
@Autowired
@Qualifier("perfilConverter")
private PerfilConverter perfilconverter;

@GetMapping("/perfil")
public String redirectPerfilForm(Model model,HttpServletRequest request) {
HttpSession session=request.getSession();
    model.addAttribute("user", session.getAttribute(Template.USER));
    //model.addAttribute("id",perfil.getId() );
	model.addAttribute("bibliotecas", usuarioImp.listBibliotecas());
	model.addAttribute("municipios", usuarioImp.listMunicipios());

	return Template.PERFIL;

}
	

@GetMapping("/editar")
public String editar(@RequestParam(name="id",required=false) int id,
		Model model) {
	PerfilModel perfil=perfilservice.findUsuarioByIdModel(id);
	Usuario user=new Usuario();
	user=perfilconverter.convertPerfilModel2Perfil(perfil);
	model.addAttribute("perfilModel",perfil );
	model.addAttribute("bibliotecas", usuarioImp.listBibliotecas());
	model.addAttribute("municipios", usuarioImp.listMunicipios());
	
	System.out.println("nombre de usuario   "+ perfil.getNombre()+" apellido"+perfil.getApellido());
return Template.EDITAR;
}
		
@PostMapping("/addPerfil")
public String addperfil(@ModelAttribute(name="perfilModel")PerfilModel perfilModel,HttpServletRequest request,@ModelAttribute("bib") String bib ,@ModelAttribute("mun") String mun) {
Usuario usuario= perfilconverter.convertPerfilModel2Perfil(perfilModel);
	perfilRepository.actualizarNombre(perfilModel.getNombre(), perfilModel.getId());
	perfilRepository.actualizarApellido(perfilModel.getApellido(), perfilModel.getId());
	perfilRepository.actualizarEmail(perfilModel.getEmail(), perfilModel.getId());
	perfilRepository.actualizarGenero(perfilModel.getGenero(), perfilModel.getId());
	int id_numicipio = Integer.parseInt(mun);
	int id_biblioteca = Integer.parseInt(bib);
	
	perfilRepository.actualizarMunicipio(id_numicipio,perfilModel.getId());
	
	perfilRepository.actualizarBiblioteca(id_biblioteca,perfilModel.getId());
	
	perfilRepository.actualizarPadre(perfilModel.getNombre_padre(), perfilModel.getId());
	
	perfilRepository.actualizarMadre(perfilModel.getNombre_madre(), perfilModel.getId());
	
	
	perfilRepository.actualizarNumero(perfilModel.getNumero_telefono(), perfilModel.getId());
	
	perfilRepository.actualizarUsername(perfilModel.getUsername(), perfilModel.getId());
	
	HttpSession sesion = request.getSession();
	
	sesion.setAttribute("usuario", usuario);
	sesion.setAttribute("usuario.genero",usuario.getGenero());
	
return "redirect:/index";
}
	
}
	

