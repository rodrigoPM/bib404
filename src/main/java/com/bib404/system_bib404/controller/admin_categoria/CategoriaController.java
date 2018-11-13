package com.bib404.system_bib404.controller.admin_categoria;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.constant.Url;
import com.bib404.system_bib404.model.CategoriaModel;
import com.bib404.system_bib404.service.impl.BibliotecaServiceImpl;
import com.bib404.system_bib404.service.impl.CategoriaServiceImpl;
import com.bib404.system_bib404.service.impl.Functions;

@Controller
@RequestMapping(Url.CATEGORIA) // --> /bib404/{id_bib}/dashboard/categorias
public class CategoriaController {
	
	@Autowired
	@Qualifier("Functions")
	private Functions funcion;
	
	@Autowired
	@Qualifier("bibliotecaServiceImpl")
	private BibliotecaServiceImpl biblioteca;
	
	@Autowired
	@Qualifier("categoriaServiceImpl")
	private CategoriaServiceImpl categoria;
	
	@RequestMapping("")
	public ModelAndView adminCategoria(@PathVariable("id_bib") int id_bib,HttpServletRequest request) {
		ModelAndView mav =new ModelAndView(Template.CATEGORIA);
		mav.addObject("titulo", "Categorias");
		mav.addObject("name_bib", biblioteca.findById(id_bib).getNombre_biblioteca());
		mav.addObject("crearCategoria", "/bib404/"+id_bib+"/dashboard/categorias/nueva"); //action del form crear categoria
		mav.addObject("categoriaModel", new CategoriaModel());
		if(categoria.listAllCategorias().size()>0) {
			mav.addObject("categorias", categoria.listAllCategorias());
		}
		
		HttpSession session = request.getSession();
		if(session.getAttribute("addCategoria")!=null) {
			if((boolean)session.getAttribute("addCategoria")) {
				mav.addObject("exito", "Se agrego exitosamente la categoria");
			}else {
				mav.addObject("fracaso", "No se pudo crear la nueva categoria");
			}
		}
		
		if(funcion.isAnyUser(request)) {
			mav.addObject("isUser", true);
		}else {
			mav.addObject("isNoUser", true);
			System.out.println("No se valida si es usuario");
		}
		
		return mav;
	}
	
	@PostMapping("/nueva")
	public String crearCategoria(@ModelAttribute(name="categoriaModel") CategoriaModel categoriaModel,@PathVariable("id_bib") int id_bib,HttpServletRequest request ) {
		if(null != categoria.addCategoria(categoriaModel)) {
			request.setAttribute("addCategoria", true);
		}else {
			request.setAttribute("addCategoria", false);
		}
		String redirect="redirect:/bib404/"+id_bib+"/dashboard/categorias";
		return redirect;
	}
	
	

}
