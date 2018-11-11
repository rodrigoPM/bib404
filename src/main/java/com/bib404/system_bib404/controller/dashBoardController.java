package com.bib404.system_bib404.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.service.impl.Functions;

@Controller
@RequestMapping("/DashBoard")
public class dashBoardController {
	
	@Autowired
	@Qualifier("Functions")
	private Functions funtions;
	
	@GetMapping("/admin")
	public ModelAndView indexAnonimo(HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView();
		if (funtions.isSuperUser(request)) {
			mav.setViewName(Template.dashBoard);
		}else {
			if (funtions.isAdmin(request)) {
				mav.setViewName(Template.dashBoard);
			}else {
				mav.setViewName(Template.dashBoard);
			}
		}
		mav.addObject("titulo", "System BIB404");
		return mav;
	}
	
	
}
