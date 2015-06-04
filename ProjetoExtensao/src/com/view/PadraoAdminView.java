package com.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

public class PadraoAdminView extends VerticalLayout  {

	MenuTopoAdmView menutopo;
	Navegacao navegacao;
	
	
	
	public PadraoAdminView() {
		menutopo = new MenuTopoAdmView();
		menutopo.setHeight("20px");
		navegacao = new Navegacao();
		navegacao.setSizeFull();
		
		this.addComponent(menutopo);
		this.addComponent(navegacao);
	}

	public MenuTopoAdmView getMenutopo() {
		return menutopo;
	}


	public void setMenutopo(MenuTopoAdmView menutopo) {
		this.menutopo = menutopo;
	}


	public Navegacao getNavegacao() {
		return navegacao;
	}


	public void setNavegacao(Navegacao meio) {
		this.navegacao = navegacao;
	}

}
