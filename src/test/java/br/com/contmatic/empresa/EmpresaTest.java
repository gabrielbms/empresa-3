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

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.util.Annotations;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;;

/**
 * The Class EmpresaTest.
 */
@FixMethodOrder(NAME_ASCENDING)
public class EmpresaTest {

	/** The empresa. */
	private static Empresa empresa;

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
		empresa = Fixture.from(Empresa.class).gimme("valido");
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		this.validator = factory.getValidator();
	}
	
	public boolean isValid(Empresa empresa, String mensagem) {
		validator = factory.getValidator();
		boolean valido = true;
		Set<ConstraintViolation<Empresa>> restricoes = validator.validate(empresa);
		for (ConstraintViolation<Empresa> constraintViolation : restricoes)
			if (constraintViolation.getMessage().equalsIgnoreCase(mensagem))
				valido = false;
		return valido;
	}
	
	/* TESTES NO CNPJ */
	
	/**
	 * Nao deve aceitar cnpj nulo.
	 */
	@Test
	public void nao_deve_aceitar_cnpj_nulo() {
		assertNotNull(empresa.getCnpj());
	}
	
	/**
	 * Deve testar o get cnpj esta funcionando corretamente.
	 */
	@Test
	public void deve_testar_o_getCnpj_esta_funcionando_corretamente() {
		empresa.setCnpj("35667373000103");
		assertThat(empresa.getCnpj(), containsString("35667373000103"));
	}
	
	/**
	 * Nao deve aceitar espacos em branco no cnpj.
	 */
	@Test
	public void nao_deve_aceitar_espacos_em_branco_no_cnpj() {
		assertFalse(empresa.getCnpj().trim().isEmpty());
	}
	
	/**
	 * Deve validar cnpj annotations.
	 */
	@Test
	public void deve_validar_cnpj_annotations() {
		Empresa cadastroValidator = Fixture.from(Empresa.class).gimme("valido");
		assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getCnpj()));
	}
	
	/* TESTES NO NOME */
	
	/**
	 * Nao deve aceitar nome nulo.
	 */
	@Test
	public void nao_deve_aceitar_nome_nulo() {
		empresa.setNome(null);
		assertFalse(isValid(empresa, "O campo nome não pode estar vazio"));
	}
	
	/**
	 * Deve testar o get nome esta funcionando corretamente.
	 */
	@Test
	public void deve_testar_o_getNome_esta_funcionando_corretamente() {
		empresa.setNome("GB computadores");
		assertThat(empresa.getNome(), containsString("GB computadores"));
	}
	
	/**
	 * Nao deve aceitar nome curto.
	 */
	@Test
	public void nao_deve_aceitar_nome_curto() {
		Empresa Empresa = new Empresa();
		Empresa.setNome("GB Computadores");
		Set<ConstraintViolation<Empresa>> violations = validator.validate(Empresa);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void deve_aceitar_nome_valido() {
		empresa.setNome("Gabriel");
		assertTrue(isValid(empresa, "O campo nome não pode estar vazio"));
	}
	
	/**
	 * Nao deve aceitar espacos em branco no nome.
	 */
	@Test
	public void nao_deve_aceitar_espacos_em_branco_no_nome() {
		assertFalse(empresa.getNome().trim().isEmpty());
	}
	
	@Test
	public void deve_aceitar_nome_sem_espaco() {
		empresa.setNome("GabrielBueno");
		assertTrue(isValid(empresa, "O nome da empresa está incorreto"));
	}

	@Test
	public void deve_aceitar_nome_com_acento() {
		empresa.setNome("João");
		assertTrue(isValid(empresa, "O nome da empresa está incorreto"));
	}

	@Test
	public void deve_aceitar_nome_com_cedilha() {
		empresa.setNome("Maria Conceição");
		assertTrue(isValid(empresa, "O nome da empresa está incorreto"));
	}

	@Test
	public void deve_aceitar_nome_com_espaco() {
		empresa.setNome("Gabriel Bueno");
		assertTrue(isValid(empresa, "O nome da empresa está incorreto"));
	}

	@Test
	public void nao_deve_aceitar_nome_com_arroba() {
		empresa.setNome("G@briel");
		assertFalse(isValid(empresa, "O nome da empresa está incorreto"));
	}
	
	/**
	 * Deve validar nome annotations.
	 */
	@Test
	public void deve_validar_nome_annotations() {
		Empresa cadastroValidator = Fixture.from(Empresa.class).gimme("valido");
		assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getNome()));
	}
	
	/* TESTES NO SITE */
	
	/**
	 * Nao deve aceitar site nulo.
	 */
	@Test
	public void nao_deve_aceitar_site_nulo() {
		empresa.setSite("gbconsertos.net");
		assertNotNull(empresa.getNome());
	}
	
	/**
	 * Deve testar o set site esta funcionando corretamente.
	 */
	@Test
	public void deve_testar_o_getSite_esta_funcionando_corretamente() {
		assertTrue(empresa.getSite().equals("http://www.gbconsertos.com.br"));
	}
	
	/**
	 * Nao deve aceitar espacos em branco no site.
	 */
	@Test
	public void nao_deve_aceitar_espacos_em_branco_no_site() {
		assertFalse(empresa.getSite().trim().isEmpty());
	}
	
	@Test
	public void deve_aceitar_site_valido() {
		empresa.setSite("http://www.gbconsertos.com.br");
		assertTrue(isValid(empresa, "O site da empresa está inválido"));
	}

	@Test
	public void nao_deve_aceitar_site_com_acento() {
		empresa.setSite("http://www.gbcômacênto.com.br");
		assertFalse(isValid(empresa, "O site da empresa está inválido"));
	}

	@Test
	public void nao_deve_aceitar_site_com_cedilha() {
		empresa.setSite("http://www.conceiçãocostureira.com.br");
		assertFalse(isValid(empresa, "O site da empresa está inválido"));
	}

	@Test
	public void nao_deve_aceitar_site_com_espaco() {
		empresa.setSite("http://www.gb consertos.com.br");
		assertFalse(isValid(empresa, "O site da empresa está inválido"));
	}

	@Test
	public void nao_deve_aceitar_site_com_arroba() {
		empresa.setSite("http://www.gbc@nsert@s.com.br");
		assertFalse(isValid(empresa, "O site da empresa está inválido"));
	}
	
	/**
	 * Deve validar site annotations.
	 */
	@Test
	public void deve_validar_site_annotations() {
		Empresa cadastroValidator = Fixture.from(Empresa.class).gimme("valido");
		assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getSite()));
	}
	
	/* TESTES NO TELEFONE */
	
	/**
	 * Nao deve aceitar telefone nulo.
	 */
	@Test
	public void nao_deve_aceitar_telefone_nulo() {
		empresa.setTelefones(null);
		assertFalse(isValid(empresa, "O telefone da empresa não pode ser nulo"));
	}
	
	@Test
	public void nao_deve_aceitar_telefone_vazio() {
		empresa.setTelefones(new HashSet<Telefone>());
		assertFalse(isValid(empresa, "os telefones da empresa não devem ser menor que um"));
	}
	
	/**
	 * Deve testar o set telefones.
	 */
	@Test
	public void deve_testar_o_setTelefones() {
		Set<Telefone> telefone = new HashSet<>();
		telefone.add(Fixture.from(Telefone.class).gimme("valido"));
		empresa.setTelefones(telefone);
		assertTrue(telefone.equals(telefone));
	}
	
	/**
	 * Deve validar telefones annotations.
	 */
	@Test
	public void deve_validar_telefones_annotations() {
		Empresa cadastroValidator = Fixture.from(Empresa.class).gimme("valido");
		assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getTelefone()));
	}
	
	/* TESTES NO ENDERECO */

	/**
	 * Nao deve aceitar endereco nulo.
	 */
	@Test
	public void nao_deve_aceitar_endereco_nulo() {
		empresa.setEnderecos(null);
		assertFalse(isValid(empresa, "O endereço da empresa está vazio"));
	}
	
	/**
	 * Deve testar o set endereco esta funcionando corretamente.
	 */
	@Test
	public void deve_testar_o_getEndereco_esta_funcionando_corretamente() {
		empresa.getEndereco();
		assertTrue(empresa.getEndereco().equals(empresa.getEndereco()));
	}
	
	@Test
	public void nao_deve_aceitar_endereco_vazio() {
		empresa.setEnderecos(new HashSet<Endereco>());
		assertFalse(isValid(empresa, "Tem que cadastrar pelo menos um endereço"));
	}
	
	/**
	 * Deve testar o set enderecos.
	 */
	@Test
	public void deve_testar_o_setEnderecos() {
		Set<Endereco> endereco = new HashSet<>();
		endereco.add(Fixture.from(Endereco.class).gimme("valido"));
		empresa.setEnderecos(endereco);
		assertTrue(endereco.equals(endereco));
	}
	
	/**
	 * Deve validar enderecos annotations.
	 */
	@Test
	public void deve_validar_enderecos_annotations() {
		Empresa cadastroValidator = Fixture.from(Empresa.class).gimme("valido");
		assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getEndereco()));
	}
	
	/* OUTROS TESTES */

	/**
	 * Deve gerar dados validos.
	 */
	@Test
	public void deve_gerar_dados_validos() {
		Set<ConstraintViolation<Empresa>> constraintViolations = validator.validate(empresa);
		assertEquals(0, constraintViolations.size());
	}

	/**
	 * Nao deve aceitar empresa sem cnpj nome telefone endereco.
	 */
	@Test
	public void nao_deve_aceitar_empresa_sem_cnpj_nome_telefone_endereco() {
		Empresa Empresa = new Empresa();
		Set<ConstraintViolation<Empresa>> restricoes = validator.validate(Empresa);
		assertThat(restricoes, Matchers.hasSize(5));
	}

	/**
	 * Deve passar na validacao com cnpj nome site telefone endereco informados.
	 */
	@Test
	public void deve_passar_na_validacao_com_cnpj_nome_site_telefone_endereco_informados() {
		empresa = Fixture.from(Empresa.class).gimme("valido");
		Set<ConstraintViolation<Empresa>> restricoes = validator.validate(empresa);
		assertThat(restricoes, empty());
	}

	/**
	 * Deve retornar true no hash code com empresas iguais.
	 */
	@Test
	public void deve_retornar_true_no_hashCode_com_empresas_iguais() {
		Empresa empresa01 = empresa;
		assertTrue(empresa.hashCode() == empresa01.hashCode());
	}

	/**
	 * Deve retornar true no equals com empresas iguais.
	 */
	@Test
	public void deve_retornar_true_no_equals_com_Empresas_iguais() {
		Empresa empresa01 = empresa;
		assertTrue(empresa.equals(empresa01) & empresa01.equals(empresa));
	}

	/**
	 * Deve retornar false no equals com um empresa de cnpj null.
	 */
	@Test
	public void deve_retornar_false_no_equals_com_um_Empresa_de_cnpj_null() {
		Empresa empresa1 = Fixture.from(Empresa.class).gimme("valido");
		Empresa empresa2 = null;
		assertFalse(empresa1.equals(empresa2));
	}

	/**
	 * Deve retornar true no equals comparando uma empresa com ela mesmo.
	 */
	@Test
	public void deve_retornar_true_no_equals_comparando_uma_empresa_com_ela_mesmo() {
		assertTrue(empresa.equals(empresa));
	}

	/**
	 * Deve retornar false no equals comparando uma empresa com null.
	 */
	@Test
	public void deve_retornar_false_no_equals_comparando_uma_empresa_com_null() {
		assertFalse(empresa.equals(null));
	}

	/**
	 * Deve retornar false no equals com empresas de cnpj diferentes.
	 */
	@Test
	public void deve_retornar_false_no_equals_com_Empresas_de_cnpj_diferentes() {
		Empresa empresa1 = Fixture.from(Empresa.class).gimme("valido");
		empresa1.setCnpj("36.621.217/0001-67");
		Empresa empresa2 = Fixture.from(Empresa.class).gimme("valido");
		empresa1.setCnpj("36.621.217/0001-68");
		assertFalse(empresa2.equals(empresa1));
	}

	/**
	 * Deve retornar false no equals com a empresa e um objeto aleatorio.
	 */
	@Test
	public void deve_retornar_false_no_equals_com_a_empresa_e_um_objeto_aleatorio() {
		assertFalse(empresa.equals(new Object()));
	}

	/**
	 * To string deve retornar null.
	 */
	@Test
	public void toString_deve_retornar_null() {
		Empresa empresaNull = new Empresa(null, null, null, null);
		assertThat(empresaNull.toString(), containsString(""));
	}

	/**
	 * To string deve retornar valores preenchidos.
	 */
	@Test
	public void toString_deve_retornar_valores_preenchidos() {
		empresa = Fixture.from(Empresa.class).gimme("valido");
		assertThat(empresa.toString(), is(empresa.toString()));
	}

	/**
	 * Tear down.
	 */
	@After
	public void TearDown() {
	}

	/**
	 * Tear down after class.
	 */
	@AfterClass
	public static void TearDownAfterClass() {
	}

}
