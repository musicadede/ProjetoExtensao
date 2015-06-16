package com.Service;

import java.util.List;

import com.DAO.AbdominalDAO;
import com.model.Abdominal;

public class AbdominalService {

	AbdominalDAO abdominalDAO;
	
	public void salvarAbdominal(Abdominal abdominal) {
		
		abdominalDAO = new AbdominalDAO();
		abdominalDAO.SalvarAbdominal(abdominal);
		
	}

	public List<Abdominal> buscarTodos(char tabelaSexo) {
		abdominalDAO = new AbdominalDAO();
		return abdominalDAO.buscarTodos(tabelaSexo);
	}

	public void excluirFlexibilidade(Abdominal abdominalManipulado) {
		abdominalDAO = new AbdominalDAO();
		abdominalDAO.excluirAbdominal(abdominalManipulado);
		
	}

}
