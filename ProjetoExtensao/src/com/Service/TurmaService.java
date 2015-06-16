package com.Service;

import java.util.List;

import com.DAO.TurmaDAO;

public class TurmaService {

	TurmaDAO turmaDAO;
	
	public List<Character> buscarTurma(int idEscola, int idSerie, String ensino) {
		turmaDAO = new TurmaDAO();
		return turmaDAO.buscarTurmas(idEscola,idSerie,ensino);
	}

}
