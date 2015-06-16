package com.Service;

import java.util.List;

import com.DAO.Corrida20MetrosDAO;
import com.model.Corrida20Metros;


public class Corrida20MetrosService {

	
	Corrida20MetrosDAO corrida20MetrosDAO;
	
	public void salvarCorrida20Metros(Corrida20Metros corrida20Metros) {
		
		corrida20MetrosDAO = new Corrida20MetrosDAO();
		corrida20MetrosDAO.SalvarCorrida20Metros(corrida20Metros);
		
	}

	public List<Corrida20Metros> buscarTodos(char tabelaSexo) {
		corrida20MetrosDAO = new Corrida20MetrosDAO();
		return corrida20MetrosDAO.buscarTodos(tabelaSexo);
	}

	public void excluirCorrida20Metros(Corrida20Metros corrida20Metros) {
		corrida20MetrosDAO = new Corrida20MetrosDAO();
		corrida20MetrosDAO.excluirCorrida20Metros(corrida20Metros);
		
	}

}
