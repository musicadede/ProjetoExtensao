package com.Service;

import java.util.List;

import com.DAO.QuadradoDAO;
import com.model.Quadrado;


public class QuadradoService {

	QuadradoDAO quadradoDAO;
	
	public void salvarQuadrado(Quadrado quadrado) {
		
		quadradoDAO = new QuadradoDAO();
		quadradoDAO.SalvarQuadrado(quadrado);
		
	}

	public List<Quadrado> buscarTodos(char tabelaSexo) {
		quadradoDAO = new QuadradoDAO();
		return quadradoDAO.buscarTodos(tabelaSexo);
	}

	public void excluirQuadrado(Quadrado quadrado) {
		quadradoDAO = new QuadradoDAO();
		quadradoDAO.excluirQuadrado(quadrado);
		
	}
}
