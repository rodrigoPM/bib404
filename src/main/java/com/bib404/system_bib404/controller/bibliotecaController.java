package com.bib404.system_bib404.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bib404.system_bib404.constant.Template;

@Controller
public class bibliotecaController {
	
//	@GetMapping("/")
	@RequestMapping("/{name_bib}")
	@ResponseBody
	public ModelAndView inicioBibX(@PathVariable("name_bib") String name_bib, HttpServletRequest request, Model model)  throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();
		mav.addObject("name_bib",name_bib);
		HttpSession sesion = request.getSession();
		if(sesion.getAttribute(Template.USER)!=null) {
			mav.setViewName(Template.INDEX_USER);
		}else {
			mav.setViewName(Template.INDEX_ANONIMO);
		}
		return mav;
	}
	
	
	
}
