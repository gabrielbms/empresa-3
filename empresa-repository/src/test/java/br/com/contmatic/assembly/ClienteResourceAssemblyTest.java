package br.com.contmatic.assembly;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.apache.commons.lang.StringEscapeUtils;
import org.bson.Document;
import org.junit.Test;

import br.com.contmatic.easyRandom.EasyRandomClass;
import br.com.contmatic.empresa.Cliente;

public class ClienteResourceAssemblyTest {

    private static EasyRandomClass randomObject = EasyRandomClass.InstanciaEasyRandomClass();

    @Test
    public void deve_transformar_uma_classe_cliente_em_document() {
        Cliente cliente = randomObject.clienteRandomizer();
        cliente.setBoleto(BigDecimal.valueOf(250.10));
        Document document = new ClienteResourceAssembly().toDocument(cliente);
        String clienteUTF8 = StringEscapeUtils.unescapeJava(cliente.toString()).replaceAll("\\s", "");
        assertThat(clienteUTF8, equalTo(document.toJson().replaceAll("\\s", "")));
    }

    @Test
    public void deve_retornar_nulo_se_um_cliente_for_nulo_ao_transformar_em_documento() {
        Document document = new FuncionarioResourceAssembly().toDocument(null);
        assertThat(document, equalTo(null));
    }

    @Test
    public void deve_transformar_uma_classe_document_em_cliente() {
        Document document = Document.parse(randomObject.clienteRandomizer().toString());
        Cliente cliente = new ClienteResourceAssembly().toResource(document);
        String clienteUTF8 = StringEscapeUtils.unescapeJava(cliente.toString()).replaceAll("\\s", "");
        assertThat(clienteUTF8.replace(".10,", ".1,").replace(".20,", ".2,").replace(".30,", ".3,")
            .replace(".40,", ".4,").replace(".50,", ".5,").replace(".60,", ".6,").replace(".70,", ".7,").replace(".80,", ".8,").replace(".90,", ".9,"), equalTo(document.toJson().replaceAll("\\s", "").replace(".10,", ".1,").replace(".20,", ".2,").replace(".30,", ".3,")
            .replace(".40,", ".4,").replace(".50,", ".5,").replace(".60,", ".6,").replace(".70,", ".7,").replace(".80,", ".8,").replace(".90,", ".9,")));
    }

    @Test
    public void deve_retornar_nulo_se_um_document_for_nulo_ao_transformar_em_cliente() {
        Cliente cliente = new ClienteResourceAssembly().toResource(null);
        assertThat(cliente, equalTo(null));
    }

}
