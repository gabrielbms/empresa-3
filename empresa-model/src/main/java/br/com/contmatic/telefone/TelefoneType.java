package br.com.contmatic.telefone;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * The Enum TipoTelefone.
 * 
 * @author gabriel.santos
 */
public enum TelefoneType {

    CELULAR("Celular", 9),
    FIXO("Fixo", 8);

    private String descricao;

    private int tamanho;

    private TelefoneType(String descricao, int tamanho) {
        this.descricao = descricao;
        this.tamanho = tamanho;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public int getTamanho() {
        return this.tamanho;
    }

    private static final List<TelefoneType> tipoTelefone = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int size = tipoTelefone.size();
    private static final Random random = new Random();

    public static TelefoneType tipoTelefoneAleatorio() {
        return tipoTelefone.get(random.nextInt(size));
    }
}
