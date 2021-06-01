package bueno.ezandro.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_convenio")
public class Convenio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@NotNull(message = "{numCarteirinhaConvenio.not.null}")
	@Column(name = "numero_convenio", nullable = false)
	private Integer numCarteirinhaConvenio;

	@NotBlank(message = "{validadeCarteirinha.not.blank}")
	@Column(name = "validade_carteirinha", length = 7, nullable = false)
	private String validadeCarteirinha;

	public Convenio() {
	}

	public Convenio(Integer id, Cliente cliente, Integer numCarteirinhaConvenio, String validadeCarteirinha) {
		this.id = id;
		this.cliente = cliente;
		this.numCarteirinhaConvenio = numCarteirinhaConvenio;
		this.validadeCarteirinha = validadeCarteirinha;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getNumCarteirinhaConvenio() {
		return numCarteirinhaConvenio;
	}

	public void setNumCarteirinhaConvenio(Integer numCarteirinhaConvenio) {
		this.numCarteirinhaConvenio = numCarteirinhaConvenio;
	}

	public String getValidadeCarteirinha() {
		return validadeCarteirinha;
	}

	public void setValidadeCarteirinha(String validadeCarteirinha) {
		this.validadeCarteirinha = validadeCarteirinha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Convenio other = (Convenio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}