package com.bib404.system_bib404.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bib404.system_bib404.Repository.PerfilRepository;
import com.bib404.system_bib404.component.PerfilConverter;
import com.bib404.system_bib404.entity.Usuario;
import com.bib404.system_bib404.model.PerfilModel;
import com.bib404.system_bib404.service.PerfilService;
import com.bib404.system_bib404.Repository.UsuarioRepository;
@Service("PerfilServiceImpl")

public class PerfilServiceImpl implements PerfilService{
@Autowired
@Qualifier("perfilRepository")
	private PerfilRepository perfilRepository;

@Autowired
@Qualifier("perfilConverter")
    private PerfilConverter perfilconverter;
 
@Override
public PerfilModel addPerfil(PerfilModel perfilModel) {
	Usuario usuario =perfilRepository.save(perfilconverter.convertPerfilModel2Perfil(perfilModel));
	PerfilModel perfil=perfilconverter.convertPerfil2PerfilModel(usuario);
	return perfil ;
}


@Override
public Usuario findUsuarioById(int id) {
	return perfilRepository.findById(id);
}

public PerfilModel findUsuarioByIdModel(int id)
{
	return perfilconverter.convertPerfil2PerfilModel(findUsuarioById( id));
	

}
	
	//public ContactModel findPerfilByIdModel(int id)
	//{
		
		//return perfilConverter
	//}
	
}
