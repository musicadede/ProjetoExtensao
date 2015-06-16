package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.projetoextensao.ConexaoFactory;
import com.model.Corrida20Metros;


public class Corrida20MetrosDAO {

	Connection conexao;

	public Corrida20MetrosDAO() {
		conexao = new ConexaoFactory().getConnection();
	}
	

	public void SalvarCorrida20Metros(Corrida20Metros corrida20Metros) {
		boolean idade =false;
		List<Corrida20Metros> listaCorrida20Metros = new ArrayList<Corrida20Metros>();
		if(corrida20Metros.getTipo()=='M'){
			listaCorrida20Metros = buscarTodos('M');
		}
		
		else if(corrida20Metros.getTipo()=='F'){
			listaCorrida20Metros= buscarTodos('F');
		}
		
		for(Corrida20Metros c: listaCorrida20Metros){
			if(corrida20Metros.getIdade()==c.getIdade()){
				idade = true;
			}
		}

		
		if(idade==true){
			editarCorrida20Metros(corrida20Metros);
		}
		
		else if(idade==false){
			salvar(corrida20Metros);
		}
	}

	private void editarCorrida20Metros(Corrida20Metros corrida20Metros) {
		String sql =null;
		
		if(corrida20Metros.getTipo()=='M'){
			sql = "UPDATE corrida_masc SET"
					+ " idade_corrida="+ corrida20Metros.getIdade() + "," 
					+ "fraco= " + corrida20Metros.getFraco() + "," 
					+ "razoavel="+ corrida20Metros.getRazoavel()+","
					+ "bom ="+corrida20Metros.getBom()+","
					+"muito_bom = "+ corrida20Metros.getMuitoBom()+","
					+"excelente  = "+ corrida20Metros.getExcelente()
					+ " WHERE idade_corrida= "+ corrida20Metros.getIdade() + ";";
			
			
		}
		else if(corrida20Metros.getTipo()=='F'){
			sql = "UPDATE corrida_fem SET"
					+ " idade_corrida="+ corrida20Metros.getIdade() + "," 
					+ "fraco= " + corrida20Metros.getFraco() + "," 
					+ "razoavel="+ corrida20Metros.getRazoavel()+","
					+ "bom ="+corrida20Metros.getBom()+","
					+"muito_bom = "+ corrida20Metros.getMuitoBom()+","
					+"excelente  = "+ corrida20Metros.getExcelente()
					+ " WHERE idade_corrida= "+ corrida20Metros.getIdade() + ";";
	
			
		}

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.execute();

		} catch (Exception e) {
			System.out.println("erro na classe Corrida20MetrosDAO metodo editarCorrida20Metros= " + e);
		}

		
	}


	public List<Corrida20Metros> buscarTodos(char tabelaSexo) {
		String sql = null;
		List<Corrida20Metros> listaCorrida20Metros= new ArrayList<Corrida20Metros>();
		if(tabelaSexo=='M'){
			sql = "SELECT * FROM corrida_masc order by idade_corrida asc;";
			
		}
		
		else if(tabelaSexo=='F'){
			sql = "SELECT * FROM corrida_fem order by idade_corrida asc;";
		}

			try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet resultado = ps.executeQuery();

			while (resultado.next()) {
				Corrida20Metros corrida20Metros = new Corrida20Metros();
				corrida20Metros.setIdade(resultado.getInt("idade_corrida"));
				corrida20Metros.setFraco(resultado.getDouble("fraco"));
				corrida20Metros.setRazoavel(resultado.getDouble("razoavel"));
				corrida20Metros.setBom(resultado.getDouble("bom"));
				corrida20Metros.setMuitoBom(resultado.getDouble("muito_bom"));
				corrida20Metros.setExcelente(resultado.getDouble("excelente"));
				listaCorrida20Metros.add(corrida20Metros);
			}

		} catch (SQLException e) {
			System.out
					.println("Erro Classe Corrida20MetrosDAO metodo buscartodos(), erro : "
							+ e);
		}
		
		
		return listaCorrida20Metros;
	}
	
	public void salvar(Corrida20Metros corrida20Metros ) {
		String sql = null;
		
		if(corrida20Metros .getTipo()=='M'){
			sql = "INSERT INTO corrida_masc (idade_corrida,fraco,razoavel,bom,muito_bom,excelente)"
					+ "VALUES ("
					+ corrida20Metros.getIdade() + "," 
					+ corrida20Metros.getFraco() + "," 
					+ corrida20Metros.getRazoavel()+","
					+corrida20Metros.getBom()+","
					+corrida20Metros.getMuitoBom()+","
					+ corrida20Metros.getExcelente()
					+");";
			
		}
		
		else if(corrida20Metros.getTipo()=='F'){
			sql = "INSERT INTO corrida_fem (idade_corrida, fraco,razoavel,bom,muito_bom,excelente)"
					+ "VALUES ("
					+ corrida20Metros.getIdade() + "," 
					+ corrida20Metros.getFraco() + "," 
					+ corrida20Metros.getRazoavel()+","
					+corrida20Metros.getBom()+","
					+corrida20Metros.getMuitoBom()+","
					+ corrida20Metros.getExcelente()
					+");";
			
		}
		

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out
					.println("Erro Classe Corrida20MetrosDAo metodo salvar(), erro : "
							+ e);
		}

	}


	public void excluirCorrida20Metros(Corrida20Metros corrida20Metros) {
String sql = null;
		if (corrida20Metros.getTipo() == 'M') {
			sql = "DELETE FROM corrida_masc WHERE idade_corrida ="+ corrida20Metros.getIdade()+";";
		} 
		
		else if (corrida20Metros.getTipo() == 'F') {
			sql = "DELETE FROM corrida_fem WHERE idade_corrida="+ corrida20Metros.getIdade()+";";
		}

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out
					.println("Erro Classe excluirCorrida20Metros metodo excluirCorrida20Metros(), erro : "
							+ e);
		}
		
	}
	
}

