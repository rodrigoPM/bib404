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
}
