package com.bib404.system_bib404.controller.estad_BIB404;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bib404.system_bib404.constant.Template;
import com.bib404.system_bib404.entity.Biblioteca;
import com.bib404.system_bib404.entity.Usuario;
import com.bib404.system_bib404.model.Graf;
import com.bib404.system_bib404.service.impl.Functions;
import com.bib404.system_bib404.service.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/estadisicas")
public class EstadisticasBIB404 {
	@Autowired
	@Qualifier("Functions")
	private Functions funtions;
	
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioServiceImpl usuarioImp;
	
	@GetMapping("/BIB404")
	public ModelAndView estadisticas(HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView();
		if (funtions.isSuperUserBIB404(request)) {
			List<Biblioteca> bibliotecas = usuarioImp.listBibliotecas();
			int cantidad = bibliotecas.size();
			SimpleDateFormat formateador = new SimpleDateFormat("yyyyMMdd");
			Date f = new Date();
			String f4 = formateador.format(f);
			System.out.println(f4);
			ArrayList<Graf> graf = usuarioImp.findBibByYear(f4,31, 365, 12);
			mav.addObject("bibliotecas", bibliotecas);
			mav.addObject("cantidad", cantidad);
			mav.addObject("graf", graf);
			mav.addObject("tipo", "meses");
			Date fecha = new Date();
			int mes = fecha.getMonth() - 1;
			fecha.setMonth(mes);
			SimpleDateFormat formatos = new SimpleDateFormat("yyyy-MM-dd");
			String fecha_actual = formatos.format(new Date());
			String fecha_inicial = formatos.format(fecha); 
			mav.addObject("fecha_inicial", fecha_inicial);
			mav.addObject("fecha_actual", fecha_actual);
			mav.setViewName(Template.estadisticas);
		}else {
			mav.setViewName("/");
		}
		mav.addObject("titulo", "System BIB404");
		return mav;
	}
	
	@GetMapping("/BIB404/ultimasemana")
	public ModelAndView estadisticassemana(HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView();
		if (funtions.isSuperUserBIB404(request)) {
			List<Biblioteca> bibliotecas = usuarioImp.listBibliotecas();
			int cantidad = bibliotecas.size();
			SimpleDateFormat formateador = new SimpleDateFormat("yyyyMMdd");
			Date f = new Date();
			String f4 = formateador.format(f);
			System.out.println(f4);
			ArrayList<Graf> graf = usuarioImp.findBibByDate(f4, 7);
			mav.addObject("bibliotecas", bibliotecas);
			mav.addObject("graf", graf);
			mav.addObject("tipo", "dias");
			Date fecha = new Date();
			int mes = fecha.getMonth() - 1;
			fecha.setMonth(mes);
			SimpleDateFormat formatos = new SimpleDateFormat("yyyy-MM-dd");
			String fecha_actual = formatos.format(new Date());
			String fecha_inicial = formatos.format(fecha); 
			mav.addObject("fecha_inicial", fecha_inicial);
			mav.addObject("fecha_actual", fecha_actual);
			mav.addObject("cantidad", cantidad);
			mav.setViewName(Template.estadisticas);
		}else {
			mav.setViewName("/");
		}
		mav.addObject("titulo", "System BIB404");
		return mav;
	}
	
	@GetMapping("/BIB404/ultimomes")
	public ModelAndView estadisticasmes(HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView();
		if (funtions.isSuperUserBIB404(request)) {
			List<Biblioteca> bibliotecas = usuarioImp.listBibliotecas();
			int cantidad = bibliotecas.size();
			SimpleDateFormat formateador = new SimpleDateFormat("yyyyMMdd");
			Date f = new Date();
			String f4 = formateador.format(f);
			System.out.println(f4);
			ArrayList<Graf> graf = usuarioImp.findBibByYear(f4, 7, 31, 5);
			mav.addObject("bibliotecas", bibliotecas);
			mav.addObject("graf", graf);
			mav.addObject("tipo", "semanas");
			Date fecha = new Date();
			int mes = fecha.getMonth() - 1;
			fecha.setMonth(mes);
			SimpleDateFormat formatos = new SimpleDateFormat("yyyy-MM-dd");
			String fecha_actual = formatos.format(new Date());
			String fecha_inicial = formatos.format(fecha); 
			mav.addObject("fecha_inicial", fecha_inicial);
			mav.addObject("fecha_actual", fecha_actual);
			mav.addObject("cantidad", cantidad);
			mav.setViewName(Template.estadisticas);
		}else {
			mav.setViewName("/");
		}
		mav.addObject("titulo", "System BIB404");
		return mav;
	}
	
