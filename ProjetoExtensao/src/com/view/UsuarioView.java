package com.view;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class UsuarioView extends VerticalLayout {

	HorizontalLayout hlnavegacao;
	HorizontalLayout topomenu;
	MenuBar menu;
	MenuItem escola;
	MenuItem aluno;
	MenuItem relatorio;

	public UsuarioView() {
		hlnavegacao = new HorizontalLayout();
		hlnavegacao.setSizeFull();
		hlnavegacao.setSpacing(true);

		topomenu = menuTopo();
		topomenu.setStyleName("topo");

		this.addComponents(topomenu, hlnavegacao);
		topomenu.setHeight("10%");
		topomenu.setWidth("100%");
		this.setSpacing(true);

//		hlnavegacao.setHeight("85%");
	}

	public HorizontalLayout menuTopo() {

		HorizontalLayout hlmenutopo = new HorizontalLayout();
		hlmenutopo.setHeight("20px");
		MenuBar menu = new MenuBar();

		MenuBar.Command alterarSenha = new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				alterarSenhaView();
			}
		};

			MenuBar.Command logoutsair = new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				new Autenticar();
			}
		};
		
		MenuBar.Command cadastrarEscola = new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				// TODO Auto-generated method stub
				
			}
		};
		
		
		MenuBar.Command relatorioView = new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				relatorioView();
				
			}
		};
		
		MenuBar.Command menuAlunoView = new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				AlunoView();
				
			}
		};
		
		
		

		MenuItem escola = menu.addItem("Escola", null);
		escola.setIcon(FontAwesome.USER);
		
		MenuItem aluno= menu.addItem("Aluno",menuAlunoView);
		aluno.setIcon(FontAwesome.TABLE);
		
		MenuItem relatorio= menu.addItem("Relatório", relatorioView);
		relatorio.setIcon(FontAwesome.PIED_PIPER_SQUARE);
		
		MenuItem minhaConta= menu.addItem("Minha Conta", null);
		minhaConta.setIcon(FontAwesome.KEY);
		
		
		
		escola.addItem("Cadastrar", cadastrarEscola);
		
		aluno.addItem("Cadastrar", cadastrarEscola);
		

		MenuBar logout = new MenuBar();
		MenuItem sair = logout.addItem("Sair", logoutsair);

		minhaConta.addItem("Alterar Senha", alterarSenha);

		hlmenutopo.addComponents(menu, logout);
		hlmenutopo.setComponentAlignment(logout, Alignment.TOP_RIGHT);
		
		
		return hlmenutopo;
	}
	
	
	protected void AlunoView() {
		hlnavegacao.removeAllComponents();
		VerticalLayout hlAlunoView= new AlunoCadastro();
		hlnavegacao.addComponent(hlAlunoView);
		hlnavegacao.setComponentAlignment(hlAlunoView, Alignment.BOTTOM_CENTER);
	}

	protected void relatorioView() {
		hlnavegacao.removeAllComponents();
		hlnavegacao.addComponent(new RelatorioView());
	}

	public void alterarSenhaView(){
		hlnavegacao.removeAllComponents();
		VerticalLayout hlalterarSenha = new AlterarSenhaView();
		hlnavegacao.addComponent(hlalterarSenha);
		hlnavegacao.setComponentAlignment(hlalterarSenha, Alignment.BOTTOM_CENTER);
	}
	
	



}
