package com.bib404.system_bib404.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bib404.system_bib404.Repository.PrestamoRepository;
import com.bib404.system_bib404.component.PrestamoConverter;
import com.bib404.system_bib404.entity.Prestamo;
import com.bib404.system_bib404.entity.Usuario;
import com.bib404.system_bib404.model.PrestamoModel;
import com.bib404.system_bib404.service.PrestamoService;

@Service("prestamoServiceImpl")
public class PrestamoServiceImpl implements PrestamoService{
	
	@Autowired
	@Qualifier("prestamoRepository")
		private PrestamoRepository prestamoRepository;
	
	@Autowired
	@Qualifier("prestamoConverter")
	    private PrestamoConverter prestamoConverter;
	 
	@Override
	public PrestamoModel addPrestamo(PrestamoModel prestamoModel) {
		Prestamo prestamo =prestamoRepository.save(prestamoConverter.convertPrestamoModel2Prestamo(prestamoModel));
		return prestamoConverter.convertPrestamo2PrestamoModel(prestamo);
	}
	@Override
	public Prestamo findPrestamoById(int id) {
		return prestamoRepository.findById(id);
	}
	public PrestamoModel findPrestamoByIdModel(int id) {
		return prestamoConverter.convertPrestamo2PrestamoModel(findPrestamoById(id));
	}
	

	@Override
	public List<PrestamoModel> listPrestamos() {
		List<Prestamo> prestamos=prestamoRepository.findAll();
		List<Prestamo> valprestamos= prestamos.stream().filter(x -> x.getEstado()==1).collect(Collectors.toList());
		List<PrestamoModel> prestamoModels=new ArrayList<PrestamoModel>();
		for(Prestamo prestamo:valprestamos) {
			prestamoModels.add(prestamoConverter.convertPrestamo2PrestamoModel(prestamo));
		}
		return prestamoModels;
	}
	public List<PrestamoModel> listPrestados() {
		List<Prestamo> prestamos=prestamoRepository.findAll();
		List<Prestamo> valprestamos= prestamos.stream().filter(x -> x.getEstado()==2).collect(Collectors.toList());
		List<PrestamoModel> prestamoModels=new ArrayList<PrestamoModel>();
		for(Prestamo prestamo:valprestamos) {
			Date fech = new Date();
			int dias = fech.getDay();
			if(dias <= prestamo.getFecha_devolucion().getDay()) {
				prestamoModels.add(prestamoConverter.convertPrestamo2PrestamoModel(prestamo));
			}
		}
		return prestamoModels;
	}
	public List<PrestamoModel> listPrestadosMora() {
		List<Prestamo> prestamos=prestamoRepository.findAll();
		List<Prestamo> valprestamos= prestamos.stream().filter(x -> x.getEstado()==2).collect(Collectors.toList());
		List<PrestamoModel> prestamoModels=new ArrayList<PrestamoModel>();
		for(Prestamo prestamo:valprestamos) {
			Date fech = new Date();
			int dias = fech.getDay();
			if(dias > prestamo.getFecha_devolucion().getDay()) {
				prestamo.setMora(true);
				prestamoRepository.save(prestamo);
				prestamoModels.add(prestamoConverter.convertPrestamo2PrestamoModel(prestamo));
			}
		}
		return prestamoModels;
	}
	public List<PrestamoModel> listDenegados() {
		List<Prestamo> prestamos=prestamoRepository.findAll();
		List<Prestamo> valprestamos= prestamos.stream().filter(x -> x.getEstado()==0).collect(Collectors.toList());
		List<PrestamoModel> prestamoModels=new ArrayList<PrestamoModel>();
		for(Prestamo prestamo:valprestamos) {
			prestamoModels.add(prestamoConverter.convertPrestamo2PrestamoModel(prestamo));
		}
		return prestamoModels;
	}
	public List<PrestamoModel> listRecibidos() {
		List<Prestamo> prestamos=prestamoRepository.findAll();
		List<Prestamo> valprestamos= prestamos.stream().filter(x -> x.getEstado()==3).collect(Collectors.toList());
		List<PrestamoModel> prestamoModels=new ArrayList<PrestamoModel>();
		for(Prestamo prestamo:valprestamos) {
			prestamoModels.add(prestamoConverter.convertPrestamo2PrestamoModel(prestamo));
		}
		return prestamoModels;
	}
	@Override
	public List<Prestamo> listPrestadosEspecificos(int id_user) {
		List<Prestamo> prestamos=prestamoRepository.findByUsuarioId(id_user);
		List<Prestamo> resultado=prestamos.stream().filter(x -> !x.getRecursoEspecifico().getFormato_recurso().getNombre_formato().equals("fisico") && x.getEstado()==2).collect(Collectors.toList());
		List<Prestamo> variable=new ArrayList<Prestamo>();
		for(Prestamo prestamo:resultado) {
			variable.add(prestamo);
		}
		return variable;
	}


}
