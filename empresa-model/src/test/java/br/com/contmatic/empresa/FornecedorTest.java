package br.com.contmatic.empresa;

import static br.com.contmatic.telefone.TelefoneDDDType.DDD11;
import static br.com.contmatic.telefone.TelefoneType.CELULAR;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.joda.time.MutableDateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

import br.com.contmatic.easyRandom.EasyRandomClass;
import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.groups.Post;
import br.com.contmatic.groups.Put;
import br.com.contmatic.telefone.Telefone;
import nl.jqno.equalsverifier.EqualsVerifier;;

/**
 * The Class FornecedorTest.
 * 
 * @author gabriel.santos
 */
@FixMethodOrder(NAME_ASCENDING)
public class FornecedorTest {

    private static Fornecedor fornecedor;

    private Produto produto;

    Set<Produto> produtos = new HashSet<>();

    private Set<Telefone> telefones = new HashSet<>();
    
    private Set<Endereco> enderecos = new HashSet<>();

    private Validator validator;

    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    private static EasyRandomClass randomObject = EasyRandomClass.InstanciaEasyRandomClass();

    @Before
    public void setUp() {
        FornecedorTest.fornecedor = randomObject.fornecedorRandomizer();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    public boolean isValid(Fornecedor fornecedor, String mensagem) {
        validator = factory.getValidator();
        boolean valido = true;
        Set<ConstraintViolation<Fornecedor>> restricoesPost = validator.validate(fornecedor, Post.class);
        for(ConstraintViolation<Fornecedor> constraintViolation : restricoesPost)
            if (constraintViolation.getMessage().equalsIgnoreCase(mensagem))
                valido = false;

        Set<ConstraintViolation<Fornecedor>> restricoesPut = validator.validate(fornecedor, Put.class);
        for(ConstraintViolation<Fornecedor> constraintViolation : restricoesPut)
            if (constraintViolation.getMessage().equalsIgnoreCase(mensagem))
                valido = false;

        return valido;
    }

    @Test
    public void deve_testar_se_o_cnpj_aceita_numeros() {
        fornecedor.setCnpj("35667373000103");
        assertEquals("35667373000103", fornecedor.getCnpj());
    }

    @Test
    public void nao_deve_aceitar_null_no_cnpj() {
        fornecedor.setCnpj(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_vazio_no_cnpj() {
        fornecedor.setCnpj("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espacos_em_branco_no_cnpj() {
        fornecedor.setCnpj("  ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_letras_no_cnpj() {
        fornecedor.setCnpj("abcdef");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_caracteres_especiais_no_cnpj() {
        fornecedor.setCnpj("@#$");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espaco_no_inicio_do_cnpj() {
        fornecedor.setCnpj(" 35667373000103");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espacos_no_final_do_cnpj() {
        fornecedor.setCnpj("35667373000103 ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espacos_no_meio_do_cnpj() {
        fornecedor.setCnpj("3566737       3000103");
    }

    @Test
    public void deve_testar_o_setCnpj() {
        fornecedor.setCnpj("35667373000103");
        assertEquals(fornecedor.getCnpj(), ("35667373000103"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_exception_do_cnpj_tamanho_menor() {
        fornecedor.setCnpj("1313131313131");
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_exception_do_cnpj_tamanho_maior() {
        fornecedor.setCnpj("1515151515151515");
    }

    @Test(expected = IllegalStateException.class)
    public void deve_testar_exception_a_validação_do_cnpj() {
        fornecedor.setCnpj("35667373000104");
    }

    @Test
    public void deve_testar_se_o_nome_aceita_letras() {
        fornecedor.setNome("Gabriel");
        assertEquals("Gabriel", fornecedor.getNome());
    }

    @Test
    public void nao_deve_aceitar_null_no_nome() {
        fornecedor.setNome(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_vazio_no_nome() {
        fornecedor.setNome("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espacos_no_nome() {
        fornecedor.setNome("          ");
    }

    @Test
    public void nao_deve_aceitar_numeros_no_nome() {
        fornecedor.setNome("123456");
        assertEquals("123456", fornecedor.getNome());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_caracteres_especiais_no_nome() {
        fornecedor.setNome("@#$");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espaco_no_inicio_do_nome() {
        fornecedor.setNome(" Gabriel");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espaco_no_final_do_nome() {
        fornecedor.setNome("Gabriel ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espacos_no_meio_do_nome() {
        fornecedor.setNome("Gabriel         Bueno");
    }

    @Test
    public void deve_testar_se_o_nome_aceita_um_espaco_entre_as_palavras() {
        fornecedor.setNome("Gabriel Bueno");
        assertEquals("Gabriel Bueno", fornecedor.getNome());
    }

    @Test
    public void deve_testar_o_setNome() {
        fornecedor.setNome("Gabriel Bueno");
        assertEquals("Gabriel Bueno", fornecedor.getNome());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_exception_do_nome_tamanho_menor() {
        fornecedor.setNome("a");
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_exception_do_nome_tamanho_maior() {
        fornecedor.setNome("abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcaabcabcabcabcabcaabcabcabc" + 
    "abcabcaabcabcabcabcabcabcabcabcabcabcabxc");
    }

    @Test
    public void deve_testar_o_getTelefone() {
        fornecedor.setTelefones(telefones);
        assertEquals(fornecedor.getTelefone(), telefones);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_o_exception_do_setTelefone() {
        Set<Telefone> telefones2 = new HashSet<>();
        Telefone telefone1 = new Telefone(DDD11, "978457845", CELULAR);
        telefones2.add(telefone1);
        Telefone telefone2 = new Telefone(DDD11, "978457846", CELULAR);
        telefones2.add(telefone2);
        fornecedor.setTelefones(telefones2);
        assertEquals(fornecedor.getTelefone(), telefones2);
    }

    @Test
    public void deve_testar_o_getProduto() {
        produtos.add(produto);
        fornecedor.setProduto(produtos);
        assertEquals(fornecedor.getProduto(), produtos);
    }

    @Test
    public void deve_testar_o_getEndereco() {
        fornecedor.setEnderecos(enderecos);
        assertEquals(fornecedor.getEndereco(), enderecos);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_o_exception_do_setEnderecos() {
        Set<Endereco> enderecos2 = new HashSet<>();
        Endereco telefone1 = new Endereco("02514784", 25);
        enderecos2.add(telefone1);
        Endereco telefone2 = new Endereco("02514787", 29);
        enderecos2.add(telefone2);
        fornecedor.setEnderecos(enderecos2);
        assertEquals(fornecedor.getTelefone(), enderecos2);
    }
    
    @Test
    public void nao_deve_aceitar_null_na_data_criacao() {
        fornecedor.setDataCriacao(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_data_muito_antiga() {
        MutableDateTime dataModificada = new MutableDateTime();
        dataModificada.setDate(1500, 01, 01);
        fornecedor.setDataCriacao(dataModificada.toDateTime());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_data_no_futuro() {
        MutableDateTime dataModificada = new MutableDateTime();
        dataModificada.setDate(2100, 01, 01);
        fornecedor.setDataCriacao(dataModificada.toDateTime());
    }

    @Test
    public void deve_retornar_true_no_hashCode_com_fornecedor_iguais() {
        Fornecedor fornecedor2 = fornecedor;
        assertEquals(fornecedor.hashCode(), fornecedor2.hashCode());
    }

    @Test
    public void deve_retornar_false_no_hashCode_com_uma_fornecedor_de_cnpj_null() {
        Fornecedor fornecedor2 = new Fornecedor(null, "CA peças LTDA");
        assertNotEquals(fornecedor.hashCode(), fornecedor2.hashCode());
    }

    @Test
    public void deve_retornar_true_no_equals_com_fornecedores_iguais() {
        Fornecedor fornecedor2 = fornecedor;
        assertEquals(fornecedor, fornecedor2);
    }

    @Test
    public void deve_retornar_false_no_equals_com_um_fornecedor_de_cnpj_null() {
        Fornecedor fornecedor2 = new Fornecedor(null, "CA peças LTDA");
        assertNotEquals(fornecedor, fornecedor2);
    }

    @Test
    public void deve_retornar_true_no_equals_comparando_um_fornecedor_com_ela_mesmo() {
        assertEquals(fornecedor, fornecedor);
    }

    @Test
    public void deve_retornar_false_no_equals_comparando_um_fornecedor_com_null() {
        assertNotEquals(fornecedor, null);
    }

    @Test
    public void deve_retornar_true_no_equals_comparando_dois_fornecedores_de_cnpj_null() {
        Fornecedor fornecedor1 = new Fornecedor(null, "CA peças LTDA");
        Fornecedor fornecedor2 = new Fornecedor(null, "CA peças LTDA");
        assertEquals(fornecedor1, fornecedor2);
    }

    @Test
    public void deve_retornar_false_no_equals_com_fornecedores_de_cnpj_diferentes() {
        Fornecedor fornecedor1 = new Fornecedor("97904702000131", "CA peças LTDA", telefones, produtos, enderecos);
        Fornecedor fornecedor2 = new Fornecedor("43202648000153", "CA peças LTDA", telefones, produtos, enderecos);
        assertNotEquals(fornecedor2, fornecedor1);
    }

    @Test
    public void deve_retornar_false_no_equals_com_a_fornecedor_e_um_numero_aleatorio() {
        assertNotEquals(fornecedor, new Object());
    }

    @Test
    public void toString_deve_retornar_valores_preenchidos() {
        Fornecedor fornecedorPreenchido = new Fornecedor("97904702000131", "CA peças LTDA");
        String fornecedorPreenchidoToString = fornecedorPreenchido.toString();
        assertEquals(fornecedorPreenchido.toString(), fornecedorPreenchidoToString);
    }

    @Test
    public void simpleEqualsContract() {
        EqualsVerifier.simple().forClass(Fornecedor.class).verify();
    }

    @After
    public void tearDown() {
        System.out.println(fornecedor);
    }

}
