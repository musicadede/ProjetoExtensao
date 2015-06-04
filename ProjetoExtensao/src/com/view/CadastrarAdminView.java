package com.view;

import com.example.projetoextensao.Banco;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class CadastrarAdminView extends VerticalLayout {
	
	final HorizontalLayout meio= new HorizontalLayout();
	
	
	public CadastrarAdminView() {
		this.setStyleName("cad");
		FormLayout menucadastro =new FormLayout();
		
		TextField nome = new TextField("Nome");
		TextField login = new TextField("Login");
		PasswordField senha  = new PasswordField("Senha");	
		
		
		Button salvar = new Button("Salvar", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				if(!(nome.getValue().toString().equals("")) && !(login.getValue().toString().equals(""))&& !(senha.getValue().toString().equals(""))){
					
					try{
						Banco persistencia = new Banco();	
						persistencia.adicionarAdm(nome.getValue(), login.getValue(), senha.getValue());
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
		menucadastro.addComponent(login);
		menucadastro.addComponent(senha);
		menucadastro.addComponent(salvar);
		meio.addComponent(menucadastro);
		
		this.setSizeFull();
		this.addComponent(meio);
		
	}
	
}
