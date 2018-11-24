package com.bib404.system_bib404.controller.solicitar_biblioteca;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bib404.system_bib404.constant.Constante;
import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.constant.Url;
import com.bib404.system_bib404.entity.Biblioteca;
import com.bib404.system_bib404.entity.Municipio;
import com.bib404.system_bib404.entity.Solicitud;
import com.bib404.system_bib404.entity.SolicitudIntermedia;
import com.bib404.system_bib404.entity.Usuario;
import com.bib404.system_bib404.model.PerfilModel;
import com.bib404.system_bib404.service.EncriptadoPass;
import com.bib404.system_bib404.service.impl.Functions;
import com.bib404.system_bib404.service.impl.MensajeImp;
import com.bib404.system_bib404.service.impl.SolicitudImpl;
import com.bib404.system_bib404.service.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/solicitar") 
public class SolicitarBib {
	@Autowired
	@Qualifier("Functions")
	private Functions funtions;
	
	@Autowired
	@Qualifier("SolicitudImpl")
	private SolicitudImpl solicitudImpl;
	
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioServiceImpl usuarioImp;
	
	@Autowired
	@Qualifier("encriptadoPass")
	private EncriptadoPass encriptado;	
	
	@Autowired
	@Qualifier("mensajeImp")
	private MensajeImp mensajeImp;
	
	@GetMapping("/biblioteca")
	public ModelAndView index(@RequestParam(name="res", required=false, defaultValue="NULL") String res,HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView();
		if (funtions.isSuperUserBIB404(request)) {
			HttpSession session = request.getSession();
			Usuario user =(Usuario) session.getAttribute(Constante.USER);
			mav.addObject("user",user);
			mav.addObject("bib",usuarioImp.findBibByUser(user.getUsername()));
			mav.setViewName(Template.dashBoard);
		}else {
			if (funtions.isAdmin(request) || funtions.isSuperUser(request)) {
				HttpSession session = request.getSession();
				Usuario user =(Usuario) session.getAttribute(Constante.USER);
				mav.addObject("user",user);
				mav.addObject("bib",usuarioImp.findBibByUser(user.getUsername()));
				mav.setViewName(Template.dashAd);
			}else {
				List<Municipio> municipios = usuarioImp.listMunicipiosOrderByNombre();
				mav.addObject("municipios",municipios);
				mav.addObject("solicitud",new Solicitud());
				
				if (res.equalsIgnoreCase("exito")) {
					mav.addObject("exito",true);
				}else {
					if (res.equalsIgnoreCase("error")) {
						mav.addObject("error",true);
					}
				}
				
				if (funtions.isUser(request)) {
					mav.addObject("us",true);
				}else {
					mav.addObject("anonimo", true);
				}
				mav.setViewName(Template.solicitar);
			}
		}
		mav.addObject("titulo", "System BIB404");
		return mav;
	}
	
	@PostMapping("/addSolicitud")
	public String addSolicitud(@ModelAttribute(name="solicitud")Solicitud solicitud,@RequestParam(name="mun", required=true) String mun,HttpServletRequest request) throws ServletException, IOException {
		String retornar ="redirect:/solicitar/biblioteca";
		int id_mun = Integer.parseInt(mun);
		int id = solicitudImpl.findAll().size() + 1;
		solicitud.setId_municipio(id_mun);
		solicitud.setId(id);
		Solicitud s = solicitudImpl.save(solicitud);
		if (s!=null) {
			retornar ="redirect:/solicitar/biblioteca?res=exito";
		}else {
			retornar ="redirect:/solicitar/biblioteca?res=error";
		}
		return retornar;
	}
	
	
	@GetMapping("/recibir")
	public ModelAndView recibir(@RequestParam(name="denegado", required=false, defaultValue="") String denegado,@RequestParam(name="aceptado", required=false, defaultValue="") String aceptado,HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView();
		if (funtions.isSuperUserBIB404(request)) {
			if (!denegado.isEmpty()) {
				mav.addObject("error",true);
				int deneg = Integer.parseInt(denegado);
				Solicitud sol = solicitudImpl.findById(deneg);
				if (sol!=null) {
					solicitudImpl.eliminar(deneg);
				}
			}else {
				if (!aceptado.isEmpty()) {
					mav.addObject("exito",true);
					int acep = Integer.parseInt(aceptado);
					Solicitud sol = solicitudImpl.findById(acep);
					if (sol!=null) {
						sol.setEnable(true);
						solicitudImpl.save(sol);
						mav.setViewName("redirect:/solicitar/new_bib?id="+sol.getId());
						return mav;
					}
				}
			}
			mav.addObject("solicitudes",solicitudImpl.findAll());
			mav.setViewName(Template.recibir);
		}else {
			mav.setViewName("redirect:/");
		}
		mav.addObject("titulo", "System BIB404");
		return mav;
	}
	
