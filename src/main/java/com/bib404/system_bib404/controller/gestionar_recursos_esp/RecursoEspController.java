package com.bib404.system_bib404.controller.gestionar_recursos_esp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bib404.system_bib404.constant.Constante;
import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.constant.Url;
import com.bib404.system_bib404.entity.DetalleRecurso;
import com.bib404.system_bib404.entity.RecursoBibliotecario;
import com.bib404.system_bib404.entity.RecursoEspecifico;
import com.bib404.system_bib404.entity.Usuario;
import com.bib404.system_bib404.model.ObjectAux;
import com.bib404.system_bib404.service.impl.BibliotecaServiceImpl;
import com.bib404.system_bib404.service.impl.DetalleRecursoServiceImpl;
import com.bib404.system_bib404.service.impl.FileServiceImpl;
import com.bib404.system_bib404.service.impl.FormatoRecursoServiceImpl;
import com.bib404.system_bib404.service.impl.RecursoBibliotecarioServiceImpl;
import com.bib404.system_bib404.service.impl.RecursoEspecificoServiceImpl;
import com.bib404.system_bib404.service.impl.Functions;

@Controller
@RequestMapping(Url.GESTION_REC_ESP) // --> /bib404/{id_bib}/{id_rb}/recurso_especifico
public class RecursoEspController {

	@Autowired
	@Qualifier("Functions")
	private Functions funcion;

	@Autowired
	@Qualifier("bibliotecaServiceImpl")
	private BibliotecaServiceImpl biblioteca;

	@Autowired
	@Qualifier("recursoBibliotecarioServiceImpl")
	private RecursoBibliotecarioServiceImpl rb;

	@Autowired
	@Qualifier("recursoEspecificoServiceImpl")
	private RecursoEspecificoServiceImpl re;

	@Autowired
	@Qualifier("formatoRecursoServiceImpl")
	private FormatoRecursoServiceImpl fr;

	@Autowired
	@Qualifier("detalleRecursoServiceImpl")
	private DetalleRecursoServiceImpl dr;

	@Autowired
	@Qualifier("fileServiceImpl")
	private FileServiceImpl file;

	@RequestMapping("")
	public ModelAndView gestionRecursoEspecifico(@PathVariable("id_bib") int id_bib, @PathVariable("id_rb") int id_rb,
			HttpServletRequest request) {
		if (!biblioteca.existsBibById(id_bib) || !rb.existsById(id_rb)) {
			ModelAndView mav = new ModelAndView("redirect:/");
			return mav;
		}
		if (!funcion.isAdmin(request)) {
			ModelAndView mav = new ModelAndView("redirect:/");
			return mav;
		}
		HttpSession session = request.getSession();
		ModelAndView mav = new ModelAndView(Template.GESTION_REC_ESP);
		mav.addObject("titulo", "Recursos Especificos");
		mav.addObject("urlHome", "/bib404/" + id_bib);
		// mav.addObject("url_rec_esp",
		// "/bib404/"+id_bib+"/"+id_rb+"/recurso_especifico");
		mav.addObject("name_bib", biblioteca.findById(id_bib).getNombre_biblioteca());
		mav.addObject("name_rb", rb.findById(id_rb).getNombre_recurso_bib());
		mav.addObject("formatos", fr.listAllFormatoRec());
		mav.addObject("crearRecEsp", "/bib404/" + id_bib + "/" + id_rb + "/recurso_especifico/nueva");
		mav.addObject("borrarRecEsp", "/bib404/" + id_bib + "/" + id_rb + "/recurso_especifico/borrar");
		mav.addObject("subirFileRecEsp", "/bib404/" + id_bib + "/" + id_rb + "/recurso_especifico/subir_file");
		mav.addObject("buscarRecEsp", "/bib404/" + id_bib + "/" + id_rb + "/recurso_especifico/buscar");// buscar
		mav.addObject("editarRecEsp", "/bib404/" + id_bib + "/" + id_rb + "/recurso_especifico/editar");// editar
		mav.addObject("recEspModel", new RecursoEspecifico());
		mav.addObject("objectAux", new ObjectAux());

		if (re.listAllRecEsp(id_rb).size() > 0) {
			mav.addObject("recEsps", re.listAllRecEsp(id_rb));
		} else {
			mav.addObject("reCero", "no hay re");
		}

		if (session.getAttribute("addRE") != null) {
			if ((boolean) session.getAttribute("addRE")) {
				mav.addObject("exito", "Se agrego exitosamente el Recurso Especifico");
			} else {
				mav.addObject("fracaso", "No se pudo crear el Recuros Especifico");
			}
			session.removeAttribute("addRE");
		}
		if (session.getAttribute("deleteRE") != null) {
			if ((boolean) session.getAttribute("deleteRE")) {
				mav.addObject("exito", "El recurso Especifico solicitado fue eliminada con exito");
			} else {
				mav.addObject("fracaso", session.getAttribute("deleteError"));
			}
			session.removeAttribute("deleteRE");
			session.removeAttribute("deleteError");
		}
		if (session.getAttribute("addFile") != null) {
			if ((boolean) session.getAttribute("addFile")) {
				mav.addObject("exito", "El archivo se subio correctamente");
			} else {
				mav.addObject("fracaso", "El archivo no se pudo subir, ocurrio un error");
			}
			session.removeAttribute("addFile");
		}
		if (session.getAttribute("reMod") != null) {
			if ((boolean) session.getAttribute("reMod")) {
				mav.addObject("exito", "El recurso especifico se modifico con exito");
			} else {
				mav.addObject("fracaso", "No se pudo modificar el recurso especifico");
			}
			session.removeAttribute("reMod");
		}

		if (funcion.isAnyUser(request)) {
			mav.addObject("isUser", true);
		} else {
			mav.addObject("isNoUser", true);
			System.out.println("No se valida si es usuario");
		}

		Usuario user = (Usuario) session.getAttribute(Constante.USER);
		mav.addObject("user", user);
		return mav;
	}

