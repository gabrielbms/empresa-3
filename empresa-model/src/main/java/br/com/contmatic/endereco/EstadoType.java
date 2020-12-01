package br.com.contmatic.endereco;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * The Enum Estado.
 */
public enum EstadoType {
    
    /** The sp. */
    SP("São Paulo – SP"),    
    
    /** The ac. */
    AC("Acre - AC"),
    
    /** The al. */
    AL("Alagoas - AL"),
    
    /** The ap. */
    AP("Amapá - AP"),
    
    /** The am. */
    AM("Amazonas - AM"),
    
    /** The ba. */
    BA("Bahia - BA"),
    
    /** The ce. */
    CE("Ceará - CE"),
    
    /** The df. */
    DF("Distrito Federal - DF"),
    
    /** The es. */
    ES("Espírito Santo * ES"),
    
    /** The go. */
    GO("Goiás - GO"),
    
    /** The ma. */
    MA("Maranhão - MA"),
    
    /** The mt. */
    MT("Mato Grosso - MT"),
    
    /** The ms. */
    MS("Mato Grosso do Sul - MS"),
    
    /** The mg. */
    MG("Minas Gerais - MG"),
    
    /** The pa. */
    PA("Pará - PA"),
    
    /** The pb. */
    PB("Paraíba - PB"),
    
    /** The pr. */
    PR("Paraná - PR"),
    
    /** The pe. */
    PE("Pernambuco - PE"),
    
    /** The pi. */
    PI("Piauí - PI"),
    
    /** The rj. */
    RJ("Rio de Janeiro -RJ"),
    
    /** The rn. */
    RN("Rio Grande do Norte - RN"),
    
    /** The rs. */
    RS("Rio Grande do Sul - RS"),
    
    /** The ro. */
    RO("Rondônia - RO"),
    
    /** The rr. */
    RR("Roraima - RR"),
    
    /** The sc. */
    SC("Santa Catarina - SC"),
    
    /** The se. */
    SE("Sergipe - SE"),
    
    /** The to. */
    TO("Tocantins - TO");
    
    /** The est. */
    private String est;

    /**
     * Instantiates a new estado.
     *
     * @param estado the estado
     */
    private EstadoType(String estado) {
        this.est = estado;
    }

    /**
     * Gets the estado.
     *
     * @return the estado
     */
    public String getEstado() {
        return est;
    }
    
    private static final List<EstadoType> estados = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int tamanho = estados.size();
    private static final Random random = new Random();
    
    public static EstadoType estadoAleatorio()  {
        return estados.get(random.nextInt(tamanho));
    }
    
}
