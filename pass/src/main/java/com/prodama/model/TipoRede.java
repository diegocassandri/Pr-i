package com.prodama.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.Id;
import java.util.List;
import javax.persistence.FetchType;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="tipoRede")
public class TipoRede {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="gen_tipoRede") 
	@SequenceGenerator(sequenceName = "seq_tipoRede", allocationSize = 1, name = "gen_tipoRede")
	private Long codigo;
	
	@NotBlank
	private String nome;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoConexao")
	private List<Conexao> listaConexao;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
		TipoRede other = (TipoRede) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
}
