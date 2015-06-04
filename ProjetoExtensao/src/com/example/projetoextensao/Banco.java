package com.example.projetoextensao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import java_cup.internal_error;

import com.model.DadosRelatorio;
import com.model.Imc;
import com.model.Serie;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;


public class Banco {
	
	Connection conexao = new ConexaoFactory().getConnection();
	PreparedStatement ps;
	String sql;
	ResultSet resultado ;
	List<String> escolas;
	
	public Banco() {
		conexao =  new ConexaoFactory().getConnection();
		System.out.println("Conectado banco");
	}
	
	//okbanco
	public void adicionarUsuario(String nome, String login, String senha){
		try{
			
			sql = "INSERT INTO usuario(id_usuario,nome_usuario,login_usuario,senha_usuario) VALUES(default,'"+nome+"','"+login+"',MD5('"+senha+"'));";
			ps =conexao.prepareStatement(sql);
			ps.execute();
	
		}catch (Exception e){
			System.out.println("Exceção  de Consulta"+e);
		}
	}

	public void buscarNomesEscolas(ComboBox escolas,String cidade){
		
		sql = "SELECT nome_escola FROM cidade WHERE nome_cidade = '"+cidade+"';";
		
			try {
				ps = conexao.prepareStatement(sql);
				resultado = ps.executeQuery(); 
				
				while(resultado.next()){
					String nova = resultado.getString("nome_escola");
					escolas.addItem(nova);
				}
											
			} catch (SQLException e) {
				System.out.println("Erro Classe Banco metodo NomesEscolas, erro : "+e);
			}
		
		
	}
	
