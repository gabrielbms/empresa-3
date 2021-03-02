package br.com.contmatic.empresa;

import static br.com.contmatic.util.Constantes.DATA_CRIACAO_VAZIO;
import static br.com.contmatic.util.Constantes.DATA_MODIFICACAO_INVALIDA;
import static br.com.contmatic.util.Constantes.ID_MINIMO;
import static br.com.contmatic.util.Constantes.ID_VAZIO;
import static br.com.contmatic.util.Constantes.NOME_INVALIDO;
import static br.com.contmatic.util.Constantes.NOME_MAX_SIZE;
import static br.com.contmatic.util.Constantes.NOME_MIN_SIZE;
import static br.com.contmatic.util.Constantes.PRECO_MINIMO;
import static br.com.contmatic.util.Constantes.PRECO_MINIMO_MENSAGEM;
import static br.com.contmatic.util.Constantes.QUANTIDADE_MINIMA;
import static br.com.contmatic.util.Constantes.QUANTIDADE_MINIMA_MENSAGEM;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_NOME_GRANDE_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_NOME_PEQUENO_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_USUARIO_GRANDE_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_USUARIO_PEQUENO_DEMAIS;
import static br.com.contmatic.util.Constantes.USUARIO_INVALIDO;
import static br.com.contmatic.util.Constantes.USUARIO_MAX_SIZE;
import static br.com.contmatic.util.Constantes.USUARIO_MIN_SIZE;
import static br.com.contmatic.util.RegexType.LETRAS;
import static br.com.contmatic.util.RegexType.validaSeNaoTemEspacosIncorretosECaracteresEspeciaos;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.DateTime;

import br.com.contmatic.groups.Post;
import br.com.contmatic.groups.Put;

/**
 * The Class Produto.
 * 
 * @author gabriel.santos
 */
public class Produto {

    private Integer id;

    @Pattern(regexp = LETRAS, message = NOME_INVALIDO)
    private String nome;

    private Integer quantidade;

    private BigDecimal preco;
    
    @Null(groups = { Put.class })
    @NotNull(message = DATA_CRIACAO_VAZIO, groups = { Post.class })
    private DateTime dataCriacao;
    
    @NotNull(message = DATA_CRIACAO_VAZIO, groups = { Post.class, Put.class })
    private DateTime dataModificacao;
    
    @Null(groups = { Put.class })
    @NotNull(message = DATA_CRIACAO_VAZIO, groups = { Post.class })
    @Pattern(regexp = LETRAS, message = USUARIO_INVALIDO, groups = { Post.class })
    private String usuarioCriacao;
    
    @NotNull(message = DATA_CRIACAO_VAZIO, groups = { Post.class, Put.class })
    @Pattern(regexp = LETRAS, message = USUARIO_INVALIDO, groups = { Put.class, Post.class })
    private String usuarioModificacao;

    public Produto(Integer id, String nome) {
        this.setId(id);
        this.setNome(nome);
    }

    public Produto(Integer id, String nome, Integer quantidade, BigDecimal preco) {
        this.setId(id);
        this.setNome(nome);
        this.setQuantidade(quantidade);
        this.setPreco(preco);
    }

    public Produto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.validaIdIncorreto(id);
        this.id = id;
    }

    private void validaIdIncorreto(Integer id) {
        if (id == null) {
            return;
        }
        if (id.doubleValue() < ID_MINIMO) {
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
        if (nome != null) {
            if (validaSeNaoTemEspacosIncorretosECaracteresEspeciaos(nome)) {
                throw new IllegalArgumentException(NOME_INVALIDO);
            }
        }
    }

    private void validaNomeIncorreto(String nome) {
        this.validaNomePequenoDemais(nome);
        this.validaNomeGrandeDemais(nome);
    }

    private void validaNomeGrandeDemais(String nome) {
        if (nome != null) {
            if (nome.length() > NOME_MAX_SIZE) {
                throw new IllegalArgumentException(TAMANHO_DO_NOME_GRANDE_DEMAIS);
            }
        }
    }

    private void validaNomePequenoDemais(String nome) {
        if (nome != null) {
            if (nome.length() < NOME_MIN_SIZE) {
                throw new IllegalArgumentException(TAMANHO_DO_NOME_PEQUENO_DEMAIS);
            } 
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
        if (quantidade != null) {
            if (quantidade < QUANTIDADE_MINIMA) {
                throw new IllegalArgumentException(QUANTIDADE_MINIMA_MENSAGEM);
            }
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
        if (!preco.equals(BigDecimal.valueOf(0))) {
            if (preco.doubleValue() < PRECO_MINIMO) {
                throw new IllegalArgumentException(PRECO_MINIMO_MENSAGEM);
            }
        }
    }
    
    public DateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(DateTime dataCriacao) {
        validarDataAbsurda(dataCriacao);
        this.dataCriacao = dataCriacao;
    }
    
    private void validarDataAbsurda(DateTime dataCriacao) {
        if (dataCriacao != null) {
            if (dataCriacao.getYear() < 1950 || dataCriacao.getYear() > DateTime.now().getYear()) {
                throw new IllegalArgumentException(DATA_CRIACAO_VAZIO);
            }
        }
    }

    public DateTime getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(DateTime dataModificacao) {
        validaDataModificacao(dataModificacao);
        this.dataModificacao = dataModificacao;
    }
    
    private void validaDataModificacao(DateTime dataModificacao) {
        if (dataModificacao != null) {
            if (dataModificacao.isBefore(dataCriacao)) {
                throw new IllegalArgumentException(DATA_MODIFICACAO_INVALIDA);
            }
        }
    }

    public String getUsuarioCriacao() {
        return usuarioCriacao;
    }

    public void setUsuarioCriacao(String usuarioCriacao) {
        validaEspacosIncorretosECaracteresEspeciaisNoUsuario(usuarioCriacao);
        validaUsuarioIncorreto(usuarioCriacao);
        this.usuarioCriacao = usuarioCriacao;
    }

    public String getUsuarioModificacao() {
        return usuarioModificacao;
    }

    public void setUsuarioModificacao(String usuarioModificacao) {
        validaEspacosIncorretosECaracteresEspeciaisNoUsuario(usuarioModificacao);
        validaUsuarioIncorreto(usuarioModificacao);
        this.usuarioModificacao = usuarioModificacao;
    }
    
    private void validaEspacosIncorretosECaracteresEspeciaisNoUsuario(String usuario) {
        if (usuario != null) {
            if (validaSeNaoTemEspacosIncorretosECaracteresEspeciaos(usuario)) {
                throw new IllegalArgumentException(USUARIO_INVALIDO);
            }
        }
    }

    private void validaUsuarioIncorreto(String usuario) {
        this.validausuarioMenorQueOTamanhoMinimo(usuario);
        this.validausuarioMaiorQueOTamanhoMinimo(usuario);
    }

    private void validausuarioMaiorQueOTamanhoMinimo(String usuario) {
        if (usuario != null) {
            if (usuario.length() > USUARIO_MAX_SIZE) {
                throw new IllegalArgumentException(TAMANHO_DO_USUARIO_GRANDE_DEMAIS);
            }
        }
    }

    private void validausuarioMenorQueOTamanhoMinimo(String usuario) {
        if (usuario != null) {
            if (usuario.length() < USUARIO_MIN_SIZE) {
                throw new IllegalArgumentException(TAMANHO_DO_USUARIO_PEQUENO_DEMAIS);
            }
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

}
