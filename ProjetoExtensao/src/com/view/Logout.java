package com.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;

public class Logout extends HorizontalLayout implements View {
	
	public Logout() {

		Button logout = new Button("Logout");
		logout.addClickListener( new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				new Autenticar();
				
			}
		});
		
		this.setHeight("20px");
		this.addComponent(logout);
		this.setComponentAlignment(logout, Alignment.BOTTOM_RIGHT);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	
}
