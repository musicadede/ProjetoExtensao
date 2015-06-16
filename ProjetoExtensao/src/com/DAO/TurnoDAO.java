package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.projetoextensao.ConexaoFactory;

public class TurnoDAO {

	Connection conexao;

	public TurnoDAO() {
		conexao = new ConexaoFactory().getConnection();
	}

	public List<String> buscarTurnos(int idEscola) {

		List<String> turnos = new ArrayList<String>();
		String sql = "SELECT turno FROM serie_turma WHERE id_escola="+idEscola+";";


		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet resultado = ps.executeQuery();

			while (resultado.next()) {
				
				turnos.add(resultado.getString("turno"));
			}

		} catch (SQLException e) {
			System.out
					.println("Erro Classe TurnoDAO metodo buscarTurnos(), erro : "
							+ e);
		}

		return turnos;
	}
}
