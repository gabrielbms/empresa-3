package br.com.contmatic.telefone;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * The Enum TelefoneDDD.
 * 
 * @author gabriel.santos
 */
public enum TelefoneDDDType {
        
    DDD11(11, "São Paulo – SP"),
    DDD12(12, "São José dos Campos – SP"),
    DDD13(13, "Santos – SP"),
    DDD14(14, "Bauru – SP"),
    DDD15(15, "Sorocaba – SP"),
    DDD16(16, "Ribeirão Preto – SP"),
    DDD17(17, "São José do Rio Preto – SP"),
    DDD18(18, "Presidente Prudente – SP"),
    DDD19(19, "Campinas – SP"),
    DDD21(21, "Rio de Janeiro – RJ"),
    DDD22(22, "Campos dos Goytacazes – RJ"),
    DDD24(24, "Volta Redonda – RJ"),
    DDD27(27, "Vila Velha/Vitória – ES"),
    DDD28(28, "Cachoeiro de Itapemirim – ES"),
    DDD31(31, "Belo Horizonte – MG"),
    DDD32(32, "Juiz de Fora – MG"),
    DDD33(33, "Governador Valadares – MG"),
    DDD34(34, "Uberlândia – MG"),
    DDD35(35, "Poços de Caldas – MG"),
    DDD37(37, "Divinópolis – MG"),
    DDD38(38, "Montes Claros – MG"),
    DDD41(41, "Curitiba – PR"),
    DDD42(42, "Ponta Grossa – PR"),
    DDD43(43, "Londrina – PR"),
    DDD44(44, "Maringá – PR"),
    DDD45(45, "Foz do Iguaçú – PR"),
    DDD46(46, "Francisco Beltrão/Pato Branco – PR"),
    DDD47(47, "Joinville – SC"),
    DDD48(48, "Florianópolis – SC"),
    DDD49(49, "Chapecó – SC"),
    DDD51(51, "Porto Alegre – RS"),
    DDD53(53, "Pelotas – RS"),
    DDD54(54, "Caxias do Sul – RS"),
    DDD55(55, "Santa Maria – RS"),
    DDD61(61, "Brasília – DF"),
    DDD62(62, "Goiânia – GO"),
    DDD63(63, "Palmas – TO"),
    DDD64(64, "Rio Verde – GO"),
    DDD65(65, "Cuiabá – MT"),
    DDD66(66, "Rondonópolis – MT"),
    DDD67(67, "Campo Grande – MS"),
    DDD68(68, "Rio Branco – AC"),
    DDD69(69, "Porto Velho – RO"),
    DDD71(71, "Salvador – BA"),
    DDD73(73, "Ilhéus – BA"),
    DDD74(74, "Juazeiro – BA"),
    DDD75(75, "Feira de Santana – BA"),
    DDD77(77, "Barreiras – BA"),
    DDD79(79, "Aracaju – SE"),
    DDD81(81, "Recife – PE"),
    DDD82(82, "Maceió – AL"),
    DDD83(83, "João Pessoa – PB"),
    DDD84(84, "Natal – RN"),
    DDD85(85, "Fortaleza – CE"),
    DDD86(86, "Teresina – PI"),
    DDD87(87, "Petrolina – PE"),
    DDD88(88, "Juazeiro do Norte – CE"),
    DDD89(89, "Picos – PI"),
    DDD91(91, "Belém – PA"),
    DDD92(92, "Manaus – AM"),
    DDD93(93, "Santarém – PA"),
    DDD94(94, "Marabá – PA"),
    DDD95(95, "Boa Vista – RR"),
    DDD96(96, "Macapá – AP"),
    DDD97(97, "Coari – AM"),
    DDD98(98, "São Luís – MA"),
    DDD99(99, "Imperatriz – MA");
    
    TelefoneDDDType(int ddd, String complemento) {
        this.ddd = ddd;
        this.complemento = complemento;
    }

    private int ddd;

    private String complemento;

    public int getDdd() {
        return ddd;
    }

    public String getComplemento() {
        return complemento;
    }

    private static final List<TelefoneDDDType> DDDs = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int tamanho = DDDs.size();
    private static final Random random = new Random();

    public static TelefoneDDDType DddAleatorio() {
        return DDDs.get(random.nextInt(tamanho));
    }
}
