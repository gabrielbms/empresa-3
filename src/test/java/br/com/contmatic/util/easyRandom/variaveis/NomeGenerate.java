package br.com.contmatic.util.easyRandom.variaveis;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.jeasy.random.api.Randomizer;

public class NomeGenerate implements Randomizer<String> {

	List<String> nomes = Arrays.asList("Gabriel", "Bueno");
	
	@Override
	public String getRandomValue() {
		return nomes.get(new Random().nextInt(nomes.size()));
	}

}