	@GetMapping("/buscar")
	public ModelAndView buscar(@RequestParam(name = "str", required = false, defaultValue = "all") String str,
			@PathVariable("id_bib") int id_bib, @PathVariable("id_rb") int id_rb, HttpServletRequest request) {
		if (!biblioteca.existsBibById(id_bib) || !rb.existsById(id_rb)) {
			ModelAndView mav = new ModelAndView("redirect:/");
			return mav;
		}
		if (!funcion.isAdmin(request)) {
			ModelAndView mav = new ModelAndView("redirect:/");
			return mav;
		}
		HttpSession session = request.getSession();
		ModelAndView mav = new ModelAndView(Template.GESTION_REC_ESP);
		mav.addObject("titulo", "Recursos Especificos");
		mav.addObject("urlHome", "/bib404/" + id_bib);
		// mav.addObject("url_rec_esp",
		// "/bib404/"+id_bib+"/"+id_rb+"/recurso_especifico");
		mav.addObject("name_bib", biblioteca.findById(id_bib).getNombre_biblioteca());
		mav.addObject("name_rb", rb.findById(id_rb).getNombre_recurso_bib());
		mav.addObject("formatos", fr.listAllFormatoRec());
		mav.addObject("crearRecEsp", "/bib404/" + id_bib + "/" + id_rb + "/recurso_especifico/nueva"); 
		mav.addObject("borrarRecEsp", "/bib404/" + id_bib + "/" + id_rb + "/recurso_especifico/borrar"); 
		mav.addObject("subirFileRecEsp", "/bib404/" + id_bib + "/" + id_rb + "/recurso_especifico/subir_file"); 
		mav.addObject("buscarRecEsp", "/bib404/" + id_bib + "/" + id_rb + "/recurso_especifico/buscar");// buscar
		mav.addObject("editarRecEsp", "/bib404/" + id_bib + "/" + id_rb + "/recurso_especifico/editar");// editar
		mav.addObject("recEspModel", new RecursoEspecifico());
		mav.addObject("objectAux", new ObjectAux());


		List<RecursoEspecifico> rec_esp_all = re.listAllRecEsp(id_rb);
		List<RecursoEspecifico> reBuscados = new ArrayList<RecursoEspecifico>();
		boolean interno=false;
		if(str.toLowerCase().equals("false") || str.toLowerCase().equals("true")){
			interno=true;
		}
		if(!str.equals("all")){
			for(RecursoEspecifico rec:rec_esp_all){
				if(interno){
					if(rec.isConsulta_interna()==(boolean)Boolean.getBoolean(str.toLowerCase())){
						reBuscados.add(rec);
					}
				}else{
					if(rec.getEditorial().toLowerCase().contains(str.toLowerCase()) ||
				   	   rec.getAutores().toLowerCase().contains(str.toLowerCase())){
						reBuscados.add(rec);
					}
				}
			}
		}else{
			reBuscados=rec_esp_all;
		}

		if (reBuscados.size() > 0) {
			mav.addObject("recEsps", reBuscados);
			if(interno){
				mav.addObject("exito", "Los recursos especificos con consulta interna " + str.toUpperCase() + " son:");
			}else{
				mav.addObject("exito", "Los recursos especificos encontradas con el termino " + str.toUpperCase() + " son:");
			}
			
		} else {
			mav.addObject("fracaso", "No hay resultados que coincidan con la busqueda");
		}
		
		Usuario user = (Usuario) session.getAttribute(Constante.USER);
		mav.addObject("user", user);
		return mav;
	}

