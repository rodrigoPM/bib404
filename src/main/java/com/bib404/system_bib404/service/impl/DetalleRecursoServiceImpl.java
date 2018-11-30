package com.bib404.system_bib404.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bib404.system_bib404.Repository.DetalleRecursoRepository;
import com.bib404.system_bib404.entity.DetalleRecurso;
import com.bib404.system_bib404.service.DetalleRecursoService;

@Service("detalleRecursoServiceImpl")
public class DetalleRecursoServiceImpl implements DetalleRecursoService{

    @Autowired
    @Qualifier("detalleRecursoRepository")
    private DetalleRecursoRepository dRecRepository;

    @Override
    public List<DetalleRecurso> listAllDetalleRecurso(int id_rb){
        List<DetalleRecurso> detalleRecursos=dRecRepository.findByRecursoBibId(id_rb);
        return detalleRecursos;
    }

    @Override
    public DetalleRecurso findByid(int id_dr) {
        return dRecRepository.findById(id_dr).get();
    }

    @Override
    public boolean existsById(int id_dr) {
        return dRecRepository.existsById(id_dr);
    }

    @Override
    public DetalleRecurso addDetalleRecurso(DetalleRecurso detalleRecurso) {
        return dRecRepository.save(detalleRecurso);
    }

    @Override
    public boolean deleteDetalleRecurso(int id_dr) {
        if (findByid(id_dr)!=null) {
            dRecRepository.delete(findByid(id_dr));
            if (!existsById(id_dr)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public DetalleRecurso getLastDRbyRB(int id_rb) {
        List<DetalleRecurso> detRec=dRecRepository.findByRecursoBibIdOrderByCreatedAt(id_rb);
        if(detRec.size()>0){
            return detRec.get(detRec.size() - 1);
        }else{
            return null;
        }
       
    }

    @Override
    public void updateDetalleRecurso(DetalleRecurso detalleRecurso) {
        dRecRepository.save(detalleRecurso);
    }
}