	@GetMapping("/new_bib")
	public ModelAndView newBib(@RequestParam(name="id", required=false, defaultValue="") String id,@RequestParam(name="nm", required=false, defaultValue="") String nm,HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView();
		if (funtions.isSuperUserBIB404(request)) {
			if (!id.isEmpty()) {
				if (nm.equalsIgnoreCase("error")) {
					mav.addObject("error_user", true);
				}
				mav.addObject("info",true);
				int id_sol = Integer.parseInt(id);
				Solicitud sol = solicitudImpl.findById(id_sol);
				if (sol!=null) {
					Municipio mun = usuarioImp.findMunBy(sol.getId_municipio());
					int len = usuarioImp.listBibliotecas().size()+1;
					mav.addObject("mun",mun);
					mav.addObject("sol",sol);
					Random aleatorio = new Random(System.currentTimeMillis());
					int intAletorio = aleatorio.nextInt(100);
					String pass = ""+sol.getUsuario().charAt(0)+sol.getCorreo().charAt(0)+"00"+intAletorio;
					String cod = "B404"+len;
					mav.addObject("pass",pass);
					mav.addObject("cod",cod);
					mav.addObject("id",id_sol);
					mav.addObject("solicitudIntermedia",new SolicitudIntermedia());
					mav.setViewName(Template.aceptar);
				}else {
					mav.setViewName("redirect:"+Template.recibir);
				}
			}else {
				mav.setViewName("redirect:"+Template.recibir);
			}
		}else {
			mav.setViewName("redirect:/");
		}
		mav.addObject("titulo", "System BIB404");
		return mav;
	}
	
	@PostMapping("/addBibUser")
	public String addBibUser(@ModelAttribute(name="solicitudIntermedia")SolicitudIntermedia solicitud,@RequestParam(name="mun", required=true) String mun,@RequestParam(name="id", required=true) String id,HttpServletRequest request) throws ServletException, IOException {
		String retornar ="redirect:/solicitar/biblioteca";
		int id_mun = Integer.parseInt(mun);
		int id_sol = Integer.parseInt(id);
		if (usuarioImp.findBibByUser(solicitud.getUsuario())==null) {
			if (solicitud!=null) {
				Biblioteca bib = new Biblioteca();
				bib.setCodigo_biblioteca(solicitud.getCodigo_biblioteca());
				bib.setNombre_biblioteca(solicitud.getNombre_biblioteca());
				bib.setFecha_registro(new Date());
				int id_bib = usuarioImp.addBiblio(bib,id_mun);
				Usuario user  = new Usuario();
				user.setNombre(solicitud.getUsuario());
				user.setApellido("Editar");
				user.setBiblioteca(usuarioImp.findBibBy(id_bib));
				user.setEmail(solicitud.getCorreo());
				user.setEnable(true);
				user.setFecha_nacimiento(new Date(1990, 1, 1));
				user.setFecha_registro(new Date());
				user.setFoto_perfil("none");
				user.setGenero("indefinido");
				user.setUsername(solicitud.getUsuario());
				user.setRol(Constante.ADMIN);
				user.setPassword(encriptado.Encriptar(solicitud.getContra()));
				int v = usuarioImp.addUser(user, id_mun, id_bib);
				if (v==0) {
					retornar ="redirect:/solicitar/recibir?res=error";
				}else {
					solicitudImpl.eliminar(id_sol);
					String mensaje = "Un gusto en saludarle, por este medio le enviamos sus nuevas credenciales para su biblioteca \nUsuario:"+solicitud.getUsuario()+"\npassword:"+solicitud.getContra()+"\n deseandole un feliz dia, nos despedimos, att: BIB404";
					mensajeImp.sendMsj(mensaje,"Aceptacion de solicitud de biblioteca", solicitud.getCorreo());
					retornar ="redirect:/solicitar/recibir?res=exito";
				}
				
			}else 
				retornar ="redirect:/solicitar/recibir?res=error";
		}else {
			retornar ="redirect:/solicitar/new_bib?id="+id+"&nm=error";
		}
		return retornar;
	}
	
	
	
}
