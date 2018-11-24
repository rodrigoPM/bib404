package com.bib404.system_bib404.service;

import java.util.List;

import com.bib404.system_bib404.entity.Solicitud;

public interface SolicitudService {
	public abstract List<Solicitud> findSolicitud();
	public abstract List<Solicitud> findSolicitudAll();
	public abstract Solicitud save(Solicitud solicitud);
	public abstract Solicitud update(Solicitud solicitud);
	public abstract List<Solicitud> findAll();
	public abstract Solicitud findById(int id);
	public abstract void eliminar(int id);

}
