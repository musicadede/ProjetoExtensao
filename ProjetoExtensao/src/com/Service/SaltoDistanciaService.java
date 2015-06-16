package com.Service;

import java.util.List;

import com.DAO.SaltoDistanciaDAO;
import com.model.SaltoDistancia;


public class SaltoDistanciaService {

	SaltoDistanciaDAO saltoDistanciaDAO;
	
	public void salvarSaltoDistancia(SaltoDistancia saltoDistancia) {
		
		saltoDistanciaDAO = new SaltoDistanciaDAO();
		saltoDistanciaDAO.SalvarSaltoDistancia(saltoDistancia);
		
	}

	public List<SaltoDistancia> buscarTodos(char tabelaSexo) {
		saltoDistanciaDAO = new SaltoDistanciaDAO();
		return saltoDistanciaDAO.buscarTodos(tabelaSexo);
	}

	public void excluirSaltoDistancia(SaltoDistancia saltoDistancia) {
		saltoDistanciaDAO = new SaltoDistanciaDAO();
		saltoDistanciaDAO.excluirSaltoDistancia(saltoDistancia);
		
	}

}
