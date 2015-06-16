package com.Service;

import java.util.List;

import com.DAO.AlunoDAO;
import com.model.Aluno;

public class AlunoService {

	AlunoDAO alunoDAO;
	public String calcularIdade(String dataNascimento) {
		alunoDAO = new AlunoDAO();
		return alunoDAO.calcularIdade(dataNascimento);
	}
	public List<String> buscarTodos() {
		alunoDAO =new AlunoDAO();
		return alunoDAO.buscarTodosAlunos();
	}
	public List<String> buscarTodosNaEscola(int idEscola) {
		alunoDAO = new AlunoDAO();
		return alunoDAO.buscarTodosNaEscola(idEscola);
	}
	public void salvarAluno(Aluno aluno) {
		alunoDAO = new AlunoDAO();
		alunoDAO.salvar(aluno);
		
	}

}
