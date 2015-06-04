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
	
	private Imc imcMasc;
	private Imc imcFem;
	private ImcService imcService;
	
	private TextField idadeMasc ;
	private TextField baixoPesoMasc ;
	private TextField normalMasc ;
	private TextField excessoPesoMasc;
	
	private TextField idadeFem ;
	private TextField baixoPesoFem ;
	private TextField normalFem ;
	private TextField excessoPesoFem;
	
	public ImcCadastrarView() {
	
		imcService = new ImcService();
		construirTabelaImcMasc();
		construtorTabImcFem();
		
		Button salvar= new Button("Salvar",new ClickListener() {
					
					@Override
					public void buttonClick(ClickEvent event) {
						
						if(validarDados()){
							imcMasc= new Imc();
							imcMasc.setTipo('M');
							imcMasc.setIdade(Integer.parseInt(idadeMasc.getValue().toString()));
							imcMasc.setBaixoPeso(Double.parseDouble(baixoPesoMasc.getValue().toString()));
							imcMasc.setNormal(Double.parseDouble(normalMasc.getValue().toString()));
							imcMasc.setExcessoPeso(Double.parseDouble(excessoPesoMasc.getValue().toString()));
							
							
							imcFem= new Imc();
							imcFem.setTipo('F');
							imcFem.setIdade(Integer.parseInt(idadeFem.getValue().toString()));
							imcFem.setBaixoPeso(Double.parseDouble(baixoPesoFem.getValue().toString()));
							imcFem.setNormal(Double.parseDouble(normalFem.getValue().toString()));
							imcFem.setExcessoPeso(Double.parseDouble(excessoPesoFem.getValue().toString()));
							
							
							imcService.salvarImc(imcMasc);
							imcService.salvarImc(imcFem);
							
							limparDados();
							Notification.show("Cadastrado Com Sucesso!");
						}
					}
				});
		
		this.addComponent(salvar);
		this.setComponentAlignment(salvar, Alignment.MIDDLE_RIGHT);
		
	}
	
	protected void limparDados() {
		imcMasc= new Imc();

		idadeMasc.setValue("");
		baixoPesoMasc.setValue("");
		normalMasc.setValue("");
		excessoPesoMasc.setValue("");
		
		
		imcFem= new Imc();
		idadeFem.setValue("");
		baixoPesoFem.setValue("");
		normalFem.setValue("");
		excessoPesoFem.setValue("");
		

	}

	public void construirTabelaImcMasc(){
	
		this.setSizeUndefined();
		FormLayout geral = new FormLayout();
		geral.setSpacing(true);
		HorizontalLayout tituloTabMasc = new HorizontalLayout();
		HorizontalLayout apresTabelaMasc = new HorizontalLayout();
		apresTabelaMasc.setSpacing(true);	
		
		Label nomeTabelaMasc = new Label("Imc Masculino");
		idadeMasc = new TextField("idade");
		baixoPesoMasc = new TextField("baixo Peso");
		normalMasc = new TextField("Normal");
		excessoPesoMasc= new TextField("Excesso de Peso");
		
		
		tituloTabMasc.addComponent(nomeTabelaMasc);
		apresTabelaMasc.addComponent(idadeMasc);
		apresTabelaMasc.addComponent(baixoPesoMasc);
		apresTabelaMasc.addComponent(normalMasc);
		apresTabelaMasc.addComponent(excessoPesoMasc);
		
			
		this.addComponents(tituloTabMasc,apresTabelaMasc);
		this.setComponentAlignment(tituloTabMasc, Alignment.MIDDLE_CENTER);
		this.setComponentAlignment(apresTabelaMasc, Alignment.MIDDLE_CENTER);
	}
		
	public void construtorTabImcFem(){
		this.setSizeUndefined();
		FormLayout geral = new FormLayout();
		geral.setSpacing(true);
		HorizontalLayout tituloTabFem = new HorizontalLayout();
		HorizontalLayout apresTabelaFem= new HorizontalLayout();
		apresTabelaFem.setSpacing(true);	
		
		Label nomeTabelaFem = new Label("Imc Feminino");
		idadeFem = new TextField("idade");
		baixoPesoFem = new TextField("baixo Peso");
		normalFem = new TextField("Normal");
		excessoPesoFem= new TextField("Excesso de Peso");
		
		
		tituloTabFem.addComponent(nomeTabelaFem);
		apresTabelaFem.addComponent(idadeFem);
		apresTabelaFem.addComponent(baixoPesoFem);
		apresTabelaFem.addComponent(normalFem);
		apresTabelaFem.addComponent(excessoPesoFem);
		
		
		this.addComponents(tituloTabFem,apresTabelaFem);
		this.setComponentAlignment(tituloTabFem, Alignment.MIDDLE_CENTER);
		this.setComponentAlignment(apresTabelaFem, Alignment.MIDDLE_CENTER);
		
	}

	public boolean validarDados(){
		boolean resposta=false;
		
		if(idadeMasc.getValue()!=null||baixoPesoMasc.getValue()!=null||normalMasc.getValue()!=null||excessoPesoMasc.getValue()!=null||
				idadeFem.getValue()!=null||baixoPesoFem.getValue()!=null||normalFem.getValue()!=null||excessoPesoFem.getValue()!=null){
		resposta =true;
		}
		
		else
			Notification.show("Informe os dados Corretamentes","Há campos que nao foram Preenchidos!",Notification.Type.WARNING_MESSAGE);
		
		return resposta;
		
	}
	
}
