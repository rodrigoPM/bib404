package com.bib404.system_bib404.controller.gestionar_recursos_bib;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bib404.system_bib404.Repository.BibliotecaRepository;
import com.bib404.system_bib404.Repository.PerfilRepository;
import com.bib404.system_bib404.Repository.RecursoBibliotecarioRepository;
import com.bib404.system_bib404.Repository.TipoRecursoRepository;
import com.bib404.system_bib404.constant.Constante;
import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.entity.Biblioteca;
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
	
	
	@GetMapping("/recursos")
	public String redirectPerfilForm(Model model,HttpServletRequest request) {
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
		List<RecursoBibliotecario> lrb= new ArrayList<RecursoBibliotecario>();
		lrb=rbr.findByBibliotecaId(busca.getId());
		tr=trr.findAll();
		df.add(digi);
		df.add(fisico);
		
		HttpSession sesion = request.getSession();
			    
		model.addAttribute("TipoRecurso", tr);
        model.addAttribute("recursoBib", lrb);
        model.addAttribute("tipDF", df);
        model.addAttribute("fisico", fisico);
        model.addAttribute("digital", digi);
        model.addAttribute("bibliotecas", usuarioImp.listBibliotecas());
        model.addAttribute("bib",usuarioImp.findBibByUser(user.getUsername()));
        model.addAttribute("exito", true);
        model.addAttribute("mensaje","estos son los recursos");
		return Template.GESTION;
		
	}
	
	
	@PostMapping("/addRecurso")
	public String addContra(@Valid@ModelAttribute("RecursoBibliotecario") RecursoBibliotecario recurso,@ModelAttribute("tipoR") String tipR,@ModelAttribute("bib") String bib,@ModelAttribute("forRecurso") String df, Model model,HttpServletRequest request) {
		HttpSession sesion = request.getSession();
		TipoRecurso tiporecurso;
		Biblioteca biblioteca;
		int id_tipo = Integer.parseInt(tipR);
		Usuario user =(Usuario) sesion.getAttribute(Constante.USER);
		biblioteca=br.buscarBiblioteca(user.getBiblioteca().getId());
		int num=Integer.parseInt(df);
		tiporecurso=trr.buscarRecurso(id_tipo);
		biblioteca=br.buscarBiblioteca(biblioteca.getId());
		recurso.setTipo_recurso(tiporecurso);
		recurso.setBiblioteca(biblioteca);
		
	if(num==1)
	{
		recurso.setDigital_recurso_bib(true);
		recurso.setFisico_recurso_bib(false);
	}
	else {
		
		recurso.setFisico_recurso_bib(true);
		recurso.setDigital_recurso_bib(false);
	}
		
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