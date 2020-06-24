package br.com.contmatic.util.easyRandom.variaveis;

import org.apache.commons.lang3.RandomStringUtils;
import org.jeasy.random.api.Randomizer;

public class CpfGenerate implements Randomizer<String> {
	
	@Override
	public String getRandomValue() {
		return RandomStringUtils.randomNumeric(11);
	}
}
