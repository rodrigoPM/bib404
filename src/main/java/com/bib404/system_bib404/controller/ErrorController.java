package com.bib404.system_bib404.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bib404.system_bib404.constant.Template;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController{
 
	@RequestMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		model.addAttribute("titulo","BIB404");
		model.addAttribute("urlHome","/");
		HttpSession sesion = request.getSession();
		if (sesion.getAttribute(Template.USER)==null) {
			model.addAttribute("authF",true);
		}else {
			model.addAttribute("authT", true);
		}
		
		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());
			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				model.addAttribute("E404",true);
			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				model.addAttribute("E500",true);
			}
		}
		return "error";
	}
 
    @Override
    public String getErrorPath() {
        return "/error";
    }
}
