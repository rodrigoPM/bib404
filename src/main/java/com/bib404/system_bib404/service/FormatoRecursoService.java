package com.bib404.system_bib404.service;

import com.bib404.system_bib404.entity.FormatoRecurso;
import com.bib404.system_bib404.model.FormatoRecursoModel;

import java.util.List;

public interface FormatoRecursoService {

    FormatoRecursoModel addFormatoRecurso(FormatoRecursoModel formatoRecursoModel);

    List<FormatoRecursoModel> listAllFormatoRecursos();

    //FormatoRecursoModel findById(int id);

    void removeFormatoRecurso(int id);

    //con entidades
    public abstract List<FormatoRecurso> listAllFormatoRec();
    public abstract FormatoRecurso findById(int id_fr);

}
