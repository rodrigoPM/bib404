package com.bib404.system_bib404.service;

import java.util.List;

import com.bib404.system_bib404.model.RecursoEspecificoModel;

public interface RecursoEspecificoService {

    RecursoEspecificoModel addRecursoEspecifico(RecursoEspecificoModel recursoEspecificoModel);

    List<RecursoEspecificoModel> listAllRecursoEspecificos();

    RecursoEspecificoModel findById(int id);

    void removeRecursoEspecifico(int id);

}
