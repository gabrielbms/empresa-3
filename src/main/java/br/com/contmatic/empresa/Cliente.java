package br.com.contmatic.empresa;

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

import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.util.RegexType;

/**
 * The Class Cliente.
 * 
 * @author gabriel.santos
 */
public class Cliente {

    /** The cpf. */
    @CPF(message = "O CPF do cliente está inválido")
    @NotNull(message = "O campo CPF não pode estar nulo")
    private String cpf;

    /** The nome. */
    @NotBlank(message = "O campo nome não pode estar vazio")
    @Pattern(regexp = RegexType.NOME, message = "O nome do cliente está incorreto")
    @Size(min = 2, max = 80, message = "O nome mínimo é de {min} caracteres e no máximo de {max} caracteres")
    private String nome;

    /** The email. */
    @Email(message = "O email do cliente está invalido")
    @NotBlank(message = "O campo e-mail não pode estar vazio")
    @Pattern(regexp = RegexType.EMAIL, message = "O email do cliente está invalido")
    @Size(min = 5, max = 100, message = "O e-mail do funcionario pode ter no máximo {max} caracteres")
    private String email;

    /** The telefones. */
    @Valid
    @NotNull(message = "O telefone do cliente não pode ser vazio")
    @Size.List({ @Size(min = 1, message = "os telefones do cliente não devem ser menor que um"),
		@Size(max = 3, message = "O máximo de telefones que podem ser salvo totaliza {max} telefones") })
    private Set<Telefone> telefones;

    /** The boleto. */
    @Min(value = 1, message = "O valor do boleto não pode ser negativo")
    @NotEmpty(message = "O campo boleto não pode estar vazio")    
    private BigDecimal boleto;

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
        if (boleto.doubleValue() >= 1) {
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
