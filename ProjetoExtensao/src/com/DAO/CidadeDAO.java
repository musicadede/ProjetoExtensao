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

public class CidadeDAO {


	Connection conexao;

	public CidadeDAO() {
		conexao = new ConexaoFactory().getConnection();
	}
	
	public void salvarCidade(Cidade cidade) {
		String sql = null;
		
//			sql = "INSERT INTO imc_masculino (idade_imc,baixo_peso, normal,excesso_peso)"
//					+ "VALUES ("
//					+ cidade.getNome_cidade()
//					+ ","
//					+ cidade.getId_estado()
//					+");";
			

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out
					.println("Erro Classe CidadeDAO metodo salvarCidade(), erro : "
							+ e);
		}

	}

	public List<String> buscarCidade() {

		List<String> cidades = new ArrayList<String>();
		String sql = "SELECT * FROM cidade;";


		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet resultado = ps.executeQuery();

			while (resultado.next()) {
				
				cidades.add(resultado.getString("nome_cidade"));
			}

		} catch (SQLException e) {
			System.out
					.println("Erro Classe ImcDAO metodo buscarCidade(), erro : "
							+ e);
		}

		return cidades;
	}

	public int buscarIDCidade(String nomeCidade) {
		int idCidade = 0;
		String sql = "SELECT id_cidade FROM cidade WHERE nome_cidade='"+nomeCidade+"';";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet resultado = ps.executeQuery();

			while (resultado.next()) {
				idCidade= (resultado.getInt("id_cidade"));
			}

		} catch (SQLException e) {
			System.out
					.println("Aki Erro Classe CidadeDAO metodobuscarIDCidade, erro : "
							+ e);
		}


		return idCidade;
	}

}
