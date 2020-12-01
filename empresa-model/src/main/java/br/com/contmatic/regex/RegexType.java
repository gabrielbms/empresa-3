package br.com.contmatic.regex;

/**
 * The Class RegexType.
 */
public final class RegexType {

    /** The Constant URL. */
    public static final String URL = "^(https|http|file)://[-a-zA-Z0-9+_|!:,.;]*[-a-zA-Z0-9+_|]";

    /** The Constant URL. */
    public static final String SITE = "^(www).[-a-zA-Z0-9+_|!:,.;]*[-a-zA-Z0-9+_|]";

    /** The Constant CEP. */
    public static final String CEP = "^[[0-9]{5}-[\\\\d]{3}]+$";

    /** The Constant NOME. */
    public static final String NOME = "(\\w|\\s|ç|[á-ú])+";

    /** The Constant EMAIL. */
    public static final String EMAIL = "[\\w-]+@([\\w-]+\\.)+[\\w-]+";

    /** The Constant ENDERECO. */
    public static final String LETRAS_NUMEROS = "^[A-Za-záÁ-úÚÇÑ0-9_ '\\\\s]+$";

    /** The Constant NUMERO. */
    public static final String NUMEROS = "([0-9]{8})|([0-9]{9})";

    /** The Constant NUMERO FIXO */
    public static final String NUMERO_FIXO = "([2-5]{1})" + "([0-9]{7})";

    /** The Constant NUMERO CELULAR */
    public static final String NUMERO_CELULAR = "9" + "([0-9]{8})";

    /**
     * Instantiates a new regex type.
     */
    private RegexType() {

    }
}
