package br.com.contmatic.service;

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

import br.com.contmatic.assembly.ClienteResourceAssembly;
import br.com.contmatic.easyRandom.EasyRandomClass;
import br.com.contmatic.empresa.Cliente;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

public class ClienteServiceTest {

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
        database = mongo.getDatabase("empresa");
        database.createCollection("cliente");
    }

    @Test
    public void deve_armazenar_um_cliente_no_banco() throws IOException {
        MongoCollection<Document> collection = database.getCollection("cliente");
        ClienteService repository = new ClienteService(database);
        repository.salvar(randomObject.clienteRandomizer());
        assertTrue("Deve armazenar um cliente no banco", collection.estimatedDocumentCount() == 1);
    }

    @Test
    public void deve_alterar_um_cliente_no_banco() throws IOException, InterruptedException {
        MongoCollection<Document> collection = database.getCollection("cliente");
        ClienteService repository = new ClienteService(database);
        Cliente cliente = randomObject.clienteRandomizer();
        repository.salvar(cliente);
        cliente.setNome("Teste");
        repository.alterar(cliente);
        FindIterable<Document> findIterable = collection.find(new Document("_id", cliente.getCpf()));
        Cliente novoCliente = new ClienteResourceAssembly().toResource(findIterable.first());
        assertThat("Deve alterar um cliente no banco", cliente.getNome(), equalTo(novoCliente.getNome()));
    }

    @Test
    public void deve_apagar_um_cliente_no_banco() throws IOException {
        MongoCollection<Document> collection = database.getCollection("cliente");
        ClienteService repository = new ClienteService(database);
        Cliente cliente = randomObject.clienteRandomizer();
        repository.salvar(cliente);
        assertTrue("Deve armazenar um cliente no banco", collection.estimatedDocumentCount() == 1);
        repository.deletar(cliente);
        assertTrue("Deve armazenar um cliente no banco", collection.estimatedDocumentCount() == 0);
    }

    @Test
    public void deve_selecionar_pelo_cpf_um_cliente_no_banco() throws IOException {
        ClienteService repository = new ClienteService(database);
        Cliente cliente = randomObject.clienteRandomizer();
        repository.salvar(cliente);
        Cliente clienteBuscado = repository.selecionar(cliente.getCpf());
        assertTrue("Deve armazenar um cliente no banco", clienteBuscado.getCpf().equals(cliente.getCpf()));
    }

    @Test
    public void deve_selecionar_pelo_cpf_um_cliente_no_banco_e_retornar_campos_iguais_como_salvou() throws IOException {
        ClienteService repository = new ClienteService(database);
        Cliente cliente = randomObject.clienteRandomizer();
        repository.salvar(cliente);
        Cliente clienteBuscado = repository.selecionar(cliente.getCpf());
        assertTrue(clienteBuscado.toString().equals(cliente.toString()));
    }

    @Test
    public void deve_selecionar_pelo_cpf_um_cliente_e_nao_deve_ter_valores_nulo() throws IOException {
        ClienteService repository = new ClienteService(database);
        Cliente cliente = randomObject.clienteRandomizer();
        repository.salvar(cliente);
        Cliente clienteBuscado = repository.selecionar(cliente.getCpf());
        assertFalse(clienteBuscado.toString().contains("null"));
    }
    
    @Test
    public void deve_selecionar_todas_cliente_no_banco() throws IOException {
        ClienteService repository = new ClienteService(database);
        List<Cliente> clientes = Arrays.asList(randomObject.clienteRandomizer(), randomObject.clienteRandomizer(),
            randomObject.clienteRandomizer(), randomObject.clienteRandomizer());
        for(Cliente cliente : clientes) {
            repository.salvar(cliente);
        }

        List<Cliente> clienteBuscado = repository.selecionar();
        assertThat(clienteBuscado.size(), is(clientes.size()));
    }

    @Test
    public void deve_selecionar_todos_cliente_no_banco_e_tem_que_ser_igual() throws IOException {
        ClienteService repository = new ClienteService(database);
        List<Cliente> clientes = Arrays.asList(randomObject.clienteRandomizer(), randomObject.clienteRandomizer(),
            randomObject.clienteRandomizer(), randomObject.clienteRandomizer());
        for(Cliente cliente : clientes) {
            repository.salvar(cliente);
        }

        List<Cliente> clienteBuscada = repository.selecionar();
        assertThat(clienteBuscada, is(clientes));
    }

    @Test
    public void deve_retornar_nulo_quando_manda_uma_lista_nula() throws IOException {
        ClienteService repository = new ClienteService(database);
        Cliente cliente = randomObject.clienteRandomizer();
        repository.salvar(cliente);
        List<Cliente> clienteBuscado = repository.selecionar((List<String>) null);
        assertNull(clienteBuscado);
    }

    @Test
    public void deve_retornar_nulo_quando_manda_uma_lista_vazia() throws IOException {
        ClienteService repository = new ClienteService(database);
        Cliente cliente = randomObject.clienteRandomizer();
        repository.salvar(cliente);
        List<Cliente> clienteBuscado = repository.selecionar(new ArrayList<String>());
        assertNull(clienteBuscado);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_campo_nome_do_cliente() throws IOException {
        ClienteService repository = new ClienteService(database);
        Cliente cliente = randomObject.clienteRandomizer();
        repository.salvar(cliente);
        Cliente clienteBuscado = repository.selecionar(Arrays.asList("nome")).get(0);
        assertTrue(clienteBuscado.toString().contains("\"nome\":\"" + cliente.getNome() + "\""));

    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_campo_nulos_do_cliente_ao_selecionar_escolhendo_campo() throws IOException {
        ClienteService repository = new ClienteService(database);
        Cliente cliente = randomObject.clienteRandomizer();
        repository.salvar(cliente);
        Cliente clienteBuscado = repository.selecionar(Arrays.asList("nome", "email")).get(0);
        assertTrue(clienteBuscado.toString().contains("null"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_campo_do_cliente_mesmo_caso_nao_exista_ao_selecionar_escolhendo_campo() throws IOException {
        ClienteService repository = new ClienteService(database);
        Cliente cliente = randomObject.clienteRandomizer();
        repository.salvar(cliente);
        Cliente ClienteBuscado = repository.selecionar(Arrays.asList("nome", "email", "aaaaaaaaaaaaaaaaaaaaaaaaaaaa")).get(0);
        assertTrue(ClienteBuscado.toString().contains("null"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_ao_cliente_mesmo_nao_exista_valores() throws IOException {
        ClienteService repository = new ClienteService(database);
        Cliente cliente = randomObject.clienteRandomizer();
        repository.salvar(cliente);
        Cliente clienteBuscado = repository.selecionar(Arrays.asList("aaaaaaaaaaaaaaaaaaaaaaaaaaaa")).get(0);
        assertTrue(clienteBuscado.toString().contains("null"));
    }

    @Test
    public void deve_retornar_ao_cliente_com_o_cpnj_escolhendo_os_campos_da_classe() throws IOException {
        ClienteService repository = new ClienteService(database);
        Cliente cliente = randomObject.clienteRandomizer();
        repository.salvar(cliente);
        Cliente clienteBuscado = repository.selecionar(cliente.getCpf());
        assertThat(clienteBuscado.getCpf(), equalTo(cliente.getCpf()));
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
