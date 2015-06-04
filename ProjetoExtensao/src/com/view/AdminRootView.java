package com.view;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class AdminRootView extends VerticalLayout {

	HorizontalLayout  hlnavegacao ;
	HorizontalLayout topomenu;
	MenuBar menu ;
	MenuItem administrador;
	MenuItem usuario;
	MenuItem inserirDadosNaTabela;
	
	
	public AdminRootView() {
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
		
		MenuBar.Command cadastrarAdmin = new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				cadastrarAdmin();
				
			}
		};
		
		MenuBar.Command editarAdmin = new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
//				new CadastrarEscolaView();
				
			}
		};
		
		MenuBar.Command buscarAdmin = new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
//				new CadastrarEscolaView();
				
			}
		};
		
		MenuBar.Command excluirAdmin = new Command() {
			
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
		
		
		
		MenuItem administrador = menu.addItem("Administrador",null);
		administrador.setIcon(FontAwesome.ARROW_CIRCLE_O_DOWN);
		MenuItem usuario= menu.addItem("Usuario", null);
		
		MenuItem inserirDadosNaTabela = menu.addItem("Tabelas", null);
		
		MenuBar logout= new MenuBar();
		MenuItem sair = logout.addItem("Sair", logoutsair);
		
		
		administrador.addItem("Cadastrar", cadastrarAdmin);
		administrador.addItem("Editar", editarAdmin);
		administrador.addItem("Buscar", buscarAdmin);
		administrador.addItem("Excluir", excluirAdmin);
		
		
		MenuBar.Command cadastrarUsuario= new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				cadastrarUsuario();
				
			}
		};
		
		MenuBar.Command editarUsuario = new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
//				new CadastrarEscolaView();
				
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
		
		
		
		
		MenuItem imc = inserirDadosNaTabela.addItem("IMC", null);
		MenuItem flexibilidade = inserirDadosNaTabela.addItem("Flexibilidade", null);
		MenuItem abdominal = inserirDadosNaTabela.addItem("Abdominal", null);
		MenuItem seisMinutos = inserirDadosNaTabela.addItem("6 Minutos", null);
		MenuItem saltoEmDistancia = inserirDadosNaTabela.addItem("Salto em Distancia", null);
		MenuItem arremesoMedicineball = inserirDadosNaTabela.addItem("Arremesso de Medidineball", null);
		MenuItem quadrado = inserirDadosNaTabela.addItem("Quadrado", null);
		MenuItem corrida20Metros = inserirDadosNaTabela.addItem("Corrida de 20 Metros", null);

		imc.addItem("Cadastra", null);
		imc.addItem("Editar", null);
		imc.addItem("Buscar", null);
		imc.addItem("Excluir", null);
		
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
	
	

}
