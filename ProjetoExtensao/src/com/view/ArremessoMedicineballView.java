package com.view;

import java.util.List;

import com.Service.ArremessoMedicineballService;
import com.model.ArremessoMedicineball;
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

public class ArremessoMedicineballView extends HorizontalLayout{

	ArremessoMedicineballService arMedicineballService;
	VerticalLayout vlArremessoMedicineballMasc;
	VerticalLayout vlArremessoMedicineballFem;
	
	
	char tabelaSexo;
	int linhaSelecionada;
	

	private Window janela;
	private Label menssagem;
//	Flexibilidade flexibilidade;
	
	
	boolean tabelaSelecionada = false;
	
	
	public ArremessoMedicineballView() {

		arMedicineballService = new ArremessoMedicineballService();
		vlArremessoMedicineballMasc = new VerticalLayout();
		vlArremessoMedicineballFem = new VerticalLayout();
	
		this.setSpacing(true);
		
		Table tabelaAbdominal = null;
		 
		vlArremessoMedicineballMasc.setStyleName("vlArremessoMedicineball");
		vlArremessoMedicineballMasc.addComponent(montarlayout('M'));

		vlArremessoMedicineballFem.setStyleName("vlArremessoMedicineball");
		vlArremessoMedicineballFem.addComponent(montarlayout('F'));
		
		
		this.addComponent(vlArremessoMedicineballMasc);
		this.addComponent(vlArremessoMedicineballFem);
		
		
	}
	
	public VerticalLayout montarlayout(char tabelaSexo){
		
		VerticalLayout vlArremessoMedicineball = new VerticalLayout();
		VerticalLayout vltabela = new VerticalLayout();
		Table tabelaArremessoMedicineball= new Table();
		ArremessoMedicineball arMedicineball = new ArremessoMedicineball();
		
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
			nomeTabela.setCaption("Arremesso de Medicineball Masculino");
		}
		
		else if(tabelaSexo=='F'){
			nomeTabela.setCaption("Arremesso de Medicineball Feminino");
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
						arMedicineball.setTipo('M');
					}
					
					else if (tabelaSexo=='F'){
						arMedicineball.setTipo('F');
					}
					arMedicineball.setIdade(Integer.parseInt(idade.getValue().toString()));
					arMedicineball.setMuitoFraco(Integer.parseInt(muitoFraco.getValue().toString()));
					arMedicineball.setFraco(Integer.parseInt(fraco.getValue().toString()));
					arMedicineball.setRazoavel(Integer.parseInt(razoavel.getValue().toString()));
					arMedicineball.setBom(Integer.parseInt(bom.getValue().toString()));
					arMedicineball.setMuitoBom(Integer.parseInt(muitoBom.getValue().toString()));
					
					arMedicineballService.salvarArremessoMedicineball(arMedicineball);
												vltabela.removeAllComponents();
												vltabela.addComponent(carregarTabela(tabelaSexo, arMedicineball));
												
												
					limparDados(idade,muitoFraco,fraco,razoavel,bom,muitoBom,arMedicineball);
					Notification.show("Cadastrado Com Sucesso!");
				}
			}
		});
//		-----------------------FIM do layout com campos de cadastro
		
		
		
//		------------------------------------------------ layout da tabela
//		
		tabelaArremessoMedicineball =  carregarTabela(tabelaSexo,arMedicineball);
		
		
		
		Button editar = new Button("Editar",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				if(tabelaSelecionada==true){
					idade.setValue(String.valueOf(arMedicineball.getIdade()));
					muitoFraco.setValue(String.valueOf(arMedicineball.getMuitoFraco()));
					fraco.setValue(String.valueOf(arMedicineball.getFraco()));
					razoavel.setValue(String.valueOf(arMedicineball.getRazoavel()));
					bom.setValue(String.valueOf(arMedicineball.getBom()));
					muitoBom.setValue(String.valueOf(arMedicineball.getMuitoBom()));
					
				}
				
				else 
					Notification.show("Selecione um item da Tabela!");
				
				
				
			}
		});
		
		Button excluir = new Button("Exluir",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				if(tabelaSelecionada==true){
					layoutConfirmação(tabelaSexo,arMedicineball,vltabela);
					
				}
				
				else 
					Notification.show("Selecione um item da Tabela!");
				
				
			}
		});
		HorizontalLayout hlBotoes = new HorizontalLayout();
		hlBotoes.addComponents(editar,excluir);

		vltabela.addComponent(tabelaArremessoMedicineball);

