package com.bib404.system_bib404.service.impl;

import java.sql.Connection;
import java.sql.Date;
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
import com.bib404.system_bib404.constant.Conexion;
import com.bib404.system_bib404.model.UsuarioModel;
import com.bib404.system_bib404.service.UsuarioService;




@Service("usuarioServiceImpl")
public class UsuarioServiceImpl /*implements UsuarioService*/{
	
//	@Autowired
//	@Qualifier("usuarioRepository")
//	private UsuarioRepository usuarioRep;
//	
//	private Connection conexion;
//	
//
//	public Connection getConexion() {
//		return conexion;
//	}
//	
//	private void conectar() {
//		try {
//			Class.forName("oracle.jdbc.OracleDriver");
//			String BaseDeDatos = "jdbc:oracle:thin:@localhost:1521:orcl";
//
//			conexion = DriverManager.getConnection(BaseDeDatos, Conexion.NAME_DB, Conexion.PASS_DB);
//			if (conexion != null) {
//				System.out.println("Conexion exitosa!");
//			} else {
//				System.out.println("Conexion fallida!");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void setConexion(Connection conexion) {
//		this.conexion = conexion;
//	}
//
//
//	@Override
//	public List<UsuarioModel> listUsuario() {
//		return usuarioRep.findAll();
//	}
//
//	
//	@Override
//	public int addUser(UsuarioModel usuario) {
//		conectar();
//		UsuarioModel us = null;
//		int v=0;
//		try {
//			//TO_TIMESTAMP('1996-07-24 21:27:34.149409100', 'YYYY-MM-DD HH24:MI:SS.FF')
//			List<UsuarioModel> usuarios = listUsuario();
//			int id = usuarios.size() + 1;
//			java.util.Date f =usuario.getFechaNacimiento();
//			String fecha = "TO_TIMESTAMP(\'"+f.getYear()+"-"+f.getMonth()+"-"+f.getDay()+" "+f.getHours()+":"+f.getMinutes()+":34.149409100', 'YYYY-MM-DD HH24:MI:SS.FF')";
//			String sql = "INSERT INTO USUARIO (ID,APELLIDO, EMAIL, ENABLE, FECHANACIMIENTO, NOMBRE, PASSWORD, ROL, USERNAME) VALUES ("+id+", \'"+usuario.getApellido()+"\', \'"+usuario.getEmail()+"\', "+1+", "+fecha+", \'"+usuario.getNombre()+"\', \'"+usuario.getPassword()+"\', \'"+ usuario.getRol()+"\', \'" +usuario.getUsername()+ "\')";
//			Statement sentencia;
//			sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
//			v = sentencia.executeUpdate(sql);
//			getConexion().commit();
//			sentencia.close();
//		} catch (SQLException e) {
//			System.out.println("Ocurrio un error al escribir en DB");
//			e.printStackTrace();
//		}
//		cerrar();
//		return v;		
//	}
//
//	@Override
//	public void removeUser(UsuarioModel usuario) {
//		usuarioRep.delete(usuario);
//	}
//
//	@Override
//	public UsuarioModel updateUser(UsuarioModel usuario) {
//		return usuarioRep.save(usuario);
//	}
//	
//	@Override
//	public UsuarioModel getUserId(long id) {
//		return null;
//	}
//
//
//	@Override
//	public UsuarioModel findBy(String username) {
//		conectar();
//		ResultSet resultado = null;
//		UsuarioModel us = null;
//		try {
//			String sql = "SELECT * FROM USUARIO WHERE USERNAME = \'" + username+"\'";
//			Statement sentencia;
//			sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
//			resultado= sentencia.executeQuery(sql);
//			while (resultado.next()) {
//				us = new UsuarioModel(resultado.getString("USERNAME"), resultado.getString("PASSWORD"), resultado.getString("NOMBRE"), resultado.getString("APELLIDO"), resultado.getString("EMAIL"), resultado.getDate("FECHANACIMIENTO"));
//			}
//			getConexion().commit();
//			sentencia.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		cerrar();
//		return us;
//	}	
//	
//	private void cerrar() {
//		try {
//			conexion.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
	
	

}
