package com.view;

import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.metadata.LegendPlacements;
import org.dussan.vaadin.dcharts.metadata.locations.LegendLocations;
import org.dussan.vaadin.dcharts.metadata.renderers.SeriesRenderers;
import org.dussan.vaadin.dcharts.options.Legend;
import org.dussan.vaadin.dcharts.options.Options;
import org.dussan.vaadin.dcharts.options.SeriesDefaults;
import org.dussan.vaadin.dcharts.renderers.series.PieRenderer;

import com.model.DadosRelatorio;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class GraficoView extends VerticalLayout {

	DadosRelatorio dados;
	Label cidade;
	Label escola;
	Label serie;
	
	public GraficoView(DadosRelatorio dadosrecebidos) {
		
		
		gerarGrafrico(dados);
	}

	
	
	public void gerarGrafrico(DadosRelatorio dados) {

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
System.out.println("grafico");
		this.addComponent(chart);

	}
}
