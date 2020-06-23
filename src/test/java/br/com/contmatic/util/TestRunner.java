package br.com.contmatic.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.contmatic.empresa.ClienteTest;
import br.com.contmatic.empresa.EmpresaTest;
import br.com.contmatic.empresa.FornecedorTest;
import br.com.contmatic.empresa.FuncionarioTest;
import br.com.contmatic.endereco.EnderecoTest;
import br.com.contmatic.telefone.TelefoneTest;

/**
 * The Class TestRunner.
 */
@RunWith(Suite.class)
@SuiteClasses({ ClienteTest.class, EmpresaTest.class, FornecedorTest.class, FuncionarioTest.class, EnderecoTest.class, TelefoneTest.class })
public class TestRunner {

}
