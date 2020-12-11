package br.com.contmatic.telefone;

import static br.com.contmatic.regex.RegexType.NUMEROS;
import static br.com.contmatic.util.Constantes.DDD_VAZIO;
import static br.com.contmatic.util.Constantes.NUMERO_TELEFONE_INCORRETAMENTE;
import static br.com.contmatic.util.Constantes.TELEFONE_INVALIDO;
import static br.com.contmatic.util.Constantes.TELEFONE_PREENCHIDO_INCORRETAMENTE;
import static br.com.contmatic.util.Constantes.TELEFONE_VAZIO;
import static br.com.contmatic.util.Constantes.TEL_MAX_SIZE;
import static br.com.contmatic.util.Constantes.TEL_MIN_SIZE;
import static br.com.contmatic.util.Constantes.TIPO_TELEFONE_VAZIO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class Telefone.
 * 
 *  @author gabriel.santos
 */
public class Telefone {

    @NotBlank(message = TIPO_TELEFONE_VAZIO)
    private TelefoneType tipoTelefone;

    @NotBlank(message = DDD_VAZIO)
    private TelefoneDDDType ddd;

    @Size(min = 8, max = 10)
    @NotBlank(message = TELEFONE_VAZIO)
    @Pattern(regexp = NUMEROS, message = NUMERO_TELEFONE_INCORRETAMENTE)
    private String numero;

    public Telefone() {

    }

    public Telefone(TelefoneDDDType ddd, String numero, TelefoneType tipoTelefone) {
        this.setDdd(ddd);
        this.setTipoTelefone(tipoTelefone);
        this.setNumero(numero);
    }

    public void setDdd(TelefoneDDDType ddd) {
        this.dddVazio(ddd);
        this.ddd = ddd;
    }

    public void dddVazio(TelefoneDDDType ddd) {
        if (ddd == null) {
            throw new IllegalArgumentException(DDD_VAZIO);
        }
    }

    public TelefoneDDDType getDdd() {
        return ddd;
    }

    public void setNumero(String numero) {
        this.validaNumetoTelefoneIncorreto(numero);
        this.validaTipoTelefone(numero);
        this.numero = numero;
    }

    private TelefoneType validaTipoTelefone(String numero) {
        if (numero.length() == TEL_MAX_SIZE) {
            return TelefoneType.CELULAR;
        }
        if (numero.length() == TEL_MIN_SIZE) {
            return TelefoneType.FIXO;
        } else {
            throw new IllegalArgumentException(TELEFONE_INVALIDO);
        }
    }

    private void validaNumetoTelefoneIncorreto(String numero) {
        if (numero == null || numero.trim().isEmpty() || numero.length() < TEL_MIN_SIZE || numero.length() > TEL_MAX_SIZE) {
            throw new IllegalArgumentException(TELEFONE_PREENCHIDO_INCORRETAMENTE);
        }
    }

    public String getNumero() {
        return numero;
    }

    public TelefoneType getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(TelefoneType tipoTelefone) {
        this.tipoTelefoneVazio(tipoTelefone);
        this.tipoTelefone = tipoTelefone;
    }

    public void tipoTelefoneVazio(TelefoneType tipoTelefone) {
        if (tipoTelefone == null) {
            throw new IllegalArgumentException(TIPO_TELEFONE_VAZIO);
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
