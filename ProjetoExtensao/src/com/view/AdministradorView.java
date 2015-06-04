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
		
		MenuBar.Command cadastrarImc =new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				imcCadastrarView();
				
			}
		};
		
		MenuBar.Command editarImc =new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				imcEditarView();
				
			}
		};
		
		MenuBar.Command buscarImc =new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				imcBuscarView();
				
			}
		};
		
		MenuBar.Command ExcluirImc =new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				imcExcluirView();
				
			}
		};
		
		
		MenuItem imc = inserirDadosNaTabela.addItem("IMC", null);
		MenuItem flexibilidade = inserirDadosNaTabela.addItem("Flexibilidade", null);
		MenuItem abdominal = inserirDadosNaTabela.addItem("Abdominal", null);
		MenuItem seisMinutos = inserirDadosNaTabela.addItem("6 Minutos", null);
		MenuItem saltoEmDistancia = inserirDadosNaTabela.addItem("Salto em Distancia", null);
		MenuItem arremesoMedicineball = inserirDadosNaTabela.addItem("Arremesso de Medidineball", null);
		MenuItem quadrado = inserirDadosNaTabela.addItem("Quadrado", null);
		MenuItem corrida20Metros = inserirDadosNaTabela.addItem("Corrida de 20 Metros", null);

		imc.addItem("Cadastra", cadastrarImc);
		imc.addItem("Editar", editarImc);
		imc.addItem("Buscar", buscarImc);
		imc.addItem("Excluir", ExcluirImc);
		
		flexibilidade.addItem("Cadastra", null);
		flexibilidade.addItem("Editar", null);
		flexibilidade.addItem("Buscar", null);
		flexibilidade.addItem("Excluir", null);
		
		abdominal.addItem("Cadastra", null);
		abdominal.addItem("Editar", null);
		abdominal.addItem("Buscar", null);
		abdominal.addItem("Excluir", null);
		
		seisMinutos.addItem("Cadastra", null);
		seisMinutos.addItem("Editar", null);
		seisMinutos.addItem("Buscar", null);
		seisMinutos.addItem("Excluir", null);
		
		saltoEmDistancia.addItem("Cadastra", null);
		saltoEmDistancia.addItem("Editar", null);
		saltoEmDistancia.addItem("Buscar", null);
		saltoEmDistancia.addItem("Excluir", null);
		
		arremesoMedicineball.addItem("Cadastra", null);
		arremesoMedicineball.addItem("Editar", null);
		arremesoMedicineball.addItem("Buscar", null);
		arremesoMedicineball.addItem("Excluir", null);
		
		quadrado.addItem("Cadastra", null);
		quadrado.addItem("Editar", null);
		quadrado.addItem("Buscar", null);
		quadrado.addItem("Excluir", null);
		
		corrida20Metros.addItem("Cadastra", null);
		corrida20Metros.addItem("Editar", null);
		corrida20Metros.addItem("Buscar", null);
		corrida20Metros.addItem("Excluir", null);
		

		hlmenutopo.addComponents(menu, logout);
		hlmenutopo.setComponentAlignment(logout, Alignment.TOP_RIGHT);
		
		return hlmenutopo;
	}
	
	protected void imcExcluirView() {
		hlnavegacao.removeAllComponents();
		VerticalLayout hlimc = new ImcExcluirView();
		hlnavegacao.addComponent(hlimc);
		hlnavegacao.setComponentAlignment(hlimc, Alignment.MIDDLE_CENTER);
		
	}
	protected void imcBuscarView() {
		hlnavegacao.removeAllComponents();
		VerticalLayout hlimc = new ImcBuscarView();
		hlnavegacao.addComponent(hlimc);
		hlnavegacao.setComponentAlignment(hlimc, Alignment.MIDDLE_CENTER);
		
	}
	protected void imcEditarView() {
		hlnavegacao.removeAllComponents();
		VerticalLayout hlimc = new ImcEditarView();
		hlnavegacao.addComponent(hlimc);
		hlnavegacao.setComponentAlignment(hlimc, Alignment.MIDDLE_CENTER);
		
	}
	protected void imcCadastrarView() {
		hlnavegacao.removeAllComponents();
		VerticalLayout hlimc = new ImcCadastrarView();
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
