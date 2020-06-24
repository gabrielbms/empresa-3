package br.com.contmatic.util.easyRandom.variaveis;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.jeasy.random.api.Randomizer;

public class EmailGenerate implements Randomizer<String> {

	private List<String> emails = Arrays.asList("@gmail.com", "@hotmail.com");
	
	@Override
	public String getRandomValue() {
		return RandomStringUtils.randomAlphabetic(new Random().nextInt(8) + 10)
				+ emails.get(new Random().nextInt(emails.size() -1));
	}

}
