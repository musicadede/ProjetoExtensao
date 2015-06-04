package com.model;

public class DadosRelatorio {
	private int idCidade;
	private String nomeCidade;
	private int idEscola;
	private String nomeEscola;
	private int serie;
	private int baixoPeso;
	private int normal;
	private int excessopeso;
	private int obesidade;

	public DadosRelatorio() {

	}

	public int getNormal() {
		return normal;
	}

	public void setNormal(int normal) {
		this.normal = normal;
	}

	public int getBaixoPeso() {
		return baixoPeso;
	}

	public int getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(int idCidade) {
		this.idCidade = idCidade;
	}

	public int getIdEscola() {
		return idEscola;
	}

	public void setIdEscola(int idEscola) {
		this.idEscola = idEscola;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCcidade) {
		this.nomeCidade = nomeCcidade;
	}

	public String getNomeEscola() {
		return nomeEscola;
	}

	public void setNomeEscola(String nomeEscola) {
		this.nomeEscola = nomeEscola;
	}

	public int getSerie() {
		return serie;
	}

	public void setSerie(int serie) {
		this.serie = serie;
	}

	public void setBaixoPeso(int baixoPeso) {
		this.baixoPeso = baixoPeso;
	}

	public int getExcessoPeso() {
		return excessopeso;
	}

	public void setExcessoPeso(int excessopeso) {
		this.excessopeso = excessopeso;
	}

	public int getObesidade() {
		return obesidade;
	}

	public void setObesidade(int obesidade) {
		this.obesidade = obesidade;
	}

}