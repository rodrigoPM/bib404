package com.bib404.system_bib404.component;

import org.springframework.stereotype.Component;

import com.bib404.system_bib404.entity.Departamento;
import com.bib404.system_bib404.model.DepartamentoModel;

@Component("departamentoConverter")
public class DepartamentoConverter {
	
	public Departamento converterDepartamentoModel2Departamento(DepartamentoModel departamentoModel) {
		Departamento departamento=new Departamento();
		departamento.setId(departamentoModel.getId());
		departamento.setNombre_departamento(departamentoModel.getNombre_departamento());
		departamento.setZona_geografica(departamentoModel.getZona_geografica());
		return departamento;
	}
	
	public DepartamentoModel convertDepartamento2DepartamentoModel(Departamento departamento) {
		DepartamentoModel departamentoModel=new DepartamentoModel();
		departamentoModel.setId(departamento.getId());
		departamentoModel.setNombre_departamento(departamento.getNombre_departamento());
		departamentoModel.setZona_geografica(departamento.getZona_geografica());
		return departamentoModel;
	}
}
