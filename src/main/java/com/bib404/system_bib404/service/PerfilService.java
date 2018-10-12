package com.bib404.system_bib404.service;

import com.bib404.system_bib404.entity.Usuario;
import com.bib404.system_bib404.model.PerfilModel;

public interface PerfilService {

	public abstract PerfilModel addPerfil(PerfilModel perfilModel);

    public abstract Usuario findUsuarioById(int id);	
    public abstract PerfilModel findUsuarioByIdModel(int id);
}
