package br.com.contmatic.util.easyRandom.variaveis;

import org.jeasy.random.api.Randomizer;

import br.com.contmatic.telefone.Telefone;

public class TelefoneGenerate implements Randomizer<Telefone>{
	
	@Override
	public Telefone getRandomValue() {
		return TelefoneGenerate.telefone();
	}

}
