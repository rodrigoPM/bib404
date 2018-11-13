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

import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.constant.Url;
import com.bib404.system_bib404.controller.UsuarioController;
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
		
		if(sesion.getAttribute(Template.USER)==null) {
			mav.addObject("anonimo", true);
		}else {
			mav.addObject("user2", true);
			
			if (sesion.getAttribute("userup")==null)
			{
			
			mav.addObject("user", sesion.getAttribute(Template.USER));
			}
			else {
				
				mav.addObject("user", sesion.getAttribute("userup"));
				
			}
			
			
		}
		if(bibliotecaService.listAllBibs().size() >0) {
			mav.addObject("bib", true);
			mav.addObject("bibliotecas", bibliotecaService.listAllBibs());
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
		if(sesion.getAttribute(Template.USER)==null) {
			return "redirect:/";
		}else {

			model.addAttribute("user", sesion.getAttribute(Template.USER));
			return "redirect:/";
		}

	}
	
	
	@GetMapping("/biblioteca")
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
			return "super";
		}else {
			return "redirect:/index";
		}
	}
	
	@PostMapping("/addSuper")
	public String redireccionSuper(@Valid @ModelAttribute("usuario") Usuario usuario,@ModelAttribute("mun") String mun,HttpServletRequest request)  throws ServletException, IOException {
		String pass = encriptado.Encriptar(usuario.getPassword());
		usuario.setPassword(pass);
		System.out.println(usuario.getPassword());
		usuario.setFecha_registro(new Date());
		usuario.setRol(Template.SUPER_USUARIO);
		int id_numicipio = Integer.parseInt(mun);
		int id_biblioteca = 1;
		int val = 0;
		Date fech = new Date();
		int anio = fech.getYear() - 4;
		if(usuario.getFecha_nacimiento().getYear()>anio) {
			System.out.println(""+usuario.getFecha_nacimiento().getYear());
			return "redirect:/biblioteca?error=fecha";
		}else {
			val = usuarioImp.addUser(usuario, id_numicipio, id_biblioteca);
		}
		
		if (val==1) {
			HttpSession sesion = request.getSession();
			sesion.setAttribute("usuario", usuario);
			return "redirect:/bibliotecaS";
		}else {
			return "redirect:/biblioteca?error=user";
		}
	}
	
	
	
	@GetMapping("/bibliotecaS")
	public String biblioteca(HttpServletRequest request, Model model)  throws ServletException, IOException  {
		String bib ="biblioteca";
		if(funtions.isSuperUser(request)){
			HttpSession session = request.getSession();
			List<Municipio> municipios = usuarioImp.listMunicipiosOrderByNombre();
			model.addAttribute("biblioteca",new Biblioteca());
			model.addAttribute("municipios",municipios);
			model.addAttribute("user", session.getAttribute(Template.USER));			
		}else {
			return "redirect:/";
		}
		return bib;
	}
	

	@PostMapping("/addBib")
	public String insertBib(HttpServletRequest request,@Valid @ModelAttribute("biblioteca") Biblioteca bib, @ModelAttribute("mun") String mun_id)throws ServletException, IOException   {
		String ret="";
		Log log = LogFactory.getLog(UsuarioController.class);
		log.info(""+bib.getId());
		
		if (funtions.isSuperUser(request)) {
			HttpSession session = request.getSession();
			log.info(bib.getNombre_biblioteca());
			log.info(bib.getCodigo_biblioteca());
			int id_mun = Integer.parseInt(mun_id);
			int v=usuarioImp.addBiblio(bib, id_mun);
			if (v==0) {
				ret="redirect:/bibliotecaS";
			}else {
				Biblioteca b = usuarioImp.findBibByCode(bib.getCodigo_biblioteca());
				System.out.println(b.getCodigo_biblioteca()+b.getId());
				int c = usuarioImp.updateSuperUser((Usuario)session.getAttribute(Template.USER),b.getId());
				ret ="redirect:/DashBoard/admin";
			}
		}else {
			
		}
		return ret;
	}	
	
	
	
}
