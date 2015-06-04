package com.view;

import com.example.projetoextensao.Banco;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class CadastrarUsuarioView extends HorizontalLayout {

	public CadastrarUsuarioView() {
		
		Panel painel = new Panel("Cadastrar Usuário");
		painel.setWidth("250px");
		painel.setHeight("250px");
		FormLayout menucadastro =new FormLayout();
		menucadastro.setSpacing(true);
		
		TextField nome = new TextField("Nome");
		TextField login = new TextField("Login");
		PasswordField senha  = new PasswordField("Senha");	
		
		
		Button salvar = new Button("Salvar", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				if(!(nome.getValue().toString().equals("")) && !(login.getValue().toString().equals(""))&& !(senha.getValue().toString().equals(""))){
					
					try{
						Banco persistencia = new Banco();	
						persistencia.adicionarUsuario(nome.getValue(), login.getValue(), senha.getValue());
						nome.setValue("");
						login.setValue("");
						senha.setValue("");
						
						Notification.show("Cadastrado com sucesso!");
						
					}catch(Exception e){
						System.out.println("Exceção na persistencia ="+e);
					}
				}
				else
					Notification.show("Informe Todos os campos!");
			}
		});
		
		salvar.setClickShortcut(KeyCode.ENTER);
		menucadastro.addComponent(nome);
		menucadastro.setComponentAlignment(nome, Alignment.TOP_CENTER);
		menucadastro.addComponent(login);
		menucadastro.setComponentAlignment(login, Alignment.TOP_CENTER);
		menucadastro.addComponent(senha);
		menucadastro.addComponent(salvar);
		menucadastro.setComponentAlignment(salvar, Alignment.BOTTOM_RIGHT);
		painel.setContent(menucadastro);
		
		this.addComponent(painel);
		
	}
}
