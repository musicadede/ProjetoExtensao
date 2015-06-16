package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.projetoextensao.ConexaoFactory;
import com.model.Quadrado;

public class QuadradoDAO {

	Connection conexao;

	public QuadradoDAO() {
		conexao = new ConexaoFactory().getConnection();
	}
	

	public void SalvarQuadrado(Quadrado quadrado) {
		boolean idade =false;
		List<Quadrado> listaQuadrado = new ArrayList<Quadrado>();
		if(quadrado.getTipo()=='M'){
			listaQuadrado= buscarTodos('M');
		}
		
		else if(quadrado.getTipo()=='F'){
			listaQuadrado= buscarTodos('F');
		}
		
		for(Quadrado c: listaQuadrado){
			if(quadrado.getIdade()==c.getIdade()){
				idade = true;
			}
		}

		
		if(idade==true){
			editarQuadrado(quadrado);
		}
		
		else if(idade==false){
			salvar(quadrado);
		}
	}

	private void editarQuadrado(Quadrado quadrado) {
		String sql =null;
		
		if(quadrado.getTipo()=='M'){
			sql = "UPDATE quadrado_masc SET"
					+ " idade_quadr="+ quadrado.getIdade() + "," 
					+ "excelente= " + quadrado.getExcelente() + "," 
					+ "muito_bom="+ quadrado.getMuitoBom()+","
					+ "bom ="+quadrado.getBom()+","
					+"razoavel = "+ quadrado.getRazoavel()
					+ " WHERE idade_quadr= "+ quadrado.getIdade() + ";";
			
			
		}
		else if(quadrado.getTipo()=='F'){
			sql = "UPDATE quadrado_fem SET"
					+ " idade_quadr="+ quadrado.getIdade() + "," 
					+ "excelente= " + quadrado.getExcelente() + "," 
					+ "muito_bom="+ quadrado.getMuitoBom()+","
					+ "bom ="+quadrado.getBom()+","
					+"razoavel = "+ quadrado.getRazoavel()
					+ " WHERE idade_quadr= "+ quadrado.getIdade() + ";";
			
			
			
		}

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.execute();

		} catch (Exception e) {
			System.out.println("erro na classe QuadradoDAO metodo editarQuadrado= " + e);
		}

		
	}


	public List<Quadrado> buscarTodos(char tabelaSexo) {
		String sql = null;
		List<Quadrado> listaQuadrado= new ArrayList<Quadrado>();
		if(tabelaSexo=='M'){
			sql = "SELECT * FROM quadrado_masc order by idade_quadr asc;";
			
		}
		
		else if(tabelaSexo=='F'){
			sql = "SELECT * FROM quadrado_fem order by idade_quadr asc;";
		}

			try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet resultado = ps.executeQuery();

			while (resultado.next()) {
				Quadrado quadrado= new Quadrado();
				quadrado.setIdade(resultado.getInt("idade_quadr"));
				quadrado.setExcelente(resultado.getDouble("excelente"));
				quadrado.setMuitoBom(resultado.getDouble("muito_bom"));
				quadrado.setBom(resultado.getDouble("bom"));
				quadrado.setRazoavel(resultado.getDouble("razoavel"));
				listaQuadrado.add(quadrado);
			}

		} catch (SQLException e) {
			System.out
					.println("Erro Classe QuadradoDAO metodo buscartodos(), erro : "
							+ e);
		}
		
		
		return listaQuadrado;
	}
	
	public void salvar(Quadrado quadrado ) {
		String sql = null;
		
		if(quadrado .getTipo()=='M'){
			sql = "INSERT INTO quadrado_masc (idade_quadr,excelente,muito_bom,bom,razoavel)"
					+ "VALUES ("
					+ quadrado.getIdade() + "," 
					+ quadrado.getExcelente() + "," 
					+ quadrado.getMuitoBom()+","
					+quadrado.getBom()+","
					+quadrado.getRazoavel()
					+");";
			
		}
		
		else if(quadrado.getTipo()=='F'){
			sql = "INSERT INTO quadrado_fem (idade_quadr,excelente,muito_bom,bom,razoavel)"
					+ "VALUES ("
					+ quadrado.getIdade() + "," 
					+ quadrado.getExcelente() + "," 
					+ quadrado.getMuitoBom()+","
					+quadrado.getBom()+","
					+quadrado.getRazoavel()
					+");";
			
		}
		

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out
					.println("Erro Classe QuadradoDAo metodo salvar(), erro : "
							+ e);
		}

	}


	public void excluirQuadrado(Quadrado quadrado) {
		String sql = null;
		if (quadrado.getTipo() == 'M') {
			sql = "DELETE FROM quadrado_masc WHERE idade_quadr ="+ quadrado.getIdade()+";";
		} 
		
		else if (quadrado.getTipo() == 'F') {
			sql = "DELETE FROM quadrado_fem WHERE idade_quadr="+ quadrado.getIdade()+";";
		}

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out
					.println("Erro Classe excluirSeisMinutos metodo excluirSeisMinutos(), erro : "
							+ e);
		}
		
	}
	
}