package com.Service;

import java.util.List;

import com.DAO.CidadeDAO;

public class CidadeService {

	CidadeDAO cidadeDAO;
	
	public List<String> buscarCidade() {
		cidadeDAO = new CidadeDAO();
		return cidadeDAO.buscarCidade();
	}

	public int buscarIDCidade(String cidade) {
		cidadeDAO = new CidadeDAO();
		return cidadeDAO.buscarIDCidade(cidade);
	}

}
