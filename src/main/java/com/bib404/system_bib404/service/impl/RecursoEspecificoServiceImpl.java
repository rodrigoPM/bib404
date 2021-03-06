package com.bib404.system_bib404.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bib404.system_bib404.Repository.RecursoEspecificoRepository;
import com.bib404.system_bib404.component.RecursoEspecificoConverter;
import com.bib404.system_bib404.entity.RecursoEspecifico;
import com.bib404.system_bib404.entity.DetalleRecurso;
import com.bib404.system_bib404.model.RecursoEspecificoModel;
import com.bib404.system_bib404.service.RecursoEspecificoService;
import com.bib404.system_bib404.service.impl.DetalleRecursoServiceImpl;

@Service("recursoEspecificoServiceImpl")
public class RecursoEspecificoServiceImpl implements RecursoEspecificoService {

    @Autowired
    @Qualifier("recursoEspecificoRepository")
    RecursoEspecificoRepository recursoEspecificoRepository;

    @Autowired
    @Qualifier("recursoEspecificoConverter")
    RecursoEspecificoConverter recursoEspecificoConverter;

    @Autowired
    @Qualifier("detalleRecursoServiceImpl")
    private DetalleRecursoServiceImpl dRec;
    
    @Override
    public RecursoEspecifico findByIdRecurso(int id) {
        return recursoEspecificoRepository.getOne(id);
    }

    @Override
    public RecursoEspecificoModel addRecursoEspecifico(RecursoEspecificoModel recursoEspecificoModel) {
        return recursoEspecificoConverter.convertRecursoEspecifico2RecursoEspecificoModel(recursoEspecificoRepository.save(recursoEspecificoConverter.convertRecursoEspecificoModel2RecursoEspecifico(recursoEspecificoModel)));
    }

    @Override
    public List<RecursoEspecificoModel> listAllRecursoEspecificos() {
        List<RecursoEspecificoModel> recursoEspecificosList = new ArrayList<>();
        for (RecursoEspecifico recursoEspecifico : recursoEspecificoRepository.findAll()){
            recursoEspecificosList.add(recursoEspecificoConverter.convertRecursoEspecifico2RecursoEspecificoModel(recursoEspecifico));
        }
        return recursoEspecificosList;
    }

    @Override
    public RecursoEspecificoModel findById(int id) {
        return recursoEspecificoConverter.convertRecursoEspecifico2RecursoEspecificoModel(recursoEspecificoRepository.getOne(id));
    }

    @Override
    public void removeRecursoEspecifico(int id) {
        RecursoEspecificoModel recursoEspecifico = findById(id);
        if (recursoEspecifico != null){
            recursoEspecificoRepository.delete(recursoEspecificoConverter.convertRecursoEspecificoModel2RecursoEspecifico(recursoEspecifico));
        }

    }

// metdos utilizandos las entidades


    @Override
    public List<RecursoEspecifico> listAllRecEsp(int rb) {
      List<DetalleRecurso> dRec_rbs=dRec.listAllDetalleRecurso(rb);
      List<RecursoEspecifico> recEsp=new ArrayList<RecursoEspecifico>();
      for(DetalleRecurso dr:dRec_rbs){
        recEsp.add(dr.getRecurso_especifico());
      }
      return recEsp;
    }

    @Override
    public RecursoEspecifico addRecEsp(RecursoEspecifico re) {
        RecursoEspecifico rec_esp=recursoEspecificoRepository.save(re);
        return rec_esp;
    }

    @Override
    public RecursoEspecifico findRecEspById(int id_re) {
        try {
            Optional<RecursoEspecifico> rec_esp=recursoEspecificoRepository.findById(id_re);
            return rec_esp.get();
        } catch (Exception e) {
            //TODO: handle exception
            return null;
        }
    }

    @Override
    public boolean existsById(int id_re) {
        return recursoEspecificoRepository.existsById(id_re);
    }

    @Override
    public boolean deleteRecEsp(int id_re) {
        if (findRecEspById(id_re)!=null) {
            recursoEspecificoRepository.delete(findRecEspById(id_re));
            if (!existsById(id_re)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;   
        }
    }
}
