package com.bib404.system_bib404.controller;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bib404.system_bib404.Repository.ImagenPerfil;
import com.bib404.system_bib404.Repository.PerfilRepository;
import com.bib404.system_bib404.Repository.UsuarioRepository;
import com.bib404.system_bib404.Repository.actualizarPerfil;
import com.bib404.system_bib404.component.PerfilConverter;
import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.controller.registrar_iniciar_Sesion.UsuarioController;
import com.bib404.system_bib404.entity.Municipio;
import com.bib404.system_bib404.entity.Usuario;
import com.bib404.system_bib404.model.PerfilModel;
import com.bib404.system_bib404.service.EncriptadoPass;
import com.bib404.system_bib404.service.PerfilService;
import com.bib404.system_bib404.service.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/")
public class PerfilController {
	private static Municipio MUNICIPIO;
	private static Log LOG = LogFactory.getLog(UsuarioController.class);
	@Autowired
	@Qualifier("perfilRepository")
	private PerfilRepository perfilRepository;
	@Autowired
	@Qualifier("actualizarPerfil")
		private actualizarPerfil actPerfil;
	@Autowired
	@Qualifier("ImagenPerfil")
		private ImagenPerfil imagenPerfil;
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

@RequestMapping("/digitales")
public ModelAndView listPrestamos(Model model, HttpServletRequest request)  throws ServletException, IOException  {
	ModelAndView mav = new ModelAndView(Template.DIGITALES);
	/*mav.addObject("prestamos", prestamoServiceImpl.listPrestamos());
	model.addAttribute("prestado", 1);*/
	
	return mav;
}
@RequestMapping("/gestion_usuario")
public ModelAndView listPrestamo(Model model, HttpServletRequest request)  throws ServletException, IOException  {
	ModelAndView mav = new ModelAndView(Template.GESTION_USUARIO);
	/*mav.addObject("prestamos", prestamoServiceImpl.listPrestamos());
	model.addAttribute("prestado", 1);*/
	
	return mav;
}
@GetMapping("/perfil")
public String redirectPerfilForm(Model model,@ModelAttribute(name="username") String username,HttpServletRequest request) {

	
	
	HttpSession sesion = request.getSession();
	Usuario user=actPerfil.buscarusuario(username);
	if(sesion.getAttribute(Template.USER)==null) {
		return "redirect:/";
	}else {
		
		
		if (sesion.getAttribute("userup")==null)
		{
		
		model.addAttribute("user", sesion.getAttribute(Template.USER));
		}
		else {
			
			model.addAttribute("user", sesion.getAttribute("userup"));
			
		}
	}
	
model.addAttribute("username", user.getUsername());
    model.addAttribute("usua",user);
    
    

	return Template.PERFIL;
	
}


@GetMapping("/editar")
public String editar(@ModelAttribute("username") String username,@ModelAttribute(name="fecha_nacimiento") String fecha_nacimiento,
		Model model,HttpServletRequest request) {
	HttpSession sesion = request.getSession();
	Date fecha = new Date();
	int anio = fecha.getYear() - 4 ;
	fecha.setYear(anio);
	SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
	String fecha_actual = formateador.format(fecha);

	Usuario user=new Usuario();
	
   user=actPerfil.buscarusuario(username);
	String fechanacimiento=formateador.format(user.getFecha_nacimiento());
	if(sesion.getAttribute(Template.USER)==null) {
		return "redirect:/";
	}else {
		
		
		if (sesion.getAttribute("userup")==null)
		{
		
		model.addAttribute("user", sesion.getAttribute(Template.USER));
		}
		else {
			
			model.addAttribute("user", sesion.getAttribute("userup"));
			
		}
	}
	

	model.addAttribute("perfilModel",user );
	model.addAttribute("fechan",fechanacimiento);
	model.addAttribute("fecha_actual", fecha_actual);
	model.addAttribute("bibliotecas", usuarioImp.listBibliotecas());
	model.addAttribute("municipios", usuarioImp.listMunicipios());
	
	System.out.println("nombre de usuario   "+ user.getNombre()+" apellido"+user.getApellido()+"  fecha"+fecha_nacimiento);
return Template.EDITAR;
}
		
@PostMapping("/addPerfil")
public String addperfil(@ModelAttribute(name="perfilModel")PerfilModel perfilModel,@RequestParam(name="username", required=true) String username,HttpServletRequest request,@ModelAttribute("bib") String bib ,@ModelAttribute("mun") String mun, @ModelAttribute("fecha_nacimiento") String fecha_nacimiento,@ModelAttribute("mun") String muni, Model model) throws ParseException {
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
	
	perfilRepository.actualizarUsername(perfilModel.getUsername(),perfilModel.getId());
	  
	
	Date fecha = new Date();
	SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
	fecha=formateador.parse(fecha_nacimiento);

	imagenPerfil.actualizarFecha(fecha, username);
	System.out.println("fecha nacimiento   :"+fecha.toString());
	HttpSession sesion = request.getSession();
	
	model.addAttribute("userup", usuario);
    //model.addAttribute("id",perfil.getId() );
	sesion.setAttribute("userup", usuario);
	


 return "redirect:/index";
}

@GetMapping("/cambiarpassword")
public String cambiar(@ModelAttribute("username") String username, Model model, HttpServletRequest request) {
	HttpSession sesion = request.getSession();
	Usuario user=new Usuario();
	
	   user=actPerfil.buscarusuario(username);
	   if(sesion.getAttribute(Template.USER)==null) {
			return "redirect:/";
		}else {
			
			
			if (sesion.getAttribute("userup")==null)
			{
			
			model.addAttribute("user", sesion.getAttribute(Template.USER));
			if (sesion.getAttribute("test")!=null)
			{
			model.addAttribute("valor","Error password incorrecto o los campos no coinciden");
			model.addAttribute("error",true);
			}
			if (sesion.getAttribute("testn")!=null)
			{
			model.addAttribute("valor","Error las contraseñas no son iguales en los campos nuevo password y confirmar passsword");
			model.addAttribute("error",true);
			}
			else {
				
				model.addAttribute("error", false);
			}
			
			}
			else {
				
				model.addAttribute("user", sesion.getAttribute("userup"));
				
			}
			
		}
		
	   
	
	   
return Template.CONTRA;
}


@PostMapping("/addContra")
public String addContra(@RequestParam(name="username", required=false, defaultValue="NULL") String username, @RequestParam("contra") String contra,@RequestParam("contranew") String contranew,@RequestParam("contranovo") String contranovo, Model model,HttpServletRequest request) {
	HttpSession sesion = request.getSession();
	Usuario   user=actPerfil.buscarusuario(username);	
	
	//String pass= encriptado.Desencriptar(user.getPassword());
	String pas=encriptado.Encriptar(contranew);
	String prueba=encriptado.Desencriptar(user.getPassword());
	System.out.println("contra  "+contra+" usuario"+ username +"Encriptar"+pas+"Desencriptar"+prueba+"prueba"+prueba.equals(contra));	
	if (!(contra.equals(prueba)))
	{	
		sesion.setAttribute("test",user);
		System.out.println("contra  "+contra+" usuario"+ username +"Encriptar"+pas+"Desencriptar"+prueba);
		return "redirect:/cambiarpassword?username="+user.getUsername();
		
	}
	
	if (!(contranew.equals(contranovo))) {
		
		sesion.setAttribute("testn",user);
		System.out.println("contra  "+contra+" usuario"+ username +"Encriptar"+pas+"Desencriptar"+prueba);
		return "redirect:/cambiarpassword?username="+user.getUsername();
		
		
	}
	else {
/*
 * 
 * 
 * 
	if (contra!=pass)
	{
		
		model.addAttribute("error", true);
		model.addAttribute("valor","el password actual es incorrecto");
		
		return Template.CONTRA;
	}
	if(contranew!=contranovo)
	{
		model.addAttribute("error", true);
		model.addAttribute("valor","los campos de los password no coinciden");
		return Template.CONTRA;
		
//	}
	*/
	
	
perfilRepository.actualizarPassword(pas, user.getId())	   ;
	  
		   
		   
   return "redirect:/index";
	}
	   
	   

}
}
	


	

