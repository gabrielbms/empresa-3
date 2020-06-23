package br.com.contmatic.empresa;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.br.CNPJ;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.util.RegexType;

/**
 * The Class Empresa.
 * 
 * @author gabriel.santos
 */
public class Empresa {

    /** The cnpj. */
    @CNPJ(message = "O CNPJ do funcionario está inválido")
    @NotBlank(message = "O campo CPF não pode estar nulo")
    private String cnpj;

    /** The nome. */
    @NotBlank(message = "O campo nome não pode estar vazio")
    @Pattern(regexp = RegexType.NOME, message = "O nome da empresa está incorreto")
    @Size(min = 2, max = 100, message = "O nome mínimo é de {min} caracteres e no máximo de {max} caracteres")
    private String nome;

    /** The site. */
    @URL
    @Length(min = 5, max = 60)
    @NotBlank(message = "O campo site não pode estar nulo")
    @Pattern(regexp = RegexType.URL, message = "O site da empresa está inválido")
    private String site;

    /** The telefones. */
    @Valid
    @NotNull(message = "O telefone da empresa não pode ser nulo")
    @Size.List({ @Size(min = 1, message = "os telefones da empresa não devem ser menor que um"),
		@Size(max = 3, message = "O máximo de telefones que podem ser salvo totaliza {max} telefones") })
    private Set<Telefone> telefones;

    /** The enderecos. */
    @Valid
    @NotNull(message = "O endereço da empresa está vazio")
	@Size.List({ @Size(min = 1, message = "Tem que cadastrar pelo menos um endereço"),
			@Size(max = 3, message = "A quantidade máxima de endeços é {max} endereços") })
    private Set<Endereco> enderecos;

    /**
     * Instantiates a new empresa.
     *
     * @param cnpj the cnpj
     * @param nome the nome
     * @param telefone the telefone
     * @param endereco the endereco
     */
    public Empresa(String cnpj, String nome, @Valid Set<Telefone> telefone, @Valid Set<Endereco> endereco) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefones = telefone;
        this.enderecos = endereco;
    }

    /**
     * Instantiates a new empresa.
     */
    public Empresa() {

    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public @Valid Set<Telefone> getTelefone() {
        return telefones;
    }

    public @Valid Set<Endereco> getEndereco() {
        return enderecos;
    }

    public void setTelefones(Set<Telefone> telefone) {
       this.telefones = telefone;
    }

    public void setEnderecos(Set<Endereco> endereco) {
        this.enderecos = endereco;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
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