	public void cadastrarAluno(String nome,int idade,String sexo,String cidade,String escola,String ensino, int serie,char turma,double peso,double altura){
		sql = "SELECT inserir_aluno('"+nome+"',"+idade+",'"+sexo+"','"+cidade+"','"+escola+"','"
									+ensino+"',"+serie+",'"+turma+"',"+peso+","+altura+");";     
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.executeQuery(); 
		}catch(Exception e){
			System.out.println("Erro na classe Banco metodo cadastrarAluno "+e);
		}
		
	}
	
	public void buscarSeries(ComboBox filtroSerie, int idescola){
		sql = "SELECT S.serie FROM serie S,series_escolas SE WHERE S.id_serie = SE.id_serie AND SE.id_escola="+idescola+" GROUP BY serie ORDER BY serie ASC";
		try {
			ps = conexao.prepareStatement(sql);
			resultado = ps.executeQuery(); 
			
			while(resultado.next()){
				String nova = resultado.getString("serie");
				filtroSerie.addItem(nova);
			}
										
		} catch (SQLException e) {
			System.out.println("Erro Classe Banco metodo buscarEscolas, erro : "+e);
		}
		
	}
	
	public void buscarTurma(ComboBox turma) {
		sql = "SELECT turma FROM turma";
		
		try {
			ps = conexao.prepareStatement(sql);
			resultado = ps.executeQuery(); 
			
			while(resultado.next()){
				String nova = resultado.getString("turma");
				turma.addItem(nova);
			}
										
		} catch (SQLException e) {
			System.out.println("Erro Classe Banco metodo buscarTurma, erro : "+e);
		}
		
	}

	public void buscarCidade(ComboBox cidade) {
		sql = "SELECT nome_cidade FROM cidade";
		
		try {
			ps = conexao.prepareStatement(sql);
			resultado = ps.executeQuery(); 
			
			while(resultado.next()){
				String nova = resultado.getString("nome_cidade");
				cidade.addItem(nova);
			}
										
		} catch (SQLException e) {
			System.out.println("Erro Classe Banco metodo buscarTurma, erro : "+e);
		}
		
		
	}
	
	public int buscarIdCidade(String nomeCidade){
		sql = "SELECT id_cidade FROM cidade  where nome_cidade='"+nomeCidade+"';";
		int id =0;
		
		try {
			ps = conexao.prepareStatement(sql);
			resultado = ps.executeQuery(); 
			
			while(resultado.next()){
				id = resultado.getInt("id_cidade");
			}
										
		} catch (SQLException e) {
			System.out.println("Erro Classe Banco metodo buscarTurma, erro : "+e);
		}
		
		return id;
		
	}
	
	public void filtrarCidadeGrafico(DadosRelatorio dados){
//		String sqlBaixoPeso = "SELECT COUNT(nome_aluno) FROM aluno WHERE imc";
//		String sqlSobrePeso = "";
//		String sqlObesidade = "";
	}

	public void cadastrarEscola(String nomeesc,int idCidade){
		sql = "INSERT INTO escola (id_escola, nome_escola,id_cidade) VALUES(default,'"+nomeesc+"',"+idCidade+");";     
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.execute(); 
		}catch(Exception e){
			System.out.println("Erro na classe Banco metodo cadastrarEscola linha 156 "+e);
		}
	}

	public void buscarDadosPorCidade(DadosRelatorio dados, String nomeDaCidade) {
		String sqlBaixoPeso = "SELECT COUNT(descricao_imc) as total FROM aluno WHERE descricao_imc = 'Baixo Peso' AND nome_cidade='"+nomeDaCidade+"';";
		
		try {
			ps = conexao.prepareStatement(sqlBaixoPeso);
			resultado = ps.executeQuery(); 
			
			while(resultado.next()){
				dados.setBaixoPeso(Integer.parseInt(resultado.getString("total")));
			}
										
		} catch (SQLException e) {
			System.out.println("Erro Classe Banco metodo buscarDados em sqlBaixoPeso , erro : "+e);
		}
		
			
		
		
		String sqlSobrePeso= "SELECT COUNT(descricao_imc) as total FROM aluno WHERE descricao_imc = 'Sobrepeso'AND nome_cidade='"+nomeDaCidade+"';";
		
		try {
			ps = conexao.prepareStatement(sqlSobrePeso);
			resultado = ps.executeQuery(); 
			
			while(resultado.next()){
				dados.setExcessoPeso(Integer.parseInt(resultado.getString("total")));
			}
										
		} catch (SQLException e) {
			System.out.println("Erro Classe Banco metodo buscarDados em sqlSobrePeso , erro : "+e);
		}
		
		
		String sqlObesidade= "SELECT COUNT(descricao_imc) as total FROM aluno WHERE descricao_imc = 'obesidade'AND nome_cidade='"+nomeDaCidade+"';";
		
		try {
			ps = conexao.prepareStatement(sqlObesidade);
			resultado = ps.executeQuery(); 
			
			while(resultado.next()){
				dados.setObesidade(Integer.parseInt(resultado.getString("total")));
			}
										
		} catch (SQLException e) {
			System.out.println("Erro Classe Banco metodo buscarDados em sqlObesidade, erro : "+e);
		}
		
	}

	public void buscarDadosPorCidadeEscola(DadosRelatorio dados,
		String nomeDaCidade, String nomeDaEscola) {
		
		String sqlBaixoPeso = "SELECT COUNT(descricao_imc) as total FROM aluno WHERE descricao_imc = 'Baixo Peso' AND nome_cidade='"+nomeDaCidade+"' AND nome_escola ='"+nomeDaEscola+"';";
		
		try {
			ps = conexao.prepareStatement(sqlBaixoPeso);
			resultado = ps.executeQuery(); 
			
			while(resultado.next()){
				dados.setBaixoPeso(Integer.parseInt(resultado.getString("total")));
			}
										
		} catch (SQLException e) {
			System.out.println("Erro Classe Banco metodo buscarDados em sqlBaixoPeso , erro : "+e);
		}
		
			
		
		
		String sqlSobrePeso= "SELECT COUNT(descricao_imc) as total FROM aluno WHERE descricao_imc = 'SobrePeso'AND nome_cidade='"+nomeDaCidade+"' AND nome_escola ='"+nomeDaEscola+"';";
		
		try {
			ps = conexao.prepareStatement(sqlSobrePeso);
			resultado = ps.executeQuery(); 
			
			while(resultado.next()){
				dados.setExcessoPeso(Integer.parseInt(resultado.getString("total")));
			}
										
		} catch (SQLException e) {
			System.out.println("Erro Classe Banco metodo buscarDados em sqlSobrePeso , erro : "+e);
		}
		
		
		String sqlObesidade= "SELECT COUNT(descricao_imc) as total FROM aluno WHERE descricao_imc = 'obesidade'AND nome_cidade='"+nomeDaCidade+"' AND nome_escola ='"+nomeDaEscola+"';";
		
		try {
			ps = conexao.prepareStatement(sqlObesidade);
			resultado = ps.executeQuery(); 
			
			while(resultado.next()){
				dados.setObesidade(Integer.parseInt(resultado.getString("total")));
				
				System.out.println("BDCE baixo peso = "+dados.getBaixoPeso());
				System.out.println("BDCE sobrepeso = "+dados.getExcessoPeso());
				System.out.println("BDCE Obesidade = "+dados.getObesidade());
				
			}
										
		} catch (SQLException e) {
			System.out.println("Erro Classe Banco metodo buscarDados em sqlObesidade, erro : "+e);
		}

		
	}
	
	public void buscarDadosPorCidadeEscolaSerie(DadosRelatorio dados,
			String nomeDaCidade, String nomeDaEscola,int serie) {
			
			String sqlBaixoPeso = "SELECT COUNT(descricao_imc) as total FROM aluno WHERE descricao_imc = 'Baixo Peso' AND nome_cidade='"+nomeDaCidade+"' AND nome_escola ='"+nomeDaEscola+"' AND serie ="+serie+";";
			
			try {
				ps = conexao.prepareStatement(sqlBaixoPeso);
				resultado = ps.executeQuery(); 
				
				while(resultado.next()){
					dados.setBaixoPeso(Integer.parseInt(resultado.getString("total")));
				}
											
			} catch (SQLException e) {
				System.out.println("Erro Classe Banco metodo buscarDados em sqlBaixoPeso , erro : "+e);
			}
			
				
			
			
			String sqlSobrePeso= "SELECT COUNT(descricao_imc) as total FROM aluno WHERE descricao_imc = 'Sobrepeso'AND nome_cidade='"+nomeDaCidade+"' AND nome_escola ='"+nomeDaEscola+"'AND serie ="+serie+";";
			
			try {
				ps = conexao.prepareStatement(sqlSobrePeso);
				resultado = ps.executeQuery(); 
				
				while(resultado.next()){
					dados.setExcessoPeso(Integer.parseInt(resultado.getString("total")));
				}
											
			} catch (SQLException e) {
				System.out.println("Erro Classe Banco metodo buscarDados em sqlSobrePeso , erro : "+e);
			}
			
			
			String sqlObesidade= "SELECT COUNT(descricao_imc) as total FROM aluno WHERE descricao_imc = 'obesidade'AND nome_cidade='"+nomeDaCidade+"' AND nome_escola ='"+nomeDaEscola+"'AND serie ="+serie+";";
			
			try {
				ps = conexao.prepareStatement(sqlObesidade);
				resultado = ps.executeQuery(); 
				
				while(resultado.next()){
					dados.setObesidade(Integer.parseInt(resultado.getString("total")));
				}
											
			} catch (SQLException e) {
				System.out.println("Erro Classe Banco metodo buscarDados em sqlObesidade, erro : "+e);
			}
			
	}

	public int buscarIdEscola(String escola) {
		sql = "SELECT id_escola FROM escola WHERE nome_escola='"+escola+"';";
		int ret=0;
		try {
			ps = conexao.prepareStatement(sql);
			resultado = ps.executeQuery();

			while(resultado.next()){
				ret =resultado.getInt("id_escola");
			}

			
		} catch (SQLException e) {
			System.out.println("Erro Classe Banco metodo buscarEscolas, erro : "+e);
		}
		return ret;
	}

	public String calcularIdade(String nascimento){
		String dataNascimento = null;
		sql = "select converter_data('"+nascimento+"');";
		
		String idade=null;
		
		try {//conversor  de data do tido datField para text(date) do banco
			ps = conexao.prepareStatement(sql);
			resultado = ps.executeQuery();

			while(resultado.next()){
				dataNascimento =resultado.getString("converter_data");
			}

			
		} catch (SQLException e) {
			System.out.println("Erro Classe Banco metodo calcularIdade sql 1, erro : "+e);
		}
		
		
		try {//conversor de data do tipo ano/mes/dia para dia/mes/ano 
			
			sql = "select extract(year FROM age(current_date, to_date((SELECT to_char(DATE '"+dataNascimento+"', 'DD/MM/YYYY')), 'DD/MM/YYYY')));";
			
			ps = conexao.prepareStatement(sql);
			resultado = ps.executeQuery();
			//talvez tenha que trocar a idade de int para double
			while(resultado.next()){
				idade =resultado.getString("date_part");
			}

			
		} catch (SQLException e) {
			System.out.println("Erro Classe Banco metodo calcularIdade sql 2, erro : "+e);
		}

		return idade;
	}


	public  void salvarImcMasc(Imc imc) {
		
		String sql = "INSERT INTO imc_masculino(idade_imc_masc,baixo_peso,normal,excesso_peso,obesidade)"
						+ "Values ("+imc.getIdade()+","+imc.getBaixoPeso()+","+imc.getNormal()+","+imc.getExcessoPeso()+");";
				
		try {
			ps = conexao.prepareStatement(sql);
			ps.executeQuery();

			
		} catch (SQLException e) {
			System.out.println("Erro Classe Banco metodo SalvarImcMasc, erro : "+e);
		}
		
	}

	
	public  void salvarImcFem(Imc imc) {
		
		String sql = "INSERT INTO imc_femonino(idade_imc_masc,baixo_peso,normal,excesso_peso,obesidade)"
				+ "Values ("+imc.getIdade()+","+imc.getBaixoPeso()+","+imc.getNormal()+","+imc.getExcessoPeso()+");";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.executeQuery();
			
			
		} catch (SQLException e) {
			System.out.println("Erro Classe Banco metodo SalvarImcFem, erro : "+e);
		}
		
	}
	
	public void adicionarAdm(String nome, String login, String senha) {
			
			sql = "INSERT INTO Administrador (id_adm,nome_admn,login_adm,senha_adm) VALUES(default,'"+nome+"','"+login+"',MD5('"+senha+"'));";

		try{
			ps =conexao.prepareStatement(sql);
			ps.execute();
	
		}catch (Exception e){
			System.out.println("erro na classe Banco metodo AdmRoot = "+e);
		}
		
	}

	
	public void buscarTodosImcMasc(List<Imc> listaImc) {
		sql = "SELECT * FROM imc_masculino ";

		try {
			ps = conexao.prepareStatement(sql);
			resultado = ps.executeQuery();

			while(resultado.next()){
				Imc imc = new Imc();
				imc.setIdade(resultado.getInt("idade_imc_masc"));
				imc.setBaixoPeso(resultado.getDouble("baixo_peso"));
				imc.setNormal(resultado.getDouble("normal"));
				imc.setExcessoPeso(resultado.getDouble("excesso_peso"));
				imc.setExcessoPeso(resultado.getDouble("obesidade"));
				
				 listaImc.add(imc);
			}

			
		} catch (SQLException e) {
			System.out.println("Erro Classe Banco metodo buscarEscolas, erro : "+e);
		}
		
		
	}

	
	public void cadastrarSerieTurmaEscola( char turma, int serie,String escola, String ensino) {
		sql = "SELECT cadastrar_serie_turma_escola('"+turma+"',"+serie+",'"+escola+"','"+ensino+"');"; 
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.execute(); 
		}catch(Exception e){
			System.out.println("Erro na classe Banco metodo cadastrarSerieTumaEscola"+e);
		}
		
	}


	public int buscarIdSerie(Serie serie) {
		sql = "SELECT id_serie FROM serie WHERE serie="+serie.getSerie()+" AND ensino = '"+serie.getEnsino()+"';";
		int id=0;
		try {
			ps = conexao.prepareStatement(sql);
			resultado = ps.executeQuery();

			while(resultado.next()){
				id =resultado.getInt("id_serie");
			}

			
		} catch (SQLException e) {
			System.out.println("Erro Classe Banco metodo buscarIdSerie, erro : "+e);
		}


		return id;
	}

	public char buscarIdTurma(char turma) {
		sql = "SELECT turma FROM turma WHERE turma='"+turma+"';";
		char id = 0;
		try {
			ps = conexao.prepareStatement(sql);
			resultado = ps.executeQuery();

			while(resultado.next()){
				id =(resultado.getString("turma")).charAt(0);
			}

			
		} catch (SQLException e) {
			System.out.println("Erro Classe Banco metodo buscarIdTurma, erro : "+e);
		}


		return id;
	}

	
	public void cadastrarSerieTurma(char turma, int serie) {
		sql = "SELECT cadastrar_serie_turma VALUES("+turma+","+serie+");"; 
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.executeQuery(); 
		}catch(Exception e){
			System.out.println("Erro na classe Banco metodo cadastrarSerieTuma"+e);
		}

		
	}

	
	public void salvarSerie(Serie serie) {
		sql = "INSERT INTO serie VALUES (default,"+serie.getSerie()+",'"+serie.getEnsino()+"' )";
		
		try {
			ps = conexao.prepareStatement(sql);
			ps.execute(); 
		}catch(Exception e){
			System.out.println("Erro na classe Banco metodo salvarSerie "+e);
		}
		
		
	}


	public void editarSerie(Serie serie) {
		sql = "UPDATE serie SET serie ="+serie.getSerie()+",ensino = '"+serie.getEnsino()+"'  WHERE id_serie = "+serie.getId()+";";

		try{
			ps =conexao.prepareStatement(sql);
			ps.execute();
	
		}catch (Exception e){
			System.out.println("erro na classe Banco metodo AdmRoot = "+e);
		}
	
		
	}

	
	public void buscarSeries(Serie serie, ComboBox cbSerie) {
		sql = "SELECT serie FROM serie WHERE ensino = '"+serie.getEnsino()+"';";

		try {
			ps = conexao.prepareStatement(sql);
			resultado = ps.executeQuery();

			while(resultado.next()){
				cbSerie.addItem(resultado.getInt("serie"));
			}

			
		} catch (SQLException e) {
			System.out.println("Erro Classe Banco metodo buscarSerie, erro : "+e);
		}

	}

	public void excluirSerie(Serie serie) {
		// TODO Auto-generated method stub
		
	}

	
	


	


	
}