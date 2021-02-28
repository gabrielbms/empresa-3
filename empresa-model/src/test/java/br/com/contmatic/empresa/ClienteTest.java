package br.com.contmatic.empresa;

import static br.com.contmatic.telefone.TelefoneDDDType.DDD11;
import static br.com.contmatic.telefone.TelefoneType.CELULAR;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.MutableDateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

import br.com.contmatic.easyRandom.EasyRandomClass;
import br.com.contmatic.telefone.Telefone;
import nl.jqno.equalsverifier.EqualsVerifier;

/**
 * The Class ClienteTest.
 * 
 * @author gabriel.santos
 */
@FixMethodOrder(NAME_ASCENDING)
public class ClienteTest {

    private static Cliente cliente;

    private Telefone telefone;

    private Set<Telefone> telefones = new HashSet<>();

    private static EasyRandomClass randomObject = EasyRandomClass.InstanciaEasyRandomClass();

    @Before
    public void setUp() {
        ClienteTest.cliente = randomObject.clienteRandomizer();
        telefone = new Telefone(DDD11, "978457845", CELULAR);
        telefones.add(telefone);
    }

    @Test
    public void deve_testar_se_o_cpf_aceita_numeros() {
        cliente.setCpf("43701888817");
        assertEquals("43701888817", cliente.getCpf());
    }

