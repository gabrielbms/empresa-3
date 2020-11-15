package br.com.contmatic.util;

import static br.com.contmatic.util.Constantes.NAO_ACEITA_CARACTERES_ESPECIAIS;
import static br.com.contmatic.util.Constantes.NAO_ACEITA_ESPACO_NO_INICIO_OU_NO_FIM;
import static br.com.contmatic.util.Constantes.NAO_ACEITA_MAIS_QUE_UM_ESPACO_ENTRE_AS_PALAVRAS;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Class RegexType.
 * 
 * @author gabriel.santos
 */
public final class RegexType {
	
	/** The Constant LETRAS. */
	public static final String LETRAS = "^[A-Za-záÁ-úÚÇÑ_ '\\\\s]+$";

	/** The Constant NUMERO. */
	public static final String NUMEROS = "^[0-9]*";
	
	/** The Constant LETRAS_NUMEROS. */
	public static final String LETRAS_NUMEROS = "^[A-Za-záÁ-úÚÇÑ0-9_ '\\\\s]+$";

	/** The Constant URL. */
	public static final String URL = "^(www).[-a-zA-Z0-9+_|!:,.;]*[-a-zA-Z0-9+_|]";

	/** The Constant CEP. */
	public static final String CEP = "^[[0-9]{5}-[\\\\d]{3}]+$";

	/** The Constant EMAIL. */
	public static final String EMAIL = "[\\w-]+@([\\w-]+\\.)+[\\w-]+";
	
	public static final String ESPACOS_DUPLICADOS = "\\s\\s+";
	
	public static final String CARACTERES_ESPECIAIS = "[!@#$%¨&*()_+=|`´^~\\/{}]";

	/**
	 * Instantiates a new regex type.
	 */
	private RegexType() {
	}
	
	public static boolean validaSeNaoTemEspacosIncorretosECaracteresEspeciaos(String string) {
		return !validaEspacosIncorretosECaracteresEspeciais(string);
	}
	
	public static boolean validaEspacosIncorretosECaracteresEspeciais(String string) {
		try {
			verificaSeNaoTemCaracteresEspeciais(string);
			verificaSeNaoTemEspacosNoInicioOuFim(string);
			verificaSeNaoTemMaisQueUmEspacoEntreAsPalavras(string);
		} catch (IllegalArgumentException e) {
			throw e;
		} 
		return true;
	}
	
	public static void verificaSeNaoTemCaracteresEspeciais(String string) {
		if (contemCaracteresEspeciais(string)) {
			throw new IllegalArgumentException(NAO_ACEITA_CARACTERES_ESPECIAIS);
		}
	}

	public static boolean contemCaracteresEspeciais(String string) {
		Pattern regexCaracteresEspeciais = Pattern.compile(CARACTERES_ESPECIAIS);
		Matcher matcherCaracteresEspeciais = regexCaracteresEspeciais.matcher(string);
		return matcherCaracteresEspeciais.find();
	}
	
	public static void verificaSeNaoTemEspacosNoInicioOuFim(String string) {
		if (contemEspacosNoInicioOuFim(string)) {
			throw new IllegalArgumentException(NAO_ACEITA_ESPACO_NO_INICIO_OU_NO_FIM);
		}
	}

	private static boolean contemEspacosNoInicioOuFim(String string) {
		String stringSemTrim = string;
		String stringComTrim = string.trim();
		return !stringSemTrim.equals(stringComTrim);
	}
	
	public static void verificaSeNaoTemMaisQueUmEspacoEntreAsPalavras(String string) {
		if (contemMaisQueUmEspacoEntreAsPalavras(string)) {
			throw new IllegalArgumentException(NAO_ACEITA_MAIS_QUE_UM_ESPACO_ENTRE_AS_PALAVRAS);
		}
	}

	public static boolean contemMaisQueUmEspacoEntreAsPalavras(String string) {
		String patternStr = ESPACOS_DUPLICADOS;
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(string);
		return matcher.find();
	}
	
}