	@PostMapping("/nueva")
	public String crearRecEsp(@ModelAttribute(name = "recEspModel") RecursoEspecifico rec_esp,
			@PathVariable("id_bib") int id_bib, @PathVariable("id_rb") int id_rb, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String redirect = "redirect:/bib404/" + id_bib + "/" + id_rb + "/recurso_especifico";
		if (!biblioteca.existsBibById(id_bib) || !rb.existsById(id_rb)) {
			return redirect;
		}
		

		rec_esp.setFormato_recurso(fr.findById(rec_esp.getIdFormatoRecurso()));
		System.out.println(rec_esp.getFormato_recurso().getNombre_formato());
		if (rec_esp.getFormato_recurso().getId() != 1) {
			rec_esp.setConsulta_interna(true);
		} else {
			rec_esp.setConsulta_interna(false);
		}
		rec_esp.setPrestado(false);
		rec_esp.setArchivo("nada");
		rec_esp.setCodigo_rec_esp("rb" + id_rb + "->re" + re.listAllRecEsp(id_rb).size());

		DetalleRecurso detalleRecurso = new DetalleRecurso();
		RecursoBibliotecario rec_bib = rb.findById(id_rb);
		if (re.listAllRecEsp(id_rb).size() > 0) {
			DetalleRecurso lastDR = dr.getLastDRbyRB(id_rb);
			detalleRecurso.setTotal_rec_bib(lastDR.getTotal_rec_bib() + 1);
			rec_bib.setTotal_recurso_bib(lastDR.getTotal_rec_bib() + 1);
			if (rec_esp.getFormato_recurso().getId() != 1) {//diferente de fisico
				detalleRecurso.setTotal_dig_rec_bib(lastDR.getTotal_dig_rec_bib() + 1);
				rec_bib.setDigital_recurso_bib(true);
			} else {
				detalleRecurso.setTotal_fis_rec_bib(lastDR.getTotal_fis_rec_bib() + 1);
				rec_bib.setFisico_recurso_bib(true);
			}
		} else {
			detalleRecurso.setTotal_rec_bib(1);
			rec_bib.setTotal_recurso_bib(1);
			if (rec_esp.getFormato_recurso().getId() != 1) {
				detalleRecurso.setTotal_dig_rec_bib(1);
				detalleRecurso.setTotal_fis_rec_bib(0);
				rec_bib.setDigital_recurso_bib(true);
			} else {
				detalleRecurso.setTotal_fis_rec_bib(1);
				detalleRecurso.setTotal_dig_rec_bib(0);
				rec_bib.setFisico_recurso_bib(true);
			}
		}
		//guardando rec_bib
		rb.updateRB(rec_bib);

		/* DateFormat dateFormat= new SimpleDateFormat("HH:mm:ss"); */
		detalleRecurso.setCreatedAt(new Date());
		detalleRecurso.setFecha_ingreso_r_e(new Date());
		detalleRecurso.setRecurso_bib(rb.findById(id_rb));

		detalleRecurso.setRecurso_especifico(rec_esp);
		rec_esp.setDetalle_recurso(detalleRecurso);

		if (dr.addDetalleRecurso(detalleRecurso) != null) {
			System.out.println("Recurso Especifico creado");
			session.setAttribute("addRE", true);
		} else {
			session.setAttribute("addRE", false);
			System.out.println("fallo agregar recurso especifico");
		}

		return redirect;
	}

