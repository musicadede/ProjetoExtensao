package com.view;

import com.Service.UsuarioService;
import com.example.projetoextensao.Banco;
import com.model.Usuario;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.Position;
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

public class EditarUsuarioView extends VerticalLayout {

	private UsuarioService usuarioService;
	private Usuario usuario;
	
	public EditarUsuarioView() {
		usuarioService = new UsuarioService();
		usuario = new Usuario();
		FormLayout formBusca = new FormLayout();
		
		TextField tfbuscar = new TextField("Buscar por Nome");
		
		Button buscar = new Button("Buscar", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				usuario = usuarioService.buscarUsuario(tfbuscar.getValue());
				
//				editarUsuarioView();
			}
		});
		
		
	}
	
		
}
