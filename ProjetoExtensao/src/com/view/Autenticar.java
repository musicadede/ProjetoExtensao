package com.view;

import com.Controller.Autenticacao;
import com.example.projetoextensao.Banco;
import com.vaadin.client.ui.Icon;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ClassResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class Autenticar extends VerticalLayout implements View {
	
	String admRoot = "andre";
	String senhaRoot = "musica.1030";
	Button logout= new  Button("Logout");
	Banco banco ;
	
	
	public Autenticar() {
		setSizeFull();
		HorizontalLayout layoutLogin = new HorizontalLayout();
		HorizontalLayout h1 = new HorizontalLayout();
		HorizontalLayout h2 = new HorizontalLayout();
		HorizontalLayout h3 = new HorizontalLayout();
		
		TextField login= new TextField("Login");
		PasswordField senha= new PasswordField("Senha");

				
		VerticalLayout layoutInt = new VerticalLayout();
		Image img =new Image(null,new ClassResource("pr.gif"));
		img.setWidth("30px");
		img.setHeight("30px");
		
		
		Panel panel = new Panel("Login");
		panel.setStyleName("painel");
		
		
		panel.setIcon(FontAwesome.USER);
		
		h1.addComponent(login);
		h2.addComponent(senha);

		Button entrar = new Button("Entrar", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				Autenticacao autent = new Autenticacao();
				try{	
						 
						if(login.getValue().toString().equals(admRoot) && senha.getValue().toString().equals(senhaRoot)){
							
							UI.getCurrent().setContent(new AdminRootView());
						}
					
						else if(autent.autentAdmin(login.getValue(),senha.getValue())==true){
							UI.getCurrent().setContent(new AdministradorView());
						}
						
						else if(autent.autentUsuario(login.getValue(),senha.getValue())==true){
							UI.getCurrent().setContent(new UsuarioView());
						}
						
						else{
							Notification.show("Login ou senha Ivalido!");
						}
				}catch(Exception e){
						System.out.println( "Erro de Autenticação "+ e);
					}
			}
		});
		
		entrar.setClickShortcut(KeyCode.ENTER);
		h3.addComponent(entrar);
		h3.addComponent(img);
		
		layoutInt.addComponent(h1);
		layoutInt.setComponentAlignment(h1, Alignment.MIDDLE_CENTER);
		layoutInt.addComponent(h2);
		layoutInt.setComponentAlignment(h2, Alignment.MIDDLE_CENTER);
		layoutInt.addComponent(h3);
		layoutInt.setComponentAlignment(h3, Alignment.MIDDLE_CENTER);
		panel.setContent(layoutInt);
		panel.setStyleName("login");
		panel.setWidth("500px");
		panel.setHeight("200px");
		
		
		layoutInt.setSizeFull();		
		layoutLogin.addComponent(panel);
		this.addComponent(layoutLogin);
		this.setComponentAlignment(layoutLogin, Alignment.MIDDLE_CENTER);
		this.setSizeFull();

		UI.getCurrent().setContent(this);
		
	}
	
	@Override
	public void enter(ViewChangeEvent event) {


	
		
	}

}
