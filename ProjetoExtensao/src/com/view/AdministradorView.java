package com.view;

import com.Service.ImcService;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class AdministradorView extends VerticalLayout {
		
	HorizontalLayout  hlnavegacao ;
	HorizontalLayout topomenu;
	MenuBar menu ;
	MenuItem minhaConta;
	MenuItem usuario;
	MenuItem inserirDadosNaTabela;
	
	ImcService imcService;

	
	public AdministradorView() {
			
		hlnavegacao = new HorizontalLayout ();
		hlnavegacao.setSizeFull();
		
		topomenu = menuTopo();
		topomenu.setStyleName("topo");
		
		this.addComponents(topomenu,hlnavegacao);
		topomenu.setHeight("10%");
		topomenu.setWidth("100%");
		
		hlnavegacao.setHeight("85%");
	}
	public HorizontalLayout menuTopo(){
		
		HorizontalLayout hlmenutopo = new HorizontalLayout();
		hlmenutopo.setHeight("20px");
		MenuBar menu = new MenuBar();
		
		imcService = new ImcService();
		
		MenuBar.Command alterarSenha = new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				
				
			}
		};
		
		MenuBar.Command alterarLogin = new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
//				new CadastrarEscolaView();
				
			}
		};
		
		
		
		MenuBar.Command logoutsair = new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				new Autenticar();
			}
		};
		
		
		
		MenuItem usuario= menu.addItem("Usuario", null);
		usuario.setIcon(FontAwesome.USER);
		MenuItem inserirDadosNaTabela = menu.addItem("Tabelas", null);
		inserirDadosNaTabela.setIcon(FontAwesome.TABLE);
		MenuItem minhaConta = menu.addItem("Minha Conta",null);
		minhaConta.setIcon(FontAwesome.KEY);
		
		MenuBar logout= new MenuBar();
		MenuItem sair = logout.addItem("Sair", logoutsair);
		
		
		minhaConta.addItem("Alterar Senha", alterarSenha);
		minhaConta.addItem("Alterar Login", alterarLogin);
		
		
		
		MenuBar.Command cadastrarUsuario= new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				cadastrarUsuario();
				
			}
		};
		
		MenuBar.Command editarUsuario = new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				editarUsuario();
				
			}
		};
		
		MenuBar.Command buscarUsuario = new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
//				new CadastrarEscolaView();
				
			}
		};
		
		MenuBar.Command excluirUsuario = new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
