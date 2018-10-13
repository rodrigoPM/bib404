package com.bib404.system_bib404.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bib404.system_bib404.Repository.PrestamoRepository;
import com.bib404.system_bib404.component.PrestamoConverter;
import com.bib404.system_bib404.entity.Prestamo;
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
	public List<PrestamoModel> listPrestamos() {
		List<Prestamo> prestamos=prestamoRepository.findAll();
		List<PrestamoModel> prestamoModels=new ArrayList<PrestamoModel>();
		for(Prestamo prestamo:prestamos) {
			prestamoModels.add(prestamoConverter.convertPrestamo2PrestamoModel(prestamo));
		}
		return prestamoModels;
	}

}
