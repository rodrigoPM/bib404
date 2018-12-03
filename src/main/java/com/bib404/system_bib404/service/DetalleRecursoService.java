package com.bib404.system_bib404.service;

import java.util.List;

import com.bib404.system_bib404.entity.DetalleRecurso;

public interface DetalleRecursoService {
    public abstract List<DetalleRecurso> listAllDetalleRecurso(int id_rb);
    public abstract DetalleRecurso findByid(int id_dr);
    public abstract boolean existsById(int id_dr);
    public abstract DetalleRecurso addDetalleRecurso(DetalleRecurso detalleRecurso);
    public abstract boolean deleteDetalleRecurso(int id_dr);
    public abstract DetalleRecurso getLastDRbyRB(int id_rb);
    public abstract DetalleRecurso updateDetalleRecurso(DetalleRecurso detalleRecurso);

}
