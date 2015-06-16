package com.view;

import java.util.List;

import com.Service.AbdominalService;
import com.Service.FlexibilidadeService;
import com.model.Abdominal;
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



public class AbdominalView extends HorizontalLayout {

	AbdominalService abdominalService;
	VerticalLayout vlAbdominalMasc;
	VerticalLayout vlAbdominalFem;
	
	
	char tabelaSexo;
	int linhaSelecionada;
	

	private Window janela;
	private Label menssagem;
//	Flexibilidade flexibilidade;
	
	
	boolean tabelaSelecionada = false;
	
	
	public AbdominalView() {

		abdominalService = new AbdominalService();
		vlAbdominalMasc = new VerticalLayout();
		vlAbdominalFem = new VerticalLayout();
	
		this.setSpacing(true);
		
		Table tabelaAbdominal = null;
		 
		vlAbdominalMasc.setStyleName("vlAbdominal");
		vlAbdominalMasc.addComponent(montarlayout('M'));

		vlAbdominalFem.setStyleName("vlAbdominal");
		vlAbdominalFem.addComponent(montarlayout('F'));
		
		
		this.addComponent(vlAbdominalMasc);
		this.addComponent(vlAbdominalFem);
		
		
	}
	
	public VerticalLayout montarlayout(char tabelaSexo){
		
		VerticalLayout vlAbdominal = new VerticalLayout();
		VerticalLayout vltabela = new VerticalLayout();
		Table tabelaAbdominal= new Table();
		Abdominal abdominal= new Abdominal();
		
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
			nomeTabela.setCaption("Abdominal Masculino");
		}
		
		else if(tabelaSexo=='F'){
			nomeTabela.setCaption("Abdominal Feminino");
		}
		
		tituloTab.addComponent(nomeTabela);
		vlcadastro.addComponent(idade);
		vlcadastro.addComponent(muitoFraco);
		vlcadastro.addComponent(fraco);
		vlcadastro.addComponent(razoavel);
		vlcadastro.addComponent(bom);
		vlcadastro.addComponent(muitoBom);
		//parei aki
		
		Button salvar= new Button("Salvar",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				if(validarDados(idade,muitoFraco,fraco,razoavel,bom,muitoBom)){
					
					if(tabelaSexo=='M'){
						abdominal.setTipo('M');
					}
					
					else if (tabelaSexo=='F'){
						abdominal.setTipo('F');
					}
					abdominal.setIdade(Integer.parseInt(idade.getValue().toString()));
					abdominal.setMuitoFraco(Integer.parseInt(muitoFraco.getValue().toString()));
					abdominal.setFraco(Integer.parseInt(fraco.getValue().toString()));
					abdominal.setRazoavel(Integer.parseInt(razoavel.getValue().toString()));
					abdominal.setBom(Integer.parseInt(bom.getValue().toString()));
					abdominal.setMuitoBom(Integer.parseInt(muitoBom.getValue().toString()));
					
					abdominalService.salvarAbdominal(abdominal);
												vltabela.removeAllComponents();
												vltabela.addComponent(carregarTabela(tabelaSexo, abdominal));
												
												
					limparDados(idade,muitoFraco,fraco,razoavel,bom,muitoBom,abdominal);
					Notification.show("Cadastrado Com Sucesso!");
				}
			}
		});
//		-----------------------FIM do layout com campos de cadastro
		
		
		
//		------------------------------------------------ layout da tabela
//		
		tabelaAbdominal=  carregarTabela(tabelaSexo,abdominal);
		
		
		
		Button editar = new Button("Editar",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				if(tabelaSelecionada==true){
					idade.setValue(String.valueOf(abdominal.getIdade()));
					muitoFraco.setValue(String.valueOf(abdominal.getMuitoFraco()));
					fraco.setValue(String.valueOf(abdominal.getFraco()));
					razoavel.setValue(String.valueOf(abdominal.getRazoavel()));
					bom.setValue(String.valueOf(abdominal.getBom()));
					muitoBom.setValue(String.valueOf(abdominal.getMuitoBom()));
					
				}
				
				else 
					Notification.show("Selecione um item da Tabela!");
				
				
				
			}
		});
		
		Button excluir = new Button("Exluir",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				if(tabelaSelecionada==true){
					layoutConfirmação(tabelaSexo,abdominal,vltabela);
					
				}
				
				else 
					Notification.show("Selecione um item da Tabela!");
				
				
			}
		});
		HorizontalLayout hlBotoes = new HorizontalLayout();
		hlBotoes.addComponents(editar,excluir);

		vltabela.addComponent(tabelaAbdominal);

