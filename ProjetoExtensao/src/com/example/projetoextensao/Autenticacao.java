package com.example.projetoextensao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.collections.functors.ExceptionPredicate;



public class Autenticacao {

	public Boolean autentAdmin(String login, String senha ) throws SQLException, ClassNotFoundException {
		boolean ret =false;

		Class.forName ( "org.postgresql.Driver");
		Connection conexao =  new ConexaoFactory().getConnection();
		Statement inCon = conexao.createStatement();
		ResultSet resultado ;
		String criptografarSenha;
		
		criptografarSenha = "SELECT criptografar_senha('"+senha+"');";
		resultado = inCon.executeQuery(criptografarSenha);


				while(resultado.next()){
					criptografarSenha =  resultado.getString("criptografar_senha");
				}
			
		String sql = "SELECT login_adm, senha_adm FROM administrador WHERE login_adm ='"+login+"' AND senha_adm ='"+criptografarSenha+"';";
		resultado = inCon.executeQuery(sql);


				while(resultado.next()){
					if(resultado.getString("login_adm").equals(login)&&resultado.getString("senha_adm").equals(criptografarSenha)){
						ret=true; 
					}
				}
			
		return ret;
		
}

	public Boolean autentUsuario(String login, String senha ) throws SQLException {
		boolean ret =false;
		Connection conexao =  new ConexaoFactory().getConnection();
		Statement inCon = conexao.createStatement();
		ResultSet resultado ;
		String criptografarSenha;
		
		criptografarSenha = "SELECT criptografar_senha('"+senha+"');";
		resultado = inCon.executeQuery(criptografarSenha);


				while(resultado.next()){
					criptografarSenha =  resultado.getString("criptografar_senha");
				}
			

			String sql = "SELECT login_usuario, senha_usuario FROM usuario WHERE login_usuario ='"+login+"' AND senha_usuario='"+criptografarSenha+"';";
		
			resultado = inCon.executeQuery(sql);

		
			while(resultado.next()){
				if(resultado.getString("login_usuario").equals(login)&&resultado.getString("senha_usuario").equals(criptografarSenha)){
					ret=true; 
				}
			}
		
	return ret;
}

	
}