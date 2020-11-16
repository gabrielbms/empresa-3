package br.com.contmatic.empresa;

import static br.com.contmatic.util.Constantes.BOLETO_NEGATIVO;
import static br.com.contmatic.util.Constantes.BOLETO_VAZIO;
import static br.com.contmatic.util.Constantes.CPF_INCORRETO;
import static br.com.contmatic.util.Constantes.CPF_INVALIDO;
import static br.com.contmatic.util.Constantes.CPF_SIZE;
import static br.com.contmatic.util.Constantes.CPF_VAZIO;
import static br.com.contmatic.util.Constantes.EMAIL_INVALIDO;
import static br.com.contmatic.util.Constantes.EMAIL_MAX_SIZE;
import static br.com.contmatic.util.Constantes.EMAIL_MIN_SIZE;
import static br.com.contmatic.util.Constantes.EMAIL_TAMANHO;
import static br.com.contmatic.util.Constantes.EMAIL_VAZIO;
import static br.com.contmatic.util.Constantes.NOME_INVALIDO;
import static br.com.contmatic.util.Constantes.NOME_MAX_SIZE;
import static br.com.contmatic.util.Constantes.NOME_MIN_SIZE;
import static br.com.contmatic.util.Constantes.NOME_TAMANHO;
import static br.com.contmatic.util.Constantes.NOME_VAZIO;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_CPF_GRANDE_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_CPF_PEQUENO_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_EMAIL_GRANDE_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_EMAIL_PEQUENO_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_NOME_GRANDE_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_NOME_PEQUENO_DEMAIS;
import static br.com.contmatic.util.Constantes.TELEFONE_QTDE_MAX;
import static br.com.contmatic.util.Constantes.TELEFONE_QTDE_MINIMA;
import static br.com.contmatic.util.Constantes.TELEFONE_VAZIO;
import static br.com.contmatic.util.RegexType.EMAIL;
import static br.com.contmatic.util.RegexType.LETRAS;
import static br.com.contmatic.util.RegexType.NUMEROS;
import static br.com.contmatic.util.RegexType.validaSeNaoTemEspacosIncorretosECaracteresEspeciaos;
import static br.com.contmatic.util.Validate.isNotCPF;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import br.com.contmatic.groups.Post;
import br.com.contmatic.groups.Put;
import br.com.contmatic.telefone.Telefone;

/**
 * The Class Cliente.
 * 
 * @author gabriel.santos
 */
public class Cliente {

	/** The cpf. */
	@CPF(message = CPF_INVALIDO, groups = { Put.class, Post.class })
	@NotNull(message = CPF_VAZIO, groups = { Put.class, Post.class })
	@Pattern(regexp = NUMEROS, message = CPF_INCORRETO, groups = { Put.class, Post.class })
	private String cpf;

	/** The nome. */
	@NotBlank(message = NOME_VAZIO, groups = { Put.class, Post.class })
	@Pattern(regexp = LETRAS, message = NOME_INVALIDO, groups = { Put.class, Post.class })
	@Size(min = 2, max = 80, message = NOME_TAMANHO, groups = { Put.class, Post.class })
	private String nome;

	/** The email. */
	@Email(message = EMAIL_INVALIDO, groups = { Put.class, Post.class })
	@NotBlank(message = EMAIL_VAZIO, groups = { Put.class, Post.class })
	@Pattern(regexp = EMAIL, message = EMAIL_INVALIDO, groups = { Put.class, Post.class })
	@Size(min = 5, max = 100, message = EMAIL_TAMANHO, groups = { Put.class, Post.class })
	private String email;

	/** The boleto. */
	@Min(value = 1, message = BOLETO_NEGATIVO, groups = { Put.class, Post.class })
	@NotEmpty(message = BOLETO_VAZIO, groups = { Put.class, Post.class })
	private BigDecimal boleto;

	/** The telefones. */
	@Valid
	@NotNull(message = TELEFONE_VAZIO, groups = { Put.class, Post.class })
	@Size.List({ @Size(min = 1, message = TELEFONE_QTDE_MINIMA, groups = { Put.class, Post.class }),
		@Size(max = 3, message = TELEFONE_QTDE_MAX, groups = { Put.class, Post.class }) })
	private Set<Telefone> telefones;

	public Cliente(String cpf, String nome, @Valid Set<Telefone> telefone, BigDecimal boleto) {
		this.setCpf(cpf);
		this.setNome(nome);
		this.setTelefones(telefone);
		this.setBoleto(boleto);
	}

