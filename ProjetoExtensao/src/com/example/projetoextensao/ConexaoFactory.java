package com.example.projetoextensao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
	
	private String user;
	private String senha;
	int contador =0;
	
//	public ConexaoFactory(){
//		user = "userlaf";
//		senha = "laf.2015";
//	}
//	
//	public Connection getConnection(){
//		String url = "jdbc:postgresql://localhost:5432/db_laf";
	
	public ConexaoFactory(){
		user = "postgres";
		senha = "root";
	}
	
	public Connection getConnection(){
		String url = "jdbc:postgresql://localhost:5432/db_laf";

			try {
				Class.forName("org.postgresql.Driver");
				return DriverManager.getConnection(url, user, senha);
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("Exceção na canexao Factory ,erro :"+e);
			}  
			
		return null;
					
					
					
	}

}