package com.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;

public class MenuTopoAdmView extends HorizontalLayout {

		MenuBar menu ;
		MenuItem administrador;
		MenuItem usuario;
		MenuItem inserirDadosNaTabela;
	
	
		public MenuTopoAdmView() {
			MenuBar menu = new MenuBar();
			
			MenuBar.Command cadastrarAdmin = new Command() {
				
				@Override
				public void menuSelected(MenuItem selectedItem) {
					
					
					
				}
			};
			
			MenuBar.Command editarAdmin = new Command() {
				
				@Override
				public void menuSelected(MenuItem selectedItem) {
					new CadastrarEscolaView();
					
				}
			};
			
			MenuBar.Command buscarAdmin = new Command() {
				
				@Override
				public void menuSelected(MenuItem selectedItem) {
					new CadastrarEscolaView();
					
				}
			};
			
			MenuBar.Command excluirAdmin = new Command() {
				
				@Override
				public void menuSelected(MenuItem selectedItem) {
					new CadastrarEscolaView();
					
				}
			};
			
			
			MenuBar.Command logoutsair = new Command() {
				
				@Override
				public void menuSelected(MenuItem selectedItem) {
					new Autenticar();
				}
			};
			
			
			
			MenuItem administrador = menu.addItem("Administrador",null);
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
					new CadastrarEscolaView();
					
				}
			};
			
			MenuBar.Command editarUsuario = new Command() {
				
				@Override
				public void menuSelected(MenuItem selectedItem) {
					new CadastrarEscolaView();
					
				}
			};
			
			MenuBar.Command buscarUsuario = new Command() {
				
				@Override
				public void menuSelected(MenuItem selectedItem) {
					new CadastrarEscolaView();
					
				}
			};
			
			MenuBar.Command excluirUsuario = new Command() {
				
				@Override
				public void menuSelected(MenuItem selectedItem) {
					new CadastrarEscolaView();
					
				}
			};
			
			
			usuario.addItem("Cadastrar", cadastrarUsuario);
			usuario.addItem("Editar", editarUsuario);
			usuario.addItem("Buscar", buscarUsuario);
			usuario.addItem("Excluir", excluirUsuario);
			
			
			
			
			
			
			this.setSizeFull();
			this.addComponents(menu, logout);
			this.setComponentAlignment(logout, Alignment.TOP_RIGHT);

		}

}
