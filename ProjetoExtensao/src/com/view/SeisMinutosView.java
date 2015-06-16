package com.view;

import java.util.List;

import com.Service.SeisMinutosService;
import com.model.SeisMinutos;
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

public class SeisMinutosView extends HorizontalLayout{


	
	SeisMinutosService seisMinutosService;
	VerticalLayout vlSeisMinutosMasc;
	VerticalLayout vlSeisMinutosFem;
	
	
	char tabelaSexo;
	int linhaSelecionada;
	

	private Window janela;
	private Label menssagem;
	
	
	boolean tabelaSelecionada = false;
	
	
	public SeisMinutosView() {

		seisMinutosService = new SeisMinutosService();
		vlSeisMinutosMasc = new VerticalLayout();
		vlSeisMinutosFem = new VerticalLayout();
	
		this.setSpacing(true);
		
		Table tabelaSeisMinutos= null;
		 
		vlSeisMinutosMasc.setStyleName("SeisMinutos");
		vlSeisMinutosMasc.addComponent(montarlayout('M'));

		vlSeisMinutosFem.setStyleName("vlSeisMinutos");
		vlSeisMinutosFem.addComponent(montarlayout('F'));
		
		
		this.addComponent(vlSeisMinutosMasc);
		this.addComponent(vlSeisMinutosFem);
		
		
	}
	
	public VerticalLayout montarlayout(char tabelaSexo){
		
		VerticalLayout vlCorrida20Metros = new VerticalLayout();
		VerticalLayout vltabela = new VerticalLayout();
		Table tabelaSeisMinutos= new Table();
		SeisMinutos seisMinutos= new SeisMinutos();
		
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
			nomeTabela.setCaption("Seis Minutos Masculino");
		}
		
		else if(tabelaSexo=='F'){
			nomeTabela.setCaption("Seis MinutosFeminino");
		}
		
		tituloTab.addComponent(nomeTabela);
		vlcadastro.addComponent(idade);
		vlcadastro.addComponent(fraco);
		vlcadastro.addComponent(razoavel);
		vlcadastro.addComponent(bom);
		vlcadastro.addComponent(muitoBom);
		//parei aki
		
		Button salvar= new Button("Salvar",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				if(validarDados(idade,fraco,razoavel,bom,muitoBom)){
					
					if(tabelaSexo=='M'){
						seisMinutos.setTipo('M');
					}
					
					else if (tabelaSexo=='F'){
						seisMinutos.setTipo('F');
					}
					seisMinutos.setIdade(Integer.parseInt(idade.getValue().toString()));
					seisMinutos.setFraco(Integer.parseInt(fraco.getValue().toString()));
					seisMinutos.setRazoavel(Integer.parseInt(razoavel.getValue().toString()));
					seisMinutos.setBom(Integer.parseInt(bom.getValue().toString()));
					seisMinutos.setMuitoBom(Integer.parseInt(muitoBom.getValue().toString()));
					
					
					seisMinutosService.salvarSeisMinutos(seisMinutos);
												vltabela.removeAllComponents();
												vltabela.addComponent(carregarTabela(tabelaSexo, seisMinutos));
												
												
					limparDados(idade,fraco,razoavel,bom,muitoBom,seisMinutos);
					Notification.show("Cadastrado Com Sucesso!");
				}
			}
		});
//		-----------------------FIM do layout com campos de cadastro
		
		
		
//		------------------------------------------------ layout da tabela
//		
		tabelaSeisMinutos=  carregarTabela(tabelaSexo,seisMinutos);
		
		
		
		Button editar = new Button("Editar",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				if(tabelaSelecionada==true){
					idade.setValue(String.valueOf(seisMinutos.getIdade()));
					fraco.setValue(String.valueOf(seisMinutos.getFraco()));
					razoavel.setValue(String.valueOf(seisMinutos.getRazoavel()));
					bom.setValue(String.valueOf(seisMinutos.getBom()));
					muitoBom.setValue(String.valueOf(seisMinutos.getMuitoBom()));
					
				}
				
				else 
					Notification.show("Selecione um item da Tabela!");
				
				
				
			}
		});
		
		Button excluir = new Button("Exluir",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				if(tabelaSelecionada==true){
					layoutConfirmação(tabelaSexo,seisMinutos,vltabela);
					
				}
				
				else 
					Notification.show("Selecione um item da Tabela!");
				
				
			}
		});
		HorizontalLayout hlBotoes = new HorizontalLayout();
		hlBotoes.addComponents(editar,excluir);

		vltabela.addComponent(tabelaSeisMinutos);

