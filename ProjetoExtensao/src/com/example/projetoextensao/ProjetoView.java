package com.example.projetoextensao;

import java.util.ArrayList;
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

import com.model.Imc;
import com.model.Serie;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.ClassResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.view.SubWindowSerie;



@SuppressWarnings("serial")
public class ProjetoView  extends VerticalLayout {
	
	final HorizontalLayout hlayout = new HorizontalLayout();
	final HorizontalLayout emBaixo= new HorizontalLayout();
	
	final FormLayout interno = new FormLayout();
	
	final VerticalLayout vlEsquerda  = new VerticalLayout();
	final VerticalLayout vlMeio= new VerticalLayout();
	final HorizontalLayout vlDireita = new HorizontalLayout();
	
	String admRoot = "dede";
	String senhaRoot = "dede";
	Button logout= new  Button("Logout");
	Banco banco ;
		
	public ProjetoView() {
		banco = new Banco();
//		
		hlayout.addComponent(vlEsquerda);
		hlayout.addComponent(vlMeio);
		hlayout.addComponent(vlDireita);
		emBaixo.addComponent(logout());
		
		hlayout.setSpacing(true);
		this.setSizeFull();
		
		viewAutenticacao();
//		ViewUsuario();
//		viewAdminitrador();
		setSizeFull();
	}
	
	private void viewAutenticacao() {
		
		this.removeAllComponents();
		
		HorizontalLayout layoutLogin = new HorizontalLayout();
		HorizontalLayout h1 = new HorizontalLayout();
		HorizontalLayout h2 = new HorizontalLayout();
		HorizontalLayout h3 = new HorizontalLayout();
		
		TextField login= new TextField("Login");
		PasswordField senha= new PasswordField("Senha");

				
		VerticalLayout layoutInt = new VerticalLayout();
		Image img =new Image(null,new ClassResource("pr.gif"));
		img.setWidth("30px");
		img.setHeight("30px");

		Panel panel = new Panel("Login");
		panel.setStyleName("painel");
		
		h1.addComponent(login);
		h2.addComponent(senha);

		Button entrar = new Button("Entrar", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				Autenticacao autent = new Autenticacao();
				try{	
						
						if(login.getValue().toString().equals(admRoot) && senha.getValue().toString().equals(senhaRoot)){
							viewAdmRoot();
						}
					
						else if(autent.autentAdmin(login.getValue(),senha.getValue())==true){
							viewAdminitrador();
						}
						
						else if(autent.autentUsuario(login.getValue(),senha.getValue())==true){
							ViewUsuario();
						}
						
						else{
							Notification.show("Login ou senha Ivalido!");
						}
				}catch(Exception e){
						System.out.println( "Erro de Autenticação "+ e);
					}
			}
		});
		
		entrar.setClickShortcut(KeyCode.ENTER);
		h3.addComponent(entrar);
		h3.addComponent(img);
		
		layoutInt.addComponent(h1);
		layoutInt.setComponentAlignment(h1, Alignment.MIDDLE_CENTER);
		layoutInt.addComponent(h2);
		layoutInt.setComponentAlignment(h2, Alignment.MIDDLE_CENTER);
		layoutInt.addComponent(h3);
		layoutInt.setComponentAlignment(h3, Alignment.MIDDLE_CENTER);
		panel.setContent(layoutInt);
		panel.setStyleName("login");
		panel.setWidth("500px");
		panel.setHeight("200px");
		
		
		layoutInt.setSizeFull();		
		layoutLogin.addComponent(panel);
		this.addComponent(layoutLogin);
		this.setComponentAlignment(layoutLogin, Alignment.MIDDLE_CENTER);
		this.setSizeFull();
	}
	
	public void ViewUsuario(){
		this.removeAllComponents();
		vlEsquerda.removeAllComponents();
		vlMeio.removeAllComponents();
		vlDireita.removeAllComponents();
//		hlayout.addComponent(interno);
		
		Button escola = new Button("Escola", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				viewEscola();
				
			}
		});
		
		Button aluno = new Button("Aluno", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				viewAluno();
				
			}
		});
		
		Button relatorio = new Button("Relatório", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				viewFiltrarRelatorio();
				
			}
		}); 
		
		MenuBar menu = new MenuBar();
		
		

			
		vlEsquerda.setWidth("100px");
		vlEsquerda.setSpacing(true);
//		vlEsquerda.setSizeFull();
//		vlDireita.setSizeFull();
		vlEsquerda.addComponent(escola);
		vlEsquerda.addComponent(aluno);
		vlEsquerda.addComponent(relatorio);
		vlEsquerda.setStyleName("esquerda");
		hlayout.setSpacing(true);
		
		this.addComponent(hlayout);
		this.addComponent(emBaixo);
		this.setComponentAlignment(emBaixo, Alignment.BOTTOM_RIGHT);
		
		
		
	}

	
	public void viewAluno(){
		vlMeio.removeAllComponents();
		vlDireita.removeAllComponents();
		FormLayout menuAluno = new FormLayout();
		
		Button CadastrarAluno = new Button ("Cadastrar", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
			viewCadastroAluno();
				
			}
		});
		
		Button EditarAluno= new Button ("Editar", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
//			editar aluno
				
			}
		});
		
		Button ExcluirAluno = new Button ("Excluir", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});

		Button BuscarAluno= new Button ("Buscar", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		menuAluno.addComponent(CadastrarAluno);
		menuAluno.addComponent(EditarAluno);
		menuAluno.addComponent(ExcluirAluno);
		menuAluno.addComponent(BuscarAluno);
		menuAluno.setSpacing(true);
		
		vlMeio.addComponent(menuAluno);
	
	}

	public void viewCadastroAluno(){
		vlDireita.removeAllComponents();
		
		VerticalLayout vl =new VerticalLayout();
		HorizontalLayout a = new HorizontalLayout();
		HorizontalLayout b = new HorizontalLayout();
		HorizontalLayout c = new HorizontalLayout();
		HorizontalLayout d = new HorizontalLayout();
		HorizontalLayout e = new HorizontalLayout();
		HorizontalLayout f = new HorizontalLayout();
		HorizontalLayout g = new HorizontalLayout();
		HorizontalLayout h = new HorizontalLayout();
		HorizontalLayout i = new HorizontalLayout();
		HorizontalLayout i2 = new HorizontalLayout();
		VerticalLayout j = new VerticalLayout();
		HorizontalLayout j2 = new HorizontalLayout();
		HorizontalLayout l = new HorizontalLayout();
		HorizontalLayout m = new HorizontalLayout();
		HorizontalLayout n = new HorizontalLayout();
		HorizontalLayout o = new HorizontalLayout();
		HorizontalLayout p = new HorizontalLayout();
		HorizontalLayout q = new HorizontalLayout();
		
		ComboBox  cidade = new ComboBox("Cidade");
		banco.buscarCidade(cidade);
		
		
		ComboBox  escola = new ComboBox();
		escola.setWidth("400px");
		escola.setCaption("Escola");
		
		
		ComboBox ensino = new ComboBox("Ensino");
		ensino.addItem("Fundamental");
		ensino.addItem("Medio");
		
		ComboBox  serie = new ComboBox("Série");
		serie.setWidth("100px");
		
		ComboBox  turma = new ComboBox("Turma");
		banco.buscarTurma(turma);
		turma.setWidth("50px");

		TextField  endereco = new TextField("Endereço");
		
		
		TextField  bairro = new TextField("Bairro");
		TextField  cep = new TextField("CEP");
		TextField  telefone = new TextField("Telefone");
		TextField  email = new TextField("Email");
		
		TextField  nomeAluno = new TextField("Nome completo do Aluno");
		nomeAluno.setWidth("250px");
		
		ComboBox sexo = new ComboBox("Sexo");
		sexo.addItem("Masculino");
		sexo.addItem("Feminino");

		TextField  idade = new TextField("Idade");
		
		DateField dataNascimento = new DateField("Data de Nascimento");
		dataNascimento.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				idade.setValue(	banco.calcularIdade(dataNascimento.getValue().toString()));
				
			}
		});
		
		
		idade.setWidth("50px");
