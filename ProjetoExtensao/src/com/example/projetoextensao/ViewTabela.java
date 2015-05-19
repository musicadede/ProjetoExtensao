package com.example.projetoextensao;

import com.vaadin.ui.Table;

public class ViewTabela {

	private Table tabela;
	
	
	public Table imcTable(int linha){
		tabela =  new Table("Tabela IMC");
		
		
		
		tabela.addContainerProperty("Idade", int.class ,null);
		tabela.addContainerProperty("Baixo Peso", double.class, null);
		tabela.addContainerProperty("Normal", double.class, null);
		tabela.addContainerProperty("Excesso de Peso", double.class, null);
		tabela.addContainerProperty("Obesidade", double.class, null);
		
		for(int i =1;i<=linha;i++){
			tabela.addItem(new Object[]{},i);
			
		}
		
		
		return tabela;
	}
	
}
