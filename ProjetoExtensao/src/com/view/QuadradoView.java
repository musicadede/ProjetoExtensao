package com.view;

import java.util.List;

import com.Service.QuadradoService;
import com.model.Quadrado;
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

public class QuadradoView extends HorizontalLayout {

	QuadradoService quadradoService;
	VerticalLayout vlQuadradoMasc;
	VerticalLayout vlQuadradoFem;
	
	
	char tabelaSexo;
	int linhaSelecionada;
	

	private Window janela;
	private Label menssagem;
	
	
	boolean tabelaSelecionada = false;
	
	
	public QuadradoView() {

		quadradoService = new QuadradoService();
		vlQuadradoMasc = new VerticalLayout();
		vlQuadradoFem = new VerticalLayout();
	
		this.setSpacing(true);
		
		Table tabelaQuadrado= null;
		 
		vlQuadradoMasc.setStyleName("vlQuadrado");
		vlQuadradoMasc.addComponent(montarlayout('M'));

		vlQuadradoFem.setStyleName("vlQuadrado");
		vlQuadradoFem.addComponent(montarlayout('F'));
		
		
		this.addComponent(vlQuadradoMasc);
		this.addComponent(vlQuadradoFem);
		
		
	}
	
	public VerticalLayout montarlayout(char tabelaSexo){
		
		VerticalLayout vlQuadrado= new VerticalLayout();
		VerticalLayout vltabela = new VerticalLayout();
		Table tabelaQuadrado= new Table();
		Quadrado quadrado= new Quadrado();
		
//-----------------------layout com campos de cadastro
		

		TextField idade = new TextField("Idade");
		TextField excelente = new TextField("Excelente");
		TextField muitoBom= new TextField("Muito Bom");
		TextField bom = new TextField("Bom");
		TextField razoavel = new TextField("Razoavel");
		
		final Label nomeTabela  = new Label();
		
		FormLayout geral = new FormLayout();
		geral.setSpacing(true);
		HorizontalLayout tituloTab = new HorizontalLayout();
		VerticalLayout vlcadastro = new VerticalLayout();
		vlcadastro.setSpacing(true);	
		
		if(tabelaSexo=='M'){
			nomeTabela.setCaption("Quadrado Masculino");
		}
		
		else if(tabelaSexo=='F'){
			nomeTabela.setCaption("Quadrado Feminino");
		}
		
		tituloTab.addComponent(nomeTabela);
		vlcadastro.addComponent(idade);
		vlcadastro.addComponent(excelente);
		vlcadastro.addComponent(muitoBom);
		vlcadastro.addComponent(bom);
		vlcadastro.addComponent(razoavel);
		
		Button salvar= new Button("Salvar",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				if(validarDados(idade,excelente,muitoBom,bom,razoavel)){
					
					if(tabelaSexo=='M'){
						quadrado.setTipo('M');
					}
					
					else if (tabelaSexo=='F'){
						quadrado.setTipo('F');
					}
					quadrado.setIdade(Integer.parseInt(idade.getValue().toString()));
					quadrado.setExcelente(Double.parseDouble(excelente.getValue().toString()));
					quadrado.setMuitoBom(Double.parseDouble(muitoBom.getValue().toString()));
					quadrado.setBom(Double.parseDouble(bom.getValue().toString()));
					quadrado.setRazoavel(Double.parseDouble(razoavel.getValue().toString()));
					
					
					quadradoService.salvarQuadrado(quadrado);
												vltabela.removeAllComponents();
												vltabela.addComponent(carregarTabela(tabelaSexo, quadrado));
												
												
					limparDados(idade,excelente,muitoBom,bom,razoavel,quadrado);
					Notification.show("Cadastrado Com Sucesso!");
				}
			}
		});
//		-----------------------FIM do layout com campos de cadastro
		
		
		
//		------------------------------------------------ layout da tabela
//		
		tabelaQuadrado=  carregarTabela(tabelaSexo,quadrado);
		
		
		
		Button editar = new Button("Editar",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				if(tabelaSelecionada==true){
					idade.setValue(String.valueOf(quadrado.getIdade()));
					excelente.setValue(String.valueOf(quadrado.getExcelente()));
					muitoBom.setValue(String.valueOf(quadrado.getMuitoBom()));
					bom.setValue(String.valueOf(quadrado.getBom()));
					razoavel.setValue(String.valueOf(quadrado.getRazoavel()));
					
				}
				
				else 
					Notification.show("Selecione um item da Tabela!");
				
				
				
			}
		});
		
		Button excluir = new Button("Exluir",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				if(tabelaSelecionada==true){
					layoutConfirmação(tabelaSexo,quadrado,vltabela);
					
				}
				
				else 
					Notification.show("Selecione um item da Tabela!");
				
				
			}
		});
		HorizontalLayout hlBotoes = new HorizontalLayout();
		hlBotoes.addComponents(editar,excluir);

		vltabela.addComponent(tabelaQuadrado);

