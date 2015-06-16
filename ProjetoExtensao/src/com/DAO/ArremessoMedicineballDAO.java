package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.projetoextensao.ConexaoFactory;
import com.model.ArremessoMedicineball;


public class ArremessoMedicineballDAO {

	Connection conexao;

	public ArremessoMedicineballDAO() {
		conexao = new ConexaoFactory().getConnection();
	}
	

	public void salvarArremessoMedicineball(ArremessoMedicineball arMedicineball) {
		boolean idade =false;
		List<ArremessoMedicineball> listaArMedicineball= new ArrayList<ArremessoMedicineball>();
		if(arMedicineball.getTipo()=='M'){
			listaArMedicineball = buscarTodos('M');
		}
		
		else if(arMedicineball.getTipo()=='F'){
			listaArMedicineball= buscarTodos('F');
		}
		
		for(ArremessoMedicineball c: listaArMedicineball){
			if(arMedicineball.getIdade()==c.getIdade()){
				idade = true;
			}
		}

		
		if(idade==true){
			editarArremessoMedicineball(arMedicineball);
		}
		
		else if(idade==false){
			salvar(arMedicineball);
		}
	}

	private void editarArremessoMedicineball(ArremessoMedicineball arMedicineball) {
		String sql =null;
		
		if(arMedicineball.getTipo()=='M'){
			sql = "UPDATE amedicineball_masc SET"
					+ " idade_amedicineball="+ arMedicineball.getIdade() + "," 
					+ "muito_fraco= "+ arMedicineball.getMuitoFraco()+ "," 
					+ "fraco= " + arMedicineball.getFraco() + "," 
					+ "razoavel="+ arMedicineball.getRazoavel()+","
					+ "bom ="+arMedicineball.getBom()+","
					+"muito_bom = "+ arMedicineball.getMuitoBom()
					+ " WHERE idade_amedicineball= "+ arMedicineball.getIdade() + ";";
			
			
		}
		else if(arMedicineball.getTipo()=='F'){
			sql = "UPDATE amedicineball_fem SET"
					+ " idade_amedicineball="+ arMedicineball.getIdade() + "," 
					+ "muito_fraco= "+ arMedicineball.getMuitoFraco()+ "," 
					+ "fraco= " + arMedicineball.getFraco() + "," 
					+ "razoavel="+ arMedicineball.getRazoavel()+","
					+ "bom ="+arMedicineball.getBom()+","
					+"muito_bom = "+ arMedicineball.getMuitoBom()
					+ " WHERE idade_amedicineball= "+ arMedicineball.getIdade() + ";";

			
		}

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.execute();

		} catch (Exception e) {
			System.out.println("erro na classe arMedicineballDAO metodo editararMedicineball()= " + e);
		}

		
	}


	public List<ArremessoMedicineball> buscarTodos(char tabelaSexo) {
		String sql = null;
		List<ArremessoMedicineball> listaArremessoMedicineball= new ArrayList<ArremessoMedicineball>();
		if(tabelaSexo=='M'){
			sql = "SELECT * FROM amedicineball_masc order by idade_amedicineball asc;";
			
		}
		
		else if(tabelaSexo=='F'){
			sql = "SELECT * FROM amedicineball_fem order by idade_amedicineball asc;";
		}

			try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet resultado = ps.executeQuery();

			while (resultado.next()) {
				ArremessoMedicineball arMedicineball = new ArremessoMedicineball();
				arMedicineball.setIdade(resultado.getInt("idade_amedicineball"));
				arMedicineball.setMuitoFraco(resultado.getInt("muito_fraco"));
				arMedicineball.setFraco(resultado.getInt("fraco"));
				arMedicineball.setRazoavel(resultado.getInt("razoavel"));
				arMedicineball.setBom(resultado.getInt("bom"));
				arMedicineball.setMuitoBom(resultado.getInt("muito_bom"));
				listaArremessoMedicineball.add(arMedicineball);
			}

		} catch (SQLException e) {
			System.out
					.println("Erro Classe ArremessoMedicineballDAO metodo buscartodos(), erro : "
							+ e);
		}
		
		
		return listaArremessoMedicineball;
	}
	
	public void salvar(ArremessoMedicineball arMedicineball) {
		String sql = null;
		
		if(arMedicineball.getTipo()=='M'){
			sql = "INSERT INTO amedicineball_masc (idade_amedicineball, muito_fraco, fraco,razoavel,bom,muito_bom)"
					+ "VALUES ("
					+ arMedicineball.getIdade() + "," 
					+ arMedicineball.getMuitoFraco()+ "," 
					+ arMedicineball.getFraco() + "," 
					+ arMedicineball.getRazoavel()+","
					+arMedicineball.getBom()+","
					+ arMedicineball.getMuitoBom()
					+");";
			
		}
		
		else if(arMedicineball.getTipo()=='F'){
			sql = "INSERT INTO amedicineball_fem (idade_amedicineball, muito_fraco, fraco,razoavel,bom,muito_bom)"
					+ "VALUES ("
					+ arMedicineball.getIdade() + "," 
					+ arMedicineball.getMuitoFraco()+ "," 
					+ arMedicineball.getFraco() + "," 
					+ arMedicineball.getRazoavel()+","
					+arMedicineball.getBom()+","
					+ arMedicineball.getMuitoBom()
					+");";
			
		}
		

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out
					.println("Erro Classe ArremessoMedicineballDAo metodo salvar(), erro : "
							+ e);
		}

	}


	public void excluirArremessoMedicineball(ArremessoMedicineball arMedicineball) {
String sql = null;
		if (arMedicineball.getTipo() == 'M') {
			sql = "DELETE FROM amedicineball_masc WHERE idade_amedicineball="+ arMedicineball.getIdade()+";";
		} 
		
		else if (arMedicineball.getTipo() == 'F') {
			sql = "DELETE FROM amedicineball_fem WHERE idade_amedicineball="+ arMedicineball.getIdade()+";";
		}

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out
					.println("Erro Classe excluirArremessoMedicineball metodo excluirArremessoMedicineball(), erro : "
							+ e);
		}
		
	}

}
