package com.bib404.system_bib404.controller.gestionar_bibliotecas;

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
import com.bib404.system_bib404.entity.Biblioteca;
import com.bib404.system_bib404.model.ObjectAux;
import com.bib404.system_bib404.service.impl.Functions;
import com.bib404.system_bib404.service.impl.BibliotecaServiceImpl;

@Controller
@RequestMapping(Url.GESTION_BIBS) // --> /bib404/gestionar_bibliotecas
public class GestionBibController {
	@Autowired
	@Qualifier("Functions")
	private Functions funcion;

	@Autowired
	@Qualifier("bibliotecaServiceImpl")
	private BibliotecaServiceImpl biblioteca;

	@RequestMapping("")
	public ModelAndView gestionarBibliotecas(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView mav = new ModelAndView(Template.GESTION_BIBS);
		mav.addObject("titulo", "Gestion Bibs");
		mav.addObject("borrarBiblioteca", "/bib404/gestionar_bibliotecas/borrar"); // action del form eliminar biblioteca
		mav.addObject("buscarBiblioteca", "/bib404/gestionar_bibliotecas/buscar"); // action del form buscar biblioteca
		mav.addObject("objectAux", new ObjectAux());
		if (biblioteca.listAllBibliotecas().size() > 0) {
			mav.addObject("bibliotecas", biblioteca.listAllBibliotecas());
		}else{
			mav.addObject("bibCero", "no hay bibs");
		}
		
		if (session.getAttribute("deleteBiblioteca") != null) {
			if ((boolean) session.getAttribute("deleteBiblioteca")) {
				mav.addObject("exito", "La Biblioteca solicitada fue eliminada con exito");
			} else {
				mav.addObject("fracaso", session.getAttribute("deleteError"));
			}
			session.removeAttribute("deleteBiblioteca");
			session.removeAttribute("deleteError");
		}

		if (funcion.isAnyUser(request)) {
			mav.addObject("isUser", true);
		} else {
			mav.addObject("isNoUser", true);
			System.out.println("No se valida si es usuario");
		}
		return mav;
	}

	@GetMapping("/buscar")
	public ModelAndView buscar(@RequestParam(name = "str", required = false, defaultValue = "all") String str,
			HttpServletRequest request) {

		ModelAndView mav = new ModelAndView(Template.GESTION_BIBS);
		mav.addObject("titulo", "Gestion Bibs");
		mav.addObject("borrarBiblioteca", "/bib404/gestionar_bibliotecas/borrar"); // action del form eliminar categoria
		mav.addObject("buscarBiblioteca", "/bib404/gestionar_bibliotecas/buscar"); // action del form buscar biblioteca
		mav.addObject("objectAux", new ObjectAux());

		// buscar por nombre o codigo de la biblioteca
		List<Biblioteca> bibs = biblioteca.listAllBibliotecas();
		List<Biblioteca> bibsBuscadas = new ArrayList<Biblioteca>();
		if (!str.equals("all")) {
			for (Biblioteca bib : bibs) {
				if (bib.getNombre_biblioteca().toLowerCase().contains(str.toLowerCase())
						|| bib.getCodigo_biblioteca().toLowerCase().contains(str.toLowerCase())) {
					bibsBuscadas.add(bib);
				}
			}
		} else {
			bibsBuscadas = bibs;
		}

		if (bibsBuscadas.size() > 0) {
			mav.addObject("bibliotecas", bibsBuscadas);
			mav.addObject("exito", "Las bibliotecas encontradas con el termino " + str.toUpperCase() + " son:");
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

	@PostMapping("/borrar")
	public String borraBiblioteca(@ModelAttribute(name = "objectAux") ObjectAux ox, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String redirect = "redirect:/bib404/gestionar_bibliotecas";
		// System.out.println("id a eliminar: "+ox.getId_object());
		if (!biblioteca.existsBibById(ox.getId_object())) {
			session.setAttribute("deleteBiblioteca", false);
			session.setAttribute("deleteError", "La Biblioteca a borrar no existe");
			System.out.println("La biblioteca no existe");
			return redirect;
		}
		if (biblioteca.deleteBiblioteca(ox.getId_object())) {
			session.setAttribute("deleteBiblioteca", true);
			System.out.println("Biblioteca eliminada con exito");
		} else {
			session.setAttribute("deleteBiblioteca", false);
			session.setAttribute("deleteError", "No se pudo eliminar la biblioteca solicitada");
			System.out.println("La biblioteca no se pudo eliminar");
		}
		return redirect;
	}
}
