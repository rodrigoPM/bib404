package com.bib404.system_bib404.constant;

public class Url {
	//Url de los controller

	//ver registrar biblioteca
	public static final String INDEX_BIB404="/";

	//consultar recursos bibliotecarios
	public static final String INDEX_BIB_X="/bib404";


	//administrar categoria
	public static final String CATEGORIA="/bib404/{id_bib}/dashboard/categorias";

	//gestionar bibliotecas del sistema
	public static final String GESTION_BIBS="/bib404/gestionar_bibliotecas";
	
	//gestionar recursos especificos
	public static final String GESTION_REC_ESP="/bib404/{id_bib}/{id_rb}/recurso_especifico";

	//url para traer los archivos
	public static final String ARCHIVOS="/bib404/archivos";

}
