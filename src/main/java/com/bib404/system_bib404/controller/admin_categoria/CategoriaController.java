package com.bib404.system_bib404.controller.admin_categoria;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ModelAndView adminCategoria(@PathVariable("id_bib") int id_bib, HttpServletRequest request) {
		if (!biblioteca.existsBibById(id_bib)) {
			ModelAndView mav = new ModelAndView("redirect:/");
			return mav;
		}
		ModelAndView mav = new ModelAndView(Template.CATEGORIA);
		mav.addObject("titulo", "Categorias");
		mav.addObject("url_categoria", "/bib404/" + id_bib + "/dashboard/categorias");
		mav.addObject("name_bib", biblioteca.findById(id_bib).getNombre_biblioteca());
		mav.addObject("crearCategoria", "/bib404/" + id_bib + "/dashboard/categorias/nueva"); 
		mav.addObject("borrarCategoria", "/bib404/" + id_bib + "/dashboard/categorias/borrar"); 
		mav.addObject("buscarCategoria", "/bib404/" + id_bib + "/dashboard/categorias/buscar"); 
		mav.addObject("editarCategoria", "/bib404/" + id_bib + "/dashboard/categorias/editar");
		mav.addObject("categoriaModel", new Categoria());
		mav.addObject("objectAux", new ObjectAux());
		mav.addObject("listCategorias", categoria.listAllCategorias(id_bib));
		if (categoria.listAllCategorias(id_bib).size() > 0) {
			mav.addObject("categorias", categoria.listAllCategorias(id_bib));
		}

		HttpSession session = request.getSession();
		if (session.getAttribute("addCategoria") != null) {
			if ((boolean) session.getAttribute("addCategoria")) {
				mav.addObject("exito", "Se agrego exitosamente la categoria");
			} else {
				mav.addObject("fracaso", "No se pudo crear la nueva categoria");
			}
			session.removeAttribute("addCategoria");
		}
		if (session.getAttribute("deleteCategoria") != null) {
			if ((boolean) session.getAttribute("deleteCategoria")) {
				mav.addObject("exito", "La categoria solicitada fue eliminada con exito");
			} else {
				mav.addObject("fracaso", session.getAttribute("deleteError"));
			}
			session.removeAttribute("deleteCategoria");
			session.removeAttribute("deleteError");
		}
		if (session.getAttribute("updateCategoria") != null) {
			if ((boolean) session.getAttribute("updateCategoria")) {
				mav.addObject("exito", "La categoria solicitada se modifico con exito");
			} else {
				mav.addObject("fracaso", "La categoria no se pudo modificar");
			}
			session.removeAttribute("updateCategoria");
		}

		if (funcion.isAnyUser(request)) {
			mav.addObject("isUser", true);
		} else {
			mav.addObject("isNoUser", true);
			System.out.println("No se valida si es usuario");
		}

		mav.addObject("catService", categoria);
		mav.addObject("id_bib", id_bib);

		return mav;
	}

	@GetMapping("/buscar")
	public ModelAndView buscar(@PathVariable("id_bib") int id_bib,
			@RequestParam(name = "str", required = false, defaultValue = "all") String str,
			HttpServletRequest request) {
		if (!biblioteca.existsBibById(id_bib)) {
			ModelAndView mav = new ModelAndView("redirect:/");
			return mav;
		}
		ModelAndView mav = new ModelAndView(Template.CATEGORIA);
		mav.addObject("titulo", "Categorias");
		mav.addObject("url_categoria", "/bib404/" + id_bib + "/dashboard/categorias");
		mav.addObject("name_bib", biblioteca.findById(id_bib).getNombre_biblioteca());
		mav.addObject("crearCategoria", "/bib404/" + id_bib + "/dashboard/categorias/nueva"); 
		mav.addObject("borrarCategoria", "/bib404/" + id_bib + "/dashboard/categorias/borrar"); 
		mav.addObject("buscarCategoria", "/bib404/" + id_bib + "/dashboard/categorias/buscar"); 
		mav.addObject("editarCategoria", "/bib404/" + id_bib + "/dashboard/categorias/editar");
		mav.addObject("categoriaModel", new Categoria());
		mav.addObject("objectAux", new ObjectAux());
		mav.addObject("listCategorias", categoria.listAllCategorias(id_bib));

		// buscar por nombre de categoria
		List<Categoria> categorias = categoria.listAllCategorias(id_bib);
		List<Categoria> categoriasBuscadas = new ArrayList<Categoria>();
		if (!str.equals("all")) {
			for (Categoria cat : categorias) {
				if (cat.getNombre_categoria().toLowerCase().contains(str.toLowerCase())) {
					categoriasBuscadas.add(cat);
				}
			}
		} else {
			System.out.println("copiando");
			categoriasBuscadas = categorias;
		}

		if (categoriasBuscadas.size() > 0) {
			mav.addObject("categorias", categoriasBuscadas);
			mav.addObject("exito", "Las categorias encontradas con el termino " + str.toUpperCase() + " son:");
		} else {
			mav.addObject("fracaso", "No hay resultados que coincidan con la busqueda");
		}

		if (funcion.isAnyUser(request)) {
			mav.addObject("isUser", true);
		} else {
			mav.addObject("isNoUser", true);
			System.out.println("No se valida si es usuario");
		}
		return mav;
	}

	@PostMapping("/nueva")
	public String crearCategoria(@ModelAttribute(name = "categoriaModel") Categoria cat,
			@PathVariable("id_bib") int id_bib, HttpServletRequest request) {
		HttpSession session = request.getSession();
		cat.setBiblioteca(bibCon.convertBibliotecaModel2Biblioteca(biblioteca.findById(id_bib)));
		System.out.println("foranea:" + cat.getCategoria_id());

		if (null != categoria.addCategoria(cat)) {
			System.out.println("categoria creada");
			session.setAttribute("addCategoria", true);
		} else {
			session.setAttribute("addCategoria", false);
			System.out.println("fallo categoria");
		}
		String redirect = "redirect:/bib404/" + id_bib + "/dashboard/categorias";
		return redirect;
	}

	@PostMapping("/borrar")
	public String borrarCategoria(@ModelAttribute(name = "objectAux") ObjectAux ox, @PathVariable("id_bib") int id_bib,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		String redirect = "redirect:/bib404/" + id_bib + "/dashboard/categorias";
		System.out.println("id a eliminar: " + ox.getId_object());
		if (!categoria.existsById(ox.getId_object())) {
			session.setAttribute("deleteCategoria", false);
			session.setAttribute("deleteError", "La categoria a borrar no existe");
			System.out.println("La categoria no existe");
			return redirect;
		}
		if (categoria.deleteCategoria(ox.getId_object())) {
			session.setAttribute("deleteCategoria", true);
			System.out.println("Categoria eliminada con exito");
		} else {
			session.setAttribute("deleteCategoria", false);
			session.setAttribute("deleteError", "No se pudo eliminar la categoria solicitada");
			System.out.println("La categoria no se pudo eliminar");
		}
		return redirect;
	}
	@PostMapping("/editar")
	public String editarCategoria(@ModelAttribute(name = "cat") Categoria cat, @PathVariable("id_bib") int id_bib,
			HttpServletRequest request){
		HttpSession session = request.getSession();
		String redirect = "redirect:/bib404/" + id_bib + "/dashboard/categorias";
		Categoria catMod=categoria.findByID(cat.getId());
		catMod.setDescripcion_categoria(cat.getDescripcion_categoria());
		catMod.setNombre_categoria(cat.getNombre_categoria());
		catMod.setCategoria_id(cat.getCategoria_id());
		
		if (null != categoria.updateCategoria(catMod)) {
			System.out.println("categoria modificada");
			session.setAttribute("updateCategoria", true);
		} else {
			session.setAttribute("updateCategoria", false);
			System.out.println("fallo categoria");
		}

		System.out.println("la biblioteca de categoria: "+catMod.getId()+" "+catMod.getBiblioteca().getCodigo_biblioteca());
		
		return redirect;
	}

}
