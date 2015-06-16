package com.view;

import com.Service.ImcService;
import com.model.Imc;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ImcCadastrarView extends VerticalLayout{
	
	public Imc imc;
	private ImcService imcService;
	
	public TextField idade;
	public TextField baixoPeso;
	public TextField normal;
	public TextField excessoPeso;
	
	public ImcCadastrarView(char tabelaSexo) {
	
		imcService = new ImcService();
		construirTabelaImc(tabelaSexo);
		
		Button salvar= new Button("Salvar",new ClickListener() {
					
					@Override
					public void buttonClick(ClickEvent event) {
						
						if(validarDados()){
							imc= new Imc();
							
							if(tabelaSexo=='M'){
								imc.setTipo('M');
							}
							
							else if (tabelaSexo=='F'){
								imc.setTipo('F');
							}
							imc.setIdade(Integer.parseInt(idade.getValue().toString()));
							imc.setBaixoPeso(Double.parseDouble(baixoPeso.getValue().toString()));
							imc.setNormal(Double.parseDouble(normal.getValue().toString()));
							imc.setExcessoPeso(Double.parseDouble(excessoPeso.getValue().toString()));
							
								imcService.salvarImc(imc);
														
							limparDados();
							Notification.show("Cadastrado Com Sucesso!");
						}
					}
				});
		
		this.addComponent(salvar);
		this.setComponentAlignment(salvar, Alignment.MIDDLE_RIGHT);
		
	}
	
	protected void limparDados() {
		imc= new Imc();

		idade.setValue("");
		baixoPeso.setValue("");
		normal.setValue("");
		excessoPeso.setValue("");

	}

	public void construirTabelaImc(char tabelaSexo){
	
		
		this.setSizeUndefined();
		Label nomeTabela  = new Label();
		
		FormLayout geral = new FormLayout();
		geral.setSpacing(true);
		HorizontalLayout tituloTab = new HorizontalLayout();
		HorizontalLayout apresTabela = new HorizontalLayout();
		apresTabela.setSpacing(true);	
		
		if(tabelaSexo=='M'){
			nomeTabela.setCaption("Imc Masculino");
		}
		
		else if(tabelaSexo=='F'){
			nomeTabela.setCaption("Imc Feminino");
		}
		
		
		idade = new TextField("idade");
		baixoPeso = new TextField("baixo Peso");
		normal = new TextField("Normal");
		excessoPeso = new TextField("Excesso de Peso");
		
		
		tituloTab.addComponent(nomeTabela);
		apresTabela.addComponent(idade);
		apresTabela.addComponent(baixoPeso);
		apresTabela.addComponent(normal);
		apresTabela.addComponent(excessoPeso);
		
			
		this.addComponents(tituloTab,apresTabela);
		this.setComponentAlignment(tituloTab, Alignment.MIDDLE_CENTER);
		this.setComponentAlignment(apresTabela, Alignment.MIDDLE_CENTER);
	}
		
	
	public boolean validarDados(){
		boolean resposta=false;
		
		if(idade.getValue()!=null||baixoPeso.getValue()!=null||normal.getValue()!=null||excessoPeso.getValue()!=null){
		resposta =true;
		}
		
		else
			Notification.show("Informe os dados Corretamentes","Há campos que nao foram Preenchidos!",Notification.Type.WARNING_MESSAGE);
		
		return resposta;
		
	}
	
	
	
}
