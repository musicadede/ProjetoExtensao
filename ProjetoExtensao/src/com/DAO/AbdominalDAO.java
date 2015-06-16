package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.projetoextensao.ConexaoFactory;
import com.model.Abdominal;


public class AbdominalDAO {
	
	Connection conexao;

	public AbdominalDAO() {
		conexao = new ConexaoFactory().getConnection();
	}
	

	public void SalvarAbdominal(Abdominal abdominal) {
		boolean idade =false;
		List<Abdominal> listaAbdominal = new ArrayList<Abdominal>();
		if(abdominal.getTipo()=='M'){
			listaAbdominal = buscarTodos('M');
		}
		
		else if(abdominal.getTipo()=='F'){
			listaAbdominal= buscarTodos('F');
		}
		
		for(Abdominal c: listaAbdominal){
			if(abdominal.getIdade()==c.getIdade()){
				idade = true;
			}
		}

		
		if(idade==true){
			editarAbdominal(abdominal);
		}
		
		else if(idade==false){
			salvar(abdominal);
		}
	}

	private void editarAbdominal(Abdominal abdominal) {
		String sql =null;
		
		if(abdominal.getTipo()=='M'){
			sql = "UPDATE abdominal_masc SET"
					+ " idade_abdom="+ abdominal.getIdade() + "," 
					+ "muito_fraco= "+ abdominal.getMuitoFraco()+ "," 
					+ "fraco= " + abdominal.getFraco() + "," 
					+ "razoavel="+ abdominal.getRazoavel()+","
					+ "bom ="+abdominal.getBom()+","
					+"muito_bom = "+ abdominal.getMuitoBom()
					+ " WHERE idade_abdom = "+ abdominal.getIdade() + ";";
			
			
		}
		else if(abdominal.getTipo()=='F'){
			sql = "UPDATE flexibilidade_fem SET"
					+ " idade_abdom="+ abdominal.getIdade() + "," 
					+ "muito_fraco= "+ abdominal.getMuitoFraco()+ "," 
					+ "fraco= " + abdominal.getFraco() + "," 
					+ "razoavel="+ abdominal.getRazoavel()+","
					+ "bom ="+abdominal.getBom()+","
					+"muito_bom = "+ abdominal.getMuitoBom()
					+ " WHERE idade_abdom = "+ abdominal.getIdade() + ";";

			
		}

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.execute();

		} catch (Exception e) {
			System.out.println("erro na classe AbdominalDAO metodo editarAbdominal= " + e);
		}

		
	}


	public List<Abdominal> buscarTodos(char tabelaSexo) {
		String sql = null;
		List<Abdominal> listaAbdominal= new ArrayList<Abdominal>();
		if(tabelaSexo=='M'){
			sql = "SELECT * FROM abdominal_masc order by idade_abdom asc;";
			
		}
		
		else if(tabelaSexo=='F'){
			sql = "SELECT * FROM abdominal_fem order by idade_abdom asc;";
		}

			try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet resultado = ps.executeQuery();

			while (resultado.next()) {
				Abdominal abdominal = new Abdominal();
				abdominal.setIdade(resultado.getInt("idade_abdom"));
				abdominal.setMuitoFraco(resultado.getInt("muito_fraco"));
				abdominal.setFraco(resultado.getInt("fraco"));
				abdominal.setRazoavel(resultado.getInt("razoavel"));
				abdominal.setBom(resultado.getInt("bom"));
				abdominal.setMuitoBom(resultado.getInt("muito_bom"));
				listaAbdominal.add(abdominal);
			}

		} catch (SQLException e) {
			System.out
					.println("Erro Classe AbdominalDAO metodo buscartodos(), erro : "
							+ e);
		}
		
		
		return listaAbdominal;
	}
	
	public void salvar(Abdominal abdominal) {
		String sql = null;
		
		if(abdominal.getTipo()=='M'){
			sql = "INSERT INTO abdominal_masc (idade_abdom, muito_fraco, fraco,razoavel,bom,muito_bom)"
					+ "VALUES ("
					+ abdominal.getIdade() + "," 
					+ abdominal.getMuitoFraco()+ "," 
					+ abdominal.getFraco() + "," 
					+ abdominal.getRazoavel()+","
					+abdominal.getBom()+","
					+ abdominal.getMuitoBom()
					+");";
			
		}
		
		else if(abdominal.getTipo()=='F'){
			sql = "INSERT INTO abdominal_fem (idade_abdom, muito_fraco, fraco,razoavel,bom,muito_bom)"
					+ "VALUES ("
					+ abdominal.getIdade() + "," 
					+ abdominal.getMuitoFraco()+ "," 
					+ abdominal.getFraco() + "," 
					+ abdominal.getRazoavel()+","
					+abdominal.getBom()+","
					+ abdominal.getMuitoBom()
					+");";
			
		}
		

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out
					.println("Erro Classe AbdominalDAo metodo salvarAbdominal(), erro : "
							+ e);
		}

	}


	public void excluirAbdominal(Abdominal abdominal) {
String sql = null;
		if (abdominal.getTipo() == 'M') {
			sql = "DELETE FROM abdominal_masc WHERE idade_abdom ="+ abdominal.getIdade()+";";
		} 
		
		else if (abdominal.getTipo() == 'F') {
			sql = "DELETE FROM abdominal_fem WHERE idade_abdom="+ abdominal.getIdade()+";";
		}

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out
					.println("Erro Classe excluirAbdominal metodo excluirAbdominal(), erro : "
							+ e);
		}
		
	}

}
