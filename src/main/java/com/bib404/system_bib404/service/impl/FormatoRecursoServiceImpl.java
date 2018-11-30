package com.bib404.system_bib404.service.impl;

import java.util.List;

import com.bib404.system_bib404.Repository.FormatoRecursoRepository;
import com.bib404.system_bib404.entity.FormatoRecurso;
import com.bib404.system_bib404.model.FormatoRecursoModel;
import com.bib404.system_bib404.service.FormatoRecursoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("formatoRecursoServiceImpl")
public class FormatoRecursoServiceImpl implements FormatoRecursoService {

    @Autowired
    @Qualifier("formatoRecursoRepository")
    private FormatoRecursoRepository frRep;

    @Override
    public FormatoRecursoModel addFormatoRecurso(FormatoRecursoModel formatoRecursoModel) {
        return null;
    }

    @Override
    public List<FormatoRecursoModel> listAllFormatoRecursos() {
        return null;
    }

    /* @Override
    public FormatoRecursoModel findById(int id) {
        return null;
    } */

    @Override
    public void removeFormatoRecurso(int id) {

    }


    //con entidades
	@Override
	public List<FormatoRecurso> listAllFormatoRec() {
        return frRep.findAll();
	}

    @Override
    public FormatoRecurso findById(int id_fr) {
        return frRep.findById(id_fr).get();
    }

    
}
