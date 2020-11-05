package br.com.contmatic.assembly;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang.StringEscapeUtils;
import org.bson.Document;
import org.junit.AfterClass;
import org.junit.Test;

import br.com.contmatic.easyRandom.EasyRandomClass;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.mongoDB.MongoDbConnection;

public class EmpresaResourceAssemblyTest {
	
    private static EasyRandomClass randomObject = EasyRandomClass.InstanciaEasyRandomClass();
    
	/** The empresa. */
	private static Empresa empresa;
	
	@Test
	public void deve_transformar_uma_classe_empresa_em_document() {
		Empresa empresa = randomObject.empresaRandomizer();
		Document document = new EmpresaResourceAssembly().toDocument(empresa);
		String empresaUTF8 = StringEscapeUtils.unescapeJava(empresa.toString()).replaceAll("\\s", "");
		assertThat(empresaUTF8, equalTo(document.toJson().replaceAll("\\s", "").replaceAll("\\\\\\\\", "\\\\")));
	}

	@Test
	public void deve_retornar_nulo_se_um_empresa_for_nulo_ao_transformar_em_documento() {
		Document document = new EmpresaResourceAssembly().toDocument(null);
		assertThat(document, equalTo(null));
	}

	@Test
	public void deve_transformar_uma_classe_document_em_empresa() {
		Empresa empresaTemp = randomObject.empresaRandomizer();
		Document document = Document.parse(empresaTemp.toString()).append("_id", empresaTemp.getCnpj());
		Empresa empresa = new EmpresaResourceAssembly().toResource(document);
		document.remove("_id");
		String empresaUTF8 = StringEscapeUtils.unescapeJava(empresa.toString()).replaceAll("\\s", "");
		assertThat(empresaUTF8, equalTo(document.toJson().replaceAll("\\s", "").replaceAll("\\\\\\\\", "\\\\")));
	}

	@Test
	public void deve_retornar_nulo_se_um_document_for_nulo_ao_transformar_em_empresa() {
		Empresa empresa = new EmpresaResourceAssembly().toResource(null);
		assertThat(empresa, equalTo(null));
	}
	
    @AfterClass
    public static void envia_para_base_de_dados() {
		EmpresaResourceAssemblyTest.empresa = randomObject.empresaRandomizer();
        MongoDbConnection.SentToDatabaseEmpresa(empresa);
    }

}
