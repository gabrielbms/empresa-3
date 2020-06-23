package br.com.contmatic.endereco;

/**
 * The Enum Estado.
 */
public enum Estado {
    
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
    private Estado(String estado) {
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
    
}
