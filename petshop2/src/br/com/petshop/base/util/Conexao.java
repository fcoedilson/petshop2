package br.com.petshop.base.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	
	private String usuario;
	private String senha;
	private String url;
	private String drive;
	
	public Conexao(String url, String drive, String usuario, String senha) {
		this.url = url;
		this.drive = drive;
		this.usuario = usuario;
		this.senha = senha;
	}
	
	public static void fechaConexao(Connection conn) throws Exception {
		if (conn != null) {
			conn.close();
			conn = null;
		}
	}

	public Connection getConexao() throws Exception {
		return getConexao(drive, url, usuario, senha);
	}

	public static Connection getConexao(String drive, String url, String usuario, String senha) throws Exception {
		Connection conn;
		Class.forName(drive);
		conn = DriverManager.getConnection(url, usuario, senha);
		conn.setAutoCommit(true);
		return conn;
	}
	
}
