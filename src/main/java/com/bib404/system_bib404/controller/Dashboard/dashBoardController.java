package com.bib404.system_bib404.controller.Dashboard;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bib404.system_bib404.constant.Constante;
import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.entity.Usuario;
import com.bib404.system_bib404.service.impl.BibliotecaServiceImpl;
import com.bib404.system_bib404.service.impl.Functions;
import com.bib404.system_bib404.service.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/DashBoard")
public class dashBoardController {
	
	@Autowired
	@Qualifier("Functions")
	private Functions funtions;
	
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioServiceImpl usuarioImp;
	
	@Autowired
	@Qualifier("bibliotecaServiceImpl")
	private BibliotecaServiceImpl bibImp;
	
	@GetMapping("/admin")
	public ModelAndView index(HttpServletRequest request)  throws ServletException, IOException  {
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
				mav.setViewName("redirect:/");
			}
		}
		mav.addObject("titulo", "System BIB404");
		return mav;
	}
	
	@GetMapping("/insertAdmin")
	public ModelAndView register(@RequestParam(name="error", required=false, defaultValue="NULL") String nm, HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView();
		if (funtions.isSuperUserBIB404(request)) {
			if(nm.compareToIgnoreCase("user")==0) {
				mav.addObject("valor","Error username, ocupado");
				mav.addObject("error",1);
			}else {
				if(nm.compareToIgnoreCase("fecha")==0) {
					mav.addObject("valor","Error fecha, eres demasiado joven :v");
					mav.addObject("error",1);
				}else
					mav.addObject("error",false);
			}
			HttpSession session = request.getSession();
			Usuario user =(Usuario) session.getAttribute(Constante.USER);
			mav.addObject("user",user);
			mav.addObject("bib",usuarioImp.findBibByUser(user.getUsername()));
			mav.setViewName(Template.REGISTRAR_ADMIN);
			mav.addObject("usuario", new Usuario());
			mav.addObject("bibliotecas", usuarioImp.listBibliotecas());
			Date fecha = new Date();
			int anio = fecha.getYear() - 4 ;
			fecha.setYear(anio);
			SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
			String fecha_actual = formateador.format(fecha);
			System.out.println(fecha_actual);
			mav.addObject("fecha_actual", fecha_actual);
			mav.addObject("municipios", usuarioImp.listMunicipiosOrderByNombre());
			mav.addObject("bibliotecas",bibImp.listAllBibliotecas());
			
		}else {
			if (funtions.isAdmin(request)) {
				mav.setViewName(Template.dashAd);
			}else {
				mav.setViewName("redirect:/");
			}
		}
		mav.addObject("titulo", "System BIB404");
		return mav;
	}
	
	
	
}
