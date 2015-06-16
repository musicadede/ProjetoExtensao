package com.view;

import java.util.List;

import com.Service.SaltoDistanciaService;
import com.model.SaltoDistancia;
import com.vaadin.data.Property;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class SaltoDistanciaView extends HorizontalLayout{

	SaltoDistanciaService saltoDistanciaService;
	VerticalLayout vlSaltoDistanciaMasc;
	VerticalLayout vlSaltoDistanciaFem;
	
	
	char tabelaSexo;
	int linhaSelecionada;
	

	private Window janela;
	private Label menssagem;
	
	
	boolean tabelaSelecionada = false;
	
	
	public SaltoDistanciaView() {

		saltoDistanciaService = new SaltoDistanciaService();
		vlSaltoDistanciaMasc = new VerticalLayout();
		vlSaltoDistanciaFem = new VerticalLayout();
	
		this.setSpacing(true);
		
		Table tabelaSeisMinutos= null;
		 
		vlSaltoDistanciaMasc.setStyleName("SaltoDistancia");
		vlSaltoDistanciaMasc.addComponent(montarlayout('M'));

		vlSaltoDistanciaFem.setStyleName("vlSeisMinutos");
		vlSaltoDistanciaFem.addComponent(montarlayout('F'));
		
		
		this.addComponent(vlSaltoDistanciaMasc);
		this.addComponent(vlSaltoDistanciaFem);
		
		
	}
	
	public VerticalLayout montarlayout(char tabelaSexo){
		
		VerticalLayout vlSaltoDistancia = new VerticalLayout();
		VerticalLayout vltabela = new VerticalLayout();
		Table tabelaSaltoDistancia= new Table();
		SaltoDistancia saltoDistancia= new SaltoDistancia();
		
//-----------------------layout com campos de cadastro
		

		TextField idade = new TextField("Idade");
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
			nomeTabela.setCaption("Salto em DistanciaMasculino");
		}
		
		else if(tabelaSexo=='F'){
			nomeTabela.setCaption("Salto em Distancia Feminino");
		}
		
		tituloTab.addComponent(nomeTabela);
		vlcadastro.addComponent(idade);
		vlcadastro.addComponent(fraco);
		vlcadastro.addComponent(razoavel);
		vlcadastro.addComponent(bom);
		vlcadastro.addComponent(muitoBom);
		
		Button salvar= new Button("Salvar",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				if(validarDados(idade,fraco,razoavel,bom,muitoBom)){
					
					if(tabelaSexo=='M'){
						saltoDistancia.setTipo('M');
					}
					
					else if (tabelaSexo=='F'){
						saltoDistancia.setTipo('F');
					}
					saltoDistancia.setIdade(Integer.parseInt(idade.getValue().toString()));
					saltoDistancia.setFraco(Integer.parseInt(fraco.getValue().toString()));
					saltoDistancia.setRazoavel(Integer.parseInt(razoavel.getValue().toString()));
					saltoDistancia.setBom(Integer.parseInt(bom.getValue().toString()));
					saltoDistancia.setMuitoBom(Integer.parseInt(muitoBom.getValue().toString()));
					
					
					saltoDistanciaService.salvarSaltoDistancia(saltoDistancia);
												vltabela.removeAllComponents();
												vltabela.addComponent(carregarTabela(tabelaSexo, saltoDistancia));
												
												
					limparDados(idade,fraco,razoavel,bom,muitoBom,saltoDistancia);
					Notification.show("Cadastrado Com Sucesso!");
				}
			}
		});
//		-----------------------FIM do layout com campos de cadastro
		
		
		
//		------------------------------------------------ layout da tabela
//		
		tabelaSaltoDistancia=  carregarTabela(tabelaSexo,saltoDistancia);
		
		
		
		Button editar = new Button("Editar",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				if(tabelaSelecionada==true){
					idade.setValue(String.valueOf(saltoDistancia.getIdade()));
					fraco.setValue(String.valueOf(saltoDistancia.getFraco()));
					razoavel.setValue(String.valueOf(saltoDistancia.getRazoavel()));
					bom.setValue(String.valueOf(saltoDistancia.getBom()));
					muitoBom.setValue(String.valueOf(saltoDistancia.getMuitoBom()));
					
				}
				
				else 
					Notification.show("Selecione um item da Tabela!");
				
				
				
			}
		});
		
		Button excluir = new Button("Exluir",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				if(tabelaSelecionada==true){
					layoutConfirmação(tabelaSexo,saltoDistancia,vltabela);
					
				}
				
				else 
					Notification.show("Selecione um item da Tabela!");
				
				
			}
		});
		HorizontalLayout hlBotoes = new HorizontalLayout();
		hlBotoes.addComponents(editar,excluir);

		vltabela.addComponent(tabelaSaltoDistancia);

