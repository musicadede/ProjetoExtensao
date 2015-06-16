package com.Service;

import java.util.List;

import com.DAO.FlexibilidadeDAO;
import com.model.Flexibilidade;

public class FlexibilidadeService {

	FlexibilidadeDAO flexibilidadeDAO;
	
	public void salvarFlexibilidade(Flexibilidade flexibilidade) {
		
		flexibilidadeDAO = new FlexibilidadeDAO();
		flexibilidadeDAO.SalvarFlexibilidade(flexibilidade);
		
	}

	public List<Flexibilidade> buscarTodos(char tabelaSexo) {
		flexibilidadeDAO = new FlexibilidadeDAO();
		return flexibilidadeDAO.buscarTodos(tabelaSexo);
	}

	public void excluirFlexibilidade(Flexibilidade flexibilidadeManipulado) {
		flexibilidadeDAO = new FlexibilidadeDAO();
		flexibilidadeDAO.excluirFlexibilidade(flexibilidadeManipulado);
		
	}
}
