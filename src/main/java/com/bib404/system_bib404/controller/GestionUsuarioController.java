package com.bib404.system_bib404.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.bib404.system_bib404.entity.Categoria;
import com.bib404.system_bib404.entity.Usuario;
import com.bib404.system_bib404.model.PrestamoModel;
import com.bib404.system_bib404.service.EncriptadoPass;
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
	
	@Autowired
	@Qualifier("encriptadoPass")
	private EncriptadoPass encriptado;

	@RequestMapping("/bib404/{id_bib}/gestion_usuario")
	public ModelAndView listUsuarios(@PathVariable("id_bib") int id_bib, Model model, HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView(Template.GESTION_USUARIO);
		mav.addObject("name_bib", bibliotecaServiceImpl.findById(id_bib).getNombre_biblioteca());
		if(usuarioServiceImpl.listUsuarioBib(id_bib).size()>0) {
			mav.addObject("usuarios", usuarioServiceImpl.listUsuarioBib(id_bib));
		}
		
		return mav;
	}
	@GetMapping("/eliminar_usuario")
	public ModelAndView eliminarUsuario(@RequestParam(name="id") int id, @RequestParam(name="id_bib") int id_bib, Model model, HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView(Template.GESTION_USUARIO);
		usuarioServiceImpl.deleteUser(id);
		model.addAttribute("exito", "Se eliminó correctamente el usuario");
		System.out.println("Categoria eliminada con exito");
		return listUsuarios(id_bib, model, request);
	}
	@GetMapping("/activar_usuario")
	public ModelAndView activarUsuario(@RequestParam(name="id", required=false)int id, @RequestParam(name="id_bib") int id_bib, Model model,
			@ModelAttribute(name="usuario") Usuario usuario, HttpServletRequest request) throws ServletException, IOException  {
		Usuario user=new Usuario();
		if(id!=0) {
			user=usuarioServiceImpl.findById(id);
			user.setEnable(true);
			usuarioServiceImpl.updateUser(user);
			model.addAttribute("exito", "Se activó correctamente el usuario");
		}

		return listUsuarios(id_bib, model, request);
		
	}
	@GetMapping("/desactivar_usuario")
	public ModelAndView desactivarUsuario(@RequestParam(name="id", required=false)int id, @RequestParam(name="id_bib") int id_bib, Model model,
			@ModelAttribute(name="usuario") Usuario usuario, HttpServletRequest request) throws ServletException, IOException  {
		Usuario user=new Usuario();
		if(id!=0) {
			user=usuarioServiceImpl.findById(id);
			user.setEnable(false);
			usuarioServiceImpl.updateUser(user);
			model.addAttribute("exito", "Se desactivó correctamente el usuario");
		}

		return listUsuarios(id_bib, model, request);
		
	}
	@GetMapping("/editar_usuario")
	public ModelAndView editarUsuario(@RequestParam(name="id", required=false)int id, @RequestParam(name="id_bib") int id_bib, Model model,
			@ModelAttribute(name="usuario") Usuario usuario, HttpServletRequest request) throws ServletException, IOException  {
		Usuario user= new Usuario();
		if(id!=0) {
			user=usuarioServiceImpl.findById(id);
			
			
			model.addAttribute("exito", "Se editó correctamente el usuario");
		}
		ModelAndView mav = new ModelAndView(Template.GESTION_USUARIO_PERFIL);
		model.addAttribute("user", user);

		return mav;
		
	}
	@PostMapping("/adduser")
	public ModelAndView addUser(Model model, @RequestParam(name="id") int id, @RequestParam(name="id_bib") int id_bib,
			@ModelAttribute("username") String username,@ModelAttribute("password") String password, HttpServletRequest request)  throws ServletException, IOException  {
			Usuario user=new Usuario();
			user=usuarioServiceImpl.findById(id);
			user.setUsername(username);
			user.setPassword(password);
			String pass = encriptado.Encriptar(user.getPassword());
			user.setPassword(pass);
			Usuario user2=usuarioServiceImpl.updateUser(user);
			
			model.addAttribute("exito", "Se editó correctamente el usuario");
			
			if (user2!=null) {
				HttpSession sesion = request.getSession();
				sesion.setAttribute("usuario", user);
				model.addAttribute("exito", "Se editó correctamente el usuario");
				return listUsuarios(id_bib, model, request);
			}else {
				System.out.println("Usuario repetido");
				model.addAttribute("exito", "No se pudo editar el usuario");
				return listUsuarios(id_bib, model, request);
			}
		
	}

}
