package br.com.contmatic.service;

import static br.com.contmatic.easyRandom.Geradores.randomiza;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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

import br.com.contmatic.assembly.ProdutoResourceAssembly;
import br.com.contmatic.easyRandom.EasyRandomClass;
import br.com.contmatic.empresa.Produto;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

public class ProdutoServiceTest {
	
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
		database.createCollection("Produto");
	}

	@Test
	public void deve_armazenar_um_produto_no_banco() throws IOException {
		MongoCollection<Document> collection = database.getCollection("Produto");
		ProdutoService repository = new ProdutoService(database);
		repository.salvar(randomObject.produtoRandomizerClass());
		assertTrue("Deve armazenar um produto no banco", collection.estimatedDocumentCount() == 1);
	}

	@Test
	public void deve_alterar_um_produto_no_banco() throws IOException, InterruptedException {
		MongoCollection<Document> collection = database.getCollection("Produto");
		ProdutoService repository = new ProdutoService(database);
		Produto Produto = randomObject.produtoRandomizerClass();
		repository.salvar(Produto);
		Produto.setNome("Teste");
		repository.alterar(Produto);
		FindIterable<Document> findIterable = collection.find(new Document("_id", Produto.getId()));
		Produto novaProduto = new ProdutoResourceAssembly().toResource(findIterable.first());
		assertThat("Deve alterar um produto no banco", Produto.getNome(), equalTo(novaProduto.getNome()));
	}

	@Test
	public void deve_apagar_um_produto_no_banco() throws IOException {
		MongoCollection<Document> collection = database.getCollection("Produto");
		ProdutoService repository = new ProdutoService(database);
		Produto Produto = randomObject.produtoRandomizerClass();
		repository.salvar(Produto);
	      assertTrue("Deve armazenar um produto no banco", collection.estimatedDocumentCount() == 1);
		repository.deletar(Produto);
		assertTrue("Deve armazenar um produto no banco", collection.estimatedDocumentCount() == 0);
	}
	
    @Test(expected = IllegalArgumentException.class)
    public void deve_apagar_um_produto_selecionado_no_banco() throws IOException {
        MongoCollection<Document> collection = database.getCollection("{classe}");
        ProdutoService repository = new ProdutoService(database);
        Produto produto = randomObject.produtoRandomizerClass();
        repository.salvar(produto);
        Produto produtoBuscado = repository.selecionar(Arrays.asList("nome")).get(0);
        repository.deletar(produtoBuscado);
        assertTrue("Deve armazenar um produto no banco", collection.estimatedDocumentCount() == 0);
    }

	@Test
	public void deve_selecionar_pelo_id_um_produto_no_banco() throws IOException {
		ProdutoService repository = new ProdutoService(database);
		Produto Produto = randomObject.produtoRandomizerClass();
		repository.salvar(Produto);
		Produto ProdutoBuscada = repository.selecionar(Produto.getId());
		assertTrue("Deve armazenar um produto no banco", ProdutoBuscada.getId().equals(Produto.getId()));
	}

	@Test
	public void deve_selecionar_pelo_id_um_produto_no_banco_e_retornar_campos_iguais_como_salvou()
			throws IOException {
		ProdutoService repository = new ProdutoService(database);
		Produto Produto = randomObject.produtoRandomizerClass();
		repository.salvar(Produto);
		Produto ProdutoBuscada = repository.selecionar(Produto.getId());
		assertTrue(ProdutoBuscada.toString().equals(Produto.toString()));
	}

	@Test
	public void deve_selecionar_pelo_id_um_produto_e_nao_deve_ter_valores_nulo() throws IOException {
		ProdutoService repository = new ProdutoService(database);
		Produto Produto = randomObject.produtoRandomizerClass();
		repository.salvar(Produto);
		Produto ProdutoBuscada = repository.selecionar(Produto.getId());
		assertFalse(ProdutoBuscada.toString().contains("null"));
	}
	
    @Test
    public void deve_selecionar_todos_produto_no_banco() throws IOException {
        ProdutoService repository = new ProdutoService(database);
        List<Produto> produtos = Arrays.asList(randomObject.produtoRandomizerClass(),
                randomObject.produtoRandomizerClass(), randomObject.produtoRandomizerClass(),
                randomObject.produtoRandomizerClass(), randomObject.produtoRandomizerClass());
        for (Produto produto : produtos) {
            for (int i = 0; i < 5; i++) {
                if (produto.getId().equals(produtos.get(i).getId())) {
                    produto.setId(produto.getId() + randomiza(3));
                }
            }
            repository.salvar(produto);
        }

        List<Produto> ProdutoBuscada = repository.selecionar();
        assertThat(ProdutoBuscada.size(), is(produtos.size()));
    }

    @Test
    public void deve_selecionar_todos_produtos_no_banco_e_tem_que_ser_igual() throws IOException {
        ProdutoService repository = new ProdutoService(database);
        List<Produto> Produtos = Arrays.asList(randomObject.produtoRandomizerClass(), randomObject.produtoRandomizerClass(),
                randomObject.produtoRandomizerClass(), randomObject.produtoRandomizerClass());
        for (Produto Produto : Produtos) {
            repository.salvar(Produto);
        }

        List<Produto> ProdutoBuscada = repository.selecionar();
        assertThat(ProdutoBuscada, is(Produtos));
    }

	@Test
	public void deve_retornar_nulo_quando_manda_uma_lista_nula() throws IOException {
		ProdutoService repository = new ProdutoService(database);
		Produto Produto = randomObject.produtoRandomizerClass();
		repository.salvar(Produto);
		List<Produto> ProdutoBuscada = repository.selecionar((List<String>) null);
		assertNull(ProdutoBuscada);
	}

	@Test
	public void deve_retornar_nulo_quando_manda_uma_lista_vazia() throws IOException {
		ProdutoService repository = new ProdutoService(database);
		Produto Produto = randomObject.produtoRandomizerClass();
		repository.salvar(Produto);
		List<Produto> ProdutoBuscada = repository.selecionar(new ArrayList<String>());
		assertNull(ProdutoBuscada);
	}

	@Test (expected = IllegalArgumentException.class)
	public void deve_retornar_campo_nome_do_produto() throws IOException {
		ProdutoService repository = new ProdutoService(database);
		Produto Produto = randomObject.produtoRandomizerClass();
		repository.salvar(Produto);
		Produto ProdutoBuscada = repository.selecionar(Arrays.asList("nome")).get(0);
		assertTrue(ProdutoBuscada.toString().contains("\"nome\":\"" + Produto.getNome() + "\""));
	}

	@Test (expected = IllegalArgumentException.class)
	public void deve_retornar_campo_nulos_do_produto_ao_selecionar_escolhendo_campo() throws IOException {
		ProdutoService repository = new ProdutoService(database);
		Produto Produto = randomObject.produtoRandomizerClass();
		repository.salvar(Produto);
		Produto ProdutoBuscada = repository.selecionar(Arrays.asList("nome", "email")).get(0);
		assertTrue(ProdutoBuscada.toString().contains("null"));
	}

	@Test (expected = IllegalArgumentException.class)
	public void deve_retornar_campo_do_produto_mesmo_caso_nao_exista_ao_selecionar_escolhendo_campo()
			throws IOException {
		ProdutoService repository = new ProdutoService(database);
		Produto Produto = randomObject.produtoRandomizerClass();
		repository.salvar(Produto);
		Produto ProdutoBuscada = repository.selecionar(Arrays.asList("nome", "salario", "aaaaaaaaaaaaaaaaaaaaaaaaaaaa")).get(0);
		assertTrue(ProdutoBuscada.toString().contains("null"));
	}

	@Test (expected = IllegalArgumentException.class)
	public void deve_retornar_o_produto_mesmo_nao_exista_valores() throws IOException {
		ProdutoService repository = new ProdutoService(database);
		Produto Produto = randomObject.produtoRandomizerClass();
		repository.salvar(Produto);
		Produto ProdutoBuscada = repository.selecionar(Arrays.asList("aaaaaaaaaaaaaaaaaaaaaaaaaaaa")).get(0);
		assertTrue(ProdutoBuscada.toString().contains("null"));
	}

	@Test
	public void deve_retornar_o_produto_com_o_cpnj_escolhendo_os_campos_da_classe() throws IOException {
		ProdutoService repository = new ProdutoService(database);
		Produto Produto = randomObject.produtoRandomizerClass();
		repository.salvar(Produto);
		Produto ProdutoBuscada = repository.selecionar(Produto.getId());
		assertThat(ProdutoBuscada.getId(), equalTo(Produto.getId()));
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
