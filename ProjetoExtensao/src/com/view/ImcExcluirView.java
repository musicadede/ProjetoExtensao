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
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ImcExcluirView extends VerticalLayout {

	private Window janela;
	private Label menssagem;

	private ImcService imcService;
	private Imc imc;

	private ComboBox filtroTabela;
	private ComboBox filtroIdade;

	public ImcExcluirView() {
		construirLayout();
	}

	public void layoutConfirmação() {

		Label mgs = new Label("Deseja realmente excluir os seguintes Dados?");
		janela = new Window(mgs.getValue().toString());
		janela.setClosable(false);

		VerticalLayout geral = new VerticalLayout();
		HorizontalLayout hlBotoes = new HorizontalLayout();
		HorizontalLayout hlmgs = new HorizontalLayout();

		String tab = filtroTabela.getValue().toString();
		String id = filtroIdade.getValue().toString();
		Label lbDados = new Label("idade :" + id + " da Tabela " + tab);

		Button sim = new Button("Sim", new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				imcService = new ImcService();
				imcService.excluirImc(imc);
				janela.close();
				Notification.show("Excluido com Sucesso!");
				construirLayout();

			}
		});
		Button nao = new Button("Não", new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				janela.close();

			}
		});

		hlmgs.addComponent(lbDados);
		hlBotoes.addComponents(nao, sim);
		geral.addComponents(lbDados, hlBotoes);

		hlBotoes.setComponentAlignment(nao, Alignment.MIDDLE_CENTER);
		hlBotoes.setComponentAlignment(sim, Alignment.MIDDLE_CENTER);

		janela.setContent(geral);
		janela.center();
		UI.getCurrent().addWindow(janela);

	}

	public void construirLayout() {
		this.removeAllComponents();
		
		FormLayout form = new FormLayout();
		imcService = new ImcService();
		imc = new Imc();

		filtroTabela = new ComboBox("Escolha o IMC");
		filtroTabela.addItem("Imc Masculino");
		filtroTabela.addItem("Imc Feminino");
		filtroIdade = new ComboBox("Ecolha uma Idade");
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

	
	Button excluir = new Button("Excluir", new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				if (filtroTabela.getValue() == "Imc Masculino") {
					imc.setTipo('M');
				}

				else if (filtroTabela.getValue() == "Imc Feminino") {
					imc.setTipo('F');
				}

				imc.setIdade(Integer
						.parseInt(filtroIdade.getValue().toString()));
				imc = imcService.buscarImc(imc);
				layoutConfirmação();

			}
		});

		form.addComponents(filtroTabela, filtroIdade, excluir);
		this.addComponent(form);
		this.setComponentAlignment(form, Alignment.MIDDLE_CENTER);

	}
}
