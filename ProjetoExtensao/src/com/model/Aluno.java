package com.model;

import java.sql.Date;

public class Aluno {

	private int id ;
	private  String nome;
	private int idade;
	private char sexo ;
    private  int id_estado;
    private  int id_cidade;
    private int id_cep;
    private int id_bairro;
    private int id_rua;
    private  int id_escola;
    private String ensino;
    private  int serie;
    private  char turma;
    private  String  Turno;

    private  String email;
    private  String nome_mae;
    private  String nome_pai;
    private Date data_avaliacao;
    private  String horario_avaliacao;
    private  String temperatura;
    private  String modalidade_esport;
    private  String frequancia_semanal_esport;
    private  String duracao_sessao;
    private  String tempo_pratica;
    private  String deficiencia;
    private  double massa_corporal;
    private double estatura;
    private int envergadura;
    private double imc;
    private  String descr_imc;
    private int flexibilidade;
    private  String descr_flexibilidade;
    private int abdominal;
    private  String descr_abdonimal;
    private int seis_minutos;
    private  String descr_seis_minutos;
    private int salto_em_distancia;
    private  String descr_salto_distancia;
    private int arremesso_medicineball;
    private  String descr_A_mediciniball;
    private double quadrado;
    private  String descr_quadrado;
    private double corrida_20metros;
    private  String descr_corrida20metros;
	
    
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public int getId_estado() {
		return id_estado;
	}
	public void setId_estado(int id_estado) {
		this.id_estado = id_estado;
	}
	public int getId_cidade() {
		return id_cidade;
	}
	public void setId_cidade(int id_cidade) {
		this.id_cidade = id_cidade;
	}
	public int getId_cep() {
		return id_cep;
	}
	public void setId_cep(int id_cep) {
		this.id_cep = id_cep;
	}
	public int getId_bairro() {
		return id_bairro;
	}
	public void setId_bairro(int id_bairro) {
		this.id_bairro = id_bairro;
	}
	public int getId_rua() {
		return id_rua;
	}
	public void setId_rua(int id_rua) {
		this.id_rua = id_rua;
	}
	public int getId_escola() {
		return id_escola;
	}
	public void setId_escola(int id_escola) {
		this.id_escola = id_escola;
	}
	public String getEnsino() {
		return ensino;
	}
	public void setEnsino(String ensino) {
		this.ensino = ensino;
	}
	public int getSerie() {
		return serie;
	}
	public void setSerie(int serie) {
		this.serie = serie;
	}
	public char getTurma() {
		return turma;
	}
	public void setTurma(char turma) {
		this.turma = turma;
	}
	public String getTurno() {
		return Turno;
	}
	public void setTurno(String turno) {
		Turno = turno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome_mae() {
		return nome_mae;
	}
	public void setNome_mae(String nome_mae) {
		this.nome_mae = nome_mae;
	}
	public String getNome_pai() {
		return nome_pai;
	}
	public void setNome_pai(String nome_pai) {
		this.nome_pai = nome_pai;
	}
	public Date getData_avaliacao() {
		return data_avaliacao;
	}
	public void setData_avaliacao(Date data_avaliacao) {
		this.data_avaliacao = data_avaliacao;
	}
	public String getHorario_avaliacao() {
		return horario_avaliacao;
	}
	public void setHorario_avaliacao(String horario_avaliacao) {
		this.horario_avaliacao = horario_avaliacao;
	}
	public String getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}
	public String getModalidade_esport() {
		return modalidade_esport;
	}
	public void setModalidade_esport(String modalidade_esport) {
		this.modalidade_esport = modalidade_esport;
	}
	public String getFrequancia_semanal_esport() {
		return frequancia_semanal_esport;
	}
	public void setFrequancia_semanal_esport(String frequancia_semanal_esport) {
		this.frequancia_semanal_esport = frequancia_semanal_esport;
	}
	public String getDuracao_sessao() {
		return duracao_sessao;
	}
	public void setDuracao_sessao(String duracao_sessao) {
		this.duracao_sessao = duracao_sessao;
	}
	public String getTempo_pratica() {
		return tempo_pratica;
	}
	public void setTempo_pratica(String tempo_pratica) {
		this.tempo_pratica = tempo_pratica;
	}
	public String getDeficiencia() {
		return deficiencia;
	}
	public void setDeficiencia(String deficiencia) {
		this.deficiencia = deficiencia;
	}
	public double getMassa_corporal() {
		return massa_corporal;
	}
	public void setMassa_corporal(double massa_corporal) {
		this.massa_corporal = massa_corporal;
	}
	public double getEstatura() {
		return estatura;
	}
	public void setEstatura(double estatura) {
		this.estatura = estatura;
	}
	
