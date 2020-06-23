package br.com.contmatic.empresa;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

import com.google.common.base.Preconditions;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.util.RegexType;

/**
 * The Class Fornecedor.
 * 
 * @author gabriel.santos
 */
public class Fornecedor {

    /** The cnpj. */
    @CNPJ(message = "O CNPJ do funcionario está inválido")
    @NotBlank(message = "O campo CNPJ não pode estar nulo")
    private String cnpj;

    /** The nome. */
    @NotBlank(message = "O campo nome não pode estar vazio")
    @Pattern(regexp = RegexType.NOME, message = "O nome do fornecedor está incorreto")
    @Size(min = 2, max = 100, message = "O nome mínimo é de {min} caracteres e no máximo de {max} caracteres")
    private String nome;

    /** The produto. */
    @NotBlank(message = "O campo produto não pode estar nulo")
    @Length(min = 2, max = 80, message = "Tamanho do produto invalido")
    @Pattern(regexp = RegexType.NOME, message = "O nome do produto está incorreto")
    private String produto;
    
    /** The telefones. */
    @Valid
    @NotEmpty(message = "O telefone do fornecedor não pode ser vazio")
    @Size.List({ @Size(min = 1, message = "os telefones do fornecedor não devem ser menor que um"),
		@Size(max = 3, message = "O máximo de telefones que podem ser salvo totaliza {max} telefones") })
    private Set<Telefone> telefones;

    /** The enderecos. */
    @Valid
    @NotEmpty(message = "O endereço da empresa está vazio")
	@Size.List({ @Size(min = 1, message = "Tem que cadastrar pelo menos um endereço"),
			@Size(max = 3, message = "A quantidade máxima de endeços é {max} endereços") })
    private Set<Endereco> enderecos;

    /**
     * Instantiates a new fornecedor.
     *
     * @param cnpj the cnpj
     * @param nome the nome
     */
    public Fornecedor(String cnpj, String nome) {
        this.cnpj = cnpj;
        this.nome = nome;
    }

    /**
     * Instantiates a new fornecedor.
     */
    public Fornecedor() {

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
    
    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public @Valid Set<Telefone> getTelefone() {
        return telefones;
    }

    public @Valid Set<Endereco> getEndereco() {
        return enderecos;
    }

    public void setTelefones(Set<Telefone> telefone) {
        Preconditions.checkArgument(telefone.size() < 2, "Somente pode possuir um telefone");
        this.telefones = telefone;
    }

    public void setEnderecos(Set<Endereco> endereco) {
        Preconditions.checkArgument(endereco.size() < 2, "Somente pode possuir um endereco");
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
