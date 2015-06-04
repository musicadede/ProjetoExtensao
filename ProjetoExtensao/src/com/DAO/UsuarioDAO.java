package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.projetoextensao.ConexaoFactory;
import com.model.Usuario;

public class UsuarioDAO {
	 Connection conexao;
	
	public UsuarioDAO() {
		conexao = new ConexaoFactory().getConnection();
		
	}
	
	public Usuario buscarUsuario(String nome) {
		String  sql= "SELECT * FROM usuario WHERE nome_usuario='"+nome+"';";
		
		Usuario usuario = new Usuario();
		
		try {
			PreparedStatement ps= conexao.prepareStatement(sql);
			ResultSet resultado  = ps.executeQuery();
			ps = conexao.prepareStatement(sql);
			resultado = ps.executeQuery(); 
			
			while(resultado.next()){
				usuario.setId_usuario(resultado.getInt("id_usuario"));
				usuario.setNome_usuario(resultado.getString("nome_usuario"));
				usuario.setLogin_usuario(resultado.getString("login_usuario"));
				
			}
										
		} catch (SQLException e) {
			System.out.println("Erro Classe UsuarioDAo metodo buscarUsuario(), erro : "+e);
		}
	
		return usuario;
	}

}
