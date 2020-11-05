package br.com.contmatic.easyRandom;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Geradores {
	
	static Random random = new Random();
	
    public static String DominioEmail() {
        Random random = new Random();
        Integer numero = random.nextInt(3);
        String dominio = null;
        switch (numero) {
            case 0:
                dominio = "gmail";
                break;
            case 1:
                dominio = "hotmail";
                break;
            case 2:
                dominio = "yahoo";
                break;
        }
        return "@" + dominio + ".com.br";
    }
	
	public static String geraCpf() {
		int[] n1eros = new int[9];
		for (int i = 0; i < n1eros.length; i++) {
			n1eros[i] = random.nextInt(9);
		}
		int primeiroDigito = 0;
		for (int i = 0; i < 8; i++) {
			primeiroDigito = n1eros[i] * 10 - i;
		}
		int divisaoPrimeiro = primeiroDigito / 11;
		int multiplicacaoPrimeiro = 11 * divisaoPrimeiro;
		int subtracaoPrimeiro = primeiroDigito - multiplicacaoPrimeiro;
		int primeiro = 11 - subtracaoPrimeiro;
		String aux = Integer.toString(primeiro);

		if (primeiro >= 11) {
			primeiro += -primeiro;
		} else if (aux.length() >= 2) {
			primeiro += -10;
		}
		int segundoDigito = 0;
		for (int i = 0; i < 8; i++) {
			segundoDigito = n1eros[i] * 11 - 1;
		}
		segundoDigito += primeiro * 2;
		int divisaoSegundo = segundoDigito / 11;
		int multiplicacaoSegundo = 11 * divisaoSegundo;
		int subtracaoSegundo = segundoDigito - multiplicacaoSegundo;
		int segundo = 11 - subtracaoSegundo;
		String aux2 = Integer.toString(segundo);

		if (segundo >= 11) {
			segundo += -segundo;
		} else if (aux2.length() >= 2) {
			segundo += -10;
		}

		return Arrays.toString(n1eros).replace("[", "").replace(",", "").replace("]", "").replace(" ", "") + primeiro
				+ segundo;
	}
	
	public static String geraCnpj() {
		StringBuilder iniciais = new StringBuilder();
		Integer numero;
		for (int i = 0; i < 14; i++) {
			numero = random.nextInt();
			iniciais.append(numero.toString().substring(1, 2));
		}
		return iniciais.toString();
	}
	
	public static BigDecimal generateRandomBigDecimalValueFromRange(BigDecimal valorMinimo, BigDecimal Valormaximo) {
	    BigDecimal randomBigDecimal = valorMinimo.add(new BigDecimal(Math.random()).multiply(Valormaximo.subtract(valorMinimo)));
	    return randomBigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);
	}
	
	public static LocalDate geraData(org.joda.time.LocalDate dataMin, org.joda.time.LocalDate dataMax) {
		int diaMinConvertido = dataMin.getDayOfMonth();
		int mesMinConvertido = dataMin.getMonthOfYear();
		int anoMinConvertido = dataMin.getYear();
		long diaMin = LocalDate.of(anoMinConvertido, mesMinConvertido, diaMinConvertido).toEpochDay();
		int diaMaxConvertido = dataMax.getDayOfMonth();
		int mesMaxConvertido = dataMax.getMonthOfYear();
		int anoMaxConvertido = dataMax.getYear();
		long diaMax = LocalDate.of(anoMaxConvertido, mesMaxConvertido, diaMaxConvertido).toEpochDay();
		long diaAleatorio = ThreadLocalRandom.current().nextLong(diaMin, diaMax);
		LocalDate dataAleatoria = LocalDate.ofEpochDay(diaAleatorio);
		return dataAleatoria;
	}

}
