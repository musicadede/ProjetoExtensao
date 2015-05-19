package com.model;

import com.example.projetoextensao.Banco;

public class Imc {

	private String tipo;
	private int idade;
	private double baixoPeso;
	private double normal;
	private double excessoPeso;
	private double obesidade;
	
	public Imc() {
		// TODO Auto-generated constructor stub
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	
	public int getIdade() {
		return idade;
	}
	
	public void setBaixoPeso(double baixoPeso) {
		this.baixoPeso = baixoPeso;
	}
	
	public double getBaixoPeso() {
		return baixoPeso;
	}
	
	public void setNormal(double normal) {
		this.normal = normal;
	}
	
	public double getNormal() {
		return normal;
	}
	
	public void setExcessoPeso(double excessoPeso) {
		this.excessoPeso = excessoPeso;
	}
	
	public double getExcessoPeso() {
		return excessoPeso;
	}
	
	public void setObesidade(double obesidade) {
		this.obesidade = obesidade;
	}
	
	public double getObesidade() {
		return obesidade;
	}
	
	
}
