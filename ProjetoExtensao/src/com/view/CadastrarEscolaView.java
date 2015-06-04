package com.view;

import com.example.projetoextensao.Banco;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class CadastrarEscolaView extends VerticalLayout implements View {

	final HorizontalLayout menuTopo = new HorizontalLayout();
	final HorizontalLayout meio= new HorizontalLayout();
	final HorizontalLayout emBaixo= new HorizontalLayout();
	Banco banco;
	
	public CadastrarEscolaView() {
		banco = new Banco();
		VerticalLayout vl =new VerticalLayout();
		
		VerticalLayout vlSeriesFun = new VerticalLayout();
		HorizontalLayout hlserief1 = new HorizontalLayout();
		HorizontalLayout hlserief2 = new HorizontalLayout();
		HorizontalLayout hlserief3 = new HorizontalLayout();
		HorizontalLayout hlserief4 = new HorizontalLayout();
		HorizontalLayout hlserief5 = new HorizontalLayout();
		HorizontalLayout hlserief6 = new HorizontalLayout();
		HorizontalLayout hlserief7 = new HorizontalLayout();
		HorizontalLayout hlserief8 = new HorizontalLayout();
		HorizontalLayout hlserief9 = new HorizontalLayout();
		
		VerticalLayout vlSeriesMedio =new VerticalLayout();
		HorizontalLayout hlserieMedio1 = new HorizontalLayout();
		HorizontalLayout hlserieMedio2 = new HorizontalLayout();
		HorizontalLayout hlserieMedio3 = new HorizontalLayout();
//---------------------variaveis para persisterem com dao
		String daoFundamental;

		
		//------------série 1
		//checkBox de turmas da série 1
		CheckBox a1f = new CheckBox("A");
		CheckBox b1f = new CheckBox("B");
		CheckBox c1f = new CheckBox("C");
		CheckBox d1f = new CheckBox("D");

		CheckBox f1 = new CheckBox("1ª");
		hlserief1.addComponent(f1);
		hlserief1.setSpacing(true);
		f1.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				
				if(f1.getValue()!=false){
					
					hlserief1.addComponents(a1f,b1f,c1f,d1f);
						
				}
				
				else{
					hlserief1.removeComponent(a1f);
					hlserief1.removeComponent(b1f);
					hlserief1.removeComponent(c1f);
					hlserief1.removeComponent(d1f);
				}

			}
			
		});
		
		
				
		//----fim série 1
		
		
		//-----série 2-----
		CheckBox a2f = new CheckBox("A");
		CheckBox b2f = new CheckBox("B");
		CheckBox c2f = new CheckBox("C");
		CheckBox d2f = new CheckBox("D");
		
		CheckBox f2 = new CheckBox("2ª");
		
		hlserief2.addComponent(f2);
		hlserief2.setSpacing(true);
		f2.addValueChangeListener(new ValueChangeListener() { 
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(f2.getValue()!=false){
					hlserief2.addComponents(a2f,b2f,c2f,d2f);
				}
				
				else{
					hlserief2.removeComponent(a2f);
					hlserief2.removeComponent(b2f);
					hlserief2.removeComponent(c2f);
					hlserief2.removeComponent(d2f);
				}
				
			}
			
		
		});
		
		//fim série 2 

		//------série 3
		
		CheckBox a3f = new CheckBox("A");
		CheckBox b3f = new CheckBox("B");
		CheckBox c3f = new CheckBox("C");
		CheckBox d3f = new CheckBox("D");
		
		CheckBox f3 = new CheckBox("3ª");
		
		hlserief3.addComponent(f3);
		hlserief3.setSpacing(true);
		f3.addValueChangeListener(new ValueChangeListener() { 
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(f3.getValue()!=false){
					hlserief3.addComponents(a3f,b3f,c3f,d3f);
					
				}
				
				else{
					hlserief3.removeComponent(a3f);
					hlserief3.removeComponent(b3f);
					hlserief3.removeComponent(c3f);
					hlserief3.removeComponent(d3f);
				}
				
			}
		});
		
		//-----fim série 3
		
		//------série 4
		CheckBox a4f = new CheckBox("A");
		CheckBox b4f = new CheckBox("B");
		CheckBox c4f = new CheckBox("C");
		CheckBox d4f = new CheckBox("D");
		
		CheckBox f4 = new CheckBox("4ª");
		
		hlserief4.addComponent(f4);
		hlserief4.setSpacing(true);
		f4.addValueChangeListener(new ValueChangeListener() { 
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(f4.getValue()!=false){
					
					hlserief4.addComponents(a4f,b4f,c4f,d4f);
				}
				
				else{
					hlserief4.removeComponent(a4f);
					hlserief4.removeComponent(b4f);
					hlserief4.removeComponent(c4f);
					hlserief4.removeComponent(d4f);
				}

				
			}
		});		
		
		
		//---------fim série 4
		
		//série 5---
		CheckBox a5f = new CheckBox("A");
		CheckBox b5f = new CheckBox("B");
		CheckBox c5f = new CheckBox("C");
		CheckBox d5f = new CheckBox("D");
		
		CheckBox f5 = new CheckBox("5ª");
		
		hlserief5.addComponent(f5);
		hlserief5.setSpacing(true);
		f5.addValueChangeListener(new ValueChangeListener() { 
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(f5.getValue()!=false){
					
					hlserief5.addComponents(a5f,b5f,c5f,d5f);
				}
				
				else{
					hlserief5.removeComponent(a5f);
					hlserief5.removeComponent(b5f);
					hlserief5.removeComponent(c5f);
					hlserief5.removeComponent(d5f);
				}

				
			}
		});
		
		

		//----------------fim série 5
		
		//---------------série 6
		
		CheckBox a6f = new CheckBox("A");
		CheckBox b6f = new CheckBox("B");
		CheckBox c6f = new CheckBox("C");
		CheckBox d6f = new CheckBox("D");
		
		CheckBox f6 = new CheckBox("6ª");
		
		hlserief6.addComponent(f6);
		hlserief6.setSpacing(true);
		f6.addValueChangeListener(new ValueChangeListener() { 
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(f6.getValue()!=false){
					
					hlserief6.addComponents(a6f,b6f,c6f,d6f);
				}
				
				else{
					hlserief6.removeComponent(a6f);
					hlserief6.removeComponent(b6f);
					hlserief6.removeComponent(c6f);
					hlserief6.removeComponent(d6f);
				}

				
			}
		});
		
		
		
		//---------fim série 6
		
		//série 7

		CheckBox a7f = new CheckBox("A");
		CheckBox b7f = new CheckBox("B");
		CheckBox c7f = new CheckBox("C");
		CheckBox d7f = new CheckBox("D");
		
		CheckBox f7 = new CheckBox("7ª");
		
		hlserief7.addComponent(f7);
		hlserief7.setSpacing(true);
		f7.addValueChangeListener(new ValueChangeListener() { 
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(f7.getValue()!=false){
					
					hlserief7.addComponents(a7f,b7f,c7f,d7f);
				}
				
				else{
					hlserief7.removeComponent(a7f);
					hlserief7.removeComponent(b7f);
					hlserief7.removeComponent(c7f);
					hlserief7.removeComponent(d7f);
				}

				
			}
		});
		
				
		//------fim série 7
		
		//------------série 8
		
		CheckBox a8f = new CheckBox("A");
		CheckBox b8f = new CheckBox("B");
		CheckBox c8f = new CheckBox("C");
		CheckBox d8f = new CheckBox("D");
		
		CheckBox f8 = new CheckBox("8ª");
		
		hlserief8.addComponent(f8);
		hlserief8.setSpacing(true);
		f8.addValueChangeListener(new ValueChangeListener() { 
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(f8.getValue()!=false){
					hlserief8.addComponents(a8f,b8f,c8f,d8f);
					
				}
				
				else{
					hlserief8.removeComponent(a8f);
					hlserief8.removeComponent(b8f);
					hlserief8.removeComponent(c8f);
					hlserief8.removeComponent(d8f);
				}

				
			}
		});
		
		
		
		//------fim série 8
		
		//----série 9 
		
		
		CheckBox a9f = new CheckBox("A");
		CheckBox b9f = new CheckBox("B");
		CheckBox c9f = new CheckBox("C");
		CheckBox d9f = new CheckBox("D");
		
		CheckBox f9 = new CheckBox("9ª");
		
		hlserief9.addComponent(f9);
		hlserief9.setSpacing(true);
		f9.addValueChangeListener(new ValueChangeListener() { 
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(f9.getValue()!=false){
					hlserief9.addComponents(a9f,b9f,c9f,d9f);
					
				}
				
				else{
					hlserief9.removeComponent(a9f);
					hlserief9.removeComponent(b9f);
					hlserief9.removeComponent(c9f);
					hlserief9.removeComponent(d9f);
				}

				
			}
		});
		
		//-----fim serie 9
		
		//-----------series do ensino médio
		
		//----série 1 do ensino médio
		CheckBox a1m = new CheckBox("A");
		CheckBox b1m = new CheckBox("B");
		CheckBox c1m = new CheckBox("C");
		CheckBox d1m = new CheckBox("D");
		
		CheckBox m1 = new CheckBox("1ª");
		
		hlserieMedio1.addComponent(m1);
		hlserieMedio1.setSpacing(true);
		m1.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(m1.getValue()!=false){
					hlserieMedio1.addComponents(a1m,b1m,c1m,d1m);
				}
				
				else{
					hlserieMedio1.removeComponent(a1m);
					hlserieMedio1.removeComponent(b1m);
					hlserieMedio1.removeComponent(c1m);
					hlserieMedio1.removeComponent(d1m);
				}
				
			}
		});
		
		
		
		//fim serie 1 ensino médio
		
		//------------série 2 ensino médio
		CheckBox a2m = new CheckBox("A");
		CheckBox b2m = new CheckBox("B");
		CheckBox c2m = new CheckBox("C");
		CheckBox d2m = new CheckBox("D");
		
		CheckBox m2 = new CheckBox("2ª");
		
		hlserieMedio2.addComponent(m2);
		hlserieMedio2.setSpacing(true);
		m2.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(m2.getValue()!=false){
					hlserieMedio2.addComponents(a2m,b2m,c2m,d2m);
				}
				
				else{
					hlserieMedio2.removeComponent(a2m);
					hlserieMedio2.removeComponent(b2m);
					hlserieMedio2.removeComponent(c2m);
					hlserieMedio2.removeComponent(d2m);
				}
				
				
			}
		});
		
		
		//fim serie 2 ensino médio
		
		//------------série 3 ensino médio
		CheckBox a3m = new CheckBox("A");
		CheckBox b3m = new CheckBox("B");
		CheckBox c3m = new CheckBox("C");
		CheckBox d3m = new CheckBox("D");
		
		CheckBox m3 = new CheckBox("3ª");
		
		hlserieMedio3.addComponent(m3);
		hlserieMedio3.setSpacing(true);
		m3.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(m3.getValue()!=false){
					hlserieMedio3.addComponents(a3m,b3m,c3m,d3m);
				}
				
				else{
					hlserieMedio3.removeComponent(a3m);
					hlserieMedio3.removeComponent(b3m);
					hlserieMedio3.removeComponent(c3m);
					hlserieMedio3.removeComponent(d3m);
				}

				
			}
		});
		
		//----------fim série 3 do ensino médio
		
		
		
		TextField nomeEscola = new TextField("Nome da Escola");
		nomeEscola.setWidth("300px");
		
		
		CheckBox fundamental = new CheckBox("Fundamental");
		CheckBox medio = new CheckBox("Medio");
		
		

		fundamental.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(fundamental.getValue()==false){
					
					vlSeriesFun.removeAllComponents();
					
				}
				
				else {
					
					HorizontalLayout descricao = new HorizontalLayout();
					Label  lbseries = new Label("Séries");
					Label  lbturma = new Label("Turmas");
					descricao.addComponents(lbseries,lbturma);
					descricao.setSpacing(true);
					
					vlSeriesFun.setSpacing(true);
					vlSeriesFun.addComponents(descricao,hlserief1,hlserief2,hlserief3,hlserief4,hlserief5,hlserief6,hlserief7,hlserief8,hlserief9);
				}
				
			}
		});		

		medio.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				
				if(medio.getValue()==false){
					vlSeriesMedio.removeAllComponents();
				}
				
				else {
					HorizontalLayout descricao = new HorizontalLayout();
					Label  lbseries = new Label("Séries");
					Label  lbturma = new Label("Turmas");
					descricao.setSpacing(true);
					descricao.addComponents(lbseries,lbturma);
					
					vlSeriesMedio.setSpacing(true);
					vlSeriesMedio.addComponents(descricao,hlserieMedio1,hlserieMedio2,hlserieMedio3);
					
				}
				
			}
		});
		
		
				
		ComboBox  cidade = new ComboBox("Cidade");
		banco.buscarCidade(cidade);

		
		Button salvar = new Button("Salvar",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				if(nomeEscola.getValue().toString()!="" && cidade.getValue()!=null){
					
					if(fundamental.getValue()|| medio.getValue()){
						int idCidade= 0;
						String ensinoFundametal ="Fundamental";
						idCidade = banco.buscarIdCidade(cidade.getValue().toString());
						banco.cadastrarEscola(nomeEscola.getValue().toString(),idCidade);
						
						//-----cadastro das séries e turmas do ensino fundamental na escola na tabela serie_turma_escola
						if(f1.getValue()){
						
							if(a1f.getValue()){
									banco.cadastrarSerieTurmaEscola('A',1,nomeEscola.getValue().toString(),ensinoFundametal);
								
								if(b1f.getValue()){
										banco.cadastrarSerieTurmaEscola('B',1,nomeEscola.getValue().toString(),ensinoFundametal);
									
									if(c1f.getValue()){
											banco.cadastrarSerieTurmaEscola('C',1,nomeEscola.getValue().toString(),ensinoFundametal);
											
										if(d1f.getValue()){
											banco.cadastrarSerieTurmaEscola('D',1,nomeEscola.getValue().toString(),ensinoFundametal);
										}
									}
								}
							}
						}
							
						if(f2.getValue()){
						
								if(a2f.getValue()){
									banco.cadastrarSerieTurmaEscola('A',2,nomeEscola.getValue().toString(),ensinoFundametal);
								
									if(b2f.getValue()){
										banco.cadastrarSerieTurmaEscola('B',2,nomeEscola.getValue().toString(),ensinoFundametal);
									
										if(c2f.getValue()){
											banco.cadastrarSerieTurmaEscola('C',2,nomeEscola.getValue().toString(),ensinoFundametal);
											
											if(d2f.getValue()){
												banco.cadastrarSerieTurmaEscola('D',2,nomeEscola.getValue().toString(),ensinoFundametal);
											}
										}
									}
								}
							}
								
							if(f3.getValue()){
							
									if(a3f.getValue()){
										banco.cadastrarSerieTurmaEscola('A',3,nomeEscola.getValue().toString(),ensinoFundametal);
									
										if(b3f.getValue()){
											banco.cadastrarSerieTurmaEscola('B',3,nomeEscola.getValue().toString(),ensinoFundametal);
										
											if(c3f.getValue()){
												banco.cadastrarSerieTurmaEscola('C',3,nomeEscola.getValue().toString(),ensinoFundametal);
												
												if(d3f.getValue()){
													banco.cadastrarSerieTurmaEscola('D',3,nomeEscola.getValue().toString(),ensinoFundametal);
												}
											}
										}
									}
								}
									
								if(f4.getValue()){
							
									if(a4f.getValue()){
										banco.cadastrarSerieTurmaEscola('A',4,nomeEscola.getValue().toString(),ensinoFundametal);
									
										if(b4f.getValue()){
											banco.cadastrarSerieTurmaEscola('B',4,nomeEscola.getValue().toString(),ensinoFundametal);
										
											if(c4f.getValue()){
												banco.cadastrarSerieTurmaEscola('C',4,nomeEscola.getValue().toString(),ensinoFundametal);
												
												if(d4f.getValue()){
													banco.cadastrarSerieTurmaEscola('D',4,nomeEscola.getValue().toString(),ensinoFundametal);
												}
											}
										}
										
									}
								}
									
								if(f5.getValue()){
									if(a5f.getValue()){
										banco.cadastrarSerieTurmaEscola('A',5,nomeEscola.getValue().toString(),ensinoFundametal);
									
										if(b5f.getValue()){
											banco.cadastrarSerieTurmaEscola('B',5,nomeEscola.getValue().toString(),ensinoFundametal);
										
											if(c5f.getValue()){
												banco.cadastrarSerieTurmaEscola('C',5,nomeEscola.getValue().toString(),ensinoFundametal);
												
												if(d5f.getValue()){
													banco.cadastrarSerieTurmaEscola('D',5,nomeEscola.getValue().toString(),ensinoFundametal);
												}
											}
										}
										
									}
								}
								
								if(f6.getValue()){
									if(a6f.getValue()){
										banco.cadastrarSerieTurmaEscola('A',6,nomeEscola.getValue().toString(),ensinoFundametal);
									
										if(b6f.getValue()){
											banco.cadastrarSerieTurmaEscola('B',6,nomeEscola.getValue().toString(),ensinoFundametal);
										
											if(c6f.getValue()){
												banco.cadastrarSerieTurmaEscola('C',6,nomeEscola.getValue().toString(),ensinoFundametal);
												
												if(d6f.getValue()){
													banco.cadastrarSerieTurmaEscola('D',6,nomeEscola.getValue().toString(),ensinoFundametal);
												}
											}
										}
									}	
								}
									
								if(f7.getValue()){
									
									if(a7f.getValue()){
										banco.cadastrarSerieTurmaEscola('A',7,nomeEscola.getValue().toString(),ensinoFundametal);
									
										if(b7f.getValue()){
											banco.cadastrarSerieTurmaEscola('B',7,nomeEscola.getValue().toString(),ensinoFundametal);
										
											if(c7f.getValue()){
												banco.cadastrarSerieTurmaEscola('C',7,nomeEscola.getValue().toString(),ensinoFundametal);
												
												if(d7f.getValue()){
													banco.cadastrarSerieTurmaEscola('D',7,nomeEscola.getValue().toString(),ensinoFundametal);
												}
											}
										}
										
									}
								}
								
								if(f8.getValue()){
									
									if(a8f.getValue()){
										banco.cadastrarSerieTurmaEscola('A',8,nomeEscola.getValue().toString(),ensinoFundametal);
									
										if(b8f.getValue()){
											banco.cadastrarSerieTurmaEscola('B',8,nomeEscola.getValue().toString(),ensinoFundametal);
										
											if(c8f.getValue()){
												banco.cadastrarSerieTurmaEscola('C',8,nomeEscola.getValue().toString(),ensinoFundametal);
												
												if(d8f.getValue()){
													banco.cadastrarSerieTurmaEscola('D',8,nomeEscola.getValue().toString(),ensinoFundametal);
												}
											}
										}
										
									}
								}
								
								if(f9.getValue()){
									
									if(a9f.getValue()){
										banco.cadastrarSerieTurmaEscola('A',9,nomeEscola.getValue().toString(),ensinoFundametal);
									
										if(b9f.getValue()){
											banco.cadastrarSerieTurmaEscola('B',9,nomeEscola.getValue().toString(),ensinoFundametal);
										
											if(c9f.getValue()){
												banco.cadastrarSerieTurmaEscola('C',9,nomeEscola.getValue().toString(),ensinoFundametal);
												
												if(d9f.getValue()){
													banco.cadastrarSerieTurmaEscola('D',9,nomeEscola.getValue().toString(),ensinoFundametal);
												}
											}
										}
										
									}
								}
								
								
						
					
								
								//-----fim cadastro das séries e turmas do ensino fundamental na escola na tabela serie_turma_escola	
								
								//-----cadastro das séries e turmas do ensino médio na escola na tabela serie_turma_escola
						
							String ensinoMedio = "medio";
							
								if(a1m.getValue()){
									
									if(a1m.getValue()){
										banco.cadastrarSerieTurmaEscola('A',1,nomeEscola.getValue().toString(),ensinoMedio);
									
										if(b1m.getValue()){
											banco.cadastrarSerieTurmaEscola('B',1,nomeEscola.getValue().toString(),ensinoMedio);
										
											if(c1m.getValue()){
												banco.cadastrarSerieTurmaEscola('C',1,nomeEscola.getValue().toString(),ensinoMedio);
												
												if(d1m.getValue()){
													banco.cadastrarSerieTurmaEscola('D',1,nomeEscola.getValue().toString(),ensinoMedio);
												}
											}
										}
										
									}
								}
								
								if(a2m.getValue()){
									
									if(a2m.getValue()){
										banco.cadastrarSerieTurmaEscola('A',2,nomeEscola.getValue().toString(),ensinoMedio);
									
										if(b2m.getValue()){
											banco.cadastrarSerieTurmaEscola('B',2,nomeEscola.getValue().toString(),ensinoMedio);
										
											if(c2m.getValue()){
												banco.cadastrarSerieTurmaEscola('C',2,nomeEscola.getValue().toString(),ensinoMedio);
												
												if(d2m.getValue()){
													banco.cadastrarSerieTurmaEscola('D',2,nomeEscola.getValue().toString(),ensinoMedio);
												}
											}
										}
										
									}
								}
								
								if(a3m.getValue()){
									
									if(a3m.getValue()){
										banco.cadastrarSerieTurmaEscola('A',3,nomeEscola.getValue().toString(),ensinoMedio);
									
										if(b3m.getValue()){
											banco.cadastrarSerieTurmaEscola('B',3,nomeEscola.getValue().toString(),ensinoMedio);
										
											if(c3m.getValue()){
												banco.cadastrarSerieTurmaEscola('C',3,nomeEscola.getValue().toString(),ensinoMedio);
												
												if(d3m.getValue()){
													banco.cadastrarSerieTurmaEscola('D',3,nomeEscola.getValue().toString(),ensinoMedio);
												}
											}
										}
										
									}
								}
								
								
													
						}
					
						else
							Notification.show("Escolha o Nivel do Ensino!");
					}
				
				else
					Notification.show("Informe os Campos Corretamente!");
				}
			
		});
		

		vl.addComponent(nomeEscola);
		
		Label lbEnsino = new Label("Ensino");
		vl.addComponent(lbEnsino);
		
		vl.addComponent(fundamental);
		
		vl.addComponent(vlSeriesFun);
		
		vl.addComponent(medio);
		vl.addComponent(vlSeriesMedio);
		
		vl.addComponent(cidade);
		vl.addComponent(salvar);
		vl.setSpacing(true);
		
		meio.addComponent(vl);
		
	}

	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
