package com.prodama.repository.filter;



import com.prodama.model.Cliente;

public class ConexaoFilter {

	private Long codigoSenior;

	private Cliente cliente;

	public Long getCodigoSenior() {
		return codigoSenior;
	}

	public void setCodigoSenior(Long codigoSenior) {
		this.codigoSenior = codigoSenior;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
}
