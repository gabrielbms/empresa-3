package br.com.contmatic.regex;

/**
 * The Class RegexType.
 * 
 * @author gabriel.santos
 */
public final class RegexType {

    public static final String URL = "^(https|http|file)://[-a-zA-Z0-9+_|!:,.;]*[-a-zA-Z0-9+_|]";

    public static final String SITE = "^(www).[-a-zA-Z0-9+_|!:,.;]*[-a-zA-Z0-9+_|]";

    public static final String CEP = "^[[0-9]{5}-[\\\\d]{3}]+$";

    public static final String NOME = "(\\w|\\s|ç|[á-ú])+";

    public static final String EMAIL = "[\\w-]+@([\\w-]+\\.)+[\\w-]+";

    public static final String LETRAS_NUMEROS = "^[A-Za-záÁ-úÚÇÑ0-9_ '\\\\s]+$";

    public static final String NUMEROS = "([0-9]{8})|([0-9]{9})";

    public static final String NUMERO_FIXO = "([2-5]{1})" + "([0-9]{7})";

    public static final String NUMERO_CELULAR = "9" + "([0-9]{8})";

    private RegexType() {

    }
}
