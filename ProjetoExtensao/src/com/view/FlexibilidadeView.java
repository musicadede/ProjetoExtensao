package com.view;

import java.util.List;


import com.Service.FlexibilidadeService;
import com.Service.ImcService;
import com.model.Flexibilidade;
import com.model.Imc;
import com.vaadin.data.Property;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class FlexibilidadeView extends HorizontalLayout {

	FlexibilidadeService flexibilidadeService;
	VerticalLayout vlFlexibilidadeMasc;
	VerticalLayout vlFlexibilidadeFem;
	
	
	char tabelaSexo;
	int linhaSelecionada;
	

	private Window janela;
	private Label menssagem;
//	Flexibilidade flexibilidade;
	
	
	boolean tabelaSelecionada = false;
	
	
	public FlexibilidadeView() {

		flexibilidadeService = new FlexibilidadeService();
		vlFlexibilidadeMasc = new VerticalLayout();
		vlFlexibilidadeFem = new VerticalLayout();
	
		this.setSpacing(true);
		
		Table tabelaFlexibilidade = null;
		 
		vlFlexibilidadeMasc.setStyleName("vlFlexibilidade");
		vlFlexibilidadeMasc.addComponent(montarlayout('M'));

		vlFlexibilidadeFem.setStyleName("vlFlexibilidade");
		vlFlexibilidadeFem.addComponent(montarlayout('F'));
		
		
		this.addComponent(vlFlexibilidadeMasc);
		this.addComponent(vlFlexibilidadeFem);
		
		
	}
	
	public VerticalLayout montarlayout(char tabelaSexo){
		
		VerticalLayout vlFlebilidade = new VerticalLayout();
		VerticalLayout vltabela = new VerticalLayout();
		Table tabelaFlexibilidade = new Table();
		Flexibilidade flexibilidade = new Flexibilidade();
		
		
//-----------------------layout com campos de cadastro
		

		TextField idade = new TextField("Idade");
		TextField muitoFraco = new TextField("Muito Fraco");
		TextField fraco = new TextField("Fraco");
		TextField razoavel = new TextField("Razoavel");
		TextField bom = new TextField("Bom");
		TextField muitoBom = new TextField("Muito Bom");
		
		final Label nomeTabela  = new Label();
		
		FormLayout geral = new FormLayout();
		geral.setSpacing(true);
		HorizontalLayout tituloTab = new HorizontalLayout();
		VerticalLayout vlcadastro = new VerticalLayout();
		vlcadastro.setSpacing(true);	
		
		if(tabelaSexo=='M'){
			nomeTabela.setCaption("Flexibilidade Masculino");
		}
		
		else if(tabelaSexo=='F'){
			nomeTabela.setCaption("Flexibilidade Feminino");
		}
		
		tituloTab.addComponent(nomeTabela);
		vlcadastro.addComponent(idade);
		vlcadastro.addComponent(muitoFraco);
		vlcadastro.addComponent(fraco);
		vlcadastro.addComponent(razoavel);
		vlcadastro.addComponent(bom);
		vlcadastro.addComponent(muitoBom);
		
		
		Button salvar= new Button("Salvar",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				if(validarDados(idade,muitoFraco,fraco,razoavel,bom,muitoBom)){
					
					if(tabelaSexo=='M'){
						flexibilidade.setTipo('M');
					}
					
					else if (tabelaSexo=='F'){
						flexibilidade.setTipo('F');
					}
					flexibilidade.setIdade(Integer.parseInt(idade.getValue().toString()));
					flexibilidade.setMuitoFraco(Integer.parseInt(muitoFraco.getValue().toString()));
					flexibilidade.setFraco(Integer.parseInt(fraco.getValue().toString()));
					flexibilidade.setRazoavel(Integer.parseInt(razoavel.getValue().toString()));
					flexibilidade.setBom(Integer.parseInt(bom.getValue().toString()));
					flexibilidade.setMuitoBom(Integer.parseInt(muitoBom.getValue().toString()));
					
					flexibilidadeService.salvarFlexibilidade(flexibilidade);
												vltabela.removeAllComponents();
												vltabela.addComponent(carregarTabela(tabelaSexo, flexibilidade));
												
												
					limparDados(idade,muitoFraco,fraco,razoavel,bom,muitoBom,flexibilidade);
					Notification.show("Cadastrado Com Sucesso!");
				}
			}
		});