//		dataNascimento.setDateFormat("yyyy-MM-dd");
		TextField  nomeMae = new TextField("Nome da Mae");
		TextField  nomePai = new TextField("Nome do Pai");
		DateField dataDeAvaliacao = new DateField("Data de Avaliação");
		TextField horario = new TextField("Horario");
		TextField temperatura = new TextField("Temperatura");
		TextField modalidadeEspostiva = new TextField("Quantas Modalidade Esportiva Praticada com Frequência");
		
		TextField frenquenciaSemanal= new TextField("Frequência Semanal");
		TextField duracaoMediaCadaSessao= new TextField("Duração média de cada sessão");
		TextField TempoPratica= new TextField("Tempo de Prática");
		Label deficiencia = new Label("Apresenta alguma deficiência?");
		CheckBox sim = new CheckBox("Sim");
		CheckBox nao = new CheckBox("Não");
		
		TextField deficienciaDesc = new TextField("Qual?");
		
		
		TextField  massaCorporal= new TextField("Massa Corporal");
		TextField  estatura = new TextField("Estatura");
		TextField  envergadura = new TextField("Envergadura");
		TextField  sentarAlcancar = new TextField("Sentar e Alcançar");
		TextField  abdominal = new TextField("Abdominal");
		TextField  minuto6 = new TextField("6 minutos");
		TextField  saltoEmDistancia = new TextField("Salto em distância");
		TextField  arremesso = new TextField("Arremesso de Medicineball");
		TextField  quadrado = new TextField("Quadrado");
		TextField  corrida= new TextField("Corrida de 20 metros");
		
		
		
		Button salvar = new Button("Salvar", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				String cidadeAluno = cidade.getValue().toString();
				String nome= nomeAluno.getValue();
				String sexoAluno = sexo.getValue().toString();
				String escolaAluno = escola.getValue().toString();
				String ensinostr = ensino.getValue().toString();
				int seri=  Integer.parseInt(serie.getValue().toString());
				char turm = turma.getValue().toString().charAt(0);
				double peso = Double.parseDouble(massaCorporal.getValue().toString());
				double altur = Double.parseDouble(estatura.getValue().toString());
				int idad = Integer.parseInt(idade.getValue().toString());
				//System.out.println("data de nascimento = "+dataNascimento.get());
				banco.cadastrarAluno(nome,idad,sexoAluno,cidadeAluno,escolaAluno,ensinostr,seri,turm,peso,altur);
				Notification.show("Cadastrado com Sucesso!");
			}
		});
		sim.setImmediate(true);
		sim.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				
				if(sim.getValue()==true){
					nao.setValue(false);
					j2.addComponent(deficienciaDesc);
	
				}
				else if(sim.getValue()==false){
					j2.removeComponent(deficienciaDesc);
				}
				
				
			}
		});
		
		nao.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {

				if(nao.getValue()==true){
					sim.setValue(false);
					j2.removeComponent(deficienciaDesc);
					
				}
				
			}
		});
		
		
		cidade.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				String str = (String)cidade.getValue();
					if(str!=null){
						banco.buscarNomesEscolas(escola,cidade.getValue().toString());
						
					}
					else{
						escola.removeAllItems();
					}
					
					
			}
		});
		
		escola.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				int idEscola = banco.buscarIdEscola(escola.getValue().toString());
				banco.buscarSeries(serie,idEscola);
				
			}
		});
		
		dataNascimento.addValueChangeListener(new  ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(dataNascimento.getValue().toString()!=null){
					idade.setValue(banco.calcularIdade(dataNascimento.getValue().toString()));
					
				}
				
				else{
					idade.setValue("");
				}
				
			}
		});

		
		salvar.setClickShortcut(KeyCode.ENTER);
		a.addComponent(cidade);
		a.addComponent(escola);
		a.setSpacing(true);
		
		
		b.addComponent(ensino);
		b.addComponent(serie);
		b.addComponent(turma);
		b.setSpacing(true);
		
		c.addComponent(endereco);

		
		d.addComponent(bairro);
		d.addComponent(cep);
		d.setSpacing(true);
		
		e.addComponent(telefone);
		e.addComponent(email);
		e.setSpacing(true);
		
		f.addComponent(nomeAluno);
		
		f.addComponent(sexo);
		f.addComponent(dataNascimento);
		f.addComponent(idade);
		f.setSpacing(true);
		
		g.addComponent(nomeMae);
		g.addComponent(nomePai);
		g.setSpacing(true);
		
		h.addComponent(dataDeAvaliacao);
		h.addComponents(dataDeAvaliacao,horario);
		h.addComponent(temperatura);
		h.setSpacing(true);
		
		i.addComponents(modalidadeEspostiva,frenquenciaSemanal,duracaoMediaCadaSessao,TempoPratica);
		i.setSpacing(true);
		
		i2.addComponents(duracaoMediaCadaSessao,TempoPratica);
		i2.setSpacing(true);
		
		j.addComponent(deficiencia);
		j2.addComponents(sim,nao);
		
		l.addComponents(massaCorporal,minuto6);
		l.setSpacing(true);
		
		m.addComponents(estatura,saltoEmDistancia);
		m.setSpacing(true);
		
		n.addComponents(envergadura,arremesso);
		n.setSpacing(true);
		
		o.addComponents(sentarAlcancar,quadrado);
		o.setSpacing(true);
		
		p.addComponents(abdominal,corrida);p.setSpacing(true);
		
		q.addComponent(salvar);
		
		vl.addComponent(a);
		vl.addComponent(b);
		vl.addComponent(c);
		vl.addComponent(d);
		vl.addComponent(e);
		vl.addComponent(f);
		vl.addComponent(g);
		vl.addComponent(h);
		vl.addComponent(i);
		vl.addComponent(i2);
		vl.addComponent(j);
		vl.addComponent(j2);
		vl.addComponent(l);
		vl.addComponent(m);
		vl.addComponent(n);
		vl.addComponent(o);
		vl.addComponent(p);
		vl.addComponent(q);
		
		
		
		vlDireita.addComponent(vl);
		vlDireita.setSizeFull();
		setSizeFull();

	}
	
	public void viewAdminitrador(){
		this.removeAllComponents();
		vlEsquerda.removeAllComponents();
		vlMeio.removeAllComponents();
		emBaixo.removeAllComponents();
		
		FormLayout form = new FormLayout();
			
		Button cadastrarUsuario = new Button("Cadastrar Usuário",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				viewCadastrarUsuario();
				
			}
		});
		
		
		Button tabela = new Button ("Tabelas de Avaliação", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				viewMenuTabela();
				
			}
		});
		
		Button logout = new Button ("Logout",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				viewAutenticacao();
				
			}
		});
		
		Button cidade =new Button("Cidade",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				viewCrudCidade();
				
			}
		});

		Button bairro =new Button("Bairro",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				
			}
		});
		
		Button estado =new Button("Estado",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				
			}
		});

		Button serie =new Button("Série",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				viewMenuCrudSerie();
				
			}
		});
		
		Button turma =new Button("Turma",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				
			}
		});
		
		Button serieTurma=new Button("Série Turma",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				
			}
		});

		
		Button sexo =new Button("Sexo",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				
			}
		});



		

		form.addComponent(cadastrarUsuario);
		form.addComponent(tabela);
		form.addComponent(estado);
		form.addComponent(cidade);
		form.addComponent(bairro);
		form.addComponent(serie);
		form.addComponent(turma);
		form.addComponent(serieTurma);
		form.addComponent(sexo);
		

		
		vlEsquerda.addComponent(form);
		emBaixo.addComponent(logout);
	
		vlEsquerda.setWidth("200px");

		this.addComponent(hlayout);
		this.addComponent(emBaixo);
		this.setComponentAlignment(emBaixo,Alignment.BOTTOM_RIGHT);
		
	}

	public void viewAdmRoot(){
		this.removeAllComponents();
		emBaixo.removeAllComponents();
		vlEsquerda.removeAllComponents();
		vlMeio.removeAllComponents();
		
		FormLayout form = new FormLayout();
			
		Button cadastrarUsuario = new Button("Cadastrar Usuário",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				viewCadastrarUsuario();
				
			}
		});
		
		
		Button cadastrarAdministrador = new Button("Cadastrar Administrador",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				viewCadastrarAdmRoot();
				
			}
		});
		
		
		
		Button tabela = new Button ("Inserir dados na Tabela", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				viewMenuTabela();
				
			}
		});
		

		form.addComponent(cadastrarAdministrador);
		form.addComponent(cadastrarUsuario);
		form.addComponent(tabela);
		form.addComponent(logout());

		
		vlEsquerda.addComponent(form);
		
	
		vlEsquerda.setWidth("200px");

		this.addComponent(hlayout);
		this.addComponent(emBaixo);
		this.setComponentAlignment(emBaixo,Alignment.BOTTOM_RIGHT);

	}
	
	public void viewExcluirAluno(){
		
	}

	public void viewCadastrarUsuario(){
		vlDireita.removeAllComponents();
		vlMeio.removeAllComponents();

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
						persistencia.adicionarUsuario(nome.getValue(), login.getValue(), senha.getValue());
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
		vlMeio.addComponent(menucadastro);
		
	
		
	}

	public void viewCadastrarAdmRoot(){
		vlDireita.removeAllComponents();
		vlMeio.removeAllComponents();

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
		vlMeio.addComponent(menucadastro);
		
	}
	
	public Button logout(){
		logout.addClickListener( new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				viewAutenticacao();
				
			}
		});
		
		return logout;
			
	}
	
	public void exibirRelatórioGrafico(DadosRelatorio dados,String cidade,String escola, int serie){
		vlDireita.removeAllComponents();
		vlDireita.removeAllComponents();
		
		
		Number baixopes = dados.getBaixoPeso();
		Number sobrepes = dados.getSobrePeso();
		Number obesi = dados.getObesidade();
		
		DataSeries dataSeries = new DataSeries()
		.newSeries()
		.add("Baixo Peso", baixopes)
		.newSeries()
		.add("Excesso de Peso", sobrepes)
		.newSeries()
		.add("Obesidade", obesi);
		
		SeriesDefaults seriesDefaults = new SeriesDefaults()
		.setRenderer(SeriesRenderers.PIE)
		.setRendererOptions(
			new PieRenderer()
				.setShowDataLabels(true));

	Legend legend = new Legend()
		.setShow(true)
		.setPlacement(LegendPlacements.OUTSIDE_GRID)
		.setLocation(LegendLocations.EAST);
	
		

	Options options = new Options()
		.setSeriesDefaults(seriesDefaults)
		.setLegend(legend);

	DCharts chart = new DCharts()
		.setDataSeries(dataSeries)
		.setOptions(options)
		.show();
		
		
	/*	---------chart vaadin velho vencido

		Chart chart = new Chart(ChartType.PIE);
		Number baixopes = dados.getBaixoPeso();
		Number sobrepes = dados.getSobrePeso();
		Number obesi = dados.getObesidade();
		Configuration conf = chart.getConfiguration();
//		System.out.println("Baixo peso = "+baixopes);
//		System.out.println("sobrepeso = "+sobrepes);
//		System.out.println("obesi= "+obesi);
		
//				if(escola!=null){
//					if(serie!=0){
		String titu = "Relatório da Cidade de "+cidade;
		String subtitu ="Escola "+escola+", "+serie+"ª Serie ";

//		Legend legenda = new Legend();
//		legenda.setEnabled(true);
						conf.setTitle(titu);
						conf.setSubTitle(subtitu);
						conf.getLegend().setEnabled(true);
//						PlotOptionsLine plotOptions = new PlotOptionsLine();
//						plotOptions.setMarker(new Marker(false));
//						conf.setPlotOptions(plotOptions);
//					

		
		DataSeries series = new DataSeries();
		series.add(new DataSeriesItem("Baixo Peso", baixopes));
		series.add(new DataSeriesItem("Sobrepeso", sobrepes));
		series.add(new DataSeriesItem("Obesidade", obesi));
//		series.add(new DataSeriesItem())
		conf.addSeries(series);*/
		
//	chart.setWidth("350px");
//	chart.setHeight("350px");
	
		vlDireita.addComponent(chart);
	}
	
	public void viewFiltrarRelatorio(){
		vlMeio.removeAllComponents();
		vlDireita.removeAllComponents();

		
		ComboBox  comboCidade = new ComboBox("Cidade");
		banco.buscarCidade(comboCidade);
		
		ComboBox filtroEscola = new ComboBox("Escola");
		filtroEscola.setEnabled(false);
		
		ComboBox filtroSerie = new ComboBox("Série");
		filtroSerie.setEnabled(false);
		
		
		
		comboCidade.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
//				vlDireita.addComponent(filtroEscola,"left:5px;top:80px;");
				filtroEscola.setEnabled(true);
				banco.buscarNomesEscolas(filtroEscola,comboCidade.getValue().toString());
						
				
			}
		});
		
		filtroEscola.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
