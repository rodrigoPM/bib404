package com.bib404.system_bib404.model;

import org.springframework.web.multipart.MultipartFile;

public class ObjectAux{
  private int id_object;

  private MultipartFile file;

  public int getId_object() {
    return id_object;
  }

  public void setId_object(int id_object) {
    this.id_object = id_object;
  }
  public MultipartFile getFile(){
    return file;
  }
  public void setFile(MultipartFile file){
    this.file=file;
  }

  public ObjectAux() {
		super();
	}
  public ObjectAux(int id_object){
    this.id_object=id_object;
  }
  
  public ObjectAux(int id_object, MultipartFile file) {
    this.id_object = id_object;
    this.file=file;
  }
}
