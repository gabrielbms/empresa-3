package br.com.contmatic.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.matchers.JUnitMatchers.containsString;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.com.contmatic.assembly.FornecedorResourceAssembly;
import br.com.contmatic.easyRandom.EasyRandomClass;
import br.com.contmatic.empresa.Fornecedor;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

public class FornecedorServiceTest {

	private static final MongodStarter starter = MongodStarter.getDefaultInstance();

	private static MongodExecutable mongodExe;

	private static MongoClient mongo;

	private MongoDatabase database;
	
    private static EasyRandomClass randomObject = EasyRandomClass.InstanciaEasyRandomClass();
    
	@BeforeClass
	public static void setUpBeforeClass() {
		try {
			mongodExe = starter.prepare(new MongodConfigBuilder().version(Version.Main.V3_4)
					.net(new Net("localhost", 12345, Network.localhostIsIPv6())).build());
			mongodExe.start();
			mongo = new MongoClient("localhost", 12345);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Before
	public void setUp() {
		database = mongo.getDatabase("Empresa");
		database.createCollection("Fornecedor");
	}

	@Test
	public void deve_armazenar_uma_Fornecedor_no_banco() throws IOException {
		MongoCollection<Document> collection = database.getCollection("Fornecedor");
		FornecedorService repository = new FornecedorService(database);
		repository.salvar(randomObject.fornecedorRandomizer());
		assertTrue("Deve armazenar uma Fornecedor no banco", collection.estimatedDocumentCount() == 1);
	}

	@Test
	public void deve_alterar_uma_Fornecedor_no_banco() throws IOException, InterruptedException {
		MongoCollection<Document> collection = database.getCollection("Fornecedor");
		FornecedorService repository = new FornecedorService(database);
		Fornecedor Fornecedor = randomObject.fornecedorRandomizer();
		repository.salvar(Fornecedor);
		Fornecedor.setNome("Teste");
		repository.alterar(Fornecedor);
		FindIterable<Document> findIterable = collection.find(new Document("_id", Fornecedor.getCnpj()));
		Fornecedor novaFornecedor = new FornecedorResourceAssembly().toResource(findIterable.first());
		assertThat("Deve alterar uma Fornecedor no banco", Fornecedor.getNome(), equalTo(novaFornecedor.getNome()));
	}

	@Test
	public void deve_apagar_uma_Fornecedor_no_banco() throws IOException {
		MongoCollection<Document> collection = database.getCollection("Fornecedor");
		FornecedorService repository = new FornecedorService(database);
		Fornecedor Fornecedor = randomObject.fornecedorRandomizer();
		repository.salvar(Fornecedor);
		repository.deletar(Fornecedor);
		assertTrue("Deve armazenar uma Fornecedor no banco", collection.estimatedDocumentCount() == 0);
	}

	@Test
	public void deve_selecionar_pelo_cnpj_uma_Fornecedor_no_banco() throws IOException {
		FornecedorService repository = new FornecedorService(database);
		Fornecedor Fornecedor = randomObject.fornecedorRandomizer();
		repository.salvar(Fornecedor);
		Fornecedor FornecedorBuscada = repository.selecionar(Fornecedor.getCnpj());
		assertTrue("Deve armazenar uma Fornecedor no banco", FornecedorBuscada != null);
	}

	@Test
	public void deve_selecionar_pelo_cnpj_uma_Fornecedor_no_banco_e_retornar_campos_iguais_como_salvou()
			throws IOException {
		FornecedorService repository = new FornecedorService(database);
		Fornecedor Fornecedor = randomObject.fornecedorRandomizer();
		repository.salvar(Fornecedor);
		Fornecedor FornecedorBuscada = repository.selecionar(Fornecedor.getCnpj());
		assertTrue(FornecedorBuscada.toString().equals(Fornecedor.toString()));
	}

	@Test
	public void deve_selecionar_pelo_cnpj_uma_Fornecedor_e_nao_deve_ter_valores_nulo() throws IOException {
		FornecedorService repository = new FornecedorService(database);
		Fornecedor Fornecedor = randomObject.fornecedorRandomizer();
		repository.salvar(Fornecedor);
		Fornecedor FornecedorBuscada = repository.selecionar(Fornecedor.getCnpj());
		assertThat(FornecedorBuscada.toString(), not(containsString("null")));
	}

	@Test
	public void deve_retornar_nulo_quando_manda_uma_lista_nula() throws IOException {
		FornecedorService repository = new FornecedorService(database);
		Fornecedor Fornecedor = randomObject.fornecedorRandomizer();
		repository.salvar(Fornecedor);
		List<Fornecedor> FornecedorBuscada = repository.selecionar((List<String>) null);
		assertNull(FornecedorBuscada);
	}

	@Test
	public void deve_retornar_nulo_quando_manda_uma_lista_vazia() throws IOException {
		FornecedorService repository = new FornecedorService(database);
		Fornecedor Fornecedor = randomObject.fornecedorRandomizer();
		repository.salvar(Fornecedor);
		List<Fornecedor> FornecedorBuscada = repository.selecionar(new ArrayList<String>());
		assertNull(FornecedorBuscada);
	}

	@Test
	public void deve_retornar_campo_nome_da_Fornecedor() throws IOException {
		FornecedorService repository = new FornecedorService(database);
		Fornecedor Fornecedor = randomObject.fornecedorRandomizer();
		repository.salvar(Fornecedor);
		Fornecedor FornecedorBuscada = repository.selecionar(Arrays.asList("nome")).get(0);
		assertThat(FornecedorBuscada.toString(), containsString("\"nome\":\"" + Fornecedor.getNome() + "\""));

	}

	@Test
	public void deve_retornar_campo_nulos_da_Fornecedor_ao_selecionar_escolhendo_campo() throws IOException {
		FornecedorService repository = new FornecedorService(database);
		Fornecedor Fornecedor = randomObject.fornecedorRandomizer();
		repository.salvar(Fornecedor);
		Fornecedor FornecedorBuscada = repository.selecionar(Arrays.asList("nome", "email")).get(0);
		assertThat(FornecedorBuscada.toString(), containsString("null"));
	}

	@Test
	public void deve_retornar_campo_da_Fornecedor_mesmo_caso_nao_exista_ao_selecionar_escolhendo_campo()
			throws IOException {
		FornecedorService repository = new FornecedorService(database);
		Fornecedor Fornecedor = randomObject.fornecedorRandomizer();
		repository.salvar(Fornecedor);
		Fornecedor FornecedorBuscada = repository.selecionar(Arrays.asList("nome", "produto", "aaaaaaaaaaaaaaaaaaaaaaaaaaaa")).get(0);
		assertThat(FornecedorBuscada.toString(), containsString("null"));
	}

	@Test
	public void deve_retornar_a_Fornecedor_mesmo_nao_exista_valores() throws IOException {
		FornecedorService repository = new FornecedorService(database);
		Fornecedor Fornecedor = randomObject.fornecedorRandomizer();
		repository.salvar(Fornecedor);
		Fornecedor FornecedorBuscada = repository.selecionar(Arrays.asList("aaaaaaaaaaaaaaaaaaaaaaaaaaaa")).get(0);
		assertThat(FornecedorBuscada.toString(), containsString("null"));
	}

	@Test
	public void deve_retornar_a_Fornecedor_com_o_cpnj_escolhendo_os_campos_da_classe() throws IOException {
		FornecedorService repository = new FornecedorService(database);
		Fornecedor Fornecedor = randomObject.fornecedorRandomizer();
		repository.salvar(Fornecedor);
		Fornecedor FornecedorBuscada = repository.selecionar(Fornecedor.getCnpj());
		assertThat(FornecedorBuscada.getCnpj(), equalTo(Fornecedor.getCnpj()));
	}

	@Test
	public void deve_selecionar_todas_Fornecedor_no_banco() throws IOException {
		FornecedorService repository = new FornecedorService(database);
		List<Fornecedor> Fornecedors = Arrays.asList(randomObject.fornecedorRandomizer(), randomObject.fornecedorRandomizer(),
				randomObject.fornecedorRandomizer(), randomObject.fornecedorRandomizer());
		for (Fornecedor Fornecedor : Fornecedors) {
			repository.salvar(Fornecedor);
		}

		List<Fornecedor> FornecedorBuscada = repository.selecionar();
		assertThat(FornecedorBuscada.size(), is(Fornecedors.size()));
	}

	@Test
	public void deve_selecionar_todas_Fornecedor_no_banco_e_tem_que_ser_igual() throws IOException {
		FornecedorService repository = new FornecedorService(database);
		List<Fornecedor> Fornecedors = Arrays.asList(randomObject.fornecedorRandomizer(), randomObject.fornecedorRandomizer(),
				randomObject.fornecedorRandomizer(), randomObject.fornecedorRandomizer());
		for (Fornecedor Fornecedor : Fornecedors) {
			repository.salvar(Fornecedor);
		}

		List<Fornecedor> FornecedorBuscada = repository.selecionar();
		assertThat(FornecedorBuscada, is(Fornecedors));
	}
	
	@After
	public void tearDown() {
		database.drop();
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		mongo.close();
		mongodExe.stop();
	}
	
}
