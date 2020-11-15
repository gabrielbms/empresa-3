package br.com.contmatic.easyRandom;

import java.math.BigDecimal;
import java.time.LocalDate;
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
