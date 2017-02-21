package com.prodama.model;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.persistence.FetchType;
import java.util.List;

import com.prodama.model.Cliente;

@Entity
public class Conexao  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull
	private Long codigoSenior;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "CodigoCliente")
	private Cliente cliente;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "CodigoTipoConexao")
	private TipoConexao tipoConexao;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "CodigoTipoRede")
	private TipoRede tipoRede;
	
	private String versao;
	
	private String ip;
	private String usuarioRede;
	private String senhaRede;
	
	private String usuarioSistema;
	private String senhaSistema;
	
	private String usuarioBd;
	private String senhaBd;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private SimNao permissao;
	
	
	private String observacao;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "conexao")
	private List<ConexaoCliente> conexaoCliente;
	

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

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

	public TipoConexao getTipoConexao() {
		return tipoConexao;
	}

	public void setTipoConexao(TipoConexao tipoConexao) {
		this.tipoConexao = tipoConexao;
	}

	public TipoRede getTipoRede() {
		return tipoRede;
	}

	public void setTipoRede(TipoRede tipoRede) {
		this.tipoRede = tipoRede;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUsuarioRede() {
		return usuarioRede;
	}

	public void setUsuarioRede(String usuarioRede) {
		this.usuarioRede = usuarioRede;
	}

	public String getSenhaRede() {
		return senhaRede;
	}

	public void setSenhaRede(String senhaRede) {
		this.senhaRede = senhaRede;
	}

	public String getUsuarioSistema() {
		return usuarioSistema;
	}

	public void setUsuarioSistema(String usuarioSistema) {
		this.usuarioSistema = usuarioSistema;
	}

	public String getSenhaSistema() {
		return senhaSistema;
	}

	public void setSenhaSistema(String senhaSistema) {
		this.senhaSistema = senhaSistema;
	}

	public String getUsuarioBd() {
		return usuarioBd;
	}

	public void setUsuarioBd(String usuarioBd) {
		this.usuarioBd = usuarioBd;
	}

	public String getSenhaBd() {
		return senhaBd;
	}

	public void setSenhaBd(String senhaBd) {
		this.senhaBd = senhaBd;
	}

	public SimNao getPermissao() {
		return permissao;
	}

	public void setPermissao(SimNao permissao) {
		this.permissao = permissao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	/**
	 * @return the conexaoCliente
	 */
	public List<ConexaoCliente> getConexaoCliente() {
		return conexaoCliente;
	}

	/**
	 * @param conexaoCliente the conexaoCliente to set
	 */
	public void setConexaoCliente(List<ConexaoCliente> conexaoCliente) {
		this.conexaoCliente = conexaoCliente;
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
		Conexao other = (Conexao) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	

	

}