	@GetMapping("/BIB404/ultimomes3")
	public ModelAndView estadisticasmes3(HttpServletRequest request)  throws ServletException, IOException  {
		ModelAndView mav = new ModelAndView();
		if (funtions.isSuperUserBIB404(request)) {
			List<Biblioteca> bibliotecas = usuarioImp.listBibliotecas();
			int cantidad = bibliotecas.size();
			SimpleDateFormat formateador = new SimpleDateFormat("yyyyMMdd");
			Date f = new Date();
			String f4 = formateador.format(f);
			System.out.println(f4);
			ArrayList<Graf> graf = usuarioImp.findBibByYear(f4, 7, 93, 13);
			mav.addObject("bibliotecas", bibliotecas);
			mav.addObject("graf", graf);
			mav.addObject("tipo", "semanas");
			Date fecha = new Date();
			int mes = fecha.getMonth() - 1;
			fecha.setMonth(mes);
			SimpleDateFormat formatos = new SimpleDateFormat("yyyy-MM-dd");
			String fecha_actual = formatos.format(new Date());
			String fecha_inicial = formatos.format(fecha); 
			mav.addObject("fecha_inicial", fecha_inicial);
			mav.addObject("fecha_actual", fecha_actual);
			mav.addObject("cantidad", cantidad);
			mav.setViewName(Template.estadisticas);
		}else {
			mav.setViewName("/");
		}
		mav.addObject("titulo", "System BIB404");
		return mav;
	}
	
	@GetMapping("/BIB404/personal")
	public ModelAndView graficaPersonal(HttpServletRequest request,@RequestParam(name="opcion", required=false, defaultValue="") String opcion,@RequestParam(name="fecha_inicio", required=false, defaultValue="") String fecha_inicio ,@RequestParam(name="fecha_final", required=false, defaultValue="") String fecha_final)  throws ServletException, IOException, ParseException  {
		ModelAndView mav = new ModelAndView();
		if (funtions.isSuperUserBIB404(request)) {
			List<Biblioteca> bibliotecas = usuarioImp.listBibliotecas();
			int cantidad = bibliotecas.size();
			SimpleDateFormat formateador = new SimpleDateFormat("yyyyMMdd");
			Date f = new Date();
			String f4 = formateador.format(f);
			System.out.println(f4);
			Date fecha = new Date();
			int mes = fecha.getMonth() - 1;
			fecha.setMonth(mes);
			SimpleDateFormat formatos = new SimpleDateFormat("yyyy-MM-dd");
			String fecha_actual = formatos.format(new Date());
			String fecha_inicial = formatos.format(fecha); 
			mav.addObject("fecha_inicial", fecha_inicial);
			mav.addObject("fecha_actual", fecha_actual);
			mav.addObject("cantidad", cantidad);
			if (!opcion.isEmpty() && !fecha_inicio.isEmpty() && !fecha_final.isEmpty()) {
				Date fech_in = formatos.parse(fecha_inicio);
				Date fech_fin = formatos.parse(fecha_final);
				String f1 = formateador.format(fech_in);
				int op = Integer.parseInt(opcion);
				switch (op) {
				case 1:
					mav.addObject("tipo", "dias");
					int dif = numeroDiasEntreDosFechas(fech_in, fech_fin);
					ArrayList<Graf> graf = usuarioImp.findBibByYear(f1, 1, 0, dif);
					mav.addObject("graf", graf);
				break;
				case 5:
					mav.addObject("tipo", "semanas");
					int difSema = numeroDiasEntreDosFechas(fech_in, fech_fin) / 7;
					ArrayList<Graf> grafSem = usuarioImp.findBibByYear(f1, 7, 0, difSema);
					mav.addObject("graf", grafSem);
				break;
				case 12:
					mav.addObject("tipo", "meses");
					int difMes= numeroDiasEntreDosFechas(fech_in, fech_fin) / 30;
					ArrayList<Graf> grafMes = usuarioImp.findBibByYear(f1, 31, 0, difMes);
					mav.addObject("graf", grafMes);
				break;
				default:
					break;
				}
			}
			mav.addObject("bibliotecas", bibliotecas);
			mav.setViewName(Template.estadisticas);
		}else {
			mav.setViewName("/");
		}
		mav.addObject("titulo", "System BIB404");
		return mav;
	}
	
	public static int numeroDiasEntreDosFechas(Date fecha1, Date fecha2){
	     long startTime = fecha1.getTime();
	     long endTime = fecha2.getTime();
	     long diffTime = endTime - startTime;
	     return (int)TimeUnit.DAYS.convert(diffTime, TimeUnit.MILLISECONDS);
	}
	
	
	
}
