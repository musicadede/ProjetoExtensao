package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.projetoextensao.ConexaoFactory;

public class TurmaDAO {

	Connection conexao;
	public TurmaDAO() {
		conexao =  new ConexaoFactory().getConnection();
		System.out.println("Conectado banco");
	}
	
	public List<Character> buscarTurmas(int idEscola, int idSerie, String ensino) {
		
		String sql = "SELECT turma FROM serie_turma WHERE serie = "+idSerie+" AND id_escola ="+idEscola+" AND ensino = '"+ensino+"';";
		
		List<Character> turmas = new ArrayList<Character>();
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet resultado = ps.executeQuery(); 
			int indice= 0;
			while(resultado.next()){
				char turm=resultado.getString("turma").charAt(0);
				turmas.add(turm);
				indice++;
			}
										
		} catch (SQLException e) {
			System.out.println("Erro Classe Banco metodo NomesEscolas, erro : "+e);
		}
	
		return turmas;
	}

}
