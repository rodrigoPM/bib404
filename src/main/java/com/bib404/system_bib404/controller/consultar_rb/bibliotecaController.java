package com.bib404.system_bib404.controller.consultar_rb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.constant.Url;
import com.bib404.system_bib404.service.impl.BibliotecaServiceImpl;
import com.bib404.system_bib404.service.impl.Functions;
import com.bib404.system_bib404.service.impl.RecursoBibliotecarioServiceImpl;
import com.bib404.system_bib404.service.impl.RecursoEspecificoServiceImpl;
import com.bib404.system_bib404.entity.RecursoBibliotecario;
import com.bib404.system_bib404.entity.RecursoEspecifico;

@Controller
@RequestMapping(Url.INDEX_BIB_X) // --> /bib404
public class bibliotecaController {

	@Autowired
	@Qualifier("bibliotecaServiceImpl")
	private BibliotecaServiceImpl bibliotecaService;

	@Autowired
	@Qualifier("recursoBibliotecarioServiceImpl")
	private RecursoBibliotecarioServiceImpl rbService;

	@Autowired
	@Qualifier("recursoEspecificoServiceImpl")
	private RecursoEspecificoServiceImpl reService;

	@Autowired
	@Qualifier("Functions")
	private Functions funcion;

	@RequestMapping("/{id}")
	public String inicioBibX(@PathVariable("id") int id_bib, HttpServletRequest request, Model model)
			throws ServletException, IOException {
		if (!bibliotecaService.existsBibById(id_bib)) {
			System.out.println("No se encontro biblioteca");
			return "redirect:/";
		}

		model.addAttribute("biblioteca", bibliotecaService.findBibById(id_bib));
		model.addAttribute("name_bib", bibliotecaService.findBibById(id_bib).getNombre_biblioteca());
		model.addAttribute("urlHome", "/bib404/" + bibliotecaService.findBibById(id_bib).getId()); // url bib
		model.addAttribute("buscarBiblioteca", "/bib404/"+ bibliotecaService.findBibById(id_bib).getId()+"/buscar");

		if (funcion.isUser(request)) {
			model.addAttribute("isUser", true);
			System.out.println("Usuario simple");
		} else {
			if (funcion.isAdmin(request)) {
				model.addAttribute("isAdmin", true);
				System.out.println("admin local");
			}else {
				if(funcion.isSuperUserBIB404(request)) {
					model.addAttribute("isSuperAdmin", true);
					System.out.println("super admin");
				}else{
					model.addAttribute("isNoUser", true);
					System.out.println("no se valida usuario");
				}

			}
		}

		model.addAttribute("rbs", rbService.listAllRBOfBib(id_bib)); // listado de recursos bibliotecarios
		if (rbService.listAllRBOfBib(id_bib).size() == 0) {
			model.addAttribute("vacio", "No se encontraron Recursos bibliotecarios");
		}

		return Template.INDEX_BIB_X;
	}


	@GetMapping("/{id}/buscar")
	public String buscarRB(@RequestParam(name = "str", required = false, defaultValue = "all") String str,
				@RequestParam(name = "tipo", required = false, defaultValue = "1") int tipo,
				@PathVariable("id") int id_bib, HttpServletRequest request, Model model){
		if (!bibliotecaService.existsBibById(id_bib)) {
			System.out.println("No se encontro biblioteca");
			return "redirect:/";
		}
		model.addAttribute("biblioteca", bibliotecaService.findBibById(id_bib));
		model.addAttribute("name_bib", bibliotecaService.findBibById(id_bib).getNombre_biblioteca());
		model.addAttribute("urlHome", "/bib404/" + bibliotecaService.findBibById(id_bib).getId()); // url bib
		model.addAttribute("buscarBiblioteca", "/bib404/" + bibliotecaService.findBibById(id_bib).getId() + "/buscar");

		
		List<RecursoBibliotecario> recBibs = rbService.listAllRBOfBib(id_bib);
		List<RecursoBibliotecario> rbBuscadas = new ArrayList<RecursoBibliotecario>();
		if (!str.equals("all")) {
			System.out.println("tipo igual a: "+tipo);
			for (RecursoBibliotecario rb : recBibs) {
				if(tipo==1){//titulo
					if(rb.getNombre_recurso_bib().toLowerCase().contains(str.toLowerCase())){
						rbBuscadas.add(rb);
					}
				}
				if(tipo==2){//autores
					List<RecursoEspecifico> res=reService.listAllRecEsp(rb.getId());
					for(RecursoEspecifico recEsp:res){
						if(recEsp.getAutores().toLowerCase().contains(str.toLowerCase())){
							rbBuscadas.add(rb);
						}
					}
				}
				if(tipo==3){//tipo de recurso
					if(rb.getTipo_recurso().getNombre_tipo_recurso().toLowerCase().contains(str.toLowerCase())){
						rbBuscadas.add(rb);
					}
				}
			}
		} else {
			rbBuscadas = recBibs;
		}
		model.addAttribute("rbs", rbBuscadas);

		if (rbBuscadas.size() > 0) {
			model.addAttribute("bibliotecas", rbBuscadas);
			model.addAttribute("exito", "Los resultados encontradas con el termino " + str.toUpperCase() + " son:");
		} else {
			model.addAttribute("fracaso", "No hay resultados que coincidan con la busqueda");
		}

		if (funcion.isUser(request)) {
			model.addAttribute("isUser", true);
			System.out.println("Usuario simple");
		} else {
			if (funcion.isAdmin(request)) {
				model.addAttribute("isAdmin", true);
				System.out.println("admin local");
			} else {
				if (funcion.isSuperUserBIB404(request)) {
					model.addAttribute("isSuperAdmin", true);
					System.out.println("super admin");
				} else {
					model.addAttribute("isNoUser", true);
					System.out.println("no se valida usuario");
				}

			}
		}
		return Template.INDEX_BIB_X;
	}

}
