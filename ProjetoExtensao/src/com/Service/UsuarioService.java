package com.Service;

import com.DAO.UsuarioDAO;
import com.model.Usuario;

public class UsuarioService {

	public UsuarioService() {
		
	}
	
	
	public static Usuario buscarUsuario(String nome) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();  
		return usuarioDAO.buscarUsuario(nome);
	}

}
