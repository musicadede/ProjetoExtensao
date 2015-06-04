package com.view;

import java.util.List;

import com.Service.ImcService;
import com.model.Imc;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class ImcBuscarView extends VerticalLayout{

	private Imc imc;

	private ImcService imcService;

	private Label idade;
	private Label idadeValor;
	private Label baixoPeso;
	private Label baixoPesoValor;
	private Label normal;
	private Label normalValor;
	private Label excessoPeso;
	private Label excessoPesoValor;

	private String tituloBusca;
	private ComboBox filtroTabela;
	private ComboBox filtroIdade;
	
	public ImcBuscarView() {

		FormLayout form = new FormLayout();
		imcService = new ImcService();
		imc = new Imc();

		ComboBox filtroTabela = new ComboBox("Escolha O IMC");
		filtroTabela.addItem("Imc Masculino");
		filtroTabela.addItem("Imc Feminino");
		ComboBox filtroIdade = new ComboBox("Ecolha uma Idade");
		filtroIdade.setEnabled(false);

		filtroTabela.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				if (filtroTabela.getValue() == "Imc Masculino") {
					imc.setTipo('M');
					tituloBusca = "Imc Masculino";
					List<Integer> idades = imcService.buscarIdadeImcMasc();

					for (Integer idade : idades) {
						filtroIdade.addItem(idade);
					}

				}

				else if (filtroTabela.getValue() == "Imc Feminino") {
					imc.setTipo('F');
					tituloBusca = "Imc Feminino";
					List<Integer> idades = imcService.buscarIdadeImcFem();

					for (Integer idade : idades) {
						filtroIdade.addItem(idade);
					}

				}
				filtroIdade.setEnabled(true);
			}
		});

		Button buscar = new Button("Buscar", new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				if (filtroTabela.getValue()=="Imc Massculino") {
					imc.setTipo('M');
				}

				else if (filtroTabela.getValue()=="Imc Feminino") {
					imc.setTipo('F');
				}
				
				imc.setIdade(Integer.parseInt(filtroIdade.getValue().toString()));
				imc = imcService.buscarImc(imc);
				
				exibirImcView();

			}
		});

		form.addComponents(filtroTabela, filtroIdade, buscar);
		this.addComponent(form);
		this.setComponentAlignment(form, Alignment.MIDDLE_CENTER);

	}
	
	public void exibirImcView() {
		this.removeAllComponents();
		this.setSizeUndefined();

		FormLayout geral = new FormLayout();
		geral.setSpacing(true);

		HorizontalLayout tituloTab = new HorizontalLayout();
		VerticalLayout apresTabela= new VerticalLayout();
		HorizontalLayout hlidade= new HorizontalLayout();
		HorizontalLayout hlbaixoPeso= new HorizontalLayout();
		HorizontalLayout hlNormal= new HorizontalLayout();
		HorizontalLayout hlexcessoPeso= new HorizontalLayout();

		hlidade.setSpacing(true);

		Label nomeTabela = new Label(tituloBusca);

		idade = new Label("idade: ");
		Label idadeValor= new Label(String.valueOf(imc.getIdade()));
		hlidade.addComponents(idade,idadeValor);

		baixoPeso = new Label("baixo Peso: ");
		baixoPesoValor = new Label(String.valueOf(imc.getBaixoPeso()));
		hlbaixoPeso.addComponents(baixoPeso,baixoPesoValor);
		
		normal = new Label("Normal : ");
		normalValor = new Label(String.valueOf(imc.getNormal()));
		hlNormal.addComponents(normal,normalValor);

		excessoPeso = new Label("Excesso de Peso :");
		excessoPesoValor = new Label(String.valueOf(imc.getExcessoPeso()));
		hlexcessoPeso.addComponents(excessoPeso,excessoPesoValor);

		tituloTab.addComponent(nomeTabela);
		apresTabela.addComponent(hlidade);
		apresTabela.addComponent(hlbaixoPeso);
		apresTabela.addComponent(hlNormal);
		apresTabela.addComponent(hlexcessoPeso);

		
		this.addComponents(tituloTab, apresTabela);
		this.setComponentAlignment(tituloTab, Alignment.MIDDLE_CENTER);
		this.setComponentAlignment(apresTabela, Alignment.MIDDLE_CENTER);
	}

}