//		-----------------------FIM do layout com campos de cadastro
		
		
		
//		------------------------------------------------ layout da tabela
//		List<Flexibilidade> listaFlexibilidade = flexibilidadeService.buscarTodos(tabelaSexo);
//				
//		int linha = 1;
//		for(Flexibilidade flexi:  listaFlexibilidade){
//			tabelaFlexibilidade.addItem(new Object[]{flexi.getIdade(),flexi.getMuitoFraco(),flexi.getFraco(),flexi.getRazoavel(),flexi.getBom(),flexi.getMuitoBom()},linha);
//			linha++;
//		}
		tabelaFlexibilidade =  carregarTabela(tabelaSexo,flexibilidade);
//		tabelaFlexibilidade.setPageLength(tabelaFlexibilidade.size());
//		tabelaFlexibilidade.setSelectable(true);
//		tabelaFlexibilidade.setImmediate(true);
//		
//		tabelaFlexibilidade.addListener(new ItemClickListener() {
//			
//			@Override
//			public void itemClick(ItemClickEvent event) {
//
//					if(tabelaSexo=='M'){
//						flexibilidade.setTipo(tabelaSexo);
//						
//						Property itemProperty = event.getItem().getItemProperty("Idade");
//						flexibilidade.setIdade(Integer.parseInt(itemProperty.getValue().toString()));
//						
//						itemProperty = event.getItem().getItemProperty("Muito Fraco");
//						flexibilidade.setMuitoFraco(Integer.parseInt(itemProperty.getValue().toString()));
//						
//						itemProperty = event.getItem().getItemProperty("Fraco") ;
//						flexibilidade.setFraco(Integer.parseInt(itemProperty.getValue().toString()));
//						
//						
//						itemProperty = event.getItem().getItemProperty("Razoavel") ;
//						flexibilidade.setRazoavel(Integer.parseInt(itemProperty.getValue().toString()));
//						
//						
//						itemProperty = event.getItem().getItemProperty("Bom") ;
//						flexibilidade.setBom(Integer.parseInt(itemProperty.getValue().toString()));
//						
//						
//						itemProperty = event.getItem().getItemProperty("Muito Bom");
//						flexibilidade.setMuitoBom(Integer.parseInt(itemProperty.getValue().toString()));
//						
//						tabelaSelecionada= true;
//					}
//					
//					else if (tabelaSexo=='F'){
//						
//						flexibilidade.setTipo(tabelaSexo);
//						
//						Property itemProperty = event.getItem().getItemProperty("Idade");
//						flexibilidade.setIdade(Integer.parseInt(itemProperty.getValue().toString()));
//						
//						itemProperty = event.getItem().getItemProperty("Muito Fraco");
//						flexibilidade.setMuitoFraco(Integer.parseInt(itemProperty.getValue().toString()));
//						
//						itemProperty = event.getItem().getItemProperty("Fraco") ;
//						flexibilidade.setFraco(Integer.parseInt(itemProperty.getValue().toString()));
//						
//						
//						itemProperty = event.getItem().getItemProperty("Razoavel") ;
//						flexibilidade.setRazoavel(Integer.parseInt(itemProperty.getValue().toString()));
//						
//						
//						itemProperty = event.getItem().getItemProperty("Bom") ;
//						flexibilidade.setBom(Integer.parseInt(itemProperty.getValue().toString()));
//						
//						
//						itemProperty = event.getItem().getItemProperty("Muito Bom");
//						flexibilidade.setMuitoBom(Integer.parseInt(itemProperty.getValue().toString()));
//						
//						tabelaSelecionada = true;
//					}
//				
//			}
//		});
		
		
		
		Button editar = new Button("Editar",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				if(tabelaSelecionada==true){
					idade.setValue(String.valueOf(flexibilidade.getIdade()));
					muitoFraco.setValue(String.valueOf(flexibilidade.getMuitoFraco()));
					fraco.setValue(String.valueOf(flexibilidade.getFraco()));
					razoavel.setValue(String.valueOf(flexibilidade.getRazoavel()));
					bom.setValue(String.valueOf(flexibilidade.getBom()));
					muitoBom.setValue(String.valueOf(flexibilidade.getMuitoBom()));
					
				}
				
				else 
					Notification.show("Selecione um item da Tabela!");
				
				
				
			}
		});
		
		Button excluir = new Button("Exluir",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				if(tabelaSelecionada==true){
					layoutConfirmação(tabelaSexo,flexibilidade,vltabela);
					
				}
				
				else 
					Notification.show("Selecione um item da Tabela!");
				
				
			}
		});
		HorizontalLayout hlBotoes = new HorizontalLayout();
		hlBotoes.addComponents(editar,excluir);

		vltabela.addComponent(tabelaFlexibilidade);