//				if(filtroEscola.getValue().toString()!=null){
					
					int idEscola =banco.buscarIdEscola((String)filtroEscola.getValue());
					banco.buscarSeries(filtroSerie,idEscola);
					vlMeio.addComponent(filtroSerie);
					
//				}
//				else{
//					vlDireita.removeComponent(filtroSerie);
//				}
				
				
			}
		});
		
		

		
				
		Button exibir = new Button("Exibir",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				DadosRelatorio dados = new DadosRelatorio();
				String nomeDaCidade = (String)comboCidade.getValue();
				String nomeDaEscola =null;
				int numSeri  = 0;
				
				if(nomeDaCidade!=null){
					 nomeDaEscola = (String)filtroEscola.getValue();
					if(nomeDaEscola!=null){
//						banco.buscarDadosPorCidadeEscola(dados,nomeDaCidade,nomeDaEscola);
//						System.out.println("baixo peso = "+dados.getBaixoPeso());
//						System.out.println("sobrepeso = "+dados.getSobrePeso());
//						System.out.println("Obesidade = "+dados.getObesidade());
//						
							if(filtroSerie.getValue()!=null) {
								numSeri = Integer.parseInt(filtroSerie.getValue().toString());
								banco.buscarDadosPorCidadeEscolaSerie(dados, nomeDaCidade, nomeDaEscola, numSeri);
								
								exibirRelatórioGrafico(dados, nomeDaCidade,nomeDaEscola,numSeri);
							}
							
							else{
//								exibirRelatórioGrafico(dados, nomeDaCidade,nomeDaEscola,0);
								Notification.show("Escolha uma Série!");
							}
						
						
					}
					
					else{
//						banco.buscarDadosPorCidade(dados,nomeDaCidade);
						//banco.filtrarCidadeGrafico(dados);
//						exibirRelatórioGrafico(dados, nomeDaCidade, nomeDaEscola,numSeri);
						Notification.show("Escola uma Escola!");
					}
				}
				
				else{
					Notification.show("Escolha uma cidade!");
					
				}
			}
		});
		
		vlMeio.addComponent(comboCidade);
		vlMeio.addComponent(filtroEscola);
		vlMeio.addComponent(filtroSerie);
		vlMeio.addComponent(exibir);
		vlMeio.setSpacing(true);

	}
	
	public void viewEscola(){
		vlDireita.removeAllComponents();
		vlMeio.removeAllComponents();
		
		FormLayout subVlDireita = new FormLayout();
		
		Button CadastrarEscola = new Button ("Cadastrar", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
			viewCadastrarEscola();
				
			}

		});
		
		Button EditarEscola= new Button ("Editar", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
//			editar aluno
				
			}
		});
		
		Button ExcluirEscola = new Button ("Excluir", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});

		Button BuscarEscola= new Button ("Buscar", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});

		
		subVlDireita.addComponent(CadastrarEscola);
		subVlDireita.addComponent(EditarEscola);
		subVlDireita.addComponent(ExcluirEscola);
		subVlDireita.addComponent(BuscarEscola);
		vlMeio.addComponent(subVlDireita);

	}
	
	public void viewCadastrarEscola(){
		vlDireita.removeAllComponents();

		VerticalLayout vl =new VerticalLayout();
		
		VerticalLayout vlSeriesFun = new VerticalLayout();
		HorizontalLayout hlserief1 = new HorizontalLayout();
		HorizontalLayout hlserief2 = new HorizontalLayout();
		HorizontalLayout hlserief3 = new HorizontalLayout();
		HorizontalLayout hlserief4 = new HorizontalLayout();
		HorizontalLayout hlserief5 = new HorizontalLayout();
		HorizontalLayout hlserief6 = new HorizontalLayout();
		HorizontalLayout hlserief7 = new HorizontalLayout();
		HorizontalLayout hlserief8 = new HorizontalLayout();
		HorizontalLayout hlserief9 = new HorizontalLayout();
		
		VerticalLayout vlSeriesMedio =new VerticalLayout();
		HorizontalLayout hlserieMedio1 = new HorizontalLayout();
		HorizontalLayout hlserieMedio2 = new HorizontalLayout();
		HorizontalLayout hlserieMedio3 = new HorizontalLayout();
//---------------------variaveis para persisterem com dao
		String daoFundamental;

		
		//------------série 1
		//checkBox de turmas da série 1
		CheckBox a1f = new CheckBox("A");
		CheckBox b1f = new CheckBox("B");
		CheckBox c1f = new CheckBox("C");
		CheckBox d1f = new CheckBox("D");

		CheckBox f1 = new CheckBox("1ª");
		hlserief1.addComponent(f1);
		hlserief1.setSpacing(true);
		f1.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				
				if(f1.getValue()!=false){
					
					hlserief1.addComponents(a1f,b1f,c1f,d1f);
						
				}
				
				else{
					hlserief1.removeComponent(a1f);
					hlserief1.removeComponent(b1f);
					hlserief1.removeComponent(c1f);
					hlserief1.removeComponent(d1f);
				}

			}
			
		});
		
		
				
		//----fim série 1
		
		
		//-----série 2-----
		CheckBox a2f = new CheckBox("A");
		CheckBox b2f = new CheckBox("B");
		CheckBox c2f = new CheckBox("C");
		CheckBox d2f = new CheckBox("D");
		
		CheckBox f2 = new CheckBox("2ª");
		
		hlserief2.addComponent(f2);
		hlserief2.setSpacing(true);
		f2.addValueChangeListener(new ValueChangeListener() { 
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(f2.getValue()!=false){
					hlserief2.addComponents(a2f,b2f,c2f,d2f);
				}
				
				else{
					hlserief2.removeComponent(a2f);
					hlserief2.removeComponent(b2f);
					hlserief2.removeComponent(c2f);
					hlserief2.removeComponent(d2f);
				}
				
			}
			
		
		});
		
		//fim série 2 

		//------série 3
		
		CheckBox a3f = new CheckBox("A");
		CheckBox b3f = new CheckBox("B");
		CheckBox c3f = new CheckBox("C");
		CheckBox d3f = new CheckBox("D");
		
		CheckBox f3 = new CheckBox("3ª");
		
		hlserief3.addComponent(f3);
		hlserief3.setSpacing(true);
		f3.addValueChangeListener(new ValueChangeListener() { 
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(f3.getValue()!=false){
					hlserief3.addComponents(a3f,b3f,c3f,d3f);
					
				}
				
				else{
					hlserief3.removeComponent(a3f);
					hlserief3.removeComponent(b3f);
					hlserief3.removeComponent(c3f);
					hlserief3.removeComponent(d3f);
				}
				
			}
		});
		
		//-----fim série 3
		
		//------série 4
		CheckBox a4f = new CheckBox("A");
		CheckBox b4f = new CheckBox("B");
		CheckBox c4f = new CheckBox("C");
		CheckBox d4f = new CheckBox("D");
		
		CheckBox f4 = new CheckBox("4ª");
		
		hlserief4.addComponent(f4);
		hlserief4.setSpacing(true);
		f4.addValueChangeListener(new ValueChangeListener() { 
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(f4.getValue()!=false){
					
					hlserief4.addComponents(a4f,b4f,c4f,d4f);
				}
				
				else{
					hlserief4.removeComponent(a4f);
					hlserief4.removeComponent(b4f);
					hlserief4.removeComponent(c4f);
					hlserief4.removeComponent(d4f);
				}

				
			}
		});		
		
		
		//---------fim série 4
		
		//série 5---
		CheckBox a5f = new CheckBox("A");
		CheckBox b5f = new CheckBox("B");
		CheckBox c5f = new CheckBox("C");
		CheckBox d5f = new CheckBox("D");
		
		CheckBox f5 = new CheckBox("5ª");
		
		hlserief5.addComponent(f5);
		hlserief5.setSpacing(true);
		f5.addValueChangeListener(new ValueChangeListener() { 
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(f5.getValue()!=false){
					
					hlserief5.addComponents(a5f,b5f,c5f,d5f);
				}
				
				else{
					hlserief5.removeComponent(a5f);
					hlserief5.removeComponent(b5f);
					hlserief5.removeComponent(c5f);
					hlserief5.removeComponent(d5f);
				}

				
			}
		});
		
		

		//----------------fim série 5
		
		//---------------série 6
		
		CheckBox a6f = new CheckBox("A");
		CheckBox b6f = new CheckBox("B");
		CheckBox c6f = new CheckBox("C");
		CheckBox d6f = new CheckBox("D");
		
		CheckBox f6 = new CheckBox("6ª");
		
		hlserief6.addComponent(f6);
		hlserief6.setSpacing(true);
		f6.addValueChangeListener(new ValueChangeListener() { 
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(f6.getValue()!=false){
					
					hlserief6.addComponents(a6f,b6f,c6f,d6f);
				}
				
				else{
					hlserief6.removeComponent(a6f);
					hlserief6.removeComponent(b6f);
					hlserief6.removeComponent(c6f);
					hlserief6.removeComponent(d6f);
				}

				
			}
		});
		
		
		
		//---------fim série 6
		
		//série 7

		CheckBox a7f = new CheckBox("A");
		CheckBox b7f = new CheckBox("B");
		CheckBox c7f = new CheckBox("C");
		CheckBox d7f = new CheckBox("D");
		
		CheckBox f7 = new CheckBox("7ª");
		
		hlserief7.addComponent(f7);
		hlserief7.setSpacing(true);
		f7.addValueChangeListener(new ValueChangeListener() { 
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(f7.getValue()!=false){
					
					hlserief7.addComponents(a7f,b7f,c7f,d7f);
				}
				
				else{
					hlserief7.removeComponent(a7f);
					hlserief7.removeComponent(b7f);
					hlserief7.removeComponent(c7f);
					hlserief7.removeComponent(d7f);
				}

				
			}
		});
		
				
		//------fim série 7
		
		//------------série 8
		
		CheckBox a8f = new CheckBox("A");
		CheckBox b8f = new CheckBox("B");
		CheckBox c8f = new CheckBox("C");
		CheckBox d8f = new CheckBox("D");
		
		CheckBox f8 = new CheckBox("8ª");
		
		hlserief8.addComponent(f8);
		hlserief8.setSpacing(true);
		f8.addValueChangeListener(new ValueChangeListener() { 
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(f8.getValue()!=false){
					hlserief8.addComponents(a8f,b8f,c8f,d8f);
					
				}
				
				else{
					hlserief8.removeComponent(a8f);
					hlserief8.removeComponent(b8f);
					hlserief8.removeComponent(c8f);
					hlserief8.removeComponent(d8f);
				}

				
			}
		});
		
		
		
		//------fim série 8
		
		//----série 9 
		
		
		CheckBox a9f = new CheckBox("A");
		CheckBox b9f = new CheckBox("B");
		CheckBox c9f = new CheckBox("C");
		CheckBox d9f = new CheckBox("D");
		
		CheckBox f9 = new CheckBox("9ª");
		
		hlserief9.addComponent(f9);
		hlserief9.setSpacing(true);
		f9.addValueChangeListener(new ValueChangeListener() { 
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(f9.getValue()!=false){
					hlserief9.addComponents(a9f,b9f,c9f,d9f);
					
				}
				
				else{
					hlserief9.removeComponent(a9f);
					hlserief9.removeComponent(b9f);
					hlserief9.removeComponent(c9f);
					hlserief9.removeComponent(d9f);
				}

				
			}
		});
		
		//-----fim serie 9
		
		//-----------series do ensino médio
		
		//----série 1 do ensino médio
		CheckBox a1m = new CheckBox("A");
		CheckBox b1m = new CheckBox("B");
		CheckBox c1m = new CheckBox("C");
		CheckBox d1m = new CheckBox("D");
		
		CheckBox m1 = new CheckBox("1ª");
		
		hlserieMedio1.addComponent(m1);
		hlserieMedio1.setSpacing(true);
		m1.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(m1.getValue()!=false){
					hlserieMedio1.addComponents(a1m,b1m,c1m,d1m);
				}
				
				else{
					hlserieMedio1.removeComponent(a1m);
					hlserieMedio1.removeComponent(b1m);
					hlserieMedio1.removeComponent(c1m);
					hlserieMedio1.removeComponent(d1m);
				}
				
			}
		});
		
		
		
		//fim serie 1 ensino médio
		
		//------------série 2 ensino médio
		CheckBox a2m = new CheckBox("A");
		CheckBox b2m = new CheckBox("B");
		CheckBox c2m = new CheckBox("C");
		CheckBox d2m = new CheckBox("D");
		
		CheckBox m2 = new CheckBox("2ª");
		
		hlserieMedio2.addComponent(m2);
		hlserieMedio2.setSpacing(true);
		m2.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(m2.getValue()!=false){
					hlserieMedio2.addComponents(a2m,b2m,c2m,d2m);
				}
				
				else{
					hlserieMedio2.removeComponent(a2m);
					hlserieMedio2.removeComponent(b2m);
					hlserieMedio2.removeComponent(c2m);
					hlserieMedio2.removeComponent(d2m);
				}
				
				
			}
		});
		
		
		//fim serie 2 ensino médio
		
		//------------série 3 ensino médio
		CheckBox a3m = new CheckBox("A");
		CheckBox b3m = new CheckBox("B");
		CheckBox c3m = new CheckBox("C");
		CheckBox d3m = new CheckBox("D");
		
		CheckBox m3 = new CheckBox("3ª");
		
		hlserieMedio3.addComponent(m3);
		hlserieMedio3.setSpacing(true);
		m3.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(m3.getValue()!=false){
					hlserieMedio3.addComponents(a3m,b3m,c3m,d3m);
				}
				
				else{
					hlserieMedio3.removeComponent(a3m);
					hlserieMedio3.removeComponent(b3m);
					hlserieMedio3.removeComponent(c3m);
					hlserieMedio3.removeComponent(d3m);
				}

				
			}
		});
		
		//----------fim série 3 do ensino médio
		
		
		
		TextField nomeEscola = new TextField("Nome da Escola");
		nomeEscola.setWidth("300px");
		
		
		CheckBox fundamental = new CheckBox("Fundamental");
		CheckBox medio = new CheckBox("Medio");
		
		

		fundamental.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(fundamental.getValue()==false){
					
					vlSeriesFun.removeAllComponents();
					
				}
				
				else {
					
					HorizontalLayout descricao = new HorizontalLayout();
					Label  lbseries = new Label("Séries");
					Label  lbturma = new Label("Turmas");
					descricao.addComponents(lbseries,lbturma);
					descricao.setSpacing(true);
					
					vlSeriesFun.setSpacing(true);
					vlSeriesFun.addComponents(descricao,hlserief1,hlserief2,hlserief3,hlserief4,hlserief5,hlserief6,hlserief7,hlserief8,hlserief9);
				}
				
			}
		});		

		medio.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				
				if(medio.getValue()==false){
					vlSeriesMedio.removeAllComponents();
				}
				
				else {
					HorizontalLayout descricao = new HorizontalLayout();
					Label  lbseries = new Label("Séries");
					Label  lbturma = new Label("Turmas");
					descricao.setSpacing(true);
					descricao.addComponents(lbseries,lbturma);
					
					vlSeriesMedio.setSpacing(true);
					vlSeriesMedio.addComponents(descricao,hlserieMedio1,hlserieMedio2,hlserieMedio3);
					
				}
				
			}
		});
		
		
				
		ComboBox  cidade = new ComboBox("Cidade");
		banco.buscarCidade(cidade);

		
		Button salvar = new Button("Salvar",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				if(nomeEscola.getValue().toString()!="" && cidade.getValue()!=null){
					
					if(fundamental.getValue()|| medio.getValue()){
						int idCidade= 0;
						String ensinoFundametal ="Fundamental";
						idCidade = banco.buscarIdCidade(cidade.getValue().toString());
						banco.cadastrarEscola(nomeEscola.getValue().toString(),idCidade);
						
						//-----cadastro das séries e turmas do ensino fundamental na escola na tabela serie_turma_escola
						if(f1.getValue()){
						
							if(a1f.getValue()){
									banco.cadastrarSerieTurmaEscola('A',1,nomeEscola.getValue().toString(),ensinoFundametal);
								
								if(b1f.getValue()){
										banco.cadastrarSerieTurmaEscola('B',1,nomeEscola.getValue().toString(),ensinoFundametal);
									
									if(c1f.getValue()){
											banco.cadastrarSerieTurmaEscola('C',1,nomeEscola.getValue().toString(),ensinoFundametal);
											
										if(d1f.getValue()){
											banco.cadastrarSerieTurmaEscola('D',1,nomeEscola.getValue().toString(),ensinoFundametal);
										}
									}
								}
							}
						}
							
						if(f2.getValue()){
						
								if(a2f.getValue()){
									banco.cadastrarSerieTurmaEscola('A',2,nomeEscola.getValue().toString(),ensinoFundametal);
								
									if(b2f.getValue()){
										banco.cadastrarSerieTurmaEscola('B',2,nomeEscola.getValue().toString(),ensinoFundametal);
									
										if(c2f.getValue()){
											banco.cadastrarSerieTurmaEscola('C',2,nomeEscola.getValue().toString(),ensinoFundametal);
											
											if(d2f.getValue()){
												banco.cadastrarSerieTurmaEscola('D',2,nomeEscola.getValue().toString(),ensinoFundametal);
											}
										}
									}
								}
							}
								
							if(f3.getValue()){
							
									if(a3f.getValue()){
										banco.cadastrarSerieTurmaEscola('A',3,nomeEscola.getValue().toString(),ensinoFundametal);
									
										if(b3f.getValue()){
											banco.cadastrarSerieTurmaEscola('B',3,nomeEscola.getValue().toString(),ensinoFundametal);
										
											if(c3f.getValue()){
												banco.cadastrarSerieTurmaEscola('C',3,nomeEscola.getValue().toString(),ensinoFundametal);
												
												if(d3f.getValue()){
													banco.cadastrarSerieTurmaEscola('D',3,nomeEscola.getValue().toString(),ensinoFundametal);
												}
											}
										}
									}
								}
									
								if(f4.getValue()){
							
									if(a4f.getValue()){
										banco.cadastrarSerieTurmaEscola('A',4,nomeEscola.getValue().toString(),ensinoFundametal);
									
										if(b4f.getValue()){
											banco.cadastrarSerieTurmaEscola('B',4,nomeEscola.getValue().toString(),ensinoFundametal);
										
											if(c4f.getValue()){
												banco.cadastrarSerieTurmaEscola('C',4,nomeEscola.getValue().toString(),ensinoFundametal);
												
												if(d4f.getValue()){
													banco.cadastrarSerieTurmaEscola('D',4,nomeEscola.getValue().toString(),ensinoFundametal);
												}
											}
										}
										
									}
								}
									
								if(f5.getValue()){
									if(a5f.getValue()){
										banco.cadastrarSerieTurmaEscola('A',5,nomeEscola.getValue().toString(),ensinoFundametal);
									
										if(b5f.getValue()){
											banco.cadastrarSerieTurmaEscola('B',5,nomeEscola.getValue().toString(),ensinoFundametal);
										
											if(c5f.getValue()){
												banco.cadastrarSerieTurmaEscola('C',5,nomeEscola.getValue().toString(),ensinoFundametal);
												
												if(d5f.getValue()){
													banco.cadastrarSerieTurmaEscola('D',5,nomeEscola.getValue().toString(),ensinoFundametal);
												}
											}
										}
										
									}
								}
								
								if(f6.getValue()){
									if(a6f.getValue()){
										banco.cadastrarSerieTurmaEscola('A',6,nomeEscola.getValue().toString(),ensinoFundametal);
									
										if(b6f.getValue()){
											banco.cadastrarSerieTurmaEscola('B',6,nomeEscola.getValue().toString(),ensinoFundametal);
										
											if(c6f.getValue()){
												banco.cadastrarSerieTurmaEscola('C',6,nomeEscola.getValue().toString(),ensinoFundametal);
												
												if(d6f.getValue()){
													banco.cadastrarSerieTurmaEscola('D',6,nomeEscola.getValue().toString(),ensinoFundametal);
												}
											}
										}
									}	
								}
									
								if(f7.getValue()){
									
									if(a7f.getValue()){
										banco.cadastrarSerieTurmaEscola('A',7,nomeEscola.getValue().toString(),ensinoFundametal);
									
										if(b7f.getValue()){
											banco.cadastrarSerieTurmaEscola('B',7,nomeEscola.getValue().toString(),ensinoFundametal);
										
											if(c7f.getValue()){
												banco.cadastrarSerieTurmaEscola('C',7,nomeEscola.getValue().toString(),ensinoFundametal);
												
												if(d7f.getValue()){
													banco.cadastrarSerieTurmaEscola('D',7,nomeEscola.getValue().toString(),ensinoFundametal);
												}
											}
										}
										
									}
								}
								
								if(f8.getValue()){
									
									if(a8f.getValue()){
										banco.cadastrarSerieTurmaEscola('A',8,nomeEscola.getValue().toString(),ensinoFundametal);
									
										if(b8f.getValue()){
											banco.cadastrarSerieTurmaEscola('B',8,nomeEscola.getValue().toString(),ensinoFundametal);
										
											if(c8f.getValue()){
												banco.cadastrarSerieTurmaEscola('C',8,nomeEscola.getValue().toString(),ensinoFundametal);
												
												if(d8f.getValue()){
													banco.cadastrarSerieTurmaEscola('D',8,nomeEscola.getValue().toString(),ensinoFundametal);
												}
											}
										}
										
									}
								}
								
								if(f9.getValue()){
									
									if(a9f.getValue()){
										banco.cadastrarSerieTurmaEscola('A',9,nomeEscola.getValue().toString(),ensinoFundametal);
									
										if(b9f.getValue()){
											banco.cadastrarSerieTurmaEscola('B',9,nomeEscola.getValue().toString(),ensinoFundametal);
										
											if(c9f.getValue()){
												banco.cadastrarSerieTurmaEscola('C',9,nomeEscola.getValue().toString(),ensinoFundametal);
												
												if(d9f.getValue()){
													banco.cadastrarSerieTurmaEscola('D',9,nomeEscola.getValue().toString(),ensinoFundametal);
												}
											}
										}
										
									}
								}
								
								
						
					
								
								//-----fim cadastro das séries e turmas do ensino fundamental na escola na tabela serie_turma_escola	
								
								//-----cadastro das séries e turmas do ensino médio na escola na tabela serie_turma_escola
						
							String ensinoMedio = "medio";
							
								if(a1m.getValue()){
									
									if(a1m.getValue()){
										banco.cadastrarSerieTurmaEscola('A',1,nomeEscola.getValue().toString(),ensinoMedio);
									
										if(b1m.getValue()){
											banco.cadastrarSerieTurmaEscola('B',1,nomeEscola.getValue().toString(),ensinoMedio);
										
											if(c1m.getValue()){
												banco.cadastrarSerieTurmaEscola('C',1,nomeEscola.getValue().toString(),ensinoMedio);
												
												if(d1m.getValue()){
													banco.cadastrarSerieTurmaEscola('D',1,nomeEscola.getValue().toString(),ensinoMedio);
												}
											}
										}
										
									}
								}
								
								if(a2m.getValue()){
									
									if(a2m.getValue()){
										banco.cadastrarSerieTurmaEscola('A',2,nomeEscola.getValue().toString(),ensinoMedio);
									
										if(b2m.getValue()){
											banco.cadastrarSerieTurmaEscola('B',2,nomeEscola.getValue().toString(),ensinoMedio);
										
											if(c2m.getValue()){
												banco.cadastrarSerieTurmaEscola('C',2,nomeEscola.getValue().toString(),ensinoMedio);
												
												if(d2m.getValue()){
													banco.cadastrarSerieTurmaEscola('D',2,nomeEscola.getValue().toString(),ensinoMedio);
												}
											}
										}
										
									}
								}
								
								if(a3m.getValue()){
									
									if(a3m.getValue()){
										banco.cadastrarSerieTurmaEscola('A',3,nomeEscola.getValue().toString(),ensinoMedio);
									
										if(b3m.getValue()){
											banco.cadastrarSerieTurmaEscola('B',3,nomeEscola.getValue().toString(),ensinoMedio);
										
											if(c3m.getValue()){
												banco.cadastrarSerieTurmaEscola('C',3,nomeEscola.getValue().toString(),ensinoMedio);
												
												if(d3m.getValue()){
													banco.cadastrarSerieTurmaEscola('D',3,nomeEscola.getValue().toString(),ensinoMedio);
												}
											}
										}
										
									}
								}
								
								
													
						}
					
						else
							Notification.show("Escolha o Nivel do Ensino!");
					}
				
				else
					Notification.show("Informe os Campos Corretamente!");
				}
			
		});
		

		vl.addComponent(nomeEscola);
		
		Label lbEnsino = new Label("Ensino");
		vl.addComponent(lbEnsino);
		
		vl.addComponent(fundamental);
		
		vl.addComponent(vlSeriesFun);
		
		vl.addComponent(medio);
		vl.addComponent(vlSeriesMedio);
		
		vl.addComponent(cidade);
		vl.addComponent(salvar);
		vl.setSpacing(true);
		
		vlDireita.addComponent(vl);
		
	}
	
	public void viewEditarEscola(){
		
	}
	
	public void viewCrudCidade(){
		vlMeio.removeAllComponents();
		vlDireita.removeAllComponents();
		
		FormLayout menuMeio = new FormLayout();
		
		Button cadastrar = new Button("Cadastrar",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				
			}
		});
		
		
		Button editar= new Button("Editar",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				
			}
		});
		
		
		Button excluir= new Button("Excluir",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				
			}
		});
		
		
		Button buscar = new Button("Buscar",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				
			}
		});
		
		menuMeio.addComponent(cadastrar);
		menuMeio.addComponent(editar);
		menuMeio.addComponent(excluir);
		menuMeio.addComponent(buscar);
		
		vlMeio.addComponent(menuMeio);
		
	}
	 	
	public void viewCadastrarCidade(){
		
	}
	
	public void viewMenuTabela(){
		vlDireita.removeAllComponents();
		vlMeio.removeAllComponents();

		FormLayout menuTabela = new FormLayout();
		menuTabela.setWidth("350px");
		
		Button imc = new Button("IMC", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				viewCadastrarIMC();
				
			}
		}); 
		
		Button flexibilidade = new Button("Flexibilidade", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				viewFlexibilidade();
				
			}
		});
		
		Button abdominal= new Button("Abdominal", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				tabelaAbdominalPreencher();
				
			}
		});
		
		Button seisMinutos = new Button("6 Minutos", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				tabela6minutosPreencher();
				
			}
		});
		
		Button saltoEmDistancia= new Button("Salto em Distancia", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				tabelaSaltoEmDistanciaPreencher();
				
			}
		});
		
		Button aMedicineball= new Button("Medicineball", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				tabelaArremessoDeMedicineballPreencher();
				
			}
		});
		
		Button quadrado = new Button("Quadrado", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				tabelaQuadradopreencher();
				
			}
		});
		
		Button corrida= new Button("Corrida 20 Metros", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				tabelaCorrida20metrosPreencher();
				
			}
		});

		menuTabela.addComponent(imc);
		menuTabela.addComponent(flexibilidade);
		menuTabela.addComponent(abdominal);
		menuTabela.addComponent(seisMinutos);
		menuTabela.addComponent(saltoEmDistancia);
		menuTabela.addComponent(aMedicineball);
		menuTabela.addComponent(quadrado);
		menuTabela.addComponent(corrida);

		vlMeio.addComponent(menuTabela);
		
		
		
		
	}
	
	public void viewCadastrarIMC(){
		vlDireita.removeAllComponents();
		VerticalLayout vlImc = new VerticalLayout();
		Imc imcMasc = new Imc();
		
		
		//-----------------------tabela imc masculino ininio
		
		VerticalLayout geralMasc = new VerticalLayout();
		geralMasc.setSpacing(true);
		HorizontalLayout tituloMasc = new HorizontalLayout();
		HorizontalLayout apresTabelaMasc = new HorizontalLayout();
		apresTabelaMasc.setSpacing(true);	
		
		Label nomeTabelaMasc = new Label("IMC Masculino");
		TextField idadeMasc = new TextField("idade");
		TextField baixoPesoMasc = new TextField("baixo Peso");
		TextField normalMasc = new TextField("Normal");
		TextField excessoPesoMasc= new TextField("Excesso de Peso");
		TextField obesidadeMasc = new TextField("Obesidade");
		
		
		tituloMasc.addComponent(nomeTabelaMasc);
		apresTabelaMasc.addComponent(idadeMasc);
		apresTabelaMasc.addComponent(baixoPesoMasc);
		apresTabelaMasc.addComponent(normalMasc);
		apresTabelaMasc.addComponent(excessoPesoMasc);
		apresTabelaMasc.addComponent(obesidadeMasc);
		
		
		geralMasc.addComponent(tituloMasc);
		geralMasc.setComponentAlignment(tituloMasc, Alignment.MIDDLE_CENTER);
		geralMasc.addComponent(apresTabelaMasc);
		
		
			//------------vinculo dos componentes com o objeto imcMasc
		idadeMasc.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				imcMasc.setIdade(Integer.parseInt(idadeMasc.getValue().toString()));
				
			}
		});
		
		baixoPesoMasc.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				imcMasc.setBaixoPeso(Double.parseDouble(baixoPesoMasc.getValue().toString()));
				
			}
		});
		
		normalMasc.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				imcMasc.setNormal(Double.parseDouble(normalMasc.getValue().toString()));
				
			}
		});

		excessoPesoMasc.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				imcMasc.setExcessoPeso(Double.parseDouble(excessoPesoMasc.getValue().toString()));
				
			}
		});

		obesidadeMasc.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				imcMasc.setObesidade(Double.parseDouble(obesidadeMasc.getValue().toString()));
				
			}
		});

		
		//-------------fim do codigo de vinculo dos componentes com o objeto imcMasc
		
