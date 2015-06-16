package com.view;

import java.util.List;

import com.Service.Corrida20MetrosService;
import com.model.Corrida20Metros;
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

public class Corrida20MetrosView extends HorizontalLayout{

	
	Corrida20MetrosService corrida20MetrosService;
	VerticalLayout vlCorrida20MetrosMasc;
	VerticalLayout vlCorrida20MetrosFem;
	
	
	char tabelaSexo;
	int linhaSelecionada;
	

	private Window janela;
	private Label menssagem;
	
	
	boolean tabelaSelecionada = false;
	
	
	public Corrida20MetrosView() {

		corrida20MetrosService = new Corrida20MetrosService();
		vlCorrida20MetrosMasc = new VerticalLayout();
		vlCorrida20MetrosFem = new VerticalLayout();
	
		this.setSpacing(true);
		
		Table tabelaCorrida20Metros = null;
		 
		vlCorrida20MetrosMasc.setStyleName("vlCorrida20Metros");
		vlCorrida20MetrosMasc.addComponent(montarlayout('M'));

		vlCorrida20MetrosFem.setStyleName("vlCorrida20Metros");
		vlCorrida20MetrosFem.addComponent(montarlayout('F'));
		
		
		this.addComponent(vlCorrida20MetrosMasc);
		this.addComponent(vlCorrida20MetrosFem);
		
		
	}
	
	public VerticalLayout montarlayout(char tabelaSexo){
		
		VerticalLayout vlCorrida20Metros = new VerticalLayout();
		VerticalLayout vltabela = new VerticalLayout();
		Table tabelaCorrida20Metros= new Table();
		Corrida20Metros corrida20Metros = new Corrida20Metros();
		
//-----------------------layout com campos de cadastro
		

		TextField idade = new TextField("Idade");
		TextField fraco = new TextField("Fraco");
		TextField razoavel = new TextField("Razoavel");
		TextField bom = new TextField("Bom");
		TextField muitoBom = new TextField("Muito Bom");
		TextField excelente = new TextField("Excelente");
		
		final Label nomeTabela  = new Label();
		
		FormLayout geral = new FormLayout();
		geral.setSpacing(true);
		HorizontalLayout tituloTab = new HorizontalLayout();
		VerticalLayout vlcadastro = new VerticalLayout();
		vlcadastro.setSpacing(true);	
		
		if(tabelaSexo=='M'){
			nomeTabela.setCaption("Corrida de 20 Metros Masculino");
		}
		
		else if(tabelaSexo=='F'){
			nomeTabela.setCaption("Corrida de 20 Metros Feminino");
		}
		
		tituloTab.addComponent(nomeTabela);
		vlcadastro.addComponent(idade);
		vlcadastro.addComponent(fraco);
		vlcadastro.addComponent(razoavel);
		vlcadastro.addComponent(bom);
		vlcadastro.addComponent(muitoBom);
		vlcadastro.addComponent(excelente);
		//parei aki
		
		Button salvar= new Button("Salvar",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				if(validarDados(idade,fraco,razoavel,bom,muitoBom,excelente)){
					
					if(tabelaSexo=='M'){
						corrida20Metros.setTipo('M');
					}
					
					else if (tabelaSexo=='F'){
						corrida20Metros.setTipo('F');
					}
					corrida20Metros.setIdade(Integer.parseInt(idade.getValue().toString()));
					corrida20Metros.setFraco(Double.parseDouble(fraco.getValue().toString()));
					corrida20Metros.setRazoavel(Double.parseDouble(razoavel.getValue().toString()));
					corrida20Metros.setBom(Double.parseDouble(bom.getValue().toString()));
					corrida20Metros.setMuitoBom(Double.parseDouble(muitoBom.getValue().toString()));
					corrida20Metros.setExcelente(Double.parseDouble(excelente.getValue().toString()));
					
					corrida20MetrosService.salvarCorrida20Metros(corrida20Metros);
												vltabela.removeAllComponents();
												vltabela.addComponent(carregarTabela(tabelaSexo, corrida20Metros));
												
												
					limparDados(idade,fraco,razoavel,bom,muitoBom,excelente,corrida20Metros);
					Notification.show("Cadastrado Com Sucesso!");
				}
			}
		});
