package br.com.contmatic.endereco;

import static br.com.contmatic.util.Constantes.BAIRRO_INCORRETO;
import static br.com.contmatic.util.Constantes.BAIRRO_MAX_SIZE;
import static br.com.contmatic.util.Constantes.BAIRRO_MIN_SIZE;
import static br.com.contmatic.util.Constantes.BAIRRO_VAZIO;
import static br.com.contmatic.util.Constantes.CEP_INCORRETO;
import static br.com.contmatic.util.Constantes.CEP_INVALIDO;
import static br.com.contmatic.util.Constantes.CEP_SIZE;
import static br.com.contmatic.util.Constantes.CEP_VAZIO;
import static br.com.contmatic.util.Constantes.CIDADE_INCORRETO;
import static br.com.contmatic.util.Constantes.CIDADE_MAX_SIZE;
import static br.com.contmatic.util.Constantes.CIDADE_MIN_SIZE;
import static br.com.contmatic.util.Constantes.CIDADE_VAZIO;
import static br.com.contmatic.util.Constantes.COMPLEMENTO_INCORRETO;
import static br.com.contmatic.util.Constantes.COMPLEMENTO_INVALIDO;
import static br.com.contmatic.util.Constantes.COMPLEMENTO_MAX_SIZE;
import static br.com.contmatic.util.Constantes.COMPLEMENTO_MIN_SIZE;
import static br.com.contmatic.util.Constantes.COMPLEMENTO_VAZIO;
import static br.com.contmatic.util.Constantes.ESTADO_VAZIO;
import static br.com.contmatic.util.Constantes.NUMERO_INCORRETO;
import static br.com.contmatic.util.Constantes.NUMERO_VAZIO;
import static br.com.contmatic.util.Constantes.RUA_INCORRETO;
import static br.com.contmatic.util.Constantes.RUA_INVALIDA;
import static br.com.contmatic.util.Constantes.RUA_MAX_SIZE;
import static br.com.contmatic.util.Constantes.RUA_MIN_SIZE;
import static br.com.contmatic.util.Constantes.RUA_VAZIA;
import static br.com.contmatic.util.RegexType.CEP;
import static br.com.contmatic.util.RegexType.LETRAS_NUMEROS;
import static br.com.contmatic.util.RegexType.validaSeNaoTemEspacosIncorretosECaracteresEspeciaos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import br.com.contmatic.groups.Post;
import br.com.contmatic.groups.Put;

/**
 * The Class Endereco.
 */
public class Endereco {

    /** The cep. */
    @NotBlank(message = CEP_VAZIO, groups = { Put.class, Post.class })
    @Pattern(regexp = CEP, message = CEP_INCORRETO, groups = { Put.class, Post.class })
    private String cep;

    /** The rua. */
    @Size(min = 2, max = 80)
    @NotBlank(message = RUA_VAZIA, groups = { Put.class, Post.class })
    @Pattern(regexp = LETRAS_NUMEROS, message = RUA_INCORRETO, groups = { Put.class, Post.class })
    private String rua;

    /** The numero. */
    @NotBlank(message = NUMERO_VAZIO)
    @Size(min = 1, message = NUMERO_INCORRETO)
    private Integer numero;

    /** The complemento. */
    @Size(min = 2, max = 80)
    @NotBlank(message = COMPLEMENTO_VAZIO, groups = { Put.class, Post.class })
    @Pattern(regexp = LETRAS_NUMEROS, message = COMPLEMENTO_INCORRETO, groups = { Put.class, Post.class })
    private String complemento;

    /** The bairro. */
    @Size(min = 2, max = 40)
    @NotBlank(message = BAIRRO_VAZIO, groups = { Put.class, Post.class })
    @Pattern(regexp = LETRAS_NUMEROS, message = BAIRRO_INCORRETO, groups = { Put.class, Post.class })
    private String bairro;

    /** The cidade. */
    @Size(min = 2, max = 40)
    @NotBlank(message = CIDADE_VAZIO, groups = { Put.class, Post.class })
    @Pattern(regexp = LETRAS_NUMEROS, message = CIDADE_INCORRETO, groups = { Put.class, Post.class })
    private String cidade;

    /** The estado. */
    @NotBlank(message = ESTADO_VAZIO, groups = { Put.class, Post.class })
    private EstadoType estado;

