package com.bib404.system_bib404.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bib404.system_bib404.Repository.RecursoEspecificoRepository;
import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.model.RecursoEspecificoModel;
import com.bib404.system_bib404.service.RecursoEspecificoService;

@Controller
@RequestMapping("/recursoEspecifico")
public class RecursoEspecificoController {

    private static final Log LOG = LogFactory.getLog(RecursoEspecificoController.class);

    @Autowired
    @Qualifier("recursoEspecificoServiceImpl")
    RecursoEspecificoService recursoEspecificoService;

    @Autowired
    @Qualifier("recursoEspecificoRepository")
    RecursoEspecificoRepository recursoEspecificoRepository;
    
    @GetMapping("/mostrarTodosLosRegistros")
    public ModelAndView showRecursoEspecificos(){
        ModelAndView modelAndView = new ModelAndView(Template.RECURSOSESPECIFICOS);
        modelAndView.addObject("recursosEspecificos", recursoEspecificoService.listAllRecursoEspecificos());
        return modelAndView;
    }    
    
    @GetMapping("/mostrarPorId")
    public ModelAndView showRecursoEspecificosById(@RequestParam(name = "id", required = false) int id) {
       RecursoEspecificoModel recursosEspecificos=new RecursoEspecificoModel();
       recursosEspecificos=recursoEspecificoService.findById(id);
       ModelAndView modelAndView = new ModelAndView(Template.RECURSOSESPECIFICOS);
        modelAndView.addObject("recursosEspecificos", recursosEspecificos);
        return modelAndView;	
    }   
}