//		---------adcionando na orddem ao Vertical layout que contem os campos de cadastros e a tabela
			vlFlebilidade.addComponent(tituloTab);
			vlFlebilidade.addComponent(vlcadastro);
			vlFlebilidade.addComponent(salvar);
			vlFlebilidade.addComponent(vltabela);
			vlFlebilidade.addComponent(hlBotoes);
			vlFlebilidade.setComponentAlignment(salvar, Alignment.MIDDLE_RIGHT);

		
		
		
			
		
		
			
//			@Override
//			public void valueChange(ValueChangeEvent event) {
//				System.out.println("valor ="+ event.getProperty().getValue());
////				linhaSelecionada = Integer.parseInt(event.getProperty().getValue().toString());
//			}
//		});
		
		
		
		return vlFlebilidade;
	}
	

	
	public void layoutConfirmação(char tabSexo,Flexibilidade flex, VerticalLayout vltab) {

		Label mgs = new Label("Deseja realmente excluir os seguintes Dados?");
		janela = new Window(mgs.getValue().toString());
		janela.setClosable(false);

		VerticalLayout geral = new VerticalLayout();
		HorizontalLayout hlBotoes = new HorizontalLayout();
		HorizontalLayout hlmgs = new HorizontalLayout();

		Label lbDados = null;

		if(tabSexo=='M'){
			lbDados = new Label("idade :" +flex.getIdade());
		}
		
		else if(tabSexo=='F'){
			lbDados = new Label("idade :" +flex.getIdade());
		}
		
		
		
		

		Button sim = new Button("Sim", new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
					
				if(tabSexo=='M'){
					flexibilidadeService = new FlexibilidadeService();
					flexibilidadeService.excluirFlexibilidade(flex);
					janela.close();
					vltab.removeAllComponents();
					vltab.addComponent(carregarTabela(tabSexo, flex));
					Notification.show("Excluido com Sucesso!");
				}
				
				else if(tabSexo=='F'){
					flexibilidadeService = new FlexibilidadeService();
					flexibilidadeService.excluirFlexibilidade(flex);
					janela.close();
					vltab.removeAllComponents();
					vltab.addComponent(carregarTabela(tabSexo, flex));
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
	
				
	public void limparDados(TextField idade,TextField muitoFraco,TextField fraco,TextField razoavel,
	TextField bom,TextField muitoBom,Flexibilidade flex) {
		flex= new Flexibilidade();

		idade.setValue("");
		muitoFraco.setValue("");
		fraco.setValue("");
		razoavel.setValue("");
		bom.setValue("");
		muitoBom.setValue("");

	}

		
	public boolean validarDados(TextField idade,TextField muitoFraco,TextField fraco,TextField razoavel,
								TextField bom,TextField muitoBom){
		boolean resposta=false;
		
		if(idade.getValue()!=null||muitoFraco.getValue()!=null||fraco.getValue()!=null||razoavel.getValue()!=null||
				bom.getValue()!=null||muitoBom.getValue()!=null){
		resposta =true;
		}
		
		else
			Notification.show("Informe os dados Corretamentes","Há campos que nao foram Preenchidos!",Notification.Type.WARNING_MESSAGE);
		
		return resposta;
		
	}
			
	public Table carregarTabela(char tabSexo,Flexibilidade flex){
		
		
		Table tabelaFlex= new Table(); 

		if(tabSexo=='M'){
			tabelaFlex= new Table("Tabela Flexibilidade Masculino");
			
		}
		else if(tabSexo=='F'){
			tabelaFlex= new Table("Tabela Flexibilidade Feminino");
		}
		
				
		tabelaFlex.addContainerProperty("Idade", Integer.class, null);
		tabelaFlex.addContainerProperty("Muito Fraco", Integer.class, null);
		tabelaFlex.addContainerProperty("Fraco", Integer.class, null);
		tabelaFlex.addContainerProperty("Razoavel", Integer.class, null);
		tabelaFlex.addContainerProperty("Bom", Integer.class, null);
		tabelaFlex.addContainerProperty("Muito Bom", Integer.class, null);
		
		
		
		List<Flexibilidade> listaFlexibilidade = flexibilidadeService.buscarTodos(tabSexo);
		
		int linha = 1;
		for(Flexibilidade flexi:  listaFlexibilidade){
			tabelaFlex.addItem(new Object[]{flexi.getIdade(),flexi.getMuitoFraco(),flexi.getFraco(),flexi.getRazoavel(),flexi.getBom(),flexi.getMuitoBom()},linha);
			linha++;
		}
//		tabelaFlexibilidade =  carregarTabela(tabelaSexo);
		
		tabelaFlex.setPageLength(tabelaFlex.size());
		tabelaFlex.setSelectable(true);
		tabelaFlex.setImmediate(true);
		
		tabelaFlex.addListener(new ItemClickListener() {
			
			@Override
			public void itemClick(ItemClickEvent event) {
					if(tabSexo=='M'){
						flex.setTipo(tabSexo);
						
						Property itemProperty = event.getItem().getItemProperty("Idade");
						flex.setIdade(Integer.parseInt(itemProperty.getValue().toString()));
						
						itemProperty = event.getItem().getItemProperty("Muito Fraco");
						flex.setMuitoFraco(Integer.parseInt(itemProperty.getValue().toString()));
						
						itemProperty = event.getItem().getItemProperty("Fraco") ;
						flex.setFraco(Integer.parseInt(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Razoavel") ;
						flex.setRazoavel(Integer.parseInt(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Bom") ;
						flex.setBom(Integer.parseInt(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Muito Bom");
						flex.setMuitoBom(Integer.parseInt(itemProperty.getValue().toString()));
						
						tabelaSelecionada= true;
					}
					
					else if (tabSexo=='F'){
						
						flex.setTipo(tabSexo);
						
						Property itemProperty = event.getItem().getItemProperty("Idade");
						flex.setIdade(Integer.parseInt(itemProperty.getValue().toString()));
						
						itemProperty = event.getItem().getItemProperty("Muito Fraco");
						flex.setMuitoFraco(Integer.parseInt(itemProperty.getValue().toString()));
						
						itemProperty = event.getItem().getItemProperty("Fraco") ;
						flex.setFraco(Integer.parseInt(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Razoavel") ;
						flex.setRazoavel(Integer.parseInt(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Bom") ;
						flex.setBom(Integer.parseInt(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Muito Bom");
						flex.setMuitoBom(Integer.parseInt(itemProperty.getValue().toString()));
						
						tabelaSelecionada = true;
					}
				
			}
		});
		return tabelaFlex;
	}
	
}
