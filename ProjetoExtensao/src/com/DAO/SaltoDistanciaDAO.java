package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.projetoextensao.ConexaoFactory;
import com.model.SaltoDistancia;

public class SaltoDistanciaDAO {


	Connection conexao;

	public SaltoDistanciaDAO() {
		conexao = new ConexaoFactory().getConnection();
	}
	

	public void SalvarSaltoDistancia(SaltoDistancia saltoDistancia) {
		boolean idade =false;
		List<SaltoDistancia> listaSaltoDistancia = new ArrayList<SaltoDistancia>();
		if(saltoDistancia.getTipo()=='M'){
			listaSaltoDistancia= buscarTodos('M');
		}
		
		else if(saltoDistancia.getTipo()=='F'){
			listaSaltoDistancia= buscarTodos('F');
		}
		
		for(SaltoDistancia c: listaSaltoDistancia){
			if(saltoDistancia.getIdade()==c.getIdade()){
				idade = true;
			}
		}

		
		if(idade==true){
			editarSaltoDistancia(saltoDistancia);
		}
		
		else if(idade==false){
			salvar(saltoDistancia);
		}
	}

	private void editarSaltoDistancia(SaltoDistancia saltoDistancia) {
		String sql =null;
		
		if(saltoDistancia.getTipo()=='M'){
			sql = "UPDATE salto_dist_masc SET"
					+ " idade_salto_dist="+ saltoDistancia.getIdade() + "," 
					+ "fraco= " + saltoDistancia.getFraco() + "," 
					+ "razoavel="+ saltoDistancia.getRazoavel()+","
					+ "bom ="+saltoDistancia.getBom()+","
					+"muito_bom = "+ saltoDistancia.getMuitoBom()
					+ " WHERE idade_salto_dist= "+ saltoDistancia.getIdade() + ";";
			
			
		}
		else if(saltoDistancia.getTipo()=='F'){
			sql = "UPDATE salto_dist_fem SET"
					+ " idade_salto_dist="+ saltoDistancia.getIdade() + "," 
					+ "fraco= " + saltoDistancia.getFraco() + "," 
					+ "razoavel="+ saltoDistancia.getRazoavel()+","
					+ "bom ="+saltoDistancia.getBom()+","
					+"muito_bom = "+ saltoDistancia.getMuitoBom()
					+ " WHERE idade_salto_dist= "+ saltoDistancia.getIdade() + ";";	
			
		}

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.execute();

		} catch (Exception e) {
			System.out.println("erro na classe SaltoDistanciaDAO metodo editarSeisMinutos= " + e);
		}

		
	}


	public List<SaltoDistancia> buscarTodos(char tabelaSexo) {
		String sql = null;
		List<SaltoDistancia> listaSaltoDistancia= new ArrayList<SaltoDistancia>();
		if(tabelaSexo=='M'){
			sql = "SELECT * FROM salto_dist_masc order by idade_salto_dist asc;";
			
		}
		
		else if(tabelaSexo=='F'){
			sql = "SELECT * FROM salto_dist_fem order by idade_salto_dist asc;";
		}

			try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet resultado = ps.executeQuery();

			while (resultado.next()) {
				SaltoDistancia saltoDistancia= new SaltoDistancia();
				saltoDistancia.setIdade(resultado.getInt("idade_salto_dist"));
				saltoDistancia.setFraco(resultado.getInt("fraco"));
				saltoDistancia.setRazoavel(resultado.getInt("razoavel"));
				saltoDistancia.setBom(resultado.getInt("bom"));
				saltoDistancia.setMuitoBom(resultado.getInt("muito_bom"));
				listaSaltoDistancia.add(saltoDistancia);
			}

		} catch (SQLException e) {
			System.out
					.println("Erro Classe saltoDistanciaDAO metodo buscartodos(), erro : "
							+ e);
		}
		
		
		return listaSaltoDistancia;
	}
	
	public void salvar(SaltoDistancia saltoDistancia ) {
		String sql = null;
		
		if(saltoDistancia .getTipo()=='M'){
			sql = "INSERT INTO salto_dist_masc (idade_salto_dist,fraco,razoavel,bom,muito_bom)"
					+ "VALUES ("
					+ saltoDistancia.getIdade() + "," 
					+ saltoDistancia.getFraco() + "," 
					+ saltoDistancia.getRazoavel()+","
					+saltoDistancia.getBom()+","
					+saltoDistancia.getMuitoBom()
					+");";
			
		}
		
		else if(saltoDistancia.getTipo()=='F'){
			sql = "INSERT INTO salto_dist_fem (idade_salto_dist, fraco,razoavel,bom,muito_bom)"
					+ "VALUES ("
					+ saltoDistancia.getIdade() + "," 
					+ saltoDistancia.getFraco() + "," 
					+ saltoDistancia.getRazoavel()+","
					+saltoDistancia.getBom()+","
					+saltoDistancia.getMuitoBom()
					+");";
			
		}
		

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out
					.println("Erro Classe SaltoDistanciaDAo metodo salvar(), erro : "
							+ e);
		}

	}


	public void excluirSaltoDistancia(SaltoDistancia saltoDistancia) {
		String sql = null;
		if (saltoDistancia.getTipo() == 'M') {
			sql = "DELETE FROM salto_dist_masc WHERE idade_salto_dist ="+ saltoDistancia.getIdade()+";";
		} 
		
		else if (saltoDistancia.getTipo() == 'F') {
			sql = "DELETE FROM salto_dist_fem WHERE idade_salto_dist="+ saltoDistancia.getIdade()+";";
		}

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out
					.println("Erro Classe excluirSaltoDistanciametodo excluirSaltoDistancia(), erro : "
							+ e);
		}
		
	}
	
}