//		---------adcionando na orddem ao Vertical layout que contem os campos de cadastros e a tabela
		vlQuadrado.addComponent(tituloTab);
		vlQuadrado.addComponent(vlcadastro);
		vlQuadrado.addComponent(salvar);
		vlQuadrado.addComponent(vltabela);
		vlQuadrado.addComponent(hlBotoes);
		vlQuadrado.setComponentAlignment(salvar, Alignment.MIDDLE_RIGHT);

		
		return vlQuadrado;
	}
	

	
	public void layoutConfirmação(char tabSexo,Quadrado quadrado, VerticalLayout vltab) {

		Label mgs = new Label("Deseja realmente excluir os seguintes Dados?");
		janela = new Window(mgs.getValue().toString());
		janela.setClosable(false);

		VerticalLayout geral = new VerticalLayout();
		HorizontalLayout hlBotoes = new HorizontalLayout();
		HorizontalLayout hlmgs = new HorizontalLayout();

		Label lbDados = null;

		if(tabSexo=='M'){
			lbDados = new Label("idade :" +quadrado.getIdade());
		}
		
		else if(tabSexo=='F'){
			lbDados = new Label("idade :" +quadrado.getIdade());
		}
		
		
		
		

		Button sim = new Button("Sim", new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
					
				
				quadradoService = new QuadradoService();
				quadradoService.excluirQuadrado(quadrado);
					janela.close();
					vltab.removeAllComponents();
					vltab.addComponent(carregarTabela(tabSexo, quadrado));
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
	
				
	public void limparDados(TextField idade,TextField excelente,TextField muitoBom,
	TextField bom,TextField razoavel,Quadrado quadrado) {
		quadrado = new Quadrado();

		idade.setValue("");
		excelente.setValue("");
		muitoBom.setValue("");
		bom.setValue("");
		razoavel.setValue("");

	}

		
	public boolean validarDados(TextField idade,TextField excelente,TextField muitoBom,
								TextField bom,TextField razoavel){
		boolean resposta=false;
		
		if(idade.getValue()!=null||excelente.getValue()!=null||muitoBom.getValue()!=null||
				bom.getValue()!=null||razoavel.getValue()!=null){
		resposta =true;
		}
		
		else
			Notification.show("Informe os dados Corretamentes","Há campos que nao foram Preenchidos!",Notification.Type.WARNING_MESSAGE);
		
		return resposta;
		
	}
			
	public Table carregarTabela(char tabSexo,Quadrado quadrado){
		
		
		Table tabelaQuadrado= new Table(); 

		if(tabSexo=='M'){
			tabelaQuadrado= new Table("Tabela Quadrado Masculino");
			
		}
		else if(tabSexo=='F'){
			tabelaQuadrado= new Table("Tabela  Quadrado Feminino");
		}
		
				
		tabelaQuadrado.addContainerProperty("Idade", Integer.class, null);
		tabelaQuadrado.addContainerProperty("Excelente", Double.class, null);
		tabelaQuadrado.addContainerProperty("Muito Bom", Double.class, null);
		tabelaQuadrado.addContainerProperty("Bom", Double.class, null);
		tabelaQuadrado.addContainerProperty("Razoavel", Double.class, null);
		
		
		
		List<Quadrado> listaQuadrado= quadradoService.buscarTodos(tabSexo);
		
		int linha = 1;
		for(Quadrado qua:  listaQuadrado){
			tabelaQuadrado.addItem(new Object[]{qua.getIdade(),qua.getExcelente(),qua.getMuitoBom(),qua.getBom(),qua.getRazoavel()},linha);
			linha++;
		}
//		tabelaFlexibilidade =  carregarTabela(tabelaSexo);
		
		tabelaQuadrado.setPageLength(tabelaQuadrado.size());
		tabelaQuadrado.setSelectable(true);
		tabelaQuadrado.setImmediate(true);
		
		tabelaQuadrado.addListener(new ItemClickListener() {
			
			@Override
			public void itemClick(ItemClickEvent event) {
					if(tabSexo=='M'){
						quadrado.setTipo(tabSexo);
						
						Property itemProperty = event.getItem().getItemProperty("Idade");
						quadrado.setIdade(Integer.parseInt(itemProperty.getValue().toString()));
						
						itemProperty = event.getItem().getItemProperty("Excelente") ;
						quadrado.setExcelente(Double.parseDouble(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Muito Bom") ;
						quadrado.setMuitoBom(Double.parseDouble(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Bom") ;
						quadrado.setBom(Double.parseDouble(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Razoavel");
						quadrado.setRazoavel(Double.parseDouble(itemProperty.getValue().toString()));
						
						tabelaSelecionada= true;
					}
					
					else if (tabSexo=='F'){
						
						quadrado.setTipo(tabSexo);
						
						Property itemProperty = event.getItem().getItemProperty("Idade");
						quadrado.setIdade(Integer.parseInt(itemProperty.getValue().toString()));
						
						itemProperty = event.getItem().getItemProperty("Excelente") ;
						quadrado.setExcelente(Double.parseDouble(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Muito Bom") ;
						quadrado.setMuitoBom(Double.parseDouble(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Bom") ;
						quadrado.setBom(Double.parseDouble(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Razoavel");
						quadrado.setRazoavel(Double.parseDouble(itemProperty.getValue().toString()));
						
						tabelaSelecionada = true;
					}
				
			}
		});
		return tabelaQuadrado;
	}
}
