package com.prodama.model;

public enum StatusConexao {

	CONECTADO("Conectado"),
	DESCONECTADO("Desconectado");
	
	private String descricao;
	
	StatusConexao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
