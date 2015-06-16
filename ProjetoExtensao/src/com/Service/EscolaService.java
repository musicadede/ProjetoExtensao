package com.Service;

import java.util.List;

import com.DAO.EscolaDAO;

public class EscolaService {

	public EscolaDAO escolaDAO;
	
	public List<String>buscarNomesEscolas(int idcidade) {
		escolaDAO = new EscolaDAO();
		return escolaDAO.buscarNomesEscolas(idcidade);
	}

	public int buscarIDEScola(String nomeEscola) {
		escolaDAO = new EscolaDAO();
		return escolaDAO.buscarIDEscola(nomeEscola);
	}

	public List<String> buscarEnsino(int idEscola) {
		escolaDAO = new EscolaDAO();
		return escolaDAO.buscarEnsino(idEscola);
	}

	

}
