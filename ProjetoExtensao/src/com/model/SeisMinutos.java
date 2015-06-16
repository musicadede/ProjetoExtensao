package com.model;

public class SeisMinutos {
	
	private int idade;
	private int fraco;
	private int razoavel;
	private int bom;
	private int muitoBom;
	private int excelente;
	public char Tipo;
	
	public SeisMinutos() {
		// TODO Auto-generated constructor stub
	}

	public char getTipo() {
		return Tipo;
	}

	public void setTipo(char tipo) {
		Tipo = tipo;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public int getFraco() {
		return fraco;
	}

	public void setFraco(int fraco) {
		this.fraco = fraco;
	}

	public int getRazoavel() {
		return razoavel;
	}

	public void setRazoavel(int razoavel) {
		this.razoavel = razoavel;
	}

	public int getBom() {
		return bom;
	}

	public void setBom(int bom) {
		this.bom = bom;
	}

	public int getMuitoBom() {
		return muitoBom;
	}

	public void setMuitoBom(int muitoBom) {
		this.muitoBom = muitoBom;
	}

	public int getExcelente() {
		return excelente;
	}

	public void setExcelente(int excelente) {
		this.excelente = excelente;
	}
	
	
	
}
