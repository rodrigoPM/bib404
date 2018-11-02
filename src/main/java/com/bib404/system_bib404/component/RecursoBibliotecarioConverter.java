package com.bib404.system_bib404.component;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import com.bib404.system_bib404.entity.RecursoBibliotecario;

import com.bib404.system_bib404.model.RecursoBibliotecarioModel;

@Component("recursoBibliotecarioConverter")
public class RecursoBibliotecarioConverter {


	@Autowired
	@Qualifier("tipoRecursoConverter")
	private TipoRecursoConverter tipoRecurso;

	public RecursoBibliotecario converterRBModel2RB(RecursoBibliotecarioModel rbModel) {
		RecursoBibliotecario rb = new RecursoBibliotecario();
		rb.setId(rbModel.getId());
		rb.setNombre_recurso_bib(rbModel.getNombre_recurso_bib());
		rb.setDescripcion_recurso_bib(rbModel.getDescripcion_recurso_bib());
		rb.setSinopsis_recurso_bib(rbModel.getSinopsis_recurso_bib());
		rb.setImagen_recurso_bibl(rbModel.getImagen_recurso_bibl());
		rb.setDigital_recurso_bib(rbModel.getDigital_recurso_bib());
		rb.setFisico_recurso_bib(rbModel.getFisico_recurso_bib());
		rb.setTotal_recurso_bib(rbModel.getTotal_recurso_bib());
		
		rb.setBiblioteca(rbModel.getBiblioteca());

		rb.setTipo_recurso(tipoRecurso.convertTipoRecursoModel2TipoRecurso(rbModel.getTipo_recurso()));

		rb.setCategoria(rbModel.getCategoria());

		return rb;
	}

	public RecursoBibliotecarioModel converterRB2RBModel(RecursoBibliotecario rb) {
		RecursoBibliotecarioModel rbModel = new RecursoBibliotecarioModel();

		rbModel.setId(rb.getId());
		rbModel.setNombre_recurso_bib(rb.getNombre_recurso_bib());
		rbModel.setDescripcion_recurso_bib(rb.getDescripcion_recurso_bib());
		rbModel.setSinopsis_recurso_bib(rb.getSinopsis_recurso_bib());
		rbModel.setImagen_recurso_bibl(rb.getImagen_recurso_bibl());
		rbModel.setDigital_recurso_bib(rb.getDigital_recurso_bib());
		rbModel.setFisico_recurso_bib(rb.getFisico_recurso_bib());
		rbModel.setTotal_recurso_bib(rb.getTotal_recurso_bib());

		rbModel.setBiblioteca(rb.getBiblioteca());

		rbModel.setTipo_recurso(tipoRecurso.convertTipoRecurso2TipoRecursoModel(rb.getTipo_recurso()));

		rbModel.setCategoria(rb.getCategoria());

		return rbModel;
	}
}
