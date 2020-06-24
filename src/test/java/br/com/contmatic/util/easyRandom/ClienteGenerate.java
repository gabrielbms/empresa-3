package br.com.contmatic.util.easyRandom;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;

import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.util.easyRandom.variaveis.BoletoGenerate;
import br.com.contmatic.util.easyRandom.variaveis.CpfGenerate;
import br.com.contmatic.util.easyRandom.variaveis.EmailGenerate;
import br.com.contmatic.util.easyRandom.variaveis.NomeGenerate;
import br.com.contmatic.util.easyRandom.variaveis.TelefoneGenerate;

public class ClienteGenerate {

	public static Cliente cliente() {

		EasyRandomParameters parametros = new EasyRandomParameters().randomize(FieldPredicates.named("cpf")
				.and(FieldPredicates.ofType(String.class)).and(FieldPredicates.inClass(Cliente.class)),
				new CpfGenerate());

		parametros.randomize(FieldPredicates.named("nome").and(FieldPredicates.ofType(String.class))
				.and(FieldPredicates.inClass(Cliente.class)), new NomeGenerate());

		parametros.randomize(FieldPredicates.named("email").and(FieldPredicates.ofType(String.class))
				.and(FieldPredicates.inClass(Cliente.class)), new EmailGenerate());

		parametros.randomize(FieldPredicates.named("telefones").and(FieldPredicates.ofType(String.class))
				.and(FieldPredicates.inClass(Cliente.class)), new TelefoneGenerate());

		parametros.randomize(FieldPredicates.named("boleto").and(FieldPredicates.ofType(String.class))
				.and(FieldPredicates.inClass(Cliente.class)), new BoletoGenerate());

		return new EasyRandom(parametros).nextObject(Cliente.class);

	}

	public static void main(String[] args) {
		System.out.println(cliente());
	}

}
