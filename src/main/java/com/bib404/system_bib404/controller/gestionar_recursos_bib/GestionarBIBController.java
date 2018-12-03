package com.bib404.system_bib404.controller.gestionar_recursos_bib;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bib404.system_bib404.Repository.BibliotecaRepository;
import com.bib404.system_bib404.Repository.DetalleRecursoRepository;
import com.bib404.system_bib404.Repository.PerfilRepository;
import com.bib404.system_bib404.Repository.RecursoBibliotecarioRepository;
import com.bib404.system_bib404.Repository.TipoRecursoRepository;
import com.bib404.system_bib404.constant.Constante;
import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.entity.Biblioteca;
import com.bib404.system_bib404.entity.DetalleRecurso;
import com.bib404.system_bib404.entity.Municipio;
import com.bib404.system_bib404.entity.RecursoBibliotecario;
import com.bib404.system_bib404.entity.RecursoEspecifico;
import com.bib404.system_bib404.entity.TipoRecurso;
import com.bib404.system_bib404.entity.Usuario;
import com.bib404.system_bib404.model.ObjectAux;
import com.bib404.system_bib404.service.impl.FileServiceImpl;
import com.bib404.system_bib404.service.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/")
public class GestionarBIBController {
	
	@Autowired
	@Qualifier("TipoRecursoRepository")
	private TipoRecursoRepository trr;	
	
	@Autowired
	@Qualifier("fileServiceImpl")
	private FileServiceImpl file;

	
	@Autowired
	@Qualifier("recursoBibliotecarioRepository")
	private RecursoBibliotecarioRepository rbr;
	
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioServiceImpl usuarioImp;
	
	@Autowired
	@Qualifier("bibliotecaRepository")
	private BibliotecaRepository br;
	@Autowired
	@Qualifier("detalleRecursoRepository")
	private DetalleRecursoRepository detalle;
	
	@GetMapping("/recursos")
	public String redirectPerfilForm(Model model,HttpServletRequest request) {
		Biblioteca busca=new Biblioteca();
		HttpSession session = request.getSession();
		Usuario user =(Usuario) session.getAttribute(Constante.USER);
		busca=br.buscarBiblioteca(user.getBiblioteca().getId());
	
	
		List<TipoRecurso> tr = new ArrayList<TipoRecurso>();
		List<RecursoBibliotecario> lrb= new ArrayList<RecursoBibliotecario>();
		lrb=rbr.findByBibliotecaId(busca.getId());
		tr=trr.findAll();
		
		HttpSession sesion = request.getSession();
			    
		model.addAttribute("TipoRecurso", tr);
        model.addAttribute("recursoBib", lrb);
        model.addAttribute("bib",usuarioImp.findBibByUser(user.getUsername()));
        model.addAttribute("exito", true);
        model.addAttribute("mensaje","estos son los recursos");
        model.addAttribute("subirFileRB", "/foto");
        model.addAttribute("objectAux", new ObjectAux());
        
        if (session.getAttribute("addFile") != null) {
			if ((boolean) session.getAttribute("addFile")) {
				model.addAttribute("exito", "Se agrego exitosamente el Recurso Especifico");
			} else {
				model.addAttribute("fracaso", "No se pudo crear el Recuros Especifico");
			}
			session.removeAttribute("addFile");
		}
        
        
		return Template.GESTION;
		
	}
	
	@PostMapping("/foto")
	public String subirFoto(@ModelAttribute(name = "objectAux") ObjectAux ox, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String redirect = "redirect:/recursos";
		
		MultipartFile archivo = ox.getFile();
		RecursoBibliotecario rec_bib = rbr.findById(ox.getId_object()).get();
		
		String nombreFoto = "nada";
		if (!archivo.isEmpty()) {
			try {
				nombreFoto = file.subirFile(archivo, 1);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ocurrio un error al subir foto");
				session.setAttribute("addFile", false);
				return redirect;
			}
		}
		
		try {
			if (rec_bib.getImagen_recurso_bibl() != "nada") {// hay foto, eliminarla
				String nombreLastFile = rec_bib.getImagen_recurso_bibl();
				if (file.eliminarFile(nombreLastFile, 1)) {
					System.out.println("eliminando foto " + nombreLastFile);
				} else {
					System.out.println("no se pudo eliminar " + nombreLastFile);
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ocurrio un error");
		}
		
		
		rec_bib.setImagen_recurso_bibl(nombreFoto);
		rbr.save(rec_bib);
		System.out.println("todo correctamente");
		session.setAttribute("addFile", true);
		
		return redirect;
	}
	
	@PostMapping("/addRecurso")
	public String addContra(@Valid@ModelAttribute("RecursoBibliotecario") RecursoBibliotecario recurso,@ModelAttribute("tipoR") String tipR,@ModelAttribute("bib") String bib, Model model,HttpServletRequest request) {
		HttpSession sesion = request.getSession();
		TipoRecurso tiporecurso;
		Biblioteca biblioteca;
		int id_tipo = Integer.parseInt(tipR);
		Usuario user =(Usuario) sesion.getAttribute(Constante.USER);
		biblioteca=br.buscarBiblioteca(user.getBiblioteca().getId());
		tiporecurso=trr.buscarRecurso(id_tipo);
		biblioteca=br.buscarBiblioteca(biblioteca.getId());
		recurso.setTipo_recurso(tiporecurso);
		recurso.setBiblioteca(biblioteca);
		recurso.setImagen_recurso_bibl("nada");

		rbr.save(recurso);

		return "redirect:/recursos";
}
	
			
	
@PostMapping("/removeRecurso")

public String removerRecurso(@RequestParam("dato") String dato) {
	int id_dato = Integer.parseInt(dato);
	rbr.deleteById(id_dato);
	   
	return "redirect:/recursos";
}


@GetMapping("/Buscar")


public String buscar(Model model,HttpServletRequest request,@RequestParam("busqueda") String busqueda){
	Biblioteca busca=new Biblioteca();
	HttpSession session = request.getSession();
	Usuario user =(Usuario) session.getAttribute(Constante.USER);
TipoDigitaloFisico digi= new TipoDigitaloFisico();		
	digi.setId(1);
	digi.setTipo("Digital");
	TipoDigitaloFisico fisico= new TipoDigitaloFisico();
	fisico.setId(2);
	fisico.setTipo("Fisico");
	List <TipoDigitaloFisico> df= new ArrayList<TipoDigitaloFisico>();
	busca=br.buscarBiblioteca(user.getBiblioteca().getId());


	List<TipoRecurso> tr = new ArrayList<TipoRecurso>();
	tr=trr.findAll();
	df.add(digi);
	df.add(fisico);
	String buscando=busqueda+'%';
	
	System.out.println(buscando+"id"+busca.getId());
	List<RecursoBibliotecario> lrb= new ArrayList<RecursoBibliotecario>();
	
	lrb=rbr.buscarecurso(buscando,busca.getId());

	if (lrb.size() > 0) {
		model.addAttribute("exito",true);
		model.addAttribute("recursoBib", lrb);
		model.addAttribute("mensaje","resultados de la busqueda");
		
	} else {
		model.addAttribute("exito",false);
		model.addAttribute("mensaje", "No hay resultados que coincidan con la busqueda");
	}




	model.addAttribute("TipoRecurso", tr);

	model.addAttribute("tipDF", df);
	model.addAttribute("bib",usuarioImp.findBibByUser(user.getUsername()));
	return Template.GESTION;
}


}