	public int getEnvergadura() {
		return envergadura;
	}
	public void setEnvergadura(int envergadura) {
		this.envergadura = envergadura;
	}
	public double getImc() {
		return imc;
	}
	public void setImc(double imc) {
		this.imc = imc;
	}
	public String getDescr_imc() {
		return descr_imc;
	}
	public void setDescr_imc(String descr_imc) {
		this.descr_imc = descr_imc;
	}
	public int getFlexibilidade() {
		return flexibilidade;
	}
	public void setFlexibilidade(int flexibilidade) {
		this.flexibilidade = flexibilidade;
	}
	public String getDescr_flexibilidade() {
		return descr_flexibilidade;
	}
	public void setDescr_flexibilidade(String descr_flexibilidade) {
		this.descr_flexibilidade = descr_flexibilidade;
	}
	public int getAbdominal() {
		return abdominal;
	}
	public void setAbdominal(int abdominal) {
		this.abdominal = abdominal;
	}
	public String getDescr_abdonimal() {
		return descr_abdonimal;
	}
	public void setDescr_abdonimal(String descr_abdonimal) {
		this.descr_abdonimal = descr_abdonimal;
	}
	public int getSeis_minutos() {
		return seis_minutos;
	}
	public void setSeis_minutos(int seis_minutos) {
		this.seis_minutos = seis_minutos;
	}
	public String getDescr_seis_minutos() {
		return descr_seis_minutos;
	}
	public void setDescr_seis_minutos(String descr_seis_minutos) {
		this.descr_seis_minutos = descr_seis_minutos;
	}
	public int getSalto_em_distancia() {
		return salto_em_distancia;
	}
	public void setSalto_em_distancia(int salto_em_distancia) {
		this.salto_em_distancia = salto_em_distancia;
	}
	public String getDescr_salto_distancia() {
		return descr_salto_distancia;
	}
	public void setDescr_salto_distancia(String descr_salto_distancia) {
		this.descr_salto_distancia = descr_salto_distancia;
	}
	public int getArremesso_medicineball() {
		return arremesso_medicineball;
	}
	public void setArremesso_medicineball(int arremesso_medicineball) {
		this.arremesso_medicineball = arremesso_medicineball;
	}
	public String getDescr_A_mediciniball() {
		return descr_A_mediciniball;
	}
	public void setDescr_A_mediciniball(String descr_A_mediciniball) {
		this.descr_A_mediciniball = descr_A_mediciniball;
	}
	public double getQuadrado() {
		return quadrado;
	}
	public void setQuadrado(double quadrado) {
		this.quadrado = quadrado;
	}
	public String getDescr_quadrado() {
		return descr_quadrado;
	}
	public void setDescr_quadrado(String descr_quadrado) {
		this.descr_quadrado = descr_quadrado;
	}
	public double getCorrida_20metros() {
		return corrida_20metros;
	}
	public void setCorrida_20metros(double corrida_20metros) {
		this.corrida_20metros = corrida_20metros;
	}
	public String getDescr_corrida20metros() {
		return descr_corrida20metros;
	}
	public void setDescr_corrida20metros(String descr_corrida20metros) {
		this.descr_corrida20metros = descr_corrida20metros;
	}
 
    
    
}
