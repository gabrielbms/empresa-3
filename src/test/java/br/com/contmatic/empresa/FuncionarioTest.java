package br.com.contmatic.empresa;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
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
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.util.Annotations;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

/**
 * The Class FuncionarioTest.
 */
@FixMethodOrder(NAME_ASCENDING)
public class FuncionarioTest {

    /** The funcionario. */
    private static Funcionario funcionario;
    
    /** The validator. */
    private Validator validator;
    
    /** The factory. */
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
        funcionario = Fixture.from(Funcionario.class).gimme("valido");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }
    
    public boolean isValid(Funcionario funcionario, String mensagem) {
		validator = factory.getValidator();
		boolean valido = true;
		Set<ConstraintViolation<Funcionario>> restricoes = validator.validate(funcionario);
		for (ConstraintViolation<Funcionario> constraintViolation : restricoes)
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
        assertNotNull(funcionario.getCpf());
    }
    
    /**
     * Deve testar o get cpf esta funcionando corretamente.
     */
    @Test
    public void deve_testar_se_o_getCpf_esta_funcionando_corretamente() {
        funcionario.setCpf("43701888818");
        assertThat(funcionario.getCpf(), containsString("43701888818"));
    }
    
    /**
     * Nao deve aceitar espacos em branco no cpf.
     */
    @Test
    public void nao_deve_aceitar_espacos_em_branco_no_cpf() {
        assertFalse(funcionario.getCpf().trim().isEmpty());
    }
    
    /**
     * Deve validar cpf annotations.
     */
    @Test
    public void deve_validar_cpf_annotations() {
        Funcionario cadastroValidator = Fixture.from(Funcionario.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getCpf()));
    }
    
    /* TESTES NO NOME */

    /**
     * Nao deve aceitar nome nulo.
     */
    @Test
	public void nao_deve_aceitar_nome_nulo() {
		funcionario.setNome(null);
		assertFalse(isValid(funcionario, "O campo nome não pode estar vazio"));
	}
    
    /**
     * Deve testar o get nome esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_getNome_esta_funcionando_corretamente() {
        funcionario.setNome("Gabriel Bueno");
        assertThat(funcionario.getNome(), containsString("Gabriel Bueno"));
    }
    
    /**
     * Nao deve aceitar nome curto.
     */
    @Test
    public void nao_deve_aceitar_nome_curto() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Gabriel");
        assertTrue(funcionario.getNome().equals("Gabriel"));
    }

	@Test
	public void deve_aceitar_nome_valido() {
		funcionario.setNome("Gabriel");
		assertTrue(isValid(funcionario, "O campo nome não pode estar vazio"));
	}
	
	 /**
     * Nao deve aceitar espacos em branco no nome.
     */
    @Test
    public void nao_deve_aceitar_espacos_em_branco_no_nome() {
        assertFalse(funcionario.getNome().trim().isEmpty());
    }

	@Test
	public void deve_aceitar_nome_sem_espaco() {
		funcionario.setNome("GabrielBueno");
		assertTrue(isValid(funcionario, "O nome do funcionário está incorreto"));
	}

	@Test
	public void deve_aceitar_nome_com_acento() {
		funcionario.setNome("João");
		assertTrue(isValid(funcionario, "O nome do funcionário está incorreto"));
	}

	@Test
	public void deve_aceitar_nome_com_cedilha() {
		funcionario.setNome("Maria Conceição");
		assertTrue(isValid(funcionario, "O nome do funcionário está incorreto"));
	}

	@Test
	public void deve_aceitar_nome_com_espaco() {
		funcionario.setNome("Gabriel Bueno");
		assertTrue(isValid(funcionario, "O nome do funcionário está incorreto"));
	}

	@Test
	public void nao_deve_aceitar_nome_com_arroba() {
		funcionario.setNome("G@briel");
		assertFalse(isValid(funcionario, "O nome do funcionário está incorreto"));
	}
    
    /**
     * Deve validar nome annotations.
     */
    @Test
    public void deve_validar_nome_annotations() {
        Funcionario cadastroValidator = Fixture.from(Funcionario.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getNome()));
    }
	
    /* TESTES NA IDADE */
	
	@Test
	public void nao_deve_aceitar_idade_negativa() {
		funcionario.setIdade(-19);
		assertFalse(isValid(funcionario, "A idade do funcionario não pode ser menor que 1"));
	}
	
	/**
     * Deve testar o get idade esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_getIdade_esta_funcionando_corretamente() {
        funcionario.setIdade(19);
        assertThat(funcionario.getIdade(), is(19));
    }

	@Test
	public void nao_deve_aceitar_idade_igual_a_zero() {
		funcionario.setIdade(0);
		assertFalse(isValid(funcionario, "A idade do funcionario não pode ser menor que 1"));
	}

	@Test
	public void deve_aceitar_idade_valida() {
		funcionario.setIdade(19);
		assertTrue(isValid(funcionario, "A idade do funcionario não pode ser negativa."));
	}
    
    /**
     * Deve validar idade annotations.
     */
    @Test
    public void deve_validar_idade_annotations() {
        Funcionario cadastroValidator = Fixture.from(Funcionario.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getIdade()));
    }

    /* TESTES NO TELEFONE */
	
	@Test
	public void nao_deve_aceitar_telefone_nulo() {
		funcionario.setTelefones(null);
		assertFalse(isValid(funcionario, "O telefone do funcionario não pode ser vazio"));
	}
	
	@Test
	public void nao_deve_aceitar_telefone_vazio() {
		funcionario.setTelefones(new HashSet<Telefone>());
		assertFalse(isValid(funcionario, "os telefones do funcionario não devem ser menor que um"));
	}
	
	/**
     * Deve testar o set telefones.
     */
    @Test
    public void deve_testar_o_setTelefones() {
        Set<Telefone> telefone = new HashSet<>();
        telefone.add(Fixture.from(Telefone.class).gimme("valido"));
        funcionario.setTelefones(telefone);
        assertTrue(funcionario.equals(funcionario));
    }
    
    /**
     * Deve validar telefones annotations.
     */
    @Test
    public void deve_validar_telefones_annotations() {
        Funcionario cadastroValidator = Fixture.from(Funcionario.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getTelefone()));
    }
	
    /* TESTES NO ENDEREÇO */
	
	@Test
	public void nao_deve_aceitar_endereco_nulo() {
		funcionario.setEnderecos(null);
		assertFalse(isValid(funcionario, "O endereço da empresa está vazio"));
	}
	
	/**
     * Deve testar o get endereco esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_getEndereco_esta_funcionando_corretamente() {
        funcionario.getEndereco();
        assertThat(funcionario.toString(), containsString("cep="));
    }
	
	@Test
	public void nao_deve_aceitar_endereco_vazio() {
		funcionario.setEnderecos(new HashSet<Endereco>());
		assertFalse(isValid(funcionario, "Tem que cadastrar pelo menos um endereço"));
	}
    
    /**
     * Deve testar o set endereco.
     */
    @Test
    public void deve_testar_o_setEndereco() {
        Set<Endereco> endereco = new HashSet<>();
        endereco.add(Fixture.from(Endereco.class).gimme("valido"));
        funcionario.setEnderecos(endereco);
        assertTrue(funcionario.equals(funcionario));
    }
    
    /**
     * Deve validar endereco annotations.
     */
    @Test
    public void deve_validar_endereco_annotations() {
        Funcionario cadastroValidator = Fixture.from(Funcionario.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getEndereco()));
    }
	
    /* TESTES NO SALARIO */

    /**
     * Nao deve aceitar salario nulo.
     */
    @Test
    public void nao_deve_aceitar_salario_nulo() {
        assertNotNull(funcionario.getSalario());
    }
    
    /**
     * Deve testar o get salario esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_getSalario_esta_funcionando_corretamente() {
        funcionario.setSalario(BigDecimal.valueOf(1500.00));
        assertThat(funcionario.getSalario(), is(BigDecimal.valueOf(1500.00)));
    }
    
    @Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_salario_negativo() {
		funcionario.setSalario(BigDecimal.valueOf(-2500.00));
		assertFalse(funcionario.getSalario() == BigDecimal.valueOf(-2500.00));
	}
    
    @Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_salario_igual_a_0() {
		funcionario.setSalario(BigDecimal.valueOf(0.00));
		assertFalse(funcionario.getSalario() == BigDecimal.valueOf(0.00));
	}
    
    @Test
	public void deve_aceitar_salario_valido() {
		funcionario.setSalario(BigDecimal.valueOf(2500.00));
		assertTrue(isValid(funcionario, "O salário do funcionário não pode ser negativo"));
	}
    
    /**
     * Deve validar salarios annotations.
     */
    @Test
    public void deve_validar_salarios_annotations() {
        Funcionario cadastroValidator = Fixture.from(Funcionario.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getSalario()));
    }
    
    /* TESTES NO DATA CONTRATACAO */
    
    /**
     * Nao deve aceitar data contratacao nulo.
     */
    @Test
	public void nao_deve_aceitar_dataContratacao_nula() {
		funcionario.setDataContratacao(null);
		assertFalse(isValid(funcionario, "A data de contratação do funcionario não deve estar nula"));
	}
    
    /**
     * Deve testar o get data contratacao esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_getDataContratacao_esta_funcionando_corretamente() {
        funcionario.setDataContratacao(new LocalDate(2019, 05, 05));
        assertThat(funcionario.toString(), containsString("2019-05-05"));
    }

	@Test
	public void nao_deve_aceitar_dataContratacao_no_futuro() {
		funcionario.setDataContratacao(new LocalDate(2048, 10, 10));
		assertFalse(isValid(funcionario, "A data de contratação não pode ser maior que a data atual"));
	}

	@Test
	public void deve_aceitar_dataContratacao_valida() {
		funcionario.setDataContratacao(new LocalDate(2014, 10, 10));
		assertTrue(isValid(funcionario, "A data de contratação não pode ser maior que a data atual"));
	}
    
    /**
     * Deve validar data contratacao annotations.
     */
    @Test
    public void deve_validar_data_contratacao_annotations() {
        Funcionario cadastroValidator = Fixture.from(Funcionario.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getDataContratacao()));
    }
    
    /* TESTES NO DATA SALARIO */
	
    /**
     * Nao deve aceitar data salario nulo.
     */
	@Test
	public void nao_deve_aceitar_dataSalario_nula() {
		funcionario.setDataSalario(null);
		assertFalse(isValid(funcionario, "A data do salário do funcionario não deve estar nula"));
	}
	
	/**
     * Deve testar o get data salario esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_getDataSalario_esta_funcionando_corretamente() {
        funcionario.setDataSalario(new LocalDate(2020, 07, 07));
        assertThat(funcionario.toString(), containsString("2020-07-07"));
    }

	@Test
	public void deve_aceitar_dataSalario_valida() {
		funcionario.setDataSalario(new LocalDate(2021, 10, 10));
		assertTrue(isValid(funcionario, "A data do salario deve ser maior que a data atual"));
	}
    
    /**
     * Deve validar data salario annotations.
     */
    @Test
    public void deve_validar_data_salario_annotations() {
        Funcionario cadastroValidator = Fixture.from(Funcionario.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getDataSalario()));
    }
	
    /* OUTROS TESTES */

	/**
     * Nao deve aceitar funcionario sem cpf nome idade telefone endereco salario.
     */
    @Test
    public void nao_deve_aceitar_Funcionario_sem_cpf_nome_idade_telefone_endereco_salario() {
        Funcionario Funcionario = new Funcionario();
        Set<ConstraintViolation<Funcionario>> restricoes = validator.validate(Funcionario);
        assertThat(restricoes, Matchers.hasSize(6));
    }
    
    /**
     * Deve retornar true no hash code com funcionarios iguais.
     */
    @Test
    public void deve_retornar_true_no_hashCode_com_funcionarios_iguais() {
        Funcionario funcionario2 = funcionario;
        assertTrue(funcionario.hashCode() == funcionario2.hashCode());
    }
    
    /**
     * Deve retornar false no hash code com um funcionario de cpf null.
     */
    @Test
    public void deve_retornar_false_no_hashCode_com_um_Funcionario_de_cpf_null() {
        Funcionario funcionario2 = new Funcionario(null, "Gabriel Bueno", BigDecimal.valueOf(1500.00));
        assertFalse(funcionario.hashCode() == funcionario2.hashCode());
    }
    
    /**
     * Deve retornar true no equals com funcionarios iguais.
     */
    @Test
    public void deve_retornar_true_no_equals_com_funcionarios_iguais() {
        Funcionario funcionario2 = funcionario;
        assertTrue(funcionario.equals(funcionario2) & funcionario2.equals(funcionario));
    }
    
    /**
     * Deve retornar false no equals com um funcionario de cpf null.
     */
    @Test
    public void deve_retornar_false_no_equals_com_um_funcionario_de_cpf_null() {
        Funcionario funcionario2 = new Funcionario(null, "Gabriel Bueno", BigDecimal.valueOf(1500.00));
        assertFalse(funcionario.equals(funcionario2) & funcionario2.equals(funcionario));
    }

    /**
     * Deve retornar true no equals comparando um funcionario com ele mesmo.
     */
    @Test
    public void deve_retornar_true_no_equals_comparando_um_funcionario_com_ele_mesmo() {
        assertTrue(funcionario.equals(funcionario));
    }

    /**
     * Deve retornar false no equals comparando um funcionarios com null.
     */
    @Test
    public void deve_retornar_false_no_equals_comparando_um_funcionarios_com_null() {
        assertFalse(funcionario.equals(null));
    }

    /**
     * Deve retornar true no equals comparando dois funcionarios de cpf null.
     */
    @Test
    public void deve_retornar_true_no_equals_comparando_dois_funcionarios_de_cpf_null() {
        Funcionario funcionario1 = new Funcionario(null, "Gabriel Bueno", BigDecimal.valueOf(1500.00));
        Funcionario funcionario2 = new Funcionario(null, "Gabriel Bueno", BigDecimal.valueOf(1500.00));
        assertTrue(funcionario1.equals(funcionario2));
    }

    /**
     * Deve retornar false no equals com funcionarios de cpf diferentes.
     */
    @Test
    public void deve_retornar_false_no_equals_com_funcionarios_de_cpf_diferentes() {
        Funcionario funcionario1 = new Funcionario("43701888817", "Gabriel Bueno", BigDecimal.valueOf(1500.00));
        Funcionario funcionario2 = new Funcionario("43701888818", "Gabriel Bueno", BigDecimal.valueOf(1500.00));
        assertFalse(funcionario2.equals(funcionario1));
    }

    /**
     * Deve retornar false no equals com funcionario e um numero aleatorio.
     */
    @Test
    public void deve_retornar_false_no_equals_com_funcionario_e_um_numero_aleatorio() {
        assertFalse(funcionario.equals(new Object()));
    }

    /**
     * To string deve retornar null.
     */
    @Test
    public void toString_deve_retornar_null() {
        Funcionario funcionarioNull = new Funcionario(null, null, 0, null, null, new BigDecimal("0"));
        assertThat(funcionarioNull.toString(), containsString("salario"));
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
    public static void tearDownAfterClass() {
    }

}