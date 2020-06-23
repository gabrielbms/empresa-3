package br.com.contmatic.endereco;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import br.com.contmatic.util.RegexType;

/**
 * The Class Endereco.
 */
public class Endereco {

    /** The cep. */
	@NotBlank(message = "O campo cep não pode estar nulo")
	@Pattern(regexp = RegexType.CEP, message = "O CEP do endereço está incorreto")
    private String cep;

    /** The rua. */
    @Size(min = 2, max = 80)
    @NotBlank(message = "O campo rua não pode estar nulo")
    @Pattern(regexp = RegexType.LETRAS_NUMEROS, message = "A rua do endereço está incorreto")
    private String rua;

    /** The numero. */
    @NotBlank(message = "O campo numero não pode estar nulo")
    private int numero;

    /** The complemento. */
    @Size(min = 2, max = 80)
    @NotBlank(message = "O campo complemento não pode estar nulo")
    @Pattern(regexp = RegexType.LETRAS_NUMEROS, message = "O complemento do endereço está incorreto")
    private String complemento;

    /** The bairro. */
    @Size(min = 2, max = 40)
    @NotBlank(message = "O campo bairro não pode estar nulo")
    @Pattern(regexp = RegexType.LETRAS_NUMEROS, message = "O bairro do endereço está incorreto")
    private String bairro;

    /** The cidade. */
    @Size(min = 2, max = 40)
    @NotBlank(message = "O campo cidade não pode estar nulo")
    @Pattern(regexp = RegexType.LETRAS_NUMEROS, message = "A cidade do endereço está incorreto")
    private String cidade;

    /** The estado. */
    @NotBlank(message = "O campo estado não pode estar nulo")
    private Estado estado;

    /**
     * Instantiates a new endereco.
     *
     * @param cep the cep
     * @param numero the numero
     */
    public Endereco(String cep, Integer numero) {
        this.cep = cep;
        this.numero = numero;
    }

    /**
     * Instantiates a new endereco.
     */
    public Endereco() {
    }

    /**
     * Instantiates a new endereco.
     *
     * @param cep the cep
     * @param rua the rua
     * @param numero the numero
     * @param complemento the complemento
     * @param bairro the bairro
     * @param cidade the cidade
     * @param estado the estado
     */
    public Endereco(String cep, String rua, Integer numero, String complemento, String bairro, String cidade, Estado estado) {
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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
