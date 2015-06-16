package com.model;

public class Corrida20Metros {

	private int idade;
	private double Fraco;
	private double razoavel;
	private double bom;
	private double muitoBom;
	private double excelente;
	public char Tipo;
	public Corrida20Metros() {
		// TODO Auto-generated constructor stub
	}
	public void setTipo(char tipo) {
		Tipo = tipo;
	}
	
	public char getTipo() {
		return Tipo;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public double getFraco() {
		return Fraco;
	}

	public void setFraco(double fraco) {
		Fraco = fraco;
	}

	public double getRazoavel() {
		return razoavel;
	}

	public void setRazoavel(double razoavel) {
		this.razoavel = razoavel;
	}

	public double getBom() {
		return bom;
	}

	public void setBom(double bom) {
		this.bom = bom;
	}

	public double getMuitoBom() {
		return muitoBom;
	}

	public void setMuitoBom(double muitoBom) {
		this.muitoBom = muitoBom;
	}
	
	public void setExcelente(double excelente) {
		this.excelente = excelente;
	}
	public double getExcelente() {
		return excelente;
	}
}
