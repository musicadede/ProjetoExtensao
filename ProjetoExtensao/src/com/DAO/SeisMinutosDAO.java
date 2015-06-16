package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.projetoextensao.ConexaoFactory;
import com.model.SeisMinutos;

public class SeisMinutosDAO {

	Connection conexao;

	public SeisMinutosDAO() {
		conexao = new ConexaoFactory().getConnection();
	}
	

	public void SalvarSeisMinutos(SeisMinutos seisMinutos) {
		boolean idade =false;
		List<SeisMinutos> listaSeisMinutos = new ArrayList<SeisMinutos>();
		if(seisMinutos.getTipo()=='M'){
			listaSeisMinutos= buscarTodos('M');
		}
		
		else if(seisMinutos.getTipo()=='F'){
			listaSeisMinutos= buscarTodos('F');
		}
		
		for(SeisMinutos c: listaSeisMinutos){
			if(seisMinutos.getIdade()==c.getIdade()){
				idade = true;
			}
		}

		
		if(idade==true){
			editarSeisMinutos(seisMinutos);
		}
		
		else if(idade==false){
			salvar(seisMinutos);
		}
	}

	private void editarSeisMinutos(SeisMinutos seisMinutos) {
		String sql =null;
		
		if(seisMinutos.getTipo()=='M'){
			sql = "UPDATE seisminutos_masc SET"
					+ " idade_seismin="+ seisMinutos.getIdade() + "," 
					+ "fraco= " + seisMinutos.getFraco() + "," 
					+ "razoavel="+ seisMinutos.getRazoavel()+","
					+ "bom ="+seisMinutos.getBom()+","
					+"muito_bom = "+ seisMinutos.getMuitoBom()
					+ " WHERE idade_seismin= "+ seisMinutos.getIdade() + ";";
			
			
		}
		else if(seisMinutos.getTipo()=='F'){
			sql = "UPDATE seisminutos_fem SET"
					+ " idade_seismin="+ seisMinutos.getIdade() + "," 
					+ "fraco= " + seisMinutos.getFraco() + "," 
					+ "razoavel="+ seisMinutos.getRazoavel()+","
					+ "bom ="+seisMinutos.getBom()+","
					+"muito_bom = "+ seisMinutos.getMuitoBom()
					+ " WHERE idade_seismin= "+ seisMinutos.getIdade() + ";";
			
			
		}

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.execute();

		} catch (Exception e) {
			System.out.println("erro na classe SeisMinutosDAO metodo editarSeisMinutos= " + e);
		}

		
	}


	public List<SeisMinutos> buscarTodos(char tabelaSexo) {
		String sql = null;
		List<SeisMinutos> listaSeisMinutos= new ArrayList<SeisMinutos>();
		if(tabelaSexo=='M'){
			sql = "SELECT * FROM seisminutos_masc order by idade_seismin asc;";
			
		}
		
		else if(tabelaSexo=='F'){
			sql = "SELECT * FROM seisminutos_fem order by idade_seismin asc;";
		}

			try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet resultado = ps.executeQuery();

			while (resultado.next()) {
				SeisMinutos seisMinutos= new SeisMinutos();
				seisMinutos.setIdade(resultado.getInt("idade_seismin"));
				seisMinutos.setFraco(resultado.getInt("fraco"));
				seisMinutos.setRazoavel(resultado.getInt("razoavel"));
				seisMinutos.setBom(resultado.getInt("bom"));
				seisMinutos.setMuitoBom(resultado.getInt("muito_bom"));
				listaSeisMinutos.add(seisMinutos);
			}

		} catch (SQLException e) {
			System.out
					.println("Erro Classe SeisMinutosDAO metodo buscartodos(), erro : "
							+ e);
		}
		
		
		return listaSeisMinutos;
	}
	
	public void salvar(SeisMinutos seisMinutos ) {
		String sql = null;
		
		if(seisMinutos .getTipo()=='M'){
			sql = "INSERT INTO seisminutos_masc (idade_seismin,fraco,razoavel,bom,muito_bom)"
					+ "VALUES ("
					+ seisMinutos.getIdade() + "," 
					+ seisMinutos.getFraco() + "," 
					+ seisMinutos.getRazoavel()+","
					+seisMinutos.getBom()+","
					+seisMinutos.getMuitoBom()
					+");";
			
		}
		
		else if(seisMinutos.getTipo()=='F'){
			sql = "INSERT INTO seisminutos_fem (idade_seismin, fraco,razoavel,bom,muito_bom)"
					+ "VALUES ("
					+ seisMinutos.getIdade() + "," 
					+ seisMinutos.getFraco() + "," 
					+ seisMinutos.getRazoavel()+","
					+seisMinutos.getBom()+","
					+seisMinutos.getMuitoBom()
					+");";
			
		}
		

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out
					.println("Erro Classe SeisMinutosDAo metodo salvar(), erro : "
							+ e);
		}

	}


	public void excluirSeisMinutos(SeisMinutos seisMinutos) {
		String sql = null;
		if (seisMinutos.getTipo() == 'M') {
			sql = "DELETE FROM seisminutos_masc WHERE idade_seismin ="+ seisMinutos.getIdade()+";";
		} 
		
		else if (seisMinutos.getTipo() == 'F') {
			sql = "DELETE FROM seisminutos_fem WHERE idade_seismin="+ seisMinutos.getIdade()+";";
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

