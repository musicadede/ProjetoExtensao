package com.Service;

import java.util.List;

import com.DAO.SerieDAO;

public class SerieService {

	private SerieDAO serieDAO;

	public List<Integer> buscarSeries(int idEscola) {
		serieDAO = new SerieDAO();
		return serieDAO.buscarSeries(idEscola);
	}
	
	
	
	
}
