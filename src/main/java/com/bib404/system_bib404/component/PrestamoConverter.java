package com.bib404.system_bib404.component;

import org.springframework.stereotype.Component;

import com.bib404.system_bib404.entity.Prestamo;
import com.bib404.system_bib404.model.PrestamoModel;

@Component("prestamoConverter")
public class PrestamoConverter {
	
	public Prestamo convertPrestamoModel2Prestamo(PrestamoModel prestamoModel) {
		Prestamo prestamo =new Prestamo();
		prestamo.setCantidad_recurso_bib(prestamoModel.getCantidad_recurso_bib());
		prestamo.setFecha_devolucion(prestamoModel.getFecha_devolucion());
		prestamo.setFecha_entrega(prestamoModel.getFecha_entrega());
		prestamo.setFecha_prestamo(prestamoModel.getFecha_prestamo());
		prestamo.setMora(prestamoModel.isMora());
		prestamo.setEstado(prestamoModel.getEstado());
		prestamo.setUsuario(prestamoModel.getUsuario());
		prestamo.setRecursoEspecifico(prestamoModel.getRecursoEspecifico());
		prestamo.setId(prestamoModel.getId());
		
		return prestamo;
	}
	public PrestamoModel convertPrestamo2PrestamoModel(Prestamo prestamo) {
		PrestamoModel prestamoModel =new PrestamoModel();
		prestamoModel.setCantidad_recurso_bib(prestamo.getCantidad_recurso_bib());
		prestamoModel.setFecha_devolucion(prestamo.getFecha_devolucion());
		prestamoModel.setFecha_entrega(prestamo.getFecha_entrega());
		prestamoModel.setFecha_prestamo(prestamo.getFecha_prestamo());
		prestamoModel.setMora(prestamo.isMora());
		prestamoModel.setEstado(prestamo.getEstado());
		prestamoModel.setUsuario(prestamo.getUsuario());
		prestamoModel.setRecursoEspecifico(prestamo.getRecursoEspecifico());
		prestamoModel.setId(prestamo.getId());
		
		return prestamoModel;
	}

}
