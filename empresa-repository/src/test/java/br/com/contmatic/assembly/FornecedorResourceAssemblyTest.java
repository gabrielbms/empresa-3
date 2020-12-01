package br.com.contmatic.assembly;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang.StringEscapeUtils;
import org.bson.Document;
import org.junit.AfterClass;
import org.junit.Test;

import br.com.contmatic.easyRandom.EasyRandomClass;
import br.com.contmatic.empresa.Fornecedor;
import br.com.contmatic.mongoDB.MongoDbConnection;

public class FornecedorResourceAssemblyTest {

    private static EasyRandomClass randomObject = EasyRandomClass.InstanciaEasyRandomClass();

    /** The fornecedor. */
    private static Fornecedor fornecedor;

    @Test
    public void deve_transformar_uma_classe_fornecedor_em_document() {
        Fornecedor fornecedor = randomObject.fornecedorRandomizer();
        Document document = new FornecedorResourceAssembly().toDocument(fornecedor);
        String fornecedorUTF8 = StringEscapeUtils.unescapeJava(fornecedor.toString()).replaceAll("\\s", "");
        assertThat(fornecedorUTF8.replace(".00", ".0").replace(".10", ".1").replace(".20", ".2").replace(".30", ".3")
            .replace(".40", ".4").replace(".50", ".5").replace(".60", ".6")
                .replace(".70", ".7").replace(".80", ".8").replace(".90", ".9"),
            equalTo(document.toJson().replaceAll("\\s", "")));
    }

    @Test
    public void deve_retornar_nulo_se_um_fornecedor_for_nulo_ao_transformar_em_documento() {
        Document document = new FornecedorResourceAssembly().toDocument(null);
        assertThat(document, equalTo(null));
    }

    @Test
    public void deve_transformar_uma_classe_document_em_fornecedor() {
        Document document = Document.parse(randomObject.fornecedorRandomizer().toString());
        Fornecedor fornecedor = new FornecedorResourceAssembly().toResource(document);
        String fornecedorUTF8 = StringEscapeUtils.unescapeJava(fornecedor.toString()).replaceAll("\\s", "");
        assertThat(fornecedorUTF8.replace(".00", ".0").replace(".10", ".1").replace(".20", ".2").replace(".30", ".3")
            .replace(".40", ".4").replace(".50", ".5").replace(".60", ".6")
                .replace(".70", ".7").replace(".80", ".8").replace(".90", ".9"),
            equalTo(document.toJson().replaceAll("\\s", "")));
    }

    @Test
    public void deve_retornar_nulo_se_um_document_for_nulo_ao_transformar_em_fornecedor() {
        Fornecedor fornecedor = new FornecedorResourceAssembly().toResource(null);
        assertThat(fornecedor, equalTo(null));
    }

    @AfterClass
    public static void envia_para_base_de_dados() {
        FornecedorResourceAssemblyTest.fornecedor = randomObject.fornecedorRandomizer();
        MongoDbConnection.SentToDatabaseFornecedor(fornecedor);
    }

}
