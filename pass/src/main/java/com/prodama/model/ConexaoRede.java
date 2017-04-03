package com.prodama.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
public class ConexaoRede implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="gen_conexaoRede") 
	@SequenceGenerator(sequenceName = "seq_conexaoRede", allocationSize = 1, name = "gen_conexaoRede")
	private Long codigo;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="codigo_conexao")
	private Conexao conexao ;
	
	@NotBlank
	private String usuario;
	
	@NotBlank
	private String senha;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoBase tipoBase;
	
	
	@Enumerated(EnumType.STRING)
	private StatusConexao status;
	
	private String usuarioLogado;
	
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
	
	
	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getUsuarioLogado() {
		return usuarioLogado;
	}


	public void setUsuarioLogado(String usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	
	public TipoBase getTipoBase() {
		return tipoBase;
	}

	public void setTipoBase(TipoBase tipoBase) {
		this.tipoBase = tipoBase;
	
	}
	
	public StatusConexao getStatus() {
		return status;
	}

	public void setStatus(StatusConexao status) {
		this.status = status;
	
	}
	
	public boolean isDesconectado() {
		return StatusConexao.DESCONECTADO.equals(this.status);
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
		ConexaoRede other = (ConexaoRede) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
