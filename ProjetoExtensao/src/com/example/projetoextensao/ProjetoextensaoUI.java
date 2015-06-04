package com.example.projetoextensao;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.view.AdministradorView;
import com.view.Autenticar;
import com.view.Logout;
import com.view.UsuarioView;

@SuppressWarnings("serial")
//@Theme("projetoextensao")
@Theme("runo")
public class ProjetoextensaoUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = ProjetoextensaoUI.class, widgetset = "com.example.projetoextensao.widgetset.ProjetoextensaoWidgetset")
	public static class Servlet extends VaadinServlet {
	}
	
	
	@Override
	protected void init(VaadinRequest request) {
		setSizeFull();
//		ProjetoView inicio = new ProjetoView();
//		setContent(new UsuarioView());
		setContent(new AdministradorView());

//		setContent(new Autenticar());
	}

}