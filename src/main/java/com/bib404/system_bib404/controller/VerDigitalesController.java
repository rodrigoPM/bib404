package com.bib404.system_bib404.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.service.impl.PrestamoServiceImpl;

@Controller
@RequestMapping("/")
public class VerDigitalesController {
	@Autowired
	@Qualifier("prestamoServiceImpl")
	private PrestamoServiceImpl prestamoServiceImpl;
	
	@RequestMapping("/ver_digitales")
	public ModelAndView listPrestados(@RequestParam("id_user") int id_user, Model model, HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView(Template.DIGITALES);
		//mav.addObject("name_bib", bibliotecaServiceImpl.findById(id_bib).getNombre_biblioteca());
		if(prestamoServiceImpl.listPrestadosEspecificos(id_user).size()>0) {
			mav.addObject("prestamos", prestamoServiceImpl.listPrestadosEspecificos(id_user));
		}
		else {
			model.addAttribute("error", "No tienes recusos digitales actualmente");
		}
		
		return mav;
	}

}
