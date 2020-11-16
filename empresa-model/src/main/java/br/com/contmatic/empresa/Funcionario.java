package br.com.contmatic.empresa;

import static br.com.contmatic.util.Constantes.CPF_INCORRETO;
import static br.com.contmatic.util.Constantes.CPF_INVALIDO;
import static br.com.contmatic.util.Constantes.CPF_SIZE;
import static br.com.contmatic.util.Constantes.CPF_VAZIO;
import static br.com.contmatic.util.Constantes.DATA_CONTRATACAO_FUTURA;
import static br.com.contmatic.util.Constantes.DATA_CONTRATACAO_VAZIA;
import static br.com.contmatic.util.Constantes.DATA_SALARIO_FUTURA;
import static br.com.contmatic.util.Constantes.DATA_SALARIO_NULA;
import static br.com.contmatic.util.Constantes.ENDERECO_QTDE_MAX;
import static br.com.contmatic.util.Constantes.ENDERECO_QTDE_MINIMA;
import static br.com.contmatic.util.Constantes.ENDERECO_VAZIO;
import static br.com.contmatic.util.Constantes.IDADE_MINIMA;
import static br.com.contmatic.util.Constantes.IDADE_MINIMA_MENSAGEM;
import static br.com.contmatic.util.Constantes.NOME_INCORRETO;
import static br.com.contmatic.util.Constantes.NOME_INVALIDO;
import static br.com.contmatic.util.Constantes.NOME_MAX_SIZE;
import static br.com.contmatic.util.Constantes.NOME_MIN_SIZE;
import static br.com.contmatic.util.Constantes.NOME_TAMANHO;
import static br.com.contmatic.util.Constantes.NOME_VAZIO;
import static br.com.contmatic.util.Constantes.SALARIO_MINIMO;
import static br.com.contmatic.util.Constantes.SALARIO_MINIMO_MENSAGEM;
import static br.com.contmatic.util.Constantes.SALARIO_NEGATIVO;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_CPF_GRANDE_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_CPF_PEQUENO_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_NOME_GRANDE_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_NOME_PEQUENO_DEMAIS;
import static br.com.contmatic.util.Constantes.TELEFONE_QTDE_MAX;
import static br.com.contmatic.util.Constantes.TELEFONE_QTDE_MINIMA;
import static br.com.contmatic.util.Constantes.TELEFONE_VAZIO;
import static br.com.contmatic.util.RegexType.LETRAS;
import static br.com.contmatic.util.RegexType.NUMEROS;
import static br.com.contmatic.util.RegexType.validaSeNaoTemEspacosIncorretosECaracteresEspeciaos;
import static br.com.contmatic.util.Validate.isNotCPF;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;
import org.joda.time.LocalDate;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.groups.Post;
import br.com.contmatic.groups.Put;
import br.com.contmatic.telefone.Telefone;

/**
 * The Class Funcionario.
 * 
 * @author gabriel.santos
 */
public class Funcionario {

    /** The cpf. */
	@CPF(message = CPF_INVALIDO, groups = { Put.class, Post.class })
	@NotNull(message = CPF_VAZIO, groups = { Put.class, Post.class })
	@Pattern(regexp = NUMEROS, message = CPF_INCORRETO, groups = { Put.class, Post.class })
	private String cpf;


    /** The nome. */
	@NotBlank(message = NOME_VAZIO, groups = { Put.class, Post.class })
	@Pattern(regexp = LETRAS, message = NOME_INCORRETO, groups = { Put.class, Post.class })
	@Size(min = 2, max = 80, message = NOME_TAMANHO, groups = { Put.class, Post.class })
    private String nome;

    /** The idade. */
	@NotEmpty
	@Min(value = 1, message = IDADE_MINIMA_MENSAGEM, groups = { Put.class, Post.class })
    private Integer idade;
    
    /** The salario. */
	@Min(value = 1, message = SALARIO_NEGATIVO, groups = { Put.class, Post.class })
    private BigDecimal salario;

    /** The data contratacao. */
	@NotNull(message = DATA_CONTRATACAO_VAZIA, groups = { Put.class, Post.class })
	@Past(message = DATA_CONTRATACAO_FUTURA, groups = { Put.class, Post.class })
    private LocalDate dataContratacao;