//-----------------fim tabela imc masculino
		
		
//--------------------tabela imc feminina inicio
		VerticalLayout geralFem = new VerticalLayout();
		geralFem.setSpacing(true);
		HorizontalLayout tituloFem = new HorizontalLayout();
		HorizontalLayout apresTabelaFem= new HorizontalLayout();
		apresTabelaFem.setSpacing(true);	
		
		Imc imcFem = new Imc();
		
				
		Label nomeTabelaFem = new Label("IMC Feminino");
		TextField idadeFem = new TextField("idade");
		TextField baixoPesoFem = new TextField("baixo Peso");
		TextField normalFem = new TextField("Normal");
		TextField excessoPesoFem= new TextField("Excesso de Peso");
		TextField obesidadeFem = new TextField("Obesidade");
	
		tituloFem.addComponent(nomeTabelaFem);
		apresTabelaFem.addComponent(idadeFem);
		apresTabelaFem.addComponent(baixoPesoFem);
		apresTabelaFem.addComponent(normalFem);
		apresTabelaFem.addComponent(excessoPesoFem);
		apresTabelaFem.addComponent(obesidadeFem);
		
		
		geralFem.addComponent(tituloFem);
		geralFem.setComponentAlignment(tituloFem, Alignment.MIDDLE_CENTER);
		geralFem.addComponent(apresTabelaFem);		
		
		
