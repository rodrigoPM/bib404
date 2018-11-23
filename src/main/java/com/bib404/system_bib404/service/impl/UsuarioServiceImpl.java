package com.bib404.system_bib404.service.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bib404.system_bib404.Repository.BibliotecaRepository;
import com.bib404.system_bib404.Repository.DepartamentoRepository;
import com.bib404.system_bib404.Repository.MunicipioRepository;
import com.bib404.system_bib404.Repository.UsuarioRepository;
import com.bib404.system_bib404.constant.Conexion;
import com.bib404.system_bib404.entity.Biblioteca;
import com.bib404.system_bib404.entity.Departamento;
import com.bib404.system_bib404.entity.Municipio;
import com.bib404.system_bib404.entity.Usuario;
import com.bib404.system_bib404.model.Graf;
import com.bib404.system_bib404.service.UsuarioService;




@Service("usuarioServiceImpl")
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	@Qualifier("usuarioRepository")
	private UsuarioRepository usuarioRep;
	@Autowired
	@Qualifier("departamentoRepository")
	private DepartamentoRepository deptoRep;
	@Autowired
	@Qualifier("municipioRepository")
	private MunicipioRepository munRep;
	@Autowired
	@Qualifier("bibliotecaRepository")
	private BibliotecaRepository bibRep;
	
	private Connection conexion;
	

	public Connection getConexion() {
		return conexion;
	}
	
	private void conectar() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String BaseDeDatos = "jdbc:oracle:thin:@localhost:1521:orcl";

			conexion = DriverManager.getConnection(BaseDeDatos, Conexion.NAME_DB, Conexion.PASS_DB);
			if (conexion != null) {
				System.out.println("Conexion exitosa!");
			} else {
				System.out.println("Conexion fallida!");
			}
		} catch (Exception e) {
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
	public List<Usuario> listUsuarioBib(int id_bib) {
		return usuarioRep.findByBibliotecaId(id_bib);
	}
	
	@Override
	public List<Departamento> listDpto() {
		return deptoRep.findAll();
	}

	@Override
	public List<Municipio> listMunicipios() {
		return munRep.findAll();
	}
	
	@Override
	public List<Municipio> listMunicipiosOrderByNombre() {
		List<Municipio> mun = new ArrayList<Municipio>();
		ResultSet r = null;
		conectar();
		try {
			String sql = "select * from MUNICIPIO order by NOMBRE_MUNICIPIO";
			Statement sentencia;
			sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			r= sentencia.executeQuery(sql);
			while (r.next()) {
				Municipio m = new Municipio();
				m.setId(r.getInt("id"));
				m.setNombre_municipio(r.getString("NOMBRE_MUNICIPIO"));
				mun.add(m);
			}
			getConexion().commit();
			sentencia.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		cerrar();
		return mun;
	}

	@Override
	public List<Biblioteca> listBibliotecas() {
		return bibRep.findAll();
	}
	
	

	
	@Override
	public int addUser(Usuario usuario, int id_numicipio, int id_biblioteca) {
		conectar();
		int v=0;
		try {
			//TO_TIMESTAMP('1996-07-24 21:27:34.149409100', 'YYYY-MM-DD HH24:MI:SS.FF')
			List<Usuario> usuarios = listUsuario();
			int id = usuarios.size() + 1;
			java.util.Date f =usuario.getFecha_nacimiento();
			java.util.Date f2 =usuario.getFecha_registro();
			SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
			String f3 = formateador.format(f);
			String f4 = formateador.format(f2);
			String fecha_nacimiento = "TO_TIMESTAMP(\'"+f3+" "+f.getHours()+":"+f.getMinutes()+":34.149409100', 'YYYY-MM-DD HH24:MI:SS.FF')";			
			String fecha_registro = "TO_TIMESTAMP(\'"+f4+" "+f2.getHours()+":"+f2.getMinutes()+":34.149409100', 'YYYY-MM-DD HH24:MI:SS.FF')";		
			System.out.println(fecha_registro+"  fecha de nacimiento: "+fecha_nacimiento);
			String sql = "INSERT INTO USUARIO(ID, APELLIDO, EMAIL, ENABLE, FECHA_NACIMIENTO, FECHA_REGISTRO, FOTO_PERFIL, GENERO, LUGAR_ESTUDIO, NOMBRE, NOMBRE_MADRE, NOMBRE_PADRE, NUMERO_TELEFONO, OCUPACION, PASSWORD, ROL, USERNAME, BIBLIOTECA_ID, MUNICIPIO_ID) VALUES ("+id+", \'"+usuario.getApellido()+"\', \'"+usuario.getEmail()+"\', "+1+", "+fecha_nacimiento+", "+fecha_registro+", \'none\', \'"+usuario.getGenero()+"\', \'"+usuario.getLugar_estudio()+"\', \'"+usuario.getNombre()+"\', \'"+usuario.getNombre_madre()+"\', \'"+usuario.getNombre_padre()+"\', \'"+usuario.getNumero_telefono()+"\', \'"+usuario.getOcupacion()+"\', \'"+usuario.getPassword()+"\', \'"+usuario.getRol()+"\', \'"+usuario.getUsername()+"\', "+id_biblioteca+", "+id_numicipio+")";
			Statement sentencia;
			sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			v = sentencia.executeUpdate(sql);
			getConexion().commit();
			sentencia.close();
		} catch (Exception e) {
			System.out.println("DB");
			
		}
		cerrar();
		return v;		
	}
	
	
	
	@Override
	public int updateSuperUser(Usuario usuario, int id_biblioteca) {
		conectar();
		int v=0;
		try {
			String sql = "UPDATE USUARIO SET BIBLIOTECA_ID = "+id_biblioteca+" WHERE ID = "+usuario.getId();
			Statement sentencia;
			sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			v = sentencia.executeUpdate(sql);
			getConexion().commit();
			sentencia.close();
		} catch (Exception e) {
			System.out.println("DB");
			
		}
		cerrar();
		return v;		
	}
	
	
	
	@Override
	public int addDpto(Departamento depto) {
		conectar();
		int v=0;
		try {
			List<Departamento> depart = listDpto();
			int id = depart.size() + 1;
			String sql = "INSERT INTO DEPARTAMENTO (ID, NOMBRE_DEPARTAMENTO, ZONA_GEOGRAFICA) VALUES ("+id+", \'"+depto.getNombre_departamento()+"\', \'"+depto.getZona_geografica()+"\')";
			Statement sentencia;
			sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			v = sentencia.executeUpdate(sql);
			getConexion().commit();
			sentencia.close();
		} catch (SQLException e) {
			System.out.println("Ocurrio un error al escribir en DB");
			e.printStackTrace();
		}
		cerrar();
		return v;		
	}

	@Override
	public int addMunicipio(Municipio municipio,int id_departamento) {
		conectar();
		int v=0;
		try {
			List<Municipio> mun = listMunicipios();
			int id = mun.size() + 1;
			String sql = "INSERT INTO MUNICIPIO (ID, NOMBRE_MUNICIPIO, DEPARTAMENTO_ID) VALUES ("+id+", \'"+municipio.getNombre_municipio()+"\', "+id_departamento+")";
			Statement sentencia;
			sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			v = sentencia.executeUpdate(sql);
			getConexion().commit();
			sentencia.close();
		} catch (SQLException e) {
			System.out.println("DB");
		}
		cerrar();
		return v;		
	}
	

	@Override
	public int addBiblio(Biblioteca bib,int id_municipio) {
		conectar();
		int v=0;
		try {
			List<Biblioteca> mun = listBibliotecas();
			int id = mun.size() + 1;
			java.util.Date f2 = new java.util.Date();
			SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
			String f4 = formateador.format(f2);
			String fecha_registro = "TO_TIMESTAMP(\'"+f4+" "+f2.getHours()+":"+f2.getMinutes()+":34.149409100', 'YYYY-MM-DD HH24:MI:SS.FF')";
			String sql = "INSERT INTO BIBLIOTECA (ID, CODIGO_BIBLIOTECA, NOMBRE_BIBLIOTECA, MUNICIPIO_ID,FECHA_REGISTRO) VALUES ("+id+", \'"+bib.getCodigo_biblioteca()+"\', \'"+bib.getNombre_biblioteca()+"\', "+id_municipio+", "+fecha_registro+")";
			Statement sentencia;
			sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			v = sentencia.executeUpdate(sql);
			sentencia.close();
			getConexion().commit();
			v=id;
		} catch (SQLException e) {
			System.out.println("DB");
		}
		cerrar();
		return v;		
	}
	
	

	@Override
	public void removeUser(Usuario usuario) {
		usuarioRep.delete(usuario);
	}
	@Override
	public void deleteUser(int id) {
		Usuario usuario=findById(id);
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
		ResultSet r = null;
		Usuario us = null;
		try {
			String sql = "SELECT * FROM USUARIO WHERE USERNAME = \'" + username+"\'";
			Statement sentencia;
			sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			r= sentencia.executeQuery(sql);
			while (r.next()) {
				Municipio municipio=findMunBy(r.getInt("municipio_id"));
				Biblioteca biblioteca=findBibBy(r.getInt("biblioteca_id"));
				us= new Usuario(r.getInt("id"), r.getString("username"), r.getString("password"), r.getString("nombre"), r.getString("apellido"), r.getDate("fecha_nacimiento"), r.getString("nombre_padre"), r.getString("nombre_madre"), r.getString("numero_telefono"), r.getString("lugar_estudio"), r.getString("genero"), r.getString("ocupacion"), r.getString("email"), r.getDate("fecha_registro"), r.getString("foto_perfil"), r.getBoolean("enable"), r.getString("rol"), municipio, biblioteca);
			}
			getConexion().commit();
			sentencia.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cerrar();
		return us;
	}

	@Override
	public Usuario findById(int id) {
		conectar();
		ResultSet r = null;
		Usuario us = null;
		try {
			String sql = "SELECT * FROM USUARIO WHERE ID = " + id;
			Statement sentencia;
			sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			r= sentencia.executeQuery(sql);
			while (r.next()) {
				Municipio municipio=findMunBy(r.getInt("municipio_id"));
				Biblioteca biblioteca=findBibBy(r.getInt("biblioteca_id"));
				us= new Usuario(r.getInt("id"), r.getString("username"), r.getString("password"), r.getString("nombre"), r.getString("apellido"), r.getDate("fecha_nacimiento"), r.getString("nombre_padre"), r.getString("nombre_madre"), r.getString("numero_telefono"), r.getString("lugar_estudio"), r.getString("genero"), r.getString("ocupacion"), r.getString("email"), r.getDate("fecha_registro"), r.getString("foto_perfil"), r.getBoolean("enable"), r.getString("rol"), municipio, biblioteca);
			}
			getConexion().commit();
			sentencia.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cerrar();
		return us;
	}


	@Override
	public Biblioteca findBibBy(int id) {
		conectar();
		ResultSet r = null;
		Biblioteca us = null;
		try {
			String sql = "SELECT * FROM BIBLIOTECA WHERE ID =" + id;
			Statement sentencia;
			sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			r= sentencia.executeQuery(sql);
			while (r.next()) {
				us= new Biblioteca();
				us.setCodigo_biblioteca(r.getString("codigo_biblioteca"));
				us.setNombre_biblioteca(r.getString("nombre_biblioteca"));
				us.setMunicipio(findMunBy(r.getInt("municipio_id")));
				us.setId(id);
			}
			getConexion().commit();
			sentencia.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cerrar();
		return us;
	}
	

	@Override
	public ArrayList<Graf> findBibByDate(String id, int value) {
		conectar();
		ResultSet r = null;
		ArrayList<Graf> us = new ArrayList<Graf>();
		try {
			String sql2="select TO_NUMBER(TO_CHAR((TO_DATE("+id+",\'YYYYMMDD\') +rownum-1 -"+(value - 1)+" ),\'YYYYMMDD\')) from DUAL CONNECT BY LEVEL <= "+value;
			Statement sentencia;
			sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			r= sentencia.executeQuery(sql2);
			while (r.next()) {
				ResultSet r2 = null;
				int val = r.getInt(1);
				String sql ="SELECT count(id),TO_DATE("+val+",'YYYYMMDD') as dias FROM BIBLIOTECA WHERE TO_NUMBER(to_char(fecha_registro,\'YYYYMMDD\')) = "+val;
				Statement sentencia2;
				sentencia2 = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
				r2= sentencia2.executeQuery(sql);
				while (r2.next()) {
					us.add(new Graf(r2.getInt(1), r2.getDate(2)));
				}
			}
			getConexion().commit();
			sentencia.close();
		} catch (SQLException e) {
			System.out.println("Ocurrio una excepcion: "+e.getMessage());
		}
		cerrar();
		return us;
	}
	
	

	@Override
	public ArrayList<Graf> findBibByYear(String id, int multiplicador, int resta, int hasta) {
		conectar();
		ResultSet r = null;
		ArrayList<Graf> us = new ArrayList<Graf>();
		try {
			String sql2="select TO_NUMBER(TO_CHAR((TO_DATE("+id+",\'YYYYMMDD\') + rownum*"+multiplicador+" -1 - "+(resta - 1)+" ),\'YYYYMMDD\')), TO_NUMBER(TO_CHAR((TO_DATE("+id+",\'YYYYMMDD\') + rownum*"+multiplicador+" -1 - "+(resta - 1 -multiplicador)+" ),\'YYYYMMDD\')) from DUAL CONNECT BY LEVEL <= "+hasta;
			Statement sentencia;
			sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			r= sentencia.executeQuery(sql2);
			while (r.next()) {
				ResultSet r2 = null;
				int val = r.getInt(1);
				int val2 = r.getInt(2);
				String sql ="select count(id),TO_DATE("+val+",\'YYYYMMDD\') as dias from biblioteca where to_number(to_char(fecha_registro,\'YYYYMMDD\')) > "+val+" and to_number(to_char(fecha_registro,\'YYYYMMDD\')) <= "+val2;
				Statement sentencia2;
				sentencia2 = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
				r2= sentencia2.executeQuery(sql);
				while (r2.next()) {
					us.add(new Graf(r2.getInt(1), r2.getDate(2)));
				}
			}
			getConexion().commit();
			sentencia.close();
		} catch (SQLException e) {
			System.out.println("Ocurrio una excepcion: "+e.getMessage());
		}
		cerrar();
		return us;
	}
		
	
	
	@Override
	public Biblioteca findBibByCode(String name) {
		conectar();
		ResultSet r = null;
		Biblioteca us = null;
		try {
			String sql = "SELECT * FROM BIBLIOTECA WHERE CODIGO_BIBLIOTECA = \'" +name+"\'";
			Statement sentencia;
			sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			r= sentencia.executeQuery(sql);
			while (r.next()) {
				us= new Biblioteca();
				us.setCodigo_biblioteca(r.getString("codigo_biblioteca"));
				us.setNombre_biblioteca(r.getString("nombre_biblioteca"));
				us.setMunicipio(findMunBy(r.getInt("municipio_id")));
				us.setId(r.getInt("ID"));
			}
			getConexion().commit();
			sentencia.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cerrar();
		return us;
	}
	
	@Override
	public Biblioteca findBibByUser(String username) {
		conectar();
		ResultSet r = null;
		Biblioteca us = null;
		try {
			String sql = "SELECT * FROM USUARIO WHERE USERNAME = \'" + username+"\'";
			Statement sentencia;
			sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			r= sentencia.executeQuery(sql);
			while (r.next()) {
				us=findBibBy(r.getInt("biblioteca_id"));
			}
			getConexion().commit();
			sentencia.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cerrar();
		return us;
	}	
	
	@Override
	public Municipio findMunBy(int id) {
		conectar();
		ResultSet r = null;
		Municipio us = null;
		try {
			String sql = "SELECT * FROM MUNICIPIO WHERE ID =" + id;
			Statement sentencia;
			sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			r= sentencia.executeQuery(sql);
			while (r.next()) {
				us= new Municipio();
				us.setNombre_municipio(r.getString("nombre_municipio"));
				us.setId(id);
				
			}
			getConexion().commit();
			sentencia.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cerrar();
		return us;
	}	
	
	@Override
	public Departamento findDepBy(int id) {
		conectar();
		ResultSet r = null;
		Departamento us = null;
		try {
			String sql = "SELECT * FROM DEPARTAMENTO WHERE ID =" + id;
			Statement sentencia;
			sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			r= sentencia.executeQuery(sql);
			while (r.next()) {
				us= new Departamento();
				us.setNombre_departamento(r.getString("nombre_departamento"));
				us.setId(id);
				us.setZona_geografica(r.getString("zona_geografica"));
				
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

		}
	}

	@Override
	public int addMunicipio(Municipio municipio) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