    /** The data salario. */
	@Future(message = DATA_SALARIO_FUTURA, groups = { Put.class, Post.class })
	@NotNull(message = DATA_SALARIO_NULA, groups = { Put.class, Post.class })
    private LocalDate dataSalario;

    /** The telefones. */
	@Valid
	@NotNull(message = TELEFONE_VAZIO, groups = { Put.class, Post.class })
	@Size.List({ @Size(min = 1, message = TELEFONE_QTDE_MINIMA, groups = { Put.class, Post.class }),
			@Size(max = 3, message = TELEFONE_QTDE_MAX, groups = { Put.class, Post.class }) })
    private Set<Telefone> telefones;

    /** The enderecos. */
	@Valid
	@NotNull(message = ENDERECO_VAZIO)
	@Size.List({ @Size(min = 1, message = ENDERECO_QTDE_MINIMA),
			@Size(max = 3, message = ENDERECO_QTDE_MAX) })
    private Set<Endereco> enderecos;

	public Funcionario(String cpf, String nome, BigDecimal salario) {
		this.setCpf(cpf);
		this.setNome(nome);
		this.setSalario(salario);
	}

	public Funcionario(String cpf, String nome, Integer idade, Set<Telefone> telefone, Set<Endereco> endereco,
			BigDecimal salario, LocalDate dataContratacao, LocalDate dataSalario) {
		this.setCpf(cpf);
		this.setNome(nome);
		this.setIdade(idade);
		this.setTelefones(telefone);
		this.setEnderecos(endereco);
		this.setSalario(salario);
		this.setDataContratacao(dataContratacao);
		this.setDataSalario(dataSalario);
	}

    /**
     * Instantiates a new funcionario.
     */
    public Funcionario() {

    }

    public String getCpf() {
        return cpf;
    }

	public void setCpf(String cpf) {
		this.validaCpfIncorreto(cpf);
		this.validaCalculoCpf(cpf);
		this.validaEspacosIncorretosECaracteresEspeciais(cpf);
		this.cpf = cpf;
	}
	
	private void validaEspacosIncorretosECaracteresEspeciais(String cpf) {
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

	private void validaCpfNulloOuVazio(String cpf) {
		if (cpf == null || cpf.trim().isEmpty()) {
			throw new IllegalArgumentException(CPF_VAZIO);
		}
	}

	private void validaCpfComTamanhoMenor(String cpf) {
		if (cpf.length() < CPF_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_CPF_PEQUENO_DEMAIS);
		}
	}

	private void validaCpfComTamanhoMaior(String cpf) {
		if (cpf.length() > CPF_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_CPF_GRANDE_DEMAIS);
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

    public int getIdade() {
        return idade;
    }

	public void setIdade(int idade) {
		this.validaIdade(idade);
		this.idade = idade;
	}
	
	private void validaIdade(int idade) {
		if (idade < IDADE_MINIMA) {
			throw new IllegalArgumentException(IDADE_MINIMA_MENSAGEM);
		}
	}
    public @Valid Set<Telefone> getTelefone() {
        return telefones;
    }

    public @Valid Set<Endereco> getEndereco() {
        return enderecos;
    }

    public BigDecimal getSalario() {
        return salario;
    }

	public void setSalario(BigDecimal salario) {
		this.validaSalario(salario);
		this.salario = salario;
	}
	
	private void validaSalario(BigDecimal salario) {
		if (salario.doubleValue() < SALARIO_MINIMO) {
			throw new IllegalArgumentException(SALARIO_MINIMO_MENSAGEM);
		}
	}

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(LocalDate dataPagamento) {
        this.dataContratacao = dataPagamento;
    }

    public LocalDate getDataSalario() {
        return dataSalario;
    }

    public void setDataSalario(LocalDate dataSalario) {
        this.dataSalario = dataSalario;
    }

	public void setTelefones(Set<Telefone> telefone) {
		this.validaTelefoneNullo(telefone);
		this.telefones = telefone;
	}
	
	private void validaTelefoneNullo(Set<Telefone> telefone) {
		if (telefone == null) {
			throw new IllegalArgumentException(TELEFONE_VAZIO);
		} 	
	}

	public void setEnderecos(Set<Endereco> endereco) {
		this.validaEnderecoNullo(endereco);
		this.enderecos = endereco;
	}
	
	private void validaEnderecoNullo(Set<Endereco> endereco) {
		if (endereco == null) {
			throw new IllegalArgumentException(ENDERECO_VAZIO);
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
