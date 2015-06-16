package com.Service;

import java.util.List;

import com.DAO.ArremessoMedicineballDAO;
import com.model.ArremessoMedicineball;


public class ArremessoMedicineballService {
	
	ArremessoMedicineballDAO arMedicineballDAO;
	
	public void salvarArremessoMedicineball(ArremessoMedicineball arMedicineball) {
		
		arMedicineballDAO = new ArremessoMedicineballDAO();
		arMedicineballDAO.salvarArremessoMedicineball(arMedicineball);
		
	}

	public List<ArremessoMedicineball> buscarTodos(char tabelaSexo) {
		arMedicineballDAO = new ArremessoMedicineballDAO();
		return arMedicineballDAO.buscarTodos(tabelaSexo);
	}

	public void excluirArremessoMedicineball(ArremessoMedicineball arMedicineball) {
		arMedicineballDAO = new ArremessoMedicineballDAO();
		arMedicineballDAO.excluirArremessoMedicineball(arMedicineball);
		
	}

}
