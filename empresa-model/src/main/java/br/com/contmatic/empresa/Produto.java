package br.com.contmatic.empresa;

import static br.com.contmatic.util.Constantes.ID_MINIMO;
import static br.com.contmatic.util.Constantes.ID_VAZIO;
import static br.com.contmatic.util.Constantes.NOME_INVALIDO;
import static br.com.contmatic.util.Constantes.NOME_MAX_SIZE;
import static br.com.contmatic.util.Constantes.NOME_MIN_SIZE;
import static br.com.contmatic.util.Constantes.NOME_VAZIO;
import static br.com.contmatic.util.Constantes.PRECO_MINIMO;
import static br.com.contmatic.util.Constantes.PRECO_MINIMO_MENSAGEM;
import static br.com.contmatic.util.Constantes.QUANTIDADE_MINIMA;
import static br.com.contmatic.util.Constantes.QUANTIDADE_MINIMA_MENSAGEM;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_NOME_GRANDE_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_NOME_PEQUENO_DEMAIS;
import static br.com.contmatic.util.RegexType.validaSeNaoTemEspacosIncorretosECaracteresEspeciaos;

import java.math.BigDecimal;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import br.com.contmatic.util.Constantes;
import br.com.contmatic.util.RegexType;

public class Produto {

	private Integer id;

	@Pattern(regexp = RegexType.LETRAS, message = Constantes.NOME_INVALIDO)
	private String nome;

	private Integer quantidade;

	private BigDecimal preco;

	public Produto(Integer id, String nome) {
		super();
		this.setId(id);		
		this.setNome(nome);
	}

	public Produto(Integer id, String nome, Integer quantidade, BigDecimal preco) {
		this.setId(id);
		this.setNome(nome);
		this.setQuantidade(quantidade);
		this.setPreco(preco);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.validaIdIncorreto(id);
		this.id = id;
	}

	private void validaIdIncorreto(Integer id) {
		if (id == null || id.doubleValue() < ID_MINIMO) {
			throw new IllegalArgumentException(ID_VAZIO);
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
		this.validaNomePequenoDemais(nome);
		this.validaNomeGrandeDemais(nome);
	}

	private void validaNomeGrandeDemais(String nome) {
		if (nome.length() > NOME_MAX_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_NOME_GRANDE_DEMAIS);
		}
	}

	private void validaNomePequenoDemais(String nome) {
		if (nome.length() < NOME_MIN_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_NOME_PEQUENO_DEMAIS);
		}
	}

	private void validaNomeNulloOuVazio(String nome) {
		if (nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException(NOME_VAZIO);
		}
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.validaQuantidadeIncorreta(quantidade);
		this.quantidade = quantidade;
	}

	private void validaQuantidadeIncorreta(Integer quantidade) {
		if (quantidade < QUANTIDADE_MINIMA) {
			throw new IllegalArgumentException(QUANTIDADE_MINIMA_MENSAGEM);
		}
	}


	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.validaPrecoIncorreto(preco);
		this.preco = preco;
	}

	private void validaPrecoIncorreto(BigDecimal preco) {
		if (preco.doubleValue() < PRECO_MINIMO) {
			throw new IllegalArgumentException(PRECO_MINIMO_MENSAGEM);
		}
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
