package br.com.contmatic.util;

import java.util.InputMismatchException;

public final class Validate {
	
	private Validate() {
	}

	public static boolean isCPF(String cpf) {
		return validaCpf(cpf);
	}
	
	public static boolean isNotCPF(String cpf) {
		return !isCPF(cpf);
	}

	private static boolean validaCpf(String cpf) {
		if (verificaCpfIncorreto(cpf)) {
			return false;
		}
		return realizaCalculoDoDigitoVerificadorDoCpf(cpf);
	}

	private static boolean verificaCpfIncorreto(String cpf) {
		return cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222")
				|| cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555")
				|| cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888")
				|| cpf.equals("99999999999") || (cpf.length() != 11);
	}

	private static boolean realizaCalculoDoDigitoVerificadorDoCpf(String cpf) {
		try {
			char dig10 = calculoDosDigitosVerificadoresDoCpf(cpf, 10);
			char dig11 = calculoDosDigitosVerificadoresDoCpf(cpf, 11);
			return verificaPosicaoDosDigitosDoCpf(cpf, dig10, dig11);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

	private static char calculoDosDigitosVerificadoresDoCpf(String cpf, int peso) {
		int soma = realizaContaDoDigitoVerificadorDoCpf(cpf, peso);
		return verificaResultadoDaContaDoDigitoVerificadorDoCpf(soma);
	}

	private static int realizaContaDoDigitoVerificadorDoCpf(String cpf, int peso) {
		int soma = 0;
		int loop = peso - 1;
		for (int i = 0; i < loop; i++) {
			int num = (cpf.charAt(i) - 48);
			soma = soma + (num * peso);
			peso = peso - 1;
		}
		return soma;
	}

	private static char verificaResultadoDaContaDoDigitoVerificadorDoCpf(int soma) {
		int resultado = 11 - (soma % 11);
		char dig;
		if ((resultado == 10) || (resultado == 11)) {
			dig = '0';
		} else {
			dig = (char) (resultado + 48);
		}
		return dig;
	}

	private static boolean verificaPosicaoDosDigitosDoCpf(String cpf, char dig10, char dig11) {
		if (dig10 == cpf.charAt(9) && dig11 == cpf.charAt(10)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isCNPJ(String cnpj) {
		return validaCnpj(cnpj);
	}
	
	public static boolean isNotCNPJ(String cnpj) {
		return !isCNPJ(cnpj);
	}

	private static boolean validaCnpj(String cnpj) {
		if (verificaCnpjIncorreto(cnpj)) {
			return false;
		}
		return realizaCalculoDoDigitoVerificadorDoCnpj(cnpj);
	}

	private static boolean verificaCnpjIncorreto(String cnpj) {
		return cnpj.equals("00000000000000") || cnpj.equals("11111111111111") || cnpj.equals("22222222222222")
				|| cnpj.equals("33333333333333") || cnpj.equals("44444444444444") || cnpj.equals("55555555555555")
				|| cnpj.equals("66666666666666") || cnpj.equals("77777777777777") || cnpj.equals("88888888888888")
				|| cnpj.equals("99999999999999") || (cnpj.length() != 14);
	}

	private static boolean realizaCalculoDoDigitoVerificadorDoCnpj(String cnpj) {
		try {
			char dig13 = calculoDosDigitosVerificadoresDoCnpj(cnpj, 13);
			char dig14 = calculoDosDigitosVerificadoresDoCnpj(cnpj, 14);
			return verificaPosicaoDosDigitosDoCnpj(cnpj, dig13, dig14);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

	private static char calculoDosDigitosVerificadoresDoCnpj(String cnpj, int dig) {
		int soma = realizaContaDoDigitoVerificadorDoCnpj(cnpj, dig);
		return verificaResultadoDaContaDoDigitoVerificadorDoCnpj(soma);
	}

	private static int realizaContaDoDigitoVerificadorDoCnpj(String cnpj, int dig) {
		int soma = 0;
		int peso = 2;
		for (int i = dig - 2; i >= 0; i--) {
			int num = (cnpj.charAt(i) - 48);
			soma = soma + (num * peso);
			peso = peso + 1;
			if (peso == 10) {
				peso = 2;
			}
		}
		return soma;
	}

	private static char verificaResultadoDaContaDoDigitoVerificadorDoCnpj(int soma) {
		char dig;
		int resultado = soma % 11;
		if ((resultado == 0) || (resultado == 1)) {
			dig = '0';
		} else {
			dig = (char) ((11 - resultado) + 48);
		}
		return dig;
	}

	private static boolean verificaPosicaoDosDigitosDoCnpj(String cnpj, char dig13, char dig14) {
		if (dig13 == cnpj.charAt(12) && dig14 == cnpj.charAt(13)) {
			return true;
		} else {
			return false;
		}
	}
}