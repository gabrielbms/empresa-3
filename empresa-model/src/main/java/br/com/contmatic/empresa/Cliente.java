package br.com.contmatic.empresa;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
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
import br.com.contmatic.regex.RegexType;
import br.com.contmatic.telefone.Telefone;

/**
 * The Class Cliente.
 * 
 * @author gabriel.santos
 */
public class Cliente {

    /** The cpf. */
    @CPF(message = "O CPF do cliente está inválido", groups = { Put.class, Post.class })
    @NotNull(message = "O campo CPF não pode estar nulo", groups = { Put.class, Post.class })
    private String cpf;

    /** The nome. */
    @NotBlank(message = "O campo nome não pode estar vazio", groups = { Put.class, Post.class })
    @Pattern(regexp = RegexType.NOME, message = "O nome do cliente está incorreto", groups = { Put.class, Post.class })
    @Size(min = 2, max = 80, message = "O nome mínimo é de {min} caracteres e no máximo de {max} caracteres", groups = { Put.class, Post.class })
    private String nome;

    /** The email. */
    @Email(message = "O email do cliente está invalido", groups = { Put.class, Post.class })
    @NotBlank(message = "O campo e-mail não pode estar vazio", groups = { Put.class, Post.class })
    @Pattern(regexp = RegexType.EMAIL, message = "O email do cliente está invalido", groups = { Put.class, Post.class })
    @Size(min = 5, max = 100, message = "O e-mail do funcionario pode ter no máximo {max} caracteres", groups = { Put.class, Post.class })
    private String email;

    /** The boleto. */
    @Min(value = 1, message = "O valor do boleto não pode ser negativo")
    @NotEmpty(message = "O campo boleto não pode estar vazio")
    @Digits(integer = 13, fraction = 2)
    private BigDecimal boleto;
    
    /** The telefones. */
    @Valid
    @NotNull(message = "O telefone do cliente não pode ser vazio", groups = { Put.class, Post.class })
    @Size.List({@Size(min = 1, message = "A lista de telefone da empresa não deve ser vazio.", groups = { Put.class,Post.class }),
		@Size(max = 500, message = "A lista de telefone da empresa máxima é de {max}.", groups = { Put.class,Post.class }) })
    private Set<Telefone> telefones;

    /**
     * Instantiates a new cliente.
     *
     * @param cpf the cpf
     * @param nome the nome
     * @param telefone the telefone
     * @param boleto the boleto
     */
    public Cliente(String cpf, String nome, @Valid Set<Telefone> telefone, BigDecimal boleto) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefones = telefone;
        this.boleto = boleto;
    }

    /**
     * Instantiates a new cliente.
     */
    public Cliente() {

    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String setCpf) {
        this.cpf = setCpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public @Valid Set<Telefone> getTelefone() {
        return telefones;
    }

    public void setTelefones(Set<Telefone> telefone) {
        this.telefones = telefone;
    }

    public BigDecimal getBoleto() {
        return boleto;
    }

    public void setBoleto(BigDecimal boleto) {
        if (boleto.doubleValue() >= 0) {
        	this.boleto = boleto;
        } else {
        	throw new IllegalArgumentException("boleto não pode ser negativo");
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
