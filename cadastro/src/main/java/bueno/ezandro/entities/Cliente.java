package bueno.ezandro.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	public Cliente() {
	}

	public Cliente(Integer prontuario, String nome, String sobrenome, LocalDate dataNascimento, String genero,
			String cpf, String rg, String uf, String email, String celular, String telefone) {
		this.prontuario = prontuario;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		this.genero = genero;
		this.cpf = cpf;
		this.rg = rg;
		this.uf = uf;
		this.email = email;
		this.celular = celular;
		this.telefone = telefone;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer prontuario;

	@NotBlank(message = "{nome.not.blank}")
	@Column(name = "nome")
	private String nome;

	@NotNull(message = "{sobrenome.not.null}")
	@Column(name = "sobrenome")
	private String sobrenome;

	@NotNull(message = "{dataNascimento.not.null}")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;

	@NotBlank(message = "{genero.not.blank}")
	@Column(name = "sexo", length = 1)
	private String genero;

	@CPF(message = "O número de CPF informado não é valido")
	@Column(name = "cpf", unique = true, nullable = false)
	private String cpf;

	@NotBlank(message = "{rg.not.blank}")
	@Column(name = "rg")
	private String rg;

	@NotBlank(message = "{uf.not.blank}")
	@Column(name = "uf", length = 2)
	private String uf;

	@NotBlank(message = "{email.not.blank}")
	@Email(message = "{email.not.valid}")
	@Column(name = "email")
	private String email;

	@NotBlank(message = "{celular.not.blank}")
	@Pattern(regexp = "[0-9]{10,11}", message = "{celular.not.valid}")
	@Column(name = "celular", length = 11)
	private String celular;

	@Pattern(regexp = "[0-9]{10,11}", message = "O número de telefone possui formato inválido!")
	@Column(length = 11, nullable = true)
	private String telefone;

	@OneToMany(mappedBy = "cliente")
	private List<Convenio> convenios = new ArrayList<>();

	public Integer getProntuario() {
		return prontuario;
	}

	public void setProntuario(Integer prontuario) {
		this.prontuario = prontuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Convenio> getConvenios() {
		return convenios;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prontuario == null) ? 0 : prontuario.hashCode());
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
		Cliente other = (Cliente) obj;
		if (prontuario == null) {
			if (other.prontuario != null)
				return false;
		} else if (!prontuario.equals(other.prontuario))
			return false;
		return true;
	}

}