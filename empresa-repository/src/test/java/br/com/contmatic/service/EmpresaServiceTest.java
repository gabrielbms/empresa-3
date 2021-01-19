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

import br.com.contmatic.assembly.EmpresaResourceAssembly;
import br.com.contmatic.easyRandom.EasyRandomClass;
import br.com.contmatic.empresa.Empresa;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

public class EmpresaServiceTest {

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
        database = mongo.getDatabase("empresa");
        database.createCollection("empresa");
    }

    @Test
    public void deve_armazenar_uma_empresa_no_banco() throws IOException {
        MongoCollection<Document> collection = database.getCollection("empresa");
        EmpresaService repository = new EmpresaService(database);
        repository.salvar(randomObject.empresaRandomizer());
        assertTrue("Deve armazenar uma empresa no banco", collection.estimatedDocumentCount() == 1);
    }

    @Test
    public void deve_alterar_uma_empresa_no_banco() throws IOException, InterruptedException {
        MongoCollection<Document> collection = database.getCollection("empresa");
        EmpresaService repository = new EmpresaService(database);
        Empresa empresa = randomObject.empresaRandomizer();
        repository.salvar(empresa);
        empresa.setNome("Teste");
        repository.alterar(empresa);
        FindIterable<Document> findIterable = collection.find(new Document("_id", empresa.getCnpj()));
        Empresa novaEmpresa = new EmpresaResourceAssembly().toResource(findIterable.first());
        assertThat("Deve alterar uma empresa no banco", empresa.getNome(), equalTo(novaEmpresa.getNome()));
    }

    @Test
    public void deve_apagar_uma_empresa_no_banco() throws IOException {
        MongoCollection<Document> collection = database.getCollection("empresa");
        EmpresaService repository = new EmpresaService(database);
        Empresa empresa = randomObject.empresaRandomizer();
        repository.salvar(empresa);
        assertTrue("Deve armazenar uma empresa no banco", collection.estimatedDocumentCount() == 1);
        repository.deletar(empresa);
        assertTrue("Deve armazenar uma empresa no banco", collection.estimatedDocumentCount() == 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void deve_apagar_uma_empresa_selecionado_no_banco() throws IOException {
        MongoCollection<Document> collection = database.getCollection("empresa");
        EmpresaService repository = new EmpresaService(database);
        Empresa empresa = randomObject.empresaRandomizer();
        repository.salvar(empresa);
        Empresa empresaBuscada = repository.selecionar(Arrays.asList("nome")).get(0);
        repository.deletar(empresaBuscada);
        assertTrue("Deve armazenar uma empresa no banco", collection.estimatedDocumentCount() == 0);
    }

    @Test
    public void deve_selecionar_pelo_cnpj_uma_empresa_no_banco() throws IOException {
        EmpresaService repository = new EmpresaService(database);
        Empresa empresa = randomObject.empresaRandomizer();
        repository.salvar(empresa);
        Empresa empresaBuscada = repository.selecionar(empresa.getCnpj());
        assertTrue("Deve armazenar uma empresa no banco", empresaBuscada.getCnpj().equals(empresa.getCnpj()));
    }

    @Test
    public void deve_selecionar_pelo_cnpj_uma_empresa_no_banco_e_retornar_campos_iguais_como_salvou() throws IOException {
        EmpresaService repository = new EmpresaService(database);
        Empresa empresa = randomObject.empresaRandomizer();
        repository.salvar(empresa);
        Empresa empresaBuscada = repository.selecionar(empresa.getCnpj());
        assertTrue(empresaBuscada.toString().equals(empresa.toString()));
    }

    @Test
    public void deve_selecionar_pelo_cnpj_uma_empresa_e_nao_deve_ter_valores_nulo() throws IOException {
        EmpresaService repository = new EmpresaService(database);
        Empresa empresa = randomObject.empresaRandomizer();
        repository.salvar(empresa);
        Empresa empresaBuscada = repository.selecionar(empresa.getCnpj());
        assertFalse(empresaBuscada.toString().contains("null"));
    }
    
    @Test
    public void deve_selecionar_todas_empresa_no_banco() throws IOException {
        EmpresaService repository = new EmpresaService(database);
        List<Empresa> empresas = Arrays.asList(randomObject.empresaRandomizer(), randomObject.empresaRandomizer(), 
            randomObject.empresaRandomizer(), randomObject.empresaRandomizer());
        for(Empresa empresa : empresas) {
            repository.salvar(empresa);
        }

        List<Empresa> empresaBuscada = repository.selecionar();
        assertThat(empresaBuscada.size(), is(empresas.size()));
    }

    @Test
    public void deve_selecionar_todas_empresa_no_banco_e_tem_que_ser_igual() throws IOException {
        EmpresaService repository = new EmpresaService(database);
        List<Empresa> empresas = Arrays.asList(randomObject.empresaRandomizer(), randomObject.empresaRandomizer(),
            randomObject.empresaRandomizer(), randomObject.empresaRandomizer());
        for(Empresa empresa : empresas) {
            repository.salvar(empresa);
        }

        List<Empresa> empresaBuscada = repository.selecionar();
        assertThat(empresaBuscada, is(empresas));
    }

    @Test
    public void deve_retornar_nulo_quando_manda_uma_lista_nula() throws IOException {
        EmpresaService repository = new EmpresaService(database);
        Empresa empresa = randomObject.empresaRandomizer();
        repository.salvar(empresa);
        List<Empresa> empresaBuscada = repository.selecionar((List<String>) null);
        assertNull(empresaBuscada);
    }

    @Test
    public void deve_retornar_nulo_quando_manda_uma_lista_vazia() throws IOException {
        EmpresaService repository = new EmpresaService(database);
        Empresa empresa = randomObject.empresaRandomizer();
        repository.salvar(empresa);
        List<Empresa> empresaBuscada = repository.selecionar(new ArrayList<String>());
        assertNull(empresaBuscada);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_campo_nome_da_empresa() throws IOException {
        EmpresaService repository = new EmpresaService(database);
        Empresa empresa = randomObject.empresaRandomizer();
        repository.salvar(empresa);
        Empresa empresaBuscada = repository.selecionar(Arrays.asList("nome")).get(0);
        assertTrue(empresaBuscada.toString().contains("\"nome\":\"" + empresa.getNome() + "\""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_campo_nulos_da_empresa_ao_selecionar_escolhendo_campo() throws IOException {
        EmpresaService repository = new EmpresaService(database);
        Empresa empresa = randomObject.empresaRandomizer();
        repository.salvar(empresa);
        Empresa empresaBuscada = repository.selecionar(Arrays.asList("nome", "site")).get(0);
        assertTrue(empresaBuscada.toString().contains("null"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_campo_da_empresa_mesmo_caso_nao_exista_ao_selecionar_escolhendo_campo() throws IOException {
        EmpresaService repository = new EmpresaService(database);
        Empresa empresa = randomObject.empresaRandomizer();
        repository.salvar(empresa);
        Empresa empresaBuscada = repository.selecionar(Arrays.asList("nome", "site", "aaaaaaaaaaaaaaaaaaaaaaaaaaaa")).get(0);
        assertTrue(empresaBuscada.toString().contains("null"));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_a_empresa_mesmo_nao_exista_valores() throws IOException {
        EmpresaService repository = new EmpresaService(database);
        Empresa empresa = randomObject.empresaRandomizer();
        repository.salvar(empresa);
        Empresa empresaBuscada = repository.selecionar(Arrays.asList("aaaaaaaaaaaaaaaaaaaaaaaaaaaa")).get(0);
        assertTrue(empresaBuscada.toString().contains("null"));
    }

    @Test
    public void deve_retornar_a_empresa_com_o_cpnj_escolhendo_os_campos_da_classe() throws IOException {
        EmpresaService repository = new EmpresaService(database);
        Empresa empresa = randomObject.empresaRandomizer();
        repository.salvar(empresa);
        Empresa empresaBuscada = repository.selecionar(empresa.getCnpj());
        assertThat(empresaBuscada.getCnpj(), equalTo(empresa.getCnpj()));
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
