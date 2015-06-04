package com.view;

import java.util.List;

import com.Service.ImcService;
import com.model.Imc;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ImcEditarView extends VerticalLayout {

	private Imc imc;

	private ImcService imcService;

	private TextField idade;
	private TextField baixoPeso;
	private TextField normal;
	private TextField excessoPeso;

	private ComboBox filtroTabela;
	private ComboBox filtroIdade;

	public ImcEditarView() {
		
		construirFiltro();
	}
	
	public void construirFiltro(){
		
		this.removeAllComponents();
		
		FormLayout form = new FormLayout();
		imcService = new ImcService();

		ComboBox filtroTabela = new ComboBox("Escolha O IMC");
		filtroTabela.addItem("Imc Masculino");
		filtroTabela.addItem("Imc Feminino");
		ComboBox filtroIdade = new ComboBox("Ecolha uma Idade");
		filtroIdade.setEnabled(false);

		filtroTabela.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				if (filtroTabela.getValue() == "Imc Masculino") {
					filtroIdade.setEnabled(true);

					List<Integer> idades = imcService.buscarIdadeImcMasc();

					for (Integer idade : idades) {
						filtroIdade.addItem(idade);
					}

				}

				else if (filtroTabela.getValue() == "Imc Feminino") {
					filtroIdade.setEnabled(true);

					List<Integer> idades = imcService.buscarIdadeImcFem();

					for (Integer idade : idades) {
						filtroIdade.addItem(idade);
					}

				}
			}
		});

		Button buscar = new Button("Buscar", new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				if (filtroTabela.getValue() == "Imc Masculino") {
					imc = new Imc();
					imc.setTipo('M');
					imc.setIdade(Integer.parseInt(filtroIdade.getValue().toString()));
				}

				else if (filtroTabela.getValue() == "Imc Feminino") {
					imc = new Imc();
					imc.setTipo('F');
					imc.setIdade(Integer.parseInt(filtroIdade.getValue().toString()));
				}

				imc = imcService.buscarImc(imc);
				
				construirTabelaImcView();
			}
		});

		form.addComponents(filtroTabela, filtroIdade, buscar);
		this.addComponent(form);
		this.setComponentAlignment(form, Alignment.MIDDLE_CENTER);


	}
	

	public void construirTabelaImcView() {
		this.removeAllComponents();
//		this.setSizeUndefined();

		FormLayout geral = new FormLayout();
		geral.setSpacing(true);

		HorizontalLayout tituloTab = new HorizontalLayout();
		HorizontalLayout apresTabela = new HorizontalLayout();

		apresTabela.setSpacing(true);

		Label nomeTabela = new Label("Imc Masculino");

		idade = new TextField("idade");
		idade.setValue(String.valueOf(imc.getIdade()));

		baixoPeso = new TextField("baixo Peso");
		baixoPeso.setValue(String.valueOf(imc.getBaixoPeso()));

		normal = new TextField("Normal");
		normal.setValue(String.valueOf(imc.getNormal()));

		excessoPeso = new TextField("Excesso de Peso");
		excessoPeso.setValue(String.valueOf(imc.getExcessoPeso()));


		tituloTab.addComponent(nomeTabela);
		apresTabela.addComponent(idade);
		apresTabela.addComponent(baixoPeso);
		apresTabela.addComponent(normal);
		apresTabela.addComponent(excessoPeso);

		Button salvar = new Button("Salvar", new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				if (validarDados()) {

					imc.setIdade(Integer.parseInt(idade.getValue().toString()));
					imc.setBaixoPeso(Double.parseDouble(baixoPeso.getValue()
							.toString()));
					imc.setNormal(Double.parseDouble(normal.getValue()
							.toString()));
					imc.setExcessoPeso(Double.parseDouble(excessoPeso
							.getValue().toString()));
					
					imcService.editarImc(imc);
					limparDados();
					construirFiltro();
					Notification.show("Alterado Com Sucesso!");
				
				}
			}
		});

		this.addComponents(tituloTab, apresTabela,salvar);
		this.setComponentAlignment(tituloTab, Alignment.MIDDLE_CENTER);
		this.setComponentAlignment(apresTabela, Alignment.MIDDLE_CENTER);
		this.setComponentAlignment(salvar, Alignment.MIDDLE_RIGHT);
	}
	
	protected void limparDados() {
		imc= new Imc();

		idade.setValue("");
		baixoPeso.setValue("");
		normal.setValue("");
		excessoPeso.setValue("");
		

	}

	public boolean validarDados() {
		boolean resposta = false;


			if (idade.getValue() != null || baixoPeso.getValue() != null
					|| normal.getValue() != null
					|| excessoPeso.getValue() != null) {
				resposta = true;
			}

			else
				Notification.show("Informe os dados Corretamentes",
						"Há campos que nao foram Preenchidos!",
						Notification.Type.WARNING_MESSAGE);
		

		return resposta;

	}

}
