package br.com.contmatic.assembly;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang.StringEscapeUtils;
import org.bson.Document;
import org.junit.Test;

import br.com.contmatic.easyRandom.EasyRandomClass;
import br.com.contmatic.empresa.Funcionario;

public class FuncionarioResourceAssemblyTest {

    private static EasyRandomClass randomObject = EasyRandomClass.InstanciaEasyRandomClass();

    @Test
    public void deve_transformar_uma_classe_funcionario_em_document() {
        Funcionario funcionario = randomObject.funcionarioRandomizer();
        Document document = new FuncionarioResourceAssembly().toDocument(funcionario);
        String funcionarioUTF8 = StringEscapeUtils.unescapeJava(funcionario.toString()).replaceAll("\\s", "");
        assertThat(funcionarioUTF8.replace(".00", ".0").replace(".10", ".1").replace(".20", ".2").replace(".30", ".3")
            .replace(".40", ".4").replace(".50", ".5").replace(".60", ".6")
                .replace(".70", ".7").replace(".80", ".8").replace(".90", ".9"),
            equalTo(document.toJson().replaceAll("\\s", "").replace(".00", ".0").replace(".10", ".1").replace(".20", ".2").replace(".30", ".3").replace(".40", ".4").replace(".50", ".5").replace(".60", ".6")
                .replace(".70", ".7").replace(".80", ".8").replace(".90", ".9")));
    }

    @Test
    public void deve_retornar_nulo_se_um_funcionario_for_nulo_ao_transformar_em_documento() {
        Document document = new FuncionarioResourceAssembly().toDocument(null);
        assertThat(document, equalTo(null));
    }

    @Test
    public void deve_transformar_uma_classe_document_em_funcionario() {
        Funcionario funcionarioTemp = randomObject.funcionarioRandomizer();
        Document document = Document.parse(funcionarioTemp.toString()).append("_id", funcionarioTemp.getCpf());
        Funcionario funcionario = new FuncionarioResourceAssembly().toResource(document);
        document.remove("_id");
        String funcionarioUTF8 = StringEscapeUtils.unescapeJava(funcionario.toString()).replaceAll("\\s", "");
        assertThat(funcionarioUTF8.replace(".00", ".0").replace(".10", ".1").replace(".20", ".2").replace(".30", ".3").replace(".40", ".4").replace(".50", ".5").replace(".60", ".6")
                .replace(".70", ".7").replace(".80", ".8").replace(".90", ".9"),
            equalTo(document.toJson().replaceAll("\\s", "").replace(".00", ".0").replace(".10", ".1").replace(".20", ".2").replace(".30", ".3").replace(".40", ".4").replace(".50", ".5").replace(".60", ".6")
                .replace(".70", ".7").replace(".80", ".8").replace(".90", ".9")));
    }

    @Test
    public void deve_retornar_nulo_se_um_document_for_nulo_ao_transformar_em_funcionario() {
        Funcionario funcionario = new FuncionarioResourceAssembly().toResource(null);
        assertThat(funcionario, equalTo(null));
    }

}
