package com.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;

import com.Service.AlunoService;
import com.Service.CidadeService;
import com.Service.EscolaService;
import com.Service.SerieService;
import com.Service.TurmaService;
import com.Service.TurnoService;
import com.model.Aluno;
import com.model.Imc;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class AlunoCadastro extends VerticalLayout{

	CidadeService cidadeService;
	TurmaService turmaService;
	AlunoService alunoService;
	EscolaService escolaService;
	SerieService serieService;
	TurnoService turnoService;
	
	ComboBox escola;
	ComboBox cidade;
	ComboBox serie;
	ComboBox turma;
	ComboBox ensino;
	ComboBox turno;
	
	int idCidade;
	int idEscola;
	int idSerie;
	double imc;
	
	Table tabelaAluno;
	Aluno aluno;
	
	boolean tabelaSelecionada = false;
	
	public AlunoCadastro() {
		
		
		cidadeService = new CidadeService();
		turmaService = new TurmaService();
		alunoService = new AlunoService();
		escolaService= new EscolaService();
		serieService = new SerieService();
		turnoService =new TurnoService();
		aluno = new Aluno();
		
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
		
		cidade = new ComboBox("Cidade");
//		cidade = carregarCidades();
		List<String> listaCidades = cidadeService.buscarCidade();
		 for(String nomecidade : listaCidades){
			 cidade.addItem(nomecidade);
		 }
		
			
		escola = new ComboBox("Escola");
		escola.setWidth("400px");
		escola.setCaption("Escola");
		
		turno = new ComboBox("Turno");
		
		ensino = new ComboBox("Ensino");
		
		serie = new ComboBox("Série");
		serie.setWidth("100px");
		
		turma = new ComboBox("Turma");
		
		
		turma.setWidth("50px");

		TextField  endereco = new TextField("Endereço");
		
		
		TextField  bairro = new TextField("Bairro");
		TextField  cep = new TextField("CEP");
		TextField  telefone = new TextField("Telefone");
		TextField  email = new TextField("Email");
		
		TextField  nomeAluno = new TextField("Nome completo do Aluno");
		nomeAluno.setWidth("250px");
		
		ComboBox sexo = new ComboBox("Sexo");
		sexo.addItem("M");
		sexo.addItem("F");

		TextField  idade = new TextField("Idade");
		
		DateField dataNascimento = new DateField("Data de Nascimento");
		dataNascimento.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				idade.setValue(	alunoService.calcularIdade(dataNascimento.getValue().toString()));
				
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
		
		
		TextField  massaCorporal= new TextField("Massa Corporal(kg)");
		TextField  estatura = new TextField("Estatura(metros)");
		TextField  envergadura = new TextField("Envergadura(cm)");
		TextField  sentarAlcancar = new TextField("Sentar e Alcançar(cm)");
		TextField  abdominal = new TextField("Abdominal(qtd)");
		TextField  minuto6 = new TextField("6 minutos(metros)");
		TextField  saltoEmDistancia = new TextField("Salto em distância(cm)");
		TextField  arremesso = new TextField("Arremesso de Medicineball(cm)");
		TextField  quadrado = new TextField("Quadrado(segundos)");
		TextField  corrida= new TextField("Corrida de 20 metros(segundos)");
		
		
		
		Button salvar = new Button("Salvar", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				aluno.setNome(nomeAluno.getValue().toString());
				aluno.setSexo(sexo.getValue().toString().charAt(0));
				aluno.setMassa_corporal(Double.parseDouble(massaCorporal.getValue().toString()));
				aluno.setEstatura(Double.parseDouble(estatura.getValue().toString()));
//				System.out.println("envergadura = "+Integer.parseInt(envergadura.getValue().toString()));
				aluno.setEnvergadura(Integer.parseInt(envergadura.getValue().toString()));
				aluno.setImc(imc);
				aluno.setFlexibilidade(Integer.parseInt(sentarAlcancar.getValue().toString()));
				aluno.setAbdominal(Integer.parseInt(abdominal.getValue().toString()));
				aluno.setSeis_minutos(Integer.parseInt(minuto6.getValue().toString()));
				aluno.setSalto_em_distancia(Integer.parseInt(saltoEmDistancia.getValue().toString()));
				aluno.setArremesso_medicineball(Integer.parseInt(arremesso.getValue().toString()));
				aluno.setQuadrado(Double.parseDouble(quadrado.getValue().toString()));
				aluno.setCorrida_20metros(Double.parseDouble(corrida.getValue().toString()));
				alunoService.salvarAluno(aluno);
				
				limparCampos();
				
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
						//carregar o nome de todas as escolas da cidade selecionada no comboBoc cidade
				idCidade = cidadeService.buscarIDCidade(str);
				aluno.setId_cidade(idCidade);
				List<String> listaEscolas= escolaService.buscarNomesEscolas(idCidade);
				 for(String nomeescola : listaEscolas){
					 escola.addItem(nomeescola);
				 }		
//				escola = carregarEscolas(str);
					}
					else{
						escola.removeAllItems();
					}
					
					
			}
		});
		
		escola.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				
				
				String strEscola = escola.getValue().toString();
				idEscola = escolaService.buscarIDEScola(strEscola);
				aluno.setId_escola(idEscola);
				List<String> listaEnsino= escolaService.buscarEnsino(idEscola);
				 for(String ensin: listaEnsino){
					 ensino.addItem(ensin);
				 }
				 
					List<String> listaTurnos= turnoService.buscarTurnos(idEscola);
					 for(String turn: listaTurnos){
						 turno.addItem(turn);
					 }
				
