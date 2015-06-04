package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.projetoextensao.ConexaoFactory;
import com.model.Cidade;
import com.model.Escola;

public class EscolaDAO {

	Connection conexao;
	public EscolaDAO() {
			conexao = new ConexaoFactory().getConnection();
	}
	public List<String> buscarNomesEscolas(int idCidade) {
		
		List<String> escolas = new ArrayList<String>();
		String sql = "SELECT * FROM escola;";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet resultado = ps.executeQuery();

			while (resultado.next()) {
				escolas.add(resultado.getString("nome_escola"));
			}

		} catch (SQLException e) {
			System.out
					.println("Erro Classe ImcDAO metodo buscarNomesEscolas(), erro : "
							+ e);
		}

		return escolas;

	}
	public int buscarIDEscola(String nomeEscola) {

		int idEscola = 0;
		String sql = "SELECT id_escola FROM escola WHERE nome_escola='"+nomeEscola+"';";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet resultado = ps.executeQuery();

			while (resultado.next()) {
				idEscola = resultado.getInt("id_escola");
			}

		} catch (SQLException e) {
			System.out
					.println("Erro Classe ImcDAO metodo buscarIDEscola(), erro : "
							+ e);
		}

		return idEscola;

	}
	
	

}