    @Test
    public void nao_deve_aceitar_null_no_cpf() {
        cliente.setCpf(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_vazio_no_cpf() {
        cliente.setCpf("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espacos_em_branco_no_cpf() {
        cliente.setCpf("  ");
    }

    @Test(expected = IllegalStateException.class)
    public void nao_deve_aceitar_letras_no_cpf() {
        cliente.setCpf("abcdefabcde");
    }

    @Test(expected = IllegalStateException.class)
    public void nao_deve_aceitar_cpf_invalido() {
        cliente.setCpf("43701888818");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_caracteres_especiais_no_cpf() {
        cliente.setCpf("@#$");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espacos_no_inicio_do_cpf() {
        cliente.setCpf(" 43701888817");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espacos_no_fim_do_cpf() {
        cliente.setCpf("43701888817 ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espacos_no_meio_do_cpf() {
        cliente.setCpf("437018      88817");
    }

    @Test
    public void deve_testar_o_getCpf() {
        cliente.setCpf("43701888817");
        assertEquals("43701888817", cliente.getCpf());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_tamanho_menor_no_cpf() {
        cliente.setCpf("1010101010");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_tamanho_maior_no_cpf() {
        cliente.setCpf("121212121212");
    }

    @Test(expected = IllegalStateException.class)
    public void deve_testar_exception_da_validação_do_cpf() {
        cliente.setCpf("43701888818");
    }

    @Test
    public void deve_testar_se_o_nome_aceita_letras() {
        cliente.setNome("Gabriel");
        assertEquals("Gabriel", cliente.getNome());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_vazio_no_nome() {
        cliente.setNome("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espacos_no_nome() {
        cliente.setNome("          ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_caracteres_especiais_no_nome() {
        cliente.setNome("@#$");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espacos_no_inicio_do_nome() {
        cliente.setNome(" Gabriel");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espacos_no_final_do_nome() {
        cliente.setNome("Gabriel ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_mais_que_dois_espacos_no_meio_do_nome() {
        cliente.setNome("Gabriel         Bueno");
    }

    @Test
    public void deve_testar_se_o_nome_aceita_um_espaco_entre_as_palavras() {
        cliente.setNome("Gabriel Bueno");
        assertEquals("Gabriel Bueno", cliente.getNome());
    }

    @Test
    public void deve_testar_o_getNome() {
        cliente.setNome("Gabriel Bueno");
        assertEquals("Gabriel Bueno", cliente.getNome());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_exception_do_setNome_tamanho_menor() {
        cliente.setNome("a");
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_exception_do_setNome_tamanho_maior() {
        cliente.setNome("abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcaabcabcabcabcabcaabcabcabc" +
    "abcabcaabcabcabcabcabcabcabcabcabcabcabxc");
    }

    @Test
    public void deve_testar_o_getEmail() {
        cliente.getEmail();
        assertEquals(cliente.getEmail(), cliente.getEmail());
    }

    @Test
    public void nao_deve_aceitar_null_no_email() {
        cliente.setEmail(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_vazio_no_email() {
        cliente.setEmail("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espacos_em_branco_no_email() {
        cliente.setEmail("  ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_exception_do_setEmail_tamanho_menor() {
        cliente.setEmail("a");
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_exception_do_setEmail_tamanho_limite() {
        cliente.setEmail("abcabcabcabcabcabcabcbcabcabcaabcabcabcabcabcaabca" +
    "bcabcabcabcabcabcaabcabcabcabxc@gmail.com");
    }

    @Test
    public void deve_testar_exception_do_setEmail_tamanho_maior() {
        cliente.setEmail("abcabcabcabcabcabcabcbcabcabcaabcabcabcabcabcaabca" +
    "bcabcacabcabcabcaabcabcabcabxc@gmail.com");
    }

    @Test
    public void nao_deve_aceitar_telefone_nulo() {
        cliente.setTelefones(null);
    }

    @Test
    public void deve_testar_o_getTelefone() {
        cliente.setTelefones(telefones);
        assertEquals(telefones, cliente.getTelefone());
    }

    @Test
    public void deve_testar_o_getBoleto() {
        cliente.setBoleto(BigDecimal.valueOf(250.00));
        assertEquals(BigDecimal.valueOf(250.00), cliente.getBoleto());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_o_exception_do_boleto() {
        cliente.setBoleto(BigDecimal.valueOf(-20.00));
    }
        
    @Test
    public void nao_deve_aceitar_null_na_data_criacao() {
        cliente.setDataCriacao(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_data_muito_antiga() {
        MutableDateTime dataModificada = new MutableDateTime();
        dataModificada.setDate(1500, 01, 01);
        cliente.setDataCriacao(dataModificada.toDateTime());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_data_no_futuro() {
        MutableDateTime dataModificada = new MutableDateTime();
        dataModificada.setDate(2100, 01, 01);
        cliente.setDataCriacao(dataModificada.toDateTime());
    }
    
    @Test
    public void nao_deve_aceitar_null_na_data_modificacao() {
        cliente.setDataModificacao(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_modificacao_anterior_a_data_criacao() {
        MutableDateTime dataModificada = new MutableDateTime();
        dataModificada.setDate(2021, 02, 25);
        cliente.setDataModificacao(dataModificada.toDateTime());
    }

    @Test
    public void deve_testar_se_o_usuario_criacao_aceita_letras() {
        cliente.setUsuarioCriacao("Gabriel");
        assertEquals("Gabriel", cliente.getUsuarioCriacao());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_vazio_no_usuario_criacao() {
        cliente.setUsuarioCriacao("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espacos_no_usuario_criacao() {
        cliente.setUsuarioCriacao("          ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_caracteres_especiais_no_usuario_criacao() {
        cliente.setUsuarioCriacao("@#$");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espacos_no_inicio_do_usuario_criacao() {
        cliente.setUsuarioCriacao(" Gabriel");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espacos_no_final_do_usuario_criacao() {
        cliente.setUsuarioCriacao("Gabriel ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_mais_que_dois_espacos_no_meio_do_usuario_criacao() {
        cliente.setUsuarioCriacao("Gabriel         Bueno");
    }

    @Test
    public void deve_testar_se_o_usuario_criacao_aceita_um_espaco_entre_as_palavras() {
        cliente.setUsuarioCriacao("Gabriel Bueno");
        assertEquals("Gabriel Bueno", cliente.getUsuarioCriacao());
    }

    @Test
    public void deve_testar_o_get_usuario_criacao() {
        cliente.setUsuarioCriacao("Gabriel Bueno");
        assertEquals("Gabriel Bueno", cliente.getUsuarioCriacao());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_exception_do_set_usuario_criacao_tamanho_menor() {
        cliente.setUsuarioCriacao("a");
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_exception_do_set_usuario_criacao_tamanho_maior() {
        cliente.setUsuarioCriacao("abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcaabcabcabcabcabcaabcabcabc" +
    "abcabcaabcabcabcabcabcabcabcabcabcabcabxc");
    }

    @Test
    public void deve_testar_se_o_usuario_modificacao_aceita_letras() {
        cliente.setUsuarioModificacao("Gabriel");
        assertEquals("Gabriel", cliente.getUsuarioModificacao());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_vazio_no_usuario_modificacao() {
        cliente.setUsuarioModificacao("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espacos_no_usuario_modificacao() {
        cliente.setUsuarioModificacao("          ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_caracteres_especiais_no_usuario_modificacao() {
        cliente.setUsuarioModificacao("@#$");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espacos_no_inicio_do_usuario_modificacao() {
        cliente.setUsuarioModificacao(" Gabriel");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espacos_no_final_do_usuario_modificacao() {
        cliente.setUsuarioModificacao("Gabriel ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_mais_que_dois_espacos_no_meio_do_usuario_modificacao() {
        cliente.setUsuarioModificacao("Gabriel         Bueno");
    }

    @Test
    public void deve_testar_se_o_usuario_modificacao_aceita_um_espaco_entre_as_palavras() {
        cliente.setUsuarioModificacao("Gabriel Bueno");
        assertEquals("Gabriel Bueno", cliente.getUsuarioModificacao());
    }

    @Test
    public void deve_testar_o_get_usuario_modificacao() {
        cliente.setUsuarioModificacao("Gabriel Bueno");
        assertEquals("Gabriel Bueno", cliente.getUsuarioModificacao());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_exception_do_set_usuario_modificacao_tamanho_menor() {
        cliente.setUsuarioModificacao("a");
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_exception_do_set_usuario_modificacao_tamanho_maior() {
        cliente.setUsuarioModificacao("abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcaabcabcabcabcabcaabcabcabc" +
    "abcabcaabcabcabcabcabcabcabcabcabcabcabxc");
    }

    @Test
    public void deve_retornar_true_no_hashCode_com_clientes_iguais() {
        Cliente cliente2 = cliente;
        assertEquals(cliente.hashCode(), cliente2.hashCode());
    }

    @Test
    public void deve_retornar_false_no_hashCode_com_um_cliente_de_cpf_null() {
        Cliente cliente2 = new Cliente(null, "Gabriel", telefones, BigDecimal.valueOf(250.00));
        assertNotEquals(cliente.hashCode(), cliente2.hashCode());
    }

    @Test
    public void deve_retornar_true_no_equals_com_clientes_iguais() {
        Cliente cliente2 = cliente;
        assertEquals(cliente, cliente2);
    }

    @Test
    public void deve_retornar_false_no_equals_com_um_cliente_de_cpf_null() {
        Cliente cliente2 = new Cliente(null, "Gabriela", telefones, BigDecimal.valueOf(270.00));
        assertNotEquals(cliente, cliente2);
    }

    @Test
    public void deve_retornar_true_no_equals_comparando_um_cliente_com_ele_mesmo() {
        assertEquals(cliente, cliente);
    }

    @Test
    public void deve_retornar_false_no_equals_comparando_um_cliente_com_null() {
        assertNotEquals(cliente, null);
    }

    @Test
    public void deve_retornar_true_no_equals_comparando_dois_clientes_de_cpf_null() {
        Cliente cliente1 = new Cliente(null, "Gabriel", telefones, BigDecimal.valueOf(250.00));
        Cliente cliente2 = new Cliente(null, "Gabriel", telefones, BigDecimal.valueOf(250.00));
        assertEquals(cliente1, cliente2);
    }

    @Test
    public void deve_retornar_false_no_equals_com_clientes_de_cpf_diferentes() {
        Cliente cliente1 = new Cliente("14591819051", "Gabriel", telefones, BigDecimal.valueOf(250.00));
        Cliente cliente2 = new Cliente("95341978072", "Gabriela", telefones, BigDecimal.valueOf(270.00));
        assertNotEquals(cliente2, cliente1);
    }

    @Test
    public void deve_retornar_false_no_equals_com_clientes_e_um_numero_aleatorio() {
        assertNotEquals(cliente, new Object());
    }

    @Test
    public void toString_deve_retornar_null() {
        Cliente clienteNull = new Cliente(null, null, null, new BigDecimal("1"));
        String clienteNullToString = clienteNull.toString();
        assertEquals(clienteNull.toString(), clienteNullToString);
    }

    @Test
    public void toString_deve_retornar_valores_preenchidos() {
        String clienteToString = cliente.toString();
        assertEquals(cliente.toString(), clienteToString);
    }

    @Test
    public void simpleEqualsContract() {
        EqualsVerifier.simple().forClass(Cliente.class).verify();
    }

    @After
    public void tearDown() {
        System.out.println(cliente);
    }

}