//				carregar séries  que estão nas escolas selecionadas no comboBox escola
				List<Integer> listaSeries= serieService.buscarSeries(idEscola);
				 for(Integer seri : listaSeries){
					 serie.addItem(seri);
				
				 }
			}
		});
		
		serie.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
//				carregar turmas que estão nas séries da escola selecionadas no comboBox 
				idSerie = Integer.parseInt(serie.getValue().toString());
				String strEnsino = ensino.getValue().toString();
				aluno.setSerie(idSerie);
				List<Character> listaTurmas= turmaService.buscarTurma(idEscola, idSerie,strEnsino);
				 for(Character turm : listaTurmas){
					 turma.addItem(turm);
				
				 }
				
			}
		});
		
		turma.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				aluno.setTurma(turma.getValue().toString().charAt(0));
				
			}
		});
		
		ensino.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				aluno.setEnsino(ensino.getValue().toString());
				
			}
		});
		
		turno.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				aluno.setTurno(turno.getValue().toString());
				
			}
		});
		
		
		dataNascimento.addValueChangeListener(new  ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(dataNascimento.getValue().toString()!=null){
					idade.setValue(alunoService.calcularIdade(dataNascimento.getValue().toString()));
					aluno.setIdade(Integer.parseInt(idade.getValue().toString()));
				}
				
				else{
					idade.setValue("");
				}
				
			}
		});

		estatura.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				imc =Double.parseDouble(massaCorporal.getValue().toString())/(( Double.parseDouble(estatura.getValue().toString()))*( Double.parseDouble(estatura.getValue().toString())));
				
			}
		});
		
		salvar.setClickShortcut(KeyCode.ENTER);
		a.addComponent(cidade);
		a.addComponent(escola);
		a.setSpacing(true);
		
		
		b.addComponent(ensino);
		b.addComponent(turno);
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
		
		n.addComponents(arremesso,envergadura);
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
		
		
		
		this.addComponent(vl);
		this.setSizeFull();
		setSizeFull();

	}

	protected ComboBox carregarSeries(String nomeEscola) {
		ComboBox serie = new ComboBox("Série");
		 idEscola = escolaService.buscarIDEScola(nomeEscola);
		 List<Integer> listaSeries= serieService.buscarSeries(idEscola);
		 for(Integer numSerie : listaSeries){
			 serie.addItem(numSerie);
		 }

		return serie;
	}

	protected ComboBox carregarEscolas(String nomeCidade) {
		ComboBox escolas = new ComboBox("Escola");
		 idCidade = cidadeService.buscarIDCidade(nomeCidade);
		 List<String> listaEscolas= escolaService.buscarNomesEscolas(idCidade);
		 for(String nomeescola : listaEscolas){
			 escolas.addItem(nomeescola);
		 }
		 
		return escolas;
	}

	private ComboBox carregarTurmas() {
		
		ComboBox turmas = new ComboBox("Turma");
		 
//		 List<Character> listaTurmas= turmaService.buscarTurma(idEscola,idSerie, ensino);
//		 for(Character nometurma : listaTurmas){
//			 turmas.addItem(nometurma);
//		 }

		return turmas;
	}

	private ComboBox carregarCidades() {
		 ComboBox cidades = new ComboBox("Cidade");
		 
		 List<String> listaCidades = cidadeService.buscarCidade();
		 for(String nomecidade : listaCidades){
			 cidades.addItem(nomecidade);
		 }
		 
		return cidades;
	}
	
	
