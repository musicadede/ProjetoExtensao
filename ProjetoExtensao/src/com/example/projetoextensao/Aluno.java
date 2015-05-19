package com.example.projetoextensao;

public class Aluno {
	String nome;
	int idade;
	char sexo;
	String cidade;
	String escola;
	String ensino;
	int serie;
	char turma;
	int telefone;
//	String endereco;
	Double peso;
	Double altura;
	
	public Aluno(String nome,String escola,String ensino,int serie,char turma,Double peso, Double altura ){
		setNome(nome);
		setEscola(escola);
		setEnsino(ensino);
		setSerie(serie);
		setTurma(turma);
		setPeso(peso);
		setAltura(altura);
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setEscola(String escola2) {
		this.escola = escola2;
	}
	
	public String getEscola() {
		return escola;
	}
	
	public String getEnsino() {
		return ensino;
	}

	public void setEnsino(String ensino) {
		this.ensino = ensino;
	}
	
	public void setSerie(int serie) {
		this.serie = serie;
	}
	
	public int getSerie() {
		return serie;
	}
	
	public void setTurma(char turma) {
		this.turma = turma;
	}
	
	public char getTurma() {
		return turma;
	}
	
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	
	public Double getPeso() {
		return peso;
	}
	
	public void setAltura(Double altura) {
		this.altura = altura;
	}
	
	public Double getAltura() {
		return altura;
	}
}