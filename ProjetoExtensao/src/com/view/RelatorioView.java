package com.view;

import java.util.List;

import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.metadata.LegendPlacements;
import org.dussan.vaadin.dcharts.metadata.locations.LegendLocations;
import org.dussan.vaadin.dcharts.metadata.renderers.SeriesRenderers;
import org.dussan.vaadin.dcharts.options.Legend;
import org.dussan.vaadin.dcharts.options.Options;
import org.dussan.vaadin.dcharts.options.SeriesDefaults;
import org.dussan.vaadin.dcharts.renderers.series.PieRenderer;

import com.DAO.DadosDAO;
import com.Service.CidadeService;
import com.Service.DadosService;
import com.Service.EscolaService;
import com.Service.SerieService;
import com.model.DadosRelatorio;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;

public class RelatorioView extends HorizontalLayout{

	CidadeService cidadeService;
	EscolaService escolaService;
	SerieService serieService;
	FormLayout vlView;
	FormLayout vlGrafico;
	DadosRelatorio dados;
	DadosService dadosService;
	DadosDAO dadosDAO;
	
	
	
	public RelatorioView() {
		setSizeFull();
		escolaService = new EscolaService();
		cidadeService = new CidadeService();
		serieService = new SerieService();
		dadosService = new DadosService();
		vlView = new FormLayout();
		vlGrafico = new FormLayout();
		vlGrafico.setSizeFull();
//		vlGrafico.setWidth("700px");
//		vlGrafico.setHeight("100%");
		construirFiltroLayout();
		this.addComponents(vlView,vlGrafico);
		
	}
	
	public void construirFiltroLayout(){
			
		ComboBox  comboCidade = new ComboBox("Cidade");
		ComboBox filtroEscola = new ComboBox("Escola");
		List<String> cidades = cidadeService.buscarCidade();
		
		for(String c: cidades){
		comboCidade.addItem(c);
		}
		
		filtroEscola.setEnabled(false);
		
		ComboBox filtroSerie = new ComboBox("Série");
		filtroSerie.setEnabled(false);
		
		
		
		comboCidade.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				filtroEscola.setEnabled(true);
				int idCidade = cidadeService.buscarIDCidade(comboCidade.getValue().toString());
				List<String> escolas = escolaService.buscarNomesEscolas(idCidade);
				
				for(String esc: escolas){
					filtroEscola.addItem(esc);
					}
				
			}
		});
		
		filtroEscola.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
					filtroSerie.setEnabled(true);
					int idEscola = escolaService.buscarIDEScola(filtroEscola.getValue().toString());
					List<Integer> series = serieService.buscarSeries(idEscola);
					
					for(int seri: series){
						filtroSerie.addItem(seri);
						}
					
			}
		});
		
		

		
				
		Button exibir = new Button("Exibir",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				dados = new DadosRelatorio();
				
				if(comboCidade.getValue()!=null){
					
					String nomeDaCidade = (String)comboCidade.getValue();
					
					if(filtroEscola.getValue()!=null){
						String nomeDaEscola = (String)filtroEscola.getValue();
						
						if(filtroSerie.getValue()!=null) {//buscar dados somente por cidade, escola e série
							int numSeri  = Integer.parseInt(filtroSerie.getValue().toString());
							
							dados.setNomeCidade(nomeDaCidade);
							dados.setIdCidade(cidadeService.buscarIDCidade(dados.getNomeCidade()));
							dados.setNomeEscola(nomeDaEscola);
							dados.setIdEscola(escolaService.buscarIDEScola(dados.getNomeEscola()));
							dados.setSerie(numSeri);
							
//							System.out.println(
							dados.setBaixoPeso(dadosService.buscarDadosBaixoPesoCidadeEscolaSerie(dados.getIdCidade(),dados.getIdEscola(),dados.getSerie()));
							dados.setNormal(dadosService.buscarDadosNormalCidadeEscolaSerie(dados.getIdCidade(),dados.getIdEscola(),dados.getSerie()));
							dados.setExcessoPeso(dadosService.buscarDadosExcessoPesoCidadeEscolaSerie(dados.getIdCidade(),dados.getIdEscola(),dados.getSerie()));
							dados.setObesidade(dadosService.buscarDadosObesidadeCidadeEscolaSerie(dados.getIdCidade(),dados.getIdEscola(),dados.getSerie()));
							
//							dados.set dadosService.buscarDadosPorCidadeEscolaSerie(dados, nomeDaCidade, nomeDaEscola, numSeri);
							
						}
						
						else{//buscar dados apenas por cidade e escola
							
							dados.setNomeCidade(nomeDaCidade);
							dados.setIdCidade(cidadeService.buscarIDCidade(dados.getNomeCidade()));
							dados.setNomeEscola(nomeDaEscola);
							dados.setIdEscola(escolaService.buscarIDEScola(dados.getNomeEscola()));
							
							dados.setBaixoPeso(dadosService.buscarDadosBaixoPesoCidadeEscola(dados.getIdCidade(),dados.getIdEscola()));
							dados.setNormal(dadosService.buscarDadosNormalCidadeEscola(dados.getIdCidade(),dados.getIdEscola()));
							dados.setExcessoPeso(dadosService.buscarDadosExcessoPesoCidadeEscola(dados.getIdCidade(),dados.getIdEscola()));
							dados.setObesidade(dadosService.buscarDadosObesidadeCidadeEscola(dados.getIdCidade(),dados.getIdEscola()));
						}
						
						exibirRelatórioGrafico(dados);
					}
					
					else{
						Notification.show("Escola uma Escola!");
					}
				}
				
				else{
					Notification.show("Escolha uma cidade!");
					
				}
			}
		});
		
		vlView.addComponents(comboCidade,filtroEscola,filtroSerie,exibir);
	}

	protected void exibirRelatórioGrafico(DadosRelatorio dados) {
		Label cidade = new Label("Cidade de "+dados.getNomeCidade());
		Label escola = new Label("Escola "+dados.getNomeEscola());
		
		vlGrafico.addComponents(cidade,escola);
		if(dados.getSerie()!=0){
			
			Label serie = new Label("Serie"+String.valueOf(dados.getSerie()));
			vlGrafico.addComponent(serie);
		}
		
		DataSeries dataSeries = new DataSeries().newSeries()
				.add("Baixo Peso", dados.getBaixoPeso()).newSeries()
				.add("Normal", dados.getNormal()).newSeries()
				.add("Excesso de Peso", dados.getExcessoPeso()).newSeries()
				.add("Obesidade", dados.getObesidade());

		SeriesDefaults seriesDefaults = new SeriesDefaults().setRenderer(
				SeriesRenderers.PIE).setRendererOptions(
				new PieRenderer().setShowDataLabels(true));

		Legend legend = new Legend().setShow(true)
				.setPlacement(LegendPlacements.OUTSIDE_GRID)
				.setLocation(LegendLocations.EAST);

		Options options = new Options().setSeriesDefaults(seriesDefaults)
				.setLegend(legend);

		DCharts chart = new DCharts().setDataSeries(dataSeries)
				.setOptions(options).show();

		
		chart.setSizeFull();
		
		vlGrafico.addComponent(chart);
		
	}
}