//		---------adcionando na orddem ao Vertical layout que contem os campos de cadastros e a tabela
			vlAbdominal.addComponent(tituloTab);
			vlAbdominal.addComponent(vlcadastro);
			vlAbdominal.addComponent(salvar);
			vlAbdominal.addComponent(vltabela);
			vlAbdominal.addComponent(hlBotoes);
			vlAbdominal.setComponentAlignment(salvar, Alignment.MIDDLE_RIGHT);

		
		return vlAbdominal;
	}
	

	
	public void layoutConfirmação(char tabSexo,Abdominal abdom, VerticalLayout vltab) {

		Label mgs = new Label("Deseja realmente excluir os seguintes Dados?");
		janela = new Window(mgs.getValue().toString());
		janela.setClosable(false);

		VerticalLayout geral = new VerticalLayout();
		HorizontalLayout hlBotoes = new HorizontalLayout();
		HorizontalLayout hlmgs = new HorizontalLayout();

		Label lbDados = null;

		if(tabSexo=='M'){
			lbDados = new Label("idade :" +abdom.getIdade());
		}
		
		else if(tabSexo=='F'){
			lbDados = new Label("idade :" +abdom.getIdade());
		}
		
		
		
		

		Button sim = new Button("Sim", new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
					
				if(tabSexo=='M'){
					abdominalService = new AbdominalService();
					abdominalService.excluirFlexibilidade(abdom);
					janela.close();
					vltab.removeAllComponents();
					vltab.addComponent(carregarTabela(tabSexo, abdom));
					Notification.show("Excluido com Sucesso!");
				}
				
				else if(tabSexo=='F'){
					abdominalService = new AbdominalService();
					abdominalService.excluirFlexibilidade(abdom);
					janela.close();
					vltab.removeAllComponents();
					vltab.addComponent(carregarTabela(tabSexo, abdom));
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
	TextField bom,TextField muitoBom,Abdominal abdom) {
		abdom= new Abdominal();

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
			
	public Table carregarTabela(char tabSexo,Abdominal abdom){
		
		
		Table tabelaAbdom= new Table(); 

		if(tabSexo=='M'){
			tabelaAbdom= new Table("Tabela Abdominal Masculino");
			
		}
		else if(tabSexo=='F'){
			tabelaAbdom= new Table("Tabela Abdominal Feminino");
		}
		
				
		tabelaAbdom.addContainerProperty("Idade", Integer.class, null);
		tabelaAbdom.addContainerProperty("Muito Fraco", Integer.class, null);
		tabelaAbdom.addContainerProperty("Fraco", Integer.class, null);
		tabelaAbdom.addContainerProperty("Razoavel", Integer.class, null);
		tabelaAbdom.addContainerProperty("Bom", Integer.class, null);
		tabelaAbdom.addContainerProperty("Muito Bom", Integer.class, null);
		
		
		
		List<Abdominal> listaFlexibilidade = abdominalService.buscarTodos(tabSexo);
		
		int linha = 1;
		for(Abdominal flexi:  listaFlexibilidade){
			tabelaAbdom.addItem(new Object[]{flexi.getIdade(),flexi.getMuitoFraco(),flexi.getFraco(),flexi.getRazoavel(),flexi.getBom(),flexi.getMuitoBom()},linha);
			linha++;
		}
//		tabelaFlexibilidade =  carregarTabela(tabelaSexo);
		
		tabelaAbdom.setPageLength(tabelaAbdom.size());
		tabelaAbdom.setSelectable(true);
		tabelaAbdom.setImmediate(true);
		
		tabelaAbdom.addListener(new ItemClickListener() {
			
			@Override
			public void itemClick(ItemClickEvent event) {
					if(tabSexo=='M'){
						abdom.setTipo(tabSexo);
						
						Property itemProperty = event.getItem().getItemProperty("Idade");
						abdom.setIdade(Integer.parseInt(itemProperty.getValue().toString()));
						
						itemProperty = event.getItem().getItemProperty("Muito Fraco");
						abdom.setMuitoFraco(Integer.parseInt(itemProperty.getValue().toString()));
						
						itemProperty = event.getItem().getItemProperty("Fraco") ;
						abdom.setFraco(Integer.parseInt(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Razoavel") ;
						abdom.setRazoavel(Integer.parseInt(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Bom") ;
						abdom.setBom(Integer.parseInt(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Muito Bom");
						abdom.setMuitoBom(Integer.parseInt(itemProperty.getValue().toString()));
						
						tabelaSelecionada= true;
					}
					
					else if (tabSexo=='F'){
						
						abdom.setTipo(tabSexo);
						
						Property itemProperty = event.getItem().getItemProperty("Idade");
						abdom.setIdade(Integer.parseInt(itemProperty.getValue().toString()));
						
						itemProperty = event.getItem().getItemProperty("Muito Fraco");
						abdom.setMuitoFraco(Integer.parseInt(itemProperty.getValue().toString()));
						
						itemProperty = event.getItem().getItemProperty("Fraco") ;
						abdom.setFraco(Integer.parseInt(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Razoavel") ;
						abdom.setRazoavel(Integer.parseInt(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Bom") ;
						abdom.setBom(Integer.parseInt(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Muito Bom");
						abdom.setMuitoBom(Integer.parseInt(itemProperty.getValue().toString()));
						
						tabelaSelecionada = true;
					}
				
			}
		});
		return tabelaAbdom;
	}
	

}