//		---------adcionando na orddem ao Vertical layout que contem os campos de cadastros e a tabela
		vlCorrida20Metros.addComponent(tituloTab);
		vlCorrida20Metros.addComponent(vlcadastro);
		vlCorrida20Metros.addComponent(salvar);
		vlCorrida20Metros.addComponent(vltabela);
		vlCorrida20Metros.addComponent(hlBotoes);
		vlCorrida20Metros.setComponentAlignment(salvar, Alignment.MIDDLE_RIGHT);

		
		return vlCorrida20Metros;
	}
	

	
	public void layoutConfirmação(char tabSexo,SeisMinutos seisMinutos, VerticalLayout vltab) {

		Label mgs = new Label("Deseja realmente excluir os seguintes Dados?");
		janela = new Window(mgs.getValue().toString());
		janela.setClosable(false);

		VerticalLayout geral = new VerticalLayout();
		HorizontalLayout hlBotoes = new HorizontalLayout();
		HorizontalLayout hlmgs = new HorizontalLayout();

		Label lbDados = null;

		if(tabSexo=='M'){
			lbDados = new Label("idade :" +seisMinutos.getIdade());
		}
		
		else if(tabSexo=='F'){
			lbDados = new Label("idade :" +seisMinutos.getIdade());
		}
		
		
		
		

		Button sim = new Button("Sim", new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
					
				if(tabSexo=='M'){
					seisMinutosService = new SeisMinutosService();
					seisMinutosService.excluirSeisMinutos(seisMinutos);
					janela.close();
					vltab.removeAllComponents();
					vltab.addComponent(carregarTabela(tabSexo, seisMinutos));
					Notification.show("Excluido com Sucesso!");
				}
				
				else if(tabSexo=='F'){
					seisMinutosService = new SeisMinutosService();
					seisMinutosService.excluirSeisMinutos(seisMinutos);
					janela.close();
					vltab.removeAllComponents();
					vltab.addComponent(carregarTabela(tabSexo, seisMinutos));
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
	
				
	public void limparDados(TextField idade,TextField fraco,TextField razoavel,
	TextField bom,TextField muitoBom,SeisMinutos seisMinutos) {
		seisMinutos = new SeisMinutos();

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
			
	public Table carregarTabela(char tabSexo,SeisMinutos seisMinutos){
		
		
		Table tabelaSeisMinutos= new Table(); 

		if(tabSexo=='M'){
			tabelaSeisMinutos = new Table("Tabela de Corrida de 20 Metros Masculino");
			
		}
		else if(tabSexo=='F'){
			tabelaSeisMinutos= new Table("Tabela Corrida de 20 Metros Feminino");
		}
		
				
		tabelaSeisMinutos.addContainerProperty("Idade", Integer.class, null);
		tabelaSeisMinutos.addContainerProperty("Fraco", Integer.class, null);
		tabelaSeisMinutos.addContainerProperty("Razoavel", Integer.class, null);
		tabelaSeisMinutos.addContainerProperty("Bom", Integer.class, null);
		tabelaSeisMinutos.addContainerProperty("Muito Bom", Integer.class, null);
		
		
		
		List<SeisMinutos> listaSeisMinutos= seisMinutosService.buscarTodos(tabSexo);
		
		int linha = 1;
		for(SeisMinutos min:  listaSeisMinutos){
			tabelaSeisMinutos.addItem(new Object[]{min.getIdade(),min.getFraco(),min.getRazoavel(),min.getBom(),min.getMuitoBom()},linha);
			linha++;
		}
//		tabelaFlexibilidade =  carregarTabela(tabelaSexo);
		
		tabelaSeisMinutos.setPageLength(tabelaSeisMinutos.size());
		tabelaSeisMinutos.setSelectable(true);
		tabelaSeisMinutos.setImmediate(true);
		
		tabelaSeisMinutos.addListener(new ItemClickListener() {
			
			@Override
			public void itemClick(ItemClickEvent event) {
					if(tabSexo=='M'){
						seisMinutos.setTipo(tabSexo);
						
						Property itemProperty = event.getItem().getItemProperty("Idade");
						seisMinutos.setIdade(Integer.parseInt(itemProperty.getValue().toString()));
						
						itemProperty = event.getItem().getItemProperty("Fraco") ;
						seisMinutos.setFraco(Integer.parseInt(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Razoavel") ;
						seisMinutos.setRazoavel(Integer.parseInt(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Bom") ;
						seisMinutos.setBom(Integer.parseInt(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Muito Bom");
						seisMinutos.setMuitoBom(Integer.parseInt(itemProperty.getValue().toString()));
						
						tabelaSelecionada= true;
					}
					
					else if (tabSexo=='F'){
						
						seisMinutos.setTipo(tabSexo);
						
						Property itemProperty = event.getItem().getItemProperty("Idade");
						seisMinutos.setIdade(Integer.parseInt(itemProperty.getValue().toString()));
						
						itemProperty = event.getItem().getItemProperty("Fraco") ;
						seisMinutos.setFraco(Integer.parseInt(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Razoavel") ;
						seisMinutos.setRazoavel(Integer.parseInt(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Bom") ;
						seisMinutos.setBom(Integer.parseInt(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Muito Bom");
						seisMinutos.setMuitoBom(Integer.parseInt(itemProperty.getValue().toString()));
						
						tabelaSelecionada = true;
					}
				
			}
		});
		return tabelaSeisMinutos;
	}
}
