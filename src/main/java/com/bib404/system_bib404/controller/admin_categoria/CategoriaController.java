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
import com.bib404.system_bib404.model.ObjectAux;
import com.bib404.system_bib404.entity.Categoria;
import com.bib404.system_bib404.service.impl.BibliotecaServiceImpl;
import com.bib404.system_bib404.service.impl.CategoriaServiceImpl;
import com.bib404.system_bib404.service.impl.Functions;
import com.bib404.system_bib404.component.BibliotecaConverter;

@Controller
@RequestMapping(Url.CATEGORIA) // --> /bib404/{id_bib}/dashboard/categorias
public class CategoriaController {

	@Autowired
	@Qualifier("Functions")
	private Functions funcion;

	@Autowired
	@Qualifier("bibliotecaConverter")
	private BibliotecaConverter bibCon;

	@Autowired
	@Qualifier("bibliotecaServiceImpl")
	private BibliotecaServiceImpl biblioteca;

	@Autowired
	@Qualifier("categoriaServiceImpl")
	private CategoriaServiceImpl categoria;

	@RequestMapping("")
	public ModelAndView adminCategoria(@PathVariable("id_bib") int id_bib,HttpServletRequest request) {
		if(!biblioteca.existsBibById(id_bib)){
			ModelAndView mav =new ModelAndView("redirect:/");
			return mav;
		}
		ModelAndView mav =new ModelAndView(Template.CATEGORIA);
		mav.addObject("titulo", "Categorias");
		mav.addObject("url_categoria", "/bib404/"+id_bib+"/dashboard/categorias");
		mav.addObject("name_bib", biblioteca.findById(id_bib).getNombre_biblioteca());
		mav.addObject("crearCategoria", "/bib404/"+id_bib+"/dashboard/categorias/nueva"); //action del form crear categoria
		mav.addObject("borrarCategoria", "/bib404/"+id_bib+"/dashboard/categorias/borrar"); //action del form eliminar categoria
		mav.addObject("categoriaModel", new Categoria());
		mav.addObject("objectAux", new ObjectAux());
		if(categoria.listAllCategorias(id_bib).size()>0) {
			mav.addObject("categorias", categoria.listAllCategorias(id_bib));
		}

		HttpSession session = request.getSession();
		if(session.getAttribute("addCategoria")!=null) {
			if((boolean)session.getAttribute("addCategoria")) {
				mav.addObject("exito", "Se agrego exitosamente la categoria");
			}else {
				mav.addObject("fracaso", "No se pudo crear la nueva categoria");
			}
			session.removeAttribute("addCategoria");
		}
		if(session.getAttribute("deleteCategoria")!=null) {
			if((boolean)session.getAttribute("deleteCategoria")) {
				mav.addObject("exito", "La categoria solicitada fue eliminada con exito");
			}else {
				mav.addObject("fracaso", session.getAttribute("deleteError"));
			}
			session.removeAttribute("deleteCategoria");
			session.removeAttribute("deleteError");
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
	public String crearCategoria(@ModelAttribute(name="categoriaModel") Categoria cat,@PathVariable("id_bib") int id_bib,HttpServletRequest request ) {
		HttpSession session = request.getSession();
		cat.setBiblioteca(bibCon.convertBibliotecaModel2Biblioteca(biblioteca.findById(id_bib)));
		System.out.println("foranea:"+cat.getIdCatForanea());
		if(cat.getIdCatForanea()==-1 || cat.getIdCatForanea()==0){
			cat.setCategoria(null);
		}else{
			cat.setCategoria(categoria.findByID(cat.getIdCatForanea()));
		}

		if(null != categoria.addCategoria(cat)) {
			System.out.println("categoria creada");
			session.setAttribute("addCategoria", true);
		}else {
			session.setAttribute("addCategoria", false);
			System.out.println("fallo categoria");
		}
		String redirect="redirect:/bib404/"+id_bib+"/dashboard/categorias";
		return redirect;
	}
	@PostMapping("/borrar")
	public String borrarCategoria(@ModelAttribute(name="objectAux") ObjectAux ox,@PathVariable("id_bib") int id_bib,HttpServletRequest request ){
		HttpSession session = request.getSession();
		String redirect="redirect:/bib404/"+id_bib+"/dashboard/categorias";
		System.out.println("id a eliminar: "+ox.getId_object());
		if(!categoria.existsById(ox.getId_object())){
			session.setAttribute("deleteCategoria", false);
			session.setAttribute("deleteError", "La categoria a borrar no existe");
			System.out.println("La categoria no existe");
			return redirect;
		}
		if(categoria.deleteCategoria(ox.getId_object())){
				session.setAttribute("deleteCategoria", true);
				System.out.println("Categoria eliminada con exito");
		}else{
			session.setAttribute("deleteCategoria", false);
			session.setAttribute("deleteError", "No se pudo eliminar la categoria solicitada");
			System.out.println("La categoria no se pudo eliminar");
		}
		return redirect;
	}

}
