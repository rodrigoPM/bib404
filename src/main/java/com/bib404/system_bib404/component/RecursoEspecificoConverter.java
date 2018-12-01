package com.bib404.system_bib404.component;

import org.springframework.stereotype.Component;

import com.bib404.system_bib404.entity.RecursoEspecifico;
import com.bib404.system_bib404.model.RecursoEspecificoModel;

@Component("recursoEspecificoConverter")
public class RecursoEspecificoConverter {
	
    public RecursoEspecifico convertRecursoEspecificoModel2RecursoEspecifico(RecursoEspecificoModel recursoEspecificoModel){
        return new RecursoEspecifico(
        		recursoEspecificoModel.getId(), 
        		recursoEspecificoModel.isConsulta_interna(),
        		recursoEspecificoModel.getVolumen_recurso(),
        		recursoEspecificoModel.getEdicion_recurso(), 
        		recursoEspecificoModel.isPrestado(),
        		recursoEspecificoModel.getCodigo_rec_esp(), 
        		recursoEspecificoModel.getFormato_recurso(),
        		recursoEspecificoModel.getDetalle_recurso(),
        		recursoEspecificoModel.getAutor(),
				recursoEspecificoModel.getEditorial(),
				recursoEspecificoModel.getArchivo()
        		);
    }
    public RecursoEspecificoModel convertRecursoEspecifico2RecursoEspecificoModel(RecursoEspecifico recursoEspecifico){
        return new RecursoEspecificoModel(
        		recursoEspecifico.getId(), 
        		recursoEspecifico.isConsulta_interna(),
        		recursoEspecifico.getVolumen_recurso(),
        		recursoEspecifico.getEdicion_recurso(), 
        		recursoEspecifico.isPrestado(),
        		recursoEspecifico.getCodigo_rec_esp(), 
        		recursoEspecifico.getFormato_recurso(),
        		recursoEspecifico.getDetalle_recurso(),
        		recursoEspecifico.getAutor(),
				recursoEspecifico.getEditorial(),
				recursoEspecifico.getArchivo()
        		);
    }
}
