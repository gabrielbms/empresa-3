package br.com.contmatic.endereco;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * The Enum Estado.
 * 
 * @author gabriel.santos
 */
public enum EstadoType {
    
    SP("São Paulo – SP"),    
    AC("Acre - AC"),
    AL("Alagoas - AL"),
    AP("Amapá - AP"),
    AM("Amazonas - AM"),
    BA("Bahia - BA"),
    CE("Ceará - CE"),
    DF("Distrito Federal - DF"),
    ES("Espírito Santo * ES"),
    GO("Goiás - GO"),
    MA("Maranhão - MA"),
    MT("Mato Grosso - MT"),
    MS("Mato Grosso do Sul - MS"),
    MG("Minas Gerais - MG"),
    PA("Pará - PA"),
    PB("Paraíba - PB"),
    PR("Paraná - PR"),
    PE("Pernambuco - PE"),
    PI("Piauí - PI"),
    RJ("Rio de Janeiro -RJ"),
    RN("Rio Grande do Norte - RN"),
    RS("Rio Grande do Sul - RS"),
    RO("Rondônia - RO"),
    RR("Roraima - RR"),
    SC("Santa Catarina - SC"),
    SE("Sergipe - SE"),
    TO("Tocantins - TO");

    private String est;

    private EstadoType(String estado) {
        this.est = estado;
    }

    public String getEstado() {
        return est;
    }
    
    private static final List<EstadoType> estados = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int TAMANHO = estados.size();
    private static final Random RANDOM = new Random();
    
    public static EstadoType estadoAleatorio()  {
        return estados.get(RANDOM.nextInt(TAMANHO));
    }
    
}
