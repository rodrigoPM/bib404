package com.bib404.system_bib404.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bib404.system_bib404.constant.Template;

@Controller
//@RequestMapping("/{name_bib}")
public class bibliotecaController {
	
//	@GetMapping("/")
	@RequestMapping("/{name_bib}")
	@ResponseBody
	public ModelAndView inicioBibX(@PathVariable("name_bib") String name_bib) {
		ModelAndView mav = new ModelAndView(Template.INDEX_BIB_X);
		mav.addObject("name_bib",name_bib);
		return mav;
	}
	
	
	
}
