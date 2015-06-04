package com.Service;

import java.util.List;

import com.DAO.ImcDAO;
import com.model.Imc;

public class ImcService {
	
	private ImcDAO imcDAO;
	
	public void salvarImc(Imc imc) {
		imcDAO = new ImcDAO();
		imcDAO.salvarImc(imc);
	}

	public List<Integer> buscarIdadeImcMasc() {
		imcDAO = new ImcDAO();
		return  imcDAO.buscarIdadeImcMasc();
		
	}

	public List<Integer> buscarIdadeImcFem() {
		imcDAO = new ImcDAO();
		return  imcDAO.buscarIdadeImcFem();
		
	}

	public Imc buscarImc(Imc imc) {
		imcDAO = new ImcDAO();
		return imcDAO.buscarImc(imc);
	}

	

	public void excluirImc(Imc imc) {
		imcDAO = new ImcDAO();
		imcDAO.excluirImc(imc);
		
	}

	public void editarImc(Imc imc) {
		imcDAO = new ImcDAO();
		imcDAO.editarImc(imc);
		
		
	}

}
