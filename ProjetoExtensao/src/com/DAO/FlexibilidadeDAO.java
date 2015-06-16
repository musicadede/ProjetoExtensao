package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.projetoextensao.ConexaoFactory;
import com.model.Flexibilidade;
import com.model.Imc;

public class FlexibilidadeDAO {
	
	Connection conexao;

	public FlexibilidadeDAO() {
		conexao = new ConexaoFactory().getConnection();
	}
	

	public void SalvarFlexibilidade(Flexibilidade flexibilidade) {
		boolean idade =false;
		List<Flexibilidade> listaFlexibilidade = new ArrayList<Flexibilidade>();
		if(flexibilidade.getTipo()=='M'){
			listaFlexibilidade = buscarTodos('M');
		}
		
		else if(flexibilidade.getTipo()=='F'){
			listaFlexibilidade= buscarTodos('F');
		}
		
		for(Flexibilidade c: listaFlexibilidade){
			if(flexibilidade.getIdade()==c.getIdade()){
				idade = true;
			}
		}

		
		if(idade==true){
			editarFlexibilidade(flexibilidade);
		}
		
		else if(idade==false){
			salvar(flexibilidade);
		}
	}

	private void editarFlexibilidade(Flexibilidade flexibilidade) {
		String sql =null;
		
		if(flexibilidade.getTipo()=='M'){
			sql = "UPDATE flexibilidade_masc SET"
					+ " idade_flex="+ flexibilidade.getIdade() + "," 
					+ "muito_fraco= "+ flexibilidade.getMuitoFraco()+ "," 
					+ "fraco= " + flexibilidade.getFraco() + "," 
					+ "razoavel="+ flexibilidade.getRazoavel()+","
					+ "bom ="+flexibilidade.getBom()+","
					+"muito_bom = "+ flexibilidade.getMuitoBom()
					+ " WHERE idade_flex = "+ flexibilidade.getIdade() + ";";
			
			
		}
		else if(flexibilidade.getTipo()=='F'){
			sql = "UPDATE flexibilidade_fem SET"
					+ " idade_flex="+ flexibilidade.getIdade() + "," 
					+ "muito_fraco= "+ flexibilidade.getMuitoFraco()+ "," 
					+ "fraco= " + flexibilidade.getFraco() + "," 
					+ "razoavel="+ flexibilidade.getRazoavel()+","
					+ "bom ="+flexibilidade.getBom()+","
					+"muito_bom = "+ flexibilidade.getMuitoBom()
					+ " WHERE idade_flex = "+ flexibilidade.getIdade() + ";";

			
		}

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.execute();

		} catch (Exception e) {
			System.out.println("erro na classe FlexibilidadeDAO metodo editarFlexibilidade= " + e);
		}

		
	}


	public List<Flexibilidade> buscarTodos(char tabelaSexo) {
		String sql = null;
		List<Flexibilidade> listaFlexibilidade= new ArrayList<Flexibilidade>();
		if(tabelaSexo=='M'){
			sql = "SELECT * FROM Flexibilidade_masc order by idade_flex asc;";
			
		}
		
		else if(tabelaSexo=='F'){
			sql = "SELECT * FROM Flexibilidade_fem order by idade_flex asc;";
		}

			try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet resultado = ps.executeQuery();

			while (resultado.next()) {
				Flexibilidade flexibilidade = new Flexibilidade();
				flexibilidade.setIdade(resultado.getInt("idade_flex"));
				flexibilidade.setMuitoFraco(resultado.getInt("muito_fraco"));
				flexibilidade.setFraco(resultado.getInt("fraco"));
				flexibilidade.setRazoavel(resultado.getInt("razoavel"));
				flexibilidade.setBom(resultado.getInt("bom"));
				flexibilidade.setMuitoBom(resultado.getInt("muito_bom"));
				listaFlexibilidade.add(flexibilidade);
			}

		} catch (SQLException e) {
			System.out
					.println("Erro Classe FlexibilidadeDAO metodo buscartodos(), erro : "
							+ e);
		}
		
		
		return listaFlexibilidade;
	}
	
	public void salvar(Flexibilidade flexibilidade) {
		String sql = null;
		
		if(flexibilidade.getTipo()=='M'){
			sql = "INSERT INTO flexibilidade_masc (idade_flex, muito_fraco, fraco,razoavel,bom,muito_bom)"
					+ "VALUES ("
					+ flexibilidade.getIdade() + "," 
					+ flexibilidade.getMuitoFraco()+ "," 
					+ flexibilidade.getFraco() + "," 
					+ flexibilidade.getRazoavel()+","
					+flexibilidade.getBom()+","
					+ flexibilidade.getMuitoBom()
					+");";
			
		}
		
		else if(flexibilidade.getTipo()=='F'){
			sql = "INSERT INTO flexibilidade_fem (idade_flex, muito_fraco, fraco,razoavel,bom,muito_bom)"
					+ "VALUES ("
					+ flexibilidade.getIdade() + "," 
					+ flexibilidade.getMuitoFraco()+ "," 
					+ flexibilidade.getFraco() + "," 
					+ flexibilidade.getRazoavel()+","
					+flexibilidade.getBom()+","
					+ flexibilidade.getMuitoBom()
					+");";
			
		}
		

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out
					.println("Erro Classe UsuarioDAo metodo salvarImc(), erro : "
							+ e);
		}

	}


	public void excluirFlexibilidade(Flexibilidade flexibilidade) {
String sql = null;
		if (flexibilidade.getTipo() == 'M') {
			sql = "DELETE FROM flexibilidade_masc WHERE idade_flex ="+ flexibilidade.getIdade()+";";
		} 
		
		else if (flexibilidade.getTipo() == 'F') {
			sql = "DELETE FROM flexibilidade_fem WHERE idade_flex="+ flexibilidade.getIdade()+";";
		}

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out
					.println("Erro Classe excluirFlexibilidade metodo excluirFlexibilidade(), erro : "
							+ e);
		}
		
	}
	
}
