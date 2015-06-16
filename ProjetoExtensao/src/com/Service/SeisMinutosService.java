package com.Service;

import java.util.List;

import com.DAO.SeisMinutosDAO;
import com.model.SeisMinutos;

public class SeisMinutosService {

	SeisMinutosDAO seisMinutosDAO;
	
	public void salvarSeisMinutos(SeisMinutos seisMinutos) {
		
		seisMinutosDAO = new SeisMinutosDAO();
		seisMinutosDAO.SalvarSeisMinutos(seisMinutos);
		
	}

	public List<SeisMinutos> buscarTodos(char tabelaSexo) {
		seisMinutosDAO = new SeisMinutosDAO();
		return seisMinutosDAO.buscarTodos(tabelaSexo);
	}

	public void excluirSeisMinutos(SeisMinutos seisMinutos) {
		seisMinutosDAO = new SeisMinutosDAO();
		seisMinutosDAO.excluirSeisMinutos(seisMinutos);
		
	}

}