//		-----------------------FIM do layout com campos de cadastro
		
		
		
//		------------------------------------------------ layout da tabela
//		
		tabelaCorrida20Metros =  carregarTabela(tabelaSexo,corrida20Metros);
		
		
		
		Button editar = new Button("Editar",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				if(tabelaSelecionada==true){
					idade.setValue(String.valueOf(corrida20Metros.getIdade()));
					fraco.setValue(String.valueOf(corrida20Metros.getFraco()));
					razoavel.setValue(String.valueOf(corrida20Metros.getRazoavel()));
					bom.setValue(String.valueOf(corrida20Metros.getBom()));
					muitoBom.setValue(String.valueOf(corrida20Metros.getMuitoBom()));
					excelente.setValue(String.valueOf(corrida20Metros.getExcelente()));
					
				}
				
				else 
					Notification.show("Selecione um item da Tabela!");
				
				
				
			}
		});
		
		Button excluir = new Button("Exluir",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				if(tabelaSelecionada==true){
					layoutConfirmação(tabelaSexo,corrida20Metros,vltabela);
					
				}
				
				else 
					Notification.show("Selecione um item da Tabela!");
				
				
			}
		});
		HorizontalLayout hlBotoes = new HorizontalLayout();
		hlBotoes.addComponents(editar,excluir);

		vltabela.addComponent(tabelaCorrida20Metros);

