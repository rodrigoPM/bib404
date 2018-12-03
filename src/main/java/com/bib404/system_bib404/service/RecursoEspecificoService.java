package com.bib404.system_bib404.service;

import java.util.List;

import com.bib404.system_bib404.entity.RecursoEspecifico;
import com.bib404.system_bib404.model.RecursoEspecificoModel;

public interface RecursoEspecificoService {

    RecursoEspecificoModel addRecursoEspecifico(RecursoEspecificoModel recursoEspecificoModel);

    List<RecursoEspecificoModel> listAllRecursoEspecificos();

    RecursoEspecificoModel findById(int id);

    void removeRecursoEspecifico(int id);

//    //getionar rec esp listAllRecEsp
//    public abstract RecursoEspecifico addRecEsp(RecursoEspecifico recursoEspecifico);
    public abstract List<RecursoEspecifico> listAllRecEsp(int id_rb);
    public abstract RecursoEspecifico addRecEsp(RecursoEspecifico re);
    public abstract RecursoEspecifico findRecEspById(int id_re);
    public abstract boolean existsById(int id_re);
    public abstract boolean deleteRecEsp(int id_re);

	RecursoEspecifico findByIdRecurso(int id);

}
