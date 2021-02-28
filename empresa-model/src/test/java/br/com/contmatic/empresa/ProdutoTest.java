package br.com.contmatic.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.math.BigDecimal;

import org.joda.time.MutableDateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.easyRandom.EasyRandomClass;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import nl.jqno.equalsverifier.EqualsVerifier;

/**
 * The Class FuncionarioTest.
 * 
 * @author gabriel.santos
 */
public class ProdutoTest {

    private static Produto produto;

    private static EasyRandomClass randomObject = EasyRandomClass.InstanciaEasyRandomClass();

    @BeforeClass
    public static void setUpBeforeClass() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.util");
    }

    @Before
    public void setUp() {
        ProdutoTest.produto = randomObject.produtoRandomizerClass();
    }

    @Test
    public void deve_testar_se_o_nome_aceita_letras() {
        produto.setNome("Ryzen 5 2600");
        assertEquals("Ryzen 5 2600", produto.getNome());
    }

    @Test
    public void nao_deve_aceitar_null_no_nome() {
        produto.setNome(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_vazio_no_nome() {
        produto.setNome("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espacos_em_brancos_no_nome() {
        produto.setNome("          ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_caracteres_especiais_no_nome() {
        produto.setNome("@#$");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espaco_no_inicio_do_nome() {
        produto.setNome(" Ryzen 5 2600");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espaco_no_final_do_nome() {
        produto.setNome("Ryzen 5 2600 ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espacos_no_meio_do_nome() {
        produto.setNome("Ryzen 5        2600");
    }

    @Test
    public void deve_testar_se_o_nome_aceita_um_espaco_entre_as_palavras() {
        produto.setNome("Ryzen 5 2600");
        assertEquals("Ryzen 5 2600", produto.getNome());
    }

    @Test
    public void deve_testar_o_setNome() {
        produto.setNome("Gabriel Bueno");
        assertEquals("Gabriel Bueno", produto.getNome());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_exception_do_setNome_tamanho_menor() {
        produto.setNome("a");
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_exception_do_setNome_tamanho_maior() {
        produto.setNome("abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcaabcabcabcabcabcaabcabcabc" + 
    "abcabcaabcabcabcabcabcabcabcabcabcabcabxc");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_id_negativo() {
        produto.setId(-7);
    }

    @Test
    public void nao_deve_aceitar_quantidade_nulo() {
        assertNotNull(produto.getQuantidade());
    }

    @Test
    public void nao_deve_aceitar_preço_nulo() {
        assertNotNull(produto.getPreco());
    }

    @Test
    public void deve_testar_o_getId_esta_funcionando_corretamente() {
        produto.setId(5);
        assertSame(5, produto.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_o_exception_do_setQuantidad() {
        produto.setQuantidade(-3);
    }

    @Test
    public void deve_testar_o_getPreço_esta_funcionando_corretamente() {
        produto.setPreco(BigDecimal.valueOf(500.00));
        assertEquals(produto.getPreco(), BigDecimal.valueOf(500.00));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_o_exception_do_setPreço() {
        produto.setPreco(BigDecimal.valueOf(-500.00));
    }
    
    @Test
    public void nao_deve_aceitar_null_na_data_criacao() {
        produto.setDataCriacao(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_data_muito_antiga() {
        MutableDateTime dataModificada = new MutableDateTime();
        dataModificada.setDate(1500, 01, 01);
        produto.setDataCriacao(dataModificada.toDateTime());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_data_no_futuro() {
        MutableDateTime dataModificada = new MutableDateTime();
        dataModificada.setDate(2100, 01, 01);
        produto.setDataCriacao(dataModificada.toDateTime());
    }

    @Test
    public void deve_testar_o_toString_preenchido() {
        produto = new Produto(1, "Processador", 2, (BigDecimal.valueOf(900)));
        String produtoToString = produto.toString();
        assertEquals(produtoToString, produto.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_o_toString_nullo() {
        produto = new Produto(0, null, 1, (BigDecimal.valueOf(1)));
        produto.toString();
    }

    @Test
    public void deve_retornar_true_no_hashCode_com_produtos_iguais() {
        Produto outroProduto = produto;
        assertEquals(produto.hashCode(), outroProduto.hashCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_false_no_hashCode_com_um_produto_de_id_null() {
        Produto outroProduto = new Produto(null, "teste", 5, BigDecimal.valueOf(500.00));
        
        assertNotEquals(produto.hashCode(), outroProduto.hashCode());
    }

    @Test
    public void deve_retornar_true_no_equals_com_produtos_iguais() {
        Produto outroProduto = produto;
        assertEquals(produto, outroProduto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_false_no_equals_com_um_produto_de_id_null() {
        Produto outroProduto = new Produto(null, "teste", 8, BigDecimal.valueOf(500.00));
        assertNotEquals(produto, outroProduto);
    }

    @Test
    public void deve_retornar_true_no_equals_comparando_um_produto_com_ele_mesmo() {
        assertEquals(produto, produto);
    }

    @Test
    public void deve_retornar_false_no_equals_comparando_um_produto_com_null() {
        assertNotEquals(produto, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_true_no_equals_comparando_dois_produtos_de_id_null() {
        Produto produto1 = new Produto(null, "teste", 3, BigDecimal.valueOf(500.00));
        Produto produto2 = new Produto(null, "teste", 3, BigDecimal.valueOf(500.00));
        assertEquals(produto1, produto2);
    }

    @Test
    public void deve_retornar_false_no_equals_com_produtos_de_ids_diferentes() {
        Produto produto1 = new Produto(2, "teste");
        Produto produto2 = new Produto(3, "teste");
        assertNotEquals(produto1, produto2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_false_no_equals_com_um_id_null_e_outro_preenchido() {
        Produto produto1 = new Produto(null, "teste", 2, BigDecimal.valueOf(500.00));
        Produto produto2 = new Produto(3, "teste", 2, BigDecimal.valueOf(500.00));
        assertNotEquals(produto1, produto2);
    }

    @Test
    public void deve_retornar_false_no_equals_com_um_produto_e_um_objeto_aleatorio() {
        assertNotEquals(produto, new Object());
    }

    @Test
    public void simpleEqualsContract() {
        EqualsVerifier.simple().forClass(Produto.class).verify();
    }

    @After
    public void tearDown() {
    }

    @AfterClass
    public static void tearDownAfterClass() {
    }

}
