package br.com.contmatic.telefone;

/**
 * The Enum TelefoneDDD.
 */
public enum TelefoneDDD {
        
    /** The ddd11. */
    DDD11(11, "São Paulo – SP"),
    
    /** The ddd12. */
    DDD12(12, "São José dos Campos – SP"),
    
    /** The ddd13. */
    DDD13(13, "Santos – SP"),
    
    /** The ddd14. */
    DDD14(14, "Bauru – SP"),
    
    /** The ddd15. */
    DDD15(15, "Sorocaba – SP"),
    
    /** The ddd16. */
    DDD16(16, "Ribeirão Preto – SP"),
    
    /** The ddd17. */
    DDD17(17, "São José do Rio Preto – SP"),
    
    /** The ddd18. */
    DDD18(18, "Presidente Prudente – SP"),
    
    /** The ddd19. */
    DDD19(19, "Campinas – SP"),
    
    /** The ddd21. */
    DDD21(21, "Rio de Janeiro – RJ"),
    
    /** The ddd22. */
    DDD22(22, "Campos dos Goytacazes – RJ"),
    
    /** The ddd24. */
    DDD24(24, "Volta Redonda – RJ"),
    
    /** The ddd27. */
    DDD27(27, "Vila Velha/Vitória – ES"),
    
    /** The ddd28. */
    DDD28(28, "Cachoeiro de Itapemirim – ES"),
    
    /** The ddd31. */
    DDD31(31, "Belo Horizonte – MG"),
    
    /** The ddd32. */
    DDD32(32, "Juiz de Fora – MG"),
    
    /** The ddd33. */
    DDD33(33, "Governador Valadares – MG"),
    
    /** The ddd34. */
    DDD34(34, "Uberlândia – MG"),
    
    /** The ddd35. */
    DDD35(35, "Poços de Caldas – MG"),
    
    /** The ddd37. */
    DDD37(37, "Divinópolis – MG"),
    
    /** The ddd38. */
    DDD38(38, "Montes Claros – MG"),
    
    /** The ddd41. */
    DDD41(41, "Curitiba – PR"),
    
    /** The ddd42. */
    DDD42(42, "Ponta Grossa – PR"),
    
    /** The ddd43. */
    DDD43(43, "Londrina – PR"),
    
    /** The ddd44. */
    DDD44(44, "Maringá – PR"),
    
    /** The ddd45. */
    DDD45(45, "Foz do Iguaçú – PR"),
    
    /** The ddd46. */
    DDD46(46, "Francisco Beltrão/Pato Branco – PR"),
    
    /** The ddd47. */
    DDD47(47, "Joinville – SC"),
    
    /** The ddd48. */
    DDD48(48, "Florianópolis – SC"),
    
    /** The ddd49. */
    DDD49(49, "Chapecó – SC"),
    
    /** The ddd51. */
    DDD51(51, "Porto Alegre – RS"),
    
    /** The ddd53. */
    DDD53(53, "Pelotas – RS"),
    
    /** The ddd54. */
    DDD54(54, "Caxias do Sul – RS"),
    
    /** The ddd55. */
    DDD55(55, "Santa Maria – RS"),
    
    /** The ddd61. */
    DDD61(61, "Brasília – DF"),
    
    /** The ddd62. */
    DDD62(62, "Goiânia – GO"),
    
    /** The ddd63. */
    DDD63(63, "Palmas – TO"),
    
    /** The ddd64. */
    DDD64(64, "Rio Verde – GO"),
    
    /** The ddd65. */
    DDD65(65, "Cuiabá – MT"),
    
    /** The ddd66. */
    DDD66(66, "Rondonópolis – MT"),
    
    /** The ddd67. */
    DDD67(67, "Campo Grande – MS"),
    
    /** The ddd68. */
    DDD68(68, "Rio Branco – AC"),
    
    /** The ddd69. */
    DDD69(69, "Porto Velho – RO"),
    
    /** The ddd71. */
    DDD71(71, "Salvador – BA"),
    
    /** The ddd73. */
    DDD73(73, "Ilhéus – BA"),
    
    /** The ddd74. */
    DDD74(74, "Juazeiro – BA"),
    
    /** The ddd75. */
    DDD75(75, "Feira de Santana – BA"),
    
    /** The ddd77. */
    DDD77(77, "Barreiras – BA"),
    
    /** The ddd79. */
    DDD79(79, "Aracaju – SE"),
    
    /** The ddd81. */
    DDD81(81, "Recife – PE"),
    
    /** The ddd82. */
    DDD82(82, "Maceió – AL"),
    
    /** The ddd83. */
    DDD83(83, "João Pessoa – PB"),
    
    /** The ddd84. */
    DDD84(84, "Natal – RN"),
    
    /** The ddd85. */
    DDD85(85, "Fortaleza – CE"),
    
    /** The ddd86. */
    DDD86(86, "Teresina – PI"),
    
    /** The ddd87. */
    DDD87(87, "Petrolina – PE"),
    
    /** The ddd88. */
    DDD88(88, "Juazeiro do Norte – CE"),
    
    /** The ddd89. */
    DDD89(89, "Picos – PI"),
    
    /** The ddd91. */
    DDD91(91, "Belém – PA"),
    
    /** The ddd92. */
    DDD92(92, "Manaus – AM"),
    
    /** The ddd93. */
    DDD93(93, "Santarém – PA"),
    
    /** The ddd94. */
    DDD94(94, "Marabá – PA"),
    
    /** The ddd95. */
    DDD95(95, "Boa Vista – RR"),
    
    /** The ddd96. */
    DDD96(96, "Macapá – AP"),
    
    /** The ddd97. */
    DDD97(97, "Coari – AM"),
    
    /** The ddd98. */
    DDD98(98, "São Luís – MA"),
    
    /** The ddd99. */
    DDD99(99, "Imperatriz – MA");
    
    /**
     * Instantiates a new telefone DDD.
     *
     * @param ddd the ddd
     * @param complemento the complemento
     */
    TelefoneDDD(int ddd, String complemento) {
        this.ddd = ddd;
        this.complemento = complemento;
    }
    
    /** The ddd. */
    private int ddd;
    
    /** The complemento. */
    private String complemento;
    
    /**
     * Gets the ddd.
     *
     * @return the ddd
     */
    public int getDdd() {
        return ddd;
    }
 
    /**
     * Gets the complemento.
     *
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
    }
}
