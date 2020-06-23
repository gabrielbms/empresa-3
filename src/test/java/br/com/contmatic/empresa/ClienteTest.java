package br.com.contmatic.empresa;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.math.BigDecimal;
import java.util.HashSet;
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

import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.util.Annotations;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

/**
 * The Class ClienteTest.
 */
@FixMethodOrder(NAME_ASCENDING)
public class ClienteTest {
	
    /** The cliente. */
    private static Cliente cliente;

    /** The validator. */
    private Validator validator;
    
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    /**
     * Set up before class.
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.util");
    }

    /**
     * Set up.
     */
    @Before
    public void setUp() {
        cliente = Fixture.from(Cliente.class).gimme("valido");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }
    
    public boolean isValid(Cliente cliente, String mensagem) {
		validator = factory.getValidator();
		boolean valido = true;
		Set<ConstraintViolation<Cliente>> restricoes = validator.validate(cliente);
		for (ConstraintViolation<Cliente> constraintViolation : restricoes)
			if (constraintViolation.getMessage().equalsIgnoreCase(mensagem))
				valido = false;
		return valido;
	}
    
    /* TESTES NO CPF */
    
    /**
     * Nao deve aceitar cpf nulo.
     */
    @Test
    public void nao_deve_aceitar_cpf_nulo() {
        assertNotNull(cliente.getCpf());
    }
    
    /**
     * Deve testar o get cpf esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_setCpf_esta_funcionando_corretamente() {
        cliente.setCpf("437.018.888-18");
        assertThat(cliente.getCpf(), containsString("437.018.888-18"));
    }
    
    /**
     * Nao deve aceitar espacos em branco no cpf.
     */
    @Test
    public void nao_deve_aceitar_espacos_em_branco_no_cpf() {
        assertFalse(cliente.getCpf().trim().isEmpty());
    }
    
