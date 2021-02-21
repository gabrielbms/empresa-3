package br.com.contmatic.assembly;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang.StringEscapeUtils;
import org.bson.Document;
import org.junit.Test;

import br.com.contmatic.easyRandom.EasyRandomClass;
import br.com.contmatic.empresa.Produto;

public class ProdutoResourceAssemblyTest {

    private static EasyRandomClass randomObject = EasyRandomClass.InstanciaEasyRandomClass();

    @Test
    public void deve_transformar_uma_classe_Produto_em_document() {
        Produto Produto = randomObject.produtoRandomizerClass();
        Document document = new ProdutoResourceAssembly().toDocument(Produto);
        String ProdutoUTF8 = StringEscapeUtils.unescapeJava(Produto.toString()).replaceAll("\\s", "");
        assertThat(ProdutoUTF8.replace(".00", ".0").replace(".10", ".1").replace(".20", ".2").replace(".30", ".3")
            .replace(".40", ".4").replace(".50", ".5").replace(".60", ".6").replace(".70", ".7")
                .replace(".80", ".8").replace(".90", ".9"),
            equalTo(document.toJson().replaceAll("\\s", "")));
    }

    @Test
    public void deve_retornar_nulo_se_um_Produto_for_nulo_ao_transformar_em_documento() {
        Document document = new FuncionarioResourceAssembly().toDocument(null);
        assertThat(document, equalTo(null));
    }

    @Test
    public void deve_transformar_uma_classe_document_em_Produto() {
        Document document = Document.parse(randomObject.produtoRandomizerClass().toString());
        Produto Produto = new ProdutoResourceAssembly().toResource(document);
        String ProdutoUTF8 = StringEscapeUtils.unescapeJava(Produto.toString()).replaceAll("\\s", "");
        assertThat(ProdutoUTF8.replace(".00", ".0").replace(".10", ".1").replace(".20", ".2").replace(".30", ".3")
            .replace(".40", ".4").replace(".50", ".5").replace(".60", ".6").replace(".70", ".7")
                .replace(".80", ".8").replace(".90", ".9"),
            equalTo(document.toJson().replaceAll("\\s", "")));
    }

    @Test
    public void deve_retornar_nulo_se_um_document_for_nulo_ao_transformar_em_Produto() {
        Produto Produto = new ProdutoResourceAssembly().toResource(null);
        assertThat(Produto, equalTo(null));
    }

}