//		---------adcionando na orddem ao Vertical layout que contem os campos de cadastros e a tabela
		vlSaltoDistancia.addComponent(tituloTab);
		vlSaltoDistancia.addComponent(vlcadastro);
		vlSaltoDistancia.addComponent(salvar);
		vlSaltoDistancia.addComponent(vltabela);
		vlSaltoDistancia.addComponent(hlBotoes);
		vlSaltoDistancia.setComponentAlignment(salvar, Alignment.MIDDLE_RIGHT);

		
		return vlSaltoDistancia;
	}
	

	
	public void layoutConfirmação(char tabSexo,SaltoDistancia saltoDistancia, VerticalLayout vltab) {

		Label mgs = new Label("Deseja realmente excluir os seguintes Dados?");
		janela = new Window(mgs.getValue().toString());
		janela.setClosable(false);

		VerticalLayout geral = new VerticalLayout();
		HorizontalLayout hlBotoes = new HorizontalLayout();
		HorizontalLayout hlmgs = new HorizontalLayout();

		Label lbDados = null;

		if(tabSexo=='M'){
			lbDados = new Label("idade :" +saltoDistancia.getIdade());
		}
		
		else if(tabSexo=='F'){
			lbDados = new Label("idade :" +saltoDistancia.getIdade());
		}
		
		
		
		

		Button sim = new Button("Sim", new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
					
				
					saltoDistanciaService = new SaltoDistanciaService();
					saltoDistanciaService.excluirSaltoDistancia(saltoDistancia);
					janela.close();
					vltab.removeAllComponents();
					vltab.addComponent(carregarTabela(tabSexo, saltoDistancia));
					Notification.show("Excluido com Sucesso!");
				
				
				
				

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
	
				
	public void limparDados(TextField idade,TextField fraco,TextField razoavel,
	TextField bom,TextField muitoBom,SaltoDistancia saltoDistancia) {
		saltoDistancia = new SaltoDistancia();

		idade.setValue("");
		fraco.setValue("");
		razoavel.setValue("");
		bom.setValue("");
		muitoBom.setValue("");

	}

		
	public boolean validarDados(TextField idade,TextField fraco,TextField razoavel,
								TextField bom,TextField muitoBom){
		boolean resposta=false;
		
		if(idade.getValue()!=null||fraco.getValue()!=null||razoavel.getValue()!=null||
				bom.getValue()!=null||muitoBom.getValue()!=null){
		resposta =true;
		}
		
		else
			Notification.show("Informe os dados Corretamentes","Há campos que nao foram Preenchidos!",Notification.Type.WARNING_MESSAGE);
		
		return resposta;
		
	}
			
	public Table carregarTabela(char tabSexo,SaltoDistancia saltoDistancia){
		
		
		Table tabelaSaltoDistancia= new Table(); 

		if(tabSexo=='M'){
			tabelaSaltoDistancia = new Table("Tabela de Salto Distancia Masculino");
			
		}
		else if(tabSexo=='F'){
			tabelaSaltoDistancia= new Table("Tabela  de Salto Distancia Feminino");
		}
		
				
		tabelaSaltoDistancia.addContainerProperty("Idade", Integer.class, null);
		tabelaSaltoDistancia.addContainerProperty("Fraco", Integer.class, null);
		tabelaSaltoDistancia.addContainerProperty("Razoavel", Integer.class, null);
		tabelaSaltoDistancia.addContainerProperty("Bom", Integer.class, null);
		tabelaSaltoDistancia.addContainerProperty("Muito Bom", Integer.class, null);
		
		
		
		List<SaltoDistancia> listaSaltoDistancia= saltoDistanciaService.buscarTodos(tabSexo);
		
		int linha = 1;
		for(SaltoDistancia salto:  listaSaltoDistancia){
			tabelaSaltoDistancia.addItem(new Object[]{salto.getIdade(),salto.getFraco(),salto.getRazoavel(),salto.getBom(),salto.getMuitoBom()},linha);
			linha++;
		}
//		tabelaFlexibilidade =  carregarTabela(tabelaSexo);
		
		tabelaSaltoDistancia.setPageLength(tabelaSaltoDistancia.size());
		tabelaSaltoDistancia.setSelectable(true);
		tabelaSaltoDistancia.setImmediate(true);
		
		tabelaSaltoDistancia.addListener(new ItemClickListener() {
			
			@Override
			public void itemClick(ItemClickEvent event) {
					if(tabSexo=='M'){
						saltoDistancia.setTipo(tabSexo);
						
						Property itemProperty = event.getItem().getItemProperty("Idade");
						saltoDistancia.setIdade(Integer.parseInt(itemProperty.getValue().toString()));
						
						itemProperty = event.getItem().getItemProperty("Fraco") ;
						saltoDistancia.setFraco(Integer.parseInt(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Razoavel") ;
						saltoDistancia.setRazoavel(Integer.parseInt(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Bom") ;
						saltoDistancia.setBom(Integer.parseInt(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Muito Bom");
						saltoDistancia.setMuitoBom(Integer.parseInt(itemProperty.getValue().toString()));
						
						tabelaSelecionada= true;
					}
					
					else if (tabSexo=='F'){
						
						saltoDistancia.setTipo(tabSexo);
						
						Property itemProperty = event.getItem().getItemProperty("Idade");
						saltoDistancia.setIdade(Integer.parseInt(itemProperty.getValue().toString()));
						
						itemProperty = event.getItem().getItemProperty("Fraco") ;
						saltoDistancia.setFraco(Integer.parseInt(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Razoavel") ;
						saltoDistancia.setRazoavel(Integer.parseInt(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Bom") ;
						saltoDistancia.setBom(Integer.parseInt(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Muito Bom");
						saltoDistancia.setMuitoBom(Integer.parseInt(itemProperty.getValue().toString()));
						
						tabelaSelecionada = true;
					}
				
			}
		});
		return tabelaSaltoDistancia;
	}
}