	@PostMapping("/borrar")
	public String borrarRecEsp(@ModelAttribute(name = "objectAux") ObjectAux ox, @PathVariable("id_bib") int id_bib,
			@PathVariable("id_rb") int id_rb, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String redirect = "redirect:/bib404/" + id_bib + "/" + id_rb + "/recurso_especifico";

		if (!biblioteca.existsBibById(id_bib) || !rb.existsById(id_rb)) {
			return redirect;
		}
		if (!re.existsById(ox.getId_object())) {
			session.setAttribute("deleteRE", false);
			session.setAttribute("deleteError", "El recurso especifico a borrar no existe");
			System.out.println("El recurso no existe");
			return redirect;
		}
		String nombreLastFile = null;
		boolean foto = false;
		RecursoBibliotecario rec_bib = rb.findById(id_rb);
		if (re.listAllRecEsp(id_rb).size() > 0) {
			if (re.findById(ox.getId_object()).getArchivo() != "nada") {// hay foto, eliminarla
				nombreLastFile = re.findById(ox.getId_object()).getArchivo();
				foto = true;
			}
			if (re.listAllRecEsp(id_rb).size() > 1) {
				DetalleRecurso lastDR = dr.getLastDRbyRB(id_rb);
				String formato = re.findById(ox.getId_object()).getFormato_recurso().getNombre_formato();

				if (dr.deleteDetalleRecurso(re.findById(ox.getId_object()).getDetalle_recurso().getId())) {
					session.setAttribute("deleteRE", true);
					System.out.println("detalle recurso eliminada con exito");
					if (foto) {
						if (file.eliminarFile(nombreLastFile, 2)) {
							System.out.println("eliminando archivo " + nombreLastFile);
						} else {
							System.out.println("No se pudo eliminar " + nombreLastFile);
						}
					}
					if (formato != "Fisico") {
						lastDR.setTotal_dig_rec_bib(lastDR.getTotal_dig_rec_bib() - 1);
					} else {
						lastDR.setTotal_fis_rec_bib(lastDR.getTotal_fis_rec_bib() - 1);
					}
					rec_bib.setTotal_recurso_bib(rec_bib.getTotal_recurso_bib() - 1);//actualizando total en rb
					lastDR.setTotal_rec_bib(lastDR.getTotal_dig_rec_bib() + lastDR.getTotal_fis_rec_bib());
					dr.updateDetalleRecurso(lastDR);
				} else {
					session.setAttribute("deleteRE", false);
					session.setAttribute("deleteError", "No se pudo eliminar el recurso especifico solicitada");
					System.out.println("El detalle recurso no se pudo eliminar");
				}
			} else {
				if (dr.deleteDetalleRecurso(re.findById(ox.getId_object()).getDetalle_recurso().getId())) {
					if (foto) {
						if (file.eliminarFile(nombreLastFile, 2)) {
							System.out.println("eliminando archivo " + nombreLastFile);
						} else {
							System.out.println("No se pudo eliminar " + nombreLastFile);
						}
					}
					session.setAttribute("deleteRE", true);
					System.out.println("detalle recurso eliminada con exito");
					rec_bib.setTotal_recurso_bib(0); //si solo habia uno ya no hay nada
				} else {
					session.setAttribute("deleteRE", false);
					session.setAttribute("deleteError", "No se pudo eliminar el recurso especifico solicitada");
					System.out.println("El detalle recurso no se pudo eliminar");
				}
			}
			rb.updateRB(rec_bib);
		} else {
			return redirect;
		}

		return redirect;
	}

	@PostMapping("/subir_file")
	public String uploadFile(@ModelAttribute(name = "objectAux") ObjectAux ox, @PathVariable("id_bib") int id_bib,
			@PathVariable("id_rb") int id_rb, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String redirect = "redirect:/bib404/" + id_bib + "/" + id_rb + "/recurso_especifico";
		if (!biblioteca.existsBibById(id_bib) || !rb.existsById(id_rb)) {
			return redirect;
		}
		MultipartFile archivo = ox.getFile();
		RecursoEspecifico rec_esp = re.findRecEspById(ox.getId_object());

		String nombreFile = "nada";
		if (!archivo.isEmpty()) {
			try {
				nombreFile = file.subirFile(archivo, 2);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ocurrio un error al subir archivo");
				session.setAttribute("addFile", false);
				return redirect;
			}
		}

		if (rec_esp.getArchivo() != "nada") {// hay foto, eliminarla
			String nombreLastFile = rec_esp.getArchivo();
			if (file.eliminarFile(nombreLastFile, 2)) {
				System.out.println("eliminando archivo " + nombreLastFile);
			} else {
				System.out.println("no se pudo eliminar " + nombreLastFile);
			}

		}
		rec_esp.setArchivo(nombreFile);
		dr.updateDetalleRecurso(rec_esp.getDetalle_recurso());
		System.out.println("todo correctamente");
		session.setAttribute("addFile", true);
		return redirect;
	}
	@PostMapping("editar")
	public String editarRe(@ModelAttribute(name = "recEspModel") RecursoEspecifico rec_esp, @PathVariable("id_bib") int id_bib,
			@PathVariable("id_rb") int id_rb, HttpServletRequest request){
		HttpSession session = request.getSession();
		String redirect = "redirect:/bib404/" + id_bib + "/" + id_rb + "/recurso_especifico";

		if (!biblioteca.existsBibById(id_bib) || !rb.existsById(id_rb)) {
			return redirect;
		}

		RecursoEspecifico recEsp=re.findRecEspById(rec_esp.getId());
		recEsp.setVolumen_recurso(rec_esp.getVolumen_recurso());
		recEsp.setEdicion_recurso(rec_esp.getEdicion_recurso());
		recEsp.setEditorial(rec_esp.getEditorial());
		recEsp.setAutores(rec_esp.getAutores());


		DetalleRecurso detRec=re.findById(rec_esp.getId()).getDetalle_recurso();

		if (dr.updateDetalleRecurso(detRec) != null) {
			System.out.println("Recurso Especifico modificado");
			session.setAttribute("reMod", true);
		} else {
			session.setAttribute("reMod", false);
			System.out.println("fallo modifcar recurso especifico");
		}
		
		return redirect;
	}

}
