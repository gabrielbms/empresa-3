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
import org.hibernate.validator.constraints.br.CNPJ;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.groups.Post;
import br.com.contmatic.groups.Put;
import br.com.contmatic.regex.RegexType;
import br.com.contmatic.telefone.Telefone;

/**
 * The Class Empresa.
 * 
 * @author gabriel.santos
 */
public class Empresa {

    /** The cnpj. */
    @CNPJ(message = "O CNPJ do funcionario está inválido", groups = { Put.class, Post.class })
    @NotBlank(message = "O campo CPF não pode estar nulo", groups = { Put.class, Post.class })
    private String cnpj;

    /** The nome. */
    @NotBlank(message = "O campo nome não pode estar vazio", groups = { Put.class, Post.class })
    @Pattern(regexp = RegexType.NOME, message = "O nome da empresa está incorreto", groups = { Put.class, Post.class })
    @Size(min = 2, max = 100, message = "O nome mínimo é de {min} caracteres e no máximo de {max} caracteres", groups = { Put.class, Post.class })
    private String nome;

    /** The site. */
    @Length(min = 5, max = 70)
    @NotBlank(message = "O campo site não pode estar nulo", groups = { Put.class, Post.class })
    @Pattern(regexp = RegexType.SITE, message = "O site da empresa está inválido", groups = { Put.class, Post.class })
    private String site;

    /** The telefones. */
    @Valid
    @NotNull(message = "O telefone da empresa não pode ser nulo", groups = { Put.class, Post.class })
    @Size.List({@Size(min = 1, message = "A lista de telefone da empresa não deve ser vazio.", groups = { Put.class,Post.class }),
		@Size(max = 500, message = "A lista de telefone da empresa máxima é de {max}.", groups = { Put.class,Post.class }) })
    private Set<Telefone> telefones;

    /** The enderecos. */
    @Valid
    @NotNull(message = "O endereço da empresa está vazio", groups = { Put.class, Post.class })
    @Size.List({@Size(min = 1, message = "A lista de telefone da empresa mínima é de {min}.", groups = { Put.class,Post.class }),
		@Size(max = 500, message = "A lista de telefone da empresa máxima é de {max}.", groups = { Put.class,Post.class }) })
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
