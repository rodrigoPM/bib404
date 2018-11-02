package com.bib404.system_bib404.component;

import org.springframework.stereotype.Component;

import com.bib404.system_bib404.entity.TipoRecurso;
import com.bib404.system_bib404.model.TipoRecursoModel;

@Component("tipoRecursoConverter")
public class TipoRecursoConverter {
	
	public TipoRecurso convertTipoRecursoModel2TipoRecurso(TipoRecursoModel tipoRecursoModel) {
		TipoRecurso tipoRecurso = new TipoRecurso();
		tipoRecurso.setId(tipoRecursoModel.getId());
		tipoRecurso.setNombre_tipo_recurso(tipoRecursoModel.getNombre_tipo_recurso());
		return tipoRecurso;
	}
	
	public TipoRecursoModel convertTipoRecurso2TipoRecursoModel(TipoRecurso tipoRecurso) {
		TipoRecursoModel tipoRecursoModel=new TipoRecursoModel();
		tipoRecursoModel.setId(tipoRecurso.getId());
		tipoRecursoModel.setNombre_tipo_recurso(tipoRecurso.getNombre_tipo_recurso());
		return tipoRecursoModel;
	}
}
