package com.bib404.system_bib404.controller.gestionar_admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

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

import com.bib404.system_bib404.Repository.ConsultasRE;
import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.entity.Usuario;
import com.bib404.system_bib404.service.impl.UsuarioServiceImpl;
import com.bib404.system_bib404.service.EncriptadoPass;

@Controller
@RequestMapping("/bib404/gestion_admin")
public class GestionAdminController {
    @Autowired
    @Qualifier("consultasRE")
    ConsultasRE consultas;
    
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioServiceImpl usuarioServiceImpl;
	@Autowired
	@Qualifier("encriptadoPass")
	private EncriptadoPass encriptado;
	
	@RequestMapping("/lista_administradores")
	public ModelAndView listAdmin(HttpServletRequest request, Model model) {
        ModelAndView modelAndView = new ModelAndView(Template.GESTION_ADMIN);
        modelAndView.addObject("administradores", consultas.obtenerAdmins());
        return modelAndView;
	}

    @GetMapping("/eliminar_administrador")
    public ModelAndView eliminarAdmin(@RequestParam(name = "id", required = true) int id,HttpServletRequest request){
        usuarioServiceImpl.deleteUser(id);
        ModelAndView modelAndView = new ModelAndView(Template.GESTION_ADMIN);
        modelAndView.addObject("administradores", consultas.obtenerAdmins());
        return modelAndView;
    }
    
	@GetMapping("/editar_administrador")
	public ModelAndView editarAdmin(@RequestParam(name="id", required=false)int id, Model model,
			HttpServletRequest request) throws ServletException, IOException  {
		Usuario admin= new Usuario();
		admin=usuarioServiceImpl.findById(id);
		ModelAndView mav = new ModelAndView(Template.ADMIN_EDITAR);
		model.addAttribute("admin", admin);
		model.addAttribute("biblioteca",admin.getBiblioteca());
		model.addAttribute("bibliotecas",usuarioServiceImpl.listBibliotecas());
		return mav;	
	}
	@PostMapping("/actualizar")
	public ModelAndView updateAdmin(Model model, @RequestParam(name="id") int id,
			@ModelAttribute("nombre") String nombre,
			@ModelAttribute("password") String password,
			@ModelAttribute("bib") int bib_id, 
			HttpServletRequest request)  throws ServletException, IOException  {
			Usuario admin=new Usuario();
			admin=usuarioServiceImpl.findById(id);
			admin.setNombre(nombre);
			admin.setPassword(password);
			admin.setBiblioteca(usuarioServiceImpl.findBibBy(bib_id));
			String pass = encriptado.Encriptar(admin.getPassword());
			admin.setPassword(pass);
            usuarioServiceImpl.updateUser(admin);
		    return listAdmin(request,model);			
	}
}
