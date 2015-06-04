package com.Service;

import java.util.List;

import com.DAO.EscolaDAO;
import com.model.Escola;

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

}
