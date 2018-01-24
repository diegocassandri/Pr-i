package com.prodama.model;

public class ConexaoWs {

	private Long codigoSenior;
	
	private String versao;
	
	private String siglaSistema;

	public Long getCodigoSenior() {
		return codigoSenior;
	}

	public void setCodigoSenior(Long codigoSenior) {
		this.codigoSenior = codigoSenior;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public String getSiglaSistema() {
		return siglaSistema;
	}

	public void setSiglaSistema(String siglaSistema) {
		this.siglaSistema = siglaSistema;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoSenior == null) ? 0 : codigoSenior.hashCode());
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
		ConexaoWs other = (ConexaoWs) obj;
		if (codigoSenior == null) {
			if (other.codigoSenior != null)
				return false;
		} else if (!codigoSenior.equals(other.codigoSenior))
			return false;
		return true;
	}
}
