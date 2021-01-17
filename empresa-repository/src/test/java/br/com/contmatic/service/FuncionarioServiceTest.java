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

import br.com.contmatic.assembly.FuncionarioResourceAssembly;
import br.com.contmatic.easyRandom.EasyRandomClass;
import br.com.contmatic.empresa.Funcionario;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

public class FuncionarioServiceTest {

    private static final MongodStarter starter = MongodStarter.getDefaultInstance();

    private static MongodExecutable mongodExe;

    private static MongoClient mongo;

    private MongoDatabase database;

    private static EasyRandomClass randomObject = EasyRandomClass.InstanciaEasyRandomClass();

    @BeforeClass
    public static void setUpBeforeClass() {
        try {
            mongodExe = starter.prepare(new MongodConfigBuilder().version(Version.Main.V3_4).net(new Net("localhost", 12345, Network.localhostIsIPv6())).build());
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
        database.createCollection("Funcionario");
    }

    @Test
    public void deve_armazenar_um_funcionario_no_banco() throws IOException {
        MongoCollection<Document> collection = database.getCollection("Funcionario");
        FuncionarioService repository = new FuncionarioService(database);
        repository.salvar(randomObject.funcionarioRandomizer());
        assertTrue("Deve armazenar uma Funcionario no banco", collection.estimatedDocumentCount() == 1);
    }

    @Test
    public void deve_alterar_um_funcionario_no_banco() throws IOException, InterruptedException {
        MongoCollection<Document> collection = database.getCollection("Funcionario");
        FuncionarioService repository = new FuncionarioService(database);
        Funcionario Funcionario = randomObject.funcionarioRandomizer();
        repository.salvar(Funcionario);
        Funcionario.setNome("Teste");
        repository.alterar(Funcionario);
        FindIterable<Document> findIterable = collection.find(new Document("_id", Funcionario.getCpf()));
        Funcionario novaFuncionario = new FuncionarioResourceAssembly().toResource(findIterable.first());
        assertThat("Deve alterar uma Funcionario no banco", Funcionario.getNome(), equalTo(novaFuncionario.getNome()));
    }

    @Test
    public void deve_apagar_um_funcionario_no_banco() throws IOException {
        MongoCollection<Document> collection = database.getCollection("Funcionario");
        FuncionarioService repository = new FuncionarioService(database);
        Funcionario Funcionario = randomObject.funcionarioRandomizer();
        repository.salvar(Funcionario);
        repository.deletar(Funcionario);
        assertTrue("Deve armazenar uma Funcionario no banco", collection.estimatedDocumentCount() == 0);
    }

    @Test
    public void deve_selecionar_pelo_cnpj_um_funcionario_no_banco() throws IOException {
        FuncionarioService repository = new FuncionarioService(database);
        Funcionario Funcionario = randomObject.funcionarioRandomizer();
        repository.salvar(Funcionario);
        Funcionario FuncionarioBuscada = repository.selecionar(Funcionario.getCpf());
        assertTrue("Deve armazenar uma Funcionario no banco", FuncionarioBuscada != null);
    }

    @Test
    public void deve_selecionar_pelo_cnpj_um_funcionario_no_banco_e_retornar_campos_iguais_como_salvou() throws IOException {
        FuncionarioService repository = new FuncionarioService(database);
        Funcionario Funcionario = randomObject.funcionarioRandomizer();
        repository.salvar(Funcionario);
        Funcionario FuncionarioBuscada = repository.selecionar(Funcionario.getCpf());
        assertTrue(FuncionarioBuscada.toString().equals(Funcionario.toString()));
    }

    @Test
    public void deve_selecionar_pelo_cnpj_um_funcionario_e_nao_deve_ter_valores_nulo() throws IOException {
        FuncionarioService repository = new FuncionarioService(database);
        Funcionario Funcionario = randomObject.funcionarioRandomizer();
        repository.salvar(Funcionario);
        Funcionario FuncionarioBuscada = repository.selecionar(Funcionario.getCpf());
        assertFalse(FuncionarioBuscada.toString().contains("null"));
    }

    @Test
    public void deve_retornar_nulo_quando_manda_uma_lista_nula() throws IOException {
        FuncionarioService repository = new FuncionarioService(database);
        Funcionario Funcionario = randomObject.funcionarioRandomizer();
        repository.salvar(Funcionario);
        List<Funcionario> FuncionarioBuscada = repository.selecionar((List<String>) null);
        assertNull(FuncionarioBuscada);
    }

    @Test
    public void deve_retornar_nulo_quando_manda_uma_lista_vazia() throws IOException {
        FuncionarioService repository = new FuncionarioService(database);
        Funcionario Funcionario = randomObject.funcionarioRandomizer();
        repository.salvar(Funcionario);
        List<Funcionario> FuncionarioBuscada = repository.selecionar(new ArrayList<String>());
        assertNull(FuncionarioBuscada);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_campo_nome_do_funcionario() throws IOException {
        FuncionarioService repository = new FuncionarioService(database);
        Funcionario Funcionario = randomObject.funcionarioRandomizer();
        repository.salvar(Funcionario);
        Funcionario FuncionarioBuscada = repository.selecionar(Arrays.asList("nome")).get(0);
        assertTrue(FuncionarioBuscada.toString().contains("\"nome\":\"" + Funcionario.getNome() + "\""));

    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_campo_nulos_do_funcionario_ao_selecionar_escolhendo_campo() throws IOException {
        FuncionarioService repository = new FuncionarioService(database);
        Funcionario Funcionario = randomObject.funcionarioRandomizer();
        repository.salvar(Funcionario);
        Funcionario FuncionarioBuscada = repository.selecionar(Arrays.asList("nome", "email")).get(0);
        assertTrue(FuncionarioBuscada.toString().contains("null"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_campo_do_funcionario_mesmo_caso_nao_exista_ao_selecionar_escolhendo_campo() throws IOException {
        FuncionarioService repository = new FuncionarioService(database);
        Funcionario Funcionario = randomObject.funcionarioRandomizer();
        repository.salvar(Funcionario);
        Funcionario FuncionarioBuscada = repository.selecionar(Arrays.asList("nome", "salario", "aaaaaaaaaaaaaaaaaaaaaaaaaaaa")).get(0);
        assertTrue(FuncionarioBuscada.toString().contains("null"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_o_funcionario_mesmo_nao_exista_valores() throws IOException {
        FuncionarioService repository = new FuncionarioService(database);
        Funcionario Funcionario = randomObject.funcionarioRandomizer();
        repository.salvar(Funcionario);
        Funcionario FuncionarioBuscada = repository.selecionar(Arrays.asList("aaaaaaaaaaaaaaaaaaaaaaaaaaaa")).get(0);
        assertTrue(FuncionarioBuscada.toString().contains("null"));
    }

    @Test
    public void deve_retornar_o_funcionario_com_o_cpnj_escolhendo_os_campos_da_classe() throws IOException {
        FuncionarioService repository = new FuncionarioService(database);
        Funcionario Funcionario = randomObject.funcionarioRandomizer();
        repository.salvar(Funcionario);
        Funcionario FuncionarioBuscada = repository.selecionar(Funcionario.getCpf());
        assertThat(FuncionarioBuscada.getCpf(), equalTo(Funcionario.getCpf()));
    }

    @Test
    public void deve_selecionar_todos_funcionario_no_banco() throws IOException {
        FuncionarioService repository = new FuncionarioService(database);
        List<Funcionario> Funcionarios = Arrays.asList(randomObject.funcionarioRandomizer(), randomObject.funcionarioRandomizer(), randomObject.funcionarioRandomizer(),
            randomObject.funcionarioRandomizer());
        for(Funcionario Funcionario : Funcionarios) {
            repository.salvar(Funcionario);
        }

        List<Funcionario> FuncionarioBuscada = repository.selecionar();
        assertThat(FuncionarioBuscada.size(), is(Funcionarios.size()));
    }

    @Test
    public void deve_selecionar_todos_funcionario_no_banco_e_tem_que_ser_igual() throws IOException {
        FuncionarioService repository = new FuncionarioService(database);
        List<Funcionario> Funcionarios = Arrays.asList(randomObject.funcionarioRandomizer(), randomObject.funcionarioRandomizer(),
            randomObject.funcionarioRandomizer(), randomObject.funcionarioRandomizer());
        for(Funcionario Funcionario : Funcionarios) {
            repository.salvar(Funcionario);
        }

        List<Funcionario> FuncionarioBuscada = repository.selecionar();
        assertThat(FuncionarioBuscada, is(Funcionarios));
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
