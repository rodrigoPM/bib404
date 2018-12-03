package com.bib404.system_bib404.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

import com.bib404.system_bib404.Repository.ConsultasRE;
import com.bib404.system_bib404.constant.Constante;
import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.entity.Categoria;
import com.bib404.system_bib404.entity.Usuario;
import com.bib404.system_bib404.model.ObjectAux;
import com.bib404.system_bib404.model.PrestamoModel;
import com.bib404.system_bib404.service.EncriptadoPass;
import com.bib404.system_bib404.service.impl.BibliotecaServiceImpl;
import com.bib404.system_bib404.service.impl.Functions;
import com.bib404.system_bib404.service.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/")
public class GestionUsuarioController {
	@Autowired
	@Qualifier("Functions")
	private Functions funcion;
	
    @Autowired
    @Qualifier("consultasRE")
    ConsultasRE consultas;
    
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@Autowired
	@Qualifier("bibliotecaServiceImpl")
	private BibliotecaServiceImpl bibliotecaServiceImpl;
	
	@Autowired
	@Qualifier("encriptadoPass")
	private EncriptadoPass encriptado;

	@RequestMapping("/gestion_usuario")
	public ModelAndView listUsuarios(@RequestParam(name="id_bib") int id_bib, Model model, HttpServletRequest request)  throws ServletException, IOException  {
		if (!funcion.isAdmin(request)) {
			ModelAndView mav = new ModelAndView("redirect:/");
			return mav;
		}
		ModelAndView mav = new ModelAndView(Template.GESTION_USUARIO);
		Usuario user= new Usuario();
		mav.addObject("name_bib", bibliotecaServiceImpl.findById(id_bib).getNombre_biblioteca());
		mav.addObject("bib", bibliotecaServiceImpl.findById(id_bib).getId());
		if(usuarioServiceImpl.listUsuarioBib(id_bib).size()>0) {
			mav.addObject("usuarios", usuarioServiceImpl.listUsuarioBib(id_bib));
			mav.addObject("user", user);
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
				//HttpSession sesion = request.getSession();
				//sesion.setAttribute("usuario", user);
				model.addAttribute("exito", "Se editó correctamente el usuario");
				return listUsuarios(id_bib, model, request);
			}else {
				System.out.println("Usuario repetido");
				model.addAttribute("exito", "No se pudo editar el usuario");
				return listUsuarios(id_bib, model, request);
			}
		
	}
	@PostMapping("/addUser2")
	public ModelAndView redireccion2(Model model, @Valid @ModelAttribute("usuario") Usuario usuario,@ModelAttribute("bib") String bib ,@ModelAttribute("mun") String mun,HttpServletRequest request)  throws ServletException, IOException {
		String pass = encriptado.Encriptar(usuario.getPassword());
		usuario.setPassword(pass);
		usuario.setFecha_registro(new Date());
		usuario.setRol(Constante.USUARIO_SIMPLE);
		int id_numicipio = Integer.parseInt(mun);
		int id_biblioteca = Integer.parseInt(bib);
		int val = 0;
		Date fech = new Date();
		int anio = fech.getYear() - 4;
		if(usuario.getFecha_nacimiento().getYear()>anio) {
			System.out.println(""+usuario.getFecha_nacimiento().getYear());
			model.addAttribute("error", "Error con la fecha de nacimiento");
			return listUsuarios(id_biblioteca, model, request);
		}else {
			val = usuarioServiceImpl.addUser(usuario, id_numicipio, id_biblioteca);
		}
		model.addAttribute("exito", "Se registró correctamente el usuario");
		return listUsuarios(id_biblioteca, model, request);

	}
	@PostMapping("/buscar_usuario")
	public ModelAndView buscar(@RequestParam("id_bib") int id_bib, Model model,
			@RequestParam(name = "str", required = false, defaultValue = "all") String str, HttpServletRequest request) {
		if (!bibliotecaServiceImpl.existsBibById(id_bib)) {
			ModelAndView mav = new ModelAndView("redirect:/");
			return mav;
		}
		ModelAndView mav = new ModelAndView(Template.GESTION_USUARIO);
		Usuario user= new Usuario();
		mav.addObject("name_bib", bibliotecaServiceImpl.findById(id_bib).getNombre_biblioteca());
		mav.addObject("bib", bibliotecaServiceImpl.findById(id_bib).getId());

		model.addAttribute("user", user);
		// buscar por nombre de usuario
		List<Usuario> usuarios = usuarioServiceImpl.listUsuarioBib(id_bib);
		List<Usuario> usuariosBuscados = new ArrayList<Usuario>();
		if (!str.equals("all") && !str.equals("")) {
			for (Usuario user2 : usuarios) {
				if (user2.getUsername().toLowerCase().contains(str.toLowerCase())) {// si el nombre de la categoria contiene str de busqueda
					usuariosBuscados.add(user2);
				}
			}
		}else{
			usuariosBuscados=usuarios;
		}

		if (usuariosBuscados.size() > 0) {
			mav.addObject("usuarios", usuariosBuscados);
			mav.addObject("exito", "Los usuarios encontrados con el termino " + str.toUpperCase() + " son:");
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

}