//		---------adcionando na orddem ao Vertical layout que contem os campos de cadastros e a tabela
			vlArremessoMedicineball.addComponent(tituloTab);
			vlArremessoMedicineball.addComponent(vlcadastro);
			vlArremessoMedicineball.addComponent(salvar);
			vlArremessoMedicineball.addComponent(vltabela);
			vlArremessoMedicineball.addComponent(hlBotoes);
			vlArremessoMedicineball.setComponentAlignment(salvar, Alignment.MIDDLE_RIGHT);

		
		return vlArremessoMedicineball;
	}
	

	
	public void layoutConfirmação(char tabSexo,ArremessoMedicineball arMedicineball, VerticalLayout vltab) {

		Label mgs = new Label("Deseja realmente excluir os seguintes Dados?");
		janela = new Window(mgs.getValue().toString());
		janela.setClosable(false);

		VerticalLayout geral = new VerticalLayout();
		HorizontalLayout hlBotoes = new HorizontalLayout();
		HorizontalLayout hlmgs = new HorizontalLayout();

		Label lbDados = null;

		if(tabSexo=='M'){
			lbDados = new Label("idade :" +arMedicineball.getIdade());
		}
		
		else if(tabSexo=='F'){
			lbDados = new Label("idade :" +arMedicineball.getIdade());
		}
		
		
		
		

		Button sim = new Button("Sim", new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
					
				if(tabSexo=='M'){
					arMedicineballService = new ArremessoMedicineballService();
					arMedicineballService.excluirArremessoMedicineball(arMedicineball);
					janela.close();
					vltab.removeAllComponents();
					vltab.addComponent(carregarTabela(tabSexo, arMedicineball));
					Notification.show("Excluido com Sucesso!");
				}
				
				else if(tabSexo=='F'){
					arMedicineballService = new ArremessoMedicineballService();
					arMedicineballService.excluirArremessoMedicineball(arMedicineball);
					janela.close();
					vltab.removeAllComponents();
					vltab.addComponent(carregarTabela(tabSexo, arMedicineball));
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
	TextField bom,TextField muitoBom,ArremessoMedicineball arMedicineball) {
		arMedicineball = new ArremessoMedicineball();

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
			
	public Table carregarTabela(char tabSexo,ArremessoMedicineball arMedicineball){
		
		
		Table tabelaArMedicineball = new Table(); 

		if(tabSexo=='M'){
			tabelaArMedicineball= new Table("Tabela Arremesso de Medicineball Masculino");
			
		}
		else if(tabSexo=='F'){
			tabelaArMedicineball= new Table("Tabela Arremesso de Medicineball Feminino");
		}
		
				
		tabelaArMedicineball.addContainerProperty("Idade", Integer.class, null);
		tabelaArMedicineball.addContainerProperty("Muito Fraco", Integer.class, null);
		tabelaArMedicineball.addContainerProperty("Fraco", Integer.class, null);
		tabelaArMedicineball.addContainerProperty("Razoavel", Integer.class, null);
		tabelaArMedicineball.addContainerProperty("Bom", Integer.class, null);
		tabelaArMedicineball.addContainerProperty("Muito Bom", Integer.class, null);
		
		
		
		List<ArremessoMedicineball> listaArMedicineball = arMedicineballService.buscarTodos(tabSexo);
		
		int linha = 1;
		for(ArremessoMedicineball arMed:  listaArMedicineball){
			tabelaArMedicineball.addItem(new Object[]{arMed.getIdade(),arMed.getMuitoFraco(),arMed.getFraco(),arMed.getRazoavel(),arMed.getBom(),arMed.getMuitoBom()},linha);
			linha++;
		}
//		tabelaFlexibilidade =  carregarTabela(tabelaSexo);
		
		tabelaArMedicineball.setPageLength(tabelaArMedicineball.size());
		tabelaArMedicineball.setSelectable(true);
		tabelaArMedicineball.setImmediate(true);
		
		tabelaArMedicineball.addListener(new ItemClickListener() {
			
			@Override
			public void itemClick(ItemClickEvent event) {
					if(tabSexo=='M'){
						arMedicineball.setTipo(tabSexo);
						
						Property itemProperty = event.getItem().getItemProperty("Idade");
						arMedicineball.setIdade(Integer.parseInt(itemProperty.getValue().toString()));
						
						itemProperty = event.getItem().getItemProperty("Muito Fraco");
						arMedicineball.setMuitoFraco(Integer.parseInt(itemProperty.getValue().toString()));
						
						itemProperty = event.getItem().getItemProperty("Fraco") ;
						arMedicineball.setFraco(Integer.parseInt(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Razoavel") ;
						arMedicineball.setRazoavel(Integer.parseInt(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Bom") ;
						arMedicineball.setBom(Integer.parseInt(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Muito Bom");
						arMedicineball.setMuitoBom(Integer.parseInt(itemProperty.getValue().toString()));
						
						tabelaSelecionada= true;
					}
					
					else if (tabSexo=='F'){
						
						arMedicineball.setTipo(tabSexo);
						
						Property itemProperty = event.getItem().getItemProperty("Idade");
						arMedicineball.setIdade(Integer.parseInt(itemProperty.getValue().toString()));
						
						itemProperty = event.getItem().getItemProperty("Muito Fraco");
						arMedicineball.setMuitoFraco(Integer.parseInt(itemProperty.getValue().toString()));
						
						itemProperty = event.getItem().getItemProperty("Fraco") ;
						arMedicineball.setFraco(Integer.parseInt(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Razoavel") ;
						arMedicineball.setRazoavel(Integer.parseInt(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Bom") ;
						arMedicineball.setBom(Integer.parseInt(itemProperty.getValue().toString()));
						
						
						itemProperty = event.getItem().getItemProperty("Muito Bom");
						arMedicineball.setMuitoBom(Integer.parseInt(itemProperty.getValue().toString()));
						
						tabelaSelecionada = true;
					}
				
			}
		});
		return tabelaArMedicineball;
	}

}
