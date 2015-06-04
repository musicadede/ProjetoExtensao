package com.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class Navegacao extends HorizontalLayout implements View {
	
	VerticalLayout vlmeio;
	
	public Navegacao() {
		
		this.addComponent(vlmeio);
		this.setSizeFull();
	}
	
	public VerticalLayout getVlmeio() {
		return vlmeio;
	}

	public void setVlmeio(VerticalLayout vlmeio) {
		this.vlmeio = vlmeio;
	}
	

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

	
}