	public Cliente() {

	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.validaCpfIncorreto(cpf);
		this.validaCalculoCpf(cpf);
		this.validaEspacosIncorretosECaracteresEspeciaisNoCpf(cpf);
		this.cpf = cpf;
	}

	private void validaEspacosIncorretosECaracteresEspeciaisNoCpf(String cpf) {
		if (validaSeNaoTemEspacosIncorretosECaracteresEspeciaos(cpf)) {
			throw new IllegalArgumentException(CPF_INVALIDO);
		}
	}

	private void validaCalculoCpf(String cpf) {
		if (isNotCPF(cpf)) {
			throw new IllegalStateException(CPF_INVALIDO);
		}
	}

	private void validaCpfIncorreto(String cpf) {
		this.validaCpfNulloOuVazio(cpf);
		this.validaCpfComTamanhoMenor(cpf);
		this.validaCpfComTamanhoMaior(cpf);
	}

	private void validaCpfComTamanhoMaior(String cpf) {
		if (cpf.length() > CPF_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_CPF_GRANDE_DEMAIS);
		}
	}

	private void validaCpfComTamanhoMenor(String cpf) {
		if (cpf.length() < CPF_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_CPF_PEQUENO_DEMAIS);
		}
	}

	private void validaCpfNulloOuVazio(String cpf) {
		if (cpf == null || cpf.trim().isEmpty()) {
			throw new IllegalArgumentException(CPF_VAZIO);
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.validaNomeIncorreto(nome);
		this.validaEspacosIncorretosECaracteresEspeciaisNoNome(nome);
		this.nome = nome;
	}

	private void validaEspacosIncorretosECaracteresEspeciaisNoNome(String nome) {
		if (validaSeNaoTemEspacosIncorretosECaracteresEspeciaos(nome)) {
			throw new IllegalArgumentException(NOME_INVALIDO);
		}
	}

	private void validaNomeIncorreto(String nome) {
		this.validaNomeNulloOuVazio(nome);
		this.validaNomeMenorQueOTamanhoMinimo(nome);
		this.validaNomeMaiorQueOTamanhoMinimo(nome);
	}

	private void validaNomeMaiorQueOTamanhoMinimo(String nome) {
		if (nome.length() > NOME_MAX_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_NOME_GRANDE_DEMAIS);
		}
	}

	private void validaNomeMenorQueOTamanhoMinimo(String nome) {
		if (nome.length() < NOME_MIN_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_NOME_PEQUENO_DEMAIS);
		}
	}

	private void validaNomeNulloOuVazio(String nome) {
		if (nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException(NOME_VAZIO);
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.validaEmailIncorreto(email);
		this.email = email;
	}

	private void validaEmailIncorreto(String email) {
		this.validaEmailNulloOuVazio(email);
		this.validaEmailMenorQueOTamanhoMinimo(email);
		this.validaEmailMaiorQueOTamanhoMaximo(email);
	}

	private void validaEmailMaiorQueOTamanhoMaximo(String email) {
		if (email.length() > EMAIL_MAX_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_EMAIL_GRANDE_DEMAIS);
		}
	}

	private void validaEmailMenorQueOTamanhoMinimo(String email) {
		if (email.length() < EMAIL_MIN_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_EMAIL_PEQUENO_DEMAIS);
		}
	}

	private void validaEmailNulloOuVazio(String email) {
		if (email == null || email.trim().isEmpty()) {
			throw new IllegalArgumentException(EMAIL_VAZIO);
		}
	}

	public @Valid Set<Telefone> getTelefone() {
		return telefones;
	}

	public void setTelefones(Set<Telefone> telefone) {
		this.validaTelefoneNullo(telefone);
		this.telefones = telefone;
	}

	private void validaTelefoneNullo(Set<Telefone> telefones) {
		if (telefones == null) {
			throw new IllegalArgumentException(TELEFONE_VAZIO);
		}
	}

	public BigDecimal getBoleto() {
		return boleto;
	}

	public void setBoleto(BigDecimal boleto) {
		this.validaValorBoleto(boleto);
		this.boleto = boleto;
	}

	private void validaValorBoleto(BigDecimal boleto) {
		if (boleto.doubleValue() < 0) {
			throw new IllegalArgumentException(BOLETO_NEGATIVO);
		}
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

}
