package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.projetoextensao.ConexaoFactory;
import com.model.Imc;

public class ImcDAO {

	Connection conexao;

	public ImcDAO() {
		conexao = new ConexaoFactory().getConnection();
	}
	
	public void salvarImc(Imc imc){
		boolean idade =false;
		
		List<Imc> listaImcs = new ArrayList<Imc>();
		if(imc.getTipo()=='M'){
			listaImcs = buscarTodos('M');
		}
		
		else if(imc.getTipo()=='F'){
			listaImcs = buscarTodos('F');
		}
		
		for(Imc c: listaImcs){
			if(imc.getIdade()==c.getIdade()){
				idade = true;
			}
		}

		
		if(idade==true){
			editarImc(imc);
		}
		
		else if(idade==false){
			salvar(imc);
		}
	}

	public void editarImc (Imc imc) {
		String sql =null;
		
		if(imc.getTipo()=='M'){
			sql = "UPDATE imc_masculino SET idade_imc="
					+ imc.getIdade() + "," + "baixo_peso = " + imc.getBaixoPeso()
					+ "," + "normal = " + imc.getNormal() + "," + "excesso_peso="
					+ imc.getExcessoPeso()
					+ "  WHERE idade_imc= "
					+ imc.getIdade() + ";";

			
		}
		else if(imc.getTipo()=='F'){
			sql = "UPDATE imc_feminino SET idade_imc="
					+ imc.getIdade() + "," + "baixo_peso = " + imc.getBaixoPeso()
					+ "," + "normal = " + imc.getNormal() + "," + "excesso_peso="
					+ imc.getExcessoPeso() 
					+ "  WHERE idade_imc= "
					+ imc.getIdade() + ";";
			
		}

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.execute();

		} catch (Exception e) {
			System.out.println("erro na classe ImcDAO metodo editarImcFem= " + e);
		}

	}
	
	public void salvar(Imc imc) {
		String sql = null;
		
		if(imc.getTipo()=='M'){
			sql = "INSERT INTO imc_masculino (idade_imc,baixo_peso, normal,excesso_peso)"
					+ "VALUES ("
					+ imc.getIdade()
					+ ","
					+ imc.getBaixoPeso()
					+ ","
					+ imc.getNormal()
					+ ","
					+ imc.getExcessoPeso()+");";
			
		}
		
		else if(imc.getTipo()=='F'){
			sql = "INSERT INTO imc_feminino(idade_imc,baixo_peso, normal,excesso_peso)"
					+ "VALUES ("
					+ imc.getIdade()
					+ ","
					+ imc.getBaixoPeso()
					+ ","
					+ imc.getNormal()
					+ ","
					+ imc.getExcessoPeso()
					+ ");";
			
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

	public List<Integer> buscarIdadeImcMasc() {

		int idade = 0;
		List<Integer> idades = new ArrayList<Integer>();
		String sql = "SELECT idade_imc FROM imc_masculino;";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet resultado = ps.executeQuery();

			while (resultado.next()) {
				idade = resultado.getInt("idade_imc");
				idades.add(idade);
			}

		} catch (SQLException e) {
			System.out
					.println("Erro Classe ImcDAO metodo buscarIdadeImcMasc(), erro : "
							+ e);
		}

		return idades;
	}

	public List<Integer> buscarIdadeImcFem() {

		int idade = 0;
		List<Integer> idades = new ArrayList<Integer>();
		String sql = "SELECT idade_imc FROM imc_feminino;";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet resultado = ps.executeQuery();

			while (resultado.next()) {
				idade = resultado.getInt("idade_imc");
				idades.add(idade);
			}

		} catch (SQLException e) {
			System.out
					.println("Erro Classe ImcDAO metodo buscarIdadeImcFem(), erro : "
							+ e);
		}

		return idades;
	}

	public Imc buscarImc(Imc imc) {
		String sql = null;

		if (imc.getTipo() == 'M') {
			sql = "SELECT * FROM imc_masculino WHERE idade_imc ="
					+ imc.getIdade() + ";";
		} 
		
		else if (imc.getTipo() == 'F') {
			sql = "SELECT * FROM imc_feminino WHERE idade_imc="
					+ imc.getIdade() + ";";
		}

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet resultado = ps.executeQuery();

			while (resultado.next()) {
				imc.setIdade(resultado.getInt("idade_imc"));
				imc.setBaixoPeso(resultado.getDouble("baixo_peso"));
				imc.setNormal(resultado.getDouble("normal"));
				imc.setExcessoPeso(resultado.getDouble("excesso_peso"));
			}

		} catch (SQLException e) {
			System.out.println("Erro Classe ImcDAO metodo buscarImc(), erro : "
					+ e);
		}

		
		return imc;
	}

	public void excluirImc(Imc imc) {
		String sql = null;
		
		if (imc.getTipo() == 'M') {
			sql = "DELETE FROM imc_masculino WHERE idade_imc ="+ imc.getIdade()+";";
		} 
		
		else if (imc.getTipo() == 'F') {
			sql = "DELETE FROM imc_feminino WHERE idade_imc="+ imc.getIdade()+";";
		}

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out
					.println("Erro Classe UsuarioDAo metodo excluirImc(), erro : "
							+ e);
		}

	}

	public List<Imc> buscarTodos(char imcSexo) {
		String sql = null;
		List<Imc> listaImcs= new ArrayList<Imc>();
		if(imcSexo=='M'){
			sql = "SELECT * FROM imc_masculino order by idade_imc asc;";
			
		}
		
		else if(imcSexo=='F'){
			sql = "SELECT * FROM imc_feminino order by idade_imc asc;";
		}

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet resultado = ps.executeQuery();

			while (resultado.next()) {
				Imc imc = new Imc();
				imc.setIdade(resultado.getInt("idade_imc"));
				imc.setBaixoPeso(resultado.getDouble("baixo_peso"));
				imc.setNormal(resultado.getDouble("normal"));
				imc.setExcessoPeso(resultado.getDouble("excesso_peso"));
				listaImcs.add(imc);
			}

		} catch (SQLException e) {
			System.out
					.println("Erro Classe ImcDAO metodo buscartodos(), erro : "
							+ e);
		}

		return listaImcs;
	}

}