public Table carregarTabela(char tabSexo,int idEscola){
		
		
		tabelaAluno= new Table("Tabela de Alunos");
			
		tabelaAluno.addContainerProperty("Nome", String.class, null);
		
		List<String> listaAlunos=  new ArrayList<String>();
		if(idEscola==0){
			listaAlunos= alunoService.buscarTodos();
		}
		else if(idEscola>0){
			
			listaAlunos= alunoService.buscarTodosNaEscola(idEscola);
		}
		
		int linha = 1;
		for(String alun:  listaAlunos){
			tabelaAluno.addItem(new Object[]{alun},linha);
			linha++;
		}
		
		tabelaAluno.setPageLength(tabelaAluno.size());
		tabelaAluno.setSelectable(true);
		tabelaAluno.setImmediate(true);
		
		tabelaAluno.addListener(new ItemClickListener() {
			
			@Override
			public void itemClick(ItemClickEvent event) {
					
						Property itemProperty = event.getItem().getItemProperty("Nome");
						aluno.setNome(itemProperty.getValue().toString());
							
						tabelaSelecionada= true;
					
				
			}
		});
		return tabelaAluno;
	}

	public void limparCampos(){
		cidadeService = new CidadeService();
		turmaService = new TurmaService();
		alunoService = new AlunoService();
		escolaService= new EscolaService();
		serieService = new SerieService();
		turnoService =new TurnoService();
		aluno = new Aluno();
		
		cidade = new ComboBox("Cidade");
//		cidade = carregarCidades();
		List<String> listaCidades = cidadeService.buscarCidade();
		 for(String nomecidade : listaCidades){
			 cidade.addItem(nomecidade);
		 }
		
			
		escola.setValue("Campo Grande");
		
		turno = new ComboBox("Turno");
		
		ensino = new ComboBox("Ensino");
		
		serie = new ComboBox("Série");
		serie.setWidth("100px");
		
		turma = new ComboBox("Turma");
		
		
		turma.setWidth("50px");

		TextField  endereco = new TextField("Endereço");
		
		
		TextField  bairro = new TextField("Bairro");
		TextField  cep = new TextField("CEP");
		TextField  telefone = new TextField("Telefone");
		TextField  email = new TextField("Email");
		
		TextField  nomeAluno = new TextField("Nome completo do Aluno");
		nomeAluno.setWidth("250px");
		
		ComboBox sexo = new ComboBox("Sexo");
		sexo.addItem("M");
		sexo.addItem("F");

		TextField  idade = new TextField("Idade");
		
		DateField dataNascimento = new DateField("Data de Nascimento");

		
		dataNascimento.setDateFormat("yyyy-MM-dd");
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
		
		
		TextField  massaCorporal= new TextField("Massa Corporal(kg)");
		TextField  estatura = new TextField("Estatura(metros)");
		TextField  envergadura = new TextField("Envergadura(cm)");
		TextField  sentarAlcancar = new TextField("Sentar e Alcançar(cm)");
		TextField  abdominal = new TextField("Abdominal(qtd)");
		TextField  minuto6 = new TextField("6 minutos(metros)");
		TextField  saltoEmDistancia = new TextField("Salto em distância(cm)");
		TextField  arremesso = new TextField("Arremesso de Medicineball(cm)");
		TextField  quadrado = new TextField("Quadrado(segundos)");
		TextField  corrida= new TextField("Corrida de 20 metros(segundos)");
	}

	
	
	
}
