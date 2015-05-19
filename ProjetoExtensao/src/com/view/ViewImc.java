package com.view;

import com.example.projetoextensao.Banco;
import com.model.Imc;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ViewImc {
	

	private Label nomeTabela;
	private TextField idade ;
	private TextField baixoPeso;
	private TextField normal ;
	private TextField excessoPeso;
	private TextField obesidade ;
	
	public ViewImc(String tituloTabela) {
		tabelaImc(tituloTabela);
	}
	
	private Imc imc;
	public Imc getImc() {
		return imc;
	}
	
	
	public void setImc(Imc imc) {
		this.imc = imc;
	}

	public Label getNomeTabela() {
		return nomeTabela;
	}

	public void setNomeTabela(Label nomeTabela) {
		this.nomeTabela = nomeTabela;
	}

	public TextField getIdade() {
		return idade;
	}

	public void setIdade(TextField idade) {
		this.idade = idade;
	}

	public TextField getBaixoPeso() {
		return baixoPeso;
	}

	public void setBaixoPeso(TextField baixoPeso) {
		this.baixoPeso = baixoPeso;
	}

	public TextField getNormal() {
		return normal;
	}

	public void setNormal(TextField normal) {
		this.normal = normal;
	}

	public TextField getExcessoPeso() {
		return excessoPeso;
	}

	public void setExcessoPeso(TextField excessoPeso) {
		this.excessoPeso = excessoPeso;
	}

	public TextField getObesidade() {
		return obesidade;
	}

	public void setObesidade(TextField obesidade) {
		this.obesidade = obesidade;
	}

	public   VerticalLayout tabelaImc(String tituloTabela){
		VerticalLayout geral = new VerticalLayout();
		geral.setSpacing(true);
		HorizontalLayout titulo = new HorizontalLayout();
		HorizontalLayout apresTabelaMasc = new HorizontalLayout();
		apresTabelaMasc.setSpacing(true);	
		
		Label nomeTabela = new Label(tituloTabela);
		TextField idade = new TextField("idade");
		TextField baixoPeso = new TextField("baixo Peso");
		TextField normal = new TextField("Normal");
		TextField excessoPeso= new TextField("Excesso de Peso");
		TextField obesidade = new TextField("Obesidade");
		
		
		titulo.addComponent(nomeTabela);
		apresTabelaMasc.addComponent(idade);
		apresTabelaMasc.addComponent(baixoPeso);
		apresTabelaMasc.addComponent(normal);
		apresTabelaMasc.addComponent(excessoPeso);
		apresTabelaMasc.addComponent(obesidade);
		
		Button salvar= new Button("Salvar",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				imc= new Imc();
				imc.setIdade(Integer.parseInt(idade.getValue().toString()));
				imc.setBaixoPeso(Double.parseDouble(baixoPeso.getValue().toString()));
				imc.setNormal(Double.parseDouble(normal.getValue().toString()));
				imc.setExcessoPeso(Double.parseDouble(excessoPeso.getValue().toString()));
				imc.setObesidade(Double.parseDouble(obesidade.getValue().toString()));
				

				
			}
		});
		
		geral.addComponent(titulo);
		geral.setComponentAlignment(titulo, Alignment.MIDDLE_CENTER);
		geral.addComponent(apresTabelaMasc);
		
		return geral;
	}
}
