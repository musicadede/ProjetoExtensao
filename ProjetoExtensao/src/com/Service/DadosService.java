package com.Service;

import com.DAO.DadosDAO;
import com.model.DadosRelatorio;

public class DadosService {

	DadosDAO dadosDAO;
	
	public int buscarDadosBaixoPesoCidadeEscola(int idCidade, int idEscola) {
		dadosDAO = new DadosDAO();
		return dadosDAO.buscarBaixoPesoCidadeEscola(idCidade,idEscola);
	}

	public int buscarDadosNormalCidadeEscola(int idCidade, int idEscola) {
		dadosDAO = new DadosDAO();
		return dadosDAO.buscarNormalCidadeEscola(idCidade,idEscola);
	}

	public int buscarDadosExcessoPesoCidadeEscola(int idCidade, int idEscola) {
		dadosDAO = new DadosDAO();
		return dadosDAO.buscarExcessoPesoCidadeEscola(idCidade,idEscola);
	}

	public int buscarDadosObesidadeCidadeEscola(int idCidade, int idEscola) {
		dadosDAO = new DadosDAO();
		return dadosDAO.buscarObesidadeCidadeEscola(idCidade,idEscola);
	}

	public int buscarDadosBaixoPesoCidadeEscolaSerie(int idCidade, int idEscola,int serie) {
		dadosDAO = new DadosDAO();
		return dadosDAO.buscarDadosBaixoPesoCidadeEscolaSerie(idCidade,idEscola,serie);
	}

	public int buscarDadosNormalCidadeEscolaSerie(int idCidade, int idEscola,int serie) {
		dadosDAO = new DadosDAO();
		return dadosDAO.buscarDadosNormalCidadeEscolaSerie(idCidade,idEscola,serie);
	}

	public int buscarDadosExcessoPesoCidadeEscolaSerie(int idCidade,int idEscola, int serie) {
		dadosDAO = new DadosDAO();
		return dadosDAO.buscarDadosExcessoPesoCidadeEscolaSerie(idCidade,idEscola,serie);
	}

	public int buscarDadosObesidadeCidadeEscolaSerie(int idCidade,int idEscola, int serie) {
		dadosDAO = new DadosDAO();
		return dadosDAO.buscarDadosObesidadeCidadeEscolaSerie(idCidade,idEscola,serie);
	}

}
