package com.bib404.system_bib404.service;

import java.util.List;

import com.bib404.system_bib404.entity.Prestamo;
import com.bib404.system_bib404.model.PrestamoModel;

public interface PrestamoService {
	public abstract PrestamoModel addPrestamo(PrestamoModel prestamoModel);
	public abstract List<PrestamoModel> listPrestamos(int id_bib);
	public abstract List<Prestamo> listPrestadosEspecificos(int id_user);
	public abstract Prestamo findPrestamoById(int id);
	public abstract PrestamoModel findPrestamoByIdModel(int id);
	Prestamo addPres(Prestamo prestamo);

}