//---------------------fim tabela imc feminina

		//------------vinculo dos componentes com o objeto imcFem
		
		idadeFem.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				imcFem.setIdade(Integer.parseInt(idadeFem.getValue().toString()));
				
			}
		});
		
		baixoPesoFem.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				imcFem.setBaixoPeso(Double.parseDouble(baixoPesoFem.getValue().toString()));
				
			}
		});
		
		normalFem.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				imcFem.setNormal(Double.parseDouble(normalFem.getValue().toString()));
				
			}
		});

		excessoPesoFem.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				imcFem.setExcessoPeso(Double.parseDouble(excessoPesoFem.getValue().toString()));
				
			}
		});

		obesidadeFem.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				imcFem.setObesidade(Double.parseDouble(obesidadeFem.getValue().toString()));
				
			}
		});

		//-------------fim do codigo de vinculo dos componentes com o objeto imcFem

		
		Button salvar = new Button("Salvar", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				banco.salvarImcMasc(imcMasc);
				banco.salvarImcFem(imcFem);
				
			}
		});
		
//		ViewImc imcMasculino = new ViewImc("Imc Masculino");
//		ViewImc imcFeminino= new ViewImc("Imc Feminino");
//		
//		Imc objImcMasculino = new Imc();
		
		vlImc.addComponent(geralMasc);
		vlImc.addComponent(geralFem);
		vlImc.addComponent(salvar);
		vlImc.setComponentAlignment(salvar, Alignment.BOTTOM_RIGHT);
		vlImc.setSpacing(true);
		
		vlDireita.setSpacing(true);
		vlDireita.addComponent(vlImc);
		
		
	}
	
	public void viewBuscarImcMasc(){
		vlDireita.removeAllComponents();
		FormLayout form= new FormLayout();
		
		Table tabelaBusca= new Table("Tabela Imc Masculino");
		
		tabelaBusca.addContainerProperty("idade", int.class,null);
		tabelaBusca.addContainerProperty("Baixo peso", int.class,null);
		tabelaBusca.addContainerProperty("Normal", int.class,null);
		tabelaBusca.addContainerProperty("Excesso de Peso", int.class,null);
		tabelaBusca.addContainerProperty("Obesidade", int.class,null);
		
		List <Imc> listaImc = new ArrayList<Imc>();
		banco.buscarTodosImcMasc(listaImc);
		
		
		
		
		vlDireita.addComponent(form);
		
		
	}
	
	public void viewFlexibilidade(){
		tabelaFlexibilidadePreencher();
	}
	
	public void tabelaFlexibilidadePreencher(){
		vlDireita.removeAllComponents();	
		
		FormLayout tabMasc = new FormLayout();
		Label masc = new Label("Flexibilidade Masculino");
		TextField idadeMasc = new TextField("Idade");
		TextField muitoFracoMasc = new TextField("Muito Fraco");
		TextField fracoMasc = new TextField("Fraco");
		TextField razoavelMasc= new TextField("Razoavel");
		TextField bomMasc = new TextField("Bom");
		TextField muitoBomMasc= new TextField("Muito Bom");
		TextField excelenteMasc = new TextField("Excelente");
		
		
		FormLayout tabFem= new FormLayout();
		Label fem = new Label("Flexibilidade Feminina");
		TextField idadeFem = new TextField("Idade");
		TextField muitoFracoFem = new TextField("Muito Fraco");
		TextField fracoFem = new TextField("Fraco");
		TextField razoavelFem = new TextField("Razoavel");
		TextField bomFem = new TextField("Bom");
		TextField muitoBomFem = new TextField("Muito Bom");
		TextField excelenteFem = new TextField("Excelente");
		
		
		Button salvar = new Button("Salvar",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		vlDireita.setSpacing(true);
		tabMasc.addComponents(masc,idadeMasc,muitoFracoMasc,fracoMasc,razoavelMasc,bomMasc,muitoBomMasc,excelenteMasc);
		tabFem.addComponents(fem,idadeFem,muitoFracoFem,fracoFem,razoavelFem,bomFem,muitoBomFem,excelenteFem,salvar);
		
		vlDireita.addComponents(tabMasc);
		vlDireita.addComponents(tabFem);
		
	}
	
	public void tabelaAbdominalPreencher(){
		vlDireita.removeAllComponents();	
		
		FormLayout tabMasc = new FormLayout();
		Label masc = new Label("Abdominal Masculino");
		TextField idadeMasc = new TextField("Idade");
		TextField muitoFracoMasc = new TextField("Muito Fraco");
		TextField fracoMasc = new TextField("Fraco");
		TextField razoavelMasc= new TextField("Razoavel");
		TextField bomMasc = new TextField("Bom");
		TextField muitoBomMasc= new TextField("Muito Bom");
		TextField excelenteMasc = new TextField("Excelente");
		
		
		FormLayout tabFem= new FormLayout();
		Label fem = new Label("Abdominal Feminina");
		TextField idadeFem = new TextField("Idade");
		TextField muitoFracoFem = new TextField("Muito Fraco");
		TextField fracoFem = new TextField("Fraco");
		TextField razoavelFem = new TextField("Razoavel");
		TextField bomFem = new TextField("Bom");
		TextField muitoBomFem = new TextField("Muito Bom");
		TextField excelenteFem = new TextField("Excelente");
		
		
		Button salvar = new Button("Salvar",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		vlDireita.setSpacing(true);
		tabMasc.addComponents(masc,idadeMasc,muitoFracoMasc,fracoMasc,razoavelMasc,bomMasc,muitoBomMasc,excelenteMasc);
		tabFem.addComponents(fem,idadeFem,muitoFracoFem,fracoFem,razoavelFem,bomFem,muitoBomFem,excelenteFem,salvar);
		
		vlDireita.addComponents(tabMasc);
		vlDireita.addComponents(tabFem);
	}

	public void tabela6minutosPreencher(){
		vlDireita.removeAllComponents();	
		
		FormLayout tabMasc = new FormLayout();
		Label masc = new Label("6 minutos Masculino");
		TextField idadeMasc = new TextField("Idade");
		TextField fracoMasc = new TextField("Fraco");
		TextField razoavelMasc= new TextField("Razoavel");
		TextField bomMasc = new TextField("Bom");
		TextField muitoBomMasc= new TextField("Muito Bom");
		TextField excelenteMasc = new TextField("Excelente");
		
		
		FormLayout tabFem= new FormLayout();
		Label fem = new Label("6 minutos Feminina");
		TextField idadeFem = new TextField("Idade");
		TextField fracoFem = new TextField("Fraco");
		TextField razoavelFem = new TextField("Razoavel");
		TextField bomFem = new TextField("Bom");
		TextField muitoBomFem = new TextField("Muito Bom");
		TextField excelenteFem = new TextField("Excelente");
		
		
		Button salvar = new Button("Salvar",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		vlDireita.setSpacing(true);
		tabMasc.addComponents(masc,idadeMasc,fracoMasc,razoavelMasc,bomMasc,muitoBomMasc,excelenteMasc);
		tabFem.addComponents(fem,idadeFem,fracoFem,razoavelFem,bomFem,muitoBomFem,excelenteFem,salvar);
		
		vlDireita.addComponents(tabMasc);
		vlDireita.addComponents(tabFem);
	}

	public void tabelaSaltoEmDistanciaPreencher(){
		vlDireita.removeAllComponents();	
		
		FormLayout tabMasc = new FormLayout();
		Label masc = new Label("Salto em distância Masculino");
		TextField idadeMasc = new TextField("Idade");
		TextField fracoMasc = new TextField("Fraco");
		TextField razoavelMasc= new TextField("Razoavel");
		TextField bomMasc = new TextField("Bom");
		TextField muitoBomMasc= new TextField("Muito Bom");
		TextField excelenteMasc = new TextField("Excelente");
		
		
		FormLayout tabFem= new FormLayout();
		Label fem = new Label("Salto em distância Feminina");
		TextField idadeFem = new TextField("Idade");
		TextField fracoFem = new TextField("Fraco");
		TextField razoavelFem = new TextField("Razoavel");
		TextField bomFem = new TextField("Bom");
		TextField muitoBomFem = new TextField("Muito Bom");
		TextField excelenteFem = new TextField("Excelente");
		
		
		Button salvar = new Button("Salvar",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		vlDireita.setSpacing(true);
		tabMasc.addComponents(masc,idadeMasc,fracoMasc,razoavelMasc,bomMasc,muitoBomMasc,excelenteMasc);
		tabFem.addComponents(fem,idadeFem,fracoFem,razoavelFem,bomFem,muitoBomFem,excelenteFem,salvar);
		
		vlDireita.addComponents(tabMasc);
		vlDireita.addComponents(tabFem);
	}

	public void tabelaArremessoDeMedicineballPreencher(){
		vlDireita.removeAllComponents();	
		
		FormLayout tabMasc = new FormLayout();
		Label masc = new Label("Arremesso de Medicineball Masculino");
		TextField idadeMasc = new TextField("Idade");
		TextField muitoFracoMasc = new TextField("Muito Fraco");
		TextField fracoMasc = new TextField("Fraco");
		TextField razoavelMasc= new TextField("Razoavel");
		TextField bomMasc = new TextField("Bom");
		TextField muitoBomMasc= new TextField("Muito Bom");
		TextField excelenteMasc = new TextField("Excelente");
		
		
		FormLayout tabFem= new FormLayout();
		Label fem = new Label("Arremesso de Medicineball Feminina");
		TextField idadeFem = new TextField("Idade");
		TextField muitoFracoFem = new TextField("Muito Fraco");
		TextField fracoFem = new TextField("Fraco");
		TextField razoavelFem = new TextField("Razoavel");
		TextField bomFem = new TextField("Bom");
		TextField muitoBomFem = new TextField("Muito Bom");
		TextField excelenteFem = new TextField("Excelente");
		
		
		Button salvar = new Button("Salvar",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		vlDireita.setSpacing(true);
		tabMasc.addComponents(masc,idadeMasc,muitoFracoMasc,fracoMasc,razoavelMasc,bomMasc,muitoBomMasc,excelenteMasc);
		tabFem.addComponents(fem,idadeFem,muitoFracoFem,fracoFem,razoavelFem,bomFem,muitoBomFem,excelenteFem,salvar);
		
		vlDireita.addComponents(tabMasc);
		vlDireita.addComponents(tabFem);
	}
	
	public void tabelaQuadradopreencher(){
		vlDireita.removeAllComponents();	
		
		FormLayout tabMasc = new FormLayout();
		Label masc = new Label("Quadrado Masculino");
		TextField idadeMasc = new TextField("Idade");
		TextField excelenteMasc = new TextField("Excelente");
		TextField muitoBomMasc= new TextField("Muito Bom");
		TextField bomMasc = new TextField("Bom");
		TextField razoavelMasc= new TextField("Razoavel");
		TextField fracoMasc = new TextField("Fraco");
		
		
		FormLayout tabFem= new FormLayout();
		Label fem = new Label("Quadrado Feminina");
		TextField idadeFem = new TextField("Idade");
		TextField excelenteFem = new TextField("Excelente");
		TextField muitoBomFem = new TextField("Muito Bom");
		TextField bomFem = new TextField("Bom");
		TextField razoavelFem = new TextField("Razoavel");
		TextField fracoFem = new TextField("Fraco");
		
		
		Button salvar = new Button("Salvar",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		vlDireita.setSpacing(true);
		tabMasc.addComponents(masc,idadeMasc,excelenteMasc,muitoBomMasc,bomMasc,razoavelMasc,fracoMasc);
		tabFem.addComponents(fem,idadeFem,excelenteFem,muitoBomFem,bomFem,razoavelFem,fracoFem,salvar);
		
		vlDireita.addComponents(tabMasc);
		vlDireita.addComponents(tabFem);		
	}

	public void tabelaCorrida20metrosPreencher(){
		vlDireita.removeAllComponents();	
		
		FormLayout tabMasc = new FormLayout();
		Label masc = new Label("Corrida 20 metros Masculino");
		TextField idadeMasc = new TextField("Idade");
		TextField muitoFracoMasc = new TextField("Muito Fraco");
		TextField fracoMasc = new TextField("Fraco");
		TextField razoavelMasc= new TextField("Razoavel");
		TextField bomMasc = new TextField("Bom");
		TextField muitoBomMasc= new TextField("Muito Bom");
		TextField excelenteMasc = new TextField("Excelente");
		
		
		FormLayout tabFem= new FormLayout();
		Label fem = new Label("Corrida 20 metros Feminina");
		TextField idadeFem = new TextField("Idade");
		TextField muitoFracoFem = new TextField("Muito Fraco");
		TextField fracoFem = new TextField("Fraco");
		TextField razoavelFem = new TextField("Razoavel");
		TextField bomFem = new TextField("Bom");
		TextField muitoBomFem = new TextField("Muito Bom");
		TextField excelenteFem = new TextField("Excelente");
		
		
		Button salvar = new Button("Salvar",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		vlDireita.setSpacing(true);
		tabMasc.addComponents(masc,idadeMasc,muitoFracoMasc,fracoMasc,razoavelMasc,bomMasc,muitoBomMasc,excelenteMasc);
		tabFem.addComponents(fem,idadeFem,muitoFracoFem,fracoFem,razoavelFem,bomFem,muitoBomFem,excelenteFem,salvar);
		vlDireita.addComponent(tabMasc);
		vlDireita.addComponent(tabFem);
	}

	public void viewMenuCrudSerie(){
		vlMeio.removeAllComponents();
		vlDireita.removeAllComponents();
		
		FormLayout formMeio = new FormLayout();
		
		Button cadastrar = new Button("Cadastrar",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				viewCadastrarSerie();
				
			}
		});
		
		
		Button editar = new Button("Editar",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				viewEditarSerie();
				
			}
		});
		
		
		Button excluir = new Button("Excluir",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {

				viewExcluirSerie();
				
			}
		});
		
		
		Button buscar = new Button("Buscar",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		formMeio.addComponents(cadastrar,editar,excluir,buscar);
		
		vlMeio.addComponent(formMeio);
		
	}

	public void viewCadastrarSerie(){
		vlDireita.removeAllComponents();
		FormLayout form = new FormLayout();
		
		Serie serie = new Serie();
		
		final TextField tfserie = new TextField("Série");
		final ComboBox ensino = new ComboBox("Ensino");
		ensino.addItem("Fundamental");
		ensino.addItem("Medio");
		
		
		tfserie.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				serie.setSerie(Integer.parseInt(tfserie.getValue().toString()));
				
			}
		});
		
		ensino.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				serie.setEnsino(ensino.getValue().toString());
				
			}
		});
		
		
		Button salvar = new Button("Salvar",new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				banco.salvarSerie(serie);
				Notification.show("Cadastrado com Sucesso!");
				viewCadastrarSerie();
			}
		});
		
		
		form.addComponents(tfserie,ensino, salvar);
		
		vlDireita.addComponent(form);
	}

	public void viewEditarSerie(){
		vlDireita.removeAllComponents();
		FormLayout form = new FormLayout();
		form.setStyleName("formserie");
		form.setMargin(true);
		
		Serie serie = new Serie();
		
		final Label lbbuscar = new Label("Buscar Dados");
		final TextField tfserie = new TextField("Série");
		final ComboBox ensino = new ComboBox("Ensino");
		ensino.addItem("Fundamental");
		ensino.addItem("Medio");
		
		Button buscar = new Button("Buscar");;
		Button editar = new Button("Editar");;
		
		tfserie.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				serie.setSerie(Integer.parseInt(tfserie.getValue().toString()));
				
			}
		});
		
		ensino.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				serie.setEnsino(ensino.getValue().toString());
				
			}
		});
		
		
		buscar.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				int idserie = banco.buscarIdSerie(serie); 
				
				if(idserie!=0){
					
					serie.setId(idserie);
										
					FormLayout fldados = new FormLayout();
					fldados.setStyleName("formseriedados");
					Label dadosatuais = new Label("Dados Atuais");
					Label lbserie = new Label("Serie : "+String.valueOf(serie.getSerie()));
					Label lbEnsino = new Label("Ensino : "+String.valueOf(serie.getEnsino()));
					
					lbbuscar.setValue("Editar Serie");
					vlDireita.removeComponent(form);
					form.removeComponent(buscar);
					fldados.addComponents(dadosatuais,lbserie,lbEnsino);
					form.addComponent(editar);
					
					vlDireita.setSpacing(true);
					vlDireita.addComponents(fldados,form);
					
				}
				
				else
					Notification.show("Série nao existente!");
				
				
			}
		});
		
		editar.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				banco.editarSerie(serie);
				Notification.show("Cadastrado com Sucesso!");
				viewCadastrarSerie();
				
			}
		});
		
			
		
		form.addComponents(lbbuscar,tfserie,ensino, buscar);
		vlDireita.setSpacing(true);
		vlDireita.addComponent(form);
	}

	public void viewExcluirSerie(){
		vlDireita.removeAllComponents();
		FormLayout form = new FormLayout();
		form.setStyleName("formserie");
		form.setMargin(true);
		
		Serie serie = new Serie();
		
		final Label lbbuscar = new Label("Buscar Dados");
		final ComboBox ensino = new ComboBox("Ensino");
		ensino.addItem("Fundamental");
		ensino.addItem("Medio");
		final ComboBox cbserie = new ComboBox("Série");
		cbserie.setEnabled(false);
		
		
		Button excluir = new Button("Excluir");
		
		cbserie.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(cbserie.getValue()!=null){
					serie.setSerie(Integer.parseInt(cbserie.getValue().toString()));
					
				}
				else 
					Notification.show("Escolha uma Série");
				
			}
		});
		
		ensino.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(ensino.getValue()!=null){
					serie.setEnsino(ensino.getValue().toString());
				}
				else 
					Notification.show("Escolha um Ensino");
			}
		});
		
		
		excluir.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				if(cbserie.getValue()!=null && ensino.getValue()!=null){
					
//					UI minhajanela = new UI(SubWindowSerie.subWindowSerie(banco, serie));
//					SubWindowSerie.subWindowSerie(banco, serie);
				}
				
				else
					Notification.show("Preencha os dados corretamente!");
				
			}
		});
		
		
		
		ensino.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				cbserie.setEnabled(true);
				banco.buscarSeries(serie,cbserie);
				
			}
		});
		

		
		
		
		form.addComponents(lbbuscar,ensino,cbserie, excluir);
		vlDireita.setSpacing(true);
		vlDireita.addComponent(form);

	}
}
