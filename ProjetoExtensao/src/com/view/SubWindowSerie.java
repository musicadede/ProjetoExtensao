package com.view;

import com.example.projetoextensao.Banco;
import com.model.Serie;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class SubWindowSerie extends Window {
	
	public SubWindowSerie() {
		// TODO Auto-generated constructor stub
	}
	public static void subWindowSerie(Banco banco, Serie serie, VerticalLayout geral) {
			
		geral.removeAllComponents();
		
			
			Window confirmarExcluir = new Window("Confirmação de Exclusão");
			geral.addComponent(confirmarExcluir);
			VerticalLayout vlWindow = new VerticalLayout();
			Button sim =  new Button("Sim");
			Button nao =  new Button("Não");
			
			vlWindow.setMargin(true);
			
			sim.addClickListener(new ClickListener() {
				
				@Override
				public void buttonClick(ClickEvent event) {
					banco.excluirSerie(serie);
					
					geral.removeAllComponents();
					
					
				}
			});
			
			nao.addClickListener(new ClickListener() {
				
				@Override
				public void buttonClick(ClickEvent event) {
					confirmarExcluir.close();
					
					
				}
			
			});
		
			HorizontalLayout hlwindow = new HorizontalLayout();
			hlwindow.setSpacing(true);
			hlwindow.addComponents(sim,nao);
			Label lb = new Label("Deseja Realmente excluir esta Série?");
			Label seriedados = new Label("Serie : "+serie.getSerie());
			vlWindow.addComponents(lb,seriedados,hlwindow);
			confirmarExcluir.center();
		
		
	}
}
