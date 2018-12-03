
package com.bib404.system_bib404.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


/**
 * FileService
 */
public interface FileService {

    public abstract String subirFile(MultipartFile archivo, int dir) throws IOException;
    public abstract boolean eliminarFile(String nombreFile, int dir);
    public abstract Resource cargarFile(String nombreFile, int dir) throws MalformedURLException;
    public abstract Path getPath(String nombreFile, int dir);
    
}