    /**
     * Deve validar cpf annotations.
     */
    @Test
    public void deve_validar_cpf_annotations() {
        Cliente cadastroValidator = Fixture.from(Cliente.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getCpf()));
    }
    
    /* TESTES NO NOME */
    
    /**
     * Nao deve aceitar nome nulo.
     */
    @Test
	public void nao_deve_aceitar_nome_nulo() {
    	cliente.setNome(null);
		assertFalse(isValid(cliente, "O campo nome não pode estar vazio"));
	}
    
    /**
     * Deve testar o get nome esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_getNome_esta_funcionando_corretamente() {
    	cliente.setNome("Gabriel");
        assertThat(cliente.getNome(), containsString("Gabriel"));
    }
    
    /**
     * Nao deve aceitar nome curto.
     */
    @Test
    public void nao_deve_aceitar_nome_curto() {
        Cliente cliente = new Cliente();
        cliente.setNome("Gabriel");
        Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente);
        assertFalse(violations.isEmpty());
    }
    
    @Test
	public void deve_aceitar_nome_valido() {
    	cliente.setNome("Gabriel");
		assertTrue(isValid(cliente, "O campo nome não pode estar vazio"));
	}
    
    /**
     * Nao deve aceitar espacos em branco no nome.
     */
    @Test
    public void nao_deve_aceitar_espacos_em_branco_no_nome() {
        assertFalse(cliente.getNome().trim().isEmpty());
    }
    
    @Test
	public void deve_aceitar_nome_sem_espaco() {
    	cliente.setNome("GabrielBueno");
		assertTrue(isValid(cliente, "O nome do cliente está incorreto"));
	}

	@Test
	public void deve_aceitar_nome_com_acento() {
		cliente.setNome("João");
		assertTrue(isValid(cliente, "O nome do cliente está incorreto"));
	}

	@Test
	public void deve_aceitar_nome_com_cedilha() {
		cliente.setNome("Maria Conceição");
		assertTrue(isValid(cliente, "O nome do cliente está incorreto"));
	}

	@Test
	public void deve_aceitar_nome_com_espaco() {
		cliente.setNome("Gabriel Bueno");
		assertTrue(isValid(cliente, "O nome do cliente está incorreto"));
	}

	@Test
	public void nao_deve_aceitar_nome_com_arroba() {
		cliente.setNome("G@briel");
		assertFalse(isValid(cliente, "O nome do cliente está incorreto"));
	}
    
    /**
     * Deve validar nome annotations.
     */
    @Test
    public void deve_validar_nome_annotations() {
        Cliente cadastroValidator = Fixture.from(Cliente.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getNome()));
    }
    
    /* TESTES NO EMAIL */
    
    /**
     * Nao deve aceitar email nulo.
     */
    @Test
    public void nao_deve_aceitar_email_nulo() {
        assertNotNull(cliente.getEmail());
    }
    
    /**
     * Nao deve aceitar espacos em branco no email.
     */
    @Test
    public void nao_deve_aceitar_espacos_em_branco_no_email() {
        assertFalse(cliente.getEmail().trim().isEmpty());
    }
    
    /**
     * Deve testar o get email esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_setEmail_esta_funcionando_corretamente() {
        cliente.setEmail("bueno@hotmail.com.br");
        assertThat(cliente.getEmail(), containsString("bueno@hotmail.com.br"));
    }
    
    /**
     * Deve validar email annotations.
     */
    @Test
    public void deve_validar_email_annotations() {
        Cliente cadastroValidator = Fixture.from(Cliente.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getEmail()));
    }

	@Test
	public void nao_deve_aceitar_email_com_acento() {
    	cliente.setEmail("joãolindão@bol.com.br");
		assertFalse(isValid(cliente, "O email do cliente está invalido"));
	} 

	@Test
	public void nao_deve_aceita_email_com_cedilha() {
    	cliente.setEmail("maria_conceição@uol.com.br");
    	assertFalse(isValid(cliente, "O email do cliente está invalido"));
	} 

	@Test
	public void nao_deve_aceitar_email_com_espaco() {
    	cliente.setEmail("email com espaços@gmail.com");
    	assertFalse(isValid(cliente, "O email do cliente está invalido"));
	} 
    
    /* TESTES NO TELEFONE */
    
    /**
     * Nao deve aceitar telefone nulo.
     */
	@Test
	public void nao_deve_aceitar_telefone_nulo() {
		cliente.setTelefones(null);
		assertFalse(isValid(cliente, "O telefone do cliente não pode ser vazio"));
	}
	
	@Test
	public void nao_deve_aceitar_telefone_vazio() {
		cliente.setTelefones(new HashSet<Telefone>());
		assertFalse(isValid(cliente, "os telefones do cliente não devem ser menor que um"));
	}
	
	/**
     * Deve testar o set telefones.
     */
    @Test
    public void deve_testar_o_setTelefones() {
        Set<Telefone> telefone = new HashSet<>();
        telefone.add(Fixture.from(Telefone.class).gimme("valido"));
        cliente.setTelefones(telefone);
        assertTrue(cliente.equals(cliente));
    }
    
    /**
     * Deve validar telefones annotations.
     */
    @Test
    public void deve_validar_telefones_annotations() {
        Cliente cadastroValidator = Fixture.from(Cliente.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getTelefone()));
    }
    
    /**
     * Deve testar o get telefone esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_setTelefone_esta_funcionando_corretamente() {
        cliente = Fixture.from(Cliente.class).gimme("valido");
        assertTrue(cliente.equals(cliente));
    }
    
    /* TESTES NO BOLETO */
    
    /**
     * Nao deve aceitar boleto nulo.
     */
    @Test
    public void nao_deve_aceitar_boleto_nulo() {
        assertNotNull(cliente.getBoleto());
    }
    
    /**
     * Deve testar o get boleto esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_setBoleto_esta_funcionando_corretamente() {
        cliente.setBoleto(BigDecimal.valueOf(250.00));
        assertThat(cliente.getBoleto(), is(BigDecimal.valueOf(250.00)));
    }
    
    @Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_boleto_negativo() {
    	cliente.setBoleto(BigDecimal.valueOf(-200.00));
		assertFalse(cliente.getBoleto() == BigDecimal.valueOf(-200.00));
	}
    
    @Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_boleto_igual_a_0() {
    	cliente.setBoleto(BigDecimal.valueOf(0.00));
		assertFalse(cliente.getBoleto() == BigDecimal.valueOf(0.00));
	}
    
    @Test
	public void deve_aceitar_boleto_valido() {
    	cliente.setBoleto(BigDecimal.valueOf(200.00));
		assertTrue(isValid(cliente, "O boleto do cliente não pode ser negativo"));
	}
    
    /**
     * Deve validar boletos annotations.
     */
    @Test
    public void deve_validar_boletos_annotations() {
        Cliente cadastroValidator = Fixture.from(Cliente.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getBoleto()));
    }
    
    /* OUTROS TESTES */

    /**
     * Deve retornar true no hash code com clientes iguais.
     */
    @Test
    public void deve_retornar_true_no_hashCode_com_clientes_iguais() {
        Cliente cliente01 = cliente;
        assertTrue(cliente.hashCode() == cliente01.hashCode());
    }

    /**
     * Deve retornar true no equals com clientes iguais.
     */
    @Test
    public void deve_retornar_true_no_equals_com_clientes_iguais() {
        Cliente cliente01 = cliente;
        assertTrue(cliente.equals(cliente01) & cliente01.equals(cliente));
    }

    /**
     * Deve retornar false no equals com um cliente de cpf null.
     */
    @Test
    public void deve_retornar_false_no_equals_com_um_cliente_de_cpf_null() {
        Cliente cliente2 = null;
        assertFalse(cliente.equals(cliente2));
    }

    /**
     * Deve retornar true no equals comparando um cliente com ele mesmo.
     */
    @Test
    public void deve_retornar_true_no_equals_comparando_um_cliente_com_ele_mesmo() {
        assertTrue(cliente.equals(cliente));
    }

    /**
     * Deve retornar false no equals comparando um cliente com null.
     */
    @Test
    public void deve_retornar_false_no_equals_comparando_um_cliente_com_null() {
        assertFalse(cliente.equals(null));
    }

    /**
     * Deve retornar false no equals com clientes de cpf diferentes.
     */
    @Test
    public void deve_retornar_false_no_equals_com_clientes_de_cpf_diferentes() {
        Cliente cliente1 = Fixture.from(Cliente.class).gimme("valido");
        cliente1.setCpf("12345678912");
        Cliente cliente2 = Fixture.from(Cliente.class).gimme("valido");
        cliente2.setCpf("98765432198");
        assertFalse(cliente2.equals(cliente1));
    }

    /**
     * Deve retornar false no equals com clientes e um dado aleatorio.
     */
    @Test
    public void deve_retornar_false_no_equals_com_clientes_e_um_dado_aleatorio() {
        assertFalse(cliente.equals(new Object()));
    }

    /**
     * To string deve retornar null.
     */
    @Test
    public void toString_deve_retornar_null() {
        Cliente clienteNull = new Cliente(null, null, null, null);
        assertThat(clienteNull.toString(), containsString("null"));
    }

    /**
     * To string deve retornar valores preenchidos.
     */
    @Test
    public void toString_deve_retornar_valores_preenchidos() {
        cliente = Fixture.from(Cliente.class).gimme("valido");
        assertThat(cliente.toString(), is(cliente.toString()));
    }
    
    /**
     * Deve gerar dados validos.
     */
    @Test
    public void deve_gerar_dados_validos() {
        Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate(cliente);
        assertEquals(0, constraintViolations.size());
    }


    /**
     * Nao deve aceitar cliente sem cpf nome telefone boleto.
     */
    @Test
    public void nao_deve_aceitar_cliente_sem_cpf_nome_telefone_boleto() {
        Cliente cliente = new Cliente();
        Set<ConstraintViolation<Cliente>> restricoes = validator.validate(cliente);
        assertThat(restricoes, Matchers.hasSize(4));
    }

    /**
     * Deve passar na validacao com cpf nome telefone boleto informados.
     */
    @Test
    public void deve_passar_na_validacao_com_cpf_nome_telefone_boleto_informados() {
        cliente = Fixture.from(Cliente.class).gimme("valido");
        Set<ConstraintViolation<Cliente>> restricoes = validator.validate(cliente);
        assertThat(restricoes, empty());
    }

    /**
     * Tear down.
     */
    @After
    public void tearDown() {

    }

    /**
     * Tear down after class.
     */
    @AfterClass
    public static void TearDownAfterClass() {
    }
}