//		---------adcionando na orddem ao Vertical layout que contem os campos de cadastros e a tabela
		vlCorrida20Metros.addComponent(tituloTab);
		vlCorrida20Metros.addComponent(vlcadastro);
		vlCorrida20Metros.addComponent(salvar);
		vlCorrida20Metros.addComponent(vltabela);
		vlCorrida20Metros.addComponent(hlBotoes);
		vlCorrida20Metros.setComponentAlignment(salvar, Alignment.MIDDLE_RIGHT);

		
		return vlCorrida20Metros;
	}
	

	
	public void layoutConfirmação(char tabSexo,Corrida20Metros corrida20Metros, VerticalLayout vltab) {

		Label mgs = new Label("Deseja realmente excluir os seguintes Dados?");
		janela = new Window(mgs.getValue().toString());
		janela.setClosable(false);

		VerticalLayout geral = new VerticalLayout();
		HorizontalLayout hlBotoes = new HorizontalLayout();
		HorizontalLayout hlmgs = new HorizontalLayout();

		Label lbDados = null;

		if(tabSexo=='M'){
			lbDados = new Label("idade :" +corrida20Metros.getIdade());
		}
		
		else if(tabSexo=='F'){
			lbDados = new Label("idade :" +corrida20Metros.getIdade());
		}
		
		
		
		

		Button sim = new Button("Sim", new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
					
				if(tabSexo=='M'){
					corrida20MetrosService = new Corrida20MetrosService();
					corrida20MetrosService.excluirCorrida20Metros(corrida20Metros);
					janela.close();
					vltab.removeAllComponents();
					vltab.addComponent(carregarTabela(tabSexo, corrida20Metros));
					Notification.show("Excluido com Sucesso!");
				}
				
				else if(tabSexo=='F'){
					corrida20MetrosService = new Corrida20MetrosService();
					corrida20MetrosService.excluirCorrida20Metros(corrida20Metros);
					janela.close();
					vltab.removeAllComponents();
					vltab.addComponent(carregarTabela(tabSexo, corrida20Metros));
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
	TextField bom,TextField muitoBom,Corrida20Metros corrida20Metros) {
		corrida20Metros = new Corrida20Metros();

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
			
	public Table carregarTabela(char tabSexo,Corrida20Metros corrida20Metros){
		
		
		Table tabelaCorrida20Metros= new Table(); 

		if(tabSexo=='M'){
			tabelaCorrida20Metros= new Table("Tabela de Corrida de 20 Metros Masculino");
			
		}
		else if(tabSexo=='F'){
			tabelaCorrida20Metros= new Table("Tabela Corrida de 20 Metros Feminino");
		}
		
				
		tabelaCorrida20Metros.addContainerProperty("Idade", Integer.class, null);
		tabelaCorrida20Metros.addContainerProperty("Muito Fraco", Double.class, null);
		tabelaCorrida20Metros.addContainerProperty("Fraco", Double.class, null);
		tabelaCorrida20Metros.addContainerProperty("Razoavel", Double.class, null);
		tabelaCorrida20Metros.addContainerProperty("Bom", Double.class, null);
		tabelaCorrida20Metros.addContainerProperty("Muito Bom", Double.class, null);
		
		
		
		List<Corrida20Metros> listaCorrida20Metros = corrida20MetrosService.buscarTodos(tabSexo);
		
		int linha = 1;
		for(Corrida20Metros corrida:  listaCorrida20Metros){
			tabelaCorrida20Metros.addItem(new Object[]{corrida.getIdade(),corrida.getFraco(),corrida.getRazoavel(),corrida.getBom(),corrida.getMuitoBom(), corrida.getExcelente()},linha);
			linha++;
		}
//		tabelaFlexibilidade =  carregarTabela(tabelaSexo);
		
		tabelaCorrida20Metros.setPageLength(tabelaCorrida20Metros.size());
		tabelaCorrida20Metros.setSelectable(true);
		tabelaCorrida20Metros.setImmediate(true);
		
		tabelaCorrida20Metros.addListener(new ItemClickListener() {
			
			@Override
			public void itemClick(ItemClickEvent event) {
					if(tabSexo=='M'){
						corrida20Metros.setTipo(tabSexo);
						
						Property itemProperty = event.getItem().getItemProperty("Idade");
						corrida20Metros.setIdade(Integer.parseInt(itemProperty.getValue().toString()));
						
						itemProperty = event.getItem().getItemProperty("Fraco") ;
						corrida20Metros.setFraco(Double.parseDouble(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Razoavel") ;
						corrida20Metros.setRazoavel(Double.parseDouble(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Bom") ;
						corrida20Metros.setBom(Double.parseDouble(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Muito Bom");
						corrida20Metros.setMuitoBom(Double.parseDouble(itemProperty.getValue().toString()));
						
						itemProperty = event.getItem().getItemProperty("Muito Bom");
						corrida20Metros.setExcelente(Double.parseDouble(itemProperty.getValue().toString()));
						
						tabelaSelecionada= true;
					}
					
					else if (tabSexo=='F'){
						
						corrida20Metros.setTipo(tabSexo);
						
						Property itemProperty = event.getItem().getItemProperty("Idade");
						corrida20Metros.setIdade(Integer.parseInt(itemProperty.getValue().toString()));
						
						itemProperty = event.getItem().getItemProperty("Fraco") ;
						corrida20Metros.setFraco(Double.parseDouble(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Razoavel") ;
						corrida20Metros.setRazoavel(Double.parseDouble(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Bom") ;
						corrida20Metros.setBom(Double.parseDouble(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Muito Bom");
						corrida20Metros.setMuitoBom(Double.parseDouble(itemProperty.getValue().toString()));
						
						itemProperty = event.getItem().getItemProperty("Muito Bom");
						corrida20Metros.setExcelente(Double.parseDouble(itemProperty.getValue().toString()));
						
						tabelaSelecionada = true;
					}
				
			}
		});
		return tabelaCorrida20Metros;
	}
}
