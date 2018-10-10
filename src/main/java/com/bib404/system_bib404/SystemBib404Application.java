package com.bib404.system_bib404;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.bib404.system_bib404.Repository.UsuarioRepository;
import com.bib404.system_bib404.model.UsuarioModel;

@SpringBootApplication
public class SystemBib404Application {

	public static void main(String[] args) {
		SpringApplication.run(SystemBib404Application.class, args);
	}
}
