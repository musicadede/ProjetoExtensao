package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.projetoextensao.ConexaoFactory;
import com.model.DadosRelatorio;

public class DadosDAO {

	Connection conexao;

	public DadosDAO() {
		conexao = new ConexaoFactory().getConnection();
	}

	public int buscarBaixoPesoCidadeEscola(int idCidade, int idEscola) {

		int dados = 0;
		String sqlBaixoPeso = "SELECT COUNT(descr_imc) as total FROM aluno WHERE descr_imc = 'Baixo Peso' AND id_cidade= "
				+ idCidade + " AND id_escola =" + idEscola + ";";

		try {
			PreparedStatement ps = conexao.prepareStatement(sqlBaixoPeso);
			ResultSet resultado = ps.executeQuery();

			while (resultado.next()) {
				dados = resultado.getInt("total");
			}

		} catch (SQLException e) {
			System.out
					.println("Erro Classe DadosDAO metodo buscarBaixoPesoCidadeEscola, erro : "
							+ e);
		}
		return dados;
	}

	public int buscarNormalCidadeEscola(int idCidade, int idEscola) {
		int dados = 0;
		String sqlNormal = "SELECT COUNT(descr_imc) as total FROM aluno WHERE descr_imc = 'Normal' AND id_cidade= "
				+ idCidade + " AND id_escola =" + idEscola + ";";

		try {
			PreparedStatement ps = conexao.prepareStatement(sqlNormal);
			ResultSet resultado = ps.executeQuery();

			while (resultado.next()) {
				dados = resultado.getInt("total");
			}

		} catch (SQLException e) {
			System.out
					.println("Erro Classe DadosDAO metodo buscarNormalCidadeEscola, erro : "
							+ e);
		}
		return dados;

	}

	public int buscarExcessoPesoCidadeEscola(int idCidade, int idEscola) {
		int dados = 0;
		String sqlExcessoPeso = "SELECT COUNT(descr_imc) as total FROM aluno WHERE descr_imc = 'Excesso de Peso' AND id_cidade= "
				+ idCidade + " AND id_escola =" + idEscola + ";";

		try {
			PreparedStatement ps = conexao.prepareStatement(sqlExcessoPeso);
			ResultSet resultado = ps.executeQuery();

			while (resultado.next()) {
				dados = resultado.getInt("total");
			}

		} catch (SQLException e) {
			System.out
					.println("Erro Classe DadosDAO metodo buscarExcessoPesoCidadeEscola(), erro : "
							+ e);
		}
		return dados;

	}

	public int buscarObesidadeCidadeEscola(int idCidade, int idEscola) {
		int dados = 0;
		String sqlObesidade = "SELECT COUNT(descr_imc) as total FROM aluno WHERE descr_imc = 'Obesidade' AND id_cidade= "
				+ idCidade + " AND id_escola =" + idEscola + ";";

		try {
			PreparedStatement ps = conexao.prepareStatement(sqlObesidade);
			ResultSet resultado = ps.executeQuery();

			while (resultado.next()) {
				dados = resultado.getInt("total");
			}

		} catch (SQLException e) {
			System.out
					.println("Erro Classe DadosDAO metodo buscarObesidadeCidadeEscola, erro : "
							+ e);
		}
		return dados;

	}

	public int buscarDadosBaixoPesoCidadeEscolaSerie(int idCidade, int idEscola,int serie) {
			int dados =0;
			String sqlBaixoPeso = "SELECT COUNT(descr_imc) as total FROM aluno "
					+ "WHERE descr_imc = 'Baixo Peso' AND id_cidade="+idCidade+" AND id_escola = "+idEscola+" AND serie ="+serie+";";
			
			try {
				PreparedStatement ps = conexao.prepareStatement(sqlBaixoPeso);
				ResultSet resultado = ps.executeQuery();
				
				while(resultado.next()){
					dados = resultado.getInt("total");
				}
											
			} catch (SQLException e) {
				System.out.println("Erro Classe DadosDAO metodo buscarDadosBaixoPesoCidadeEscolaSerie(), erro : "+e);
			}
			
			return dados;
	}

	public int buscarDadosNormalCidadeEscolaSerie(int idCidade, int idEscola,int serie) {
		
		int dados =0;
		String sqlNormal= "SELECT COUNT(descr_imc) as total FROM aluno "
				+ "WHERE descr_imc = 'Normal' AND id_cidade='"+idCidade+"' AND id_escola ='"+idEscola+"' AND serie ="+serie+";";
		
		try {
			PreparedStatement ps = conexao.prepareStatement(sqlNormal);
			ResultSet resultado = ps.executeQuery();
			
			while(resultado.next()){
				dados = resultado.getInt("total");
			}
										
		} catch (SQLException e) {
			System.out.println("Erro Classe DadosDAO metodo buscarDadosNormalCidadeEscolaSerie(), erro : "+e);
		}
		
		return dados;
	}
	
	public int buscarDadosExcessoPesoCidadeEscolaSerie(int idCidade, int idEscola,int serie) {
		
		int dados =0;
		String sqlExcessoPeso= "SELECT COUNT(descr_imc) as total FROM aluno "
				+ "WHERE descr_imc = 'Excesso de Peso' AND id_cidade='"+idCidade+"' AND id_escola ='"+idEscola+"' AND serie ="+serie+";";
		
		try {
			PreparedStatement ps = conexao.prepareStatement(sqlExcessoPeso);
			ResultSet resultado = ps.executeQuery();
			
			while(resultado.next()){
				dados = resultado.getInt("total");
			}
			
		} catch (SQLException e) {
			System.out.println("Erro Classe DadosDAO metodo buscarDadosExcessoPesoCidadeEscolaSerie(), erro : "+e);
		}
		
		return dados;
	}
	
	public int buscarDadosObesidadeCidadeEscolaSerie(int idCidade, int idEscola,int serie) {
		
		int dados =0;
		String sqlObesidade= "SELECT COUNT(descr_imc) as total FROM aluno "
				+ "WHERE descr_imc = 'Obesidade' AND id_cidade='"+idCidade+"' AND id_escola ='"+idEscola+"' AND serie ="+serie+";";
		
		try {
			PreparedStatement ps = conexao.prepareStatement(sqlObesidade);
			ResultSet resultado = ps.executeQuery();
			
			while(resultado.next()){
				dados = resultado.getInt("total");
			}
			
		} catch (SQLException e) {
			System.out.println("Erro Classe DadosDAO metodo buscarDadosObesidadeCidadeEscolaSerie(), erro : "+e);
		}
		
		return dados;
	}
	
	
}
