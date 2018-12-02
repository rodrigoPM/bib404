package com.bib404.system_bib404.controller;

import java.net.MalformedURLException;

import com.bib404.system_bib404.constant.Url;
import com.bib404.system_bib404.service.impl.FileServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ArchivoController
 */
@Controller
@RequestMapping(Url.ARCHIVOS)   // --> /bib404/archivos
public class ArchivoController {

    @Autowired
    @Qualifier("fileServiceImpl")
    private FileServiceImpl file;

    @GetMapping("/img/{nombreImg:.+}")
    public ResponseEntity<Resource> verImg(@PathVariable String nombreImg) {

        Resource recurso = null;

        try {
            recurso = file.cargarFile(nombreImg,1);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }

    @GetMapping("/file/{nombreFile:.+}")
    public ResponseEntity<Resource> verFile(@PathVariable String nombreFile) {

        Resource recurso = null;

        try {
            recurso = file.cargarFile(nombreFile, 2);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }
    
}