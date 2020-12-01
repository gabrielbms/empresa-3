package br.com.contmatic.endereco;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.contmatic.easyRandom.EasyRandomClass;
import nl.jqno.equalsverifier.EqualsVerifier;

/**
 * The Class EnderecoTest.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EnderecoTest {

    /** The endereco. */
    private static Endereco endereco;

    private static EstadoType estado = EstadoType.SP;

    private static EasyRandomClass randomObject = EasyRandomClass.InstanciaEasyRandomClass();

    /**
     * Set up.
     */
    @Before
    public void setUp() {
        EnderecoTest.endereco = randomObject.enderecoRandomizerClass();
    }

    @Test
    public void deve_testar_se_o_cep_aceita_numeros() {
        endereco.setCep("04517020");
        assertEquals("04517020", endereco.getCep());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_null_no_cep() {
        endereco.setCep(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_vazio_no_cep() {
        endereco.setCep("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espacos_em_branco_no_cep() {
        endereco.setCep("  ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_letras_no_cep() {
        endereco.setCep("abcdefabcde");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_caracteres_especiais_no_cep() {
        endereco.setCep("@#$");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espaco_no_inicio_do_cep() {
        endereco.setCep(" 04517020");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espaco_no_final_do_cep() {
        endereco.setCep("04517020 ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espacos_no_meio_do_cep() {
        endereco.setCep("0451   7020");
    }

    @Test
    public void deve_testar_o_setCep() {
        endereco.setCep("04517020");
        assertEquals("04517020", endereco.getCep());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_exception_do_setCep_tamanho_menor() {
        endereco.setCep("666666");
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_exception_do_setCep_tamanho_maior() {
        endereco.setCep("121212121212");
    }

    @Test
    public void deve_testar_se_o_rua_aceita_numeros() {
        endereco.setRua("Jose Ramon");
        assertEquals("Jose Ramon", endereco.getRua());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_null_na_rua() {
        endereco.setRua(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_vazio_na_rua() {
        endereco.setRua("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espacos_em_branco_na_rua() {
        endereco.setRua("  ");
    }

    @Test
    public void deve_testar_se_o_rua_aceita_letras() {
        endereco.setRua("Jose Ramon");
        assertEquals("Jose Ramon", endereco.getRua());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_caracteres_especiais_na_rua() {
        endereco.setRua("@#$");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espaco_no_inicio_da_rua() {
        endereco.setRua(" 04517020");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espaco_no_fim_da_rua() {
        endereco.setRua("04517020 ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espacos_no_meio_da_rua() {
        endereco.setRua("0451   7020");
    }

    @Test
    public void deve_testar_o_getRua() {
        endereco.setRua("Jose Josue");
        assertEquals("Jose Josue", endereco.getRua());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_exception_do_setRua_tamanho_menor() {
        endereco.setRua("z");
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_exception_do_setRua_tamanho_maior() {
        endereco.setRua("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    }

    @Test
    public void deve_testar_se_o_complemento_aceita_letras() {
        endereco.setComplemento("NC");
        assertEquals("NC", endereco.getComplemento());
    }

    @Test
    public void deve_testar_se_o_complemento_aceita_numeros() {
        endereco.setComplemento("123");
        assertEquals("123", endereco.getComplemento());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_no_no_complemento() {
        endereco.setComplemento(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_vazio_no_complemento() {
        endereco.setComplemento("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espacos_em_branco_no_complemento() {
        endereco.setComplemento("  ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_caracteres_especiais_no_complemento() {
        endereco.setComplemento("@#$");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espaco_no_inicio_do_complemento() {
        endereco.setComplemento(" 04517020");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espacos_no_final_do_complemento() {
        endereco.setComplemento("04517020 ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espacos_no_meio_do_complemento() {
        endereco.setComplemento("0451   7020");
    }

    @Test
    public void deve_testar_o_setComplemento() {
        endereco.setComplemento("Jose Josue");
        assertEquals("Jose Josue", endereco.getComplemento());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_exception_do_setComplemento_tamanho_menor() {
        endereco.setComplemento("z");
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_exception_do_setComplemento_tamanho_maior() {
        endereco.setComplemento("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    }

    @Test
    public void deve_testar_se_o_bairro_aceita_numeros() {
        endereco.setBairro("04517020");
        assertEquals("04517020", endereco.getBairro());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_null_no_bairro() {
        endereco.setBairro(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_vazio_no_bairro() {
        endereco.setBairro("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espacos_em_branco_no_bairro() {
        endereco.setBairro("  ");
    }

    @Test
    public void deve_testar_se_o_bairro_aceita_letras() {
        endereco.setBairro("Jardim Rosangela");
        assertEquals("Jardim Rosangela", endereco.getBairro());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_caracteres_especiais_no_bairro() {
        endereco.setBairro("@#$");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espaco_no_inicio_do_bairro() {
        endereco.setBairro(" 04517020");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espaco_no_final_do_bairro() {
        endereco.setBairro("04517020 ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_se_o_bairro_aceita_muitos_espacos_entre_os_numeros() {
        endereco.setBairro("0451   7020");
    }

    @Test
    public void deve_testar_o_getBairro() {
        endereco.setBairro("Jose Josue");
        assertEquals("Jose Josue", endereco.getBairro());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_exception_do_setBairro_tamanho_menor() {
        endereco.setBairro("z");
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_exception_do_setBairro_tamanho_maior() {
        endereco.setBairro("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_null_na_cidade() {
        endereco.setCidade(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_vazio_na_cidade() {
        endereco.setCidade("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espacos_em_branco_na_cidade() {
        endereco.setCidade("  ");
    }

    @Test
    public void deve_testar_se_o_cidade_aceita_letras() {
        endereco.setCidade("Curitiba");
        assertEquals("Curitiba", endereco.getCidade());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_caracteres_especiais_na_cidade() {
        endereco.setCidade("@#$");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espaco_no_inicio_da_cidade() {
        endereco.setCidade(" 04517020");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espaco_no_final_da_cidade() {
        endereco.setCidade("04517020 ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_aceitar_espacos_no_meio_da_cidade() {
        endereco.setCidade("0451   7020");
    }

    @Test
    public void deve_testar_o_getCidade() {
        endereco.setCidade("Jose Josue");
        assertEquals("Jose Josue", endereco.getCidade());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_exception_do_setCidade_tamanho_menor() {
        endereco.setCidade("z");
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_exception_do_setCidade_tamanho_maior() {
        endereco.setCidade("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_se_o_estado_aceita_null() {
        endereco.setEstado(null);
    }

    @Test
    public void deve_testar_o_getNumero() {
        endereco.setNumero(2);
        assertEquals(2, endereco.getNumero());
    }

    @Test
    public void deve_testar_o_getEstado() {
        endereco.setEstado(estado);
        assertEquals(estado, endereco.getEstado());
    }

    @Test
    public void deve_retornar_true_no_hashCode_com_enderecos_iguais() {
        assertEquals(endereco.hashCode(), endereco.hashCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_false_no_hashCode_com_um_endereco_de_cep_null() {
        Endereco endereco2 = new Endereco(null, "a", 1, "sn", "a", "a", estado);
        assertNotEquals(endereco.hashCode(), endereco2.hashCode());
    }

    @Test
    public void deve_retornar_true_no_equals_com_enderecos_iguais() {
        assertEquals(endereco, endereco);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_false_no_equals_com_um_endereco_de_cep_null() {
        Endereco endereco2 = new Endereco(null, "a", 1, "sn", "a", "a", estado);
        assertNotEquals(endereco, endereco2);
    }

    @Test
    public void deve_retornar_true_no_equals_comparando_um_endereco_com_ele_mesmo() {
        assertEquals(endereco, endereco);
    }

    @Test
    public void deve_retornar_false_no_equals_comparando_um_endereco_com_null() {
        assertNotEquals(endereco, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_true_no_equals_comparando_dois_enderecos_de_cep_null() {
        Endereco endereco1 = new Endereco(null, "a", 1, "sn", "a", "a", estado);
        Endereco endereco2 = new Endereco(null, "a", 1, "sn", "a", "a", estado);
        assertEquals(endereco1, endereco2);
    }

    @Test
    public void deve_retornar_false_no_equals_com_enderecos_de_ceps_diferentes() {
        Endereco endereco1 = new Endereco("03806040", 777);
        Endereco endereco2 = new Endereco("03806050", 767);
        assertNotEquals(endereco1, endereco2);
    }

    @Test
    public void deve_retornar_false_no_equals_com_endereco_e_um_numero_aleatorio() {
        assertNotEquals(endereco, new Object());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_tentar_setar_o_contrutor_null() {
        Endereco outroEndereco = new Endereco(null, null);
        assertEquals(null, outroEndereco);
    }

    @Test(expected = IllegalArgumentException.class)
    public void toString_deve_retornar_null() {
        Endereco enderecoNull = new Endereco(null, null, 0, null, null, null, null);
        String enderecoNullToString = enderecoNull.toString();
        assertEquals(enderecoNull.toString(), enderecoNullToString);
    }

    @Test
    public void toString_deve_retornar_preenchido() {
        Endereco enderecoPreenchido = new Endereco("03806040", "Rua Joseph pequeno Joseph", 777, "Rua Joseph pequeno Joseph", "Jardim Santo Eduardo", "São Paulo", estado);
        String enderecoPreenchidoToString = enderecoPreenchido.toString();
        assertEquals(enderecoPreenchido.toString(), enderecoPreenchidoToString);
    }

    @Test
    public void simpleEqualsContract() {
        EqualsVerifier.simple().forClass(Endereco.class).verify();
    }

    /**
     * Tear down.
     */
    @After
    public void tearDown() {
    }

}
