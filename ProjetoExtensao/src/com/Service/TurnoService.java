package com.Service;

import java.util.List;

import com.DAO.TurnoDAO;

public class TurnoService {

	TurnoDAO turnoDAO = new TurnoDAO();

	public List<String> buscarTurnos(int idEscola) {
		turnoDAO = new TurnoDAO();
		return turnoDAO.buscarTurnos(idEscola);
	}
}
