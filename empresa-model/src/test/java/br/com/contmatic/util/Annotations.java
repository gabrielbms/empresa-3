package br.com.contmatic.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 * The Class Annotations.
 */
public class Annotations {

    /**
     * Mensagem erro annotation.
     *
     * @param t the t
     * @return true, if successful
     */
    public static boolean MensagemErroAnnotation(Object t) {
        Validator validador = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Object>> erros = validador.validate(t);
        List<String> errosMsg = new ArrayList<>();
        for(ConstraintViolation<Object> violation : erros) {
            errosMsg.add(violation.getMessage());
        }
        String verificaErros = errosMsg.toString().replace("[", "").replace("]", "");
        if ((!(verificaErros.isEmpty() || verificaErros.length() < 2))) {
            return true;
        } else {
            return false;
        }
    }

}
