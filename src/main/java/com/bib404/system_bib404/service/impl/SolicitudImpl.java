package com.bib404.system_bib404.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bib404.system_bib404.Repository.Solicitud;
import com.bib404.system_bib404.service.SolicitudService;

@Service("SolicitudImpl")
public class SolicitudImpl implements SolicitudService{
	@Autowired
	@Qualifier("solicitud")
	private Solicitud solicitud;

	@Override
	public List<com.bib404.system_bib404.entity.Solicitud> findSolicitud() {
		return solicitud.findSolicitud();
	}

	@Override
	public com.bib404.system_bib404.entity.Solicitud save(com.bib404.system_bib404.entity.Solicitud solicitud) {
		return this.solicitud.save(solicitud);
	}

	@Override
	public com.bib404.system_bib404.entity.Solicitud update(com.bib404.system_bib404.entity.Solicitud solicitud) {
		return this.solicitud.save(solicitud);
	}

	@Override
	public List<com.bib404.system_bib404.entity.Solicitud> findAll() {
		return this.solicitud.findAll();
	}

	@Override
	public com.bib404.system_bib404.entity.Solicitud findById(int id) {
		return this.solicitud.findById(id);
	}

	@Override
	public void eliminar(int id) {
		solicitud.deleteById(id);
	}

	@Override
	public List<com.bib404.system_bib404.entity.Solicitud> findSolicitudAll() {
		return solicitud.findAll();
	}
	
}
