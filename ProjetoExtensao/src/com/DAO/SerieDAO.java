package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.projetoextensao.ConexaoFactory;

public class SerieDAO {
	
	Connection conexao;

	public SerieDAO() {
		conexao = new ConexaoFactory().getConnection();
	}

	public List<Integer> buscarSeries(int idEscola) {
		
		
		List<Integer> series= new ArrayList<Integer>();
		String sql = "SELECT serie FROM serie_turma WHERE id_escola ="+idEscola+" ;";
					
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet resultado = ps.executeQuery();

			while (resultado.next()) {
				series.add(resultado.getInt("serie"));
			}

		} catch (SQLException e) {
			System.out
					.println("Erro Classe SerieDAO metodo buscarSeries(), erro : "
							+ e);
		}

		
		return series;

	}

}
