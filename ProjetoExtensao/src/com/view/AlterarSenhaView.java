package com.view;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.Position;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class AlterarSenhaView  extends VerticalLayout{
	
	public AlterarSenhaView() {
		
		Panel painel = new Panel("Alterar Senha");
		painel.setWidth("350px");
		painel.setHeight("300px");
		painel.setIcon(FontAwesome.KEY);
		
		VerticalLayout vlinternoMaior= new VerticalLayout();
		vlinternoMaior.setSpacing(true);
		
		
		
		PasswordField senhaAtual = new PasswordField("Senha Atual");
		senhaAtual.setWidth("200px");
		final PasswordField novaSenha = new PasswordField("Nova Senha(Min. 8 Caracteres)");
		novaSenha.setWidth("200px");
		PasswordField repitaNovaSenha = new PasswordField("Repita a Nova Senha");
		repitaNovaSenha.setWidth("200px");
		
		novaSenha.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(novaSenha.getValue().length() < 8){
					Notification mgs = new Notification("");
					mgs.setHtmlContentAllowed(true);
					mgs.show("Minimo 8 carateres!", "", Notification.Type.WARNING_MESSAGE);
					mgs.setPosition(Position.BOTTOM_CENTER);
					mgs.setDelayMsec(2000);
				}
				
			}
		});
		
		
		
		
		
		Button salvar = new Button("Salvar");
		
			salvar.addClickListener( new ClickListener() {
				
				@Override
				public void buttonClick(ClickEvent event) {
					boolean confirmação = false;
					boolean btamanho= false;
					boolean bLetrasNumero = false;
			
					if(!(novaSenha.getValue().equals(repitaNovaSenha.getValue()))){			
						Notification mgs = new Notification("Senhas Diferentes");
						mgs.setHtmlContentAllowed(true);
						mgs.show("As senha não conferem","Senhas Diferentes",Notification.Type.WARNING_MESSAGE);
						mgs.setPosition(Position.BOTTOM_LEFT);
						mgs.setDelayMsec(2000);
					}
					
					else{
					
					
						if(novaSenha.getValue().length() >= 8){
							btamanho= true;
						}
										
											
						if(conferirPassword(novaSenha)){
							bLetrasNumero = true;
						}
					
									
						if(btamanho && bLetrasNumero){
//							salvarSenha(senhaAtual.getValue().toString(),novaSenha.getValue().toString());
							
							System.out.println("Salvei a senha");
						}
						
					
						
						else{
							
													
							if(!btamanho && bLetrasNumero){
								Notification.show("A senha não contem no mínimo 8 caracteres!");
							}
							
							else if(btamanho&& !bLetrasNumero){
								Notification.show("A senha deve conter letras e numeros ");
	
							}
							
						}
					
					}
					
					
						
				}
			});
		
		
		vlinternoMaior.addComponents(senhaAtual,novaSenha,repitaNovaSenha,salvar);
		vlinternoMaior.setSizeFull();
		vlinternoMaior.setComponentAlignment(senhaAtual, Alignment.MIDDLE_CENTER);
		vlinternoMaior.setComponentAlignment(novaSenha, Alignment.MIDDLE_CENTER);
		vlinternoMaior.setComponentAlignment(repitaNovaSenha, Alignment.MIDDLE_CENTER);
		vlinternoMaior.setComponentAlignment(salvar, Alignment.MIDDLE_CENTER);
		painel.setContent(vlinternoMaior);
		
		this.addComponent(painel);
		this.setSizeFull();
		this.setComponentAlignment(painel, Alignment.MIDDLE_CENTER);
		
	}
	
	
	public boolean conferirPassword(PasswordField senha){
		String strSenha = senha.getValue().toString();
		char[] vetorSenha = strSenha.toCharArray();
		char[] vetorLetras = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		char[] vetorNumber ={'0','1','2','3','4','5','6','7','8','9'}; 
		boolean resposta = false;
		boolean bletras =false;
		boolean bnumero =false;
		
		int i=0;
		int l ;
		int n;
		
			while(i<vetorSenha.length){
			
				l=0;
				while(l< vetorLetras.length && !bletras){
							
					if(vetorSenha[i]==vetorLetras[l])
						bletras = true;
					
					l++;
				}
				
				n=0;
				while ( n<vetorNumber.length && !bnumero) {
											
						if(vetorSenha[i]==vetorNumber[n])
							bnumero = true;
									
					n++;
				}
				
				i++;
			}
			
			if(bletras && bnumero){
				resposta = true;
			}
		
			return resposta;
	}


}