    /**
     * Instantiates a new endereco.
     *
     * @param cep the cep
     * @param numero the numero
     */
    public Endereco(String cep, Integer numero) {
        this.setCep(cep);
        this.setNumero(numero);
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
    public Endereco(String cep, String rua, Integer numero, String complemento, String bairro, String cidade, EstadoType estado) {
        this.setCep(cep);
        this.setRua(rua);
        this.setNumero(numero);
        this.setComplemento(complemento);
        this.setBairro(bairro);
        this.setCidade(cidade);
        this.setEstado(estado);
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.validaCepIncorreto(cep);
        this.validaEspacosIncorretosECaracteresEspeciaisNoCep(cep);
        this.cep = cep;
    }

    private void validaEspacosIncorretosECaracteresEspeciaisNoCep(String cep) {
        if (validaSeNaoTemEspacosIncorretosECaracteresEspeciaos(cep)) {
            throw new IllegalArgumentException(CEP_INVALIDO);
        }
    }

    private void validaCepIncorreto(String cep) {
        if (cep == null || cep.trim().isEmpty() || cep.length() < CEP_SIZE || cep.length() > CEP_SIZE) {
            throw new IllegalArgumentException(CEP_INCORRETO);
        }
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.validaRuaIncorreto(rua);
        this.validaEspacosIncorretosECaracteresEspeciaisNaRua(rua);
        this.rua = rua;
    }

    private void validaEspacosIncorretosECaracteresEspeciaisNaRua(String rua) {
        if (validaSeNaoTemEspacosIncorretosECaracteresEspeciaos(rua)) {
            throw new IllegalArgumentException(RUA_INVALIDA);
        }
    }

    private void validaRuaIncorreto(String rua) {
        if (rua == null || rua.trim().isEmpty() || rua.length() < RUA_MIN_SIZE || rua.length() > RUA_MAX_SIZE) {
            throw new IllegalArgumentException(RUA_INCORRETO);
        }
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
        this.validaComplementoIncorreto(complemento);
        this.validaEspacosIncorretosECaracteresEspeciaisNoComplemento(complemento);
        this.complemento = complemento;
    }

    private void validaEspacosIncorretosECaracteresEspeciaisNoComplemento(String complemento) {
        if (validaSeNaoTemEspacosIncorretosECaracteresEspeciaos(complemento)) {
            throw new IllegalArgumentException(COMPLEMENTO_INVALIDO);
        }
    }

    private void validaComplementoIncorreto(String complemento) {
        if (complemento == null || complemento.trim().isEmpty() || complemento.length() < COMPLEMENTO_MIN_SIZE || complemento.length() > COMPLEMENTO_MAX_SIZE) {
            throw new IllegalArgumentException(COMPLEMENTO_INCORRETO);
        }
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.validaBairroIncorreto(bairro);
        this.validaEspacosIncorretosECaracteresEspeciaisNoBairro(bairro);
        this.bairro = bairro;
    }

    private void validaEspacosIncorretosECaracteresEspeciaisNoBairro(String bairro) {
        if (validaSeNaoTemEspacosIncorretosECaracteresEspeciaos(bairro)) {
            throw new IllegalArgumentException(BAIRRO_INCORRETO);
        }
    }

    private void validaBairroIncorreto(String bairro) {
        if (bairro == null || bairro.trim().isEmpty() || bairro.length() < BAIRRO_MIN_SIZE || bairro.length() > BAIRRO_MAX_SIZE) {
            throw new IllegalArgumentException(BAIRRO_INCORRETO);
        }
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.validaCidadeIncorreto(cidade);
        this.validaEspacosIncorretosECaracteresEspeciaisNaCidade(cidade);
        this.cidade = cidade;
    }

    private void validaEspacosIncorretosECaracteresEspeciaisNaCidade(String cidade) {
        if (validaSeNaoTemEspacosIncorretosECaracteresEspeciaos(cidade)) {
            throw new IllegalArgumentException(CIDADE_INCORRETO);
        }
    }

    private void validaCidadeIncorreto(String cidade) {
        if (cidade == null || cidade.trim().isEmpty() || cidade.length() < CIDADE_MIN_SIZE || cidade.length() > CIDADE_MAX_SIZE) {
            throw new IllegalArgumentException(CIDADE_INCORRETO);
        }
    }

    public EstadoType getEstado() {
        return estado;
    }

    public void setEstado(EstadoType estado) {
        this.estadoVazio(estado);
        this.estado = estado;
    }

    public void estadoVazio(EstadoType estado) {
        if (estado == null) {
            throw new IllegalArgumentException(ESTADO_VAZIO);
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
