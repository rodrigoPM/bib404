package com.bib404.system_bib404.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.entity.Usuario;
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
	
	@GetMapping("/admin")
	public ModelAndView indexAnonimo(HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView();
		if (funtions.isSuperUser(request)) {
			HttpSession session = request.getSession();
			Usuario user =(Usuario) session.getAttribute(Template.USER);
			mav.addObject("user",user);
			mav.addObject("bib",usuarioImp.findBibByUser(user.getUsername()));
			mav.setViewName(Template.dashBoard);
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
