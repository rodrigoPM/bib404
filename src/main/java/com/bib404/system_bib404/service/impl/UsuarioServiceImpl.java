package com.bib404.system_bib404.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bib404.system_bib404.Repository.UsuarioRepository;
import com.bib404.system_bib404.model.Usuario;
import com.bib404.system_bib404.service.UsuarioService;




@Service("usuarioServiceImpl")
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	@Qualifier("usuarioRepository")
	private UsuarioRepository usuarioRep;
	
	private Connection conexion;
	

	public Connection getConexion() {
		return conexion;
	}
	
	private void conectar() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String BaseDeDatos = "jdbc:oracle:thin:@localhost:1521:orcl";

			conexion = DriverManager.getConnection(BaseDeDatos, "bib404", "too115");
			if (conexion != null) {
				System.out.println("Conexion exitosa!");
			} else {
				System.out.println("Conexion fallida!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}


	@Override
	public List<Usuario> listUsuario() {
		return usuarioRep.findAll();
	}

	
	@Override
	public int addUser(Usuario usuario) {
		conectar();
		Usuario us = null;
		int v=0;
		try {
			List<Usuario> usuarios = listUsuario();
			int id = usuarios.size() + 1;
			String sql = "INSERT INTO USUARIO (ID,APELLIDO, EMAIL, ENABLE, FECHANACIMIENTO, NOMBRE, PASSWORD, ROL, USERNAME) VALUES ("+id+", \'"+usuario.getApellido()+"\', \'"+usuario.getEmail()+"\', "+1+", \'"+usuario.getFechaNacimiento()+"\', \'"+usuario.getNombre()+"\', \'"+usuario.getPassword()+"\', \'"+ usuario.getRol()+"\', \'" +usuario.getUsername()+ "\')";
			Statement sentencia;
			sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			v = sentencia.executeUpdate(sql);
			getConexion().commit();
			sentencia.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cerrar();
		return v;		
	}

	@Override
	public void removeUser(Usuario usuario) {
		usuarioRep.delete(usuario);
	}

	@Override
	public Usuario updateUser(Usuario usuario) {
		return usuarioRep.save(usuario);
	}
	
	@Override
	public Usuario getUserId(long id) {
		return null;
	}


	@Override
	public Usuario findBy(String username) {
		conectar();
		ResultSet resultado = null;
		Usuario us = null;
		try {
			String sql = "SELECT * FROM USUARIO WHERE USERNAME = \'" + username+"\'";
			Statement sentencia;
			sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			resultado= sentencia.executeQuery(sql);
			while (resultado.next()) {
				us = new Usuario(resultado.getString("USERNAME"), resultado.getString("PASSWORD"), resultado.getString("NOMBRE"), resultado.getString("APELLIDO"), resultado.getString("EMAIL"), resultado.getString("FECHANACIMIENTO"));
			}
			getConexion().commit();
			sentencia.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cerrar();
		return us;
	}	
	
	private void cerrar() {
		try {
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
