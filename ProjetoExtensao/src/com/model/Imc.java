package com.model;

import com.example.projetoextensao.Banco;

public class Imc {

	private char tipo;
	private int idade;
	private double baixoPeso;
	private double normal;
	private double excessoPeso;
	
	public Imc() {
		// TODO Auto-generated constructor stub
	}
	
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}
	
	public char getTipo() {
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
	
	
	
}
