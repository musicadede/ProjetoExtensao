package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.projetoextensao.ConexaoFactory;
import com.model.Aluno;

public class AlunoDAO {
	
	Connection conexao ;

	public AlunoDAO() {
		conexao =  new ConexaoFactory().getConnection();
	}
	

	public String calcularIdade(String nascimento) {
		
		String dataNascimento = null;
		
		String sql = "select converter_data('"+nascimento+"');";
		
		String idade=null;
		
		try {//conversor  de data do tido datField para text(date) do banco
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet resultado = ps.executeQuery();

			while(resultado.next()){
				dataNascimento =resultado.getString("converter_data");
			}

			
		} catch (SQLException e) {
			System.out.println("Erro Classe Banco metodo calcularIdade sql 1, erro : "+e);
		}
		
		
		try {//conversor de data do tipo ano/mes/dia para dia/mes/ano 
			
			sql = "select extract(year FROM age(current_date, to_date((SELECT to_char(DATE '"+dataNascimento+"', 'DD/MM/YYYY')), 'DD/MM/YYYY')));";
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet resultado = ps.executeQuery();
			//talvez tenha que trocar a idade de int para double
			while(resultado.next()){
				idade =resultado.getString("date_part");
			}

			
		} catch (SQLException e) {
			System.out.println("Erro Classe Banco metodo calcularIdade sql 2, erro : "+e);
		}

		return idade;

	}


	public List<String> buscarTodosAlunos() {

		String sql = "SELECT id_aluno,nome_aluno FROM aluno ;";
		List<String> nomes= new ArrayList<String>();
		
			try {
				PreparedStatement ps = conexao.prepareStatement(sql);
				ResultSet resultado = ps.executeQuery(); 
				
				while(resultado.next()){
					
					int idAluno = resultado.getInt("id_aluno");
					String nome = resultado.getString("nome_aluno");
					nomes.add(idAluno, nome);
				}
											
			} catch (SQLException e) {
				System.out.println("Erro Classe Banco metodo NomesEscolas, erro : "+e);
			}
		
		return nomes;
	}


	public List<String> buscarTodosNaEscola(int idEscola) {
		String sql = "SELECT id_aluno,nome_aluno FROM aluno WHERE id_escola="+idEscola+";";
		List<String> nomes= new ArrayList<String>();
		
			try {
				PreparedStatement ps = conexao.prepareStatement(sql);
				ResultSet resultado = ps.executeQuery(); 
				
				while(resultado.next()){
					
					int idAluno = resultado.getInt("id_aluno");
					String nome = resultado.getString("nome_aluno");
					nomes.add(idAluno, nome);
				}
											
			} catch (SQLException e) {
				System.out.println("Erro Classe Banco metodo NomesEscolas, erro : "+e);
			}
		
		return nomes;
	}


	public void salvar(Aluno aluno) {
		String sql = "SELECT inserir_aluno('"+aluno.getNome()+"',"
				+aluno.getIdade()+",'"
				+aluno.getSexo()+"',"
				+ aluno.getId_bairro()+","
				+ aluno.getId_cep()+","
				+ aluno.getId_rua()+","
				+ aluno.getId_estado()+","
				+ aluno.getId_cidade()+","
				+ aluno.getId_escola()+",'"
				+ aluno.getEnsino()+"',"
				+ aluno.getSerie()+",'"
				+ aluno.getTurma()+"','"
				+ aluno.getTurno()+"',"
				+ aluno.getMassa_corporal()+","
				+ aluno.getEstatura()+","
				+ aluno.getEnvergadura()+","
				+ aluno.getImc()+","
				+ aluno.getFlexibilidade()+","
				+ aluno.getAbdominal()+","
				+ aluno.getSeis_minutos()+","
				+ aluno.getSalto_em_distancia()+","
				+ aluno.getArremesso_medicineball()+","
				+ aluno.getQuadrado()+","
				+ aluno.getCorrida_20metros()+")";

		
			try {
				System.out.println("sql = "+sql);
				System.out.println("conexao = "+conexao);
				PreparedStatement ps = conexao.prepareStatement(sql);
				 ps.execute(); 
															
			} catch (SQLException e) {
				System.out.println("Erro Classe Banco metodo NomesEscolas, erro : "+e);
			}
		
	}

}
