package br.com.contmatic.util.easyRandom.variaveis;

import java.math.BigDecimal;
import org.jeasy.random.api.Randomizer;

public class BoletoGenerate implements Randomizer<BigDecimal> {

	@Override
	public BigDecimal getRandomValue() {
		return BigDecimal.valueOf(Math.random());
	}

}
