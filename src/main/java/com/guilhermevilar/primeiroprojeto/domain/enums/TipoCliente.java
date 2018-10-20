package com.guilhermevilar.primeiroprojeto.domain.enums;

public enum TipoCliente {
	
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");

	private int cod;
	private String descricao;
	//construtor de tipo enumerado é private
	
	private TipoCliente(int cod, String descricao) {
		
		this.cod = cod;
		this.descricao = descricao;
	}
	
	//para tipos enumerados só fazer o get, uma vez criados estes tipos não se muda mais
	
	public int getCod() {
		return cod;
	}
	
	
	public String getDescricao() {
		return descricao;
	}
	
	
	
	public static TipoCliente toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(TipoCliente x: TipoCliente.values()) { //esse for percorre todos os valores possíveis do TipoCliente
			
			if(cod.equals(x.getCod())) {
				return x; //retorna o objeto aqui
			}
			
		}
		
		//se não for nulo e não for nenhum código que existe, lança exception
		
		throw new IllegalArgumentException("Id inválido:" + cod);
		
	}
}
