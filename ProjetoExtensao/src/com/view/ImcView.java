package com.view;

import java.util.List;

import com.Service.ImcService;
import com.model.Imc;
import com.vaadin.data.Property;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ImcView extends HorizontalLayout {

	ImcService imcService;
	VerticalLayout vlImcMasc;
	VerticalLayout vlImcFem;
	
	VerticalLayout vlImcMascTopo;
	VerticalLayout vlImcMascMeio;
	HorizontalLayout vlImcMascEmBaixo;
	
	VerticalLayout vlImcFemTopo;
	VerticalLayout vlImcFemcMeio;
	HorizontalLayout vlImcFemEmBaixo;
	
	Table tabelaImc;
	char tabelaSexo;
	Imc imcManipuladoMasc;
	Imc imcManipuladoFem;
	 Imc imcCopia;
	int linhaSelecionada;
	
	boolean tabelaSelecionadaMasc;
	boolean tabelaSelecionadaFem;
	
	
	private Window janela;
	private Label menssagem;
	
	
	
	public ImcView() {
		imcService = new ImcService();
		vlImcMasc = new VerticalLayout();
		vlImcFem = new VerticalLayout();
	
		vlImcMascTopo = new VerticalLayout();
		vlImcMascMeio= new VerticalLayout();
		vlImcMascEmBaixo= new HorizontalLayout();
		
		vlImcFemTopo= new VerticalLayout();
		vlImcFemcMeio= new VerticalLayout();
		vlImcFemEmBaixo= new HorizontalLayout();
		
		tabelaSelecionadaMasc = false;
		tabelaSelecionadaFem = false;
		
		
		vlImcMasc.addComponents(vlImcMascTopo,vlImcMascMeio,vlImcMascEmBaixo);
		vlImcFem.addComponents(vlImcFemTopo,vlImcFemcMeio, vlImcFemEmBaixo);
		
		
		this.setSpacing(true);
		
		 ImcCadastrarView imccadastrarMasc= new ImcCadastrarView('M');
		 
		vlImcMasc.setStyleName("vlImc");
		vlImcMascTopo.addComponent(imccadastrarMasc);
		vlImcMascMeio.addComponent(montarTabela('M'));

		ImcCadastrarView imccadastrarFem= new ImcCadastrarView('F');
		vlImcFem.setStyleName("vlImc");
		vlImcFemTopo.addComponent(imccadastrarFem);
		vlImcFemcMeio.addComponent(montarTabela('F'));
		
		Button editarMasc = new Button("Editar",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				if(tabelaSelecionadaMasc==true){
					imccadastrarMasc. idade.setValue(String.valueOf(imcManipuladoMasc.getIdade()));
					imccadastrarMasc.baixoPeso.setValue(String.valueOf(imcManipuladoMasc.getBaixoPeso()));
					imccadastrarMasc.normal.setValue(String.valueOf(imcManipuladoMasc.getNormal()));
					imccadastrarMasc.excessoPeso.setValue(String.valueOf(imcManipuladoMasc.getExcessoPeso()));
					
				}
				
				else 
					Notification.show("Selecione um item da Tabela!");
				
				
				
			}
		});
		
		Button excluirMasc = new Button("Exluir",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				if(tabelaSelecionadaMasc==true){
					layoutConfirmação('M');
					
				}
				
				else 
					Notification.show("Selecione um item da Tabela!");
				
				
			}
		});
		
		Button editarFem= new Button("Editar",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				if(tabelaSelecionadaMasc==true){
					imccadastrarFem. idade.setValue(String.valueOf(imcManipuladoFem.getIdade()));
					imccadastrarFem.baixoPeso.setValue(String.valueOf(imcManipuladoFem.getBaixoPeso()));
					imccadastrarFem.normal.setValue(String.valueOf(imcManipuladoFem.getNormal()));
					imccadastrarFem.excessoPeso.setValue(String.valueOf(imcManipuladoFem.getExcessoPeso()));
					
				}
				
				else 
					Notification.show("Selecione um item da Tabela!");
				
				
				
			}
		});
		
		Button excluirFem = new Button("Excluir", new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				if(tabelaSelecionadaFem==true){
					layoutConfirmação('F');
				}
				else 
					Notification.show("Selecione um item da Tabela!");


			}
		});
		
		vlImcMascEmBaixo.addComponents(editarMasc, excluirMasc);
		vlImcMascEmBaixo.setSpacing(true);
		vlImcFemEmBaixo.addComponents(editarFem,excluirFem);
		vlImcFemEmBaixo.setSpacing(true);
		this.addComponent(vlImcMasc);
		this.addComponent(vlImcFem);
		
		
	}
	
	public Table montarTabela(char tabelaSexo){

		if(tabelaSexo=='M'){
			tabelaImc = new Table("Imc Masculino");
			
		}
		else if(tabelaSexo=='F'){
			tabelaImc = new Table("Imc Feminino");
		}
		
		tabelaImc.addContainerProperty("Idade", Integer.class, null);
		tabelaImc.addContainerProperty("Baixo Peso", Double.class, null);
		tabelaImc.addContainerProperty("Normal", Double.class, null);
		tabelaImc.addContainerProperty("Excesso de Peso", Double.class, null);
		
		
		List<Imc> listaImc = imcService.buscarTodos(tabelaSexo);
		
		
		int linha = 1;
		for(Imc imc: listaImc){
			tabelaImc.addItem(new Object[]{imc.getIdade(),imc.getBaixoPeso(),imc.getNormal(),imc.getExcessoPeso()},linha);
			linha++;
		}
		tabelaImc.setPageLength(tabelaImc.size());
		tabelaImc.setSelectable(true);
		tabelaImc.setImmediate(true);
		
		tabelaImc.addListener(new ItemClickListener() {
			
			@Override
			public void itemClick(ItemClickEvent event) {

					if(tabelaSexo=='M'){
						imcManipuladoMasc = new Imc();
						imcManipuladoMasc.setTipo(tabelaSexo);
						
						Property itemProperty = event.getItem().getItemProperty("Idade");
						imcManipuladoMasc.setIdade(Integer.parseInt(itemProperty.getValue().toString()));
						
						itemProperty = event.getItem().getItemProperty("Baixo Peso");
						imcManipuladoMasc.setBaixoPeso(Double.parseDouble(itemProperty.getValue().toString()));
						
						itemProperty = event.getItem().getItemProperty("Normal") ;
						imcManipuladoMasc.setNormal(Double.parseDouble(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Excesso de Peso");
						imcManipuladoMasc.setExcessoPeso(Double.parseDouble(itemProperty.getValue().toString()));
						
						tabelaSelecionadaMasc = true;
					}
					
					else if (tabelaSexo=='F'){
						
						imcManipuladoFem = new Imc();
						imcManipuladoFem.setTipo(tabelaSexo);
						
						Property itemProperty = event.getItem().getItemProperty("Idade");
						imcManipuladoFem.setIdade(Integer.parseInt(itemProperty.getValue().toString()));
						
						itemProperty = event.getItem().getItemProperty("Baixo Peso");
						imcManipuladoFem.setBaixoPeso(Double.parseDouble(itemProperty.getValue().toString()));
						
						itemProperty = event.getItem().getItemProperty("Normal") ;
						imcManipuladoFem.setNormal(Double.parseDouble(itemProperty.getValue().toString()));
						
						itemProperty = event.getItem().getItemProperty("Excesso de Peso");
						imcManipuladoFem.setExcessoPeso(Double.parseDouble(itemProperty.getValue().toString()));
						
						tabelaSelecionadaFem = true;
					}
					
				
				
//				System.out.println("idade = "+imcBuscado.getIdade()+"\nbaixo peso ="+imcBuscado.getBaixoPeso()+""
//						+ "\nnormal = "+imcBuscado.getNormal()+"\n excesso de peso  = "+imcBuscado.getExcessoPeso());
			}
		});
		
			
//			@Override
//			public void valueChange(ValueChangeEvent event) {
//				System.out.println("valor ="+ event.getProperty().getValue());
////				linhaSelecionada = Integer.parseInt(event.getProperty().getValue().toString());
//			}
//		});
		
		
		
		return tabelaImc;
	}
	
	public void alterarImc(Imc imc){
		
	}
	
	public void layoutConfirmação(char tabelaSelecionada) {

		Label mgs = new Label("Deseja realmente excluir os seguintes Dados?");
		janela = new Window(mgs.getValue().toString());
		janela.setClosable(false);

		VerticalLayout geral = new VerticalLayout();
		HorizontalLayout hlBotoes = new HorizontalLayout();
		HorizontalLayout hlmgs = new HorizontalLayout();

		Label lbDados = null;

		if(tabelaSelecionada=='M'){
			lbDados = new Label("idade :" +imcManipuladoMasc.getIdade());
		}
		
		else if(tabelaSelecionada=='F'){
			lbDados = new Label("idade :" +imcManipuladoFem.getIdade());
		}
		
		
		
		

		Button sim = new Button("Sim", new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
					
				if(tabelaSelecionada=='M'){
					imcService = new ImcService();
					imcService.excluirImc(imcManipuladoMasc);
					janela.close();
					Notification.show("Excluido com Sucesso!");
				}
				
				else if(tabelaSelecionada=='F'){
					imcService = new ImcService();
					imcService.excluirImc(imcManipuladoFem);
					janela.close();
					Notification.show("Excluido com Sucesso!");
				}
				
				

			}
		});
		Button nao = new Button("Não", new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				janela.close();

			}
		});

		hlmgs.addComponent(lbDados);
		hlBotoes.addComponents(nao, sim);
		geral.addComponents(lbDados, hlBotoes);

		hlBotoes.setComponentAlignment(nao, Alignment.MIDDLE_CENTER);
		hlBotoes.setComponentAlignment(sim, Alignment.MIDDLE_CENTER);

		janela.setContent(geral);
		janela.center();
		UI.getCurrent().addWindow(janela);

	}
	
	
}
