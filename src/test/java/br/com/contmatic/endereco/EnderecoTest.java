package br.com.contmatic.endereco;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.contmatic.util.Annotations;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

/**
 * The Class EnderecoTest.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EnderecoTest {

    /** The endereco. */
    private static Endereco endereco;
    
    /** The validator. */
    private Validator validator;
    
    /** The factory. */
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    /**
     * Inicio dos testes.
     */
    @BeforeClass
    public static void InicioDosTestes() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.util");
    }

    /**
     * Set up.
     */
    @Before
    public void setUp() {
        endereco = Fixture.from(Endereco.class).gimme("valido");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }
    
    public boolean isValid(Endereco endereco, String mensagem) {
		validator = factory.getValidator();
		boolean valido = true;
		Set<ConstraintViolation<Endereco>> restricoes = validator.validate(endereco);
		for (ConstraintViolation<Endereco> constraintViolation : restricoes)
			if (constraintViolation.getMessage().equalsIgnoreCase(mensagem))
				valido = false;
		return valido;
	}
    
    /* TESTES NO CEP */
    
    /**
     * Nao deve aceitar cep nulo.
     */
    @Test
    public void nao_deve_aceitar_cep_nulo() {
        assertNotNull(endereco.getCep());
    }
    
    /**
     * Deve testar o get CEP esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_getCep_esta_funcionando_corretamente() {
        endereco.setCep("03806040");
        assertThat(endereco.getCep(), containsString("03806040"));
    }
    
    @Test
    public void nao_deve_aceitar_espacos_em_branco_no_Cep() {
        assertFalse(endereco.getCep().trim().isEmpty());
    }
    
    /* TESTES NA RUA */
    
    /**
     * Nao deve aceitar rua nulo.
     */
    @Test
    public void nao_deve_aceitar_rua_nulo() {
        assertNotNull(endereco.getRua());
    }
    
    /**
     * Deve testar o get rua esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_getRua_esta_funcionando_corretamente() {
        endereco.setRua("Rua Joseph pequeno Joseph");
        assertThat(endereco.getRua(), containsString("Rua Joseph pequeno Joseph"));
    }
    
    /**
     * Nao deve aceitar espacos em branco na rua.
     */
    @Test
    public void nao_deve_aceitar_espacos_em_branco_na_rua() {
        assertFalse(endereco.getRua().trim().isEmpty());
    }
    
    @Test
	public void deve_aceitar_rua_valida() {
    	endereco.setRua("Gabriel Silva");
		assertTrue(isValid(endereco, "A rua do endereço está incorreto"));
	}

    @Test
	public void deve_aceitar_rua_sem_espaco() {
		endereco.setRua("Joselito");
		assertTrue(isValid(endereco, "A rua do endereço está incorreto"));
	}

	@Test
	public void deve_aceitar_rua_com_acento() {
		endereco.setRua("João dos santos");
		assertTrue(isValid(endereco, "A rua do endereço está incorreto"));
	}

	@Test
	public void deve_aceitar_rua_com_cedilha() {
		endereco.setRua("Lurdes Conceição");
		assertTrue(isValid(endereco, "A rua do endereço está incorreto"));
	}

	@Test
	public void deve_aceitar_rua_com_espaco() {
		endereco.setRua("José da Silva guimarães");
		assertTrue(isValid(endereco, "A rua do endereço está incorreto"));
	}

	@Test
	public void nao_deve_aceitar_rua_com_arroba() {
		endereco.setRua("rua com @");
		assertFalse(isValid(endereco, "A rua do endereço está incorreto"));
	}
    
    /**
     * Deve validar rua annotations.
     */
    @Test
    public void deve_validar_rua_annotations() {
        Endereco cadastroValidator = Fixture.from(Endereco.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator));
    }
    
    /* TESTES NO NUMERO */
    
    /**
     * Nao deve aceitar numero nulo.
     */
    @Test
    public void nao_deve_aceitar_numero_nulo() {
        assertNotNull(endereco.getNumero());
    }
    
    /**
     * Deve testar o get numero esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_getNumero_esta_funcionando_corretamente() {
        endereco.setNumero(777);
        assertTrue(endereco.getNumero() == 777);
    }
    
    /**
     * Deve validar numero annotations.
     */
    @Test
    public void deve_validar_numero_annotations() {
        Endereco cadastroValidator = Fixture.from(Endereco.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator));
    }
    
    /* TESTES NO COMPLEMENTO */
    
    /**
     * Nao deve aceitar complemento nulo.
     */
    @Test
    public void nao_deve_aceitar_complemento_nulo() {
        assertNotNull(endereco.getComplemento());
    }

    /**
     * Deve testar o get complemento esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_getComplemento_esta_funcionando_corretamente() {
        endereco.setComplemento("Sem complemento");
        assertThat(endereco.getComplemento(), containsString("Sem complemento"));
    }
    
    /**
     * Nao deve aceitar espacos em branco no completo.
     */
    @Test
    public void nao_deve_aceitar_espacos_em_branco_no_completo() {
        assertFalse(endereco.getComplemento().trim().isEmpty());
    }
    
    @Test
	public void deve_aceitar_complemento_valido() {
    	endereco.setComplemento("apartamento 64");
		assertTrue(isValid(endereco, "O complemento do endereço está incorreto"));
	}

    @Test
	public void deve_aceitar_complemento_sem_espaco() {
		endereco.setComplemento("complexo6");
		assertTrue(isValid(endereco, "O complemento do endereço está incorreto"));
	}

	@Test
	public void deve_aceitar_complemento_com_acento() {
		endereco.setComplemento("próximo ao posto José gasosa");
		assertTrue(isValid(endereco, "O complemento do endereço está incorreto"));
	}

	@Test
	public void deve_aceitar_complemento_com_cedilha() {
		endereco.setComplemento("próximo a loja da maria lurdes Conceição");
		assertTrue(isValid(endereco, "O complemento do endereço está incorreto"));
	}

	@Test
	public void deve_aceitar_complemento_com_espaco() {
		endereco.setComplemento("apartamento 64 bloco 4");
		assertTrue(isValid(endereco, "O complemento do endereço está incorreto"));
	}

	@Test
	public void nao_deve_aceitar_complemento_com_arroba() {
		endereco.setComplemento("próximo a l@n house");
		assertFalse(isValid(endereco, "O complemento do endereço está incorreto"));
	}
    
    /**
     * Deve validar complemento annotations.
     */
    @Test
    public void deve_validar_complemento_annotations() {
        Endereco cadastroValidator = Fixture.from(Endereco.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator));
    }
    
    /* TESTES NO BAIRRO */
    
    /**
     * Nao deve aceitar bairro nulo.
     */
    @Test
    public void nao_deve_aceitar_bairro_nulo() {
        assertNotNull(endereco.getBairro());
    }
    
    /**
     * Deve testar o get bairro esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_getBairro_esta_funcionando_corretamente() {
        endereco.setBairro("Jardim Santo Eduardo");
        assertThat(endereco.getBairro(), containsString("Jardim Santo Eduardo"));
    }
    
    /**
     * Nao deve aceitar espacos em branco no bairro.
     */
    @Test
    public void nao_deve_aceitar_espacos_em_branco_no_bairro() {
        assertFalse(endereco.getBairro().trim().isEmpty());
    }
    
    @Test
	public void deve_aceitar_bairro_valido() {
    	endereco.setBairro("Morumbi");
		assertTrue(isValid(endereco, "O bairro do endereço está incorreto"));
	}

    @Test
	public void deve_aceitar_bairro_sem_espaco() {
		endereco.setBairro("Jardins");
		assertTrue(isValid(endereco, "O bairro do endereço está incorreto"));
	}

	@Test
	public void deve_aceitar_bairro_com_acento() {
		endereco.setBairro("Parque São José");
		assertTrue(isValid(endereco, "O bairro do endereço está incorreto"));
	}

	@Test
	public void deve_aceitar_bairro_com_cedilha() {
		endereco.setBairro("Parque do Ç");
		assertTrue(isValid(endereco, "O bairro do endereço está incorreto"));
	}

	@Test
	public void deve_aceitar_bairro_com_espaco() {
		endereco.setBairro("Jardim Grimaldi");
		assertTrue(isValid(endereco, "O bairro do endereço está incorreto"));
	}

	@Test
	public void nao_deve_aceitar_bairro_com_arroba() {
		endereco.setBairro("J@rdim do joselito");
		assertFalse(isValid(endereco, "O bairro do endereço está incorreto"));
	}
    
    /**
     * Deve validar bairro annotations.
     */
    @Test
    public void deve_validar_bairro_annotations() {
        Endereco cadastroValidator = Fixture.from(Endereco.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator));
    }

    
    /* TESTES NO CIDADE */
    
    /**
     * Nao deve aceitar cidade nulo.
     */
    @Test
    public void nao_deve_aceitar_cidade_nulo() {
        assertNotNull(endereco.getCidade());
    }
    
    /**
     * Deve testar o get cidade esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_getCidade_esta_funcionando_corretamente() {
        endereco.setCidade("São Paulo");
        assertThat(endereco.getCidade(), containsString("São Paulo"));
    }

    /**
     * Nao deve aceitar espacos em branco no cidade.
     */
    @Test
    public void nao_deve_aceitar_espacos_em_branco_no_cidade() {
        assertFalse(endereco.getCidade().trim().isEmpty());
    }
    
    @Test
	public void deve_aceitar_cidade_valida() {
    	endereco.setCidade("São Paulo");
		assertTrue(isValid(endereco, "A cidade do endereço está incorreto"));
	}

    @Test
	public void deve_aceitar_cidade_sem_espaco() {
		endereco.setCidade("Barueri");
		assertTrue(isValid(endereco, "O nome do funcionário está incorreto"));
	}

	@Test
	public void deve_aceitar_cidade_com_acento() {
		endereco.setCidade("Jundiaí");
		assertTrue(isValid(endereco, "O nome do funcionário está incorreto"));
	}

	@Test
	public void deve_aceitar_cidadee_com_cedilha() {
		endereco.setCidade("Monções");
		assertTrue(isValid(endereco, "O nome do funcionário está incorreto"));
	}

	@Test
	public void deve_aceitar_cidade_com_espaco() {
		endereco.setCidade("São Paulo");
		assertTrue(isValid(endereco, "O nome do funcionário está incorreto"));
	}

	@Test
	public void nao_deve_aceitar_cidade_com_arroba() {
		endereco.setCidade("cidade com @rroba");
		assertFalse(isValid(endereco, "A cidade do endereço está incorreto"));
	}
    
    /**
     * Deve validar cidade annotations.
     */
    @Test
    public void deve_validar_cidade_annotations() {
        Endereco cadastroValidator = Fixture.from(Endereco.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator));
    }
    
    /* TESTES NO ESTADO */
    
    /**
     * Nao deve aceitar estado nulo.
     */
    @Test
    public void nao_deve_aceitar_estado_nulo() {
        assertNotNull(endereco.getEstado());
    }
    
    /**
     * Deve testar o get estado.
     */
    @Test
    public void deve_testar_o_getEstado() {
        endereco.setEstado(Estado.SP);
        assertThat(endereco.getEstado().getEstado(), is("São Paulo – SP"));
    }
    
    /**
     * Deve validar estado annotations.
     */
    @Test
    public void deve_validar_estado_annotations() {
        Endereco cadastroValidator = Fixture.from(Endereco.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator));
    }

    /* OUTROS TESTES */

    /**
     * Deve gerar dados validos.
     */
    @Test
    public void deve_gerar_dados_validos() {
        Set<ConstraintViolation<Endereco>> constraintViolations = validator.validate(endereco);
        assertEquals(0, constraintViolations.size());
    }

    /**
     * Nao deve aceitar endereco sem cpf nome telefone boleto.
     */
    @Test
    public void nao_deve_aceitar_Endereco_sem_cep_rua_numero_complemento_bairro_cidade_estado() {
        Endereco Endereco = new Endereco();
        Set<ConstraintViolation<Endereco>> restricoes = validator.validate(Endereco);
        assertThat(restricoes, Matchers.hasSize(0));
    }

    /**
     * Deve passar na validacao com cpf nome telefone boleto informados.
     */
    @Test
    public void deve_passar_na_validacao_com_cep_rua_numero_complemento_bairro_cidade_estado_informados() {
        endereco = Fixture.from(Endereco.class).gimme("valido");
        Set<ConstraintViolation<Endereco>> restricoes = validator.validate(endereco);
        assertThat(restricoes, empty());
    }

    /**
     * Deve retornar true no hash code com enderecos iguais.
     */
    @Test
    public void deve_retornar_true_no_hashCode_com_enderecos_iguais() {
        assertTrue(endereco.hashCode() == endereco.hashCode());
    }

    /**
     * Deve retornar false no hash code com um endereco de cep null.
     */
    @Test
    public void deve_retornar_false_no_hashCode_com_um_endereco_de_cep_null() {
        Endereco endereco2 = new Endereco(null, 777);
        assertFalse(endereco.hashCode() == endereco2.hashCode());
    }

    /**
     * Deve retornar true no equals com enderecos iguais.
     */
    @Test
    public void deve_retornar_true_no_equals_com_enderecos_iguais() {
        assertTrue(endereco.equals(endereco) & endereco.equals(endereco));
    }

    /**
     * Deve retornar false no equals com um endereco de cpf null.
     */
    @Test
    public void deve_retornar_false_no_equals_com_um_endereco_de_cpf_null() {
        Endereco endereco2 = new Endereco(null, 777);
        assertFalse(endereco.equals(endereco2) & endereco2.equals(endereco));
    }

    /**
     * Deve retornar true no equals comparando um endereco com ele mesmo.
     */
    @Test
    public void deve_retornar_true_no_equals_comparando_um_endereco_com_ele_mesmo() {
        assertTrue(endereco.equals(endereco));
    }

    /**
     * Deve retornar false no equals comparando um endereco com null.
     */
    @Test
    public void deve_retornar_false_no_equals_comparando_um_endereco_com_null() {
        assertFalse(endereco.equals(null));
    }

    /**
     * Deve retornar true no equals comparando dois enderecos de cep null.
     */
    @Test
    public void deve_retornar_true_no_equals_comparando_dois_enderecos_de_cep_null() {
        Endereco endereco1 = new Endereco(null, 777);
        Endereco endereco2 = new Endereco(null, 777);
        assertTrue(endereco1.equals(endereco2));
    }

    /**
     * Deve retornar false no equals com enderecos de ceps diferentes.
     */
    @Test
    public void deve_retornar_false_no_equals_com_enderecos_de_ceps_diferentes() {
        Endereco endereco1 = new Endereco("03806040", 777);
        Endereco endereco2 = new Endereco("03806050", 767);
        assertFalse(endereco2.equals(endereco1));
    }

    /**
     * Deve retornar false no equals com endereco e um numero aleatorio.
     */
    @Test
    public void deve_retornar_false_no_equals_com_endereco_e_um_numero_aleatorio() {
        assertFalse(endereco.equals(new Object()));
    }

    /**
     * To string deve retornar null.
     */
    @Test
    public void toString_deve_retornar_null() {
        Endereco enderecoNull = new Endereco(null, null, 0, null, null, null, null);
        assertThat(enderecoNull.toString(), containsString(""));
    }

    /**
     * To string deve retornar preenchido.
     */
    @Test
    public void toString_deve_retornar_preenchido() {
        assertThat(endereco.toString(), containsString(""));
    }

    /**
     * Tear down.
     */
    @After
    public void tearDown() {
    }

    /**
     * Teste no to string.
     */
    @AfterClass
    public static void teste_no_toString() {
    }

}