//				new CadastrarEscolaView();
				
			}
		};
		
		
		usuario.addItem("Cadastrar", cadastrarUsuario);
		usuario.addItem("Editar", editarUsuario);
		usuario.addItem("Buscar", buscarUsuario);
		usuario.addItem("Excluir", excluirUsuario);
		
		MenuBar.Command menuImc =new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				mostrarImcView();
				
			}
		};
		
		
		MenuBar.Command menuFlexibilidade=new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				mostrarFlexibilidadeView();
				
			}
		};
		
		
		MenuBar.Command menuAbdominal=new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				mostrarAbdominalView();
				
			}
		};
		
		MenuBar.Command menuArremessoMedicineball=new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				mostrarArremessoMedicineballView();
				
			}
		};
		
		
		MenuBar.Command menuCorrida20Metros=new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				mostrarCorrida20MetrosView();
				
			}
		};
		
		MenuBar.Command menuSeisMinutos=new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				mostrarSeisMinutosView();
				
			}
		};
		
		MenuBar.Command menuSaltoDistancia=new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				mostrarSaltoDistanciaView();
				
			}
		};
		
		
		MenuBar.Command menuQuadrado=new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				mostrarQuadradoView();
				
			}
		};
		
		
		
		MenuItem imc = inserirDadosNaTabela.addItem("IMC", menuImc);
		MenuItem flexibilidade = inserirDadosNaTabela.addItem("Flexibilidade", menuFlexibilidade);
		MenuItem abdominal = inserirDadosNaTabela.addItem("Abdominal", menuAbdominal);
		MenuItem seisMinutos = inserirDadosNaTabela.addItem("6 Minutos", menuSeisMinutos);
		MenuItem saltoEmDistancia = inserirDadosNaTabela.addItem("Salto em Distancia", menuSaltoDistancia);
		MenuItem arremesoMedicineball = inserirDadosNaTabela.addItem("Arremesso de Medidineball", menuArremessoMedicineball);
		MenuItem quadrado = inserirDadosNaTabela.addItem("Quadrado", menuQuadrado);
		MenuItem corrida20Metros = inserirDadosNaTabela.addItem("Corrida de 20 Metros",menuCorrida20Metros);

		
				

		hlmenutopo.addComponents(menu, logout);
		hlmenutopo.setComponentAlignment(logout, Alignment.TOP_RIGHT);
		
		return hlmenutopo;
	}
	
	protected void mostrarQuadradoView() {
		hlnavegacao.removeAllComponents();
		HorizontalLayout hlQuadrado= new QuadradoView();
		hlnavegacao.addComponent(hlQuadrado);
		hlnavegacao.setComponentAlignment(hlQuadrado, Alignment.MIDDLE_CENTER);
		
		
	}
	protected void mostrarSaltoDistanciaView() {
		hlnavegacao.removeAllComponents();
		HorizontalLayout hlSaltoDistancia= new SaltoDistanciaView();
		hlnavegacao.addComponent(hlSaltoDistancia);
		hlnavegacao.setComponentAlignment(hlSaltoDistancia, Alignment.MIDDLE_CENTER);
		
	}
	protected void mostrarSeisMinutosView() {
		hlnavegacao.removeAllComponents();
		HorizontalLayout hlSeisMinutos= new SeisMinutosView();
		hlnavegacao.addComponent(hlSeisMinutos);
		hlnavegacao.setComponentAlignment(hlSeisMinutos, Alignment.MIDDLE_CENTER);
		
	}
	protected void mostrarCorrida20MetrosView() {
		hlnavegacao.removeAllComponents();
		HorizontalLayout hlCorrida20Metros = new Corrida20MetrosView();
		hlnavegacao.addComponent(hlCorrida20Metros);
		hlnavegacao.setComponentAlignment(hlCorrida20Metros, Alignment.MIDDLE_CENTER);
		
	}
	protected void mostrarArremessoMedicineballView() {
		hlnavegacao.removeAllComponents();
		HorizontalLayout hlArremessoMedicineball = new ArremessoMedicineballView();
		hlnavegacao.addComponent(hlArremessoMedicineball);
		hlnavegacao.setComponentAlignment(hlArremessoMedicineball, Alignment.MIDDLE_CENTER);
		
	}
	protected void mostrarAbdominalView() {
		hlnavegacao.removeAllComponents();
		HorizontalLayout hlAbdominal = new AbdominalView();
		hlnavegacao.addComponent(hlAbdominal);
		hlnavegacao.setComponentAlignment(hlAbdominal, Alignment.MIDDLE_CENTER);
		
	}
	public void mostrarFlexibilidadeView() {
		hlnavegacao.removeAllComponents();
		HorizontalLayout hlFlexibilidade = new FlexibilidadeView();
		hlnavegacao.addComponent(hlFlexibilidade);
		hlnavegacao.setComponentAlignment(hlFlexibilidade, Alignment.MIDDLE_CENTER);
		
	}

	protected void mostrarImcView() {
		hlnavegacao.removeAllComponents();
		HorizontalLayout hlimc = new ImcView();
		hlnavegacao.addComponent(hlimc);
		hlnavegacao.setComponentAlignment(hlimc, Alignment.MIDDLE_CENTER);
		
	}
	
	public void cadastrarAdmin(){
		hlnavegacao.removeAllComponents();
		HorizontalLayout hlcadastrarAdmn = new HorizontalLayout(new CadastrarAdminView());
		hlnavegacao.addComponent(hlcadastrarAdmn);
		hlnavegacao.setComponentAlignment(hlcadastrarAdmn, Alignment.TOP_CENTER);
		
	}
	
	public void cadastrarUsuario(){
		hlnavegacao.removeAllComponents();
		HorizontalLayout hlcadastrarUsuario= new HorizontalLayout(new CadastrarUsuarioView());
		hlnavegacao.addComponent(hlcadastrarUsuario);
		hlnavegacao.setComponentAlignment(hlcadastrarUsuario, Alignment.TOP_CENTER);
		
		
	}
	
	public void editarUsuario(){
		hlnavegacao.removeAllComponents();
		HorizontalLayout hleditarusuario = new HorizontalLayout(new EditarUsuarioView());
		
	}
	
	
	public void alterarSenhaView(){
		hlnavegacao.removeAllComponents();
		VerticalLayout hlalterarSenha = new AlterarSenhaView();
		hlnavegacao.addComponent(hlalterarSenha);
		hlnavegacao.setComponentAlignment(hlalterarSenha, Alignment.BOTTOM_CENTER);
	}
	

}
