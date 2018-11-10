package com.bib404.system_bib404.controller;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.entity.Usuario;
import com.bib404.system_bib404.service.EncriptadoPass;
import com.bib404.system_bib404.service.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController extends HttpServlet{
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioServiceImpl usuarioImp;
	
	@Autowired
	@Qualifier("encriptadoPass")
	private EncriptadoPass encriptado;	
	
	@RequestMapping("/listUser")
	public ModelAndView getAllUser(HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView(Template.USUARIOS);
		mav.addObject("usuarios", usuarioImp.listUsuario());
		return mav;
	}
	
	@GetMapping("/registrarse")
	public String registrarse(@RequestParam(name="error", required=false, defaultValue="NULL") String nm, Model model,HttpServletRequest request)  throws ServletException, IOException  {
		model.addAttribute("titulo","BIB404-registrarse");
		HttpSession session = request.getSession();
		if(session.getAttribute(Template.USER)==null) {
			if(nm.compareToIgnoreCase("user")==0) {
				model.addAttribute("valor","Error username, ocupado");
				model.addAttribute("error",1);
			}else {
				if(nm.compareToIgnoreCase("fecha")==0) {
					model.addAttribute("valor","Error fecha, eres demasiado joven :v");
					model.addAttribute("error",1);
				}else
				model.addAttribute("error",false);
			}
			model.addAttribute("usuario", new Usuario());
			model.addAttribute("bibliotecas", usuarioImp.listBibliotecas());
			Date fecha = new Date();
			int anio = fecha.getYear() - 4 ;
			fecha.setYear(anio);
			SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
			String fecha_actual = formateador.format(fecha);
			System.out.println(fecha_actual);
			model.addAttribute("fecha_actual", fecha_actual);
			model.addAttribute("municipios", usuarioImp.listMunicipiosOrderByNombre());
			model.addAttribute("departamentos", usuarioImp.listDpto());
			return Template.REGISTRAR;
		}else {
			return "redirect:/index";
		}
	}
	
	@PostMapping("/addUser")
	public String redireccion(@Valid @ModelAttribute("usuario") Usuario usuario,@ModelAttribute("bib") String bib ,@ModelAttribute("mun") String mun,HttpServletRequest request)  throws ServletException, IOException {
		String pass = encriptado.Encriptar(usuario.getPassword());
		usuario.setPassword(pass);
		usuario.setFecha_registro(new Date());
		usuario.setRol(Template.USUARIO_SIMPLE);
		int id_numicipio = Integer.parseInt(mun);
		int id_biblioteca = Integer.parseInt(bib);
		int val = 0;
		Date fech = new Date();
		int anio = fech.getYear() - 4;
		if(usuario.getFecha_nacimiento().getYear()>anio) {
			System.out.println(""+usuario.getFecha_nacimiento().getYear());
			return "redirect:/usuarios/registrarse?error=fecha";
		}else {
			val = usuarioImp.addUser(usuario, id_numicipio, id_biblioteca);
		}
		
		if (val==1) {
			HttpSession sesion = request.getSession();
			sesion.setAttribute("usuario", usuario);
			return "redirect:/index";
		}else {
			return "redirect:/usuarios/registrarse?error=user";
		}
	}
	
	@PostMapping("/logcheck")
	public String loginCheck(@ModelAttribute("username") String username,@ModelAttribute("password") String password, HttpServletRequest request)  throws ServletException, IOException {
		Usuario user = usuarioImp.findBy(username);
		if(user==null) {
			return "redirect:/?error=user";
		}else {
			HttpSession sesion = request.getSession();
			System.out.println("username= " +username+"=="+user.getUsername()+" password= " +password+"=="+encriptado.Desencriptar(user.getPassword()));
			if (username.compareToIgnoreCase(user.getUsername()) == 0 && password.compareToIgnoreCase(encriptado.Desencriptar(user.getPassword()))==0 && sesion.getAttribute("usuario")==null) {
				sesion.setAttribute("usuario", user);
				return "redirect:/index";
			}else {
				return "redirect:/?error=user";
			}
		}
	}
	
	@GetMapping("/logout")
	public String logout(@RequestParam(name="error", required=false, defaultValue="NULL") String name, Model model,HttpServletRequest request)  throws ServletException, IOException  {
		model.addAttribute("titulo","BIB404-registrarse");
		HttpSession session = request.getSession();
		if(session.getAttribute(Template.USER)!=null) {
			session.setAttribute(Template.USER, null);
			session.invalidate();
		}
		return "redirect:/";
	}
	
	
}
