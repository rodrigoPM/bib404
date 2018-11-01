package com.bib404.system_bib404.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.bib404.system_bib404.entity.Municipio;
import com.bib404.system_bib404.model.MunicipioModel;

@Component("municipioConverter")
public class MunicipioConverter {
	
	@Autowired
	@Qualifier("departamentoConverter")
	private DepartamentoConverter departamento;
	
	public Municipio convertMunicipioModel2Municipio(MunicipioModel municipioModel) {
		Municipio municipio=new Municipio();
		municipio.setId(municipioModel.getId());
		municipio.setNombre_municipio(municipioModel.getNombre_municipio());
		municipio.setDepartamento(departamento.converterDepartamentoModel2Departamento(municipioModel.getDepartamento()));
		return municipio;
	}
	
	public MunicipioModel convertMunicipio2MunicipioModel(Municipio municipio) {
		MunicipioModel municipioModel = new MunicipioModel();
		municipioModel.setId(municipio.getId());
		municipioModel.setNombre_municipio(municipio.getNombre_municipio());
		municipioModel.setDepartamento(departamento.convertDepartamento2DepartamentoModel(municipio.getDepartamento()));
		return municipioModel;
	}
}
