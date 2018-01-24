package com.prodama.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;


@Entity
public class ConexaoCliente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="gen_conexaoCliente") 
	@SequenceGenerator(sequenceName = "seq_conexaoCliente", allocationSize = 1, name = "gen_conexaoCliente")
	private Long codigo;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="codigo_conexao")
	private Conexao conexao ;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="codigo_sistema")
	private Sistema sistema;
	
	@NotBlank
	private String versao;

    
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public Conexao getConexao() {
		return conexao;
	}

	public void setConexao(Conexao conexao) {
		this.conexao = conexao;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConexaoCliente other = (ConexaoCliente) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
