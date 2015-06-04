package com.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;

public class MenuTopoUsuarioView extends HorizontalLayout implements View {

	public MenuTopoUsuarioView() {

		MenuBar menu = new MenuBar();
		MenuBar.Command escolaComand = new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				new CadastrarEscolaView();
				
			}
		};
		
		MenuItem escolaMenu = menu.addItem("Escola",null);
		MenuItem alunoMenu= menu.addItem("Aluno", null);
		MenuItem relatórioMenu = menu.addItem("Relatório", null);
		
		
		escolaMenu.addItem("Cadastrar", escolaComand);
		escolaMenu.addItem("Editar", null);
		escolaMenu.addItem("Buscar", null);
		escolaMenu.addItem("Excluir", null);
		
		this.addComponent(menu);
	}
		
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
