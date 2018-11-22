package com.bib404.system_bib404.controller.gestionar_bibliotecas;

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
    public ModelAndView gestionarBibliotecas(HttpServletRequest request){
      HttpSession session = request.getSession();
      ModelAndView mav= new ModelAndView(Template.GESTION_BIBS);
      mav.addObject("titulo", "Gestion Bibs");
      mav.addObject("borrarBiblioteca", "/bib404/gestionar_bibliotecas/borrar"); //action del form eliminar categoria
      mav.addObject("objectAux", new ObjectAux());
      if(biblioteca.listAllBibliotecas().size()>0) {
        mav.addObject("bibliotecas", biblioteca.listAllBibliotecas());
  		}

  		if(session.getAttribute("deleteBiblioteca")!=null) {
  			if((boolean)session.getAttribute("deleteBiblioteca")) {
  				mav.addObject("exito", "La Biblioteca solicitada fue eliminada con exito");
  			}else {
  				mav.addObject("fracaso", session.getAttribute("deleteError"));
  			}
  			session.removeAttribute("deleteBiblioteca");
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

    @PostMapping("/borrar")
  	public String borraBiblioteca(@ModelAttribute(name="objectAux") ObjectAux ox,HttpServletRequest request ){
  		HttpSession session = request.getSession();
  		String redirect="redirect:/bib404/gestionar_bibliotecas";
  		// System.out.println("id a eliminar: "+ox.getId_object());
  		if(!biblioteca.existsBibById(ox.getId_object())){
  			session.setAttribute("deleteBiblioteca", false);
  			session.setAttribute("deleteError", "La Biblioteca a borrar no existe");
  			System.out.println("La biblioteca no existe");
  			return redirect;
  		}
  		if(biblioteca.deleteBiblioteca(ox.getId_object())){
  				session.setAttribute("deleteBiblioteca", true);
  				System.out.println("Biblioteca eliminada con exito");
  		}else{
  			session.setAttribute("deleteBiblioteca", false);
  			session.setAttribute("deleteError", "No se pudo eliminar la biblioteca solicitada");
  			System.out.println("La biblioteca no se pudo eliminar");
  		}
  		return redirect;
  	}
}
